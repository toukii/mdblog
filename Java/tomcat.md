# Tomcat配置

## 安装好后的配置

1. **Ecpise 配置**

	Windows --> Preference --> Server --> Runtime Environments : Add

2. **项目配置**

	右键 --> Run As --> Run on a Server : 添加Tomcat

3. **运行配置**

	右键 --> Run Configuration : 参数配置

```
-Xms256m -Xmx512m -XX:MaxNewSize=256m -XX:MaxPermSize=256m
-Xms512M -Xmx1024M -XX:PermSize=256M -XX:MaxPermSize=512m
```

_2016-03-25_