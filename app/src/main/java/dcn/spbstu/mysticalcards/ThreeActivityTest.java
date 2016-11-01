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
import dcn.spbstu.mysticalcards.Storage;

public class ThreeActivityTest extends AppCompatActivity implements View.OnClickListener {

    LinearLayout linearLayout;
    Button back2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        linearLayout = (LinearLayout) findViewById(R.id.linlay);

        for (int i = 0; i < Storage.cards_.size(); i++) {
            if (Storage.cards_.get(i).getBox() == 1) {
                TextView txtv = new TextView(this);
                txtv.setText(Storage.cards_.get(i).getEn() + " - " + Storage.cards_.get(i).getRu());
                txtv.setId(i);
                linearLayout.addView(txtv);
            }
        }

        back2 = (Button) findViewById(R.id.back3);
        back2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
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
