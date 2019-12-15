package hello.service;

import hello.dataBase.DollarRate;
import hello.dataBase.DollarRateCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

@Component
public class RBKService {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private DollarRateCrudRepository dollarRateCrudRepository;

    @Transactional
    public void setDollarRateToDB(Double rate) {
        dollarRateCrudRepository.save(new DollarRate(rate, Calendar.getInstance().getTime()));
    }

    @Transactional
    public Optional<Double> getDollarRateFromDB(Date date){
        Optional<DollarRate> dollarRate = dollarRateCrudRepository.findByDate(DollarRate.getDateFormat(date));
        return dollarRate.map(DollarRate::getRate);
    }

    public double getMaxMonthDollarRate() {
        Optional<Double> maxDollarRate = getDollarRateFromDB(Calendar.getInstance().getTime());
        if (maxDollarRate.isPresent()) {
            return maxDollarRate.get();
        }
        Double dollarRate = getMax(parseDollarRateData(getDollarRateData()));
        setDollarRateToDB(dollarRate);
        return dollarRate;
    }

    public  ArrayList<Double> getRates() {
        return parseDollarRateData(getDollarRateData());
    }

    public Double getMax(ArrayList<Double> arr) {
        double max = Double.MIN_VALUE;
        for (Double i : arr) {
            max = Math.max(max, i);
        }
        return max;
    }

    public ArrayList<String> getDollarRateData(){
        String url = "http://export.rbc.ru/free/selt.0/free.fcgi?period=DAILY&tickers="+
                "USD000000TOD&separator=TAB&data_format=BROWSER&lastdays=30";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return new ArrayList<>(Arrays.asList(response.getBody().split("\\s+")));
    }


    public ArrayList<Double> parseDollarRateData(ArrayList<String> sArr){
        ArrayList<Double> res = new ArrayList<>();
        int numOfColumn = 8;
        for (int i = 0; i < sArr.size(); i++){
            if( i % numOfColumn == numOfColumn - 1){
                res.add(Double.parseDouble(sArr.get(i)));
            }
        }
        return res;
    }
}
