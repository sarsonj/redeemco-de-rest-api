package de.redeemco.sample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.widget.EditText;
import de.redeemco.library.RedeemCodeActivator;
import de.redeemco.library.constants.VerificationStatus;

/**
 * Shows dialog that requests for redeem code.
 */
public class SampleRedeemCodeDialog {

    /**
     * Shows dialog.
     *
     * @param activity parent activity
     * @param appKey   app key
     */
    public static void show(final MainActivity activity, final String appKey) {
        final EditText input = new EditText(activity);
        new AlertDialog.Builder(activity)
                .setTitle(R.string.enter_redeem_code_dialog_title)
                .setMessage(R.string.enter_redeem_code_dialog_message)
                .setView(input)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Editable redeemCode = input.getText();
                        new RedeemCodeActivator(activity, appKey, redeemCode.toString(), new AbstractProcessingCallback(activity) {
                            @Override
                            public void processingCompleted(VerificationStatus verificationStatus) {
                                displayMessage(activity.getString(R.string.enter_redeem_code_dialog_success), new AfterButtonClickedAction() {
                                    @Override
                                    public void execute() {
                                        activity.showUnlockedState();
                                    }
                                });
                            }

                            @Override
                            protected void handleError() {
                                activity.showLockedState();
                            }
                        }).execute();
                    }
                }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Do nothing.
            }
        }).show();
    }
}
