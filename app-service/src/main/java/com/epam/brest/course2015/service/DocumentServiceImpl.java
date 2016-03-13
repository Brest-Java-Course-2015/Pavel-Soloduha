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
        LOGGER.debug("addDocument(): docType = {}, docDate = {}",
                tmpHead.getDocumentType(), tmpHead.getDocumentDate());
        LOGGER.debug("addDocument(): docBodySize = {}", document.getDocBody().size());
        Integer docId = docHeadDao.addDocHead(document.getDocHead());
        List<DocBody> bodyList = document.getDocBody();
        for (int i = 0; i < bodyList.size(); i++) {
            bodyList.get(i).setDocumentId(docId);
            Assert.isTrue(isPresentDetailInTable(bodyList.get(i).getDetailId()));
        }
        docBodyDao.addDocBody(bodyList);
        Assert.isTrue(isPossibleState(this.getCurrentState()));
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

    //fixme
    @Override
    public void updateDocument(Document document) {
        Assert.notNull(document);
        Assert.notNull(document.getDocHead());
        Assert.notNull(document.getDocBody());
        Assert.notNull(document.getDocHead().getDocumentId());
        DocHead docHead = document.getDocHead();
        LOGGER.debug("updateDocument: docId = {}", docHead.getDocumentId());
        Assert.isTrue(isPresentDocumentInTable(docHead.getDocumentId()));
        docHeadDao.updateDocHead(docHead);
        docBodyDao.deleteDocBodyById(docHead.getDocumentId());
        docBodyDao.addDocBody(document.getDocBody());
        Assert.isTrue(isPossibleState(this.getCurrentState()));
    }

    @Override
    public void deleteDocument(Integer documentId) {
        Assert.notNull(documentId);
        LOGGER.debug("deleteDocument(): docId = {}", documentId);
        Assert.isTrue(documentId > 0);
        Assert.isTrue(isPresentDocumentInTable(documentId));
        docBodyDao.deleteDocBodyById(documentId);
        docHeadDao.deleteDocHeadById(documentId);
        Assert.isTrue(isPossibleState(this.getCurrentState()));
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

    @Override
    public List<DocBody> getCurrentState() {
        LOGGER.debug("getCurrentState()");
        List<DocBody> input = docBodyDao.getAllIncomeDetails();
        List<DocBody> output = docBodyDao.getAllOutputDetails();
        List<DocBody> result = new ArrayList<DocBody>();

        result.addAll(input);

        for(int i = 0; i < output.size(); i++) {
            DocBody outDocBody = output.get(i);
            for(int j = 0; j < result.size(); j++) {
                DocBody resDocBody = result.get(j);
                if(resDocBody.getDetailName().equals(outDocBody.getDetailName())) {
                    resDocBody.setDetailCount(resDocBody.getDetailCount() - outDocBody.getDetailCount());
                    result.set(j, resDocBody);
                }
            }
        }

        Assert.isTrue(isPossibleState(result));

        for(int i = result.size() - 1; i >= 0; i--) {
            DocBody resDocBody = result.get(i);
            if(resDocBody.getDetailCount() == 0) {
                result.remove(i);
            }
        }

//        boolean check = false;
//        for(int i = 0; i < input.size(); i++) {
//            DocBody item = input.get(i);
//
//            for(int j = 0; j < result.size(); j++) {
//                DocBody resItem = result.get(j);
//
//                check = false;
//                if(resItem.getDetailName().equals(item.getDetailName())) {
//                    resItem.setDetailCount(resItem.getDetailCount() + item.getDetailCount());
//                    result.set(j, resItem);
//                    check = true;
//                    break;
//                }
//            }
//
//            if(!check) {
//                DocBody newItem = new DocBody(item);
//                newItem.setDocumentId(1);
//                result.add(newItem);
//            }
//        }
//        for(int i = 0; i < output.size(); i++) {
//            DocBody item = output.get(i);
//
//            check = false;
//            for(int j = 0; j < result.size(); j++) {
//                DocBody resItem = result.get(j);
//
//                if(resItem.getDetailName().equals(item.getDetailName())) {
//                    resItem.setDetailCount(resItem.getDetailCount() - item.getDetailCount());
//                    result.set(j, resItem);
//                    check = true;
//                    break;
//                }
//            }
//
//            if(!check) {
//                DocBody newItem = new DocBody(item);
//                newItem.setDocumentId(1);
//                newItem.setDetailCount(-item.getDetailCount());
//                result.add(newItem);
//            }
//        }
//
//        for(int i = result.size() - 1; i >= 0; i--) {
//            DocBody item = result.get(i);
//            if(item.getDetailCount() == 0) {
//                result.remove(i);
//            }
//        }
        return result;
    }

    @Override
    public Boolean isPossibleState(List<DocBody> docBodies) {

        for(DocBody docBody : docBodies) {
            if(docBody.getDetailCount() < 0) {
                return false;
            }
        }
        return true;
    }
}
