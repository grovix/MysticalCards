package dcn.spbstu.mysticalcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class EditCardActivity extends AppCompatActivity {

    String word = new String();
    String translation = new String();
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);

        position = getIntent().getIntExtra("position", -1);
        word = getIntent().getStringExtra("en");
        translation = getIntent().getStringExtra("ru");
        EditText editText1 = (EditText) findViewById(R.id.editword);
        editText1.setText(word);
        final EditText editText2 = (EditText) findViewById(R.id.edittranslation);
        editText2.setText(translation);
        LinearLayout layout = (LinearLayout) findViewById(R.id.linlayout);
        int d = 0;
        for (int i = 0; i < DictionarySet.dictionaries.size(); i++) {
            if (DictionarySet.dictionaries.get(i).getMap().containsKey(word)) {
                d = 1;
                for (Map.Entry<String, String[]> entry : DictionarySet.dictionaries.get(i).getMap().entrySet()) {
                    if (entry.getKey().equals(word)) {
                        String[] translations = entry.getValue();
                        final String[] arr = new String[translations.length];
                        for (int j = 0; j < translations.length; j++) {
                            arr[j] = translations[j];
                        }
                        TextView tv = new TextView(layout.getContext());
                        tv.setText("Переводы в соварях:");
                        layout.addView(tv);
                        ListView lv = new ListView(layout.getContext());
                        layout.addView(lv);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                android.R.layout.simple_list_item_1, arr);
                        lv.setAdapter(adapter);
                        lv.setAdapter(adapter);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                editText2.setText(arr[position].toString());
                            }
                        });
                    }
                }
            }
        }
        if (d != 1) {
            TextView txt = new TextView(layout.getContext());
            txt.setText("Количество переводов в словарях: 0");
            layout.addView(txt);
        }
        Button edit = new Button(layout.getContext());
        edit.setText("Изменить");
        layout.addView(edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText1 = (EditText) findViewById(R.id.editword);
                word = editText1.getText().toString();
                EditText editText2 = (EditText) findViewById(R.id.edittranslation);
                translation = editText2.getText().toString();
                int r = 0;
                if (!word.equals("") && !translation.equals("")) {
                    for (int i = 0; i < Storage.cards_.size(); i++) {
                        if (Storage.cards_.get(i).getEn().equals(word) && Storage.cards_.get(i).getRu().equals(translation) && i != position){
                            r = 1;
                        }
                    }
                    if(r!= 1){
                    Storage.cards_.get(position).setEn(word);
                    Storage.cards_.get(position).setRu(translation);
                    Toast.makeText(getApplicationContext(),
                            "Изменения сохранены",
                            Toast.LENGTH_SHORT).show();
                    finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),
                                "Такое слово уже есть среди карточек",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Некорректные данные",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

}
