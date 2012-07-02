package de.redeemco.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import de.redeemco.library.RedeemCodeValidator;

/**
 * Sample usage of Redeemco.de verification library.
 */
public class MainActivity extends Activity {

    /**
     * Redeem Code Validator
     */
    private RedeemCodeValidator mTask;

    /**
     * Put APP KEY taken from redeemco.de here.
     */
//    public static final String APP_KEY = "D9NQMDNG8AB9HDIYNR7ESBXIGCHTZS";

    public static final String APP_KEY = "A8QK4CKZ35Y5Z9LNW3IZ1M2HG5EBHY";

    private Button bApplyRedeemCode;

    private TextView tvStatus;
    private ProgressBar pbLoading;

    /**
     * Called when Activity is created.
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // text view with verificationStatus
        tvStatus = (TextView) findViewById(R.id.textViewStatus);

        // button used for application of redeem code
        bApplyRedeemCode = (Button) findViewById(R.id.buttonApplyRedeemcode);

        // progress bar
        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);

        // this code handles long verification
        @SuppressWarnings("deprecated")
        Object retained = getLastNonConfigurationInstance();
        if (retained instanceof RedeemCodeValidator) {
            mTask = (RedeemCodeValidator) retained;
            mTask.setActivity(this);
        } else {
            mTask = new RedeemCodeValidator(this, APP_KEY, new SampleLicenseChecking(this));
            mTask.execute();
        }
    }


    /**
     * After a screen orientation change, this method is invoked.
     * As we're going to state save the task, we can no longer associate
     * it with the Activity that is going to be destroyed here.
     */
    @Override
    @SuppressWarnings("deprecated")
    public Object onRetainNonConfigurationInstance() {
        mTask.setActivity(null);
        return mTask;
    }

    /**
     * Puts controls to unlocked state.
     */
    public void showUnlockedState() {
        pbLoading.setVisibility(View.GONE);
        bApplyRedeemCode.setEnabled(false);
        tvStatus.setText(R.string.status_unlocked);
    }

    /**
     * Puts controls to locked state.
     */
    public void showLockedState() {
        pbLoading.setVisibility(View.GONE);
        bApplyRedeemCode.setEnabled(true);
        tvStatus.setText(R.string.status_free);
        bApplyRedeemCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SampleRedeemCodeDialog.show(MainActivity.this, MainActivity.APP_KEY);
            }
        });
    }
}
