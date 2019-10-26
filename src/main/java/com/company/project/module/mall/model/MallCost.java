package com.company.project.module.mall.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@Table(name = "M_COST")
public class MallCost {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "MONEY")
    private String money;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return MONEY
     */
    public String getMoney() {
        return money;
    }

    /**
     * @param money
     */
    public void setMoney(String money) {
        this.money = money;
    }
}