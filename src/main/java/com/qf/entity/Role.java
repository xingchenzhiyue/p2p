package com.qf.entity;

import java.io.Serializable;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yunwenbo
 * \* Date: 2017/8/24
 * \* Time: 14:32
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Role implements Serializable{

    private int rid;
    private String role_name;

    public Role() {
    }

    public Role(int rid, String role_name) {
        this.rid = rid;
        this.role_name = role_name;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}