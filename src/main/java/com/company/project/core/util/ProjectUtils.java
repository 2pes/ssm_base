package com.company.project.core.util;

import java.util.Map;

import org.apache.commons.beanutils.BeanMap;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class ProjectUtils {
	public static String passwordToMD5(String password, String salt) {

		// 散列次数
		int hashIterations = 1;
		// 上边散列1次：f3694f162729b7d0254c6e40260bf15c
		// 上边散列2次：36f2dfa24d0a9fa97276abbe13e596fc

		// 构造方法中：
		// 第一个参数：明文，原始密码
		// 第二个参数：盐，通过使用随机数
		// 第三个参数：散列的次数，比如散列两次，相当 于md5(md5(''))
		Md5Hash md5Hash = new Md5Hash(password, salt, hashIterations);

		// 第一个参数：散列算法
		SimpleHash simpleHash = new SimpleHash("md5", password, salt, hashIterations);
		System.out.println(simpleHash.toString());
		return md5Hash.toString();
	}
	 /**
     * map转对象
     * @param obj
     * @return
     */
    public static Map<?, ?> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new BeanMap(obj);
    }
}
