package iipo.asynctask;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
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
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 06.11.2015.
 */
public class AuthorizationTask extends AsyncTask<String, Integer, String> {
    Context cont;
    ArrayList<String> arList;
    View v;
    ListView listView;
    String id;
    String hash;
    JSONArray jsonAr;
    private final static String EXIST = "exist";
    private final static String NOT_EXIST = "not_exist";

    public AuthorizationTask(Context cont, ArrayList<String> arList) {
        this.cont = cont;
        this.arList = arList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        arList = new ArrayList<String>();
    }

    @Override
    protected String doInBackground(String[] params) {
        hash = "";
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://mobile32.esy.es/auth.php");
        List<NameValuePair> login = new ArrayList<NameValuePair>();
        login.add(new BasicNameValuePair("login", params[0]));
        login.add(new BasicNameValuePair("password", params[1]));
        String str = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(login));
//            httpPost.addHeader("login", "1");
//            httpPost.addHeader("passw", "2");
            HttpResponse httpResponse = httpClient.execute(httpPost);
//            Header[] headers = httpResponse.getAllHeaders();
//            for (Header header : headers) {
//                if(header.getName() == "isExist")
//                {
//                    exist = header.getValue();
//                    if(exist == "false")
//                    {
//                        return NOT_EXIST;
//                    }
//                }
//                if(header.getName() == "hash")
//                {
//                    //hash = header.getValue();
//                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(cont);
//                    SharedPreferences.Editor editor = sp.edit();
//                    editor.putString("hash", header.getValue().toString());
//                }
//
//            }
            InputStream iStream = httpResponse.getEntity().getContent();
            InputStreamReader iStreamReader = new InputStreamReader(iStream);
            BufferedReader bufReader = new BufferedReader(iStreamReader);
            StringBuilder strBuild = new StringBuilder();
            while ((str = bufReader.readLine()) != null)
            {
                strBuild.append(str);
                //arList.add(str);
            }
            bufReader.close();
            str = strBuild.toString();

            jsonAr = new JSONArray(str);
            JSONObject jsonObject;

            for (int i = 0; i < jsonAr.length(); i++)
            {
                jsonObject = (JSONObject)jsonAr.get(i);
                id = jsonObject.getString("id").toString();
                hash = jsonObject.getString("hash").toString();
                //arrayList.add(mystr);
            }
            if(id == "0")
            {
                return NOT_EXIST;
            }
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(cont);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("id", id);
            editor.putString("hash", hash);
//            if(hash == "")
//            {
//                return "12345";
//            }
            return EXIST;
        }
        catch (ClientProtocolException e) {
            e.printStackTrace();
            return "1";
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "1";
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
            return "1";
        }
            catch(IOException e){
                e.printStackTrace();
            } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
        }

        @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
            //Toast.makeText(cont , str, Toast.LENGTH_LONG).show();

    }
}
