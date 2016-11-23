package dcn.spbstu.mysticalcards;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


import static android.content.Context.MODE_PRIVATE;

public class Storage implements iAdditionOfCard, iArchive {

    static volatile List<Card> cards_ = new ArrayList<Card>();
    static volatile List<Card> archive_  = new ArrayList<Card>();

    public Storage() {
    }

    public Card getCard(int i){
        return cards_.get(i);
    }

    public void readBox(int box, Context context) throws IOException {
        BufferedReader reader;
        switch (box) {
            case 1:
                reader = new BufferedReader(new InputStreamReader(context.openFileInput("box_1")));
                break;
            case 2:
                reader = new BufferedReader(new InputStreamReader(context.openFileInput("box_2")));
                break;
            case 3:
                reader = new BufferedReader(new InputStreamReader(context.openFileInput("box_3")));
                break;
            case 4:
                reader = new BufferedReader(new InputStreamReader(context.openFileInput("box_4")));
                break;
            case 5:
                reader = new BufferedReader(new InputStreamReader(context.openFileInput("box_5")));
                break;
            default:
                reader = new BufferedReader(new InputStreamReader(context.openFileInput("box_1")));
                break;
        }
        String line;
        List<String> list = new ArrayList<String>();
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        for (int j = 0; j < list.size(); j++) {
            String[] arrayMessage = list.get(j).split(" - ");
            cards_.add(new Card(box, arrayMessage[0], arrayMessage[1]));
        }
    }

    public void readArchive(Context context) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                context.openFileInput("archive")));
        String line;
        List<String> list = new ArrayList<String>();
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        for (int j = 0; j < list.size(); j++) {
            String[] arrayMessage = list.get(j).split(" - ");
            archive_.add(new Card(0, arrayMessage[0], ""));
        }
    }

    public void writeBox(Context context) throws IOException {
        BufferedWriter pw;
        for (int i = 1; i <= 5; i++) {
            String str = "box_" + String.valueOf(i);
            pw = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(str, MODE_PRIVATE)));
            for (int j = 0; j < cards_.size(); j++) {
                if (cards_.get(j).getBox() == i) {
                    pw.write(cards_.get(j).getEn() + " - " + cards_.get(j).getRu());
                    pw.write('\n');
                }
            }
            pw.close();
        }
    }

    public void writeArchive(Context context) throws IOException {
        BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(context.openFileOutput("archive", MODE_PRIVATE)));
        for (int i = 0; i < archive_.size(); i++) {
            pw.write(archive_.get(i).en_);
            pw.write('\n');
        }
        pw.close();
    }

    @Override
    public void toAddArchive(String word) {
        archive_.add(new Card(0, word, ""));
    }

    @Override
    public void toAddCard(Card card) {
        cards_.add(card);
    }

    @Override
    public void editWord(String word, String translation){
        for(int i = 0; i < archive_.size(); i++){
            if(archive_.get(i).getEn().equals(word)){
                archive_.get(i).setRu(translation);
            }
        }
    }

    @Override
    public void removeWord(String word){
        for(int i = 0; i < archive_.size(); i++){
            if(archive_.get(i).getEn().equals(word)){
                archive_.remove(archive_.get(i));
            }
        }
    }

    @Override
    public boolean isTranslated(String word){
        for(int i = 0; i < archive_.size(); i++){
            if(archive_.get(i).getEn().equals(word)){
                if(!archive_.get(i).getRu().equals("")){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return  false;
    }

}

