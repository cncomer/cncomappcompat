package com.cncom.app.library.appcompat.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;

public class AppCompatDialogUtils {
	
	public interface DialogCallback extends DialogInterface.OnClickListener, 
		DialogInterface.OnDismissListener, 
		DialogInterface.OnCancelListener {
		@Override
        public void onCancel(DialogInterface dialog); 
		@Override
        public void onDismiss(DialogInterface dialog);
		@Override
        public void onClick(DialogInterface dialog, int which);
		
	}
	
	public static class DialogCallbackSimpleImpl implements DialogCallback {

		@Override
        public void onCancel(DialogInterface dialog) {
	        
        }

		@Override
        public void onDismiss(DialogInterface dialog) {
	        
        }

		@Override
        public void onClick(DialogInterface dialog, int which) {
	        switch(which) {
	        case DialogInterface.BUTTON_POSITIVE:
	        	onPositiveButtonClick(dialog);
	        	break;
	        case DialogInterface.BUTTON_NEGATIVE:
	        	onNegativeButtonClick(dialog);
	        	break;
	        }
        }
		
		public void onPositiveButtonClick(DialogInterface dialog) {}
		public void onNegativeButtonClick(DialogInterface dialog) {}
		
	}
	
	public static DialogCallback getDialogCallbackSimpleImpl() {
		return new DialogCallbackSimpleImpl();
	}
	public static void createSimpleConfirmAlertDialog(Context context, CharSequence message) {
		createSimpleConfirmAlertDialog(context, null, message);
	}

	public static void createSimpleConfirmAlertDialog(Context context, CharSequence title, CharSequence message) {
		createSimpleConfirmAlertDialog(context, title, message, context.getString(android.R.string.ok), null, null);
	}

	public static void createSimpleConfirmAlertDialog(Context context, CharSequence message, DialogCallback callback) {
		createSimpleConfirmAlertDialog(context, null, message, context.getString(android.R.string.ok), context.getString(android.R.string.cancel), true, callback);
	}

	/**
	 * 创建一个简单的确认对话框
	 * @param context
	 * @param message
	 * @param callback
	 */
	public static void createSimpleConfirmAlertDialog(Context context, CharSequence message, CharSequence positiveButton, CharSequence negativeButton, DialogCallback callback) {
		createSimpleConfirmAlertDialog(context, null, message, positiveButton, negativeButton, callback);
	}

	public static void createSimpleConfirmAlertDialog(Context context, CharSequence title, CharSequence message, DialogCallback callback) {
		createSimpleConfirmAlertDialog(context, title, message, context.getString(android.R.string.ok), context.getString(android.R.string.cancel), callback);
	}

	/**
	 * 创建一个简单的确认对话框
	 * @param context
	 * @param message
	 * @param callback
	 */
	public static void createSimpleConfirmAlertDialog(Context context, CharSequence title, CharSequence message, CharSequence positiveButton, CharSequence negativeButton, DialogCallback callback) {
		createSimpleConfirmAlertDialog(context, title, message, positiveButton, negativeButton, false, callback);
	}

	/**
	 * 创建一个简单的确认对话框
	 * @param context
	 * @param message
	 * @param callback
	 */
	public static void createSimpleConfirmAlertDialog(Context context, CharSequence title, CharSequence message, CharSequence positiveButton, CharSequence negativeButton, boolean cancelable, DialogCallback callback) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(title)
				.setMessage(message);
		if (positiveButton != null) {
			builder.setPositiveButton(positiveButton, callback);
		}
		if (negativeButton != null) {
			builder.setNegativeButton(negativeButton, callback);
		}
		builder.setCancelable(cancelable);
		builder.setOnCancelListener(callback);
		Dialog dialog = builder.create();
		if (context instanceof Activity) {

		} else {
			dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		}

		dialog.show();
	}
	
	public static void createSimpleConfirmAlertDialog(Context context, int messageResId, int positiveButton, int negativeButton, DialogCallback callback) {
		createSimpleConfirmAlertDialog(context, context.getString(messageResId), positiveButton == -1?null:context.getString(positiveButton), negativeButton == -1?null:context.getString(negativeButton), callback);
	}
	
	public static void createSimpleInputDialog(Context context, CharSequence inputTitleText, CharSequence inputMessageText, EditText inputEdit, CharSequence positiveButton, CharSequence negativeButton, DialogCallback callback) {
		createSimpleInputDialog(context, inputTitleText, inputMessageText, inputEdit, positiveButton, negativeButton, callback, true);
	}

	public static void createSimpleInputDialog(Context context, CharSequence inputTitleText, CharSequence inputMessageText, EditText inputEdit, CharSequence positiveButton, CharSequence negativeButton, DialogCallback callback, boolean inputMustNoEmpty) {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
		if (!TextUtils.isEmpty(inputTitleText)) {
			dialogBuilder.setTitle(inputTitleText);
		}
		if (!TextUtils.isEmpty(inputMessageText)) {
			dialogBuilder.setMessage(inputMessageText);
		}
		dialogBuilder.setView(inputEdit);

		if (positiveButton != null) {
			dialogBuilder.setPositiveButton(positiveButton, callback);
		}
		if (negativeButton != null) {
			dialogBuilder.setNegativeButton(negativeButton, callback);
		}
		final AlertDialog dialog = dialogBuilder.create();
		if (context instanceof Activity) {

		} else {
			dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		}

		if (inputMustNoEmpty) {
			inputEdit.addTextChangedListener(new TextWatcher() {

				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {

				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {

				}

				@Override
				public void afterTextChanged(Editable s) {
					dialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(s.toString().trim().length() > 0);
				}

			});
		}

		dialog.show();
		if (inputMustNoEmpty) {
			dialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(inputEdit.getText().toString().trim().length() > 0);
		}
	}

}
