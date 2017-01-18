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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DictionarySetActivity extends AppCompatActivity implements View.OnClickListener {

    Button backToMenu;
    Button load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_set);

        backToMenu = (Button) findViewById(R.id.backToMenu);
        backToMenu.setOnClickListener(this);

        load = (Button) findViewById(R.id.load);
        load.setOnClickListener(this);

        String[] arr = new String[DictionarySet.dictionaries.size() - 2];
        for (int i = 2; i < DictionarySet.dictionaries.size(); i++) {
            arr[i - 2] = DictionarySet.dictionaries.get(i).getName();
        }
        ListView lv = (ListView) findViewById(R.id.set);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arr);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showPopupMenu(view, position);
            }
        });
    }

    private void showPopupMenu(final View v, final int position) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.menu_dictionary);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu1:
                        String name = DictionarySet.dictionaries.get(position + 2).getName();
                        deleteFile(name);
                        DictionarySet.dictionaries.remove(position + 2);
                        Toast.makeText(getApplicationContext(),
                                "Словарь удален",
                                Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent = getIntent();
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backToMenu:
                finish();
                break;
            case R.id.load:
                LinearLayout layout = (LinearLayout) findViewById(R.id.ll);
                TextView txt = new TextView(layout.getContext());
                txt.setText("Введите путь к файлу со словарем:");
                layout.addView(txt);
                final EditText editText = new EditText(layout.getContext());
                layout.addView(editText);
                TextView txt1 = new TextView(layout.getContext());
                txt1.setText("Назовите словарь:");
                layout.addView(txt1);
                final EditText editText1 = new EditText(layout.getContext());
                layout.addView(editText1);
                Button btn = new Button(layout.getContext());
                btn.setText("Загрузить");
                layout.addView(btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str = editText1.getText().toString();
                        String fileName = editText.getText().toString();
                        Dictionary dictionary = new Dictionary();
                        dictionary.setName(str);

                        File sdPath = Environment.getExternalStorageDirectory();
                        File file = new File(sdPath, fileName);
                        BufferedReader bufferedReader = null;
                        int k = 0;
                        try {
                            bufferedReader = new BufferedReader(new FileReader(file));
                        } catch (IOException e) {
                            // e.printStackTrace();
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Неверный путь", Toast.LENGTH_SHORT);
                            toast.show();
                            k = 1;
                        }
                        if (k != 1) {
                            String line;
                            List<String> list = new ArrayList<String>();
                            try {
                                while ((line = bufferedReader.readLine()) != null) {
                                    list.add(line);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Map<String, String> map = new TreeMap<>();
                            for (int j = 0; j < list.size(); j++) {
                                String[] arrayMessage = list.get(j).split(" : ");
                                map.put(arrayMessage[0], arrayMessage[1]);
                            }
                            for (Map.Entry<String, String> entry : map.entrySet()) {
                                String[] translations = entry.getValue().split("; ");
                                dictionary.map_.put(entry.getKey(), translations);
                            }
                        BufferedWriter pw1;
                        if(!str.equals("")){
                        try {
                            pw1 = new BufferedWriter(new OutputStreamWriter(openFileOutput(str, MODE_PRIVATE)));
                            for (Map.Entry<String, String[]> entry : dictionary.map_.entrySet()) {
                                try {
                                    pw1.write(entry.getKey() + " - ");
                                    pw1.write(entry.getValue()[0]);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                for (int j = 1; j < entry.getValue().length; j++) {
                                    pw1.write("; " + entry.getValue()[j]);
                                }
                                pw1.write('\n');
                            }
                            pw1.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        DictionarySet.dictionaries.add(dictionary);
                        finish();
                        Intent intent = getIntent();
                        startActivity(intent);
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Придумайте название для словаря!", Toast.LENGTH_SHORT);
                        toast.show();
                    }}
                }});
                break;
            default:
                break;
        }
    }
}
