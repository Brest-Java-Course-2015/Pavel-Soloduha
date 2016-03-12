package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.DocHead;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.sql.Date;

/**
 * Created by pavel on 11/16/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional()
public class DocHeadDaoImplTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Integer DOCUMENT_ID_1 = 63;
    private static final Integer DOCUMENT_ID_2 = 43;
    private static final Integer DOCUMENT_TYPE = 2;
    private static final Date DOCUMENT_DATE = Date.valueOf("2015-10-20");
    private static final DocHead DOC_HEAD = new DocHead(DOCUMENT_ID_1, DOCUMENT_TYPE, DOCUMENT_DATE);

    @Autowired
    private DocHeadDao docHeadDao;

    @Test
    public void testGetAllDocHeads() {
        LOGGER.debug("test: getAllDocHeads()");
        List<DocHead> docHeads = docHeadDao.getAllDocHeads();
        Assert.assertTrue(docHeads.size() == 3);
    }

    @Test
    public void testAddDocHead() {
        LOGGER.debug("test: addDocHead()");
        int docId = docHeadDao.addDocHead(DOC_HEAD);
        DocHead docHead = docHeadDao.getDocHeadById(docId);
        Assert.assertTrue(docHead.equals(DOC_HEAD));
    }

    @Test
    public void testGetDocHeadById() {
        LOGGER.debug("test: getDocHeadById()");
        int docId = docHeadDao.addDocHead(DOC_HEAD);
        DocHead docHead = docHeadDao.getDocHeadById(docId);
        Assert.assertTrue(docHead.equals(DOC_HEAD));
    }

    @Test
    public void testUpdateDocHead() {
        LOGGER.debug("test: updateDocHead()");
        int docId = docHeadDao.addDocHead(DOC_HEAD);
        DocHead docHead = new DocHead(docId, DOCUMENT_TYPE, Date.valueOf("2015-10-22"));
        docHeadDao.updateDocHead(docHead);
        Assert.assertTrue(docHeadDao.getDocHeadById(docHead.getDocumentId()).getDocumentDate().compareTo(Date.valueOf("2015-10-22")) == 0);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDeleteDocHeadByIdEmptyResultExc() {
        LOGGER.debug("test: deleteDocHeadByIdEmptyResultExc()");
        docHeadDao.addDocHead(DOC_HEAD);
        DocHead docHead = docHeadDao.getDocHeadById(DOCUMENT_ID_1);
        docHeadDao.deleteDocHeadById(DOCUMENT_ID_1);
        DocHead newDocHead = docHeadDao.getDocHeadById(DOCUMENT_ID_1);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testDeleteDocHeadByIdForeignKeyExc() {
        LOGGER.debug("test: deleteDocHeadByIdForeignKeyExc()");
        DocHead docHead = docHeadDao.getDocHeadById(DOCUMENT_ID_2);
        docHeadDao.deleteDocHeadById(DOCUMENT_ID_2);
    }

    @Test
    public void testIsPresentDocument() {
        LOGGER.debug("test: isPresentDocument()");
        Assert.assertTrue(docHeadDao.isPresentDocument(DOCUMENT_ID_2));
    }

    @Test
    public void testIsPresentDocumentExc() {
        LOGGER.debug("test: isPresentDocumentExc()");
        Assert.assertTrue(!docHeadDao.isPresentDocument(99));
    }
}