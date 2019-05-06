package com.mingcai.edu.test.dao;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.utils.PageData;
import com.mingcai.edu.common.utils.SpringContextHolder;
import com.mingcai.edu.modules.oa.dao.eos.OaWxExtendedDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class) // 整合
@ActiveProfiles("dev")
@ContextConfiguration(locations = {"/spring-context.xml"})
@Transactional    // extends AbstractTransactionalJUnit4SpringContextTests
public class testa {


  /*  @Test
    @Transactional(readOnly = false)
    @Rollback(false)    //标明使用完此方法后事务不回滚,true时为回滚
    public void SyschronizeArea() {
        OaWxExtendedDao dao = SpringContextHolder.getBean(OaWxExtendedDao.class);
        try {
            PageData pd=new PageData();
            List<PageData> procount = dao.procount(pd);
            TreeMap<String,A> map=new TreeMap<String,A>();
            for(PageData pdata:procount){

                String name=pdata.getString("namea");
                if(map.get(name)==null) {
                    A a=new A();
                    a.name=name;
                    a.cost=Double.parseDouble(pdata.getString("cost"));
                    List<PageData> pds=new ArrayList<PageData>();
                    pds.add(pdata);
                    a.pds=pds;
                    a.fid=pdata.getString("fid");
                    a.gid=pdata.getString("gid");
                    map.put(a.name, a);
                }else{
                    A a=map.get(name);
                    a.name=name;
                    a.pds.add(pdata);
                    a.fid=pdata.getString("fid");
                    a.gid=pdata.getString("gid");
                    a.cost+=Double.parseDouble(pdata.getString("cost"));
                    map.put(a.name,a);
                }
            }
            for(String key:map.keySet()){
                A a=map.get(key);
                List<PageData> pds=a.pds;
                TreeMap<String,B> mapb=new TreeMap<String,B>();
                for(PageData obj:pds){
                    String name=obj.getString("namea");
                    if(mapb.get("name")==null){
                        B b=new B();
                        b.name=name;
                        b.cost=Double.parseDouble(obj.getString("cost"));
                        List<PageData> pdc=new ArrayList<PageData>();
                        pdc.add(obj);
                        b.pds=pdc;
                        b.fid=obj.getString("fid");
                        b.gid=obj.getString("gid");
                        mapb.put(name,b);
                    }else{
                        B b=mapb.get(name);
                        b.name=name;
                        b.pds.add(obj);
                        b.fid=obj.getString("fid");
                        b.gid=obj.getString("gid");
                        b.cost+=Double.parseDouble(obj.getString("cost"));
                        mapb.put(name,b);
                    }
                }
                a.mapb=mapb;
                System.out.println(a.cost);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @Test
    @Transactional(readOnly = false)
    @Rollback(false)    //标明使用完此方法后事务不回滚,true时为回滚
    public  void main(){
        TreeMap<String,A> map=new TreeMap<String, A>();
        OaWxExtendedDao dao = SpringContextHolder.getBean(OaWxExtendedDao.class);
        PageData pageData=new PageData();
        pageData.put("proId","b6605757f7644f8bacfb6b42b1207b83");
        List<PageData> pageData1 = dao.proCount(pageData);
        for (PageData keys: pageData1) {
            A a =new A();
            a.fid=keys.getString("fid");
            a.name=keys.getString("namea");
            a.cost=Double.parseDouble(keys.getString("cost"));
            pageData.put("fid",a.fid);
           a.pds=dao.proCountItem(pageData);
            System.out.println(a);
        }
    }



}
class base{
    public String fid;
    public  String gid;
}
class A extends base{
    public String name;
    public Double cost=0.0;
    public List<PageData> pds;
}
class B extends base{
    public String name;
    public Double cost=0.0;
}