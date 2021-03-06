package com.lingnan.usersys.common.exception;

/**
 * Service异常类
 * @author 曾洁义
 *
 */
public class ServiceException extends RuntimeException {
	/**
	 * 默认的构造方法
	 */
	public ServiceException() {
	}
	
	/**
	 * 构造方法
	 * @param arg0  异常的详细信息
	 */
	public ServiceException(String arg0) {
		super(arg0);//调用父类的super(String)构造方法，重写异常的详细信息	
	}
	
	/**
	 * 构造方法
	 * @param arg0  产生异常的原因
	 */
	public ServiceException(Throwable arg0) {
		super(arg0);//调用父类的super(Throwable)构造方法，重写产生异常的原因		
	}
	
	/**
	 * 构造方法
	 * @param arg0  异常的详细信息
	 * @param arg1  产生异常的原因
	 */
	public ServiceException(String arg0,Throwable arg1) {
		super(arg0,arg1);//调用父类的super(String,Throwable)构造方法，重写异常的详细信息和异常产生的原因		
	}
}
