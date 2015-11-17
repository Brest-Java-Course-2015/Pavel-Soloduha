package com.epam.brest.course2015.dao;


import com.epam.brest.course2015.domain.Detail;
import com.epam.brest.course2015.domain.DocBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
        assertTrue(docBody.size() == 4);
    }

    //TODO fix implimentation of method
//    @Test
//    public void testAddDocBody() {
//        LOGGER.debug("test: addDocBody()");
//        int oldSize = docBodyDao.getDocBodyByDocId(DOCUMENT_ID).size();
//        System.out.println("oldSize = " + oldSize);
//        docBodyDao.addDocBody(DOC_BODY_LIST);
//        int newSize = docBodyDao.getDocBodyByDocId(DOCUMENT_ID).size();
//        System.out.println("newSize = " + newSize);
//        assertTrue(oldSize + DOC_BODY_LIST.size() == newSize);
//    }

    @Test
    public void testDeleteDocBodyById() {
        LOGGER.debug("test: deleteDocBodyById()");
        docBodyDao.deleteDocBodyById(DOCUMENT_ID);
        int newSize = docBodyDao.getDocBodyByDocId(DOCUMENT_ID).size();
        assertTrue(newSize == 0);
    }

    //TODO fix implimentation of method
//    @Test
//    public void testGetAllIncomeDetails() {
//        LOGGER.debug("test: deleteDocBodyById()");
//        List<DocBody> list = docBodyDao.getAllIncomeDetails();
//        List<Detail> detList = detailDao.getAllDetails();
//        assertTrue(list.size() == detList.size());
//    }
}
