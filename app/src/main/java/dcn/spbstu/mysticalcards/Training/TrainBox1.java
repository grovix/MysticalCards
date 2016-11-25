package dcn.spbstu.mysticalcards.Training;


import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import dcn.spbstu.mysticalcards.Training.DialogFragmentForBackBtn;
import dcn.spbstu.mysticalcards.Card;
import dcn.spbstu.mysticalcards.R;

public class TrainBox1 extends AppCompatActivity implements View.OnClickListener{

    TextView myText;
    Button guessNo;
    Button guessYes;
    int iTraining = 0;
    //   Card[] cards_b1 = new Card[5];
    Card[] card_t = new Card[5];
    final int numberOfWords = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_box1);

        //private int numberOfWords = iConfig.getNumberOfWords();
        //Пока нет интерфейса для получения количества слов
        card_t[0] = new Card(1, "work", "работа");
        card_t[1] = new Card(1, "nose", "нос");
        card_t[2] = new Card(1, "rage", "гнев");
        card_t[3] = new Card(1, "head", "голова");
        card_t[4] = new Card(1, "river", "река");

        final String word_ru = "Слово";
        final String word_eu = "Word";


        //cards_b1 =(Card[]) getIntent().getExtras().get("card");


        myText = (TextView) findViewById(R.id.main_word);

        guessNo = (Button) findViewById(R.id.guess_b_no);
        guessYes = (Button) findViewById(R.id.guess_b_yes);


        guessNo.setOnClickListener(this);
        guessYes.setOnClickListener(this);

        myText.setText(card_t[0].getRu());
    }


    public void onClick(View v) {

        iTraining++;
        if (iTraining >= numberOfWords) {
            switch (v.getId()) {
                case R.id.guess_b_no:
                    Intent intent = new Intent(this, TrainBox1_rev_side.class);
                    intent.putExtra("translation", card_t[iTraining - 1].getEn());
                    startActivity(intent);
                    break;
                case R.id.guess_b_yes:
                    finish();
                    break;
            }
        } else {
            switch (v.getId()) {
                case R.id.guess_b_no:
                    Intent intent = new Intent(this, TrainBox1_rev_side.class);
                    intent.putExtra("translation", card_t[iTraining - 1].getEn());
                    startActivity(intent);
                    break;
                case R.id.guess_b_yes:
                    if (iTraining >= numberOfWords)
                        finish();
                    myText.setText(card_t[iTraining].getRu());
                    break;
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (iTraining >= numberOfWords)
            finish();
        else
            myText.setText(card_t[iTraining].getRu());
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

       // DialogFragmentForBackBtn backBtn = new DialogFragmentForBackBtn();

    }
}

