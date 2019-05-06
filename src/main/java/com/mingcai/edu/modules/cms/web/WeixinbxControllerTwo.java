package com.mingcai.edu.modules.cms.web;

import com.alibaba.druid.sql.visitor.functions.If;
import com.mingcai.edu.common.config.Global;
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.utils.DateUtils;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.common.utils.excel.ExportExcel;
import com.mingcai.edu.common.utils.excel.annotation.ExcelField;
import com.mingcai.edu.common.utils.excel.test;
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.common.web.QiniuUpload;
import com.mingcai.edu.modules.cms.entity.Site;
import com.mingcai.edu.modules.cms.utils.CmsUtils;
import com.mingcai.edu.modules.oa.dao.eos.OaWxExtendedDao;
import com.mingcai.edu.modules.oa.entity.eos.*;
import com.mingcai.edu.modules.oa.entity.wx.OaWxDepartment;
import com.mingcai.edu.modules.oa.service.eos.*;
import com.mingcai.edu.modules.oa.service.wx.OaWxDepartmentService;
import com.mingcai.edu.modules.oa.utils.FinanceExcel;
import com.mingcai.edu.modules.sys.dao.UserDao;
import com.mingcai.edu.modules.sys.entity.Role;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 基本报销controller
 */
@Controller
@RequestMapping(value = "${OaPath}/weixin/two")
public class WeixinbxControllerTwo extends BaseController {

    @Autowired
    private OaWxExtendedService oaWxExtendedService;

    @Autowired
    OaWxExtendedSuperService oaWxExtendedSuperService;

    @Autowired
    private OaWxBxCorrelationService oaWxBxCorrelationService;

    @Autowired
    private  OaWxBxCorrelationSuperService oaWxBxCorrelationSuperService;

    @Autowired
    private OaWxExtendedDao oaWxExtendedDao;

    @Autowired
    OaEosFlowItemService oaEosFlowItemService;

    @Autowired
    OaEosProService oaEosProService;

    @Autowired
    OaEosProUnService oaEosProUnService;

    @Autowired
    OaWxDepartmentService oaWxDepartmentService;

    @Autowired
    UserDao userDao;
    /**
     * 其他报销
     */
    @RequestMapping("other")
    public String Other(String type, String types, @RequestParam(required = false) String code, HttpServletRequest request, HttpServletResponse response, Model model){
        Other(type,types,code,model);
        return "modules/cms/front/themes/weixin/baoxiao/jiben/frontBaoXiaoAddOther";
    }
    public void Other(String type, String types,String code, Model model){
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        site.setTheme("weixin");
        User manager=null;
        User management=null;
        if (types.equals("ylx")||types.equals("lx")){
            manager = UserUtils.get(pzid);
            management = UserUtils.get(cmh);
        }else {
            manager = UserUtils.get(fzid);
            management = UserUtils.get(cmh);
        }
        model.addAttribute("manager",manager);
        model.addAttribute("management",management);
        model.addAttribute("site", site);
        model.addAttribute("code", code);
        model.addAttribute("type", type);
        model.addAttribute("types", types);
        model.addAttribute("gsons", Global.wxusermap);
    }
    /**
     * 基本报销添加
     * @param type
     * @param types
     * @param code
     * @param files
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("add")
    public  String  add(String type, String types, @RequestParam(required = false) String code,@RequestParam(required = false) MultipartFile[] files, HttpServletRequest request, HttpServletResponse response, Model model){
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        site.setTheme("weixin");
        List feleimg =new ArrayList();
        try {
                     /*      for(MultipartFile file:files){
                           String key= QiniuUpload.upload(file.getInputStream(),AK,SK,BUCKET);
                           feleimg.add( qiniuRUL+key);
                    }*/
            oaWxExtendedService.baoxiaosave(request, response,feleimg);
            return "redirect:/oa/weixin";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "你的申请信息有误请重新申请");
            model.addAttribute("gsons", Global.wxusermap);
            Other(type,types,code,model);
            return "modules/cms/front/themes/weixin/baoxiao/jiben/frontBaoXiaoAddOther";
        }
    }
    /**
     * 部门报销添加
     * @param type
     * @param types
     * @param code
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("bmadd")
    public  String  bmadd(String type, String types, @RequestParam(required = false) String code, HttpServletRequest request, HttpServletResponse response, Model model){
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        site.setTheme("weixin");
        model.addAttribute("site", site);
        model.addAttribute("code", code);
        model.addAttribute("type", type);
        model.addAttribute("types", types);
        try {
            oaWxExtendedService.bmbaoxiaosave(request,response);
            return "redirect:/oa/weixin";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "你的申请信息有误请重新申请");
            model.addAttribute("gsons", Global.wxusermap);
            Other(type,types,code,model);
            return "modules/cms/front/themes/weixin/baoxiao/jiben/frontBaoXiaoAddOther";
        }
    }
    /**
     * 个人基本报销查询
     * @param code
     * @param type
     * @param request
     * @param response
     * @param model
     * @param types
     * @return
     */
    @ResponseBody
    @RequestMapping("selby")
    public Object ylx1(@RequestParam(required = false) String code, String type, HttpServletRequest request, HttpServletResponse response, Model model, String types) {
        User user = UserUtils.getUser();
        OaWxBxCorrelationSuper oaWxBxCorrelationSuper=new OaWxBxCorrelationSuper();
        oaWxBxCorrelationSuper.setCreateBy(user);
        Page<OaWxBxCorrelationSuper> page = oaWxBxCorrelationSuperService.findPage(new Page<OaWxBxCorrelationSuper>(request, response), oaWxBxCorrelationSuper);
        List<Map> mapList = null;
        for (OaWxBxCorrelationSuper ite: page.getList()) {
            mapList=new ArrayList<Map>();
            double mocey = 0;
            OaWxExtendedSuper oa=new OaWxExtendedSuper();
            oa.setId(ite.getExtendedSuperId());
            OaWxExtended oaWxExtended=new OaWxExtended();
            oaWxExtended.setOaWxExtendedSuper(oa);
           List<OaWxExtended> owelist=oaWxExtendedService.findBySuperProId(oaWxExtended);
            for (OaWxExtended i :owelist){
                mocey += i.getCost();
            }
            TreeMap<String, Object> map = new TreeMap<String, Object>();
            map.put("id", ite.getId());
            map.put("user", UserUtils.get(ite.getCreateBy().getId()));
            if (ite.getOaWxExtendedSuper().getProItemType().equals("ylx")) {
                ite.setPro( oaEosProService.get(ite.getProId()));
                ite.getOaWxExtendedSuper().setPro( oaEosProService.get(ite.getProId()));
                map.put("proName", ite.getPro().getName());
            } else if (ite.getOaWxExtendedSuper().getProItemType().equals("lx")) {
                ite.setPro( oaEosProService.get(ite.getProId()));
                ite.getOaWxExtendedSuper().setPro( oaEosProService.get(ite.getProId()));
                map.put("proName", ite.getPro().getName());
            } else if (ite.getOaWxExtendedSuper().getProItemType().equals("bm")) {
              ite.getOaWxExtendedSuper().setOaWxDepartment(oaWxDepartmentService.get(ite.getProId()));
                map.put("proName", ite.getOaWxExtendedSuper().getOaWxDepartment().getName());
            } else if (ite.getOaWxExtendedSuper().getProItemType().equals("fxs")) {
                ite.setUn( oaEosProUnService.get(ite.getProId()));
                ite.getOaWxExtendedSuper().setOaEosProUn( oaEosProUnService.get(ite.getProId()));
                map.put("proName", ite.getOaWxExtendedSuper().getOaEosProUn().getName());
            }
            map.put("cost", mocey);
            map.put("state", ite.getOaWxExtendedSuper().getState().equals("0") ? "待审核" : ite.getOaWxExtendedSuper().getState().equals("1") ? "审核通过" : ite.getOaWxExtendedSuper().getState().equals("-1") ? "已驳回" : ite.getOaWxExtendedSuper().getState().equals("2") ? "已撤销" : "出现异常请联系系统管理员");
            map.put("crdate", DateUtils.formatDateTimeF(ite.getCreateDate()));
            map.put("appropriation", ite.getOaWxExtendedSuper().getAppropriation());
            map.put("type", ite.getOaWxExtendedSuper().getProItemType());
            mapList.add(map);
            ite.setMap(mapList);
        }
        return page;
    }
    /**
     * 基本报销未审核信息查询
     * @param code
     * @param type
     * @param request
     * @param response
     * @param model
     * @param types
     * @return
     */
    @ResponseBody
    @RequestMapping("selbysh")
    public Object ylx1sh(@RequestParam(required = false) String code, String type, HttpServletRequest request, HttpServletResponse response, Model model, String types) {
        User user = UserUtils.getUser();
        OaWxBxCorrelationSuper oaWxBxCorrelationSuper=new OaWxBxCorrelationSuper();
        oaWxBxCorrelationSuper.setCreateBy(user);
        Page<OaWxBxCorrelationSuper> page = oaWxBxCorrelationSuperService.findUntreatedList(new Page<OaWxBxCorrelationSuper>(request, response), oaWxBxCorrelationSuper);
        List<Map> mapList = null;
        for (OaWxBxCorrelationSuper ite: page.getList()) {
            mapList=new ArrayList<Map>();
            double mocey = 0;
            OaWxBxCorrelation OaWxBxCorrelation=new OaWxBxCorrelation();
            OaWxBxCorrelation.setOaWxBxCorrelationSuper(ite);
            List<OaWxBxCorrelation> bySuperId = oaWxBxCorrelationService.findBySuperId(OaWxBxCorrelation);
            for (OaWxBxCorrelation i :bySuperId){
                mocey += i.getEx().getCost();
            }
            TreeMap<String, Object> map = new TreeMap<String, Object>();
            map.put("id", ite.getId());
            map.put("user", UserUtils.get(ite.getCreateBy().getId()));
            if (ite.getOaWxExtendedSuper().getProItemType().equals("ylx")) {
                ite.setPro( oaEosProService.get(ite.getProId()));
                ite.getOaWxExtendedSuper().setPro( oaEosProService.get(ite.getProId()));
                map.put("proName", ite.getPro().getName());
            } else if (ite.getOaWxExtendedSuper().getProItemType().equals("lx")) {
                ite.setPro( oaEosProService.get(ite.getProId()));
                ite.getOaWxExtendedSuper().setPro( oaEosProService.get(ite.getProId()));
                map.put("proName", ite.getPro().getName());
            } else if (ite.getOaWxExtendedSuper().getProItemType().equals("bm")) {
                ite.getOaWxExtendedSuper().setOaWxDepartment(oaWxDepartmentService.get(ite.getProId()));
                map.put("proName", ite.getOaWxExtendedSuper().getOaWxDepartment().getName());
            } else if (ite.getOaWxExtendedSuper().getProItemType().equals("fxs")) {
                ite.setUn( oaEosProUnService.get(ite.getProId()));
                ite.getOaWxExtendedSuper().setOaEosProUn( oaEosProUnService.get(ite.getProId()));
                map.put("proName", ite.getOaWxExtendedSuper().getOaEosProUn().getName());
            }
            map.put("cost", mocey);
            map.put("state", ite.getOaWxExtendedSuper().getState().equals("0") ? "待审核" : ite.getOaWxExtendedSuper().getState().equals("1") ? "审核通过" : ite.getOaWxExtendedSuper().getState().equals("-1") ? "已驳回" : ite.getOaWxExtendedSuper().getState().equals("2") ? "已撤销" : "出现异常请联系系统管理员");
            map.put("crdate", DateUtils.formatDateTimeF(ite.getCreateDate()));
            map.put("appropriation", ite.getOaWxExtendedSuper().getAppropriation());
            map.put("type", ite.getOaWxExtendedSuper().getProItemType());
            mapList.add(map);
            ite.setMap(mapList);
        }
        return page;
    }


    /**
     * 基本报销财务查询
     * @param code
     * @param type
     * @param request
     * @param response
     * @param model
     * @param types
     * @return
     */
    @ResponseBody
    @RequestMapping("selbycw")
    public Object ylx1cw(@RequestParam(required = false) String code, String type, HttpServletRequest request, HttpServletResponse response, Model model, String types) {
        User user = UserUtils.getUser();
        OaWxBxCorrelationSuper oaWxBxCorrelationSuper=new OaWxBxCorrelationSuper();
        oaWxBxCorrelationSuper.setCreateBy(user);
        Page<OaWxBxCorrelationSuper> page = oaWxBxCorrelationSuperService.Finance(new Page<OaWxBxCorrelationSuper>(request, response), oaWxBxCorrelationSuper);
        List<Map> mapList = null;
        List<Role> rols= user.getRoleList();
        boolean bool=false;
        for(Role role:rols){
            if(role.getName().equals(financeAuditRole)){//财务
                bool =true;
                break;
            }
        }
        for (OaWxBxCorrelationSuper ite: page.getList()) {
            mapList=new ArrayList<Map>();
            double mocey = 0;
            OaWxBxCorrelation OaWxBxCorrelation=new OaWxBxCorrelation();
            OaWxBxCorrelation.setOaWxBxCorrelationSuper(ite);
            OaWxExtendedSuper oa=new OaWxExtendedSuper();
            oa.setId(ite.getExtendedSuperId());
            OaWxExtended oaWxExtended=new OaWxExtended();
            oaWxExtended.setOaWxExtendedSuper(oa);
            List<OaWxExtended> owelist=oaWxExtendedService.findBySuperProId(oaWxExtended);
            for (OaWxExtended i :owelist){
                mocey += i.getCost();
            }
            TreeMap<String, Object> map = new TreeMap<String, Object>();
            map.put("id", ite.getId());
            map.put("user", UserUtils.get(ite.getCreateBy().getId()));
            if (ite.getOaWxExtendedSuper().getProItemType().equals("ylx")) {
                ite.setPro( oaEosProService.get(ite.getProId()));
                ite.getOaWxExtendedSuper().setPro( oaEosProService.get(ite.getProId()));
                map.put("proName", ite.getPro().getName());
            } else if (ite.getOaWxExtendedSuper().getProItemType().equals("lx")) {
                ite.setPro( oaEosProService.get(ite.getProId()));
                ite.getOaWxExtendedSuper().setPro( oaEosProService.get(ite.getProId()));
                map.put("proName", ite.getPro().getName());
            } else if (ite.getOaWxExtendedSuper().getProItemType().equals("bm")) {
                ite.getOaWxExtendedSuper().setOaWxDepartment(oaWxDepartmentService.get(ite.getProId()));
                map.put("proName", ite.getOaWxExtendedSuper().getOaWxDepartment().getName());
            } else if (ite.getOaWxExtendedSuper().getProItemType().equals("fxs")) {
                ite.setUn( oaEosProUnService.get(ite.getProId()));
                ite.getOaWxExtendedSuper().setOaEosProUn( oaEosProUnService.get(ite.getProId()));
                map.put("proName", ite.getOaWxExtendedSuper().getOaEosProUn().getName());
            }
            map.put("cost", mocey);
            map.put("state", ite.getOaWxExtendedSuper().getState().equals("0") ? "待审核" : ite.getOaWxExtendedSuper().getState().equals("1") ? "审核通过" : ite.getOaWxExtendedSuper().getState().equals("-1") ? "已驳回" : ite.getOaWxExtendedSuper().getState().equals("2") ? "已撤销" : "出现异常请联系系统管理员");
            map.put("crdate", DateUtils.formatDateTimeF(ite.getCreateDate()));
            map.put("appropriation", ite.getOaWxExtendedSuper().getAppropriation());
            map.put("type", ite.getOaWxExtendedSuper().getProItemType());
            map.put("role",bool);

            mapList.add(map);
            ite.setMap(mapList);
        }
        return page;
    }
    /**
     * 已审核报销信息查询
     * @param code
     * @param type
     * @param request
     * @param response
     * @param model
     * @param types
     * @return
     */
    @ResponseBody
    @RequestMapping("selbyysh")
    public Object ylx1ysh(@RequestParam(required = false) String code, String type, HttpServletRequest request, HttpServletResponse response, Model model, String types) {
        User user = UserUtils.getUser();
        OaWxBxCorrelationSuper oaWxBxCorrelationSuper=new OaWxBxCorrelationSuper();
        oaWxBxCorrelationSuper.setCreateBy(user);
        Page<OaWxBxCorrelationSuper> page = oaWxBxCorrelationSuperService.findProcessedList(new Page<OaWxBxCorrelationSuper>(request, response), oaWxBxCorrelationSuper);
        List<Map> mapList = null;
        for (OaWxBxCorrelationSuper ite: page.getList()) {
            mapList=new ArrayList<Map>();
            double mocey = 0;
            OaWxBxCorrelation OaWxBxCorrelation=new OaWxBxCorrelation();
            OaWxBxCorrelation.setOaWxBxCorrelationSuper(ite);
            OaWxExtendedSuper oa=new OaWxExtendedSuper();
            oa.setId(ite.getExtendedSuperId());
            OaWxExtended oaWxExtended=new OaWxExtended();
            oaWxExtended.setOaWxExtendedSuper(oa);
            List<OaWxExtended> owelist=oaWxExtendedService.findBySuperProId(oaWxExtended);
            for (OaWxExtended i :owelist){
                mocey += i.getCost();
            }
            TreeMap<String, Object> map = new TreeMap<String, Object>();
            map.put("id", ite.getId());
            map.put("user", UserUtils.get(ite.getCreateBy().getId()));
            if (ite.getOaWxExtendedSuper().getProItemType().equals("ylx")) {
                ite.setPro( oaEosProService.get(ite.getProId()));
                ite.getOaWxExtendedSuper().setPro( oaEosProService.get(ite.getProId()));
                map.put("proName", ite.getPro().getName());
            } else if (ite.getOaWxExtendedSuper().getProItemType().equals("lx")) {
                ite.setPro( oaEosProService.get(ite.getProId()));
                ite.getOaWxExtendedSuper().setPro( oaEosProService.get(ite.getProId()));
                map.put("proName", ite.getPro().getName());
            } else if (ite.getOaWxExtendedSuper().getProItemType().equals("bm")) {
                ite.getOaWxExtendedSuper().setOaWxDepartment(oaWxDepartmentService.get(ite.getProId()));
                map.put("proName", ite.getOaWxExtendedSuper().getOaWxDepartment().getName());
            } else if (ite.getOaWxExtendedSuper().getProItemType().equals("fxs")) {
                ite.setUn( oaEosProUnService.get(ite.getProId()));
                ite.getOaWxExtendedSuper().setOaEosProUn( oaEosProUnService.get(ite.getProId()));
                map.put("proName", ite.getOaWxExtendedSuper().getOaEosProUn().getName());
            }
            map.put("cost", mocey);
            map.put("state", ite.getOaWxExtendedSuper().getState().equals("0") ? "待审核" : ite.getOaWxExtendedSuper().getState().equals("1") ? "审核通过" : ite.getOaWxExtendedSuper().getState().equals("-1") ? "已驳回" : ite.getOaWxExtendedSuper().getState().equals("2") ? "已撤销" : "出现异常请联系系统管理员");
            map.put("crdate", DateUtils.formatDateTimeF(ite.getCreateDate()));
                map.put("appropriation", ite.getOaWxExtendedSuper().getAppropriation());
            map.put("type", ite.getOaWxExtendedSuper().getProItemType());
            mapList.add(map);
            ite.setMap(mapList);
        }
        return page;
    }

    /**
     * 跳转到相对应的报销信息页面页面通过ajax进行相关查询
     * @param model
     * @param types
     * @return
     */
    @RequestMapping("tz")
    public String tz(Model model, String types) {
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        site.setTheme("weixin");
        model.addAttribute("site", site);
        model.addAttribute("type", types);
        if (types.equals("jb")) {
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/jiben/frontbxtableOther";
        }else if (types.equals("cw")){
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/jiben/frontExamineFinance";
        }else if (types.equals("sh")){
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/jiben/frontExamineOther";
        }else {
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/jiben/frontAuditedOther";
        }
    }

    /**
     * 报销详情查询，审核人员可以在此页面进行审核
     * @param idss
     * @param type
     * @param request
     * @param cost
     * @param sh
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("Details")
    public String Details(String idss, @RequestParam(required = false) String type, HttpServletRequest request, String cost,String sh, HttpServletResponse response, Model model){
        boolean success = Boolean.parseBoolean(request.getParameter("success"));
        if (success) {
            model.addAttribute("success", "success");
        }
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        site.setTheme("weixin");
        model.addAttribute("site", site);
        User user = UserUtils.getUser();
        OaWxBxCorrelationSuper oaWxBxCorrelationSuper = oaWxBxCorrelationSuperService.get1(idss);
        OaWxExtended oaWxExtended =new OaWxExtended();
        oaWxExtended.setOaWxBxCorrelationSuper(oaWxBxCorrelationSuper);
        oaWxBxCorrelationSuper.setUn(oaEosProUnService.get(oaWxBxCorrelationSuper.getProId()));
        oaWxBxCorrelationSuper.setOaWxDepartment(oaWxDepartmentService.get(oaWxBxCorrelationSuper.getProId()));
        List<OaWxExtended> list = oaWxExtendedDao.findBySuperId(oaWxExtended);
        oaWxBxCorrelationSuper.setOaWxBxCorrelations(list);
        OaEosFlow oeflow = new OaEosFlow();
        oeflow.setEosId(oaWxBxCorrelationSuper.getOaWxExtendedSuper().getId());
        OaEosFlowItem item = new OaEosFlowItem();
        model.addAttribute("gsons", Global.wxusermap);
        if (oaWxBxCorrelationSuper.getOaWxExtendedSuper().getFlow() != null) {
            item.setFlowId(oaWxBxCorrelationSuper.getOaWxExtendedSuper().getFlow().getId());
            TreeMap<Integer, List<OaEosFlowItem>> maps = oaEosFlowItemService.findListByFlowId(item);
            model.addAttribute("flowmap", maps);
            model.addAttribute("flow", new OaEosFlow());

            if (user != null) {

                   oaEosFlowItemService.FlowUsers(maps); //找到要处理的流程

                List<OaEosFlowItem> oeflsit = oaEosFlowItemService.FlowUsers(maps);
                for (OaEosFlowItem oflow : oeflsit) {
                    if (oflow.getUser().getId().equals(user.getUserId())) {
                        model.addAttribute("flow", oflow);
                    }
                }
            }
        }
        oaWxBxCorrelationSuper.getOaWxExtendedSuper().setUpdateBy(userDao.get(oaWxBxCorrelationSuper.getOaWxExtendedSuper().getUpdateBy().getId()));
        model.addAttribute("map", oaWxBxCorrelationSuper);
        model.addAttribute("user",UserUtils.get(oaWxBxCorrelationSuper.getCreateBy().getId()));
        model.addAttribute("cost", cost);
        model.addAttribute("types", type);
        model.addAttribute("site", site);
        model.addAttribute("sh",sh);
        if ("bm".equals(type)) {
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/jiben/frontBaoxiaoDetailsOtherBm";
        } else if ("fxs".equals(type)) {
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/jiben/frontBaoxiaoDetailsOtherFxs";
        } else if ("ylx".equals(type)){
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/jiben/frontBaoxiaoDetailsOtherYlx";
        }else if ("lx".equals(type)){
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/jiben/frontBaoxiaoDetailsOtherLx";
        }else{
            return "";
        }
    }

    /**
     * 审核通过跳转的方法
     *
     * @param user
     * @param eosid
     * @param flowid
     * @param content
     * @return
     */
    @RequestMapping(value = "auditylx", method = RequestMethod.POST)
    @ResponseBody
    public String auditpro(User user, @RequestParam(required = false) String eosid ,String stop , @RequestParam(required = false) String flowid, @RequestParam(required = false) String content) {

        JSONObject json = new JSONObject();
        if (user == null) {
            json.put("success", false);
            json.put("message", "用户过期，请重新访问！");
            return json.toString();
        }
        if (StringUtils.isEmpty(eosid) || StringUtils.isEmpty(flowid)) {
            json.put("success", false);
            json.put("message", "请求参数错误！");
            return json.toString();
        }
        try {
            if (oaWxExtendedService.Auditylx(eosid, flowid, content,stop)) {
                json.put("success", true);
                json.put("message", "审核完成！");
            } else {
                json.put("success", false);
                json.put("message", "审核异常！请联系管理员");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("success", false);
            json.put("message", "系统异常！");
        }
        return json.toString();
    }

    /**
     * 财务审核
     * @param user
     * @param eosid
     * @param stop
     * @param type
     * @param flowid
     * @param content
     * @return
     */

    @ResponseBody
    @RequestMapping("appropriation")
    public String appropriation(User user, @RequestParam(required = false) String eosid,String stop, String type,String flowid, @RequestParam(required = false) String content) {
        JSONObject json = new JSONObject();
        if (user == null) {
            json.put("success", false);
            json.put("message", "用户过期，请重新访问！");
            return json.toString();
        }
        try {
            if (oaWxExtendedService.appropriationbaoxiao(eosid, type,stop, flowid,content)) {
                json.put("success", true);
                json.put("message", "处理完成");
            } else {
                json.put("success", false);
                json.put("message", "出现异常！请联系管理员");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("success", false);
            json.put("message", "系统异常！");
        }
        return json.toString();
    }



    /**
     * 当前用户撤销申请跳转方法
     *
     * @param user
     * @param eosid
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping("delbx")
    public String delbx(User user, @RequestParam(required = false) String eosid, @RequestParam(required = false) String content) {

        JSONObject json = new JSONObject();
        if (user == null) {
            json.put("success", false);
            json.put("message", "用户过期，请重新访问！");
            return json.toString();
        }
        try {
            if (oaWxExtendedService.baoxiaodelcx(user, eosid)) {
                json.put("success", true);
                json.put("message", "撤销成功");
            } else {
                json.put("success", false);
                json.put("message", "出现异常！请联系管理员");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("success", false);
            json.put("message", "系统异常！");
        }
        return json.toString();
    }

    /**
     * 财务驳回方法
     * @param user
     * @param eosid
     * @param stop
     * @param type
     * @param flowid
     * @param content
     * @return
     */

    @ResponseBody
    @RequestMapping("outappropriation")
    public String outappropriation(User user, @RequestParam(required = false) String eosid,String stop, String type,String flowid, @RequestParam(required = false) String content) {
        JSONObject json = new JSONObject();
        if (user == null) {
            json.put("success", false);
            json.put("message", "用户过期，请重新访问！");
            return json.toString();
        }
        try {
            if (oaWxExtendedService.AuditProBack(eosid, flowid,content)) {
                json.put("success", true);
                json.put("message", "处理完成");
            } else {
                json.put("success", false);
                json.put("message", "出现异常！请联系管理员");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("success", false);
            json.put("message", "系统异常！");
        }
        return json.toString();
    }



    /**
     * 审核人驳回方法
     * @param user
     * @param eosid
     * @param stop
     * @param type
     * @param flowid
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping("out")
    public String out(User user, @RequestParam(required = false) String eosid,String stop, String type,String flowid, @RequestParam(required = false) String content) {
        JSONObject json = new JSONObject();
        if (user == null) {
            json.put("success", false);
            json.put("message", "用户过期，请重新访问！");
            return json.toString();
        }
        try {
            if (oaWxExtendedService.out(eosid, flowid,content)) {
                json.put("success", true);
                json.put("message", "处理完成");
            } else {
                json.put("success", false);
                json.put("message", "出现异常！请联系管理员");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("success", false);
            json.put("message", "系统异常！");
        }
        return json.toString();
    }
    /**
     * 财务导出Excel报表
     * @param site
     * @param oaWxBxCorrelationSuper
     * @param ks
     * @param js
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "export", method=RequestMethod.GET)
    public Object exportFile(Site site,OaWxBxCorrelationSuper oaWxBxCorrelationSuper,String ks,String js, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "财务数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            User user = UserUtils.getUser();
            oaWxBxCorrelationSuper.setCreateBy(user);
            System.out.println(ks+"+++"+js);

            if (StringUtils.isEmpty(ks)){
                ks="2019-1-1";
            }
            if (StringUtils.isEmpty(js)){
                ks="2019-4-26";
            }
             ks = ks.replace(",","-");
            js = js.replace(",","-");
            Page<OaWxBxCorrelationSuper> page = oaWxBxCorrelationSuperService.findFinanceExcel(new Page<OaWxBxCorrelationSuper>(request, response),ks, js,oaWxBxCorrelationSuper);
             List<FinanceExcel> list=new ArrayList<FinanceExcel>();
            for (OaWxBxCorrelationSuper ite: page.getList()) {
                FinanceExcel financeExcel=new FinanceExcel();
                double mocey = 0;
                OaWxBxCorrelation OaWxBxCorrelation=new OaWxBxCorrelation();
                OaWxBxCorrelation.setOaWxBxCorrelationSuper(ite);
                OaWxExtendedSuper oa=new OaWxExtendedSuper();
                oa.setId(ite.getExtendedSuperId());
                OaWxExtended oaWxExtended=new OaWxExtended();
                oaWxExtended.setOaWxExtendedSuper(oa);
                List<OaWxExtended> owelist=oaWxExtendedService.findBySuperProId(oaWxExtended);
                for (OaWxExtended i :owelist){
                    mocey += i.getCost();
                }
                financeExcel.setUserName(UserUtils.get(ite.getCreateBy().getId()).getName());
                if (ite.getOaWxExtendedSuper().getProItemType().equals("ylx")) {
                    ite.setPro( oaEosProService.get(ite.getProId()));
                    ite.getOaWxExtendedSuper().setPro( oaEosProService.get(ite.getProId()));
                    financeExcel.setProName(ite.getPro().getName());
                    financeExcel.setProid(ite.getPro().getId());
                    financeExcel.setPaProid(ite.getPro().getPaNumber());
                } else if (ite.getOaWxExtendedSuper().getProItemType().equals("lx")) {
                    ite.setPro( oaEosProService.get(ite.getProId()));
                    ite.getOaWxExtendedSuper().setPro( oaEosProService.get(ite.getProId()));
                    financeExcel.setProName(ite.getPro().getName());
                    financeExcel.setProid(ite.getPro().getId());
                    financeExcel.setPaProid(ite.getPro().getPaNumber());
                    financeExcel.setProProid(ite.getPro().getProNumber());
                } else if (ite.getOaWxExtendedSuper().getProItemType().equals("bm")) {
                    ite.getOaWxExtendedSuper().setOaWxDepartment(oaWxDepartmentService.get(ite.getProId()));
                    financeExcel.setProName(ite.getOaWxExtendedSuper().getOaWxDepartment().getName());
                    financeExcel.setProid(ite.getOaWxExtendedSuper().getOaWxDepartment().getId());
                } else if (ite.getOaWxExtendedSuper().getProItemType().equals("fxs")) {
                    ite.setUn( oaEosProUnService.get(ite.getProId()));
                    ite.getOaWxExtendedSuper().setOaEosProUn( oaEosProUnService.get(ite.getProId()));
                    financeExcel.setProid(ite.getOaWxExtendedSuper().getOaEosProUn().getId());
                    financeExcel.setProProid(ite.getOaWxExtendedSuper().getOaEosProUn().getProNumber());
                    financeExcel.setProName(ite.getOaWxExtendedSuper().getOaEosProUn().getName());
                }
                financeExcel.setMoney(mocey);
                financeExcel.setState(ite.getOaWxExtendedSuper().getState().equals("0") ? "待审核" : ite.getOaWxExtendedSuper().getState().equals("1") ? "审核通过" : ite.getOaWxExtendedSuper().getState().equals("-1") ? "已驳回" : ite.getOaWxExtendedSuper().getState().equals("2") ? "已撤销" : "出现异常请联系系统管理员");
                financeExcel.setCreateDate(ite.getCreateDate());
                financeExcel.setType(ite.getOaWxExtendedSuper().getProItemType().equals("lx")?"立项":ite.getOaWxExtendedSuper().getProItemType().equals("ylx")?"预立项":ite.getOaWxExtendedSuper().getProItemType().equals("fxs")?"非销售立项":ite.getOaWxExtendedSuper().getProItemType().equals("bm")?"部门":"系统异常");

                list.add(financeExcel);
            }
             new ExportExcel("财务数据", FinanceExcel.class).setDataList(list).write(response, fileName).dispose();
             return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/oa/weixin/two/tz?types=cw";
    }
    /**
     * 跳转到导出报表页面
     * @param site
     * @return
     */
    @RequestMapping("/excel")
    public  String excel(Site site){
        site.setTheme("weixin");
        return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/jiben/Excelexpo";
    }
}
