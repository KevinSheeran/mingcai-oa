/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.wx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mingcai.edu.common.utils.IdGen;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.dao.wx.OaWxDepartmentUsersDao;
import com.mingcai.edu.modules.oa.dao.wx.OaWxUsersDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProStartItem;
import com.mingcai.edu.modules.oa.entity.wx.OaWxDepartmentUsers;
import com.mingcai.edu.modules.oa.entity.wx.OaWxUsers;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.wx.OaWxDepartment;
import com.mingcai.edu.modules.oa.dao.wx.OaWxDepartmentDao;

/**
 * 微信部门Service
 * @author kun
 * @version 2019-03-05
 */
@Service
@Transactional(readOnly = true)
public class OaWxDepartmentService extends CrudService<OaWxDepartmentDao, OaWxDepartment> {
	@Autowired
	OaWxUsersDao udao;
    @Autowired
    OaWxDepartmentUsersDao dudao;
	public OaWxDepartment get(String id) {
		return super.get(id);
	}
	
	public List<OaWxDepartment> findList(OaWxDepartment oaWxDepartment) {
		return super.findList(oaWxDepartment);
	}
	
	public Page<OaWxDepartment> findPage(Page<OaWxDepartment> page, OaWxDepartment oaWxDepartment) {
		return super.findPage(page, oaWxDepartment);
	}
	
	@Transactional(readOnly = false)
	public void save(List<OaWxDepartment> oaWxDepartment) {
		if(oaWxDepartment.size()>0){
				dao.delete("");
				udao.delete("");
                dudao.delete("");
                Map<String,OaWxUsers> dumap=new HashMap<String,OaWxUsers>();
                List<OaWxDepartmentUsers> duserlist=new ArrayList<OaWxDepartmentUsers>();
                //添加部门
				for(OaWxDepartment owd:oaWxDepartment){
						dao.insert(owd);
						if(owd.getUsers()!=null&&owd.getUsers().size()>0) {
							for (OaWxUsers user : owd.getUsers()) {
								String str1= StringUtils.join(user.getDepartment(), ",");
								user.setDepartment_s(","+str1+",");
								user.setOrder_s(","+StringUtils.join(user.getOrder(), ",")+",");
								user.setIs_leader_in_dept_s(","+StringUtils.join(user.getIs_leader_in_dept(), ",")+",");
								user.setId(IdGen.uuid());
								user.getUserid();
								user.setPinyin(getPinYin(user.getName()));
								user.setMobile(StringUtils.isNotEmpty(user.getMobile())?user.getMobile():null);
                                user.setAvatar(StringUtils.isNotEmpty(user.getAvatar())?user.getAvatar().substring(0,user.getAvatar().lastIndexOf("/")):"");
								//过滤重复用户
                                dumap.put(user.getUserid(),user);
							}
						}
				}

				//保存微信用户信息 及部门关联信息
            for(String us:dumap.keySet()){
                OaWxUsers user=dumap.get(us);
                udao.insert(user);
                int i=0;
                for(Integer num:user.getDepartment()){
                    OaWxDepartmentUsers oduser=new OaWxDepartmentUsers();
                    oduser.setUserid(user.getUserid());
                    oduser.setId(num.toString());
                    oduser.setIsLeaderInDept(user.getIs_leader_in_dept()[i]);
                    oduser.setOrder(user.getOrder()[i]);
                    dudao.insert(oduser);
                    i++;
                }
            }

		}
	}
	/**
	 * 将汉字转换为全拼
	 *
	 * @param src
	 * @return String
	 */
	public static String getPinYin(String src)
	{
		if(src == null || "".endsWith(src)) {
			return "";
		}
		char[] words = src.toCharArray();
		int wordCount = words.length;
		String[] pinyin = new String[wordCount];

		// 设置汉字拼音输出的格式
		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
		outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

		StringBuffer sb = new StringBuffer();
		try
		{
			for (int i = 0; i < wordCount; i++)
			{
				// 判断能否为汉字字符
				// System.out.println(t1[i]);
				if (Character.toString(words[i]).matches("[\\u4E00-\\u9FA5]+"))
				{
					pinyin = PinyinHelper.toHanyuPinyinStringArray(words[i], outputFormat);// 将汉字的几种全拼都存到t2数组中
					sb.append(pinyin[0]);// 取出该汉字全拼的第一种读音并连接到字符串t4后
				}
				else
				{
					// 如果不是汉字字符，间接取出字符并连接到字符串t4后
					sb.append(Character.toString(words[i]));
				}
			}
		}
		catch (BadHanyuPinyinOutputFormatCombination e)
		{
			e.printStackTrace();
		}
		return sb.toString();
	}
	@Transactional(readOnly = false)
	public void delete(OaWxDepartment oaWxDepartment) {
		super.delete(oaWxDepartment);
	}

	public OaWxDepartment selByproid(OaWxDepartment oaWxDepartment){
		return dao.selByproid(oaWxDepartment);
	}
}