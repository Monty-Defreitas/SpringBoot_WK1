package com.promineotech.jeep.Dao;

import com.promineotech.jeep.Entity.Jeep;
import com.promineotech.jeep.Entity.JeepModel;

import java.util.List;

public interface JeepSalesDao {

    List<Jeep> fetchJeeps(JeepModel model, String trim);
}
