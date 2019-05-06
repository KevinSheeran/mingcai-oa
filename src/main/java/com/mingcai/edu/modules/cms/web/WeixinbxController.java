package com.mingcai.edu.modules.cms.web;


import com.mingcai.edu.common.config.Global;
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.utils.DateUtils;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.common.web.QiniuUpload;
import com.mingcai.edu.modules.cms.entity.Site;
import com.mingcai.edu.modules.cms.utils.CmsUtils;
import com.mingcai.edu.modules.oa.entity.eos.*;
import com.mingcai.edu.modules.oa.entity.wx.OaWxDepartment;
import com.mingcai.edu.modules.oa.service.eos.*;
import com.mingcai.edu.modules.oa.service.wx.OaWxDepartmentService;
import com.mingcai.edu.modules.sys.dao.UserDao;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * 微信报销控制器
 *
 * @author liyijie
 */


@Controller
@RequestMapping(value = "${OaPath}/weixin/bx")
public class WeixinbxController extends BaseController {

    public static final TreeMap<String, Object> MAP = new TreeMap<String, Object>();

    @Autowired
    private OaEosProItemService oaEosProItemService;

    @Autowired
    private OaWxExtendedService oaWxExtendedService;

    @Autowired
    private OaEosFlowItemService oaEosFlowItemService;

    @Autowired
    private OaWxExtendedSuperService oaWxExtendedSuperService;

    @Autowired
    private OaEosProService oaEosProService;

    @Autowired
    private OaEosProStartItemService oaEosProStartItemService;

    @Autowired
    private OaWxDepartmentService oaWxDepartmentService;

    @Autowired
    private OaEosProUnService oaEosProUnService;

    @Autowired
    private  UserDao userDao;

    /**
     * 查询报销列表
     *
     * @param code
     * @param type
     * @param request
     * @param response
     * @param model
     * @param types    1 已审核 0 未审核 -1 财务审核 该默认查询当前用户所有报销列表
     * @return
     */
    @ResponseBody
    @RequestMapping("selby")
    public Object ylx(@RequestParam(required = false) String code, String type, HttpServletRequest request, HttpServletResponse response, Model model, String types) {
        User user = UserUtils.getUser();
        OaWxExtended oaWxExtended = new OaWxExtended();
        OaWxExtendedSuper oaWxExtendedSuper = new OaWxExtendedSuper();
        oaWxExtendedSuper.setCreateBy(user);
        oaWxExtendedSuper.setRbsType("1");
        List<Map> mapList = null;
        OaWxExtendedSuper oaWxExtendedSuper1 = null;
        Page<OaWxExtendedSuper> list = null;
        if ("1".equals(types)) {
            list = oaWxExtendedService.findProcessedList(new Page<OaWxExtendedSuper>(request, response), oaWxExtendedSuper);
        } else if ("0".equals(types)) {
            list = oaWxExtendedService.findUntreatedList(new Page<OaWxExtendedSuper>(request, response), oaWxExtendedSuper);
        } else if ("-1".equals(types)) {
            list = oaWxExtendedService.Finance(new Page<OaWxExtendedSuper>(request, response), oaWxExtendedSuper);
        } else {
            list = oaWxExtendedSuperService.findPage(new Page<OaWxExtendedSuper>(request, response), oaWxExtendedSuper);
        }
        for (OaWxExtendedSuper ite : list.getList()) {
            double mocey = 0;
            oaWxExtendedSuper1 = ite;
            mapList = new ArrayList<Map>();
            oaWxExtended.setOaWxExtendedSuper(ite);
            List<OaWxExtended> list1 = oaWxExtendedService.findBylistid(oaWxExtended);
            for (int i = 0; i < list1.size(); i++) {
                mocey += list1.get(i).getCost();
            }
            TreeMap<String, Object> map = new TreeMap<String, Object>();
            map.put("id", ite.getId());
            map.put("user", UserUtils.get(ite.getCreateBy().getId()));
            if (ite.getProItemType().equals("ylx")) {
                map.put("proName", ite.getPro().getName());
            } else if (ite.getProItemType().equals("lx")) {
                map.put("proName", ite.getPro().getName());
            } else if (ite.getProItemType().equals("fxs")) {
                map.put("proName", ite.getOaEosProUn().getName());
            }
            map.put("cost", mocey);
            map.put("state", ite.getState().equals("0") ? "待审核" : ite.getState().equals("1") ? "审核通过" : ite.getState().equals("-1") ? "已驳回" : ite.getState().equals("2") ? "已撤销" : "出现异常请联系系统管理员");
            map.put("crdate", DateUtils.formatDateTimeF(ite.getCreateDate()));
            map.put("appropriation", ite.getAppropriation());
            map.put("type", ite.getProItemType());
            mapList.add(map);
            oaWxExtendedSuper1.setMapList(mapList);
            ite = oaWxExtendedSuper1;

        }
        return list;
    }


    @RequestMapping("caigou")
    public String caigou(String type, String types, @RequestParam(required = false) String code, HttpServletRequest request, HttpServletResponse response, Model model) {
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        site.setTheme("weixin");
        model.addAttribute("site", site);
        model.addAttribute("code", code);
        model.addAttribute("type", type);
        model.addAttribute("types", types);
        String feleimg = null;
        model.addAttribute("gsons", Global.wxusermap);
        return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/caigou/frontBaoXiaoAdd";
    }

    /**
     * 添加报销信息
     *
     * @param type
     * @param types
     * @param code
     * @param request
     * @param response
     * @param model
     * @return
     * @throws ParseException
     * @throws IOException
     */
    @RequestMapping("baoxiaoadd")
    public String baoxiaoadd(String type, String types, @RequestParam(required = false) String code, HttpServletRequest request, HttpServletResponse response, Model model) {
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        site.setTheme("weixin");
        model.addAttribute("site", site);
        model.addAttribute("code", code);
        model.addAttribute("type", type);
        model.addAttribute("types", types);
        String feleimg = ",";
        try {
//                for(MultipartFile file:files){
//                    if(file.getSize()>0) {
//                        try {
//                            String key= QiniuUpload.upload(file.getInputStream(),AK,SK,BUCKET);
//                           feleimg+=qiniuRUL+key+",";
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
            feleimg = feleimg.replace(',', ' ').trim().replace(' ', ',');
            oaWxExtendedService.saveitem(request, response, feleimg);
            return "redirect:/oa/weixin";
        } catch (Exception e) {
            model.addAttribute("types", request.getParameter("proItemType"));
            model.addAttribute("type", request.getParameter("proItemType"));
            model.addAttribute("error", "你的申请信息有误请重新申请");
            model.addAttribute("gsons", Global.wxusermap);
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/caigou/frontBaoXiaoAdd";
        }
    }

    /*@RequestMapping("bmbaoxiaoadd")
    public String bmbaoxiaoadd(String type, String types, @RequestParam(required = false) String code, HttpServletRequest request, HttpServletResponse response, Model model) {
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        site.setTheme("weixin");
        model.addAttribute("site", site);
        model.addAttribute("code", code);
        model.addAttribute("type", type);
        model.addAttribute("types", types);

        if (request.getParameter("fid") == null) {
            model.addAttribute("gsons", Global.wxusermap);
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/caigou/frontBaoXiaoAdd";
        } else {
            try {
                oaWxExtendedService.bmsaveitem(request, response);
                return "redirect:/oa/weixin";
            } catch (Exception e) {
                model.addAttribute("types", request.getParameter("proItemType"));
                model.addAttribute("type", request.getParameter("proItemType"));
                model.addAttribute("error", "你的申请信息有误请重新申请");
                model.addAttribute("gsons", Global.wxusermap);
                return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/caigou/frontBaoXiaoAdd";
            }
        }

    }*/

    /**
     * 查询所有项目并返回json对象
     *
     * @param types
     * @return
     */
    @RequestMapping("json")
    @ResponseBody
    public Object json(String types) {
        oaEosProService.getAllPro();
        TreeMap map = null;
        if (types.equals("ylx")) {
            map = new TreeMap<String, OaEosPro>();
            for (String item : Global.getTreeMap().keySet()) {
                OaEosPro pro = Global.getTreeMap().get(item);
                if (pro != null && pro.getType() == 1) {
                    map.put(pro.getId(), pro);
                }
            }
        } else if (types.equals("lx")) {
            map = new TreeMap<String, OaEosPro>();
            for (String item : Global.getTreeMap().keySet()) {
                OaEosPro pro = Global.getTreeMap().get(item);
                if (pro != null && pro.getType() == 2) {
                    map.put(pro.getId(), pro);
                }
            }
        } else if (types.equals("bm")) {
            map = new TreeMap<String, OaWxDepartment>();
            for (String item : Global.getDepartmentMap().keySet()) {
                OaWxDepartment department = Global.getDepartmentMap().get(item);
                if (StringUtils.isNotEmpty(department.getId())) {
                    map.put(department.getId(), department);
                }
            }
        } else if (types.equals("fxs")) {
            map = new TreeMap<String, OaEosProUn>();
            for (String item : Global.getOaEosProUnMap().keySet()) {
                OaEosProUn oaEosProUn = Global.getOaEosProUnMap().get(item);
                if (StringUtils.isNotEmpty(oaEosProUn.getId())) {
                    map.put(oaEosProUn.getId(), oaEosProUn);
                }
            }
        }
        return map;
    }

    /**
     * 报销详细信息
     * 审核人员在当前页面看到审核信息
     *
     * @param idss
     * @param type
     * @param request
     * @param cost
     * @param response
     * @param model
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("Details")
    public Object details(String idss, String sh, @RequestParam(required = false) String type, HttpServletRequest request, String cost, HttpServletResponse response, Model model) throws ServletException, IOException {
        boolean success = Boolean.parseBoolean(request.getParameter("success"));
        if (success) {
            model.addAttribute("success", "success");
        }
        model.addAttribute("sh", sh);
        User user = UserUtils.getUser();
        OaWxExtendedSuper oaWxExtendedSuper = oaWxExtendedSuperService.get(idss);
        OaWxExtended owe = new OaWxExtended();
        owe.setOaWxExtendedSuper(oaWxExtendedSuper);
        List<OaWxExtended> bylistid = oaWxExtendedService.findBylistid(owe);
        for (OaWxExtended ext : bylistid) {
            if ("lx".equals(type)) {
                ext.setOaEosProStartItem(oaEosProStartItemService.get(ext.getProId()));
            } else if ("ylx".equals(type)) {
                ext.setOaEosProItem(oaEosProItemService.get(ext.getProId()));
            } else if ("bm".equals(type)) {
                ext.setOaWxDepartment(oaWxDepartmentService.get(ext.getProId()));
            } else if ("fxs".equals(type)) {
                ext.setOaEosProStartItem(oaEosProStartItemService.get(ext.getProId()));
            }
        }
        oaWxExtendedSuper.setList(bylistid);
        OaEosFlow oeflow = new OaEosFlow();
        oeflow.setEosId(oaWxExtendedSuper.getId());
        OaEosFlowItem item = new OaEosFlowItem();
        model.addAttribute("gsons", Global.wxusermap);
        if (oaWxExtendedSuper.getFlow() != null) {
            item.setFlowId(oaWxExtendedSuper.getFlow().getId());
            TreeMap<Integer, List<OaEosFlowItem>> maps = oaEosFlowItemService.findListByFlowId(item);
            model.addAttribute("flowmap", maps);
            model.addAttribute("flow", new OaEosFlow());

            if (user != null) {
                /**
                 *  oaEosFlowItemService.FlowUsers(maps)找到要处理的流程
                 */
                List<OaEosFlowItem> oeflsit = oaEosFlowItemService.FlowUsers(maps);
                for (OaEosFlowItem oflow : oeflsit) {
                    if (oflow.getUser().getId().equals(user.getUserId())) {
                        model.addAttribute("flow", oflow);
                    }
                }
            }
        }
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        site.setTheme("weixin");
        model.addAttribute("map", oaWxExtendedSuper);
        model.addAttribute("user", UserUtils.get(oaWxExtendedSuper.getCreateBy().getId()));
        model.addAttribute("cost", cost);
        model.addAttribute("types", type);
        model.addAttribute("site", site);
        if ("bm".equals(type)) {
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/caigou/frontBMBaoxiaoDetails";
        } else if ("fxs".equals(type)) {
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/caigou/frontFXSBaoxiaoDetails";
        } else {
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/caigou/frontBaoxiaoDetails";
        }
    }

    /**
     * 预立项报销审核
     *
     * @param user
     * @param eosid
     * @param flowid
     * @param content
     * @return
     */
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    @ResponseBody
    public String auditpro(User user, @RequestParam(required = false) String eosid, String stop, @RequestParam(required = false) String flowid, @RequestParam(required = false) String content) {

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
            if (oaWxExtendedService.AuditPro(eosid, flowid, content, stop)) {
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


    @RequestMapping("userjson")
    @ResponseBody
    public Object userjson(@RequestParam(required = false) String role) {

        return UserUtils.getByRole(role);
    }

    /**
     * 财务审核通过跳转的方法
     *
     * @param user
     * @param eosid
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping("appropriation")
    public String appropriation(User user, @RequestParam(required = false) String eosid, String stop, String type, String flowid, @RequestParam(required = false) String content) {
        JSONObject json = new JSONObject();
        if (user == null) {
            json.put("success", false);
            json.put("message", "用户过期，请重新访问！");
            return json.toString();
        }
        try {
            if (oaWxExtendedService.appropriation(eosid, type, stop, flowid)) {
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
            if (oaWxExtendedService.delcx(user, eosid)) {
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

    @RequestMapping("tz")
    public String tz(Model model, String types) {
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        site.setTheme("weixin");
        model.addAttribute("site", site);
        model.addAttribute("type", types);
        if (types.equals("ysh")) {
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/caigou/frontbxtableysh";
        } else if (types.equals("cw")) {
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/caigou/frontbxtablecw";
        } else if (types.equals("sh")) {
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/caigou/frontbxtablesh";
        } else {
            return "modules/cms/front/themes/" + site.getTheme() + "/baoxiao/caigou/frontbxtable";
        }
    }

    /**
     * 查询所有项目列表
     *
     * @param user
     * @param id
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("front_pro")
    public String front_pro(User user, String id, HttpServletRequest request, HttpServletResponse response, Model model) {
        oaEosProService.getAllPro();
        List<OaEosPro> arraylist = Global.getArraylist();
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        site.setTheme("weixin");
        model.addAttribute("site", site);
        model.addAttribute("map", arraylist);
        model.addAttribute("summary", request.getParameter("summary"));
        return "modules/cms/front/themes/" + site.getTheme() + "/front_pro_number_select";
    }

    /**
     * 根据父项目查询他的子项目列表
     *
     * @param id
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("front_pro_item")
    public String front_pro_item(String type, String id, Model model, HttpServletRequest request, HttpServletResponse response) {
        if ("ylx".equals(type)) {
            OaEosProItem oaEosProItem = new OaEosProItem();
            oaEosProItem.setEosId(id);
            List<OaEosProItem> oaEosProItem1 = oaEosProItemService.selByprolistid(oaEosProItem);
            OaEosPro oaEosPro = new OaEosPro();
            oaEosPro.setId(id);
            OaEosPro oaEosPro1 = oaEosProService.selByprozid(oaEosPro);
            model.addAttribute("oaEosPro", oaEosPro1);
            model.addAttribute("list", oaEosProItem1);
            model.addAttribute("type", "ylx");
        } else if ("lx".equals(type)) {
            OaEosProStartItem oaEosProStartItem = new OaEosProStartItem();
            oaEosProStartItem.setEosId(id);
            List<OaEosProStartItem> oaEosProStartItem1 = oaEosProStartItemService.selByprolistid(oaEosProStartItem);
            OaEosPro oaEosPro = new OaEosPro();
            oaEosPro.setId(id);
            OaEosPro oaEosPro1 = oaEosProService.selByprozid(oaEosPro);
            model.addAttribute("oaEosPro", oaEosPro1);
            model.addAttribute("list", oaEosProStartItem1);
            model.addAttribute("type", "lx");
        } else if ("flx".equals(type)) {
            OaEosProStartItem oaEosProStartItem = new OaEosProStartItem();
            oaEosProStartItem.setEosId(id);
            List<OaEosProStartItem> oaEosProStartItem1 = oaEosProStartItemService.selByprolistid(oaEosProStartItem);
            OaEosProUn oaEosProUn = new OaEosProUn();
            oaEosProUn.setId(id);
            OaEosProUn oaEosProUn1 = oaEosProUnService.selByprozid(oaEosProUn);
            model.addAttribute("oaEosPro", oaEosProUn1);
            model.addAttribute("list", oaEosProStartItem1);
            model.addAttribute("type", "flx");
        }
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        site.setTheme("weixin");
        model.addAttribute("site", site);
        return "modules/cms/front/themes/" + site.getTheme() + "/front_pro_item_number_select";
    }


    @ResponseBody
    @RequestMapping("selbyysh")
    public Object selbyysh(@RequestParam(required = false) String code, String type, HttpServletRequest request, HttpServletResponse response, Model model, String types) {
        User user = UserUtils.getUser();
        OaWxExtended oaWxExtended = new OaWxExtended();
        OaWxExtendedSuper oaWxExtendedSuper = new OaWxExtendedSuper();
        oaWxExtendedSuper.setCreateBy(user);
        oaWxExtendedSuper.setRbsType("1");
        List<Map> mapList = null;
        OaWxExtendedSuper oaWxExtendedSuper1 = null;
        Page<OaWxExtendedSuper> list = null;
        list = oaWxExtendedService.findProcessedList(new Page<OaWxExtendedSuper>(request, response), oaWxExtendedSuper);
        for (OaWxExtendedSuper ite : list.getList()) {
            double mocey = 0;
            oaWxExtendedSuper1 = ite;
            mapList = new ArrayList<Map>();
            oaWxExtended.setOaWxExtendedSuper(ite);
            List<OaWxExtended> list1 = oaWxExtendedService.findBylistid(oaWxExtended);
            for (int i = 0; i < list1.size(); i++) {
                mocey += list1.get(i).getCost();
            }
            TreeMap<String, Object> map = new TreeMap<String, Object>();
            map.put("id", ite.getId());
            map.put("user", UserUtils.get(ite.getCreateBy().getId()));
            if (ite.getProItemType().equals("ylx")) {
                map.put("proName", ite.getPro().getName());
            } else if (ite.getProItemType().equals("lx")) {
                map.put("proName", ite.getPro().getName());
            } else if (ite.getProItemType().equals("fxs")) {
                map.put("proName", ite.getOaEosProUn().getName());
            }
            map.put("cost", mocey);
            map.put("state", ite.getState().equals("0") ? "待审核" : ite.getState().equals("1") ? "审核通过" : ite.getState().equals("-1") ? "已驳回" : ite.getState().equals("2") ? "已撤销" : "出现异常请联系系统管理员");
            map.put("crdate", DateUtils.formatDateTimeF(ite.getCreateDate()));
            map.put("appropriation", ite.getAppropriation());
            map.put("type", ite.getProItemType());
            mapList.add(map);
            oaWxExtendedSuper1.setMapList(mapList);
            ite = oaWxExtendedSuper1;

        }
        return list;
    }

    @ResponseBody
    @RequestMapping("selbysh")
    public Object ylx1sh(@RequestParam(required = false) String code, String type, HttpServletRequest request, HttpServletResponse response, Model model, String types) {
        User user = UserUtils.getUser();
        OaWxExtended oaWxExtended = new OaWxExtended();
        OaWxExtendedSuper oaWxExtendedSuper = new OaWxExtendedSuper();
        oaWxExtendedSuper.setCreateBy(user);
        oaWxExtendedSuper.setRbsType("1");
        List<Map> mapList = null;
        OaWxExtendedSuper oaWxExtendedSuper1 = null;
        Page<OaWxExtendedSuper> list = null;
        list = oaWxExtendedService.findUntreatedList(new Page<OaWxExtendedSuper>(request, response), oaWxExtendedSuper);
        for (OaWxExtendedSuper ite : list.getList()) {
            double mocey = 0;
            oaWxExtendedSuper1 = ite;
            mapList = new ArrayList<Map>();
            oaWxExtended.setOaWxExtendedSuper(ite);
            List<OaWxExtended> list1 = oaWxExtendedService.findBylistid(oaWxExtended);
            for (int i = 0; i < list1.size(); i++) {
                mocey += list1.get(i).getCost();
            }
            TreeMap<String, Object> map = new TreeMap<String, Object>();
            map.put("id", ite.getId());
            map.put("user", UserUtils.get(ite.getCreateBy().getId()));
            if (ite.getProItemType().equals("ylx")) {
                map.put("proName", ite.getPro().getName());
            } else if (ite.getProItemType().equals("lx")) {
                map.put("proName", ite.getPro().getName());
            } else if (ite.getProItemType().equals("fxs")) {
                map.put("proName", ite.getOaEosProUn().getName());
            }
            map.put("cost", mocey);
            map.put("state", ite.getState().equals("0") ? "待审核" : ite.getState().equals("1") ? "审核通过" : ite.getState().equals("-1") ? "已驳回" : ite.getState().equals("2") ? "已撤销" : "出现异常请联系系统管理员");
            map.put("crdate", DateUtils.formatDateTimeF(ite.getCreateDate()));
            map.put("appropriation", ite.getAppropriation());
            map.put("type", ite.getProItemType());
            mapList.add(map);
            oaWxExtendedSuper1.setMapList(mapList);
            ite = oaWxExtendedSuper1;

        }
        return list;
    }


    @ResponseBody
    @RequestMapping("selbycw")
    public Object ylx1cw(@RequestParam(required = false) String code, String type, HttpServletRequest request, HttpServletResponse response, Model model, String types) {
        User user = UserUtils.getUser();
        OaWxExtended oaWxExtended = new OaWxExtended();
        OaWxExtendedSuper oaWxExtendedSuper = new OaWxExtendedSuper();
        oaWxExtendedSuper.setCreateBy(user);
        oaWxExtendedSuper.setRbsType("1");
        List<Map> mapList = null;
        OaWxExtendedSuper oaWxExtendedSuper1 = null;
        Page<OaWxExtendedSuper> list = null;
        list = oaWxExtendedService.Finance(new Page<OaWxExtendedSuper>(request, response), oaWxExtendedSuper);
        for (OaWxExtendedSuper ite : list.getList()) {
            double mocey = 0;
            oaWxExtendedSuper1 = ite;
            mapList = new ArrayList<Map>();
            oaWxExtended.setOaWxExtendedSuper(ite);
            List<OaWxExtended> list1 = oaWxExtendedService.findBylistid(oaWxExtended);
            for (int i = 0; i < list1.size(); i++) {
                mocey += list1.get(i).getCost();
            }
            TreeMap<String, Object> map = new TreeMap<String, Object>();
            map.put("id", ite.getId());
            map.put("user", UserUtils.get(ite.getCreateBy().getId()));
            if (ite.getProItemType().equals("ylx")) {
                map.put("proName", ite.getPro().getName());
            } else if (ite.getProItemType().equals("lx")) {
                map.put("proName", ite.getPro().getName());
            } else if (ite.getProItemType().equals("fxs")) {
                map.put("proName", ite.getOaEosProUn().getName());
            }
            map.put("cost", mocey);
            map.put("state", ite.getState().equals("0") ? "待审核" : ite.getState().equals("1") ? "审核通过" : ite.getState().equals("-1") ? "已驳回" : ite.getState().equals("2") ? "已撤销" : "出现异常请联系系统管理员");
            map.put("crdate", DateUtils.formatDateTimeF(ite.getCreateDate()));
            map.put("appropriation", ite.getAppropriation());
            map.put("type", ite.getProItemType());
            mapList.add(map);
            oaWxExtendedSuper1.setMapList(mapList);
            ite = oaWxExtendedSuper1;
        }
        return list;
    }
}