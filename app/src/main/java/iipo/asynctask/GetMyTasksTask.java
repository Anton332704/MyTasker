package iipo.asynctask;

import android.os.AsyncTask;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 13.11.2015.
 */
public class GetMyTasksTask extends AsyncTask<String, Integer, ArrayList<String>> {
    JSONArray jsonAr;
    @Override
    protected ArrayList<String> doInBackground(String... params) {
        ArrayList<String> arrayList = new ArrayList<String>();
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://mobile32.esy.es");
        NameValuePair hash = new BasicNameValuePair("hash", params[0]);
        NameValuePair id = new BasicNameValuePair("id", params[1]);
        List<NameValuePair> listHash = new ArrayList<NameValuePair>();
        listHash.add(hash);
        listHash.add(id);
        String str = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(listHash));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            InputStream iStream = httpResponse.getEntity().getContent();
            InputStreamReader streamReader = new InputStreamReader(iStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while((str = bufferedReader.readLine())!=null)
            {
                stringBuilder.append(str);
            }
            str = stringBuilder.toString();
            jsonAr = new JSONArray(str);
            JSONObject jsonObject;
            for (int i = 0; i < jsonAr.length(); i++)
            {
                jsonObject = (JSONObject)jsonAr.get(i);
                String mystr = jsonObject.getString("login").toString();
                arrayList.add(mystr);
            }
            arrayList.add("qweetyy");
            return arrayList;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        arrayList.add("First Task");
        arrayList.add("12345");
        arrayList.add("12dfd");
        return arrayList;
    }
}
