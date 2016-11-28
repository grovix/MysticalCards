package dcn.spbstu.mysticalcards.Training;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import dcn.spbstu.mysticalcards.Card;
import dcn.spbstu.mysticalcards.R;


public class CustomDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    private View form = null;
    private final String[] trnsl_dir = {"Eng->Rus", "Rus->Eng"};
    private final String[] nmbOfWords = {"5", "10", "25", "50", "100"};
    int[] word_num = {5, 10, 25, 50, 100};
    private Spinner spinner;
    private Spinner spinner_w;
    private String tr_dir;
    private String nmbWords;
    int q_Words;
    int posOfspin_w;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        super.onCreateDialog(savedInstanceState);
        form = getActivity().getLayoutInflater()
                .inflate(R.layout.configuration, null);
        spinner = (Spinner) form.findViewById(R.id.trans_dir_spinner);
        spinner_w = (Spinner) form.findViewById(R.id.numOfWords_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, trnsl_dir);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posOfspin_w = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter_words = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, nmbOfWords);
        adapter_words.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_w.setAdapter(adapter_words);
        spinner_w.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                q_Words = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Конфигурация тренировки").setView(form)
                .setPositiveButton("Ок",this)
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        String inf = getActivity().getIntent().getExtras().getString("info");
        Intent intent = new Intent(getContext(), TrainBox.class);
        intent.putExtra("box", getActivity().getIntent().getSerializableExtra("box"));
        intent.putExtra("numOfWords", word_num[q_Words]);
        intent.putExtra("tr_dir", posOfspin_w);

        startActivity(intent);

    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//        if (id == parent.getSelectedItemId()) {
//            tr_dir = spinner.getSelectedItem().toString();
//            temp = position;
//            Toast.makeText(getContext(), "Position how it is. THIS: " + temp, Toast.LENGTH_SHORT).show();
//        }
//        if (id == parent.getSelectedItemId()) {
//            nmbWords = spinner_w.getSelectedItem().toString();
//            posOfspin_w = position;
//        }
//    }
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }

    @Override
    public void onDismiss(DialogInterface unused) {
        super.onDismiss(unused);
    }

    @Override
    public void onCancel(DialogInterface unused) {
        super.onCancel(unused);
    }
}
