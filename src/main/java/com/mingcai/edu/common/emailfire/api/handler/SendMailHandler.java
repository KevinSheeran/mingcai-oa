package com.mingcai.edu.common.emailfire.api.handler;

import java.util.ArrayList;
import java.util.List;

import com.mingcai.edu.common.emailfire.api.util.HttpUtil;
import com.mingcai.edu.common.emailfire.api.util.MD5;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


public class SendMailHandler {
			
	private int pid;
	private String secretKey;
	private static String createTaskUrl="http://api.emailfire.cn/api/createTask.action";
	private static String sendMailUrl="http://api.emailfire.cn/api/sendMail.action";
	
	public SendMailHandler(int pid, String secretKey) {
		this.pid = pid;
		this.secretKey = secretKey;
	}
	
	/**
	 * 
	 * @param title 标题
	 * @return 结果
	 */
	public String createTask(String title) {

		String timestamp = String.valueOf(System.currentTimeMillis());

		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append(pid);
		sbuffer.append(timestamp);
		sbuffer.append(secretKey);
		String sign = MD5.MD5(sbuffer.toString());

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("pid", String.valueOf(pid)));
		nvps.add(new BasicNameValuePair("title", title));
		nvps.add(new BasicNameValuePair("timestamp", timestamp));
		nvps.add(new BasicNameValuePair("sign", sign));

		String result = null;
		try {
			result = HttpUtil.doPost(createTaskUrl, nvps);// 返回code
			/**
			 * 其它业务处理
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @param taskId
	 *            任务编号
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 * @param statClick
	 *            是否统计链接点击
	 * @param recipient
	 *            收件人
	 * @param replay
	 *            回复地址
	 * @param displayName
	 *            发件人(邮件标题列表显示发件人的名称)
	 * @return 发送结果
	 */
	public String sendMessage(int taskId, String title, String content,boolean statClick, String fromEmail, String recipient,String replay, String displayName) {

		String timestamp = String.valueOf(System.currentTimeMillis());
		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append(pid);
		sbuffer.append(taskId);
		sbuffer.append(timestamp);
		sbuffer.append(secretKey);
		String sign = MD5.MD5(sbuffer.toString());

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("pid", String.valueOf(pid)));
		nvps.add(new BasicNameValuePair("title", title));
		nvps.add(new BasicNameValuePair("taskId", String.valueOf(taskId)));
		nvps.add(new BasicNameValuePair("timestamp", timestamp));
		nvps.add(new BasicNameValuePair("sign", sign));
		nvps.add(new BasicNameValuePair("replay", replay));
		nvps.add(new BasicNameValuePair("displayName", displayName));
		nvps.add(new BasicNameValuePair("fromEmail", fromEmail));
		nvps.add(new BasicNameValuePair("recipient", recipient));
		nvps.add(new BasicNameValuePair("content", content));
		nvps.add(new BasicNameValuePair("statClick", String.valueOf(statClick)));

		String result = null;
		try {
			result = HttpUtil.doPost(sendMailUrl, nvps);
			/**
			 * 其它业务处理
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @param taskId
	 *            任务编号
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 * @param attachments
	 * 			  附件       
	 * @param statClick
	 *            是否统计链接点击
	 * @param recipient
	 *            收件人
	 * @param replay
	 *            回复地址
	 * @param displayName
	 *            发件人(邮件标题列表显示发件人的名称)
	 * @return 发送结果
	 */
	public String sendMessage(String title, String content,String attachments,boolean statClick, String fromEmail, String recipient,String replay, String displayName) {

		String timestamp = String.valueOf(System.currentTimeMillis());
		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append(pid);
		sbuffer.append(timestamp);
		sbuffer.append(secretKey);
		String sign = MD5.MD5(sbuffer.toString());

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("pid", String.valueOf(pid)));
		nvps.add(new BasicNameValuePair("title", title));
		nvps.add(new BasicNameValuePair("timestamp", timestamp));
		nvps.add(new BasicNameValuePair("sign", sign));
		nvps.add(new BasicNameValuePair("replay", replay));
		nvps.add(new BasicNameValuePair("displayName", displayName));
		nvps.add(new BasicNameValuePair("fromEmail", fromEmail));
		nvps.add(new BasicNameValuePair("recipient", recipient));
		nvps.add(new BasicNameValuePair("content", content));
		nvps.add(new BasicNameValuePair("attachments", attachments));
		nvps.add(new BasicNameValuePair("statClick", String.valueOf(statClick)));

		String result = null;
		try {
			result = HttpUtil.doPost(sendMailUrl, nvps);
			/**
			 * 其它业务处理
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 * @param statClick
	 *            是否统计链接点击
	 * @param recipient
	 *            收件人
	 * @param replay
	 *            回复地址
	 * @param displayName
	 *            发件人(邮件标题列表显示发件人的名称)
	 * @return 发送结果
	 */
	public String sendMessage(String title, String content,boolean statClick, String fromEmail, String recipient,String replay, String displayName) {

		String timestamp = String.valueOf(System.currentTimeMillis());
		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append(pid);
		sbuffer.append(timestamp);
		sbuffer.append(secretKey);
		String sign = MD5.MD5(sbuffer.toString());

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("pid", String.valueOf(pid)));
		nvps.add(new BasicNameValuePair("title", title));
		nvps.add(new BasicNameValuePair("timestamp", timestamp));
		nvps.add(new BasicNameValuePair("sign", sign));
		nvps.add(new BasicNameValuePair("replay", replay));
		nvps.add(new BasicNameValuePair("displayName", displayName));
		nvps.add(new BasicNameValuePair("fromEmail", fromEmail));
		nvps.add(new BasicNameValuePair("recipient", recipient));
		nvps.add(new BasicNameValuePair("content", content));
		nvps.add(new BasicNameValuePair("statClick", String.valueOf(statClick)));

		String result = null;
		try {
			result = HttpUtil.doPost(sendMailUrl, nvps);
			/**
			 * 其它业务处理
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
