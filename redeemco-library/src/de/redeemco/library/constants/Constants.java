package de.redeemco.library.constants;

/**
 * Holds various constants.
 */
public class Constants {

    /**
     * Base REST api.
     */
    public static final String REST_API_BASE_URL = "http://redeemco.de/api/v1/%s/";

    // --------------------------------------------------------------------------------------------------------

    /**
     * REST API method for device status.
     */
    public static final String REST_API_DEVICE_STATUS = REST_API_BASE_URL + "devicestatus?deviceid=%s";

    /**
     * Positive response
     */
    public static final String REST_STATUS_RESPONSE_APPROVED = "approved";

    // --------------------------------------------------------------------------------------------------------

    /**
     * REST API method for activation of redeem code.
     */
    public static final String REST_API_ACTIVATION = REST_API_BASE_URL + "redeemcode?deviceid=%s&redeemcode=%s";

    /**
     * Activation response in case of wrong code.
     */
    public static final String REST_ACTIVATION_RESPONSE_WRONGCODE = "wrongcode";

    /**
     * Activation response when code was used on different device.
     */
    public static final String REST_ACTIVATION_RESPONSE_ALREADYUSED = "alreadyused";

    /**
     * Success response for activation.
     */
    public static final String REST_ACTIVATION_RESPONSE_REDEEMED = "redeemed";
}
