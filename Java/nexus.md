# nexus [first]

## change password

```
最近要更新Maven服务器，发生密码忘记需要重置，方法如下：
1.修改sonatype-work/nexus/conf/security.xml文件
找到<id>是你要修改的用户的那段，将<password>值修改主：f865b53623b121fd34ee5426c792e5c33af8c227 ，即admin123
2.重启Nexus服务
./Nexus restart
```

然而，没有这个目录啊，原来在配置文件XX.propertity文件里指明了该目录在`${user.home}/`

修改密码，重启tomcat，默认密码登录，修改密码！！搞定。

- nexus.war在网上可以下载得到，很快构建私有maven库，.m2的setting文件：

```xml
<mirrors>	
<mirror>
	<id>my-nexus2</id>
	<mirrorOf>central</mirrorOf>
	<url>http://111.111.111.111:8080/nexus/content/groups/public/</url>
</mirror>
</mirrors>	
```