package hello.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.weather.WeatherInstance;
import hello.weather.WeatherTable;
import hello.weather.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DarkSpyService {

    private static final String BASE_URL = "https://api.darksky.net/forecast/";
    private static final String SECRET_PART = "3ce5ca6c6c64befaa69dd9cf05b939db";

    @Autowired
    private RestTemplate restTemplate;

    public String getWeatherData(String strTime){
        String url = BASE_URL + SECRET_PART + "/37.8267,-122.4233," + strTime;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new IllegalStateException();
        }
        return response.getBody();
    }

    public List<Double> getWeather() {
        List<Double> data = new ArrayList<>();
        for (long i = 0; i < 30; i++) {
            WeatherResponse weatherResponse = parseWeatherData(getWeatherData(
                    String.valueOf((System.currentTimeMillis() / 1000L - i * 86400L))));
            WeatherTable weatherTable = weatherResponse.daily.data.get(0);
            data.add(weatherTable.temperatureHigh);
        }
        return data;
    }

    public WeatherResponse parseWeatherData(String str) {
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherResponse weatherResponse = null;
        try {
            weatherResponse = objectMapper.readValue(str, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherResponse;
    }

    public WeatherInstance getCurrentWeather() {
        String str = getWeatherData(String.valueOf(System.currentTimeMillis() / 1000L));
        return parseWeatherData(str).currently;
    }
}
