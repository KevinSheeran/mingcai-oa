package com.mingcai.edu.modules.oa.web.eos;


import com.mingcai.edu.common.utils.PageData;
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.modules.oa.entity.eos.*;
import com.mingcai.edu.modules.oa.entity.jd;
import com.mingcai.edu.modules.oa.service.eos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目统计controller
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/sta")
public class OaEosProStatisticsController extends BaseController {

    @Autowired
    OaEosProStartService oaEosProStartService;

    @Autowired
    OaEosProService oaEosProService;

    @Autowired
    OaEosProItemService oaEosProItemService;

    @Autowired
    OaEosProUnService oaEosProUnService;

    @Autowired
    OaEosProStartItemService oaEosProStartItemService;

    @Autowired
    OaWxExtendedSuperService oaWxExtendedSuperService;

    @Autowired
    OaWxExtendedService oaWxExtendedService;

    @Autowired
    OaWxBasicClassificationOfReimbursementService oaWxBasicClassificationOfReimbursementService;

    @Autowired
    OaWxStageOfReimbursementService oaWxStageOfReimbursementService;

    @Autowired
    OaWxBxCorrelationService oaWxBxCorrelationService;



    @RequestMapping(value = {"list",""})
    public  String sta(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("id",id);
        return "modules/oa/eos/oaEosProStartStatistics";
    }

    /**
     * 立项项目项目跟踪财务报表
     * @param id
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "getbycw")
    public  String getbycw(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        OaEosPro oaEosPro = oaEosProService.get(id);
        OaEosProItem oaEosProItem=new OaEosProItem();
        oaEosProItem.setEosId(oaEosPro.getPaNumber());
        oaEosPro.setTotalSum(oaEosProItemService.getbysum(oaEosPro.getPaNumber())==null?0.0:oaEosProItemService.getbysum(oaEosPro.getPaNumber())+oaEosPro.getRealInputEstimation());
        oaEosPro.setOaEosProItemList( oaEosProItemService.selByprolistid(oaEosProItem));
        OaEosProStart oaEosProStart = oaEosProStartService.get(id);
        oaEosProStart.setTotalSum((oaEosProStartItemService.getbysum(id)==null?0.0:oaEosProStartItemService.getbysum(id))+oaEosProStart.getRealInputEstimation());
        System.out.println(oaEosProStart.getRealInputEstimation()+"++++++++++++++++");
        OaEosProStartItem oaEosProStartItem =new OaEosProStartItem();
        oaEosProStartItem.setEosId(oaEosProStart.getId());
        oaEosProStart.setPro(oaEosPro);
        oaEosProStart.setOaEosProStartItemList(oaEosProStartItemService.selByprolistid(oaEosProStartItem));
        model.addAttribute("oaEosProStart",oaEosProStart);
        return "modules/oa/eos/oaEosProFinancial";
    }

    /**
     * 预立项项目项目跟踪财务报表
     * @param id
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "getylxbycw/{id}")
    public  String getylxbycw(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response, Model model){
        OaEosPro oaEosPro = oaEosProService.get(id);
        OaEosProItem oaEosProItem=new OaEosProItem();
        oaEosProItem.setEosId(oaEosPro.getPaNumber());
        oaEosProItem.setEosId(oaEosPro.getId());
       Double a=  oaEosProItemService.getbysum(oaEosPro.getId());
        if (null==a){
        a=0.0;
        }
        oaEosPro.setTotalSum((a==null?0.0:a)+(oaEosPro.getRealInputEstimation()==null?0.0:oaEosPro.getRealInputEstimation()));
        oaEosPro.setOaEosProItemList(oaEosProItemService.selByprolistid(oaEosProItem));
        model.addAttribute("oaEosPro",oaEosPro);
        return "modules/oa/eos/oaEosProFinancialYlx";
    }

    /**
     * 非销售立项项目项目跟踪财务报表
     * @param id
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "getfxsbycw/{id}")
    public  String getfxsbycw(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response, Model model){
        OaEosProUn oaEosProUn = oaEosProUnService.get(id);
        OaEosProStartItem oaEosProItem=new OaEosProStartItem();
        oaEosProItem.setEosId(oaEosProUn.getProNumber());
        oaEosProItem.setEosId(oaEosProUn.getId());
        Double a=  oaEosProStartItemService.getbysum(oaEosProUn.getId());
        if (null==a){
            a=0.0;
        }
        oaEosProUn.setTotalSum((a==null?0.0:a)+(oaEosProUn.getRealInputEstimation()==null?0.0:oaEosProUn.getRealInputEstimation()));
        oaEosProUn.setOaEosProStartItemList(oaEosProStartItemService.selByprolistid(oaEosProItem));
        model.addAttribute("oaEosPro",oaEosProUn);
        return "modules/oa/eos/oaEosProFinancialFxs";
    }
    @ResponseBody
    @RequestMapping(value = "cs/{id}")
    public  Object cs(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response, Model model){
        OaEosPro oaEosPro = oaEosProService.get(id);
        OaEosProItem oaEosProItem=new OaEosProItem();
        oaEosProItem.setEosId(oaEosPro.getPaNumber());
        oaEosPro.setTotalSum(oaEosProItemService.getbysum(oaEosPro.getPaNumber())==null?0.0:oaEosProItemService.getbysum(oaEosPro.getPaNumber())+oaEosPro.getRealInputEstimation());
        oaEosPro.setOaEosProItemList( oaEosProItemService.selByprolistid(oaEosProItem));
        OaEosProStart oaEosProStart = oaEosProStartService.get(id);
        oaEosProStart.setTotalSum((oaEosProStartItemService.getbysum(id)==null?0.0:oaEosProStartItemService.getbysum(id))+oaEosProStart.getRealInputEstimation());
        OaEosProStartItem oaEosProStartItem =new OaEosProStartItem();
        oaEosProStartItem.setEosId(oaEosProStart.getId());
        oaEosProStart.setPro(oaEosPro);
        oaEosProStart.setOaEosProStartItemList(oaEosProStartItemService.selByprolistid(oaEosProStartItem));
        return oaEosProStart;
    }


    @RequestMapping(value = "classjd")
    public  Object Classjd(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        PageData pageData=new PageData();
        pageData.put("proId",id);
        List<PageData> pageData1 = oaWxExtendedService.proCount(pageData);
        List<jd> jdList=new ArrayList<jd>();
        for (PageData keys: pageData1) {
            jd a =new jd();
            a.setFid(keys.getString("fid"));
            a.setName(keys.getString("namea"));
            a.setCost(Double.parseDouble(keys.getString("cost")));
            pageData.put("fid",a.getFid());
            a.setPds(oaWxExtendedService.proCountItem(pageData));
            jdList.add(a);
        }
        model.addAttribute("jd",jdList);
            return "modules/oa/eos/oaEosProClassfin";

    }

    @RequestMapping(value = "classjdylx")
    public  Object Classjdylx(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        PageData pageData=new PageData();
        pageData.put("proId",id);
        List<PageData> pageData1 = oaWxExtendedService.proCount(pageData);
        List<jd> jdList=new ArrayList<jd>();
        for (PageData keys: pageData1) {
            jd a =new jd();
            a.setFid(keys.getString("fid"));
            a.setName(keys.getString("namea"));
            a.setCost(Double.parseDouble(keys.getString("cost")));
            pageData.put("fid",a.getFid());
            a.setPds(oaWxExtendedService.proCountItem(pageData));
            jdList.add(a);
        }
        model.addAttribute("jd",jdList);
        return "modules/oa/eos/oaEosProClassfin";

    }
}
