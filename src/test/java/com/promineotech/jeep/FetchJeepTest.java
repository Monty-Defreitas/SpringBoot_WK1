package com.promineotech.jeep;


import com.promineotech.jeep.Support.FetchJeepTestSupport;
import com.promineotech.jeep.Entity.Jeep;
import com.promineotech.jeep.Entity.JeepModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
        "classpath:migrations/V1.0__Jeep_Schema.sql",
        "classpath:migrations/V1.1__Jeep_Data.sql"},
        config = @SqlConfig(encoding = "utf-8"))
@Slf4j
class FetchJeepTest extends FetchJeepTestSupport {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int serverPort;

    @Test
    void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {

           JeepModel model = JeepModel.WRANGLER;
                  String trim = "Sport";
                  String uri = String.format("http://localhost:%d/jeeps?model=%s&trim=%s", serverPort, model, trim);

                  ResponseEntity<List<Jeep>> response = restTemplate.exchange(uri, HttpMethod.GET,null, new ParameterizedTypeReference<>() {});

                  assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

       // Values to assert that correct list of jeeps is returned.
        List<Jeep> actual = response.getBody();
        List<Jeep> expected = buildExpected();

        assertThat(actual).isEqualTo(expected);
    }
}
