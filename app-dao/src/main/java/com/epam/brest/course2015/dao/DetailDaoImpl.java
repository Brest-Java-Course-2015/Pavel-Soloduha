package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.Detail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.epam.brest.course2015.domain.Detail.DetailFields.*;

/**
 * Created by pavel on 11/7/15.
 */
public class DetailDaoImpl implements DetailDao{

    @Value("${detail.select}")
    private String detailSelectSql;

    @Value("${detail.deleteDetail}")
    private String detailDeleteSql;

    @Value("${detail.updateDetail}")
    private String detailUpdateSql;

    @Value("${detail.insertDetail}")
    private String detailInsertSql;

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DetailDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private MapSqlParameterSource getParametersMap(Detail detail) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(DETAIL_ID.getValue(), detail.getDetailId());
        parameterSource.addValue(DETAIL_NAME.getValue(), detail.getDetailName());
        return parameterSource;
    }

    private class UserRowMapper implements RowMapper<Detail> {

        @Override
        public Detail mapRow(ResultSet resultSet, int i) throws SQLException {
            Detail detail = new Detail(resultSet.getInt(DETAIL_ID.getValue()),
                    resultSet.getString(DETAIL_NAME.getValue()));
            return detail;
        }
    }

    @Override
    public List<Detail> getAllDetails() {
        return jdbcTemplate.query(detailSelectSql, new UserRowMapper());
    }

    @Override
    public void deleteDetail(Integer detailId) {
        jdbcTemplate.update(detailDeleteSql, new Object[]{detailId});
    }

    @Override
    public void updateDetail(Detail detail) {
        jdbcTemplate.update(detailUpdateSql, new Object[]{detail.getDetailName(), detail.getDetailId()});
    }

    @Override
    public Integer addDetail(Detail detail) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(detailInsertSql, getParametersMap(detail), keyHolder);
        return keyHolder.getKey().intValue();
    }

}