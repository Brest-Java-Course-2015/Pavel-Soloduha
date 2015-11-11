package com.epam.brest.course2015.service;

import com.epam.brest.course2015.domain.DocHead;
import com.epam.brest.course2015.domain.Document;

import java.util.List;

/**
 * Created by pavel on 11/10/15.
 */
public interface DocumentService {

    List<Document> getAllDocuments();

    Integer addDocument(Document document);

    Document getDocumentById(Integer documentId);

    void updateDocumentPrice(Integer documentId, Integer documentPrice);

    void deleteDocument(Integer documentId);
}
