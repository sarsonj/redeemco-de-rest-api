package de.redeemco.sample;

import de.redeemco.library.constants.VerificationStatus;

/**
 * Sample implementation of verification callback.
 */
public class SampleLicenseChecking extends AbstractProcessingCallback {

    /**
     * Class constuctor.
     *
     * @param activity parent activity
     */
    public SampleLicenseChecking(MainActivity activity) {
        super(activity);
    }

    /**
     * Lets finish activity when there is an error during license verification.
     */
    @Override
    protected void handleError() {
        activity.finish();
    }

    /**
     * Called after successful communication with server.
     *
     * @param verificationStatus verificationStatus of the application
     */
    @Override
    public void processingCompleted(VerificationStatus verificationStatus) {

        // set controls to unlocked state
        if (verificationStatus == VerificationStatus.UNLOCKED) {
            activity.showUnlockedState();
        }

        // set controls to locked state
        if (verificationStatus == VerificationStatus.LOCKED) {
            activity.showLockedState();
        }
    }

}
