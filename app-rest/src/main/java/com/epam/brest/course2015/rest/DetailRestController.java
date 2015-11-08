package com.epam.brest.course2015.rest;

import com.epam.brest.course2015.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pavel on 11/7/15.
 */

@RestController
public class DetailRestController {

    @Autowired
    private DetailService detailService;
}