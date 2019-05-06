package com.mingcai.edu.modules.oa.utils;

import com.google.gson.Gson;
import com.mingcai.edu.common.utils.SpringContextHolder;
import com.mingcai.edu.modules.oa.dao.eos.OaWxBasicClassificationOfReimbursementDao;
import com.mingcai.edu.modules.oa.entity.eos.OaWxBasicClassificationOfReimbursement;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OaWxBasicClassificationOfReimbursementUtils {

    private static OaWxBasicClassificationOfReimbursementDao dao = SpringContextHolder.getBean(OaWxBasicClassificationOfReimbursementDao.class);

    public static String findlistAll(){
        Map<String,String> map=new TreeMap<String, String>();
        OaWxBasicClassificationOfReimbursement oaWxBasicClassificationOfReimbursemen =new OaWxBasicClassificationOfReimbursement();
        oaWxBasicClassificationOfReimbursemen.setDelFlag("0");
        List<OaWxBasicClassificationOfReimbursement> list = dao.findAllList(oaWxBasicClassificationOfReimbursemen);
        for (OaWxBasicClassificationOfReimbursement item : list) {
            map.put(item.getId(),item.getName());
        }
        Gson gs= new Gson();
        String s = gs.toJson(map);
        return s;
    }
    public static List<OaWxBasicClassificationOfReimbursement> findlistAlls(){
        Map<String,String> map=new TreeMap<String, String>();
        OaWxBasicClassificationOfReimbursement oaWxBasicClassificationOfReimbursemen =new OaWxBasicClassificationOfReimbursement();
        oaWxBasicClassificationOfReimbursemen.setDelFlag("0");
        List<OaWxBasicClassificationOfReimbursement> list = dao.findAllList(oaWxBasicClassificationOfReimbursemen);
        return  list;
    }
    public static OaWxBasicClassificationOfReimbursement get(String id){
        Map<String,String> map=new TreeMap<String, String>();
       OaWxBasicClassificationOfReimbursement list = dao.get(id);
        return  list;
    }
}
