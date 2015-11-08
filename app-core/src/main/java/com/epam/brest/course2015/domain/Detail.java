package com.epam.brest.course2015.domain;

/**
 * Created by pavel on 11/8/15.
 */

public class Detail {

    private Integer detailId;

    private String detailName;

    public Detail() {
    }

    public Detail(Integer detailId) {
        this.detailId = detailId;
    }

    public Detail(String detailName) {
        this.detailName = detailName;
    }


    public Detail(Integer detailId, String detailName) {
        this.detailName = detailName;
        this.detailId = detailId;
    }

    public enum DetailFields {
        DETAIL_ID("detailId"),
        DETAIL_NAME("detailName");

        DetailFields(String value) {
            this.value = value;
        }

        private final String value;

        public String getValue() {
            return value;
        }
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

    @Override
    public String toString() {
        return "Detail{" +
                "detailId=" + detailId +
                ", detailName='" + detailName + '\'' +
                '}';
    }
}