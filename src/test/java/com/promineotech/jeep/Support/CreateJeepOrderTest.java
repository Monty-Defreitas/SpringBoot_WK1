package com.promineotech.jeep.Support;

import com.promineotech.jeep.Entity.JeepModel;
import com.promineotech.jeep.Entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
        "classpath:migrations/V1.0__Jeep_Schema.sql",
        "classpath:migrations/V1.1__Jeep_Data.sql"},
        config = @SqlConfig(encoding = "utf-8"))
@Slf4j
public class CreateJeepOrderTest extends BaseTest{


    @Test
    void testCreateOrderReturnsSuccess201(){
        HttpHeaders httpHeaders = new HttpHeaders();
        String body = createOrderBody();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> bodyEntity = new HttpEntity<>(body,httpHeaders);

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
