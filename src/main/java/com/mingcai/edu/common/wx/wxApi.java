package com.mingcai.edu.common.wx;

import com.mingcai.edu.common.utils.weixinApi.TokenService;

public class wxApi {
    public final static int OK = 0;
    public final static int GETTOKEN = 1;//获取公司 acces_token
    public final static int DepartmentList = 2;//获取部门列表
    public final static int DepartmentUserList = 3;//获取部门用户列表
    public final static int GETUserInfo = 4;//获取用户信息
    public final static int SendMsgToUser= 5;//推送用户消息
    public final static int GetUserByCode= 6;//code用户消息
    private int code;
    public static String getMessage(int code,String param) {
        String token= "";
        if(TokenService.token!=null){
            token= "access_token="+TokenService.token.getAccess_token();
        }
        switch (code) {
            case GETTOKEN:
                return "https://qyapi.weixin.qq.com/cgi-bin/gettoken?"+param;
            case DepartmentList:
                return "https://qyapi.weixin.qq.com/cgi-bin/department/list?"+token+"&"+param;
            case DepartmentUserList:
                return "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?"+token+"&"+param;
            case GETUserInfo:
                return "https://qyapi.weixin.qq.com/cgi-bin/user/get?"+token+"&"+param;
            case SendMsgToUser:
                return "https://qyapi.weixin.qq.com/cgi-bin/message/send?"+token;
            case GetUserByCode:
                return "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?"+token+"&"+param;
            default:
                return null; // cannot be
        }
    }
}
