package com.lingnan.usersys.common.test;


import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.lingnan.usersys.common.util.TypeUtils;

public class TypeUtilsTest {

	@Test
	public void testStrToDate() {
//		String str = "2018-03-01";//测试方法的参数
//		TypeUtils t = new TypeUtils();
//		Date date = t.strToDate(str);//调用测试方法
//		System.out.println(date);//输出测试结果
		System.out.println(TypeUtils.strToDate("2017-09-19"));
	}

	@Test
	public void testDateToString() {
//		Date date = new Date(2018,9,1);
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//		TypeUtils t = new TypeUtils();
//		String str = t.dateToString(date);
//		System.out.println(str);
		System.out.println(TypeUtils.dateToString(new Date(2018,1,1)));
	}
	
	@Test
	public void testCheckEmail() {
//		String email = "12345@";
//		TypeUtils t = new TypeUtils();
//		t.checkEmail(email);	
		TypeUtils.checkEmail("2018@123mandy.com");
	}

}
