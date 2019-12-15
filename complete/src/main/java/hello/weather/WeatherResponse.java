package hello.weather;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    public WeatherData daily;
    public WeatherInstance currently;
}
