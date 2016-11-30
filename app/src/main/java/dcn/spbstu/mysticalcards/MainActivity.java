package dcn.spbstu.mysticalcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dcn.spbstu.mysticalcards.Training.ChooseTrainingActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button setDictionary;
    Button addWord;
    Button storage;
    Button end;

    Storage storage1 = new Storage();
    Forms forms = new Forms();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button trainbtn = (Button) findViewById(R.id.trainingActivity);
        trainbtn.setOnClickListener(this);

        if (DictionarySet.dictionaries.isEmpty()) {
            Dictionary dictionary = new Dictionary();
            dictionary.setName("slovar.txt");
            DictionarySet.dictionaries.add(dictionary);
            for (int i = 0; i < DictionarySet.dictionaries.size(); i++) {
                try {
                    DictionarySet.dictionaries.get(i).read(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(openFileInput("Names_of_loaded_dictionaries")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                Dictionary dictionary = new Dictionary();
                dictionary.setName(line);
                BufferedReader br;
                br = new BufferedReader(new InputStreamReader(openFileInput(line)));
                String line1;
                List<String> list1 = new ArrayList<String>();
                try {
                    while ((line = br.readLine()) != null) {
                        list1.add(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Map<String, String> map = new TreeMap<>();
                for (int j = 0; j < list1.size(); j++) {
                    String[] arrayMessage = list1.get(j).split(" - ");
                    map.put(arrayMessage[0], arrayMessage[1]);
                }
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String[] translations = entry.getValue().split("; ");
                    dictionary.map_.put(entry.getKey(), translations);
                }
                DictionarySet.dictionaries.add(dictionary);;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Forms.forms_.isEmpty()) {
            try {
                forms.readForms(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (Storage.cards_.isEmpty() && Storage.archive_.isEmpty()) {
            for (int i = 1; i <= 5; i++) {
                try {
                    storage1.readBox(i, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                storage1.readArchive(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } /*else {
            try {
                storage1.writeBox(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                storage1.writeArchive(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                forms.writeForms(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        setDictionary = (Button) findViewById(R.id.setDictionary);
        setDictionary.setOnClickListener(this);

        addWord = (Button) findViewById(R.id.addWord);
        addWord.setOnClickListener(this);

        storage = (Button) findViewById(R.id.storage);
        storage.setOnClickListener(this);

        end = (Button) findViewById(R.id.end);
        end.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.trainingActivity:
                Intent intent = new Intent(this, ChooseTrainingActivity.class);
                startActivity(intent);
                break;
            case R.id.setDictionary:
                Intent intent2 = new Intent(this, DictionarySetActivity.class);
                startActivity(intent2);
                break;
            case R.id.addWord:
                /*Intent intent3 = new Intent(this, AddWordActivity.class);
                intent3.putExtra("name", "");
                intent3.putExtra("index", -1);
                startActivity(intent3);*/
                Intent intent3 = new Intent(this, AddWordVariants.class);
                startActivity(intent3);
                break;
            case R.id.storage:
                Intent intent4 = new Intent(this, StorageActivity.class);
                startActivity(intent4);
                break;
            case R.id.end:
                try {
                    storage1.writeBox(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    storage1.writeArchive(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    forms.writeForms(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            default:
                break;
        }

    }
}
