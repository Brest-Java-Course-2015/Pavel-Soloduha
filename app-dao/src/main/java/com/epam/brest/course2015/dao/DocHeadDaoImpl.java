package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.DocHead;
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

import static com.epam.brest.course2015.domain.DocHead.DocHeadFields.*;

/**
 * Created by pavel on 11/10/15.
 */
public class DocHeadDaoImpl implements DocHeadDao {

    private static final Logger LOGGER = LogManager.getLogger();

    @Value("${dochead.select}")
    private String docHeadSelectSql;

    @Value("${dochead.insertDocHead}")
    private String docHeadInsertSql;

    @Value("${dochead.selectDocHeadById}")
    private String docHeadSelectByIdSql;

    @Value("${dochead.updateDocHeadPrice}")
    private String docHeadUpdatePriceSql;

    @Value("${dochead.deleteDocHeadById}")
    private String docHeadDeleteByIdSql;

    @Value("${dochead.documentdPresSql}")
    private String docHeadDocumentdPresSql;

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DocHeadDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private MapSqlParameterSource getParametersMap(DocHead docHead) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(DOCUMENT_ID.getValue(), docHead.getDocumentId());
        parameterSource.addValue(DOCUMENT_TYPE.getValue(), docHead.getDocumentType());
        parameterSource.addValue(DOCUMENT_DATE.getValue(), docHead.getDocumentDate());
        parameterSource.addValue(DOCUMENT_PRICE.getValue(), docHead.getDocumentPrice());
        return parameterSource;
    }

    private class DocHeadRowMapper implements RowMapper<DocHead> {

        @Override
        public DocHead mapRow(ResultSet resultSet, int i) throws SQLException {
            DocHead docHead = new DocHead(resultSet.getInt(DOCUMENT_ID.getValue()),
                    resultSet.getInt(DOCUMENT_TYPE.getValue()),
                    resultSet.getDate(DOCUMENT_DATE.getValue()),
                    resultSet.getInt(DOCUMENT_PRICE.getValue()));
            return docHead;
        }
    }

    @Override
    public List<DocHead> getAllDocHeads() {
        LOGGER.debug("getAllDocHeads()");
        return jdbcTemplate.query(docHeadSelectSql, new DocHeadRowMapper());
    }

    @Override
    public Integer addDocHead(DocHead docHead) {
        LOGGER.debug("addDocHead(): docType = {}, docDate = {}, docPrice = {}",
                docHead.getDocumentType(), docHead.getDocumentDate(), docHead.getDocumentPrice());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(docHeadInsertSql, getParametersMap(docHead), keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public DocHead getDocHeadById(Integer documentId) {
        LOGGER.debug("getDocHeadById(): docId = {}", documentId);
        return jdbcTemplate.queryForObject(docHeadSelectByIdSql, new Object[]{documentId}, new DocHeadRowMapper());
    }

    @Override
    public void updateDocHeadPrice(Integer documentId, Integer documentPrice) {
        LOGGER.debug("updateDocHeadPrice(): docId = {}, docPrice = {}", documentId, documentPrice);
        jdbcTemplate.update(docHeadUpdatePriceSql, new Object[]{documentPrice, documentId});
    }

    @Override
    public void deleteDocHeadById(Integer documentId) {
        LOGGER.debug("deleteDocHeadById(): docId = {}", documentId);
        jdbcTemplate.update(docHeadDeleteByIdSql, new Object[]{documentId});
    }

    @Override
    public Boolean isPresentDocument(Integer documentId) {
        LOGGER.debug("isPresentDocument():");
        return jdbcTemplate.queryForObject(docHeadDocumentdPresSql, new Object[]{documentId}, Integer.class) > 0 ? true : false;
    }
}
