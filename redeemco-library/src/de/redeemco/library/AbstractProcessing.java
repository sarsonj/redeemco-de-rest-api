package de.redeemco.library;

import android.app.Activity;
import android.os.AsyncTask;
import android.provider.Settings;
import de.redeemco.library.constants.ErrorCode;
import de.redeemco.library.constants.VerificationStatus;
import de.redeemco.library.utils.HttpUtils;
import de.redeemco.library.utils.LoggingUtils;

import java.net.UnknownHostException;

/**
 * Abstract REST processing.
 */
public abstract class AbstractProcessing extends AsyncTask<Void, Void, ProcessingResult> {

    /**
     * Parent activity.
     */
    protected Activity activity;

    /**
     * Verification callback.
     */
    protected final ProcessingCallback processingCallback;

    /**
     * App Key.
     */
    protected final String appKey;

    /**
     * Class contructor.
     *
     * @param activity           parent activity
     * @param appKey             app key
     * @param processingCallback processing callback
     */
    protected AbstractProcessing(Activity activity, String appKey, ProcessingCallback processingCallback) {
        this.activity = activity;
        this.appKey = appKey;
        this.processingCallback = processingCallback;
    }

    /**
     * Setter for activity.
     *
     * @param activity parent activity
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * Returns result back to callback.
     *
     * @param o result of the processing.
     */
    @Override
    protected final void onPostExecute(ProcessingResult o) {
        super.onPostExecute(o);
        if (o.getStatus() == VerificationStatus.ERROR) {
            processingCallback.processingError(o.getCause());
        } else {
            processingCallback.processingCompleted(o.getStatus());
        }
    }

    /**
     * Performs HTTP request.
     *
     * @param objects not used, required by {@link android.os.AsyncTask} API.
     * @return result of the processing.
     */
    @Override
    protected final ProcessingResult doInBackground(Void... objects) {
        // get unique id of the device
        String deviceId = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);

        // create REST URL
        String url = createUrl(appKey, deviceId);

        try {
            LoggingUtils.logDebug("Executing " + url);
            return processContent(HttpUtils.getContent(url));
        } catch (UnknownHostException e) {
            LoggingUtils.logException("Error loading " + url, e);
            return ProcessingResult.buildErrorResponse(ErrorCode.INTERNET_CONNECTION_ERROR);
        } catch (Exception e) {
            LoggingUtils.logException("Error loading " + url, e);
            return ProcessingResult.buildErrorResponse(ErrorCode.UNKNOW_CAUSE);
        }
    }

    /**
     * Should process content of the web and return result.
     *
     * @param content content of the URL
     * @return result
     */
    protected abstract ProcessingResult processContent(String content);

    /**
     * Should build URL to be queried.
     *
     * @param appKey   key of the app
     * @param deviceId device id
     * @return URL as string.
     */
    protected abstract String createUrl(String appKey, String deviceId);
}
