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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddFromTextActivity extends AppCompatActivity implements View.OnClickListener {

    Button show;
    Button back;

    Forms forms = new Forms();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_from_text);

        show = (Button) findViewById(R.id.show);
        show.setOnClickListener(this);

        back = (Button) findViewById(R.id.backback);
        back.setOnClickListener(this);
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        show.performClick();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show:
                List<String> articles_prepositions = new ArrayList<String>();
                articles_prepositions.add("a");
                articles_prepositions.add("the");
                articles_prepositions.add("an");
                articles_prepositions.add("of");
                articles_prepositions.add("to");

                final ArrayList<String> list = new ArrayList<String>();
                EditText editText = (EditText) findViewById(R.id.input_text);
                String str = editText.getText().toString();
                String[] arrayWord = str.split("[\"\',-;:.!?&$() {1-9}{а-я}]+");
                for (int i = 0; i < arrayWord.length; i++) {
                    int d = 0;
                    for (int k = 0; k < articles_prepositions.size(); k++) {
                        if (articles_prepositions.get(k).equals(arrayWord[i].toLowerCase())) {
                            d = 1;
                        }
                    }
                    if (d != 1) {
                        for (Map.Entry<String, String[]> entry : Forms.forms_.entrySet()) {
                            if (entry.getKey().equals(arrayWord[i].toLowerCase())) {
                                d = 1;
                            }
                            if (d != 1) {
                                for (int j = 0; j < entry.getValue().length - 1; j++) {
                                    if (entry.getValue()[j].equals(arrayWord[i].toLowerCase())) {
                                        d = 1;
                                    }
                                }
                            }
                        }
                    }
                    if (d != 1) {
                        if (!list.contains(arrayWord[i].toLowerCase())) {
                            list.add(arrayWord[i].toLowerCase());
                        }
                    }
                }
                String[] arr = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    arr[i] = list.get(i);
                }
                ListView lv = (ListView) findViewById(R.id.listofwords);
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, arr);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        showPopupMenu(view, position, list);
                    }
                });
                break;
            case R.id.backback:
                finish();
                break;
            default:
                break;
        }
    }
    private void showPopupMenu(final View v, final int position, final ArrayList<String> list) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        final Intent intent2 = new Intent(this, AddWordActivity.class);
        popupMenu.inflate(R.menu.menu_words);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu1:
                        intent2.putExtra("name", list.get(position).toString());
                        intent2.putExtra("index", -2);
                        startActivity(intent2);
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }
}
