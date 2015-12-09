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
public class DocumentRestController {

    @Autowired
    DocumentService documentService;

    @RequestMapping(value = "/docs", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @RequestMapping(value = "/doc", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody Integer addDocument(@RequestBody Document document) {
        return documentService.addDocument(document);
    }

    @RequestMapping(value = "/doc/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Document getDocumentById(@PathVariable(value = "id") Integer documentId) {
        return documentService.getDocumentById(documentId);
    }

    @RequestMapping(value = "/docPr/{id}/{price}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateDocumentPrice(@PathVariable(value = "id") Integer documentId,
                                        @PathVariable(value = "price") Integer documentPrice) {
        documentService.updateDocumentPrice(documentId, documentPrice);
    }

    @RequestMapping(value = "/doc/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteDocument(@PathVariable(value = "id") Integer documentId) {
        documentService.deleteDocument(documentId);
    }

    @RequestMapping(value = "/docinc", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<DocBody> getAllIncomeDetails() {
        return documentService.getAllIncomeDetails();
    }

    @RequestMapping(value = "/docout", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<DocBody> getAllOutputDetails() {
        return documentService.getAllOutputDetails();
    }

    @RequestMapping(value = "/docheads", method = RequestMethod.GET)
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
