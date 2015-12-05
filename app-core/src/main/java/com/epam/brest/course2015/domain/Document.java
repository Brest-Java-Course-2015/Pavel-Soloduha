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

    public Document(DocHead docHead) {
        this.docHead = docHead;
    }

    public Document(DocHead docHead, List<DocBody> docBody) {
        this.docHead = docHead;
        this.docBody = docBody;
    }

    public Document(Document document) {
        this.docHead = document.getDocHead();
        this.docBody = document.getDocBody();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (getDocHead() != null ? !getDocHead().equals(document.getDocHead()) : document.getDocHead() != null)
            return false;
        return !(getDocBody() != null ? !getDocBody().equals(document.getDocBody()) : document.getDocBody() != null);

    }

    @Override
    public int hashCode() {
        int result = getDocHead() != null ? getDocHead().hashCode() : 0;
        result = 31 * result + (getDocBody() != null ? getDocBody().hashCode() : 0);
        return result;
    }
}