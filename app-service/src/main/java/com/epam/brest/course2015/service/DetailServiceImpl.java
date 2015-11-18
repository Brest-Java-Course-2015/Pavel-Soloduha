package com.epam.brest.course2015.service;

import com.epam.brest.course2015.dao.DetailDao;
import com.epam.brest.course2015.domain.Detail;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by pavel on 11/7/15.
 */

@Transactional
public class DetailServiceImpl implements DetailService {

    private static final Logger LOGGER = LogManager.getLogger();

    private DetailDao detailDao;

    public void setDetailDao(DetailDao detailDao) {
        this.detailDao = detailDao;
    }

    //TODO impl method
    @Override
    public Boolean hasReferences(Integer detailId) {
        return false;
    }

    @Override
    public Boolean isPresentInTable(Integer detailId) {
        return true;
    }


    @Override
    public List<Detail> getAllDetails() {
        LOGGER.debug("getAllDetails()");
        return detailDao.getAllDetails();
    }

    @Override
    public void deleteDetail(Integer detailId) {
        LOGGER.debug("deleteDetail(): detailId = {}", detailId);
        Assert.notNull(detailId, "DetailId must not be null.");
        Assert.isTrue(detailId > 0);
        Assert.isTrue(isPresentInTable(detailId));
        Assert.isTrue(!hasReferences(detailId));
        detailDao.deleteDetail(detailId);
    }

    @Override
    public void updateDetail(Detail detail) {
        Assert.notNull(detail, "Detail must not be null");
        LOGGER.debug("updateDetail(): detailId = {}, detailName = {}", detail.getDetailId(), detail.getDetailName());
        Assert.notNull(detail.getDetailId(), "DetailId must not be null");
        Assert.hasText(detail.getDetailName(), "DetailName must not be null");
        Assert.isTrue(detail.getDetailId() > 0);
        Assert.isTrue(isPresentInTable(detail.getDetailId()));
        detailDao.updateDetail(detail);
    }

    @Override
    public Integer addDetail(Detail detail) {
        Assert.notNull(detail, "Detail must not be null");
        LOGGER.debug("addDetail(): detailId = {}, detailName = {}", detail.getDetailId(), detail.getDetailName());
        Assert.hasText(detail.getDetailName(), "DetailName must not be null");
        return detailDao.addDetail(detail);
    }

    @Override
    public Detail getDetailById(Integer detailId) {
        LOGGER.debug("getDetailById(): detailId = {}", detailId);
        Assert.notNull(detailId, "DetailId must not be null");
        Assert.isTrue(detailId > 0);
        return detailDao.getDetailById(detailId);
    }
}