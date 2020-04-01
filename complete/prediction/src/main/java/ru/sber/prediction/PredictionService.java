package ru.sber.prediction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public abstract class PredictionService {

    @Value("${app.address.rbk}")
    String rbkUrl;

    @Value("${app.address.darksky}")
    String darkskyUrl;

    public static void main(String[] args) {
        SpringApplication.run(PredictionService.class, args);
    }

    public Double predictWithMNK() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> darkskyResponse = restTemplate.getForEntity(darkskyUrl, String.class);
        ResponseEntity<String> rbkResponse = restTemplate.getForEntity(rbkUrl, String.class);

        String x = rbkResponse.getBody();
        String y = darkskyResponse.getBody();

        String[] xStr = x.split(": ");
        String[] yStr = y.split(": ");

        Double xDoub = Double.valueOf(xStr[1]);
        Double yDoub = Double.valueOf(yStr[1]);

        return xDoub + yDoub;
    }
}
