package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.DocHead;

import java.util.List;

/**
 * Created by pavel on 11/10/15.
 */
public interface DocHeadDao {
    List<DocHead> getAllDocHeads();

    Integer addDocHead(DocHead docHead);

    DocHead getDocHeadById(Integer documentId);

    void updateDocHead(DocHead docHead);

    void deleteDocHeadById(Integer documentId);

    Boolean isPresentDocument(Integer documentId);
}
