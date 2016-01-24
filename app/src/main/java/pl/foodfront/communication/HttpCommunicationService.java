package pl.foodfront.communication;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.GsonBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

import pl.foodfront.ICallback;

/**
 * Created by Michał Stobiński on 2016-01-18.
 */
class HttpCommunicationService extends AsyncTask<String, Void, String> {

    private Map<String, String> mJson;
    private ICallback callback;
    private Responser responser;

    HttpCommunicationService(ICallback callback) {
        this.callback = callback;
        this.responser = new Responser(callback);
    }

    protected synchronized void invoke(String url, Map<String, String> mJson) {
        this.execute(url);

        this.mJson = mJson;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected String doInBackground(String... params) {
        String uri = params[0];
        String response = "";

        String json = new GsonBuilder().create().toJson(mJson, Map.class);
        HttpPost httpPost = new HttpPost(uri);
        try {
            StringEntity entity = new StringEntity(json);
            entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httpPost.setEntity(entity);
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);
            Log.i("Json", response);
        } catch (IOException e) {
            Log.i("Exception", "Nie udało się nawiązać połączenia");
        }

        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);

        // Oddelegowanie odpowiedzi do osobnej klasy
        responser.answerMe(mJson, response);
    }
}
