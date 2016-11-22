package dcn.spbstu.mysticalcards;

import android.content.Intent;
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

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class TwoActivityTest extends AppCompatActivity implements View.OnClickListener {

    Forms forms = new Forms();

    //EditText editText = (EditText) findViewById(R.id.editText);
    String str1 = new String();
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        str1 = getIntent().getStringExtra("name");
        EditText editText = (EditText) findViewById(R.id.editText);
        if(!str1.equals("")){
            editText.setText(str1);
        }
        index = getIntent().getIntExtra("index", 0);

        Button findWord = (Button) findViewById(R.id.findWord);
        findWord.setOnClickListener(this);

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);

        try {
            forms.readForms(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.findWord:
                EditText editText = (EditText) findViewById(R.id.editText);
                final String str = editText.getText().toString();
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
                                                Card card = new Card(1, str, translations[k - 1]);
                                                if (Storage.cards_.contains(card)) {
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
                                                        if(str.equals(str1)){
                                                            Storage.archive_.remove(index);
                                                        }
                                                        Toast toast = Toast.makeText(getApplicationContext(),
                                                                "Создана новая карточка", Toast.LENGTH_SHORT);
                                                        toast.show();
                                                        forms.setForms_(str);
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
                                        Card card = new Card(0, str, "");
                                        if(!str.equals(str1)){
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
                                    Card card = new Card(1, str, word);
                                    if (Storage.cards_.contains(card)) {
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
                                            if(str.equals(str1)){
                                                Storage.archive_.remove(index);
                                            }
                                            Toast toast = Toast.makeText(getApplicationContext(),
                                                    "Создана новая карточка", Toast.LENGTH_SHORT);
                                            toast.show();
                                            forms.setForms_(str);
                                        }
                                    }
                                } else {
                                    Card card = new Card(0, str, "");
                                    if(!str.equals(str1)){
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
                                Card card = new Card(0, str, "");
                                if(!str.equals(str1)){
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
                Intent intent = new Intent(this, TestDictionaryActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}