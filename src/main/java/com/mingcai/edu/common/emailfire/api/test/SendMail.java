package com.mingcai.edu.common.emailfire.api.test;

import java.io.File;

import com.mingcai.edu.common.emailfire.api.handler.SendMailHandler;
import com.mingcai.edu.common.emailfire.api.util.Base64Util;
import com.mingcai.edu.common.mapper.PutEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SendMail {

	private static final int pid = 43309;//PID 需联系在线客服索取。
	private static final String secretKey="19c3c28ddb914f71ad17bb915fac09ba";//secretKey 需联系在线客服索取。
	public static void main(String[] args) {
		//无附件发送。
		PutEntity putmail=new PutEntity();
		putmail.setTitle("任务通知");
		putmail.setContent("城建学院项目分配任务：是男是女任务，请前往平台确认任务状态！");
		putmail.setRecipient("jiangkun@mingcaiedu.com");
		putmail.setDisplayName("江坤");
		putmail.setReplay("jiangkun@mingcaiedu.com");
		singleSender(putmail);
		//单个附件发送，需要修改自己对应的附件名称和附件路径
		//attSingleSender();
		//多个附件发送，需要修改自己对应的附件名称和附件路径
		//attMultiSender();
	}

	//无附件发送。
	public static void singleSender(PutEntity putmail){

		SendMailHandler smh = new SendMailHandler(pid,secretKey);
		String title =putmail.getTitle();//邮件标题
		String content=putmail.getContent();
		String recipient=putmail.getRecipient();//收件人邮箱service@XXX.dataea.com
		String fromEmail="mingcai@oa.mingcaiedu.com";//service@mail.dataea.com 发件邮箱。正式使用可以绑定自己的域名作为发件人。
		String displayName=putmail.getDisplayName();//发件人名称，可自行修改，如：产品名称
		String replay=putmail.getReplay();//回复邮箱地址
		boolean statClick=true;

		String result = smh.sendMessage(title,content,statClick,fromEmail,recipient,replay,displayName);

		System.out.println("result="+result);
	}
	
	//单个附件发送
	public static void attSingleSender(){
		//附件名称
		String fileName = "yhf_server.csv";//根据自己实际情况修改
		//附件地址
		String filePath = "/Users/penggaolong/Documents/kewail/yhf_server.csv";//根据自己实际情况修改
		
		File file = new File(filePath);
		String fileContent = Base64Util.encode(file);
		JSONObject attJson = new JSONObject();
		attJson.put("name", fileName);
		attJson.put("content", fileContent);
		
		JSONArray array = new JSONArray();
		array.add(0, attJson);
		String attachment = array.toString();
		
		System.out.println(attachment);
		
		SendMailHandler smh = new SendMailHandler(pid,secretKey);
		String title ="您好,账号激活验证码,戳开看看!附件发送";//邮件标题
		String content="您好，感谢您使用我们xxx平台，验证码：JSEA。请把该验证码复制到激活界面进行激活。<br/>该邮件为系统自动发出，请勿回复谢谢！";
		String recipient="185141532@qq.com";//收件人邮箱
		String fromEmail="techno@emailfire.cn";//发件邮箱。正式使用可以绑定自己的域名作为发件人。
		String displayName="EmailFire";//发件人名称，可自行修改，如：产品名称
		String replay="185141532@qq.com";//回复邮箱地址
		boolean statClick=true;
		
		String result = smh.sendMessage(title,content,attachment,statClick,fromEmail,recipient,replay,displayName);
		
		System.out.println("result="+result);
	}
	
	//多个附件发送
	public static void attMultiSender(){
		//附件名称1
		String fileName1 = "测试文件1.txt";//根据自己实际情况修改
		//附件地址1
		String filePath1 = "C:/测试文件1.txt";//根据自己实际情况修改
		
		//附件名称2
		String fileName2 = "测试文件2.txt";//根据自己实际情况修改
		//附件地址2
		String filePath2 = "C:/测试文件2.txt";//根据自己实际情况修改
		
		//附件名称3
		String fileName3 = "测试文件3.txt";//根据自己实际情况修改
		//附件地址3
		String filePath3 = "C:/测试文件3.txt";//根据自己实际情况修改
		
		/**
		 * 更多同上，或者用数组遍历
		 */
		
		File file1 = new File(filePath1);
		String fileContent1 = Base64Util.encode(file1);
		JSONObject attJson1 = new JSONObject();
		attJson1.put("name", fileName1);
		attJson1.put("content", fileContent1);
		
		File file2 = new File(filePath2);
		String fileContent2 = Base64Util.encode(file2);
		JSONObject attJson2 = new JSONObject();
		attJson2.put("name", fileName2);
		attJson2.put("content", fileContent2);
		
		File file3 = new File(filePath3);
		String fileContent3 = Base64Util.encode(file3);
		JSONObject attJson3 = new JSONObject();
		attJson3.put("name", fileName3);
		attJson3.put("content", fileContent3);
		
		JSONArray array = new JSONArray();
		array.add(0, attJson1);
		array.add(1, attJson2);
		array.add(2, attJson3);
		
		String attachment = array.toString();
		
		SendMailHandler smh = new SendMailHandler(pid,secretKey);
		String title ="您好,账号激活验证码,戳开看看!";//邮件标题
		String content="您好，感谢您使用我们xxx平台，验证码：JSEA。请把该验证码复制到激活界面进行激活。<br/>该邮件为系统自动发出，请勿回复谢谢！";
		String recipient="xxxx@xxx.com";//收件人邮箱
		String fromEmail="service@mail.dataea.com";//发件邮箱。正式使用可以绑定自己的域名作为发件人。
		String displayName="EmailFire";//发件人名称，可自行修改，如：产品名称
		String replay="xxxx@xxx.com";//回复邮箱地址
		boolean statClick=true;
		
		String result = smh.sendMessage(title,content,attachment,statClick,fromEmail,recipient,replay,displayName);
		
		System.out.println("result="+result);
	}
}
