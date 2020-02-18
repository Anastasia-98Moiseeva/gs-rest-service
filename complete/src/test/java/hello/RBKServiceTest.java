package hello;

import hello.service.RBKService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RBKServiceTest {

    @Mock
    private RestTemplate restTemplateMock;

    @Mock
    private ResponseEntity<String> responseEntityMock;

    @InjectMocks
    private RBKService service = new RBKService();

    @Test
    public void getMax() {
        String responseBody = "63.5025,63.87,63.48,63.835";
        when(responseEntityMock.getBody()).thenReturn(responseBody);
        when(restTemplateMock.getForEntity(anyString(), eq(String.class))).thenReturn(responseEntityMock);
        ArrayList<String> resArr = service.getDollarRateData();
        String resStr = resArr.get(0);
        for (int i = 1; i < resArr.size(); i++){
            resStr +=  " " + resArr.get(i);
        }
        assertEquals(responseBody, resStr);
    }
}
