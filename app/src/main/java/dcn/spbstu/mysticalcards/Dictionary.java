package dcn.spbstu.mysticalcards;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Людмила on 01.11.2016.
 */

public class Dictionary{
    String name_;
    static volatile Map<String, String[]> map_ = new TreeMap<>();

    public Dictionary(){
    }

    public String getName(){
        return name_;
    }

    public void setName(String name){
        name_ = name;
    }

    public void addWord(String word, String[] translations){
        map_.put(word, translations);
    }

    public void read(Context context) throws IOException {
        AssetManager assetManager = context.getAssets();
        InputStreamReader istream = new InputStreamReader(assetManager.open(name_));
        BufferedReader reader = new BufferedReader(istream);
        String line;
        List<String> list = new ArrayList<String>();
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }

        for(int j = 0; j < list.size(); j++) {
            String[] arrayMessage = list.get(j).split(" : ");
            String[] translations = new String[arrayMessage.length - 1];
            for(int i = 1; i < arrayMessage.length; i++){
                translations[i - 1] = arrayMessage[i];
            }
            map_.put(arrayMessage[0],translations);
        }
    }

    public void show(){
        for (Map.Entry<String, String[]> entry : map_.entrySet()) {
            if (entry.getValue().length != 1) {
                System.out.print(entry.getKey() + " : ");
                for (int i = 0; i < entry.getValue().length - 1; i++) {
                    System.out.print(entry.getValue()[i] + "; ");
                }
                System.out.println(entry.getValue()[entry.getValue().length - 1]);
            } else {
                System.out.println(entry.getKey() + " : " + entry.getValue()[0]);
            }
        }
    }
}
