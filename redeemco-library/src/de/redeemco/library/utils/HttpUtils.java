package de.redeemco.library.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Helper class for querying web.
 */
public class HttpUtils {

    /**
     * Returns content of the URL or null in case of error.
     *
     * @param webUrl url to be loaded
     * @return content of the URL or null in case of error
     * @throws MalformedURLException in case of malformed URL entered as parameter
     */
    public static String getContent(String webUrl) throws IOException {

        URL url = new URL(webUrl);
        HttpURLConnection urlConnection = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return readStream(in);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

    }

    /**
     * Reads content of the input stream to String instance.
     *
     * @param is input stream
     * @return content of the input stream.
     * @throws IOException in multiple I/O related cases
     */
    private static String readStream(InputStream is) throws IOException {
        final char[] buffer = new char[0x10000];
        StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(is, "UTF-8");
        try {
            int read;
            do {
                read = in.read(buffer, 0, buffer.length);
                if (read > 0) {
                    out.append(buffer, 0, read);
                }
            } while (read >= 0);
        } finally {
            in.close();
        }
        return out.toString();
    }


}
