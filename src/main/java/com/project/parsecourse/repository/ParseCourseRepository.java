package com.project.parsecourse.repository;

import com.project.parsecourse.enums.CurrencyEnum;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ParseCourseRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ParseCourseRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(String tableName, CurrencyEnum currency, Timestamp timestamp, BigInteger cost) {
        Map<String, Object> parameters = new HashMap<String, Object>() {{
            put("time_stamp", timestamp);
            put("currency", currency.name());
            put("cost", cost);
        }};
        jdbcTemplate.update(String.format("INSERT INTO %s VALUES(:time_stamp, :currency, :cost)", tableName), parameters);
    }
}
