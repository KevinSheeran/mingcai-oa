/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.*;

import com.mingcai.edu.common.utils.DateUtils;
import com.mingcai.edu.common.utils.PageData;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.dao.eos.OaEosFlowDao;
import com.mingcai.edu.modules.oa.dao.eos.OaEosProDao;
import com.mingcai.edu.modules.oa.dao.eos.OaWxExtendedSuperDao;
import com.mingcai.edu.modules.oa.entity.eos.*;
import com.mingcai.edu.modules.oa.entity.user.OaUserAccount;
import com.mingcai.edu.modules.oa.service.user.OaUserAccountService;
import com.mingcai.edu.modules.sys.dao.UserDao;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.dao.eos.OaWxExtendedDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信报销详情Service
 *
 * @author 李艺杰
 * @version 2019-03-11
 */
@SuppressWarnings("ALL")
@Service
@Transactional(rollbackFor = Exception.class,readOnly = true)
public class OaWxExtendedService extends CrudService<OaWxExtendedDao, OaWxExtended> {
    @Autowired
    OaEosFlowDao oefDao;
    @Autowired
    OaEosFlowItemService oefiService;

    @Autowired
    private UserDao userDao;

    @Autowired
    OaWxExtendedSuperDao oaWxExtendedSuperService;
    @Autowired
    OaWxExtendedDao oaWxExtendedDao;
    @Autowired
    OaWxExtendedSuperService oaWxExtendedSuperService1;

    @Autowired
    OaEosProStartItemService oaEosProStartItemService;

    @Autowired
    OaEosProItemService oaEosProItemService;


    @Autowired
    OaWxBxCorrelationService oaWxBxCorrelationService;

    @Autowired
    OaWxBxCorrelationSuperService oaWxBxCorrelationSuperService;
    /**
     * 财务角色id
     */
    @Value("${financeAuditRole}")
    protected String financeAuditRole;
    @Override
    public OaWxExtended get(String id) {
        return super.get(id);
    }

    @Autowired
    public OaUserAccountService accountService;
    @Override
    public List<OaWxExtended> findList(OaWxExtended oaWxExtended) {
        return super.findList(oaWxExtended);
    }


    @Override
    public Page<OaWxExtended> findPage(Page<OaWxExtended> page, OaWxExtended oaWxExtended) {
        return super.findPage(page, oaWxExtended);
    }


    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Override
    @Transactional(rollbackFor = Exception.class,readOnly = false)
    public void save(OaWxExtended oaWxExtended) {
        super.save(oaWxExtended);
    }

    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Override
    @Transactional(rollbackFor = Exception.class,readOnly = false)
    public void delete(OaWxExtended oaWxExtended) {
        super.delete(oaWxExtended);
    }


    //保存基本流程
    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public String saveAudit(OaWxExtendedSuper oaWxExtendedSuper, HttpServletRequest request,String types) {
        OaWxBxCorrelationSuper oaWxBxCorrelationSuperService2 = oaWxBxCorrelationSuperService.get2(oaWxExtendedSuper.getId());
        String[] group_threes=null;
        String[] group_twos =null;
        String group_two =null;
        String group_three=null;
        /**
         * 除了销售预立项和销售立项之外其他的都不走管理中心流程
         */
        if (!"bm".equals(types) || !"fxs".equals(types)){
            group_twos = request.getParameterValues("group_two");
             group_two = StringUtils.join(group_twos, ",");
        }
        group_threes = request.getParameterValues("group_three");
        group_three = StringUtils.join(group_threes, ",");
        String[] group_ones = request.getParameterValues("group_one");
        String group_one = StringUtils.join(group_ones, ",");
        User user = new User();
        user.setId("1");
        oaWxExtendedSuper.setState("0");
        String[] audits = {group_one, group_two, group_three};
        if (StringUtils.isNotEmpty(audits[0])) {
            TreeMap<Integer, String> flowmap = new TreeMap<Integer, String>();
            flowmap.put(0, group_one);
            if (!"bm".equals(types) && !"fxs".equals(types)){
                flowmap.put(1, group_two);
            }
            flowmap.put(2, group_three);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("type", 1);
            User user1 = userDao.get(oaWxExtendedSuper.getCreateBy().getId());
            String content = "您有新的报销信息需要审批，请到OA应用确认审批。\n申请人："+user1.getName()+"\n申请日期："+DateUtils.formatDateTimeF(oaWxExtendedSuper.getCreateDate())+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2ftwo%2fDetails%3Fidss%3D" + oaWxBxCorrelationSuperService2.getId() +"%26type%3D"+oaWxExtendedSuper.getProItemType()+"%26sh%3D"+0+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
            paramMap.put("flowName", "报销流程");
            Map<String, String> returnMap = new HashMap<String, String>();
            returnMap = oefiService.createFlow(flowmap, paramMap);
            if ("false".equals(returnMap.get("success"))) {
                return returnMap.get("message");
            } else {
                String flowid = returnMap.get("flowId");
                OaEosFlowItem flows = new OaEosFlowItem();
                flows.setId(flowid);
                oaWxExtendedSuper.setFlow(flows);
                returnMap = oefiService.startFlow(flowid, true, content);
                if ("false".equals(returnMap.get("success"))) {
                    return returnMap.get("message");
                } else {
                    oaWxExtendedSuperService.updateAudit(oaWxExtendedSuper);
                }
            }
        }
        return "success";
    }

    //保存采购流程
    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public String cgsaveAudit(OaWxExtendedSuper oaWxExtendedSuper, HttpServletRequest request,String types) {
        String[] group_threes=null;
        String[] group_twos =null;
        String group_two =null;
        String group_three=null;
            group_twos = request.getParameterValues("group_two");
            group_two = StringUtils.join(group_twos, ",");
        group_threes = request.getParameterValues("group_three");
        group_three = StringUtils.join(group_threes, ",");
        User user = new User();
        user.setId("1");
        oaWxExtendedSuper.setState("0");
        String[] audits = { group_two, group_three};
        if (StringUtils.isNotEmpty(audits[0])) {
            TreeMap<Integer, String> flowmap = new TreeMap<Integer, String>();
                flowmap.put(0, group_two);
            flowmap.put(1, group_three);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("type", 1);
            User user1 = userDao.get(oaWxExtendedSuper.getCreateBy().getId());
            String content = "您有新的报销信息需要审批，请到OA应用确认审批。\n申请人："+user1.getName()+"\n申请日期："+DateUtils.formatDateTimeF(oaWxExtendedSuper.getCreateDate())+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2fbx%2fDetails%3Fidss%3D" + oaWxExtendedSuper.getId() +"%26type%3D"+oaWxExtendedSuper.getProItemType() +"%26sh%3D"+0+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
            paramMap.put("flowName", "报销流程");
            Map<String, String> returnMap = new HashMap<String, String>();
            returnMap = oefiService.createFlow(flowmap, paramMap);
            if ("false".equals(returnMap.get("success"))) {
                return returnMap.get("message");
            } else {
                String flowid = returnMap.get("flowId");
                OaEosFlowItem flows = new OaEosFlowItem();
                flows.setId(flowid);
                oaWxExtendedSuper.setFlow(flows);
                returnMap = oefiService.startFlow(flowid, true, content);
                if ("false".equals(returnMap.get("success"))) {
                    return returnMap.get("message");
                } else {
                    oaWxExtendedSuperService.updateAudit(oaWxExtendedSuper);
                }
            }
        }
        return "success";
    }



    /**
     * 审核完成
     * @param proid
     * @param flowid
     * @param content
     * @return
     */
    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public boolean AuditPro(String proid, String flowid, String content,String stop) {
        OaWxExtendedSuper oaWxExtendedSuper1=new OaWxExtendedSuper();
        OaWxExtendedSuper oaWxExtendedSuper=oaWxExtendedSuperService1.get(proid);
        Map<String, String> returnmap=null;
        OaWxExtendedSuper byid =null;
        oaWxExtendedSuper1.setId(proid);
         byid = oaWxExtendedSuperService.findByid(oaWxExtendedSuper1);
            User user1 = userDao.get(oaWxExtendedSuper.getCreateBy().getId());
        String message = "您有新的报销信息需要审批，请到OA应用确认审批。\n申请人："+user1.getName()+"\n申请日期："+DateUtils.formatDateTimeF(oaWxExtendedSuper.getCreateDate())+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2fbx%2fDetails%3Fidss%3D" + byid.getId() +"%26type%3D"+oaWxExtendedSuper.getProItemType()+"%26sh%3D"+0+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
        String finishmessage = "";//"报销信息审批完成。\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2fbx%2Details?idss=" + byid.getId() + "&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
        returnmap = oefiService.auditFlow(flowid, true, message, finishmessage, content);
        if (!"true".equals(returnmap.get("success"))) {
            oaWxExtendedSuper.setState("-1");
            oaWxExtendedSuperService.updateAudit(oaWxExtendedSuper);
            return false;
        } else {
                if ("true".equals(returnmap.get("pass"))) {
                    /**
                     * 找到所有拥有财务审批权限的角色
                     */
                    List<User> cfos = UserUtils.getByRole(financeAuditRole);
                    StringBuffer sb = new StringBuffer();
                    /**
                     * 循环这个集合把集合拼接成一条字符串用 | 隔开
                     */
                    for (User item : cfos) {
                        if (item.getWxUsers() == null) {
                            continue;
                        } else {
                            sb.append(item.getWxUsers().getUserid() + "|");
                        }
                    }
                    /**
                     * 把字符串最后的一个 | 去掉
                     */
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    String message1 = "报销信息审批完成请确认拨款。\n申请人："+user1.getName()+"\n申请日期："+DateUtils.formatDateTimeF(oaWxExtendedSuper.getCreateDate())+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2fbx%2fDetails%3Fidss%3D" + byid.getId() +"%26success%3Dtrue" +"%26type%3D"+oaWxExtendedSuper.getProItemType()+"%26sh%3D"+0+ "&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
                    oefiService.sendMsg(sb.toString(), message1);
                    oaWxExtendedSuper.setState("1");
                    oaWxExtendedSuperService.updateAudit(oaWxExtendedSuper);
                }

        }
        return true;
    }

    /**
     *
     * @param request
     * @param response
     * @throws ParseException
     */
    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public void saveitem(HttpServletRequest request, HttpServletResponse response,String a) throws ParseException {
        String[] zids = request.getParameterValues("zid");
        String[] costs = request.getParameterValues("cost");
        String types=request.getParameter("proItemType");
        String fid = request.getParameter("fid");
        String[] fymxes = request.getParameterValues("fymx");
        List<OaWxExtended> list = new ArrayList<OaWxExtended>();
        for (int i = 0; i < zids.length; i++) {
            OaWxExtended oaWxExtended = new OaWxExtended();
            oaWxExtended.setCost(Double.valueOf(costs[i]) * 100);
            oaWxExtended.setContent(fymxes[i]);
            oaWxExtended.setProId(zids[i]);
            oaWxExtended.setCreateDate(new Date());
            oaWxExtended.setState("0");
            list.add(oaWxExtended);
        }
        OaWxExtendedSuper oaWxExtendedSuper = new OaWxExtendedSuper();
        oaWxExtendedSuper.setAppropriation("0");
        oaWxExtendedSuper.setProItemType(types);
        oaWxExtendedSuper.setEnclosure(a);
        oaWxExtendedSuper.setRbsType("1");
        oaWxExtendedSuper.setProId(fid);
        oaWxExtendedSuperService1.save(oaWxExtendedSuper);
        String s = cgsaveAudit(oaWxExtendedSuper, request,types);
        for (OaWxExtended items : list) {
            items.setOaWxExtendedSuper(oaWxExtendedSuper);
            save(items);
        }
    }

    public OaWxExtended findByid(OaWxExtended oaWxExtended) {
        return dao.findByid(oaWxExtended);
    }

    /**
     * 撤销申请
     * @param user
     * @param id
     * @return
     */
    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public boolean delcx(User user, String id) {
        OaWxExtendedSuper oaWxExtended1 = new OaWxExtendedSuper();
        oaWxExtended1.setCreateBy(user);
        oaWxExtended1.setId(id);
        try {
            OaWxExtendedSuper oaWxExtended = oaWxExtendedSuperService.findByid(oaWxExtended1);
            OaEosFlowItem oaEosFlowItem = new OaEosFlowItem();
            oaEosFlowItem.setFlowId(oaWxExtended.getFlow().getFlowId());
            oefiService.delete(oaEosFlowItem);
            oaWxExtended.setState("2");
            cancelAudit(oaWxExtended);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     *财务审核方法
     * @param proid
     * @param type
     * @return
     */
    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Transactional(rollbackFor = Exception.class , readOnly = false)
    public boolean appropriation(String proid, String type,String stop,String flowid) {
        type = type.replaceAll("\\s*", "");
        OaWxExtendedSuper oaWxExtendedSuper = oaWxExtendedSuperService.get(proid);
        try {
                oaWxExtendedSuper.setAppropriation("1");
                if ("lx".equals(oaWxExtendedSuper.getProItemType())) {
                    OaWxExtended oaWxExtended = new OaWxExtended();
                    oaWxExtended.setOaWxExtendedSuper(oaWxExtendedSuper);
                    List<OaWxExtended> oaWxExtendedList = dao.findBylistid(oaWxExtended);
                    OaEosProStartItem oaEosProStartItem = null;
                    OaEosProStartItem oaEosProStartItem1 = null;
                    for (OaWxExtended item : oaWxExtendedList) {
                        oaEosProStartItem1 = new OaEosProStartItem();
                        oaEosProStartItem1.setId(item.getProId());
                        oaEosProStartItem = oaEosProStartItemService.selByproid(oaEosProStartItem1);
                        if (null == oaEosProStartItem.getInputEstimation()) {
                            oaEosProStartItem.setInputEstimation(0.0);
                        }
                        double sum = oaEosProStartItem.getInputEstimation() + item.getCost();

                        oaEosProStartItem.setInputEstimation(sum);
                        oaEosProStartItemService.updateCumulative(oaEosProStartItem);

                    }

                    oaWxExtendedSuperService.updateApproPriAtion(oaWxExtendedSuper);
                } else if ("ylx".equals(oaWxExtendedSuper.getProItemType())) {
                    OaWxExtended oaWxExtended = new OaWxExtended();
                    oaWxExtended.setOaWxExtendedSuper(oaWxExtendedSuper);
                    List<OaWxExtended> oaWxExtendedList = dao.findBylistid(oaWxExtended);
                    OaEosProItem oaEosProItem = null;
                    OaEosProItem oaEosProItem1 = null;
                    for (OaWxExtended item : oaWxExtendedList) {
                        oaEosProItem1 = new OaEosProItem();
                        oaEosProItem1.setId(item.getProId());
                        oaEosProItem = oaEosProItemService.selByproid(oaEosProItem1);
                        if (null == oaEosProItem.getInputEstimation()) {
                            oaEosProItem.setInputEstimation(0.0);
                        }
                        Double sum = oaEosProItem.getInputEstimation() + item.getCost();
                        oaEosProItem.setInputEstimation(sum);
                        oaEosProItemService.updateCumulative(oaEosProItem);

                    }

                    oaWxExtendedSuperService.updateApproPriAtion(oaWxExtendedSuper);
                } else if ("bm".equals(oaWxExtendedSuper.getProItemType())) {
                    oaWxExtendedSuperService.updateApproPriAtion(oaWxExtendedSuper);
                } else if ("fxs".equals(oaWxExtendedSuper.getProItemType())) {
                    OaWxExtended oaWxExtended = new OaWxExtended();
                    oaWxExtended.setOaWxExtendedSuper(oaWxExtendedSuper);
                    List<OaWxExtended> oaWxExtendedList = dao.findBylistid(oaWxExtended);
                    OaEosProStartItem oaEosProStartItem = null;
                    OaEosProStartItem oaEosProStartItem1 = null;
                    for (OaWxExtended item : oaWxExtendedList) {
                        oaEosProStartItem1 = new OaEosProStartItem();
                        oaEosProStartItem1.setId(item.getProId());
                        oaEosProStartItem = oaEosProStartItemService.selByproid(oaEosProStartItem1);
                        if (null == oaEosProStartItem.getInputEstimation()) {
                            oaEosProStartItem.setInputEstimation(0.0);
                        }
                        double sum = oaEosProStartItem.getInputEstimation() + item.getCost();

                        oaEosProStartItem.setInputEstimation(sum);
                        oaEosProStartItemService.updateCumulative(oaEosProStartItem);

                    }
                    oaWxExtendedSuperService.updateApproPriAtion(oaWxExtendedSuper);
                }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<OaWxExtended> findBylistid(OaWxExtended oaWxExtended) {
        return dao.findBylistid(oaWxExtended);
    }

    /**
     * 已审核
     *
     * @return
     */
    public Page findProcessedList(Page<OaWxExtendedSuper> page, OaWxExtendedSuper wesuper) {

        User user = UserUtils.getUser();
        wesuper.setPage(page);
        page.setList(oaWxExtendedSuperService.findProcessedList((user.getId()),wesuper));
        return page;
    }

    /**
     * 未审核
     *
     * @return
     */
    public Page findUntreatedList(Page<OaWxExtendedSuper> page, OaWxExtendedSuper wesuper) {
        User user = UserUtils.getUser();
        wesuper.setPage(page);
        page.setList(oaWxExtendedSuperService.findUntreatedList((user.getId()),wesuper));
        return page;
    }

    /**
     * 财务未拨款
     * @param page
     * @param wesuper
     * @return
     */
    public Page Finance(Page<OaWxExtendedSuper> page, OaWxExtendedSuper wesuper){
        User user = UserUtils.getUser();
        wesuper.setPage(page);
        page.setList(oaWxExtendedSuperService.findFinanceList(wesuper));
        return  page;
    }


    /**
     * 部门报销添加
     * @param request
     * @param response
     * @throws ParseException
     */
    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Transactional( rollbackFor = Exception.class ,readOnly = false)
    public void bmbaoxiaosave(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String[] costs = request.getParameterValues("cost");
        String types=request.getParameter("proItemType");
        String fid = request.getParameter("fid");
        String[] fymxes = request.getParameterValues("fymx");
        String file = request.getParameter("file");
        String[] lx = request.getParameterValues("lx");
        List<OaWxExtended> list = new ArrayList<OaWxExtended>();
        Double money=0.0;
        for (int i = 0; i < costs.length; i++) {
            OaWxExtended oaWxExtended = new OaWxExtended();
            money+=Double.valueOf(costs[i])*100;
            oaWxExtended.setCost(Double.valueOf(costs[i])*100);
            oaWxExtended.setContent(fymxes[i]);
            oaWxExtended.setProId("0");
            oaWxExtended.setCreateDate(new Date());
            oaWxExtended.setState("0");
            list.add(oaWxExtended);
        }
        OaWxExtendedSuper oaWxExtendedSuper = new OaWxExtendedSuper();
        oaWxExtendedSuper.setEnclosure(file);
        oaWxExtendedSuper.setAppropriation("0");
        oaWxExtendedSuper.setProItemType(types);
        oaWxExtendedSuper.setRbsType("0");
        oaWxExtendedSuper.setProId(fid);
        oaWxExtendedSuperService1.save(oaWxExtendedSuper);
        OaWxBxCorrelationSuper oaWxBxCorrelationSuper=new OaWxBxCorrelationSuper();
        oaWxBxCorrelationSuper.setExtendedSuperId(oaWxExtendedSuper.getId());
        oaWxBxCorrelationSuper.setProId(fid);
        oaWxBxCorrelationSuperService.save(oaWxBxCorrelationSuper);
        String s = saveAudit(oaWxExtendedSuper, request,types);
        for (int i=0;i< list.size();i++) {
            list.get(i).setOaWxExtendedSuper(oaWxExtendedSuper);
            this.save(list.get(i));
            OaWxBxCorrelation oaWxBxCorrelation=new OaWxBxCorrelation();
            oaWxBxCorrelation.setDetailsId(list.get(i).getId());
            oaWxBxCorrelation.setClassificationId(lx[i]);
            oaWxBxCorrelation.setSuperId(oaWxBxCorrelationSuper.getId());
            oaWxBxCorrelationService.save(oaWxBxCorrelation);
        }
        accountService.AccountAdd(money/100,UserUtils.getUser().getId(),oaWxBxCorrelationSuper.getId());
    }

    /**
     * 项目报销添加
     * @param request
     * @param response
     * @throws ParseException
     */

    @Transactional( rollbackFor = Exception.class ,readOnly = false)
    public void baoxiaosave(HttpServletRequest request, HttpServletResponse response,List<String>  a) throws ParseException {
        String[] costs = request.getParameterValues("cost");
        String types=request.getParameter("proItemType");
        String fid = request.getParameter("fid");
        String[] fymxes = request.getParameterValues("fymx");
        String file = request.getParameter("file");
        String[] jd = request.getParameterValues("jd");
        String[] lx = request.getParameterValues("lx");
        List<OaWxExtended> list = new ArrayList<OaWxExtended>();
        double money=0.0;
        for (int i = 0; i < costs.length; i++) {
            OaWxExtended oaWxExtended = new OaWxExtended();
            money+=Double.valueOf(costs[i]) * 100;
            oaWxExtended.setCost(Double.valueOf(costs[i])*100);
            oaWxExtended.setContent(fymxes[i]);
            oaWxExtended.setProId("0");
            oaWxExtended.setCreateDate(new Date());
            oaWxExtended.setState("0");
            //oaWxExtended.setRemarks(a.get(i));
            list.add(oaWxExtended);
        }
        OaWxExtendedSuper oaWxExtendedSuper = new OaWxExtendedSuper();
        oaWxExtendedSuper.setEnclosure(file);
        oaWxExtendedSuper.setAppropriation("0");
        oaWxExtendedSuper.setProItemType(types);
        oaWxExtendedSuper.setProId(fid);
        oaWxExtendedSuper.setRbsType("0");
        oaWxExtendedSuperService1.save(oaWxExtendedSuper);
        OaWxBxCorrelationSuper oaWxBxCorrelationSuper=new OaWxBxCorrelationSuper();
        oaWxBxCorrelationSuper.setExtendedSuperId(oaWxExtendedSuper.getId());
        oaWxBxCorrelationSuper.setProId(fid);
        oaWxBxCorrelationSuperService.save(oaWxBxCorrelationSuper);
        String s = saveAudit(oaWxExtendedSuper, request,types);
        for (int i=0;i< list.size();i++) {
            list.get(i).setOaWxExtendedSuper(oaWxExtendedSuper);
            this.save(list.get(i));
            OaWxBxCorrelation oaWxBxCorrelation=new OaWxBxCorrelation();
            oaWxBxCorrelation.setDetailsId(list.get(i).getId());
            oaWxBxCorrelation.setStageId(jd[i]);
            oaWxBxCorrelation.setClassificationId(lx[i]);
            oaWxBxCorrelation.setSuperId(oaWxBxCorrelationSuper.getId());
            oaWxBxCorrelationService.save(oaWxBxCorrelation);

        }
        accountService.AccountAdd(money/100,UserUtils.getUser().getId(),oaWxBxCorrelationSuper.getId());
    }


    /**
     * 预立项报销审核
     * @param proid
     * @param flowid
     * @param content
     * @return
     */
    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public boolean Auditylx(String proid, String flowid, String content,String stop) {
        OaWxExtendedSuper oaWxExtendedSuper1=new OaWxExtendedSuper();
        OaWxExtendedSuper oaWxExtendedSuper=oaWxExtendedSuperService1.get(proid);
        Map<String, String> returnmap=null;
        OaWxBxCorrelationSuper oaWxBxCorrelationSuperService2=null;
        OaWxExtendedSuper byid =null;
            oaWxExtendedSuper1.setId(proid);
            byid = oaWxExtendedSuperService.findByid(oaWxExtendedSuper1);
             oaWxBxCorrelationSuperService2 = oaWxBxCorrelationSuperService.get2(byid.getId());
            User user1 = userDao.get(oaWxExtendedSuper.getCreateBy().getId());
            String message = "您有新的报销信息需要审批，请到OA应用确认审批。\n申请人："+user1.getName()+"\n申请日期："+DateUtils.formatDateTimeF(oaWxExtendedSuper.getCreateDate())+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2ftwo%2fDetails%3Fidss%3D" + oaWxBxCorrelationSuperService2.getId() +"%26type%3D"+oaWxExtendedSuper.getProItemType()+"%26sh%3D"+0+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
            String finishmessage = "";//"报销信息审批完成。\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2fbx%2Details?idss=" + byid.getId() + "&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
            returnmap = oefiService.auditFlow(flowid, true, message, finishmessage, content);
        if (!"true".equals(returnmap.get("success"))) {
            oaWxExtendedSuper.setState("-1");
            oaWxExtendedSuperService.updateAudit(oaWxExtendedSuper);
            return false;
        } else {
                if ("true".equals(returnmap.get("pass"))) {
                    List<User> cfos = UserUtils.getByRole(financeAuditRole);
                    StringBuffer sb = new StringBuffer();

                    for (User item : cfos) {
                        if (item.getWxUsers() == null) {
                            continue;
                        } else {
                            sb.append(item.getWxUsers().getUserid() + "|");
                        }
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    String message1 = "报销信息审批完成请确认拨款。\n申请人："+user1.getName()+"\n申请日期："+ DateUtils.formatDateTimeF(oaWxExtendedSuper.getCreateDate())+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2ftwo%2fDetails%3Fidss%3D" + oaWxBxCorrelationSuperService2.getId() +"%26success%3Dtrue" +"%26type%3D"+oaWxExtendedSuper.getProItemType()+"%26sh%3D"+0+ "&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
                    oefiService.sendMsg(sb.toString(), message1);
                    oaWxExtendedSuper.setState("1");
                    oaWxExtendedSuperService.updateAudit(oaWxExtendedSuper);
                }
        }
        return true;
    }


    @Autowired
    OaEosProService oaEosProService;

    @Autowired
    OaEosProStartService oaEosProStartService;

    @Autowired
    OaEosProUnService oaEosProUnService;
    @Autowired
    OaEosProDao oaEosProDao;

    /**
     * 财务审核基本报销方法
     * @param proid
     * @param type
     * @param stop
     * @param flowid
     * @param content
     * @return
     */
    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Transactional(rollbackFor = Exception.class , readOnly = false)
    public boolean appropriationbaoxiao(String proid, String type,String stop,String flowid,String content) {
        User user = UserUtils.getUser();
        type = type.replaceAll("\\s*", "");
        OaWxBxCorrelationSuper oaWxBxCorrelationSuperService1 = oaWxBxCorrelationSuperService.get(proid);
        OaWxExtendedSuper oaWxExtendedSuper=null;
            oaWxExtendedSuper = oaWxExtendedSuperService.get(oaWxBxCorrelationSuperService1.getExtendedSuperId());
            oaWxExtendedSuper.setRemarks(content);
            oaWxExtendedSuper.setUpdateBy(user);
        try {
                oaWxExtendedSuper.setAppropriation("1");
                if ("ylx".equals(oaWxExtendedSuper.getProItemType())) {
                    OaWxExtended oaWxExtended = new OaWxExtended();
                    oaWxExtended.setOaWxExtendedSuper(oaWxExtendedSuper);
                    List<OaWxExtended> oaWxExtendedList = dao.findBylistid(oaWxExtended);
                    OaEosPro oaEosPro = null;
                    oaEosPro = oaEosProService.get(oaWxExtendedSuper.getProId());
                    for (OaWxExtended item : oaWxExtendedList) {
                        if (null == oaEosPro.getInputEstimation()) {
                            oaEosPro.setRealInputEstimation(0.0);
                        }
                        oaEosPro.setRealInputEstimation(oaEosPro.getRealInputEstimation() + item.getCost());
                    }
                    oaEosProDao .update(oaEosPro);
                    oaWxExtendedSuperService.update(oaWxExtendedSuper);
                } else if ("lx".equals(oaWxExtendedSuper.getProItemType())) {
                    OaWxExtended oaWxExtended = new OaWxExtended();
                    oaWxExtended.setOaWxExtendedSuper(oaWxExtendedSuper);
                    List<OaWxExtended> oaWxExtendedList = dao.findBylistid(oaWxExtended);
                    OaEosProStart oaEosProStart = null;
                    oaEosProStart = oaEosProStartService.get(oaWxExtendedSuper.getProId());
                    for (OaWxExtended item : oaWxExtendedList) {
                        if (null == oaEosProStart.getRealInputEstimation()) {
                            oaEosProStart.setRealInputEstimation(0.0);
                        }
                        oaEosProStart.setRealInputEstimation(oaEosProStart.getRealInputEstimation() + item.getCost());
                    }
                    oaEosProStartService.updatamoney(oaEosProStart);
                    oaWxExtendedSuperService.update(oaWxExtendedSuper);
                } else if ("bm".equals(oaWxExtendedSuper.getProItemType())) {
                    oaWxExtendedSuperService.update(oaWxExtendedSuper);
                } else if ("fxs".equals(oaWxExtendedSuper.getProItemType())) {
                    OaWxExtended oaWxExtended = new OaWxExtended();
                    oaWxExtended.setOaWxExtendedSuper(oaWxExtendedSuper);
                    List<OaWxExtended> oaWxExtendedList = dao.findBylistid(oaWxExtended);
                    OaEosProUn oaEosProUn = null;
                    oaEosProUn = oaEosProUnService.get(oaWxExtendedSuper.getProId());
                    for (OaWxExtended item : oaWxExtendedList) {
                        if (null == oaEosProUn.getRealInputEstimation()) {
                            oaEosProUn.setRealInputEstimation(0.0);
                        }
                        double sum = oaEosProUn.getRealInputEstimation() + item.getCost();
                        oaEosProUn.setRealInputEstimation(sum);
                    }
                    oaEosProUnService.updatamoney(oaEosProUn);
                    oaWxExtendedSuperService.update(oaWxExtendedSuper);
                }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 基本报销撤销方法
     * @param user
     * @param id
     * @return
     */
    @SuppressWarnings("AlibabaTransactionMustHaveRollback")
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public boolean baoxiaodelcx(User user, String id) {
        OaWxBxCorrelationSuper oaWxBxCorrelationSuper = oaWxBxCorrelationSuperService.get(id);

        OaWxExtendedSuper oaWxExtended1 = new OaWxExtendedSuper();
        oaWxExtended1.setCreateBy(user);
        oaWxExtended1.setId(oaWxBxCorrelationSuper.getExtendedSuperId());
        try {
            OaWxExtendedSuper oaWxExtended = oaWxExtendedSuperService.findByid(oaWxExtended1);
            OaEosFlowItem oaEosFlowItem = new OaEosFlowItem();
            oaEosFlowItem.setFlowId(oaWxExtended.getFlow().getFlowId());
            oefiService.delete(oaEosFlowItem);
            oaWxExtended.setState("2");
            oaWxExtendedSuperService.updateAudit(oaWxExtended);
            cancelAudit(oaWxExtended);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<OaWxExtended> findBySuperProId (OaWxExtended oaWxExtended){
        return  dao.findBySuperProId(oaWxExtended);
    }

    public List<PageData> proCount(PageData pd){
        return  dao.proCount(pd);
    }

    public  List<PageData> proCountItem(PageData pd){
        return  dao.proCountItem(pd);
    }

    /**
     * 财务驳回发送一条通知给报销申请人
     * @param proid
     * @param flowid
     * @param content
     * @return
     */
    @Transactional(readOnly = false)
    public boolean AuditProBack(String proid,String flowid,String content){
        System.out.println(proid);
        User user= UserUtils.getUser();
        OaWxBxCorrelationSuper oaWxBxCorrelationSuperService2 = oaWxBxCorrelationSuperService.get(proid);
        OaWxExtendedSuper  oaWxExtendedSuper=  oaWxExtendedSuperService1.get(oaWxBxCorrelationSuperService2.getExtendedSuperId());
        String finishmessage="你的报销审批被"+user.getName()+"驳回。\n 驳回原因："+content+"\n驳回时间："+DateUtils.formatDateTimeF(new Date())+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2ftwo%2fDetails%3Fidss%3D" + oaWxBxCorrelationSuperService2.getId() +"%26success%3Dtrue" +"%26type%3D"+oaWxExtendedSuper.getProItemType()+"%26sh%3D"+1+ "&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
            OaWxExtendedSuper prostart=oaWxExtendedSuper;
            prostart.setState("-1");
            prostart.setAppropriation("2");
            prostart.setRemarks(content);
            prostart.setUpdateBy(user);
            oaWxExtendedSuperService.update(prostart);
            User u=userDao.get(prostart.getCreateBy().getId());
            if(u.getWxUsers()!=null) {
                oefiService.sendMsg(u.getWxUsers().getUserid(), finishmessage);//流程执行完成通知用户
            }
            OaWxExtended extended=new OaWxExtended();
            extended.setOaWxExtendedSuper(oaWxExtendedSuper);
            cancelAudit(oaWxExtendedSuper);
        return true;
    }

    /**
     *基本报销驳回发送一条通知给报销申请人
     * @param proid
     * @param flowid
     * @param content
     * @return
     */
    @Transactional(readOnly = false)
    public boolean out(String proid,String flowid,String content){
        System.out.println(proid);
        User user= UserUtils.getUser();
        OaWxExtendedSuper  oaWxExtendedSuper=  oaWxExtendedSuperService1.get(proid);
        OaWxBxCorrelationSuper oaWxBxCorrelationSuperService2 = oaWxBxCorrelationSuperService.get2(oaWxExtendedSuper.getId());
        String finishmessage="你的报销审批被"+user.getName()+"驳回。\n 驳回原因："+content+"\n驳回时间："+DateUtils.formatDateTimeF(new Date())+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2ftwo%2fDetails%3Fidss%3D" + oaWxBxCorrelationSuperService2.getId() +"%26success%3Dtrue" +"%26type%3D"+oaWxExtendedSuper.getProItemType()+"%26sh%3D"+1+ "&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
        Map<String,String> returnmap=oefiService.rejectFlow(flowid,content);
        //基本报销驳回 添加报销启动数据
        OaWxExtendedSuper prostart=oaWxExtendedSuper;
        prostart.setState("-1");
        prostart.setAppropriation("2");
        oaWxExtendedSuperService.update(prostart);
        User u=userDao.get(prostart.getCreateBy().getId());
        if(u.getWxUsers()!=null) {
            oefiService.sendMsg(u.getWxUsers().getUserid(), finishmessage);//流程执行完成通知用户
        }
        cancelAudit(oaWxExtendedSuper);
        return true;
    }

    /***
     * 撤销驳回类 个人用户变更
     * @param oaWxExtendedSuper
     */
    public void cancelAudit (OaWxExtendedSuper oaWxExtendedSuper){
        OaWxExtended extended=new OaWxExtended();
        extended.setOaWxExtendedSuper(oaWxExtendedSuper);
        List<OaWxExtended> extentendlist=oaWxExtendedDao.findBylistid(extended);
        Double money=0.0;
        for(OaWxExtended ex:extentendlist){
            money+=ex.getCost();
        }
        accountService.AccountAdd(-money,UserUtils.getUser().getId(),oaWxExtendedSuper.getId());
    }

}