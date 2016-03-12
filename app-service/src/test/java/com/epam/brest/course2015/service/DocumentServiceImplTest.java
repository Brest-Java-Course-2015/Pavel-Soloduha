package com.epam.brest.course2015.service;

import com.epam.brest.course2015.domain.DocBody;
import com.epam.brest.course2015.domain.DocHead;
import com.epam.brest.course2015.domain.Document;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 * Created by pavel on 11/18/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service.xml"})
@Transactional()
public class DocumentServiceImplTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final DocHead DOC_HEAD = new DocHead(60, 2, Date.valueOf("2015-10-20"));
    private static final List<DocBody> DOC_BODY = new ArrayList<DocBody>();
    {
        DOC_BODY.add(new DocBody(60, 12, "", 50));
        DOC_BODY.add(new DocBody(60, 16, "", 40));
        DOC_BODY.add(new DocBody(60, 25, "", 5));
    }
    private static final Document DOCUMENT = new Document(DOC_HEAD, DOC_BODY);

    @Autowired
    DocumentService documentService;

    @Test
    public void testGetAllDocuments() throws Exception {
        LOGGER.debug("test: getAllDocuments");
        List<Document> documentList = documentService.getAllDocuments();
        Assert.assertTrue(documentList.size() > 0);
    }

    @Test
    public void testAddDocument() throws Exception {
        LOGGER.debug("test: addDocument()");
        int oldSize = documentService.getAllDocuments().size();
        documentService.addDocument(DOCUMENT);
        int newSize = documentService.getAllDocuments().size();
        Assert.assertTrue(oldSize + 1 == newSize);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullDocument() throws Exception {
        LOGGER.debug("test: addNullDocument()");
        documentService.addDocument(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDocumentWithNullDocHead() throws Exception {
        LOGGER.debug("test: addDocumentWithNullDocHead()");
        documentService.addDocument(new Document(null, DOC_BODY));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDocumentWithNullDocBody() throws Exception {
        LOGGER.debug("test: addDocumentWithNullDocBody()");
        documentService.addDocument(new Document(DOC_HEAD, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDocumentWithAbsentDetail() throws Exception {
        LOGGER.debug("test: addDocumentWithAbsentDetail()");
        Document document = new Document(DOCUMENT);
        document.getDocBody().add(new DocBody(DOC_HEAD.getDocumentId(), 99, "", 50));
        documentService.addDocument(document);
    }

    @Test
    public void testGetDocumentById() throws Exception {
        LOGGER.debug("test: getDocumentById()");
        Document doc = documentService.getDocumentById(40);
        Assert.assertTrue(doc.getDocBody().size() == 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDocumentByNullId() throws Exception {
        LOGGER.debug("test: getDocumentByNullId()");
        Document doc = documentService.getDocumentById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDocumentByNegId() throws Exception {
        LOGGER.debug("test: getDocumentByNegId()");
        Document doc = documentService.getDocumentById(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDocumentByAbsentId() throws Exception {
        LOGGER.debug("test: getDocumentByAbsentId()");
        Document doc = documentService.getDocumentById(99);
    }

//    @Test
//    public void testUpdateDocumentPrice() throws Exception {
//        LOGGER.debug("test: updateDocumentPrice()");
//        documentService.updateDocumentPrice(40, 9600);
//        Document doc = documentService.getDocumentById(40);
//        Assert.assertTrue(doc.getDocHead().getDocumentPrice().equals(9600));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testUpdateDocumentNullPrice() throws Exception {
//        LOGGER.debug("test: updateDocumentNullPrice()");
//        documentService.updateDocumentPrice(40, null);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testUpdateDocumentPriceNullId() throws Exception {
//        LOGGER.debug("test: updateDocumentPriceNullId()");
//        documentService.updateDocumentPrice(null, 9600);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testUpdateDocumentNegPrice() throws Exception {
//        LOGGER.debug("test: updateDocumentNegPrice()");
//        documentService.updateDocumentPrice(40, -15000);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testUpdateAbsDocumentPrice() throws Exception {
//        LOGGER.debug("test: updateAbsDocumentPrice()");
//        documentService.updateDocumentPrice(99, 6500);
//    }

    @Test
    public void testDeleteDocument() throws Exception {
        LOGGER.debug("test: deleteDocument()");
        int oldSize = documentService.getAllDocuments().size();
        documentService.deleteDocument(40);
        int newSize = documentService.getAllDocuments().size();
        Assert.assertTrue(oldSize - 1 == newSize);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteDocumentWithNullId() throws Exception {
        LOGGER.debug("test: deleteDocumentWithNullId()");
        documentService.deleteDocument(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteDocumentWithNeglId() throws Exception {
        LOGGER.debug("test: deleteDocumentWithNeglId()");
        documentService.deleteDocument(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteDocumentWithAbsId() throws Exception {
        LOGGER.debug("test: deleteDocumentWithAbsId()");
        documentService.deleteDocument(99);
    }

    @Test
    public void testGetAllIncomeDetails() throws Exception {
        LOGGER.debug("test: deleteDocumentWithAbsId()");
        List<DocBody> list = documentService.getAllIncomeDetails();
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testGetAllOutputDetails() throws Exception {
        LOGGER.debug("test: getAllOutputDetails()");
        List<DocBody> list = documentService.getAllOutputDetails();
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testGetAllDocHeads() throws Exception {
        LOGGER.debug("test: deleteDocumentWithAbsId()");
        List<DocHead> list = documentService.getAllDocHeads();
        Assert.assertTrue(list.size() > 0);
    }
}
