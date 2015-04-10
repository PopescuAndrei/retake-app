package com.retake.retakeapp.beacon.logging;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.retakeapp.R;

/**
 * Created by Work 2 on 12/17/2014.
 */
public class LogDialogFragment extends DialogFragment {

    public interface LogDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    LogDialogListener mLogDialogListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mLogDialogListener = (LogDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement LogDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_log, null);
        builder.setView(dialogView)
                .setPositiveButton("Send logs", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mLogDialogListener.onDialogPositiveClick(LogDialogFragment.this);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mLogDialogListener.onDialogNegativeClick(LogDialogFragment.this);
                    }
                });
        return builder.create();
    }
}
