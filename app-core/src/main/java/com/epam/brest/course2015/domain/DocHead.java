package com.epam.brest.course2015.domain;

import java.util.Date;

/**
 * Created by pavel on 11/10/15.
 */
public class DocHead {

    private Integer documentId;

    private Integer documentType;

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

    public enum DetailFields {
        DOCUMENT_ID("documentId"),
        DOCUMENT_TYPE("documentType"),
        DOCUMENT_DATE("documentId"),
        DOCUMENT_PRICE("documentId");


        DetailFields(String value) {
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
