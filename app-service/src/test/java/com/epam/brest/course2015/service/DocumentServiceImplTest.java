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
    private static final DocHead DOC_HEAD = new DocHead(60, 1, Date.valueOf("2015-10-20"));
    private static final List<DocBody> DOC_BODY = new ArrayList<DocBody>();
    {
        DOC_BODY.add(new DocBody(60, 12, "Молоток", 50));
        DOC_BODY.add(new DocBody(60, 16, "Гвоздь", 40));
        DOC_BODY.add(new DocBody(60, 25, "Лопата", 5));
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
//    public void testUpdateDocument() throws Exception {
//        LOGGER.debug("test: updateDocument()");
//        int docId = documentService.addDocument(DOCUMENT);
//        Document document = documentService.getDocumentById(docId);
//        document.getDocBody().clear();
//        documentService.updateDocument(document);
//        System.out.println(documentService.getDocumentById(docId).getDocBody().size());
//        Assert.assertTrue(documentService.getDocumentById(docId).getDocBody().size() == 0);
//    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullDocument() throws Exception {
        LOGGER.debug("test: updateNullDocument()");
        documentService.updateDocument(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateDocumentNullDocHead() throws Exception {
        LOGGER.debug("test: updateDocumentNullDocHead()");
        documentService.updateDocument(new Document(null, DOC_BODY));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateDocumentNullDocBody() throws Exception {
        LOGGER.debug("test: updateDocumentNullDocBody()");
        documentService.updateDocument(new Document(DOC_HEAD, null));

    }

    @Test
    public void testDeleteDocument() throws Exception {
        LOGGER.debug("test: deleteDocument()");
        int oldSize = documentService.getAllDocuments().size();
        documentService.deleteDocument(50);
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
        LOGGER.debug("test: getAllIncomeDetails()");
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
        LOGGER.debug("test: getAllDocHeads()");
        List<DocHead> list = documentService.getAllDocHeads();
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testIsPresentDocInTableTrue() throws Exception {
        LOGGER.debug("test: isPresentDocInTableTrue()");
        Assert.assertTrue(documentService.isPresentDocumentInTable(40));
    }

    @Test
    public void testIsPresentDocInTableFalse() throws Exception {
        LOGGER.debug("test: isPresentDocInTableFalse()");
        Assert.assertTrue(!documentService.isPresentDocumentInTable(2));
    }

    @Test
    public void testIsPresentDetailInTableTrue() throws Exception {
        LOGGER.debug("test: isPresentDetailInTableTrue()");
        Assert.assertTrue(documentService.isPresentDetailInTable(12));
    }

    @Test
    public void testIsPresentDetailInTableFalse() throws Exception {
        LOGGER.debug("test: isPresentDetailInTableFalse()");
        Assert.assertTrue(!documentService.isPresentDetailInTable(1));
    }

    @Test
    public void testIsPossibleState() throws Exception {
        LOGGER.debug("test: isPossibleState()");
        Assert.assertTrue(documentService.isPossibleState(documentService.getCurrentState()));
    }
}
