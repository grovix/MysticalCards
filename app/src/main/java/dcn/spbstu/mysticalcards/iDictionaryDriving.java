package dcn.spbstu.mysticalcards;// режим управления словарями:
// просмотр списка загруженных словарей;
// загрузка нового словаря;
// удаление словаря

interface iDictionaryDriving {
    void showListOfDictionaries();
    void loadNewDictionary();
    void removeDictionary();
}