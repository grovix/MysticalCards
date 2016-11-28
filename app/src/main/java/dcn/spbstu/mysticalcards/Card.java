package dcn.spbstu.mysticalcards;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Card implements iCard, Serializable {
    private int box_;
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

//    private Card(Parcel in) {
//        String[] data = new String[2];
//        in.readStringArray(data);
//        en_ = data[0];
//        ru_ = data[1];
//
//    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeStringArray(new String[] {en_, ru_});
//    }
//
//    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
//        @Override
//        public Card createFromParcel(Parcel source) {
//            return new Card(source);
//        }
//
//        @Override
//        public Card[] newArray(int size) {
//            return new Card[size];
//        }
//    };


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
