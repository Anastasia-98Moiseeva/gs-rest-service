package hello.controller;

import hello.service.DarkSpyService;
import hello.service.PredictionService;
import hello.service.RBKService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private RBKService service;

    @Autowired
    private DarkSpyService darkSpyService;

    @Autowired
    private PredictionService predictionService;

    @RequestMapping(value = "/rbk", method = RequestMethod.GET)
    public String getMaxDollarRate() {
        return "Max dollar rate: " + service.getMaxMonthDollarRate();
    }

    /*@RequestMapping(value = "/rbk", method = RequestMethod.GET)
    public String rbk() {
        List<Double> rateHistory = service.getRates();
        return "Dollar rate month ago: " + rateHistory.get(rateHistory.size() - 1);
    }*/

    @RequestMapping(value = "/darkspy", method = RequestMethod.GET)
    public String weather() {
        return "Current temperature: " + darkSpyService.getCurrentWeather().temperature;
    }

    @RequestMapping(value = "/prediction", method = RequestMethod.GET)
    public Double prediction(){
        return predictionService.predictWithMNK();
    }
}
