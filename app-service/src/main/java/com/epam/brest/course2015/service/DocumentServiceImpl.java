package com.epam.brest.course2015.service;

import com.epam.brest.course2015.dao.DocBodyDao;
import com.epam.brest.course2015.dao.DocHeadDao;
import org.springframework.transaction.annotation.Transactional;
import com.epam.brest.course2015.domain.DocBody;
import com.epam.brest.course2015.domain.DocHead;
import com.epam.brest.course2015.domain.Document;

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
    public List<DocHead> getAllDocHeads() {
        return docHeadDao.getAllDocHeads();
    }
}
