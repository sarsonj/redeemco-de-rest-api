package de.redeemco.sample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import de.redeemco.library.ProcessingCallback;
import de.redeemco.library.constants.ErrorCode;

/**
 * Abstract processing callback.
 */
public abstract class AbstractProcessingCallback implements ProcessingCallback {
    /**
     * Holds reference to main activity.
     */
    protected MainActivity activity;

    /**
     * Class constructor.
     *
     * @param activity activity.
     */
    public AbstractProcessingCallback(MainActivity activity) {
        this.activity = activity;
    }

    /**
     * Called when error occurs.
     *
     * @param errorCode error code
     */
    @Override
    public void processingError(ErrorCode errorCode) {
        String msg = activity.getResources().getString(R.string.error_verifying_license, errorCode.toString());
        displayMessage(msg, new AfterButtonClickedAction() {
            @Override
            public void execute() {
                handleError();
            }
        });
    }

    /**
     * Called after error occurs and error message was shown to user.
     */
    protected abstract void handleError();

    /**
     * Callback interface called after message was shown.
     */
    protected interface AfterButtonClickedAction {
        public void execute();
    }

    /**
     * Displays message.
     *
     * @param msg                      message
     * @param afterButtonClickedAction called after OK button clicked
     */
    protected void displayMessage(String msg, final AfterButtonClickedAction afterButtonClickedAction) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        afterButtonClickedAction.execute();
                    }
                })
                .show();
    }
}
