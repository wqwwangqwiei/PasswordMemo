package com.passwordmemo.sky.passwordmemo.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "SYN_DATA".
 */
public class SynData {

    private Long id;
    private String userpasswordId;
    private Integer adminId;
    private String mark;

    public SynData() {
    }

    public SynData(Long id) {
        this.id = id;
    }

    public SynData(Long id, String userpasswordId, Integer adminId, String mark) {
        this.id = id;
        this.userpasswordId = userpasswordId;
        this.adminId = adminId;
        this.mark = mark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserpasswordId() {
        return userpasswordId;
    }

    public void setUserpasswordId(String userpasswordId) {
        this.userpasswordId = userpasswordId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

}