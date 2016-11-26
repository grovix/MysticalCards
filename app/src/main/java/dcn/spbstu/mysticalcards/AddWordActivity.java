package dcn.spbstu.mysticalcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
                LinearLayout layout = (LinearLayout) findViewById(R.id.linl1);
                layout.removeAllViews();
                for (int i = 0; i < DictionarySet.dictionaries.size(); i++) {
                    if (DictionarySet.dictionaries.get(i).getMap().containsKey(str)) {
                        for (Map.Entry<String, String[]> entry : DictionarySet.dictionaries.get(i).getMap().entrySet()) {
                            if (entry.getKey().equals(str)) {
                                final String[] translations = entry.getValue();
                                TextView txtV = (TextView) findViewById(R.id.search);
                                txtV.setText("Количество переводов слова \"" + str + "\" в словаре " + DictionarySet.dictionaries.get(i).getName() + " - " + translations.length);
                                String[] arr = new String[translations.length];
                                for (int j = 0; j < translations.length; j++) {
                                    arr[j] = (j + 1) + ") " + translations[j];
                                }
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                        android.R.layout.simple_list_item_1, arr);
                                lv.setAdapter(adapter);
                                TextView txt = new TextView(layout.getContext());
                                txt.setText("Введите номер перевода");
                                layout.addView(txt);
                                final EditText editText2 = new EditText(layout.getContext());
                                layout.addView(editText2);
                                Button btn = new Button(layout.getContext());
                                btn.setText("Добавить перевод");
                                layout.addView(btn);
                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        String word = editText2.getText().toString();
                                        for (int k = 1; k <= translations.length; k++) {
                                            if (word.equals(String.valueOf(k))) {
                                                Card card;
                                                if (direction.getText().equals("EN|RU")) {
                                                    card = new Card(1, str, translations[k - 1]);
                                                } else {
                                                    card = new Card(1, translations[k - 1], str);
                                                }
                                                int r = 0;
                                                for (int i = 0; i < Storage.cards_.size(); i++) {
                                                    if (Storage.cards_.get(i).getEn().equals(str) && Storage.cards_.get(i).getRu().equals(translations[k - 1])) {
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
                                                        if (str.equals(str1) && !(index == -2) ) {
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
                                        if (!str.equals(str1)&& !(index == -2)) {
                                            Storage.archive_.add(card);
                                        }
                                        Toast toast = Toast.makeText(getApplicationContext(),
                                                "Слово помещено в архив", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                });
                            }
                        }
                        i = DictionarySet.dictionaries.size();
                    } else if (i == DictionarySet.dictionaries.size() - 1) {
                        TextView txtV = (TextView) findViewById(R.id.search);
                        txtV.setText("Слова \"" + str + "\" нет в словарях");
                        TextView txt = new TextView(layout.getContext());
                        txt.setText("Введите перевод вручную:");
                        layout.addView(txt);
                        final EditText editText2 = new EditText(layout.getContext());
                        layout.addView(editText2);
                        Button btn = new Button(layout.getContext());
                        btn.setText("Добавить");
                        layout.addView(btn);
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String word = editText2.getText().toString();
                                if (!word.equals("")) {
                                    Card card;
                                    if (direction.getText().equals("EN|RU")) {
                                        card = new Card(1, str, word);
                                    } else {
                                        card = new Card(1, word, str);
                                    }
                                    int r = 0;
                                    for (int i = 0; i < Storage.cards_.size(); i++) {
                                        if (Storage.cards_.get(i).getEn().equals(str) && Storage.cards_.get(i).getRu().equals(word)) {
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
                                            for (int j = 0; j < entry.getValue().length; j++) {
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
                                            if (str.equals(str1)&& !(index == -2)) {
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
                                } else {
                                    Card card;
                                    if (direction.getText().equals("EN|RU")) {
                                        card = new Card(0, str, "");
                                    } else {
                                        card = new Card(0, "", str);
                                    }
                                    if (!str.equals(str1)&& !(index == -2)) {
                                        Storage.archive_.add(card);
                                    }
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            "Слово помещено в архив", Toast.LENGTH_SHORT);
                                    toast.show();
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
                                if (!str.equals(str1)&& !(index == -2)) {
                                    Storage.archive_.add(card);
                                }
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        "Слово помещено в архив", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        });
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
}