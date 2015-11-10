package com.epam.brest.course2015.rest;

import com.epam.brest.course2015.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pavel on 11/10/15.
 */

@RestController
public class DocumentRestController {

    @Autowired
    DocumentService docomentService;
}
