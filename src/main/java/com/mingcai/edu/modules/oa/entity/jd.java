package com.mingcai.edu.modules.oa.entity;

import com.mingcai.edu.common.utils.PageData;

import java.util.List;

public class jd extends base {
    private String name;
    private Double cost=0.0;
    private List<PageData> pds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public List<PageData> getPds() {
        return pds;
    }

    public void setPds(List<PageData> pds) {
        this.pds = pds;
    }
}
