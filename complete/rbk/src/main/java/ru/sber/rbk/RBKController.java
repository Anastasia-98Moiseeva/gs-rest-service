package ru.sber.rbk;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RBKController {

    @Autowired
    private RBKService service;

    @RequestMapping(value = "/rbk", method = RequestMethod.GET)
    public String getMaxDollarRate() {
        //return "Max dollar rate: " + service.getMaxMonthDollarRate();
        return "Max dollar rate: 80.00";
    }

    /*@RequestMapping(value = "/rbk", method = RequestMethod.GET)
    public String rbk() {
        List<Double> rateHistory = service.getRates();
        return "Dollar rate month ago: " + rateHistory.get(rateHistory.size() - 1);
    }*/

}
