package pl.foodfront.communication;

import android.os.AsyncTask;
import android.util.Log;

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

import pl.foodfront.ICallback;
import pl.foodfront.serialized.iSend;

/**
 * Created by Michał Stobiński on 2016-01-18.
 */
class HttpCommunicationService extends AsyncTask<String, Void, String> {

    private iSend send;
    private ICallback callback;
    private Responser responser;

    protected void connectActivity(ICallback callback) {
        this.callback = callback;
        this.responser = new Responser(callback);
    }

    protected void disconnectActivity() {
        this.callback = null;
    }

    protected synchronized void invoke(String url, iSend send) {
        this.execute(url);

        this.send = send;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected String doInBackground(String... params) {
        String uri = params[0];
        String response = "";

        String json = JsonConverter.serializePost(send);
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
        if(callback != null) {  // sprawdzenie czy aktywność nie została usunięta
            responser.answerMe(send, response);
        }
    }
}
