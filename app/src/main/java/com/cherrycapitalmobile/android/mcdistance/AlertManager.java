package com.cherrycapitalmobile.android.mcdistance;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertManager {

    public static void showAlert(Context appContext, String title, String message) {
        AlertDialog dialog = new AlertDialog.Builder(appContext).create();

        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setIcon(R.drawable.ic_launcher);
        dialog.show();
    }
}
