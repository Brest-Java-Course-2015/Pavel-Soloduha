package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.Detail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOGGER = LogManager.getLogger();

    @Value("${detail.select}")
    private String detailSelectSql;

    @Value("${detail.deleteDetail}")
    private String detailDeleteSql;

    @Value("${detail.updateDetail}")
    private String detailUpdateSql;

    @Value("${detail.insertDetail}")
    private String detailInsertSql;

    @Value("${detail.selectDetailById}")
    private String detailSelectByIdSql;

    @Value("${detail.hasRef}")
    private String detailHasRefSql;

    @Value("${detail.presId}")
    private String detailIdPresSql;

    @Value("${detail.presName}")
    private String detailNamePresSql;

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

    private class DetailRowMapper implements RowMapper<Detail> {

        @Override
        public Detail mapRow(ResultSet resultSet, int i) throws SQLException {
            Detail detail = new Detail(resultSet.getInt(DETAIL_ID.getValue()),
                    resultSet.getString(DETAIL_NAME.getValue()));
            return detail;
        }
    }

    @Override
    public List<Detail> getAllDetails() {
        LOGGER.debug("getAllDetails()");
        return jdbcTemplate.query(detailSelectSql, new DetailRowMapper());
    }

    @Override
    public void deleteDetail(Integer detailId) {
        LOGGER.debug("deleteDetail(): detailId = {}", detailId);
        jdbcTemplate.update(detailDeleteSql, new Object[]{detailId});
    }

    @Override
    public void updateDetail(Detail detail) {
        LOGGER.debug("updateDetail(): detailId = {}, detailName = {}"
                , detail.getDetailId(), detail.getDetailName());
        jdbcTemplate.update(detailUpdateSql, new Object[]{detail.getDetailName(), detail.getDetailId()});
    }

    @Override
    public Integer addDetail(Detail detail) {
        LOGGER.debug("addDetail(): detailName = {}"
                , detail.getDetailName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(detailInsertSql, getParametersMap(detail), keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Detail getDetailById(Integer detailId) {
        LOGGER.debug("getDetailById(): detailId = {}", detailId);
        return jdbcTemplate.queryForObject(detailSelectByIdSql, new Object[]{detailId}, new DetailRowMapper());
    }

    @Override
    public Boolean hasReferences(Integer detailId) {
        LOGGER.debug("hasReferences(): detailId = {}", detailId);
        return jdbcTemplate.queryForObject(detailHasRefSql, new Object[]{detailId}, Integer.class) > 0 ? true : false;
    }

    @Override
    public Boolean isIdPresentInTable(Integer detailId) {
        LOGGER.debug("isIdPresentInTable(): detailId = {}", detailId);
        return jdbcTemplate.queryForObject(detailIdPresSql, new Object[]{detailId}, Integer.class) > 0 ? true : false;
    }

    @Override
    public Boolean isNamePresentInTable(String detailName) {
        LOGGER.debug("isNamePresentInTable(): detailName = {}", detailName);
        return jdbcTemplate.queryForObject(detailNamePresSql, new Object[]{detailName}, Integer.class) > 0 ? true : false;
    }
}