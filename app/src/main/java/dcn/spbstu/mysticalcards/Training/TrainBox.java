package dcn.spbstu.mysticalcards.Training;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import dcn.spbstu.mysticalcards.Card;
import dcn.spbstu.mysticalcards.R;
import dcn.spbstu.mysticalcards.Storage;


public class TrainBox extends AppCompatActivity implements View.OnClickListener {

    enum type {EN_RU, RU_EN}

    TextView myText;
    Button guessNo;
    Button guessYes;
    int iTraining = 0;
    int numberOfWords, iterations;
    int counterYes, counterNo = 0;
    int know, dont_know;
    ArrayList<Card> shuffledArray = new ArrayList<>();
    ArrayList<Card> box = new ArrayList<Card>();

    private static type TYPE_OF_TRAINING;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_box);

        know = dont_know = 0;

        numberOfWords = getIntent().getExtras().getInt("numOfWords");
        int boxNumber = getIntent().getExtras().getInt("boxNumber");
        shuffledArray.clear();
        //Quantity of words is actual amount of words stored in the box %boxNumber%
        //Number of words is quantity of words that we want to be in our training box
        createBox(shuffledArray, boxNumber);
        int quantityOfWords = createTrainingBox(box);
        if (quantityOfWords == 0) {
            Toast.makeText(this, "Box №" + boxNumber + " is empty", Toast.LENGTH_LONG).show();
            finish();
        } else if (quantityOfWords != numberOfWords) {
            Toast.makeText(this, "We dont have enough words in this box, let's train with amount of words that we have", Toast.LENGTH_SHORT).show();
            numberOfWords = quantityOfWords;
        }

        iterations = getIntent().getExtras().getInt("iteration");

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
                            //shuffledArray.remove(iTraining - 1);
                            box.get(iTraining - 1).setBox(box.get(iTraining - 1).getBox() + 1);
                            counterYes++;
                            finishActivity();
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

                            box.get(iTraining - 1).setBox(box.get(iTraining - 1).getBox() + 1);
                            counterYes++;
                            if (iTraining >= numberOfWords)
                                finishActivity();
                            //else
                             //   shuffledArray.remove(iTraining - 1);
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
                            //shuffledArray.remove(iTraining - 1);
                            box.get(iTraining - 1).setBox(box.get(iTraining - 1).getBox() + 1);
                            counterYes++;
                            finishActivity();
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

                            box.get(iTraining - 1).setBox(box.get(iTraining - 1).getBox() + 1);
                            counterYes++;
                            if (iTraining >= numberOfWords)
                                finishActivity();
                           // else
                                //shuffledArray.remove(iTraining - 1);
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
        if (iTraining >= numberOfWords) {
            if (iterations > 1) {
                iterations--;
                iTraining = 0;
                box.clear();
                numberOfWords = counterNo;
                counterNo = 0;
                if ((createTrainingBox(box)) == 0) {
                    finishActivity();
                }

                switch (TYPE_OF_TRAINING) {
                    case EN_RU:
                        myText.setText(box.get(iTraining).getEn());
                        break;
                    case RU_EN:
                        myText.setText(box.get(iTraining).getRu());
                        break;
                }
            } else
                finishActivity();
        } else {
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


    public void finishActivity() {

        know = counterYes;
        dont_know = counterNo;
        if (iterations > 1 & dont_know != 0) {
            this.onResume();
            return;
        }

        for(int i = 0; i < dont_know; i++) {
            box.get(i).setBox(1);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Знаете слов: " + know + "\nНе знаете слов: " + dont_know).setTitle("Результат тренировки")
                .setPositiveButton("Я молодец!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        finish();
                    }
                }).setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Предупреждение").setMessage("Вы уверены, что хотите завершить тренировку?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TrainBox.super.onBackPressed();
                        dialog.cancel();
                        finish();
                    }
                }).setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // DialogFragmentForBackBtn backBtn = new DialogFragmentForBackBtn();

    }

    //Add cards from storage which are in the %boxNumber% box then shuffle them in random order.
    private void createBox(ArrayList<Card> shuffledArray, int boxNumber) {
        Storage storage = new Storage();

        for (int i = 0; i < storage.getSize(); i++) {
            if (storage.getCard(i).getBox() == boxNumber) {
                shuffledArray.add(storage.getCard(i));
            }
        }

        Random random = new Random();
        for(int i = 0; i < shuffledArray.size(); i++) {
            int index = random.nextInt(i + 1);
            Card temp = shuffledArray.get(index);
            shuffledArray.set(index, shuffledArray.get(i));
            shuffledArray.set(i, temp);
        }

    }

    //Create box with the exact number of words needed for training
    private int createTrainingBox(ArrayList<Card> box) {
        int j;
        for (j = 0 ; (j < shuffledArray.size()) & (j < numberOfWords); j++) {
            box.add(shuffledArray.get(j));
        }

        return j;
    }

}

