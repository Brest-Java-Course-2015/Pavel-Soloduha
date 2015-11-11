package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.DocBody;

import java.util.List;

/**
 * Created by pavel on 11/10/15.
 */
public interface DocBodyDao {

    List<DocBody> getDocBodyByDocId(Integer documentId);

    void addDocBody(List<DocBody> docBody);

    void deleteDocBodyById(Integer documentId);
}
