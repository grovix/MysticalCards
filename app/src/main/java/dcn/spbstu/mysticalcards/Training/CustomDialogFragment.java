package dcn.spbstu.mysticalcards.Training;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import dcn.spbstu.mysticalcards.R;



public class CustomDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    private View form = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        form = getActivity().getLayoutInflater()
                .inflate(R.layout.configuration, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Конфигурация тренировки").setView(form)
                .setPositiveButton("Ок",this)
                .setNegativeButton("Отмена",this);

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

//        switch ()
        Intent intent = new Intent(getContext(), TrainBox1.class);
        startActivity(intent);

    }

    @Override
    public void onDismiss(DialogInterface unused) {
        super.onDismiss(unused);
    }

    @Override
    public void onCancel(DialogInterface unused) {
        super.onCancel(unused);
    }
}
