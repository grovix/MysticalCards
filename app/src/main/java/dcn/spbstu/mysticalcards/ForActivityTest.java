package dcn.spbstu.mysticalcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class ForActivityTest extends AppCompatActivity implements View.OnClickListener{

    Button back3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for);


        String[] arr = new String[Storage.archive_.size()];
        for(int i = 0; i < Storage.archive_.size(); i++){
            arr[i] = Storage.archive_.get(i).en_;
        }
        ListView lv = (ListView) findViewById(R.id.lv);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arr);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showPopupMenu(view, position);
            }
        });
        back3 = (Button) findViewById(R.id.back3);
        back3.setOnClickListener(this);
    }
    private void showPopupMenu(final View v, final int position) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        final Intent intent2 = new Intent(this, TwoActivityTest.class);
        popupMenu.inflate(R.menu.menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu1:
                        Storage.archive_.remove(position);
                        Toast.makeText(getApplicationContext(),
                                "Слово удалено",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = getIntent();
                        startActivity(intent);
                        return true;
                    case R.id.menu2:
                        intent2.putExtra("name", Storage.archive_.get(position).en_.toString());
                        intent2.putExtra("index", position);
                        startActivity(intent2);
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }
    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.back3:
                Intent intent3 = new Intent(this, TestDictionaryActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }
}
