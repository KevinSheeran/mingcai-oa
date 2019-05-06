package com.mingcai.edu.common.utils.weixinApi;


import com.google.gson.Gson;
import com.mingcai.edu.modules.oa.entity.wx.OaWxDepartment;
import com.mingcai.edu.modules.oa.entity.wx.OaWxUsers;
import org.activiti.engine.impl.util.json.JSONObject;

import java.util.List;

public class Token {
    private Integer errcode;
    private String errmsg;
    // 接口访问凭证
    private String access_token="cLEOYiLAP4J6NVEOv6uKOTvNU3ObxWwtd9397RwpseEhcM8MHq5h_zO1_rK2SIddEK6PmUkjZ9szimZ37kT0Y59zyvgJlHTZ2NaWbEorY1BVEwrYtsdw4oSoRjN76O8MnU8cYzI9gQTk7ZJHju9un69DOwAq-cR-g1uhEd9mfkpQ1opPvryuzOIJ5EQgC2-AK42ZVt2YwTyTqE93At6aVQ";
    // 凭证有效期，单位：秒
    private int expires_in;
    private List<OaWxDepartment> department;
    private List<WxUsers>   userlist;
    private OaWxUsers user;
    public String CorpId;
    public String UserId;
    public String DeviceId;
    public String user_ticket;
    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<OaWxDepartment> getDepartment() {
        return department;
    }

    public void setDepartment(List<OaWxDepartment> department) {
        this.department = department;
    }

    public List<WxUsers> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<WxUsers> userlist) {
        this.userlist = userlist;
    }



    @Override
    public String toString() {
        return "Token{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                ", department=" + department +
                '}';
    }
}