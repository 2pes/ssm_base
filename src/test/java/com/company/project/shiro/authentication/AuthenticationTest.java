package com.company.project.shiro.authentication;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * 
 * <p>
 * Title: AuthenticationTest
 * </p>
 * <p>
 * Description: 认证测试
 * </p>
 * <p>
 * Company: www.itcast.com
 * </p>
 * 
 * @author 传智.燕青
 * @date 2015-3-23下午4:10:37
 * @version 1.0
 */
public class AuthenticationTest {

	// 用户登陆和退出
	@Test
	public void testLoginAndLogout() {
		//IniSecurityManagerFactory,在shiro1.4。0中已经过时。
		// 创建securityManager工厂，通过ini配置文件创建securityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:config/shiro-first.ini");

		// 创建SecurityManager
		SecurityManager securityManager = factory.getInstance();

		// 将securityManager设置当前的运行环境中
		SecurityUtils.setSecurityManager(securityManager);

		// 从SecurityUtils里边创建一个subject
		Subject subject = SecurityUtils.getSubject();

		// 在认证提交前准备token（令牌）
		// 这里的账号和密码 将来是由用户输入进去
		// UsernamePasswordToken token = new UsernamePasswordToken("zhangsan",
		// "111111");
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan2", "111111");

		try {
			// 执行认证提交
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 是否认证通过
		boolean isAuthenticated = subject.isAuthenticated();

		System.out.println("是否认证通过：" + isAuthenticated);

		// 退出操作
		subject.logout();

		// 是否认证通过
		isAuthenticated = subject.isAuthenticated();

		System.out.println("是否认证通过：" + isAuthenticated);

	}

	// 自定义realm
	@Test
	public void testCustomRealm() {

		// 创建securityManager工厂，通过ini配置文件创建securityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:config/shiro-realm.ini");

		// 创建SecurityManager
		SecurityManager securityManager = factory.getInstance();

		// 将securityManager设置当前的运行环境中
		SecurityUtils.setSecurityManager(securityManager);

		// 从SecurityUtils里边创建一个subject
		Subject subject = SecurityUtils.getSubject();

		// 在认证提交前准备token（令牌）
		// 这里的账号和密码 将来是由用户输入进去
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111111");

		try {
			// 执行认证提交
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 是否认证通过
		boolean isAuthenticated = subject.isAuthenticated();

		System.out.println("是否认证通过：" + isAuthenticated);

	}

	// 自定义realm实现散列值匹配
	@Test
	public void testCustomRealmMd5() {

		// 创建securityManager工厂，通过ini配置文件创建securityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:config/shiro-realm-md5.ini");

		// 创建SecurityManager
		SecurityManager securityManager = factory.getInstance();

		// 将securityManager设置当前的运行环境中
		SecurityUtils.setSecurityManager(securityManager);

		// 从SecurityUtils里边创建一个subject
		Subject subject = SecurityUtils.getSubject();

		// 在认证提交前准备token（令牌）
		// 这里的账号和密码 将来是由用户输入进去
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111111");
//		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "222222");

		try {
			// 执行认证提交
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 是否认证通过
		boolean isAuthenticated = subject.isAuthenticated();

		System.out.println("是否认证通过：" + isAuthenticated);

	}

	// 测试md5
	@Test
	public void testMD5() {

		// 原始 密码
		String source = "111111";
		// 盐
		String salt = "qwerty";
		// 散列次数
		int hashIterations = 1;
		// 上边散列1次：f3694f162729b7d0254c6e40260bf15c
		// 上边散列2次：36f2dfa24d0a9fa97276abbe13e596fc

		// 构造方法中：
		// 第一个参数：明文，原始密码
		// 第二个参数：盐，通过使用随机数
		// 第三个参数：散列的次数，比如散列两次，相当 于md5(md5(''))
		Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);

		String password_md5 = md5Hash.toString();
		System.out.println(password_md5);
		// 第一个参数：散列算法
		SimpleHash simpleHash = new SimpleHash("md5", source, salt, hashIterations);
		System.out.println(simpleHash.toString());

	}
}
