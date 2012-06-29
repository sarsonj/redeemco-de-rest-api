package de.redeemco.library;

import android.app.Activity;
import de.redeemco.library.constants.Constants;
import de.redeemco.library.constants.ErrorCode;
import de.redeemco.library.constants.VerificationStatus;

/**
 * Class
 */
public class RedeemCodeActivator extends AbstractProcessing {

    /**
     * Redeem Code to be processed.
     */
    private final String redeemCode;

    /**
     * Class constructor matching {@link AbstractProcessing}'s constructor.
     *
     * @param activity           activity
     * @param appKey             app key
     * @param processingCallback processing callback
     */
    public RedeemCodeActivator(Activity activity, String appKey, String redeemCode, ProcessingCallback processingCallback) {
        super(activity, appKey, processingCallback);
        this.redeemCode = redeemCode;
    }

    @Override
    protected ProcessingResult processContent(String content) {
        if (content.indexOf(Constants.REST_ACTIVATION_RESPONSE_REDEEMED) > 0) {
            return ProcessingResult.buildResponse(VerificationStatus.UNLOCKED);
        }
        if (content.indexOf(Constants.REST_ACTIVATION_RESPONSE_ALREADYUSED) > 0) {
            return ProcessingResult.buildErrorResponse(ErrorCode.ALREADY_USED);
        }
        if (content.indexOf(Constants.REST_ACTIVATION_RESPONSE_WRONGCODE) > 0) {
            return ProcessingResult.buildErrorResponse(ErrorCode.INVALID_CODE);
        }
        throw new UnsupportedOperationException("Should never reach this place");
    }

    @Override
    protected String createUrl(String appKey, String deviceId) {
        return String.format(Constants.REST_API_ACTIVATION, appKey, deviceId, redeemCode);
    }


}
