package com.mingcai.edu.common.emailfire.api.test;


import com.mingcai.edu.common.emailfire.api.handler.SendMailHandler;

public class CreateTask {
	private static final int pid = 0;//PID 需联系在线客服索取。
	private static final String secretKey="";//secretKey 需联系在线客服索取。
	
	public static void main(String[] args) {
		SendMailHandler smh = new SendMailHandler(pid,secretKey);
		
		String title = "验证码发送";
		String result = smh.createTask(title);
		
		System.out.print("result="+result);
	}

}
