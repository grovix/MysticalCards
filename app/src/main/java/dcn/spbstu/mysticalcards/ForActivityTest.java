package dcn.spbstu.mysticalcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arr);
        lv.setAdapter(adapter);

        back3 = (Button) findViewById(R.id.back3);
        back3.setOnClickListener(this);
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
