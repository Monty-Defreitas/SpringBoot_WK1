package com.promineotech.jeep.Service;

import com.promineotech.jeep.Dao.JeepSalesDao;
import com.promineotech.jeep.Entity.Jeep;
import com.promineotech.jeep.Entity.JeepModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DefaultJeepSalesService implements JeepSalesService{

@Autowired
    private JeepSalesDao jeepSalesDao;

    @Override
    public List<Jeep> fetchJeeps(JeepModel model, String trim) {
        log.info("Parameters: model ={} and trim = {}, made it to the service method", model,trim);
        return jeepSalesDao.fetchJeeps(model, trim);
    }
}
