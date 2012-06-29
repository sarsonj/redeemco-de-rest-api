package de.redeemco.library;

import android.app.Activity;
import de.redeemco.library.constants.Constants;
import de.redeemco.library.constants.VerificationStatus;

/**
 * Simple implementation of Redeemco.de REST API validation.
 */
public class RedeemCodeValidator extends AbstractProcessing {

    /**
     * @param activity           parent activity
     * @param processingCallback callback. Can't be null.
     */
    public RedeemCodeValidator(Activity activity, String appKey, ProcessingCallback processingCallback) {
        super(activity, appKey, processingCallback);
    }

    @Override
    protected ProcessingResult processContent(String content) {
        return ProcessingResult.buildResponse(content.indexOf(Constants.REST_STATUS_RESPONSE_APPROVED) > 0 ? VerificationStatus.UNLOCKED : VerificationStatus.LOCKED);
    }

    @Override
    protected String createUrl(String appKey, String deviceId) {
        return String.format(Constants.REST_API_DEVICE_STATUS, appKey, deviceId);
    }

}
