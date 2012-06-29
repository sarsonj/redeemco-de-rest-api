package de.redeemco.library;

import de.redeemco.library.constants.ErrorCode;
import de.redeemco.library.constants.VerificationStatus;

/**
 * Used for verification handling.
 */
public interface ProcessingCallback {

    /**
     * Called when there is some verification error.
     *
     * @param errorCode error code
     */
    void processingError(ErrorCode errorCode);

    /**
     * Called when verification successfully performed.
     *
     * @param verificationStatus verificationStatus of the application
     */
    void processingCompleted(VerificationStatus verificationStatus);
}
