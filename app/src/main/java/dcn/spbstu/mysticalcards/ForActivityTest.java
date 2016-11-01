package dcn.spbstu.mysticalcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import dcn.spbstu.mysticalcards.MainActivity;
import dcn.spbstu.mysticalcards.R;

public class ForActivityTest extends AppCompatActivity implements View.OnClickListener{

    LinearLayout linearLayout2;
    Button back3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for);

        linearLayout2 = (LinearLayout) findViewById(R.id.linlay2);

        for(int i = 0; i < Storage.archive_.size(); i++){
            TextView txtv = new TextView(this);
            txtv.setText(Storage.archive_.get(i).en_);
            txtv.setId(i);
            linearLayout2.addView(txtv);
        }

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
