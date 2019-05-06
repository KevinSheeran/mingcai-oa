package com.mingcai.edu.common.utils.weixinApi;

import com.mingcai.edu.common.config.Global;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.dao.eos.OaEosProStartItemDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosPro;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProItem;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProStartItem;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProUn;
import com.mingcai.edu.modules.oa.entity.wx.OaWxDepartment;
import com.mingcai.edu.modules.oa.entity.wx.OaWxUsers;
import com.mingcai.edu.modules.oa.service.eos.OaEosProItemService;
import com.mingcai.edu.modules.oa.service.eos.OaEosProService;
import com.mingcai.edu.modules.oa.service.eos.OaEosProUnService;
import com.mingcai.edu.modules.oa.service.wx.OaWxDepartmentService;
import com.mingcai.edu.modules.oa.service.wx.OaWxUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
@Service
@Lazy(false)
public class GlobalService {
    @Autowired
    OaWxUsersService oaWxUsersService;
    @Autowired
    OaEosProService  oaEosProService;
    @Autowired
    private OaEosProItemService oaEosProItemService;

    public void InitGloabal() {
        usertype();
        baoxiao();
        itemlist();
    }

//    /**
//     * 设置定时任务600秒启动一次该方法
//     */
//    @Scheduled(cron="0/600 * *  * * ? ")
    public void baoxiao() {
        oaEosProService.getAllPro();
    }

    /**
     * 查询用户
     */
    private void usertype() {
        new TokenService().TokenValue();//同步wx token
        List<OaWxUsers> oalist = oaWxUsersService.findList(new OaWxUsers());
        TreeMap<String, List<OaWxUsers>> wxusermap = new TreeMap<String, List<OaWxUsers>>();
        for (OaWxUsers owu : oalist) {
            String s = owu.getPinyin().substring(0, 1).toUpperCase();
            List<OaWxUsers> maplist = wxusermap.get(s);
            if (maplist == null) {
                maplist = new ArrayList<OaWxUsers>();
                maplist.add(owu);
                wxusermap.put(s, maplist);
            } else {
                maplist.add(owu);
            }
        }
        Global.wxusermap = wxusermap;


    }
    
public  void itemlist(){
  Global.setArraylistitem(oaEosProItemService.findList(null));
}
}
