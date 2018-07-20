package com.lingnan.usersys.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lingnan.usersys.common.exception.DaoException;

/**
 * 数据库工具类
 * @author 曾洁义
 *
 */
public class DBUtils {

	//定义成员变量
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
	private static String username = "scott";
	private static String password = "123";
	private static Connection conn = null;	
	
	/**
	 * 获取数据库连接
	 * @return 
	 */
	public static Connection getConnection() {
		try {
			Class.forName(driver);	//注册驱动程序
			conn = DriverManager.getConnection//获取数据连接对象
						(url, username, password);
		} catch (ClassNotFoundException e) {
			throw new DaoException("Oracle的驱动程序找不到啦，看一下是不是对应的jar包没加载?",e);
		}catch (SQLException e) {	
			throw new DaoException("获取数据库连接时出现SQL语句错误",e);
		}
		return conn;
	}
	
	/**
	 * 开启事务
	 * @param conn
	 */
	public static void beginTransaction(Connection conn) {
		try {			
			conn.setAutoCommit(false);//将事务的自动提交模式设为假
		} catch (SQLException e) {
			throw new DaoException("事务开启失败",e);
		}
	}
	
	/**
	 * 提交事务
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			conn.commit();//提交事务
			conn.setAutoCommit(true);//将事务的自动提交模式设为真
		} catch (SQLException e) {
			throw new DaoException("事务提交失败",e);
		}
	}
	
	/**
	 * 回滚事务
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			conn.rollback();//回滚事务
			conn.setAutoCommit(true);//将事务的自动提交模式设为真
		} catch (SQLException e) {;
			throw new DaoException("事务回滚失败",e);
		}
	}
	
	/**
	 * 关闭连接
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		try {
			
			if(conn != null){//如果连接对象不为空，关闭该对象
				conn.close();
				conn = null;
				}
		} catch (SQLException e) {
			throw new DaoException("关闭连接出现错误",e);
		}
	}
	
	/**
	 * 关闭声明statement
	 * @param rs
	 * @param stmt
	 */
	public static void closeStatement(ResultSet rs, Statement stmt) {
		try {
			if(rs != null){//如果查询结果集对象不为空，关闭该对象
				rs.close();
				rs = null;
				}
			
			if(stmt != null){//如果声明对象不为空，关闭该对象
				stmt.close();
				stmt = null;
			}
	
		} catch (SQLException e) {
			throw new DaoException("关闭声明出现错误",e);
		}
	}
	
}
