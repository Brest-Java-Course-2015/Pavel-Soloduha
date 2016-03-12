package com.epam.brest.course2015.rest;

import com.epam.brest.course2015.domain.DocBody;
import com.epam.brest.course2015.domain.DocHead;
import com.epam.brest.course2015.domain.Document;
import com.epam.brest.course2015.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by pavel on 11/10/15.
 */

@RestController
@RequestMapping(value = "document")
public class DocumentRestController {

    @Autowired
    DocumentService documentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody Integer addDocument(@RequestBody Document document) {
        return documentService.addDocument(document);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Document getDocumentById(@PathVariable(value = "id") Integer documentId) {
        return documentService.getDocumentById(documentId);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateDocument(@RequestBody Document document) {
        documentService.updateDocument(document);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteDocument(@PathVariable(value = "id") Integer documentId) {
        documentService.deleteDocument(documentId);
    }

    @RequestMapping(value = "/inc", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<DocBody> getAllIncomeDetails() {
        return documentService.getAllIncomeDetails();
    }

    @RequestMapping(value = "/out", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<DocBody> getAllOutputDetails() {
        return documentService.getAllOutputDetails();
    }

    @RequestMapping(value = "/heads", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<DocHead> getAllDocHeads() {
        return documentService.getAllDocHeads();
    }

    @RequestMapping(value = "/curstat", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<DocBody> getCurrentState() {
        return documentService.getCurrentState();
    }
}
