package dcn.spbstu.mysticalcards;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Map;


public class FormsActivity extends AppCompatActivity  implements View.OnClickListener{

    Button back4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms);

        String[] arr = new String[Forms.forms_.size()];
        int i = 0;
        for (Map.Entry<String, String[]> entry : Forms.forms_.entrySet()) {
            arr[i] = entry.getKey() + " - ";
            for(int j = 0; j < entry.getValue().length - 1; j++){
                arr[i] = arr[i] + entry.getValue()[j] + "; ";
            }
            arr[i] = arr[i] + entry.getValue()[entry.getValue().length - 1];
            i++;
        }
        ListView lv = (ListView) findViewById(R.id.lv_1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arr);
        lv.setAdapter(adapter);

        back4 = (Button) findViewById(R.id.back4);
        back4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.back4:
                Intent intent3 = new Intent(this, MainActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }

}
