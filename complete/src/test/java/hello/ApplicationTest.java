package hello;

import hello.service.RBKService;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationTest {

    private RBKService serviceMock = mock(RBKService.class);

    ArrayList<String> strData = new ArrayList<>(Arrays.asList(
            "USD000000TOD\t2019-11-06\t63.5025\t63.87\t63.48\t63.835\t778842000\t63.6625\n",
            "USD000000TOD\t2019-11-07\t63.89\t63.91\t63.605\t63.6475\t627763000\t63.7205\n",
            "USD000000TOD\t2019-11-08\t63.65\t63.895\t63.5575\t63.7975\t800232000\t63.7626\n",
            "USD000000TOD\t2019-11-12\t63.8275\t64.09\t63.745\t64.03\t767986000\t63.9449\n",
            "USD000000TOD\t2019-11-13\t64.225\t64.4575\t64.1225\t64.3825\t810906000\t64.3069\n"));

    ArrayList<Double> data = new ArrayList<>(Arrays.asList(
            63.5025, 63.87, 63.48, 63.835,
            63.89, 63.91, 63.605, 63.6475,
            63.65, 63.895, 63.5575, 63.7975,
            63.8275, 64.09, 63.745, 64.03,
            64.225, 64.4575, 64.1225, 64.3825));

    @Test
    public void testGetMax() {
        RBKService service = new RBKService();
        when(serviceMock.parseData((ArrayList<String>) Mockito.<String>anyCollection())).thenReturn(data);
        assertEquals(64.4575, service.getMax(serviceMock.parseData(strData)), 0.1);
    }
}