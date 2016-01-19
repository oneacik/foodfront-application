package pl.foodfront.communication;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.GsonBuilder;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by Michał Stobiński on 2016-01-18.
 */
public class JsonService extends AsyncTask<String, Void, JSONObject> {

    private Object asynchObject;    // zaktualizowanie asynchroniczne obiektu
    private Map<String, String> mJson;

    public synchronized void get(String url, Object asynchObject) {
        this.execute(url, "GET");

        this.asynchObject = asynchObject;
    }

    public synchronized void post(String url, Map<String, String> mJson) {
        this.execute(url, "POST");

        this.mJson = mJson;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected JSONObject doInBackground(String... params) {
        String uri = params[0];

        if(params[1].equals("GET")) {
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;

            try {
                URL url = new URL(uri);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                StringBuilder sb = new StringBuilder();
                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                String json;
                while ((json = bufferedReader.readLine()) != null) {
                    sb.append(json + "\n");
                }
                return new JSONObject(sb.toString().trim());

            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                httpURLConnection.disconnect();
                try {
                    if (bufferedReader != null)
                        bufferedReader.close();
                } catch (IOException e) {
                    Log.i("Exception", "Nie udało się nawiązać połączenia");
                }
            }
        } else if(params[1].equals("POST")) {

            String json = new GsonBuilder().create().toJson(mJson, Map.class);
            HttpPost httpPost = new HttpPost(uri);
            try {
                httpPost.setEntity(new StringEntity(json));
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                new DefaultHttpClient().execute(httpPost);
            } catch (IOException e) {
                Log.i("Exception", "Nie udało się nawiązać połączenia");
            }

        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        super.onPostExecute(json);

        // TODO Odebranie Jsona i przypisanie go gdzieś
    }
}
