package dcn.spbstu.mysticalcards.Training;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import dcn.spbstu.mysticalcards.Card;
import dcn.spbstu.mysticalcards.R;
import dcn.spbstu.mysticalcards.Storage;

import static dcn.spbstu.mysticalcards.R.id.storage;

public class ChooseTrainingActivity extends AppCompatActivity implements View.OnClickListener {

    AlertDialog.Builder builder;
    ArrayList<Card> box = new ArrayList<Card>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_training);
        //    getIntent().removeExtra("info");

        builder = new AlertDialog.Builder(ChooseTrainingActivity.this);
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);
        builder.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ChooseTrainingActivity.this, "Вы нажали ОК", Toast.LENGTH_LONG)
                        .show();
            }
        });

        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ChooseTrainingActivity.this, "Вы нажали отмена", Toast.LENGTH_LONG)
                        .show();
            }
        });

        builder.setCancelable(true);

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(ChooseTrainingActivity.this, "Вы ничего не выбрали", Toast.LENGTH_LONG)
                        .show();
            }
        });

        box.add(new Card(1, "work", "работа"));
        box.add(new Card(1, "nose", "нос"));
        box.add(new Card(1, "rage", "гнев"));
        box.add(new Card(1, "head", "голова"));
        box.add(new Card(1, "river", "река"));


        Button training1 = (Button) findViewById(R.id.training1);
        Button training2 = (Button) findViewById(R.id.training2);
        Button training3 = (Button) findViewById(R.id.training3);
        Button training4 = (Button) findViewById(R.id.training4);
        Button training5 = (Button) findViewById(R.id.training5);
        training1.setOnClickListener(this);
        training2.setOnClickListener(this);
        training3.setOnClickListener(this);
        training4.setOnClickListener(this);
        training5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.training1:
                startTraining(1);
                break;
            case R.id.training2:
                startTraining(2);
                break;
            case R.id.training3:
                startTraining(3);
                //Передается номер коробки, чтобы после выбора параметров в диалоге создалось активити с нужным набором карточек
                break;
            case R.id.training4:
                startTraining(4);
                break;
            case R.id.training5:
                startTraining(5);
                break;
        }

    }

    private void startTraining(int boxNumber) {
        getIntent().putExtra("boxNumber", boxNumber);
        new CustomDialogFragment().show(getSupportFragmentManager(), "conf");
//        int know = getIntent().getExtras().getInt("know");
//        int dont_know = getIntent().getExtras().getInt("dont_know");
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Результат тренировки:\nЗнаете слов: " + know + "\nНе знаете слов: " + dont_know).setTitle("Привет").setPositiveButton("Я молодец!", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//                finish();
//            }
//        });
//        AlertDialog alert = builder.create();
//        alert.show();

    }
}




