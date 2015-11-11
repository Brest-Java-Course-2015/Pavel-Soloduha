package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.DocBody;
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

    @Value("${docbody.selectDocBodyById}")
    private String docBodySelectByIdSql;

    @Value("${docbody.insertDocBody}")
    private String docBodyInsertSql;

    @Value("${docbody.deleteDocBodyById}")
    private String docBodyDeleteByIdSql;

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
        parameterSource.addValue(DETAIL_COUNT.getValue(), docBody.getDetailCount());
        return parameterSource;
    }

    private class DocBodyRowMapper implements RowMapper<DocBody> {

        @Override
        public DocBody mapRow(ResultSet resultSet, int i) throws SQLException {
            DocBody docBody = new DocBody(resultSet.getInt(DOCUMENT_ID.getValue()),
                    resultSet.getInt(DETAIL_ID.getValue()),
                    resultSet.getInt(DETAIL_COUNT.getValue()));
            return docBody;
        }
    }

    @Override
    public List<DocBody> getDocBodyByDocId(Integer documentId) {
        return jdbcTemplate.query(docBodySelectByIdSql, new Object[]{documentId}, new DocBodyRowMapper());
    }

    @Override
    public void addDocBody(List<DocBody> docBody) {
        for(DocBody docBody1 : docBody) {
            jdbcTemplate.query(docBodyInsertSql, new Object[]{docBody1}, new DocBodyRowMapper());
        }
    }

    @Override
    public void deleteDocBodyById(Integer documentId) {
        jdbcTemplate.update(docBodyDeleteByIdSql, new Object[]{documentId});
    }
}
