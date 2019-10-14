# ssm整合基本代码
## 原项目地址	
基于博客文章，https://blog.csdn.net/qq598535550/article/details/51703190，搭建的最基本ssm框架。		
github:https://github.com/liyifeng1994/ssm
## 1. 数据库
```sql
-- 添加新用户
create user 'smmtest'@'%' identified by 'ssmtest';
-- 为用户创建数据库
create database ssm_base DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
-- 授予用户通过外网IP对于该数据库的全部权限
grant all privileges on `ssm_base`.* to 'smmtest'@'%' identified by 'smmtest';
-- 刷新权限
flush privileges;
```
## 2. 项目导入
1. 项目导入eclipse
2. 右键项目，debug as --> debug on server
3. 访问地址		
	http://localhost:8080/ssm/hello	
	http://localhost:8080/ssm/1000/detail		

