package hello;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

public class RBKService {

    public double getMaxMonthDollarRate() {
        return getMax(parseData(getDataFromServer()));
    }

    public Double getMax(ArrayList<Double> arr) {
        double max = Double.MIN_VALUE;
        for (Double i : arr) {
            max = Math.max(max, i);
        }
        return max;
    }

    public ArrayList<String> getDataFromServer(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://export.rbc.ru/free/selt.0/free.fcgi?period=DAILY&tickers="+
                "USD000000TOD&separator=TAB&data_format=BROWSER&lastdays=30";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return new ArrayList<>(Arrays.asList(response.getBody().split("\\s+")));
    }


    public ArrayList<Double> parseData(ArrayList<String> sArr){
        ArrayList<Double> res = new ArrayList<>();
        int column1 = 2, column2 = 6;
        for (int i = 0; i < sArr.size(); i++){
            for (int j = column1; j < column2; j++){
                res.add(Double.parseDouble(sArr.get(j)));
            }
        }
        return res;
    }
}
