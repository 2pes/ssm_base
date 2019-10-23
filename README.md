# ssm整合基本代码
## 原项目地址	
- 基于博客文章:[https://blog.csdn.net/qq598535550/article/details/51703190](https://blog.csdn.net/qq598535550/article/details/51703190)，搭建的最基本ssm框架，平时用于考核新人，练习使用，感谢博主。		
- github:	[https://github.com/liyifeng1994/ssm](https://github.com/liyifeng1994/ssm)	

-  ssm整合还可以参考:[https://blog.csdn.net/qq_44543508/article/details/100192558](https://blog.csdn.net/qq_44543508/article/details/100192558)	
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
	
## 3. 分支permrm_system
permrm_system分支使用easyui作为前端，实现基本权限管理功能。	
地址：[https://github.com/maxmarvel/ssm_base/tree/perm_system](https://github.com/maxmarvel/ssm_base/tree/perm_system)	

## 4. 测试
controller测试，测试appoint方法可不必写jsp，用curl就行，比如		
```sh
curl -H “Accept: application/json; charset=utf-8” -d “studentId=1234567890” localhost:8080/ssm_perm/book/1003/appoint
```

## 5. 通过profile 控制环境	
参考：https://blog.csdn.net/csolo/article/details/82794969

我们经常面临的是开发、测试、部署环境下配置文件有不同，因此maven提供了profile来管理这些配置文件

resources配置：    
以jdbc.properties 为例，数据库在测试、开发、在线系统上配置是不同的，因此建立不同的文件夹和jdbc.properties，三个文件在本例子中以用户名和密码来区别，不同的环境对应不同的账号和密码

在build节点前，增加profiles的设置     
```xml

    <!--这里定义了profiles 用来区分开发、测试、生产环境 -->
    <profiles>
        <profile>
            <id>dev</id>
            <properties>
                <env>dev</env>
            </properties>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
        </profile>
        <profile>
            <id>test</id>
            <properties>
                <env>test</env>
            </properties>
        </profile>
        <profile>
            <id>online</id>
            <properties>
                <env>online</env>
            </properties>
        </profile>
    </profiles>
    <build>
        <finalName>ssm_perm</finalName>
        <resources>
            <!--因为mapper目录下，有xml映射文件 -->
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
            </resource>
            <!--resources 目录 -->
            <!-- 这里的resource配置的是需要导入到项目的资源文件夹 -->
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.xml</include>
                    <include>**/*.properties</include>
                </includes>
            </resource>

        </resources>

        <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
            <plugins>
                <plugin>
                    <artifactId>maven-clean-plugin</artifactId>
                    <version>3.0.0</version>
                </plugin>
                <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
                <plugin>
                    <artifactId>maven-resources-plugin</artifactId>
                    <version>3.0.2</version>
                </plugin>
                <plugin>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.7.0</version>
                </plugin>
                <plugin>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>2.20.1</version>
                </plugin>
                <plugin>
                    <artifactId>maven-war-plugin</artifactId>
                    <version>3.2.0</version>
                </plugin>
                <plugin>
                    <artifactId>maven-install-plugin</artifactId>
                    <version>2.5.2</version>
                </plugin>
                <plugin>
                    <artifactId>maven-deploy-plugin</artifactId>
                    <version>2.8.2</version>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
```	    

默认是dev，且activeByDefault 设置为true。    

pom.xml修改build节点

验证profiles
配置完pom.xml后在IDEA环境的maven project 面板多出来一块内容

我们可以测试一下，先将dev 打勾，执行maven resources命令

执行完成后，在classes目录下，jdbc 确实是dev环境。Maven resources是将src目录下的资源拷贝到 target/classes目录下，war命令是将这个目录下的内容生成到项目部署目录。

现在我们在面板上profile中online 打勾，再次执行 maven resources命令

## 目标
1. 整合框架
	- [x] ssm: [v1.0](https://github.com/maxmarvel/ssm_base/archive/v1.0.zip)
	- [x] druid
	- [x] 通用mapper 
	- [x] 代码生成器 
	- [x] swagger: [v2.0](https://github.com/maxmarvel/ssm_base/archive/v2.0.zip)
	- [ ] shiro 
	- [ ] quartz  

## 参考
https://www.sojson.com/shiro	
https://www.cnblogs.com/learnhow/p/5694876.html		
https://blog.csdn.net/puhaiyang/article/details/51753925	
