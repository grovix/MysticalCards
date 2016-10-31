// режим добавления карточки:
// добавить карточку;
// перевести слово;
// добавить перевод вручную;
// добавить слово в архив

interface iAdditionOfCard {
    void toAddCard(/*word*/);
    String toTranstale(/*word*/);
    void toAddTranslation();
    void toArchive();
    //void getFromSpecialFile();
    //void getFromText();
}