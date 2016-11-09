package dcn.spbstu.mysticalcards;// режим управления словарями:
// просмотр списка загруженных словарей;
// загрузка нового словаря;
// удаление словаря

import java.util.ArrayList;

interface iDictionaryDriving {
    ArrayList<String> getListOfDictionaries();//void showListOfDictionaries();
    //void loadNewDictionary(String name);
    void removeDictionary(String name);
}