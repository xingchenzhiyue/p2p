package com.qf.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: yunwenbo
 * \* Date: 2017/8/23
 * \* Time: 16:50
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Res implements Serializable {

    private int id;
    private String text;
    private String url;
//    指定转换为json时使用的key的名称
    @JSONField(name="_parentId")
    private int pid;

    private String perms;

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    private Set<Object> i=new HashSet<Object>();

    public Set<Object> getI() {
        return i;
    }

    public void setI(Set<Object> i) {
        this.i = i;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //存放子节点
    private Set<Res> children=new HashSet<Res>();

    public Set<Res> getChildren() {
        return children;
    }

    public void setChildren(Set<Res> children) {
        this.children = children;
    }

    public Res() {
    }

    public Res(int id, String text, String url, int pid, String perms, Set<Object> i, Set<Res> children) {
        this.id = id;
        this.text = text;
        this.url = url;
        this.pid = pid;
        this.perms = perms;
        this.i = i;
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Res{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", pid=" + pid +
                ", perms='" + perms + '\'' +
                ", i=" + i +
                ", children=" + children +
                '}';
    }
}