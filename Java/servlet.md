#	Servlet

*	servlet解决这样的事情：路由映射，访问一个url时，映射到某个servlet处理;通过web.xml来配置。
javax.servlet.http.HttpServlet定义了两个方法，doGet和doPost。

web.xml的配置如下：

```xml
<servlet>
      <servlet-name>simple</servlet-name>// 命名servlet
      <servlet-class>com.shaalx.servlet.MyServlet</servlet-class>
</servlet>
<servlet-mapping>  //地址映射
      <servlet-name>simple</servlet-name>
      <url-pattern>/api/*</url-pattern>//地址名
</servlet-mapping>
```

##	Listener

##	Dispatcher

web.xml

app-servlet.xml

[Servlet](http://www.cnblogs.com/xdp-gacl/p/3760336.html)

[Servlet upload download](http://www.cnblogs.com/xdp-gacl/p/4200090.html)
