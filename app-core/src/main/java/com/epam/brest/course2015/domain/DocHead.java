package com.epam.brest.course2015.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by pavel on 11/10/15.
 */
public class DocHead {

    private Integer documentId;

    private Integer documentType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date documentDate;

    private Integer documentPrice;

    public DocHead() {
    }

    public DocHead(Integer documentId, Integer documentType, Date documentDate, Integer documentPrice) {
        this.documentId = documentId;
        this.documentType = documentType;
        this.documentDate = documentDate;
        this.documentPrice = documentPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocHead docHead = (DocHead) o;

//        if (!getDocumentId().equals(docHead.getDocumentId())) return false;
        if (!getDocumentType().equals(docHead.getDocumentType())) return false;
        if (!getDocumentDate().equals(docHead.getDocumentDate())) return false;
        return getDocumentPrice().equals(docHead.getDocumentPrice());

    }

    @Override
    public int hashCode() {
        int result = getDocumentId().hashCode();
        result = 31 * result + getDocumentType().hashCode();
        result = 31 * result + getDocumentDate().hashCode();
        result = 31 * result + getDocumentPrice().hashCode();
        return result;
    }

    public enum DocHeadFields {
        DOCUMENT_ID("documentId"),
        DOCUMENT_TYPE("documentType"),
        DOCUMENT_DATE("documentDate"),
        DOCUMENT_PRICE("documentPrice");


        DocHeadFields(String value) {
            this.value = value;
        }

        private final String value;

        public String getValue() {
            return value;
        }
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public Integer getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Integer documentType) {
        this.documentType = documentType;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public Integer getDocumentPrice() {
        return documentPrice;
    }

    public void setDocumentPrice(Integer documentPrice) {
        this.documentPrice = documentPrice;
    }

    @Override
    public String toString() {
        return "DocHead{" +
                "documentId=" + documentId +
                ", documentType=" + documentType +
                ", documentDate=" + documentDate +
                ", documentPrice=" + documentPrice +
                '}';
    }
}
