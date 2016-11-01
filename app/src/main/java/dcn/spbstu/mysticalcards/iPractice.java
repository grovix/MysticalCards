package dcn.spbstu.mysticalcards;// интерфейс тренировки:
// принимается конфигурация (номер коробки, число слов тренировки, направление перевода, число итераций)
// составляется набор карточек
// запуск тренировки
// завершение тренировки

// нужен некоторый (dcn.spbstu.mysticalcards.iConfig)
//      config {
//          int boxNumber;                 //номер коробки
//          int NumberOfWords;             //количество слов тренировки
//          translationDirection type;     //направление перевода (enum)
//          int numberOfIterations;        //число итераций
//      }

interface iPractice {
    void setConfig(/*config*/);
    void setCards();
    void run();
    void finish();
}