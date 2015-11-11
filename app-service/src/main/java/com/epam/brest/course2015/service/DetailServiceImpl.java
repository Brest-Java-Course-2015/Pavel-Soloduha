package com.epam.brest.course2015.service;

import com.epam.brest.course2015.dao.DetailDao;
import com.epam.brest.course2015.domain.Detail;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    public List<Detail> getAllDetails() {
        LOGGER.debug("getAllDetails()");
        return detailDao.getAllDetails();
    }

    @Override
    public void deleteDetail(Integer detailId) {
        LOGGER.debug("deleteDetail(): detailId = {}", detailId);
        detailDao.deleteDetail(detailId);
    }

    @Override
    public void updateDetail(Detail detail) {
        LOGGER.debug("updateDetail(): detailId = {}, detailName = {}"
                , detail.getDetailId(), detail.getDetailName());
        detailDao.updateDetail(detail);
    }

    @Override
    public Integer addDetail(Detail detail) {
        LOGGER.debug("addDetail(): detailId = {}, detailName = {}"
                , detail.getDetailId(), detail.getDetailName());
        return detailDao.addDetail(detail);
    }

    @Override
    public Detail getDetailById(Integer detailId) {
        LOGGER.debug("getDetailById(): detailId = {}", detailId);
        return detailDao.getDetailById(detailId);
    }
}