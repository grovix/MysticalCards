package dcn.spbstu.mysticalcards;

public class Card implements iCard {
    int box_;
    String en_;
    String ru_;

    public Card(){
        box_ = 0;
        en_ = "";
        ru_ = "";
    }

    public Card(int box, String en, String ru){
        box_ = box;
        en_ = en;
        ru_ = ru;
    }
    @Override
    public int getBox(){return box_;}

    @Override
    public String getEn(){
        return en_;
    }

    @Override
    public String getRu(){
        return ru_;
    }

    @Override
    public void setBox(int box){box_ = box;}

    @Override
    public void setEn(String en){
        en_ = en;
    }

    @Override
    public void setRu(String ru){
        ru_ = ru;
    }
}
