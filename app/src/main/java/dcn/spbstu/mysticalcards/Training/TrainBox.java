package dcn.spbstu.mysticalcards.Training;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import dcn.spbstu.mysticalcards.Card;
import dcn.spbstu.mysticalcards.R;


public class TrainBox extends AppCompatActivity implements View.OnClickListener {

    enum type {EN_RU, RU_EN};

    TextView myText;
    Button guessNo;
    Button guessYes;
    int iTraining = 0;
    int numberOfWords;
    int counterYes, counterNo = 0;
    ArrayList<Card> box = new ArrayList<Card>();

    private static type TYPE_OF_TRAINING;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_box);
        box = (ArrayList<Card>) getIntent().getSerializableExtra("box");
        numberOfWords = getIntent().getExtras().getInt("numOfWords", 5);
        Toast.makeText(this, "Pos: " + getIntent().getExtras().getInt("tr_dir"), Toast.LENGTH_LONG).show();

        switch (getIntent().getExtras().getInt("tr_dir")) {
            case 0:
                TYPE_OF_TRAINING = type.EN_RU;
                break;
            case 1:
                TYPE_OF_TRAINING = type.RU_EN;
                break;
        }

        myText = (TextView) findViewById(R.id.main_word);
        guessNo = (Button) findViewById(R.id.guess_b_no);
        guessYes = (Button) findViewById(R.id.guess_b_yes);
        guessNo.setOnClickListener(this);
        guessYes.setOnClickListener(this);
    }


    public void onClick(View v) {

        iTraining++;
        switch (TYPE_OF_TRAINING) {
            case EN_RU:
                if (iTraining >= numberOfWords) {
                    switch (v.getId()) {
                        case R.id.guess_b_no:
                            counterNo++;
                            Intent intent = new Intent(this, TrainBox_rev_side.class);
                            intent.putExtra("translation", box.get(iTraining - 1).getRu());
                            startActivity(intent);
                            break;
                        case R.id.guess_b_yes:
                            box.get(iTraining - 1).setBox(box.get(iTraining- 1).getBox() + 1);
                            counterYes++;
                            finish();
                            break;
                    }
                } else {
                    switch (v.getId()) {
                        case R.id.guess_b_no:
                            counterNo++;
                            Intent intent = new Intent(this, TrainBox_rev_side.class);
                            intent.putExtra("translation", box.get(iTraining - 1).getRu());
                            startActivity(intent);
                            break;
                        case R.id.guess_b_yes:
                            box.get(iTraining - 1).setBox(box.get(iTraining- 1).getBox() + 1);
                            counterYes++;
                            if (iTraining >= numberOfWords)
                                finish();
                            myText.setText(box.get(iTraining).getEn());
                            break;
                    }
                }
                break;
            case RU_EN:
                if (iTraining >= numberOfWords) {
                    switch (v.getId()) {
                        case R.id.guess_b_no:
                            counterNo++;
                            Intent intent = new Intent(this, TrainBox_rev_side.class);
                            intent.putExtra("translation", box.get(iTraining - 1).getEn());
                            startActivity(intent);
                            break;
                        case R.id.guess_b_yes:
                            box.get(iTraining - 1).setBox(box.get(iTraining- 1).getBox() + 1);
                            counterYes++;
                            finish();
                            break;
                    }
                } else {
                    switch (v.getId()) {
                        case R.id.guess_b_no:
                            counterNo++;
                            Intent intent = new Intent(this, TrainBox_rev_side.class);
                            intent.putExtra("translation", box.get(iTraining - 1).getEn());
                            startActivity(intent);
                            break;
                        case R.id.guess_b_yes:
                            box.get(iTraining - 1).setBox(box.get(iTraining- 1).getBox() + 1);
                            counterYes++;
                            if (iTraining >= numberOfWords)
                                finish();
                            myText.setText(box.get(iTraining).getRu());
                            break;
                    }
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (iTraining >= numberOfWords)
            finish();
        else {
            switch (TYPE_OF_TRAINING) {
                case EN_RU:
                    myText.setText(box.get(iTraining).getEn());
                    break;
                case RU_EN:
                    myText.setText(box.get(iTraining).getRu());
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // DialogFragmentForBackBtn backBtn = new DialogFragmentForBackBtn();

    }
}

