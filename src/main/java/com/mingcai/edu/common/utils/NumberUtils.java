package com.mingcai.edu.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 文件上传工具类
 *
 * @author yangdc
 * @date Apr 18, 2012
 *
 * <pre>
 * </pre>
 */
public class NumberUtils {
	public static String gnumberFarmat(Double doub){
		NumberFormat nf = new DecimalFormat("#,###.####");
		String str = nf.format(doub);
		return str;
	}
	public static String gnumberFarmatTwo(Double doub){
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(doub);
	}
}
