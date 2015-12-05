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

    @Override
    public Boolean hasReferences(Integer detailId) {
        LOGGER.debug("hasReferences(): detailId = {}", detailId);
        return detailDao.hasReferences(detailId);
    }

    @Override
    public Boolean isIdPresentInTable(Integer detailId) {
        LOGGER.debug("isIdPresentInTable(): detailId = {}", detailId);
        return detailDao.isIdPresentInTable(detailId);
    }

    @Override
    public Boolean isNamePresentInTable(String detailName) {
        LOGGER.debug("isNamePresentInTable(): detailName = {}", detailName);
        return detailDao.isNamePresentInTable(detailName);
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
        Assert.isTrue(isIdPresentInTable(detailId));
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
        Assert.isTrue(isIdPresentInTable(detail.getDetailId()));
        detailDao.updateDetail(detail);
    }

    @Override
    public Integer addDetail(Detail detail) {
        Assert.notNull(detail, "Detail must not be null");
        LOGGER.debug("addDetail(): detailId = {}, detailName = {}", detail.getDetailId(), detail.getDetailName());
        Assert.hasText(detail.getDetailName(), "DetailName must not be null");
        Assert.isTrue(!isNamePresentInTable(detail.getDetailName()));
        return detailDao.addDetail(detail);
    }

    @Override
    public Detail getDetailById(Integer detailId) {
        LOGGER.debug("getDetailById(): detailId = {}", detailId);
        Assert.notNull(detailId, "DetailId must not be null");
        Assert.isTrue(detailId > 0);
        Assert.isTrue(isIdPresentInTable(detailId));
        return detailDao.getDetailById(detailId);
    }
}