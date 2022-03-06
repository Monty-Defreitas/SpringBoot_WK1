package com.promineotech.jeep.Support;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

public class BaseTest {

    @LocalServerPort
    private int serverPort;



    @Getter //Lombok annotation
    @Autowired
    private TestRestTemplate restTemplate;


    protected String getBaseUriForOrders(){
        return String.format("http://localhost:%d/orders", serverPort);
    }


    protected String getBaseUriForJeeps(){
        return String.format("http://localhost:%d/jeeps", serverPort);
    }

    protected TestRestTemplate getRestTemplate() {
        return restTemplate;
    }
}
