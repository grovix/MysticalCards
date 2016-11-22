package dcn.spbstu.mysticalcards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;

public class ThreeActivityTest extends AppCompatActivity implements View.OnClickListener {

    Button back2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        String[] arr = new String[Storage.cards_.size()];
        for(int i = 0; i < Storage.cards_.size(); i++){
            arr[i] = Storage.cards_.get(i).getEn() + " - " + Storage.cards_.get(i).getRu();
        }
        ListView lv = (ListView) findViewById(R.id.lv_2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arr);
        lv.setAdapter(adapter);

        back2 = (Button) findViewById(R.id.back2);
        back2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back2:
                Intent intent3 = new Intent(this, TestDictionaryActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }
}
