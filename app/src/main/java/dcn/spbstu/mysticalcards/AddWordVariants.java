package dcn.spbstu.mysticalcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import dcn.spbstu.mysticalcards.Training.ChooseTrainingActivity;

public class AddWordVariants extends AppCompatActivity implements View.OnClickListener{

    Button addOneWord;
    Button addFromText;
    Button addFromDownloadedText;
    Button download;
    Button backToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word_variants);

        addOneWord = (Button) findViewById(R.id.addOneWord);
        addOneWord.setOnClickListener(this);

        addFromText = (Button) findViewById(R.id.AddFromText);
        addFromText.setOnClickListener(this);

        addFromDownloadedText = (Button) findViewById(R.id.AddFromDownloadedText);
        addFromDownloadedText.setOnClickListener(this);

        backToMenu = (Button) findViewById(R.id.backToMenu1);
        backToMenu.setOnClickListener(this);

        download = (Button) findViewById(R.id.DownloadCards);
        download.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addOneWord:
                Intent intent = new Intent(this, AddWordActivity.class);
                intent.putExtra("name", "");
                intent.putExtra("index", -1);
                startActivity(intent);
                break;
            case R.id.AddFromText:
                Intent intent2 = new Intent(this, AddFromTextActivity.class);
                startActivity(intent2);
                break;
            case R.id.AddFromDownloadedText:
                Intent intent3 = new Intent(this, AddFromDownloadedTextActivity.class);
                startActivity(intent3);
                break;
            case R.id.DownloadCards:
                Intent intent4 = new Intent(this, DownloadCardsActivity.class);
                startActivity(intent4);
            case R.id.backToMenu1:
                finish();
                break;
            default:
                break;
        }

    }
}
