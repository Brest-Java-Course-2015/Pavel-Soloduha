package com.epam.brest.course2015.rest;

import com.epam.brest.course2015.domain.DocHead;
import com.epam.brest.course2015.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by pavel on 11/10/15.
 */

@RestController
public class DocumentRestController {

    @Autowired
    DocumentService documentService;

    @RequestMapping(value = "/docs", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public List<DocHead> getVersion() {
        return documentService.getAllDocHeads();
    }
}
