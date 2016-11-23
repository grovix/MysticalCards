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
import android.widget.ListView;
import android.widget.Toast;

public class DictionarySetActivity extends AppCompatActivity implements View.OnClickListener{

    Button backToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_set);

        backToMenu = (Button)findViewById(R.id.backToMenu);
        backToMenu.setOnClickListener(this);

        String[] arr = new String[DictionarySet.dictionaries.size()];
        for (int i = 1; i < DictionarySet.dictionaries.size(); i++) {
            arr[i] = DictionarySet.dictionaries.get(i).getName();
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
        final Intent intent2 = new Intent(this, AddWordActivity.class);
        popupMenu.inflate(R.menu.menu_dictionary);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu1:
                        DictionarySet.dictionaries.remove(position + 1);
                        Toast.makeText(getApplicationContext(),
                                "Словарь удален",
                                Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
