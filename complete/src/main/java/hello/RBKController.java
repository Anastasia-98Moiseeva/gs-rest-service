package hello;

import hello.service.RBKService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.web.bind.annotation.RestController
public class RBKController {

    @Autowired
    private RBKService service;

    @RequestMapping(value = "/rbk", method = RequestMethod.GET)
    public Double getMaxDollarRate() {
        return service.getMaxMonthDollarRate();
    }

}
