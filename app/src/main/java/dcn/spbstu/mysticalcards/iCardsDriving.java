package dcn.spbstu.mysticalcards;// управление карточками:
// просмотр карточки с учетом типа сортировки
// редактирование карточки
// удаление карточки
// перемещение карточки

import dcn.spbstu.mysticalcards.iCard;

interface iCardsDriving {

    void showCards(sortType type);
    void toEditCard(iCard card);
    void removeCard(iCard card);
    void moveCard(iCard card);

}