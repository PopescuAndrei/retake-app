package com.retake.retakeapp.utils;

import android.R;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;

public final class PopDialog {

	public static final int TYPE_OK = 1;

	public static void showDialog(Context ctx, String title, String message,
			android.content.DialogInterface.OnClickListener ocl) {
		Builder builder = new AlertDialog.Builder(ctx);
		builder.setMessage(message).setTitle(title)
				.setNeutralButton(ctx.getText(R.string.ok), ocl);
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public static void showDialog(Context ctx, String message,
			OnClickListener yes, OnClickListener no) {
		Builder builder = new AlertDialog.Builder(ctx);
		builder.setMessage(message)
				.setPositiveButton(ctx.getText(R.string.yes), yes)
				.setNegativeButton(ctx.getText(R.string.no), no)
				.setCancelable(true);
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public static void showDialog(Context ctx, String message,
			String positiveMessage, String negativeMessage,
			OnClickListener yes, OnClickListener no) {
		Builder builder = new AlertDialog.Builder(ctx);
		builder.setMessage(message).setPositiveButton(positiveMessage, yes)
				.setNegativeButton(negativeMessage, no).setCancelable(true);
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public static void showDialog(Context ctx, String message,
			OnClickListener ok) {
		Builder builder = new AlertDialog.Builder(ctx);
		builder.setMessage(message).setNeutralButton(ctx.getText(R.string.ok),
				ok);
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public static void showDialog(Context ctx, String message,
			OnClickListener ok, OnCancelListener onCancelListener) {
		Builder builder = new AlertDialog.Builder(ctx);
		builder.setMessage(message).setNeutralButton(ctx.getText(R.string.ok),
				ok);
		builder.setOnCancelListener(onCancelListener);
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public static void showDialog(Context ctx, String message,
			OnClickListener ok, OnCancelListener onCancelListener,
			OnDismissListener onDismissListener) {
		Builder builder = new AlertDialog.Builder(ctx);
		builder.setMessage(message).setNeutralButton(ctx.getText(R.string.ok),
				ok);
		builder.setOnCancelListener(onCancelListener);
		//builder.setOnDismissListener(onDismissListener);
		AlertDialog dialog = builder.create();
		dialog.show();
	}

}
