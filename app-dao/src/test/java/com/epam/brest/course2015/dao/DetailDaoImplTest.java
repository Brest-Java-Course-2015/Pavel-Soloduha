package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.Detail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.junit.*;

import java.util.List;

/**
 * Created by pavel on 11/16/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional()
public class DetailDaoImplTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final Integer DETAIL_ID = 44;
    private static final String DETAIL_NAME = "Кирка";
    private static final String NEW_DETAIL_NAME = "Болт";
    private static final Detail DETAIL = new Detail(DETAIL_ID, NEW_DETAIL_NAME);
    private static final Detail NEW_DETAIL = new Detail(NEW_DETAIL_NAME);

    @Autowired
    private DetailDao detailDao;

    @Test
    public void testGetAllDetails() {
        LOGGER.debug("test: getAllDetails()");
        List<Detail> details = detailDao.getAllDetails();
        Assert.assertTrue(details.size() == 5);
    }

    @Test
    public void testDeleteDetail() {
        LOGGER.debug("test: deleteDetail()");
        int sizeBefore = detailDao.getAllDetails().size();
        Assert.assertTrue(sizeBefore > 0);
        detailDao.deleteDetail(DETAIL_ID);
        int sizeAfter = detailDao.getAllDetails().size();
        Assert.assertTrue(sizeBefore - 1 == sizeAfter);
    }

    @Test
    public void testUpdateDetail() {
        LOGGER.debug("test: updateDetail()");
        Detail oldDetail = detailDao.getDetailById(DETAIL.getDetailId());
        detailDao.updateDetail(DETAIL);
        Detail newDetail = detailDao.getDetailById(DETAIL.getDetailId());
        Integer oldDetailId = oldDetail.getDetailId();
        Integer newDetailId = newDetail.getDetailId();
        Assert.assertTrue(oldDetailId.equals(newDetailId));
    }

    @Test
    public void testAddDetail() {
        LOGGER.debug("test: addDetail()");
        Integer detailId = detailDao.addDetail(NEW_DETAIL);
        Assert.assertNotNull(detailId);
        Detail detail = detailDao.getDetailById(detailId);
        Assert.assertTrue(detail.getDetailName().equals(NEW_DETAIL_NAME));
    }

    @Test
    public void testGetDetailById() {
        LOGGER.debug("test: getDetailById()");
        Detail detail = detailDao.getDetailById(DETAIL_ID);
        Assert.assertNotNull(detail);
        Assert.assertEquals(detail.getDetailName(), DETAIL_NAME);
    }

    @Test
    public void testHasReferencesTrue() {
        LOGGER.debug("test: hasReferencesTrue()");
        Assert.assertTrue(detailDao.hasReferences(16));
    }

    @Test
    public void testHasReferencesFalse() {
        LOGGER.debug("test: hasReferencesFalse()");
        Assert.assertTrue(!detailDao.hasReferences(1));
    }

    @Test
    public void testIsIdPresentInTable() {
        LOGGER.debug("test: isIdPresentInTable()");
        Assert.assertTrue(detailDao.isIdPresentInTable(16));
    }

    @Test(expected = AssertionError.class)
    public void testIsIdPresentInTableExc() {
        LOGGER.debug("test: isIdPresentInTableExc()");
        Assert.assertTrue(detailDao.isIdPresentInTable(1));
    }

    @Test
    public void testIsNamePresentInTable() {
        LOGGER.debug("test: isNamePresentInTable()");
        Assert.assertTrue(detailDao.isNamePresentInTable("Лопата"));
    }

    @Test(expected = AssertionError.class)
    public void testIsNamePresentInTableExc() {
        LOGGER.debug("test: isNamePresentInTableExc()");
        Assert.assertTrue(detailDao.isNamePresentInTable("Шайба"));
    }
}