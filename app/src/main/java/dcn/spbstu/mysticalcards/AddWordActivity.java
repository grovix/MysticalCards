package dcn.spbstu.mysticalcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class AddWordActivity extends AppCompatActivity implements View.OnClickListener {

    Forms forms = new Forms();
    String str1 = new String();
    int index = -1;
    Button direction;
    Button back;
    Button findWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        str1 = getIntent().getStringExtra("name");
        EditText editText = (EditText) findViewById(R.id.editText);
        if (!str1.equals("")) {
            editText.setText(str1);
        }
        index = getIntent().getIntExtra("index", 0);

        findWord = (Button) findViewById(R.id.findWord);
        findWord.setOnClickListener(this);

        direction = (Button) findViewById(R.id.direction);
        direction.setOnClickListener(this);

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.findWord:
                EditText editText = (EditText) findViewById(R.id.editText);
                final String str = editText.getText().toString().toLowerCase();
                ListView lv = (ListView) findViewById(R.id.lv_3);
                lv.setAdapter(null);
                LinearLayout.LayoutParams lParams1;
                LinearLayout layout = (LinearLayout) findViewById(R.id.linl1);
                layout.removeAllViews();
                for (int i = 0; i < DictionarySet.dictionaries.size(); i++) {
                    if (DictionarySet.dictionaries.get(i).getMap().containsKey(str)) {
                        for (Map.Entry<String, String[]> entry : DictionarySet.dictionaries.get(i).getMap().entrySet()) {
                            if (entry.getKey().equals(str)) {
                                lParams1 = (LinearLayout.LayoutParams) lv.getLayoutParams();
                                lParams1.height = 200;
                                final String[] translations = entry.getValue();
                                TextView txtV = (TextView) findViewById(R.id.download);
                                txtV.setText("Количество переводов слова \"" + str + "\" в словаре " + DictionarySet.dictionaries.get(i).getName() + " - " + translations.length);
                                String[] arr = new String[translations.length];
                                for (int j = 0; j < translations.length; j++) {
                                    arr[j] = translations[j];
                                }
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                        android.R.layout.simple_list_item_1, arr);
                                lv.setAdapter(adapter);
                                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        showPopupMenu(view, str, translations[position]);
                                    }
                                });
                                Button addArchive = new Button(layout.getContext());
                                addArchive.setText("Добавить в архив");
                                layout.addView(addArchive);
                                addArchive.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Card card;
                                        if (direction.getText().equals("EN|RU")) {
                                            card = new Card(0, str, "");
                                        } else {
                                            card = new Card(0, "", str);
                                        }
                                        int r = 0;
                                        for (int i = 0; i < Storage.cards_.size(); i++) {
                                            if (Storage.cards_.get(i).getEn().equals(str) && Storage.cards_.get(i).getBox() == 5) {
                                                r = 1;
                                            }
                                        }
                                        if (r != 1) {
                                            if (!str.equals(str1) && !(index == -2)) {
                                                Storage.archive_.add(card);
                                            }
                                            Toast toast = Toast.makeText(getApplicationContext(),
                                                    "Слово помещено в архив", Toast.LENGTH_SHORT);
                                            toast.show();
                                        } else {
                                            Toast toast = Toast.makeText(getApplicationContext(),
                                                    "Вы знаете это слово", Toast.LENGTH_SHORT);
                                            toast.show();
                                        }
                                    }
                                });
                            }
                        }
                        i = DictionarySet.dictionaries.size();
                    } else if (i == DictionarySet.dictionaries.size() - 1) {
                        lParams1 = (LinearLayout.LayoutParams) lv.getLayoutParams();
                        lParams1.height = 50;
                        TextView txtV = (TextView) findViewById(R.id.download);
                        txtV.setText("Слова \"" + str + "\" нет в словарях");
                        TextView txt = new TextView(layout.getContext());
                        txt.setText("Введите перевод вручную:");
                        layout.addView(txt);
                        final EditText editText2 = new EditText(layout.getContext());
                        layout.addView(editText2);
                        final Button btn = new Button(layout.getContext());
                        btn.setText("Добавить");
                        layout.addView(btn);
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String word = editText2.getText().toString();
                                if (!word.equals("")) {
                                    showPopupMenu(v, str, word);
                                } else {
                                    Card card;
                                    if (direction.getText().equals("EN|RU")) {
                                        card = new Card(0, str, "");
                                    } else {
                                        card = new Card(0, "", str);
                                    }
                                    int r = 0;
                                    for (int i = 0; i < Storage.cards_.size(); i++) {
                                        if (Storage.cards_.get(i).getEn().equals(str) && Storage.cards_.get(i).getBox() == 5) {
                                            r = 1;
                                        }
                                    }
                                    if (r != 1) {
                                        if (!str.equals(str1) && !(index == -2)) {
                                            Storage.archive_.add(card);
                                        }
                                        Toast toast = Toast.makeText(getApplicationContext(),
                                                "Слово помещено в архив", Toast.LENGTH_SHORT);
                                        toast.show();
                                    } else {
                                        Toast toast = Toast.makeText(getApplicationContext(),
                                                "Вы знаете это слово", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                }
                            }
                        });
                        Button addArchive = new Button(layout.getContext());
                        addArchive.setText("Добавить в архив");
                        layout.addView(addArchive);
                        addArchive.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Card card;
                                if (direction.getText().equals("EN|RU")) {
                                    card = new Card(0, str, "");
                                } else {
                                    card = new Card(0, "", str);
                                }
                                int r = 0;
                                for (int i = 0; i < Storage.cards_.size(); i++) {
                                    if (Storage.cards_.get(i).getEn().equals(str) && Storage.cards_.get(i).getBox() == 5) {
                                        r = 1;
                                    }
                                }
                                if (r != 1) {
                                    if (!str.equals(str1) && !(index == -2)) {
                                        Storage.archive_.add(card);
                                    }
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            "Слово помещено в архив", Toast.LENGTH_SHORT);
                                    toast.show();
                                } else {
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            "Вы знаете это слово", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }
                        });
                       /* if (direction.getText().equals("EN|RU")) {
                            Button addForm = new Button(layout.getContext());
                            addForm.setText("Добавить в формы");
                            layout.addView(addForm);
                            addForm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int d = 0;
                                    for (Map.Entry<String, String[]> entry : Forms.forms_.entrySet()) {
                                        if (entry.getKey().equals(str)) {
                                            d = 1;
                                        }
                                        if (d != 1) {
                                            for (int j = 0; j < entry.getValue().length - 1; j++) {
                                                if (entry.getValue()[j].equals(str)) {
                                                    d = 1;
                                                }
                                            }
                                        }
                                    }
                                    if (d != 1) {
                                        forms.setForms_(str);
                                    }
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            "Cозданы формы слова", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            });
                        }*/
                    }
                }
                break;
            case R.id.back:
                finish();
                break;
            case R.id.direction:
                TextView tv = (TextView) findViewById(R.id.lable);
                if (direction.getText().equals("EN|RU")) {
                    tv.setText("Введите слово на русском языке");
                    direction.setText("RU|EN");
                } else {
                    tv.setText("Введите слово на английском языке");
                    direction.setText("EN|RU");
                }
            default:
                break;
        }
    }

    private void showPopupMenu(final View v, final String str, final String tr) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.menu_variant);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu1:
                        Card card;
                        if (direction.getText().equals("EN|RU")) {
                            card = new Card(1, str, tr);
                        } else {
                            card = new Card(1, tr, str);
                        }
                        int p = 0;
                        for (int i = 0; i < Storage.cards_.size(); i++) {
                            if (Storage.cards_.get(i).getEn().equals(str) && Storage.cards_.get(i).getBox() == 5) {
                                p = 1;
                            }
                        }
                        if (p != 1) {
                            int r = 0;
                            for (int i = 0; i < Storage.cards_.size(); i++) {
                                if (Storage.cards_.get(i).getEn().equals(str) && Storage.cards_.get(i).getRu().equals(tr)) {
                                    r = 1;
                                }
                            }
                            if (r == 1) {
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        "Такое слово уже есть среди карточек", Toast.LENGTH_SHORT);
                                toast.show();
                            } else {
                                int d = 0;
                                for (Map.Entry<String, String[]> entry : forms.getMap().entrySet()) {
                                    for (int j = 0; j < entry.getValue().length - 1; j++) {
                                        if (entry.getValue()[j].equals(str)) {
                                            Toast toast = Toast.makeText(getApplicationContext(),
                                                    "Такое слово уже есть среди карточек", Toast.LENGTH_SHORT);
                                            toast.show();
                                            d = 1;
                                        }
                                    }
                                }
                                if (d != 1) {
                                    Storage.cards_.add(card);
                                    if (str.equals(str1) && !(index == -2)) {
                                        Storage.archive_.remove(index);
                                    }
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            "Создана новая карточка", Toast.LENGTH_SHORT);
                                    toast.show();
                                    if (direction.getText().equals("EN|RU")) {
                                        forms.setForms_(str);
                                    }
                                }
                            }
                        }
                        else{
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Вы знаете это слово", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                        return true;
                    case R.id.menu2:
                        Card card1;
                        if (direction.getText().equals("EN|RU")) {
                            card1 = new Card(5, str, tr);
                        } else {
                            card1 = new Card(5, tr, str);
                        }
                        p = 0;
                        for (int i = 0; i < Storage.cards_.size(); i++) {
                            if (Storage.cards_.get(i).getEn().equals(str) && Storage.cards_.get(i).getBox() == 5) {
                                p = 1;
                            }
                        }
                        if (p != 1) {
                        int r1 = 0;
                        for (int i = 0; i < Storage.cards_.size(); i++) {
                            if (Storage.cards_.get(i).getEn().equals(str) && Storage.cards_.get(i).getRu().equals(tr)) {
                                r1 = 1;
                            }
                        }
                        if (r1 == 1) {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Такое слово уже есть среди карточек", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            int d = 0;
                            for (Map.Entry<String, String[]> entry : forms.getMap().entrySet()) {
                                for (int j = 0; j < entry.getValue().length - 1; j++) {
                                    if (entry.getValue()[j].equals(str)) {
                                        Toast toast = Toast.makeText(getApplicationContext(),
                                                "Такое слово уже есть среди карточек", Toast.LENGTH_SHORT);
                                        toast.show();
                                        d = 1;
                                    }
                                }
                            }
                            if (d != 1) {
                                Storage.cards_.add(card1);
                                if (str.equals(str1) && !(index == -2)) {
                                    Storage.archive_.remove(index);
                                }
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        "Создана новая карточка", Toast.LENGTH_SHORT);
                                toast.show();
                                if (direction.getText().equals("EN|RU")) {
                                    forms.setForms_(str);
                                }
                            }
                        }}
                        else{
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Вы знаете это слово", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }
}