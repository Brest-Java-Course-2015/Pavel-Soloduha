package com.epam.brest.course2015.dao;


import com.epam.brest.course2015.domain.DocBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavel on 11/16/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional()
public class DocBodyDaoImplTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final Integer DOCUMENT_ID = 50;
    private static final List<DocBody> DOC_BODY_LIST = new ArrayList<DocBody>();

    {
        DOC_BODY_LIST.add(new DocBody(DOCUMENT_ID, 3, "detailName", 50));
        DOC_BODY_LIST.add(new DocBody(DOCUMENT_ID, 12, "detailName", 20));
        DOC_BODY_LIST.add(new DocBody(DOCUMENT_ID, 44, "detailName", 3));
    }

    @Autowired
    private DocBodyDao docBodyDao;

    @Autowired
    private DetailDao detailDao;

    @Test
    public void testGetDocBodyByDocId() {
        LOGGER.debug("test: getDocBodyByDocId()");
        int docId = 43;
        List<DocBody> docBody = docBodyDao.getDocBodyByDocId(docId);
        Assert.assertTrue(docBody.size() == 4);
    }

    @Test
    public void testAddDocBody() {
        LOGGER.debug("test: addDocBody()");
        int oldSize = docBodyDao.getDocBodyByDocId(DOCUMENT_ID).size();
        docBodyDao.addDocBody(DOC_BODY_LIST);
        int newSize = docBodyDao.getDocBodyByDocId(DOCUMENT_ID).size();
        Assert.assertTrue(oldSize + DOC_BODY_LIST.size() == newSize);
    }

    @Test
    public void testDeleteDocBodyById() {
        LOGGER.debug("test: deleteDocBodyById()");
        docBodyDao.deleteDocBodyById(DOCUMENT_ID);
        int newSize = docBodyDao.getDocBodyByDocId(DOCUMENT_ID).size();
        Assert.assertTrue(newSize == 0);
    }

    @Test
    public void testGetAllIncomeDetails() {
        LOGGER.debug("test: getAllIncomeDetails()");
        List<DocBody> list = docBodyDao.getAllIncomeDetails();
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testGetAllOutputDetails() {
        LOGGER.debug("test: getAllOutputDetails()");
        List<DocBody> list = docBodyDao.getAllOutputDetails();
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testIsPresentDetail() {
        LOGGER.debug("test: isPresentDetail()");
        Assert.assertTrue(docBodyDao.isPresentDetail(25));
    }

    @Test
    public void testIsPresentDetailFalse() {
        LOGGER.debug("test: isPresentDetailFalse()");
        Assert.assertTrue(!docBodyDao.isPresentDetail(99));
    }

}
