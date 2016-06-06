package iipo.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker.R;
import iipo.asynctask.GetWeatherData;
import iipo.models.WeatherInfo;
import interfaces.OnTaskCompleteListener;

/**
 * Created by user on 05.06.2016.
 */
public class WeatherFragment extends Fragment implements OnTaskCompleteListener {

    private ImageView imageView;
    private TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_layout, container, false);
        imageView = (ImageView) view.findViewById(R.id.iconWeather);
        textView = (TextView) view.findViewById(R.id.textViewWeather);
        GetWeatherData weatherData = new GetWeatherData(this);
        weatherData.execute();
        return view;
    }

    @Override
    public void onComplete(Object data) {
        if(data != null){
            final WeatherInfo info = (WeatherInfo) data;
            textView.setText(info.getInfo() + " " + info.getTemp());
            final String icon = info.getIcon();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = url = new URL("http://openweathermap.org/img/w/" + icon + ".png");
                        final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        imageView.post(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bmp);
                            }
                        });
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
}