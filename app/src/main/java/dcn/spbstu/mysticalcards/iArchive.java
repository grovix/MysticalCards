package dcn.spbstu.mysticalcards;// архив:
// изменить слово
// удалить слово
// проверить наличие перевода

interface iArchive {
    /*words*/
    void editWord(String word, String translation);
    void removeWord(String word);
    boolean isTranslated(String word);

}