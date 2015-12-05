package com.epam.brest.course2015.service;

import com.epam.brest.course2015.dao.DocBodyDao;
import com.epam.brest.course2015.dao.DocHeadDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import com.epam.brest.course2015.domain.DocBody;
import com.epam.brest.course2015.domain.DocHead;
import com.epam.brest.course2015.domain.Document;
import org.springframework.util.Assert;

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
        Assert.notNull(document, "Document must not be null.");
        Assert.notNull(document.getDocHead(), "DocumentHead must not be null.");
        Assert.notNull(document.getDocBody(), "DocumentBody must not be null.");
        DocHead tmpHead = document.getDocHead();
        LOGGER.debug("addDocument(): docType = {}, docDate = {}, docPrice = {}",
                tmpHead.getDocumentType(), tmpHead.getDocumentDate(), tmpHead.getDocumentPrice());
        LOGGER.debug("addDocument(): docBodySize = {}", document.getDocBody().size());
        Integer docId = docHeadDao.addDocHead(document.getDocHead());
        List<DocBody> bodyList = document.getDocBody();
        for (int i = 0; i < bodyList.size(); i++) {
            bodyList.get(i).setDocumentId(docId);
            Assert.isTrue(isPresentDetailInTable(bodyList.get(i).getDetailId()));
        }
        docBodyDao.addDocBody(bodyList);
        return docId;
    }

    @Override
    public Document getDocumentById(Integer documentId) {
        Assert.notNull(documentId);
        LOGGER.debug("getDocumentById(): docId = {}", documentId);
        Assert.isTrue(documentId > 0);
        Assert.isTrue(isPresentDocumentInTable(documentId));
        DocHead docHead = docHeadDao.getDocHeadById(documentId);
        List<DocBody> docBody = docBodyDao.getDocBodyByDocId(documentId);
        return new Document(docHead, docBody);
    }

    @Override
    public void updateDocumentPrice(Integer documentId, Integer documentPrice) {
        Assert.notNull(documentId);
        Assert.notNull(documentPrice);
        LOGGER.debug("updateDocumentPrice(): docId = {}, docPrice", documentId, documentPrice);
        Assert.isTrue(isPresentDocumentInTable(documentId));
        Assert.isTrue(documentPrice >= 0);
        docHeadDao.updateDocHeadPrice(documentId, documentPrice);
    }

    @Override
    public void deleteDocument(Integer documentId) {
        Assert.notNull(documentId);
        LOGGER.debug("deleteDocument(): docId = {}", documentId);
        Assert.isTrue(documentId > 0);
        Assert.isTrue(isPresentDocumentInTable(documentId));
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

    @Override
    public Boolean isPresentDocumentInTable(Integer documentId) {
        LOGGER.debug("isPresentDocumentInTable(): docId = {}", documentId);
        return docHeadDao.isPresentDocument(documentId);
    }

    @Override
    public Boolean isPresentDetailInTable(Integer detailId) {
        LOGGER.debug("isPresentDetailInTable(): detailId = ()", detailId);
        return docBodyDao.isPresentDetail(detailId);
    }

    @Override
    public List<DocBody> getAllOutputDetails() {
        LOGGER.debug("getAllOutputDetails()");
        return docBodyDao.getAllOutputDetails();
    }
}
