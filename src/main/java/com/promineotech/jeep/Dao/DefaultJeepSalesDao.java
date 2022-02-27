package com.promineotech.jeep.Dao;

import com.promineotech.jeep.Entity.Jeep;
import com.promineotech.jeep.Entity.JeepModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DefaultJeepSalesDao implements JeepSalesDao{

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Jeep> fetchJeeps(JeepModel model, String trim) {
        log.info("model {} and trim {} have reached the DAO layer", model,trim);
        String sql = ""
                + "SELECT * "
                + "FROM models "
                + "WHERE model_id = :model_id " +
                "AND trim_level = :trim_level";
        Map<String, Object> params = new HashMap<>();
        params.put("model_id", model.toString());
        params.put("trim_level", trim);
        return jdbcTemplate.query(sql, params, (rs, rowNum) -> Jeep.builder()
                .basePrice(new BigDecimal(rs.getString("base_price")))
                .modelId(JeepModel.valueOf(rs.getString("model_id")))
                .modelPK(rs.getLong("model_pk"))
                .numDoors(rs.getInt("num_doors"))
                .trimLevel(rs.getString("trim_level"))
                .wheelSize(rs.getInt("wheel_size"))
                .build());
    }
}
