package com.lingnan.usersys.common.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.lingnan.usersys.usermgr.view.IndexFrame;

public class IndexFrameTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShow() {
		IndexFrame idfr = new IndexFrame();
		idfr.show();
	}

}
