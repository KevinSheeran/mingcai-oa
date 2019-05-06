package com.mingcai.edu.modules.oa.utils;

import com.mingcai.edu.common.utils.DateUtils;
import com.mingcai.edu.common.utils.IdGen;
import com.mingcai.edu.common.utils.SpringContextHolder;
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
public class AccountUtils {
    private static OaUserAccountDao accountDao = SpringContextHolder.getBean(OaUserAccountDao.class);
    //获取销售用户账户
    public static OaUserAccount getAccount(String userid){
        OaUserAccount account=accountDao.get(userid);
        return account;
    }

}
