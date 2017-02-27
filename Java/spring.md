#	Spring

*	**FROM [shiyanlou](https://www.shiyanlou.com/)**

##	project dirs

	---pom.xml
	---src
	------main
	---------resources
	------------beans.xml
	---------java
	------------com.shiyanlou.springhelloworld
	---------------App.java
	---------------HelloWorld.java
	---------------IOutputGenerator.java
	---------------OutputHelper.java
	---------------JsonOutputGenerator.java
	---------------XmlOutputGenerator.java

##	beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

    <bean id="helloBean" class="com.shiyanlou.springhelloworld.HelloWorld">
        <property name="name" value="shiyanlou" />
    </bean>

    <bean id="outputHelper" class="com.shiyanlou.springhelloworld.OutputHelper">
        <property name="outputGenerator" ref="xmlOutputGenerator"/>
    </bean>
    <bean id="jsonOutputGenerator" class="com.shiyanlou.springhelloworld.JsonOutputGenerator">
    </bean>
    <bean id="xmlOutputGenerator" class="com.shiyanlou.springhelloworld.XmlOutputGenerator"></bean>
</beans>
```

##	App.java

```java
package com.ctrip.springhelloworld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private static ApplicationContext context;

    public static void main( String[] args )
    {
        context = new ClassPathXmlApplicationContext("beans.xml");

        HelloWorld obj = (HelloWorld) context.getBean("helloBean");
        obj.printHello();

        OutputHelper outputHelper = (OutputHelper)context.getBean("outputHelper");
        outputHelper.OutputGenerate();
    }

}
```

##	HelloWorld.java

```java
package com.ctrip.springhelloworld;

public class HelloWorld{

    private String name;

    public void setName(String n){
        this.name=n;
    }

    public void printHello(){
        System.out.println("The first Spring 3:hello"+name);

    }
}
```

##	IOutpuGenerator
```java
package com.ctrip.springhelloworld;

public interface IOutputGenerator {
    public void generateOutput();
}
```

##	OutputHelper
```java
package com.ctrip.springhelloworld;

public class OutputHelper {
    IOutputGenerator outputGenerator;

    public void setOutputGenerator(IOutputGenerator outputGenerator) {
        this.outputGenerator = outputGenerator;
    }

    public void OutputGenerate() {
        outputGenerator.generateOutput();
    }
}
```

##	JsonOutputGenerator
```java
package com.ctrip.springhelloworld;

public class JsonOutputGenerator implements IOutputGenerator {
    public void generateOutput() {
        System.out.println("JsonOutputGenerator:{json:data}");
    }
}
```

##	XmlOutputGenerator
```java
package com.ctrip.springhelloworld;

public class XmlOutputGenerator implements IOutputGenerator {
    public void generateOutput() {
        System.out.println("XmlOutputGenerator:<xml><key>value</key></xml>");
    }
}
```

##	pom.xml

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.shiyanlou.demo</groupId>
    <artifactId>spring3-Example</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>spring3-Example</name>
    <url>http://maven.apache.org</url>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <scope>test</scope>
        </dependency>
        <!-- Spring3配置 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>4.0.9.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>4.0.9.RELEASE</version>
        </dependency>
    </dependencies>
</project>
```

_2015-12-04_


### 静态文件目录

        src/main/resources/static


### [Spring Web 配置](http://blog.csdn.net/xiaoyu411502/article/details/48453513)


_2016/03/11