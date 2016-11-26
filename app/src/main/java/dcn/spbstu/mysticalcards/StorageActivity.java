package dcn.spbstu.mysticalcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StorageActivity extends AppCompatActivity implements View.OnClickListener {

    Button box_1;
    Button box_2;
    Button box_3;
    Button box_4;
    Button box_5;
    Button archive;
    Button mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        box_1 = (Button) findViewById(R.id.box_1);
        box_1.setOnClickListener(this);

        box_2 = (Button) findViewById(R.id.box_2);
        box_2.setOnClickListener(this);

        box_3 = (Button) findViewById(R.id.box_3);
        box_3.setOnClickListener(this);

        box_4 = (Button) findViewById(R.id.box_4);
        box_4.setOnClickListener(this);

        box_5 = (Button) findViewById(R.id.box_5);
        box_5.setOnClickListener(this);

        archive = (Button) findViewById(R.id.archive);
        archive.setOnClickListener(this);

        mainMenu = (Button) findViewById(R.id.mainMenu);
        mainMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.box_1:
                Intent intent1 = new Intent(this, BoxActivity.class);
                intent1.putExtra("num", 1);
                startActivity(intent1);
                break;
            case R.id.box_2:
                Intent intent2 = new Intent(this, BoxActivity.class);
                intent2.putExtra("num", 2);
                startActivity(intent2);
                break;
            case R.id.box_3:
                Intent intent3 = new Intent(this, BoxActivity.class);
                intent3.putExtra("num", 3);
                startActivity(intent3);
                break;
            case R.id.box_4:
                Intent intent4 = new Intent(this, BoxActivity.class);
                intent4.putExtra("num", 4);
                startActivity(intent4);
                break;
            case R.id.box_5:
                Intent intent5 = new Intent(this, BoxActivity.class);
                intent5.putExtra("num", 5);
                startActivity(intent5);
                break;
            case R.id.archive:
                Intent intent6 = new Intent(this, ArchiveActivity.class);
                startActivity(intent6);
                break;
            case R.id.mainMenu:
                finish();
            default:
                break;
        }

    }
}
