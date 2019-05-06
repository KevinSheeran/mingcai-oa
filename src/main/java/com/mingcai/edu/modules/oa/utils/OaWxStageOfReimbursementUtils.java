package com.mingcai.edu.modules.oa.utils;

import com.google.gson.Gson;
import com.mingcai.edu.common.utils.SpringContextHolder;
import com.mingcai.edu.modules.oa.dao.eos.OaWxBxCorrelationDao;
import com.mingcai.edu.modules.oa.dao.eos.OaWxStageOfReimbursementDao;
import com.mingcai.edu.modules.oa.entity.eos.OaWxBxCorrelation;
import com.mingcai.edu.modules.oa.entity.eos.OaWxStageOfReimbursement;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OaWxStageOfReimbursementUtils {
    private static OaWxStageOfReimbursementDao dao = SpringContextHolder.getBean(OaWxStageOfReimbursementDao.class);

    public static String findlist(){
        OaWxStageOfReimbursement OaWxStageOfReimbursement=new OaWxStageOfReimbursement();
        OaWxStageOfReimbursement.setDelFlag("0");
        List<OaWxStageOfReimbursement>  list=dao.findAllList(OaWxStageOfReimbursement);
        Map<String,String> map=new TreeMap<String, String>();
        for (OaWxStageOfReimbursement item  : list) {
            map.put(item.getId(),item.getName());
        }
        Gson gson=new Gson();
        String s = gson.toJson(map);
        return s ;
    }

    public static List<OaWxStageOfReimbursement> findlists(){
        OaWxStageOfReimbursement OaWxStageOfReimbursement=new OaWxStageOfReimbursement();
        OaWxStageOfReimbursement.setDelFlag("0");
        List<OaWxStageOfReimbursement>  list=dao.findAllList(OaWxStageOfReimbursement);
        return  list;
    }

    private static OaWxBxCorrelationDao dao1 = SpringContextHolder.getBean(OaWxBxCorrelationDao.class);
    public  static List<OaWxBxCorrelation> selByProId1(String proid){
       return dao1.selByProId(proid);
    }
}
