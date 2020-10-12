package com.project.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String account;

    private String password;

    private String email;

    private String realname;

    private Date createtime;

    private Integer mobil;

    private String card;

    private Integer jifen;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return realname
     */
    public String getRealname() {
        return realname;
    }

    /**
     * @param realname
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * @return createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * @return mobil
     */
    public Integer getMobil() {
        return mobil;
    }

    /**
     * @param mobil
     */
    public void setMobil(Integer mobil) {
        this.mobil = mobil;
    }

    /**
     * @return card
     */
    public String getCard() {
        return card;
    }

    /**
     * @param card
     */
    public void setCard(String card) {
        this.card = card;
    }

    /**
     * @return jifen
     */
    public Integer getJifen() {
        return jifen;
    }

    /**
     * @param jifen
     */
    public void setJifen(Integer jifen) {
        this.jifen = jifen;
    }
}