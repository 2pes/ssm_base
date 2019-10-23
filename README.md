# ssm整合基本代码
## 原项目地址	
- 基于博客文章:[https://blog.csdn.net/qq598535550/article/details/51703190](https://blog.csdn.net/qq598535550/article/details/51703190)，搭建的最基本ssm框架，平时用于考核新人，练习使用，感谢博主。		
- github:	[https://github.com/liyifeng1994/ssm](https://github.com/liyifeng1994/ssm)	

## 1. 数据库
1. mysql中执行下面语句:	
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
2. 导入执sql目录下的`schema.sql`文件。

## 2. 项目导入
1. 以maven项目导入eclipse或idea。
2. 右键项目，`debug as` --> `debug on server`。
3. 访问地址：		
	- http://localhost:8080/ssm/	
	- http://localhost:8080/ssm/book/list	
	- http://localhost:8080/ssm/book/1000/detail	

## 3. 实体类生成
1。 导入`generatorSqlmapCustom`项目。
2。 修改`generatorConfig.xml`中的数据库连接项。
3. 运行`GeneratorSqlmap.java`中`main`方法。
4. 拷贝生成的代码到自己的项目目录。