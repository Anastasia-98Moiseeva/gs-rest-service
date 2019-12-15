package hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PredictionService {

    @Autowired
    private RBKService rbkService;
    @Autowired
    private DarkSpyService darkSpyService;

    public double predictWithMNK() {
        List<Double> y = rbkService.getRates();
        Collections.reverse(y);
        List<Double> x = darkSpyService.getWeather().subList(0, y.size());
        double sumX = 0.0, sumY = 0.0, sumXY = 0.0, sumXSq = 0.0;
        for (Double i: x) {
            sumX += i;
            sumXSq += i * i;
        }
        for (Double j: y) {
            sumY += j;
        }
        assert(x.size() == y.size());
        for (int i = 0; i < y.size(); i++) {
            sumXY += x.get(i) * y.get(i);
        }
        double b = (x.size() * sumXY - sumX * sumY)/(x.size() * sumXSq - sumX * sumX);
        double a = (sumY - b * sumX)/x.size();
        double t = darkSpyService.getCurrentWeather().temperature;
        return a + b * t;
    }
}
