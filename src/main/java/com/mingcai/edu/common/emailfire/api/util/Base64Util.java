package com.mingcai.edu.common.emailfire.api.util;

import com.sun.xml.internal.messaging.saaj.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Base64Util {
	// 附件base64加密
	public static String encode(File file) {
		String content = null;
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			StringBuffer sbuffer = new StringBuffer();
			byte[] enbase64 = Base64.encode(b);
			for (int i = 0; i < enbase64.length; i++) {
				char c = (char) enbase64[i];
				sbuffer.append(c);
			}
			content = sbuffer.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

}
