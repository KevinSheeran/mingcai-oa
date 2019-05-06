package com.mingcai.edu.modules.oa.utils;
import com.mingcai.edu.common.utils.DateUtils;
import com.mingcai.edu.common.utils.IdGen;
import com.mingcai.edu.modules.oa.dao.user.OaUserAccountDao;
import com.mingcai.edu.modules.oa.dao.user.OaUserAccountLogDao;
import com.mingcai.edu.modules.oa.entity.user.OaUserAccount;
import com.mingcai.edu.modules.oa.entity.user.OaUserAccountLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
@Lazy(false)
public class AccountService {
    @Autowired
    OaUserAccountDao oaUserAccountDao;
    @Autowired
    OaUserAccountLogDao oaUserAccountLogDao;
    /**
     * 设置定时任务600秒启动一次该方法
     */
    //@Scheduled(cron="0/5 * * * * ?")
    public void InitData() {
        OaUserAccount oaUserAccount=new OaUserAccount();
        List<OaUserAccount> userlist=oaUserAccountDao.findList(oaUserAccount);
        for(OaUserAccount account:userlist){
            String nowdate= DateUtils.getYearMonth();
            if(account.getBeginEndMonth()==null||!nowdate.equals(DateUtils.formatYearMonth(account.getBeginEndMonth()))){
                OaUserAccountLog log=new OaUserAccountLog();
                log.setUserId(account.getId());
                log.setBeginEndMonth(account.getBeginEndMonth());
                log.setBranchQuota(account.getBranchQuota());
                log.setCostIncurred(account.getCostIncurred());
                log.setFlag(account.getFlag());
                log.setPoolFunds(account.getPoolFunds());
                log.setSalesVolumeId(account.getSalesVolumeId());
                log.setSpareA(account.getSpareA());
                log.setSpareB(account.getSpareB());
                log.setSpareC(account.getSpareC());
                log.setStringA(account.getStringA());
                log.setStringB(account.getStringB());
                log.setStringC(account.getStringC());
                log.setCreateBy(account.getCreateBy());
                log.setCreateDate(account.getCreateDate());
                log.setUpdateBy(account.getUpdateBy());
                log.setUpdateDate(account.getUpdateDate());
                log.setId(IdGen.uuid());
                oaUserAccountLogDao.insert(log);
                account.setBeginEndMonth(new Date());
                account.setCostIncurred(0.0);
                oaUserAccountDao.update(account);
            }

        }

    }
}
