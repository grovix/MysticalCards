package dcn.spbstu.mysticalcards.Training;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import dcn.spbstu.mysticalcards.R;

public class TrainBox_rev_side extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_box_rev_side);

        final TextView myText = (TextView) findViewById(R.id.transl_word);
        String translation = getIntent().getExtras().getString("translation");
        //String translation = iCard.getEN();
        myText.setText("Перевод слова: " + translation);
        //Button btn = (Button) findViewById(R.id.return_button);
        TextView retText = (TextView) findViewById(R.id.ret_view);

        retText.setOnClickListener(this);
        myText.setOnClickListener(this);


//
//        Button guessNo = (Button) findViewById(R.id.guess_b_no);
//        Button guessYes = (Button) findViewById(R.id.guess_b_yes);
//        Button turn_card = (Button) findViewById(R.id.turn_card);
//
//
//        View.OnClickListener onClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //for (int i = 0; i < numberOfWords; ) {
//                switch (v.getId()) {
//                    case R.id.turn_card:
//                        Intent intent = new Intent(TrainBox_rev_side.this, TrainBox.class);
//                        startActivity(intent);
//                        break;
//                    case R.id.guess_b_no:
//                        myText.setText("Я не знаю слово");
//                        break;
//                    case R.id.guess_b_yes:
//                        //i++;
//                        myText.setText("Word");
//                        intent = new Intent(TrainBox_rev_side.this, TrainBox.class);
//                        startActivity(intent);
//                        //To be implemented
//                        break;
//                }
//            }
//            //}
//        };
//        turn_card.setOnClickListener(onClickListener);
//        guessNo.setOnClickListener(onClickListener);
//        guessYes.setOnClickListener(onClickListener);
//
//
////        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
////        setSupportActionBar(toolbar);
////
////        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
////        fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////            }
////        });
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ret_view:
                finish();
                break;
            case R.id.transl_word:
                finish();
                break;

        }
    }

}
