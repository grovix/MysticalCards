package dcn.spbstu.mysticalcards.Training;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import dcn.spbstu.mysticalcards.Card;
import dcn.spbstu.mysticalcards.R;

public class ChooseTrainingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_training);






        final AlertDialog.Builder builder = new AlertDialog.Builder(ChooseTrainingActivity.this);
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








        Button training1 = (Button) findViewById(R.id.training1);
        Button training2 = (Button) findViewById(R.id.training2);
        Button training3 = (Button) findViewById(R.id.training3);
        Button training4 = (Button) findViewById(R.id.training4);
        Button training5 = (Button) findViewById(R.id.training5);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.training1 :
                        Intent intent = new Intent(ChooseTrainingActivity.this, TrainBox1.class);


                        //intent.putExtra("card", card_t);
                        //intent.putExtra("cardEN", card_t.getEn());
                        startActivity(intent);
                        break;
                    case R.id.training2 :
                        builder.show();
                        //To be implemented
                        break;
                    case R.id.training3 :
                        new CustomDialogFragment().show(getSupportFragmentManager(), "conf");
                        //To be implemented
                        break;
                    case R.id.training4 :
                        //To be implemented
                        break;
                    case R.id.training5 :
                        //To be implemented
                        break;
                }
            }
        };

        training1.setOnClickListener(onClickListener);
        training2.setOnClickListener(onClickListener);
        training3.setOnClickListener(onClickListener);
        training4.setOnClickListener(onClickListener);
        training5.setOnClickListener(onClickListener);

    }

}


