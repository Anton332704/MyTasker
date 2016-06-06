package iipo.models;

/**
 * Created by user on 05.06.2016.
 */
public class WeatherInfo {

    private double temp;
    private double presure;
    private double humidity;
    private String info;
    private String icon;

    public WeatherInfo(double temp, double presure, double humidity, String info, String icon) {
        this.temp = temp;
        this.presure = presure;
        this.humidity = humidity;
        this.info = info;
        this.icon = icon;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPresure() {
        return presure;
    }

    public void setPresure(double presure) {
        this.presure = presure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
