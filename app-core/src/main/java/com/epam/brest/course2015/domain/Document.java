package com.epam.brest.course2015.domain;

import java.util.List;

/**
 * Created by pavel on 11/9/15.
 */
public class Document {

    private DocHead docHead;

    private List<DocBody> docBody;

    public Document() {
    }

    public Document(DocHead docHead, List<DocBody> docBody) {
        this.docHead = docHead;
        this.docBody = docBody;
    }

    public DocHead getDocHead() {
        return docHead;
    }

    public void setDocHead(DocHead docHead) {
        this.docHead = docHead;
    }

    public List<DocBody> getDocBody() {
        return docBody;
    }

    public void setDocBody(List<DocBody> docBody) {
        this.docBody = docBody;
    }

    @Override
    public String toString() {
        return "Document{" +
                "docHead=" + docHead +
                ", docBody=" + docBody +
                '}';
    }
}