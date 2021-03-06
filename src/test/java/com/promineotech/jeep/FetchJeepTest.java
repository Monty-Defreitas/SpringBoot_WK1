package com.promineotech.jeep;


import com.promineotech.jeep.Entity.Order;
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
import org.springframework.http.*;
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

    @Test
    void testCreateOrderReturnsSuccess201(){
        HttpHeaders httpHeaders = new HttpHeaders();
        String body = createOrderBody();
        log.debug("body = {}", body);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        log.debug("httpHeader = {}", httpHeaders);
        HttpEntity<String> bodyEntity = new HttpEntity<>(body,httpHeaders);
            log.debug("bodyEntity = {}", bodyEntity);
        ResponseEntity<Order> response = getRestTemplate().exchange(getBaseUriForOrders(),
                HttpMethod.POST, bodyEntity, Order.class);
        log.debug("response = {}", response);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        assertThat(response.getBody()).isNotNull();

        Order order = response.getBody();

        assertThat(order.getCustomer().getCustomerId()).isEqualTo("MORISON_LINA");
        assertThat(order.getModel().getModelId()).isEqualTo(JeepModel.WRANGLER);
        assertThat(order.getModel().getTrimLevel()).isEqualTo("Sport Altitude");
        assertThat(order.getModel().getNumDoors()).isEqualTo(4);
        assertThat(order.getColor().getColorId()).isEqualTo("EXT_NACHO");
        assertThat(order.getEngine().getEngineId()).isEqualTo("2_0_TURBO");
        assertThat(order.getTire().getTireId()).isEqualTo("35_TOYO");
        assertThat(order.getOptions()).hasSize(6);
    }
    public String createOrderBody() {
        return "{\n" +
                "  \"customer\":\"MORISON_LINA\",\n " +
                " \"model\":\"WRANGLER\",\n" +
                "  \"trim\":\"Sport Altitude\",\n" +
                "  \"doors\":4,\n" +
                "  \"color\":\"EXT_NACHO\",\n" +
                "  \"engine\":\"2_0_TURBO\",\n" +
                "  \"tire\":\"35_TOYO\",\n" +
                "  \"options\":[\n" +
                "  \"DOOR_QUAD_4\",\n" +
                "  \"EXT_AEV_LIFT\",\n" +
                "  \"EXT_WARN_WINCH\",\n" +
                "  \"EXT_WARN_BUMPER_FRONT\",\n" +
                "\"EXT_WARN_BUMPER_REAR\",\n" +
                " \"EXT_ARB_COMPRESSOR\"\n" +
                "  ]\n" +
                "}";

    }

}
