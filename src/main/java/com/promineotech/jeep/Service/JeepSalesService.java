package com.promineotech.jeep.Service;

import com.promineotech.jeep.Entity.Jeep;
import com.promineotech.jeep.Entity.JeepModel;

import java.util.List;

public interface JeepSalesService {
    List<Jeep> fetchJeeps(JeepModel model, String trim);
}
