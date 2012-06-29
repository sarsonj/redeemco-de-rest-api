package de.redeemco.library;

import de.redeemco.library.constants.ErrorCode;
import de.redeemco.library.constants.VerificationStatus;

/**
 * Class holding response.
 */
public class ProcessingResult {

    /**
     * Verification status
     */
    private final VerificationStatus status;

    /**
     * Cause of the error or null in case of status != {@link de.redeemco.library.constants.VerificationStatus.ERROR}
     */
    private final ErrorCode cause;

    /**
     * Class constructor.
     *
     * @param status status
     * @param cause  cause
     */
    ProcessingResult(VerificationStatus status, ErrorCode cause) {
        this.status = status;
        this.cause = cause;
    }

    /**
     * Factory method for error.
     *
     * @param cause cause
     * @return response instance
     */
    public static ProcessingResult buildErrorResponse(ErrorCode cause) {
        return new ProcessingResult(VerificationStatus.ERROR, cause);
    }

    /**
     * Factory method for correct response.
     *
     * @param status status
     * @return response instance
     */
    public static ProcessingResult buildResponse(VerificationStatus status) {
        return new ProcessingResult(status, null);
    }

    /**
     * Getter for status.
     *
     * @return status
     */
    public VerificationStatus getStatus() {
        return status;
    }

    /**
     * Getter for cause.
     *
     * @return cause
     */
    public ErrorCode getCause() {
        return cause;
    }
}
