package com.epam.brest.course2015.service;

import com.epam.brest.course2015.domain.Detail;

import java.util.List;

/**
 * Created by pavel on 11/7/15.
 */

public interface DetailService {

    List<Detail> getAllDetails();

    void deleteDetail(Integer detailId);

    void updateDetail(Detail detail);

    Integer addDetail(Detail detail);
}