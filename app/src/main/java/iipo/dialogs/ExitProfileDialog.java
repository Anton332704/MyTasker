package iipo.dialogs;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker.Authorization;
import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker.R;

/**
 * Created by user on 10.11.2015.
 */
public class ExitProfileDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.exit);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("login", "");
                editor.commit();
                Intent intent = new Intent(getActivity(), Authorization.class);
                startActivity(intent);
                dialog.cancel();
                getActivity().finish();
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }


}
