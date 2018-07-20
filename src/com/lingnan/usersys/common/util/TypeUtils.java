package com.lingnan.usersys.common.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import com.lingnan.usersys.common.exception.DateException;

/**
 * 类型转换工具类
 * @author 曾洁义
 *
 */
public class TypeUtils {

	/**
	 * 字符串转换为日期
	 * @param str 指定的字符串
	 * @return 转换后的日期
	 */
	public static Date strToDate(String str) {
		Date date = null;		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置要格式化的日期格式
		try {		
			date = sdf.parse(str);//调用parse方法，将字符串解析成指定格式的日期类型
		} catch (ParseException e) {
			throw new DateException("字符串转换为日期出错",e);
		}		
		return date;//返回转换后的值
	}
	
	/**
	 * 日期转换为字符串
	 * @param date 指定的日期
	 * @return 转换后的字符串
	 */
	public static String dateToString(Date date) {
		String str = null;		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置要格式化的日期格式
		try{			
			str = sdf.format(date);//调用format方法，将日期解析成指定格式的字符串
		} catch (Exception e) {
			throw new DateException("日期转换为字符串出错",e);
		}		
		return str;//返回转换后的值
	}
	
	/**
	 * 检查邮箱格式是否正确
	 * @param email 指定的邮箱
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean checkEmail(String email) {
		String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";//设定邮箱格式  
		boolean flag = Pattern.matches(REGEX_EMAIL, email);//调用matches方法，验证邮箱格式是否正确
		if(flag) {
//			System.out.println("邮箱格式正确");
		} else {
			System.out.println("邮箱格式不正确......");
			System.out.print(">>请重新输入邮箱：");
		}
		return flag;//返回校验结果
	}
}
