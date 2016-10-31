// архив:
// изменить слово
// удалить слово
// проверить наличие перевода

interface iArchive {
    /*words*/
    void editWord(/*word*/);
    void removeWord(/*word*/);
    boolean isTranslated(/*word*/);

}