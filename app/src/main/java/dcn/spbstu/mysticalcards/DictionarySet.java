package dcn.spbstu.mysticalcards;

import java.util.ArrayList;

public class DictionarySet implements iDictionaryDriving {

    public static volatile ArrayList<Dictionary> dictionaries = new ArrayList<Dictionary>();

    public DictionarySet(){
    }


    @Override
    public ArrayList<String> getListOfDictionaries(){
        ArrayList<String> set = new ArrayList<String>();
        for(int i = 0; i < dictionaries.size(); i++){
            set.add(dictionaries.get(i).getName());
        }
        return set;
    }

    /*@Override
    public void loadNewDictionary(String name){
        Dictionary newDictionary = new Dictionary(name);
    }*/

    @Override
    public void removeDictionary(String name){
        for(int i = 0; i < dictionaries.size(); i++){
            if(dictionaries.get(i).getName().equals(name)){
                dictionaries.remove(dictionaries.get(i));
            }
        }
    }
}
