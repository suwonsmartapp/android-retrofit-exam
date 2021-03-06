package com.jsoh.retrofitexam;

/**
 * Created by junsuk on 2017. 2. 4..
 */

public class Weather {
    private String country;
    private String weather;
    private String temperature;

    public Weather(String country, String temperature, String weather) {
        this.country = country;
        this.temperature = temperature;
        this.weather = weather;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Weather{");
        sb.append("country='").append(country).append('\'');
        sb.append(", weather='").append(weather).append('\'');
        sb.append(", temperature='").append(temperature).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
