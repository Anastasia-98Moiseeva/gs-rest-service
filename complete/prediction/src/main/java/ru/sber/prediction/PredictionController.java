package ru.sber.prediction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictionController {

    @Autowired
    private PredictionService predictionService;

    @RequestMapping(value = "/prediction", method = RequestMethod.GET)
    public Double prediction(){
        return predictionService.predictWithMNK();
        //return Double.valueOf(0);
    }
}
