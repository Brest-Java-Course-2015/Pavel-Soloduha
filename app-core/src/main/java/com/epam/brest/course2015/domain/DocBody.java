package com.epam.brest.course2015.domain;

/**
 * Created by pavel on 11/10/15.
 */
public class DocBody {

    private Integer documentId;

    private Integer detailId;

    private String detailName;

    private Integer detailCount;

    public DocBody() {
    }

    public DocBody(Integer documentId, Integer detailId, String detailName, Integer detailCount) {
        this.documentId = documentId;
        this.detailId = detailId;
        this.detailName = detailName;
        this.detailCount = detailCount;
    }

    public enum DocBodyFields {
        DOCUMENT_ID("documentId"),
        DETAIL_ID("detailId"),
        DETAIL_NAME("detailName"),
        DETAIL_COUNT("detailCount");

        DocBodyFields(String value) {
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

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public Integer getDetailCount() {
        return detailCount;
    }

    public void setDetailCount(Integer detailCount) {
        this.detailCount = detailCount;
    }

    @Override
    public String toString() {
        return "DocBody{" +
                "documentId=" + documentId +
                ", detailId=" + detailId +
                ", detailName='" + detailName + '\'' +
                ", detailCount=" + detailCount +
                '}';
    }
}