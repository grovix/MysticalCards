package dcn.spbstu.mysticalcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class TestDictionaryActivity extends AppCompatActivity implements View.OnClickListener {

    Button addWord;
    Button box1;
    Button archive;
    Button formsSet;

    Storage storage = new Storage();
    Forms forms = new Forms();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dictionary);

        if(DictionarySet.dictionaries.isEmpty()) {
            Dictionary dictionary = new Dictionary();
            dictionary.setName("slovar.txt");
            DictionarySet.dictionaries.add(dictionary);
            for (int i = 0; i <  DictionarySet.dictionaries.size(); i++) {
                try {
                    DictionarySet.dictionaries.get(i).read(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(Forms.forms_.isEmpty()) {
           try{
               forms.readForms(this);
           }
           catch (IOException e) {
               e.printStackTrace();
           }
        }

        if(Storage.cards_.isEmpty() && Storage.archive_.isEmpty()) {
            for (int i = 1; i <= 5; i++) {
                try {
                    storage.readBox(i, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                storage.readArchive(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else{
            try {
                storage.writeBox(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                storage.writeArchive(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try{
                forms.writeForms(this);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        addWord = (Button) findViewById(R.id.addWord);
        addWord.setOnClickListener(this);

        box1 = (Button) findViewById(R.id.box1);
        box1.setOnClickListener(this);

        formsSet = (Button) findViewById(R.id.formsSet);
        formsSet.setOnClickListener(this);

        archive = (Button) findViewById(R.id.archive);
        archive.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addWord:
                Intent intent1 = new Intent(this, TwoActivityTest.class);
                intent1.putExtra("name", "".toString());
                intent1.putExtra("index", 0);
                startActivity(intent1);
                break;
            case R.id.box1:
                Intent intent2 = new Intent(this, ThreeActivityTest.class);
                startActivity(intent2);
                break;
            case R.id.archive:
                Intent intent3 = new Intent(this, ForActivityTest.class);
                startActivity(intent3);
                break;
            case R.id.formsSet:
                Intent intent4 = new Intent(this, FiveActivityTest.class);
                startActivity(intent4);
                break;
            default:
                break;
        }

    }

}
