package hello.weather;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherTable {
    public double temperatureHigh;
    public int time;
}
