package de.redeemco.library.constants;

/**
 * Error Codes.
 */
public enum ErrorCode {

    /**
     * Error connecting to the internet. Possible causes:
     * <p/>
     * <ul>
     * <li>&lt;uses-permission android:name="android.permission.INTERNET"/&gt; is not in your <code>AndroidManifest.xml</code></li>
     * <li>internet connection does not work</li>
     * </ul>
     */
    INTERNET_CONNECTION_ERROR,

    /**
     * Redeem code is not valid for given application.
     */
    INVALID_CODE,

    /**
     * Redeem code already used on different device.
     */
    ALREADY_USED,

    /**
     * Unable to better specify cause of the error. Most probably caused by something unexpected by API designer.
     */
    UNKNOW_CAUSE

}
