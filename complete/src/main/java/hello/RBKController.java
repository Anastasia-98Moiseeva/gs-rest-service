package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.web.bind.annotation.RestController
public class RBKController {

    private RBKService service = new RBKService();

    @RequestMapping(value = "/rbk", method = RequestMethod.GET)
    public Double getMaxDollarRate() {
        return service.getMaxMonthDollarRate();
    }

}
