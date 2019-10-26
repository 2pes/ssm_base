package com.company.project.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml", "classpath:spring/spring-quartz.xml" })
public class BaseTest {
	public static void main(String[] args) {
		System.out.println("aaaaa");
	}
	@Test
	public void testPrint() {
		System.out.println("testPrint");

	}

}
