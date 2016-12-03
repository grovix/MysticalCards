package dcn.spbstu.mysticalcards;

import android.content.Intent;
import android.os.Environment;
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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddFromDownloadedTextActivity extends AppCompatActivity implements View.OnClickListener{
    Button backToMenu;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_from_downloaded_text);

        backToMenu = (Button) findViewById(R.id.backToMenu4);
        backToMenu.setOnClickListener(this);

        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(this);
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        search.performClick();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backToMenu4:
                finish();
                break;
            case R.id.search:
                FileInputStream fStream = null;
                InputStreamReader inputStream = null;
                EditText edText = (EditText) findViewById(R.id.edtext3);
                String fileName = edText.getText().toString();
                File sdPath = Environment.getExternalStorageDirectory();
                File file = new File(sdPath, fileName);
                int k = 0;
                try {
                    fStream = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Неверный путь", Toast.LENGTH_SHORT);
                    toast.show();
                    k = 1;
                }
                if (k != 1) {
                    try {
                        inputStream = new InputStreamReader(fStream, "Cp1251");
                    } catch (UnsupportedEncodingException e) {

                        e.printStackTrace();
                    }
                    BufferedReader bufferedReader = null;
                    bufferedReader = new BufferedReader(inputStream);

                    String line;
                    List<String> articles_prepositions = new ArrayList<String>();
                    articles_prepositions.add("a");
                    articles_prepositions.add("the");
                    articles_prepositions.add("an");
                    articles_prepositions.add("of");
                    articles_prepositions.add("to");

                    final List<String> list = new ArrayList<String>();
                    try {
                        while ((line = bufferedReader.readLine()) != null) {
                            String[] arrayWord = line.split("[\",-;:.!?&$() {1-9}{а-я}]+");
                            for (int i = 0; i < arrayWord.length; i++) {
                                    int d = 0;
                                    for (int j = 0; j < articles_prepositions.size(); j++) {
                                        if (articles_prepositions.get(j).equals(arrayWord[i].toLowerCase())) {
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
                        }
                        String[] arr = new String[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            arr[i] = list.get(i);
                        }
                        ListView lv = (ListView) findViewById(R.id.wordsInText);
                        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                android.R.layout.simple_list_item_1, arr);
                        lv.setAdapter(adapter);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                showPopupMenu(view, position, list);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;
            default:
                break;
        }
    }

    private void showPopupMenu(final View v, final int position, final List<String> list) {
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
