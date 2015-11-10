package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.DocHead;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.epam.brest.course2015.domain.DocHead.DocHeadFields.*;

/**
 * Created by pavel on 11/10/15.
 */
public class DocHeadDaoImpl implements DocHeadDao {

    @Value("${dochead.select}")
    private String docHeadSelectSql;

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
        return jdbcTemplate.query(docHeadSelectSql, new DocHeadRowMapper());
    }
}
