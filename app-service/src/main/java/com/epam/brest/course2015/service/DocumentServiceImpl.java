package com.epam.brest.course2015.service;

import com.epam.brest.course2015.dao.DocBodyDao;
import com.epam.brest.course2015.dao.DocHeadDao;
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

        Integer docId = docHeadDao.addDocHead(document.getDocHead());

        docBodyDao.addDocBody(document.getDocBody());

        return docId;
    }

    @Override
    public Document getDocumentById(Integer documentId) {
        DocHead docHead = docHeadDao.getDocHeadById(documentId);
        List<DocBody> docBody = docBodyDao.getDocBodyByDocId(documentId);
        return new Document(docHead, docBody);
    }

    @Override
    public void updateDocumentPrice(Integer documentId, Integer documentPrice) {
        docHeadDao.updateDocHeadPrice(documentId, documentPrice);
    }

    @Override
    public void deleteDocument(Integer documentId) {
        docBodyDao.deleteDocBodyById(documentId);

        docHeadDao.deleteDocHeadById(documentId);
    }
}
