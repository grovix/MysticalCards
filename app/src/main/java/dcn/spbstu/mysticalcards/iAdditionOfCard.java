package dcn.spbstu.mysticalcards;// режим добавления карточки:
// считать слово;
// добавить перевод;
// добавить карточку;
// добавить слово в архив

import dcn.spbstu.mysticalcards.Card;

interface iAdditionOfCard {
  //  void toReadWord();
  //  void toAddTranslation();
    void toAddCard(Card card);
    void toAddArchive(String word);
    //void getFromSpecialFile();
    //void getFromText();
}