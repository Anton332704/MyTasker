package iipo.asynctask;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by user on 20.10.2015.
 */
public class ServerTask extends AsyncTask<String, Integer, ArrayList<String>> {
    Context asyncContext;
    JSONArray jsonArray;
    ArrayList<String> arrayList;


    public ServerTask(Context asyncContext) {
        this.asyncContext = asyncContext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        arrayList = new ArrayList<String>();
    }

    @Override
    protected ArrayList<String> doInBackground(String ... strings) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONArray jsonAr;
        String str = "hey";
        HttpGet httpGet = new HttpGet("http://mobile32.esy.es");
        try {
            HttpResponse httpResp = httpClient.execute(httpGet);
            InputStream iStream = httpResp.getEntity().getContent();
            InputStreamReader iStreamReader = new InputStreamReader(iStream);
            BufferedReader bufReader = new BufferedReader(iStreamReader);
            StringBuilder strBuild = new StringBuilder();
            while ((str = bufReader.readLine()) != null) {
                strBuild.append(str);
            }
            bufReader.close();
            str = strBuild.toString();


            jsonAr = new JSONArray(str);
            JSONObject jsonObject;

            for (int i = 0; i < jsonAr.length(); i++)
            {
                jsonObject = (JSONObject)jsonAr.get(i);
                String mystr = jsonObject.getString("login").toString();
                arrayList.add(mystr);
            }
            return arrayList;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute (ArrayList<String> str){
        super.onPostExecute(str);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i< str.size(); i++)
        {
            builder.append(str.get(i));
        }
        String superString = builder.toString();

        Toast.makeText(asyncContext, superString, Toast.LENGTH_LONG).show();
    }
}

