package com.promineotech.jeep.Service;

import com.promineotech.jeep.Entity.Order;
import com.promineotech.jeep.Entity.OrderRequest;

public interface JeepOrderService {
    Order createOrder(OrderRequest orderRequest);
}
