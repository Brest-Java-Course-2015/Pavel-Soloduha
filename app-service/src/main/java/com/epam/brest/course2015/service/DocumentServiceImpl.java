package com.epam.brest.course2015.service;

import com.epam.brest.course2015.dao.DocBodyDao;
import com.epam.brest.course2015.dao.DocHeadDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import com.epam.brest.course2015.domain.DocBody;
import com.epam.brest.course2015.domain.DocHead;
import com.epam.brest.course2015.domain.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavel on 11/10/15.
 */

@Transactional
public class DocumentServiceImpl implements DocumentService {

    private static final Logger LOGGER = LogManager.getLogger();

    private DocHeadDao docHeadDao;

    private DocBodyDao docBodyDao;

    public void setDocHeadDao(DocHeadDao docHeadDao) {
        this.docHeadDao = docHeadDao;
    }

    public void setDocBodyDao(DocBodyDao docBodyDao) {
        this.docBodyDao = docBodyDao;
    }

    @Override
    public List<Document> getAllDocuments() {
        LOGGER.debug("getAllDocuments()");
        List<Document> docs = new ArrayList<Document>();
        List<DocHead> heads = docHeadDao.getAllDocHeads();
        for(DocHead docHead : heads) {
            List<DocBody> docBody = docBodyDao.getDocBodyByDocId(docHead.getDocumentId());
            docs.add(new Document(docHead, docBody));
        }
        return docs;
    }

    @Override
    public Integer addDocument(Document document) {
        DocHead tmpHead = document.getDocHead();
        LOGGER.debug("addDocument(): docType = {}, docDate = {}, docPrice = {}",
                tmpHead.getDocumentType(), tmpHead.getDocumentDate(), tmpHead.getDocumentPrice());
        LOGGER.debug("addDocument(): docBodySize = {}", document.getDocBody().size());
        Integer docId = docHeadDao.addDocHead(document.getDocHead());
        docBodyDao.addDocBody(document.getDocBody());
        return docId;
    }

    @Override
    public Document getDocumentById(Integer documentId) {
        LOGGER.debug("getDocumentById(): docId = {}", documentId);
        DocHead docHead = docHeadDao.getDocHeadById(documentId);
        List<DocBody> docBody = docBodyDao.getDocBodyByDocId(documentId);
        return new Document(docHead, docBody);
    }

    @Override
    public void updateDocumentPrice(Integer documentId, Integer documentPrice) {
        LOGGER.debug("updateDocumentPrice(): docId = {}, docPrice", documentId, documentPrice);
        docHeadDao.updateDocHeadPrice(documentId, documentPrice);
    }

    @Override
    public void deleteDocument(Integer documentId) {
        LOGGER.debug("deleteDocument(): docId = {}", documentId);
        docBodyDao.deleteDocBodyById(documentId);
        docHeadDao.deleteDocHeadById(documentId);
    }

    @Override
    public List<DocBody> getAllIncomeDetails() {
        LOGGER.debug("getAllIncomeDetails()");
        return docBodyDao.getAllIncomeDetails();
    }

    @Override
    public List<DocHead> getAllDocHeads() {
        LOGGER.debug("getAllDocHeads()");
        return docHeadDao.getAllDocHeads();
    }
}
