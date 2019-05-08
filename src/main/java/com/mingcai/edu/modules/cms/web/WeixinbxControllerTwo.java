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
    OaEosProStartItemService oaEosProStartItemService;
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
        if (types.equals("ylx")||types.equals("lx")){
            model.addAttribute("pz",userDao.get(pzid));
            model.addAttribute("cmh",userDao.get(cmh));
        }
        if (types.equals("fxs")) {
            model.addAttribute("fz", userDao.get(pzid));
        }
        if (types.equals("bm")) {
            model.addAttribute("fz", userDao.get(fzid));
            model.addAttribute("cmh", userDao.get(cmh));
        }
        model.addAttribute("site", site);
        model.addAttribute("SaleDepartmentId", SaleDepartmentId);
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
            model.addAttribute("site", site);
            return "redirect:" + OaPath + "/weixin/two/applysuccess?cost="+oaWxExtendedService.baoxiaosave(request, response,feleimg)/100;
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "你的申请信息有误请重新申请");
            model.addAttribute("gsons", Global.wxusermap);
            Other(type,types,code,model);
            return "modules/cms/front/themes/weixin/baoxiao/jiben/frontBaoXiaoAddOther";
        }
    }
    @RequestMapping("applysuccess")
    public String testPage(@RequestParam(required = false) String cost,Model model){
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        site.setTheme("weixin");
        model.addAttribute("site", site);
        model.addAttribute("cost", cost);
        return "modules/cms/front/themes/weixin/baoxiao/success";
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
            return "redirect:" + OaPath + "/weixin/two/applysuccess?cost="+oaWxExtendedService.bmbaoxiaosave(request,response)/100;
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
    @RequestMapping("fxsadd")
    public  String  fxsadd(String type, String types, @RequestParam(required = false) String code, HttpServletRequest request, HttpServletResponse response, Model model){
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        site.setTheme("weixin");
        model.addAttribute("site", site);
        model.addAttribute("code", code);
        model.addAttribute("type", type);
        model.addAttribute("types", types);
        try {
            return "redirect:" + OaPath + "/weixin/two/applysuccess?cost="+oaWxExtendedService.fxsbaoxiaosave(request,response)/100;
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
        OaWxExtendedSuper exsuper=new OaWxExtendedSuper();
        exsuper.setUserId(user.getId());
        exsuper.setRbsType("0");//基本类型
        Page<OaWxExtendedSuper> page = oaWxExtendedSuperService.findPage(new Page<OaWxExtendedSuper>(request, response), exsuper);
        OaWxExtendedSuperList(page);
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
        OaWxExtendedSuper exsuper=new OaWxExtendedSuper();
        exsuper.setUserId(user.getId());
        exsuper.setRbsType("0");//基本类型
        Page<OaWxExtendedSuper> page = oaWxExtendedSuperService.findListUn(new Page<OaWxExtendedSuper>(request, response), exsuper);
        OaWxExtendedSuperList(page);
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

        OaWxExtendedSuper exsuper=new OaWxExtendedSuper();
        exsuper.setUserId(user.getId());
        exsuper.setRbsType("0");//基本类型
        exsuper.setCw(true);
        Page<OaWxExtendedSuper> page = oaWxExtendedSuperService.findPage(new Page<OaWxExtendedSuper>(request, response), exsuper);
        OaWxExtendedSuperList(page);
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
        OaWxExtendedSuper exsuper=new OaWxExtendedSuper();
        exsuper.setUserId(user.getId());
        exsuper.setRbsType("0");//基本类型
        Page<OaWxExtendedSuper> page = oaWxExtendedSuperService.findFinishListByUser(new Page<OaWxExtendedSuper>(request, response), exsuper);
        OaWxExtendedSuperList(page);
        return page;
    }
    //处理报销列表list
    public void OaWxExtendedSuperList(Page<OaWxExtendedSuper> page){
        for (OaWxExtendedSuper ite: page.getList()) {
            double mocey = 0;
            OaWxExtended owe=new OaWxExtended();
            owe.setOaWxExtendedSuper(ite);
            List<OaWxExtended> bySuperId = oaWxExtendedService.findBySuperProId(owe);
            for (OaWxExtended i :bySuperId){
                mocey += i.getCost();
            }
            TreeMap<String, Object> map = new TreeMap<String, Object>();
            map.put("id", ite.getId());
            map.put("user", UserUtils.get(ite.getCreateBy().getId()));
            if (ite.getProItemType().equals("ylx")) {
                ite.setPro( oaEosProService.get(ite.getProId()));
                ite.setPro( oaEosProService.get(ite.getProId()));
                map.put("proName", ite.getPro().getName());
            } else if (ite.getProItemType().equals("lx")) {
                ite.setPro( oaEosProService.get(ite.getProId()));
                ite.setPro( oaEosProService.get(ite.getProId()));
                map.put("proName", ite.getPro().getName());
            } else if (ite.getProItemType().equals("bm")) {
                ite.setOaWxDepartment(oaWxDepartmentService.get(ite.getProId()));
                map.put("proName", ite.getOaWxDepartment().getName());
            } else if (ite.getProItemType().equals("fxs")) {
                ite.setOaEosProUn( oaEosProUnService.get(ite.getProId()));
                ite.setOaEosProUn( oaEosProUnService.get(ite.getProId()));
                map.put("proName", ite.getOaEosProUn().getName());
            }
            map.put("cost", mocey);
            map.put("state", ite.getState().equals("0") ? "待审核" : ite.getState().equals("1") ? "审核通过" : ite.getState().equals("-1") ? "已驳回" : ite.getState().equals("2") ? "已撤销" : "出现异常请联系系统管理员");
            map.put("crdate", DateUtils.formatDateTimeF(ite.getCreateDate()));
            if(ite.getFlow()!=null&&ite.getFlow().getStatus()!=null) {
                map.put("status", ite.getFlow().getStatus() == 1 ? "已处理" : "驳回");
                map.put("update", DateUtils.formatDateTime(ite.getFlow().getUpdateDate()));
            }
            User user = UserUtils.getUser();
            List<Role> rols= user.getRoleList();
            for(Role role:rols){
                if(role.getName().equals(financeAuditRole)){//财务
                    map.put("cw", true);
                    break;
                }
            }
            map.put("appropriation", ite.getAppropriation());
            map.put("type", ite.getProItemType());
            ite.setObjmap(map);
        }
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

//    /**
//     * 报销详情查询，审核人员可以在此页面进行审核
//     * @param idss
//     * @param type
//     * @param request
//     * @param cost
//     * @param sh
//     * @param response
//     * @param model
//     * @return
//     */
//    @RequestMapping("Details")
//    public String Details(String idss, @RequestParam(required = false) String type, HttpServletRequest request, String cost,String sh, HttpServletResponse response, Model model){
//        boolean success = Boolean.parseBoolean(request.getParameter("success"));
//        if (success) {
//            model.addAttribute("success", "success");
//        }
//        Site site = CmsUtils.getSite(Site.defaultSiteId());
//        site.setTheme("weixin");
//        model.addAttribute("site", site);
//        User user = UserUtils.getUser();
//        OaWxBxCorrelationSuper oaWxBxCorrelationSuper = oaWxBxCorrelationSuperService.get1(idss);
//        OaWxExtended oaWxExtended =new OaWxExtended();
//        oaWxExtended.setOaWxBxCorrelationSuper(oaWxBxCorrelationSuper);
//        oaWxBxCorrelationSuper.setUn(oaEosProUnService.get(oaWxBxCorrelationSuper.getProId()));
//        oaWxBxCorrelationSuper.setOaWxDepartment(oaWxDepartmentService.get(oaWxBxCorrelationSuper.getProId()));
//        List<OaWxExtended> list = oaWxExtendedDao.findBySuperId(oaWxExtended);
//        oaWxBxCorrelationSuper.setOaWxBxCorrelations(list);
//        OaEosFlow oeflow = new OaEosFlow();
//        oeflow.setEosId(oaWxBxCorrelationSuper.getId());
//        OaEosFlowItem item = new OaEosFlowItem();
//        model.addAttribute("gsons", Global.wxusermap);
//        if (oaWxBxCorrelationSuper.getOaWxExtendedSuper().getFlow() != null) {
//            item.setFlowId(oaWxBxCorrelationSuper.getOaWxExtendedSuper().getFlow().getId());
//            TreeMap<Integer, List<OaEosFlowItem>> maps = oaEosFlowItemService.findListByFlowId(item);
//            model.addAttribute("flowmap", maps);
//            model.addAttribute("flow", new OaEosFlow());
//
//            if (user != null) {
//
//                   oaEosFlowItemService.FlowUsers(maps); //找到要处理的流程
//
//                List<OaEosFlowItem> oeflsit = oaEosFlowItemService.FlowUsers(maps);
//                for (OaEosFlowItem oflow : oeflsit) {
//                    if (oflow.getUser().getId().equals(user.getUserId())) {
//                        model.addAttribute("flow", oflow);
//                    }
//                }
//            }
//        }
//        oaWxBxCorrelationSuper.setUpdateBy(userDao.get(oaWxBxCorrelationSuper.getUpdateBy().getId()));
//        model.addAttribute("map", oaWxBxCorrelationSuper);
//        model.addAttribute("user",UserUtils.get(oaWxBxCorrelationSuper.getCreateBy().getId()));
//        model.addAttribute("cost", cost);
//        model.addAttribute("types", type);
//        model.addAttribute("site", site);
//        model.addAttribute("sh",sh);
//        if ("bm".equals(type)) {
//            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/jiben/frontBaoxiaoDetailsOtherBm";
//        } else if ("fxs".equals(type)) {
//            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/jiben/frontBaoxiaoDetailsOtherFxs";
//        } else if ("ylx".equals(type)){
//            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/jiben/frontBaoxiaoDetailsOtherYlx";
//        }else if ("lx".equals(type)){
//            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/jiben/frontBaoxiaoDetailsOtherLx";
//        }else{
//            return "";
//        }
//    }
    /**
     * 非销售报销详情查询，审核人员可以在此页面进行审核
     * @param id
     * @param id
     * @param request
     * @param cost
     * @param sh
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("fxsDetails")
    public String fxsDetails(String id,  HttpServletRequest request, String cost,String sh, HttpServletResponse response, Model model){
        boolean success = Boolean.parseBoolean(request.getParameter("success"));
        if (success) {
            model.addAttribute("success", "success");
        }
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        site.setTheme("weixin");
        model.addAttribute("site", site);
        User user = UserUtils.getUser();
        OaWxExtendedSuper oweSuper=oaWxExtendedSuperService.get(id);
        OaWxExtended owe=new OaWxExtended();
        owe.setOaWxExtendedSuper(oweSuper);
        List<OaWxExtended> list=oaWxExtendedService.findBylistid(owe);
        double mocey=0.0;
        for(OaWxExtended owed:list){
            mocey += owed.getCost();
            if("fxs".equals(oweSuper.getProItemType())) {
                owed.setOaEosProStartItem(oaEosProStartItemService.get(owed.getProId()));
            }
        }
        oweSuper.setList(list);

        OaEosFlowItem item = new OaEosFlowItem();
        if (oweSuper.getFlow() != null) {
            item.setFlowId(oweSuper.getFlow().getId());
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
        model.addAttribute("oweSuper", oweSuper);
        model.addAttribute("user",userDao.get(oweSuper.getCreateBy().getId()));
        model.addAttribute("cost", mocey);
        model.addAttribute("types", oweSuper.getProItemType());
        model.addAttribute("site", site);
        model.addAttribute("sh",sh);
        model.addAttribute("gsons", Global.wxusermap);
        if ("bm".equals(oweSuper.getProItemType())) {
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/jiben/frontBaoxiaoDetailsOtherBm";
        } else if ("fxs".equals(oweSuper.getProItemType())) {
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/jiben/frontBaoxiaoDetailsOtherFxs";
        } else if ("ylx".equals(oweSuper.getProItemType())){
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/jiben/frontBaoxiaoDetailsOtherYlx";
        }else if ("lx".equals(oweSuper.getProItemType())) {
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/jiben/frontBaoxiaoDetailsOtherLx";
        }
        return "";
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
                js=DateUtils.formatDateTime(new java.util.Date());
            }
             ks = ks.replace(",","-");
            js = js.replace(",","-");
            OaWxExtendedSuper pwesuper=new OaWxExtendedSuper();
            pwesuper.setStarttime(ks);
            pwesuper.setEndtime(js);
            pwesuper.setRbsType("0");
            pwesuper.setCw(true);
            List<OaWxExtendedSuper> page = oaWxExtendedSuperService.findList(pwesuper);
             List<FinanceExcel> list=new ArrayList<FinanceExcel>();
            for (OaWxExtendedSuper ite: page) {
                FinanceExcel financeExcel=new FinanceExcel();
                double mocey = 0;
                OaWxExtended oaWxExtended=new OaWxExtended();
                oaWxExtended.setOaWxExtendedSuper(ite);
                List<OaWxExtended> owelist=oaWxExtendedService.findBySuperProId(oaWxExtended);
                for (OaWxExtended i :owelist){
                    mocey += i.getCost();
                }
                financeExcel.setUserName(UserUtils.get(ite.getCreateBy().getId()).getName());
                if (ite.getProItemType().equals("ylx")) {
                    ite.setPro( oaEosProService.get(ite.getProId()));
                    ite.setPro( oaEosProService.get(ite.getProId()));
                    financeExcel.setProName(ite.getPro().getName());
                    financeExcel.setProid(ite.getPro().getId());
                    financeExcel.setPaProid(ite.getPro().getPaNumber());
                } else if (ite.getProItemType().equals("lx")) {
                    ite.setPro( oaEosProService.get(ite.getProId()));
                    ite.setPro( oaEosProService.get(ite.getProId()));
                    financeExcel.setProName(ite.getPro().getName());
                    financeExcel.setProid(ite.getPro().getId());
                    financeExcel.setPaProid(ite.getPro().getPaNumber());
                    financeExcel.setProProid(ite.getPro().getProNumber());
                } else if (ite.getProItemType().equals("bm")) {
                    ite.setOaWxDepartment(oaWxDepartmentService.get(ite.getProId()));
                    financeExcel.setProName(ite.getOaWxDepartment().getName());
                    financeExcel.setProid(ite.getOaWxDepartment().getId());
                } else if (ite.getProItemType().equals("fxs")) {
                    ite.setOaEosProUn( oaEosProUnService.get(ite.getProId()));
                    ite.setOaEosProUn( oaEosProUnService.get(ite.getProId()));
                    financeExcel.setProid(ite.getOaEosProUn().getId());
                    financeExcel.setProProid(ite.getOaEosProUn().getProNumber());
                    financeExcel.setProName(ite.getOaEosProUn().getName());
                }
                financeExcel.setMoney(mocey);
                financeExcel.setState(ite.getState().equals("0") ? "待审核" : ite.getState().equals("1") ? "审核通过" : ite.getState().equals("-1") ? "已驳回" : ite.getState().equals("2") ? "已撤销" : "出现异常请联系系统管理员");
                financeExcel.setCreateDate(ite.getCreateDate());
                financeExcel.setType(ite.getProItemType().equals("lx")?"立项":ite.getProItemType().equals("ylx")?"预立项":ite.getProItemType().equals("fxs")?"非销售立项":ite.getProItemType().equals("bm")?"部门":"系统异常");

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
