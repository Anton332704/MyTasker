package iipo.asynctask;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import iipo.models.WeatherInfo;
import interfaces.OnTaskCompleteListener;

/**
 * Created by user on 05.06.2016.
 */
public class GetWeatherData extends AsyncTask<Void, Void, JSONObject> {

    public final static String PATH_TO_WEATHER = "http://api.openweathermap.org/data/2.5/forecast/city?id=571476&APPID=e0c1e0effc4fa9f43cce8eb0f20777dc";
    private OnTaskCompleteListener listener;

    public GetWeatherData(OnTaskCompleteListener listener) {
        this.listener = listener;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        InputStream is;
        JSONObject result = null;
        try {
            HttpURLConnection con = (HttpURLConnection) (new URL(PATH_TO_WEATHER)).openConnection();

            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.connect();
            int response = con.getResponseCode();
            if (response == 200) {
                is = con.getInputStream();
            } else {
                is = con.getErrorStream();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            result = new JSONObject(sb.toString());
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        if (jsonObject != null){
            try {
                JSONArray array = jsonObject.getJSONArray("list");
                JSONObject jsonObjectBig = array.getJSONObject(1);
                JSONObject jsonObjectMain = jsonObjectBig.getJSONObject("main");
                double temp = jsonObjectMain.getDouble("temp");
                double pressure = jsonObjectMain.getDouble("pressure");
                double humidity = jsonObjectMain.getDouble("humidity");
                JSONArray jsonArrayWeather = jsonObjectBig.getJSONArray("weather");
                JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                String info = jsonObjectWeather.getString("description");
                String icon  = jsonObjectWeather.getString("icon");
                WeatherInfo weatherInfo = new WeatherInfo(temp, pressure, humidity, info, icon);
                listener.onComplete(weatherInfo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
