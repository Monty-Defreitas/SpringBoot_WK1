package com.promineotech.jeep.controller;

import com.promineotech.jeep.Entity.Order;
import com.promineotech.jeep.Entity.OrderRequest;
import com.promineotech.jeep.Service.JeepOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
@RestController
@Slf4j
public class DefaultJeepOrderController implements JeepOrderController {


@Autowired
    JeepOrderService jeepOrderService;


        @Override
        public Order createOrder(OrderRequest orderRequest) {
            log.debug("Order={}", orderRequest);
            return jeepOrderService.createOrder(orderRequest) ;
        }

    }

