package com.lingnan.usersys.common.test;

import java.sql.Connection;

import org.junit.Test;

import com.lingnan.usersys.common.util.DBUtils;

public class DBUtilsTest {

	@Test
	public void testGetConnection() {
		Connection connection = DBUtils.getConnection();
		System.out.println(connection);
	}

}
