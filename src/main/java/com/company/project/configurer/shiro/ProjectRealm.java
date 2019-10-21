package com.company.project.configurer.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.company.project.module.sys.model.ActiveUser;
import com.company.project.module.sys.model.SysPermission;

public class ProjectRealm extends AuthorizingRealm {

	// 设置realm的名称
	@Override
	public void setName(String name) {
		super.setName("customRealm");
	}

	// 用于认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 从token中获取用户身份信息
		String username = (String) token.getPrincipal();
		// 拿着username从数据库中进行查询
		// ....
		// 如果查询不到返回null
		if (!username.equals("zhangsan")) {
			return null;
		}
		// 获取从数据库查询出来的用户密码
		String password = "123"; // 这里使用静态数据进行测试
		// 根据用户id从数据库中取出菜单
		// ...先使用静态数据
		List<SysPermission> menus = new ArrayList<SysPermission>();
		
		SysPermission sysPermission_1 = new SysPermission();
		sysPermission_1.setName("商品管理");
		sysPermission_1.setUrl("/module/mall/items");
		
		SysPermission sysPermission_2 = new SysPermission();
		sysPermission_2.setName("用户管理");
		sysPermission_2.setUrl("/module/sys/user");
		
		menus.add(sysPermission_1);
		menus.add(sysPermission_2);
		// 构建用户身份信息
		ActiveUser activeUser = new ActiveUser();
		activeUser.setUserid(username);
		activeUser.setUsername(username);
		activeUser.setUsercode(username);
		activeUser.setMenus(menus);
		// 返回认证信息由父类AuthenticationRealm进行认证
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser, password,
				this.getName());
		return simpleAuthenticationInfo;
	}

	// 用于授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 获取身份信息
		ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();
		// 用户id
		String userid = activeUser.getUserid();
		// 根据用户id从数据库中查询权限数据
		// ...这里使用静态数据模拟
		List<String> permissions = new ArrayList<String>();
		permissions.add("item:query");
		permissions.add("item:update");

		// 将权限信息封装为AuthorizationInfo
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// 基于资源权限的访问控制
		for (String permission : permissions) {
			simpleAuthorizationInfo.addStringPermission(permission);
		}
		// 如果基于角色进行访问控制
		// for (String role : roles) {
		// simpleAuthorizationInfo.addRole(role);
		// }

		return simpleAuthorizationInfo;
	}

}
