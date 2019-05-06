package com.mingcai.edu.modules.oa.utils;

import com.mingcai.edu.common.utils.excel.annotation.ExcelField;

import java.util.Date;

public class FinanceExcel {

    private  Double money;

    private  String proName;

    private  String userName;

    private Date createDate;

    private String state ;

    private  String type;

    private  String proid;  //项目id

    private  String  paProid; //预立项编号

    private  String proProid;//立项编号

    @ExcelField(title="项目id", type=1, align=2, sort=0)
    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    @ExcelField(title="项目名", type=1, align=2, sort=1)
    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    @ExcelField(title="预立项编号", type=1, align=2, sort=2)
    public String getPaProid() {
        return paProid;
    }

    public void setPaProid(String paProid) {
        this.paProid = paProid;
    }
    @ExcelField(title="立项编号", type=1, align=2, sort=3)
    public String getProProid() {
        return proProid;
    }

    public void setProProid(String proProid) {
        this.proProid = proProid;
    }

    @ExcelField(title="报销人", type=1, align=2, sort=4)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @ExcelField(title="报销金额", type=1, align=2, sort=5)
    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @ExcelField(title="申请时间", type=1, align=2, sort=6)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @ExcelField(title="报销状态", type=1, align=2, sort=7)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @ExcelField(title="报销类型", type=1, align=2, sort=8)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
