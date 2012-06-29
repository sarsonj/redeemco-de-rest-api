package de.redeemco.library.utils;

import android.util.Log;

/**
 * Wraps Error Logging calls.
 */
public class LoggingUtils {

    /**
     * Logging tag.
     */
    private static final String TAG = "RedeemCodeLibrary";

    /**
     * Logs Exception
     *
     * @param msg message
     * @param e   exception or error
     */
    public static void logException(String msg, Throwable e) {
        Log.e(TAG, msg + " [" + e + "]", e);
    }

    /**
     * Logs debug messages.
     *
     * @param msg message
     */
    public static void logDebug(String msg) {
        Log.d(TAG, msg);
    }
}
