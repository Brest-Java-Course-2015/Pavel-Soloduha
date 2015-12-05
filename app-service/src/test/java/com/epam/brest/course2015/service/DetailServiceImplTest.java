package com.epam.brest.course2015.service;

import com.epam.brest.course2015.domain.Detail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pavel on 11/18/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service.xml"})
@Transactional()
public class DetailServiceImplTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Integer DETAIL_ID_1 = 44;
    private static final Integer DETAIL_ID_2 = 25;

    private static final String DETAIL_NAME_1 = "Болт";
    private static final String DETAIL_NAME_2 = "Лопата";
    private static final Detail DETAIL = new Detail(DETAIL_ID_1, DETAIL_NAME_1);


    @Autowired
    DetailService detailService;

    @Test
    public void testGetAllDetails() throws Exception {
        LOGGER.debug("test: getAllDetails()");
        List<Detail> detailList = detailService.getAllDetails();
        Assert.assertTrue(detailList.size() > 0); //?
    }

    @Test
    public void testDeleteDetail() throws Exception {
        LOGGER.debug("test: deleteDetail()");
        int sizeBefore = detailService.getAllDetails().size();
        detailService.deleteDetail(DETAIL_ID_1);
        int sizeAfter = detailService.getAllDetails().size();
        Assert.assertTrue(sizeBefore - 1 == sizeAfter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteDetailNullDetailId() throws Exception {
        LOGGER.debug("test: deleteDetailNullDetailId()");
        detailService.deleteDetail(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteDetailNegDetailId() throws Exception {
        LOGGER.debug("test: deleteDetailNegDetailId()");
        detailService.deleteDetail(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteAbsentDetail() throws Exception {
        LOGGER.debug("test: deleteAbsentDetail()");
        detailService.deleteDetail(68);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteDetailForKey() throws Exception {
        LOGGER.debug("test: deleteDetailForKey()");
        detailService.deleteDetail(2);
    }

    @Test
    public void testUpdateDetail() throws Exception {
        LOGGER.debug("test: updateDetail()");
        detailService.updateDetail(DETAIL);
        String detName = detailService.getDetailById(DETAIL.getDetailId()).getDetailName();
        Assert.assertTrue(detName.equals(DETAIL_NAME_1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullDetail() throws Exception {
        LOGGER.debug("test: updateNullDetail()");
        detailService.updateDetail(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateDetailNullDetailId() throws Exception {
        LOGGER.debug("test: updateDetailNullDetailId()");
        detailService.updateDetail(new Detail(null, DETAIL_NAME_1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateDetailNullDetailName() throws Exception {
        LOGGER.debug("test: updateDetailNullDetailName()");
        detailService.updateDetail(new Detail(DETAIL_ID_1, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateDetailNegDetailId() throws Exception {
        LOGGER.debug("test: updateDetailNegDetailId()");
        detailService.updateDetail(new Detail(-10, DETAIL_NAME_1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateAbsentDetail() throws Exception {
        LOGGER.debug("test: updateAbsentDetail()");
        detailService.updateDetail(new Detail(60, DETAIL_NAME_1));
    }

    @Test
    public void testAddDetail() throws Exception {
        LOGGER.debug("test: addDetail()");
        Detail detail = new Detail(DETAIL_NAME_1);
        int id = detailService.addDetail(detail);
        Detail newDetail = detailService.getDetailById(id);
        Assert.assertTrue(DETAIL_NAME_1.equals(newDetail.getDetailName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullDetail() {
        LOGGER.debug("test: addNullDetail()");
        detailService.addDetail(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDetailNullName() throws Exception {
        LOGGER.debug("test: addDetailNullName()");
        detailService.addDetail(new Detail());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddExistDetail() throws Exception {
        LOGGER.debug("test: addExistDetail()");
        detailService.addDetail(new Detail(DETAIL_NAME_2));
    }

    @Test
    public void testGetDetailById() throws Exception {
        LOGGER.debug("test: getDetailById()");
        Detail detail = detailService.getDetailById(DETAIL_ID_2);
        Assert.assertTrue(detail.getDetailName().equals(DETAIL_NAME_2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDetailByNullId() throws Exception {
        LOGGER.debug("test: getDetailByNullId()");
        detailService.getDetailById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDetailByNegId() throws Exception {
        LOGGER.debug("test: getDetailByNegId()");
        detailService.getDetailById(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAbsentDetailById() throws Exception {
        LOGGER.debug("test: getAbsentDetailById()");
        detailService.getDetailById(15);
    }

    @Test
    public void testHasReferencesTrue() {
        LOGGER.debug("test: hasReferencesTrue()");
        Assert.assertTrue(detailService.hasReferences(16));
    }

    @Test
    public void testHasReferencesFalse() {
        LOGGER.debug("test: hasReferencesFalse()");
        Assert.assertTrue(!detailService.hasReferences(1));
    }
}