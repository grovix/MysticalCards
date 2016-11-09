package dcn.spbstu.mysticalcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class TwoActivityTest extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Button findWord = (Button) findViewById(R.id.findWord);
        findWord.setOnClickListener(this);

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.findWord:
                EditText editText = (EditText) findViewById(R.id.editText);
                final String str = editText.getText().toString();
                for (int i = 0; i < DictionarySet.dictionaries.size(); i++) {
                    if (DictionarySet.dictionaries.get(i).getMap().containsKey(str)) {
                        for (Map.Entry<String, String[]> entry : DictionarySet.dictionaries.get(i).getMap().entrySet()) {
                            if (entry.getKey().equals(str)) {
                                final String[] translations = entry.getValue()[0].split("; ");
                                TextView txtV = (TextView) findViewById(R.id.search);
                                LinearLayout layout = (LinearLayout) findViewById(R.id.linl1);
                                txtV.setText("Количество переводов слова \"" + str + "\" в словаре " + DictionarySet.dictionaries.get(i).getName()+ " - " + translations.length);
                                for (int j = 0; j < translations.length; j++) {
                                    TextView txtv = new TextView(layout.getContext());
                                    txtv.setText((j + 1) + ") " + translations[j]);
                                    txtv.setId(j + 1);
                                    layout.addView(txtv);
                                }
                                TextView txt = new TextView(layout.getContext());
                                txt.setText("Введите номер перевода");
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
                                        for (int k = 1; k <= translations.length; k++) {
                                            if (word.equals(String.valueOf(k))) {
                                                Card card = new Card(1, str, translations[k - 1]);
                                                Storage.cards_.add(card);
                                                Toast toast = Toast.makeText(getApplicationContext(),
                                                        "Создана новая карточка", Toast.LENGTH_SHORT);
                                                toast.show();
                                            }
                                        }
                                    }
                                });
                            }
                        }
                        i = DictionarySet.dictionaries.size();
                    } else if (i == DictionarySet.dictionaries.size() - 1) {
                        TextView txtV = (TextView) findViewById(R.id.search);
                        txtV.setText("Слова \"" + str + "\" нет в словарях");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.linl1);
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
                                    Storage.cards_.add(card);
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            "Создана новая карточка", Toast.LENGTH_SHORT);
                                    toast.show();
                                } else {
                                    Card card = new Card(0, str, "");
                                    Storage.archive_.add(card);
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            "Слово помещено в архив", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
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