package ru.sber.temperature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DarkSkyController {

    @Autowired
    private DarkSkyService darkSkyService;

    @RequestMapping(value = "/darksky", method = RequestMethod.GET)
    public String weather() {
        return "Current temperature: " + darkSkyService.getCurrentWeather().temperature;
    }
}
