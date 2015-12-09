package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.DocBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.epam.brest.course2015.domain.DocBody.DocBodyFields.*;

/**
 * Created by pavel on 11/10/15.
 */
public class DocBodyDaoImpl implements DocBodyDao {

    private static final Logger LOGGER = LogManager.getLogger();

    @Value("${docbody.selectDocBodyById}")
    private String docBodySelectByIdSql;

    @Value("${docbody.insertDocBody}")
    private String docBodyInsertSql;

    @Value("${docbody.deleteDocBodyById}")
    private String docBodyDeleteByIdSql;

    @Value("${docbody.selectIncomeDetails}")
    private String incomeDetailsSelectSql;

    @Value("${docbody.detailPresId}")
    private String detailIdPresSql;

    @Value("${docbody.selectOutputDetails}")
    private String outputDetailsSelectSql;

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DocBodyDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private MapSqlParameterSource getParametersMap(DocBody docBody) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(DOCUMENT_ID.getValue(), docBody.getDocumentId());
        parameterSource.addValue(DETAIL_ID.getValue(), docBody.getDetailId());
        parameterSource.addValue(DETAIL_NAME.getValue(), docBody.getDetailName());
        parameterSource.addValue(DETAIL_COUNT.getValue(), docBody.getDetailCount());
        return parameterSource;
    }

    private class DocBodyRowMapper implements RowMapper<DocBody> {

        @Override
        public DocBody mapRow(ResultSet resultSet, int i) throws SQLException {
            DocBody docBody = new DocBody(resultSet.getInt(DOCUMENT_ID.getValue()),
                    resultSet.getInt(DETAIL_ID.getValue()),
                    resultSet.getString(DETAIL_NAME.getValue()),
                    resultSet.getInt(DETAIL_COUNT.getValue()));
            return docBody;
        }
    }

    @Override
    public List<DocBody> getDocBodyByDocId(Integer documentId) {
        LOGGER.debug("getDocBodyByDocId(): docId = {}", documentId);
        return jdbcTemplate.query(docBodySelectByIdSql, new Object[]{documentId}, new DocBodyRowMapper());
    }

    @Override
    public void addDocBody(List<DocBody> docBody) {
        LOGGER.debug("addDocBody(): docBodySize = {}", docBody.size());
        for(DocBody docBody1 : docBody) {
            jdbcTemplate.update(docBodyInsertSql,new Object[]{docBody1.getDocumentId()
                , docBody1.getDetailId()
                , docBody1.getDetailCount()});
        }
    }

    @Override
    public void deleteDocBodyById(Integer documentId) {
        LOGGER.debug("deleteDocBodyById(): docId = {}", documentId);
        jdbcTemplate.update(docBodyDeleteByIdSql, new Object[]{documentId});
    }

    @Override
    public List<DocBody> getAllIncomeDetails() {
        LOGGER.debug("getAllIncomeDetails():");
        return jdbcTemplate.query(incomeDetailsSelectSql, new DocBodyRowMapper());
    }

    @Override
    public Boolean isPresentDetail(Integer detailId) {
        LOGGER.debug("isPresentDetail():");
        return jdbcTemplate.queryForObject(detailIdPresSql, new Object[]{detailId}, Integer.class) > 0 ? true : false;
    }

    @Override
    public List<DocBody> getAllOutputDetails() {
        LOGGER.debug("getAllIncomeDetails():");
        return jdbcTemplate.query(outputDetailsSelectSql, new DocBodyRowMapper());
    }
}
