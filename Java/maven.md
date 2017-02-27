#	Maven

##	maven project pkgs

	
	Project
	--pom.xml
	--src
	----main
	------java
	---------com
	------------daoapp
	---------------mdbg
	------------------HelloMaven.java
	----test
	------java
	---------com
	------------daoapp
	---------------mdbg
	------------------TestHelloMaven.java


##	pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.daoapp.mdbg</groupId>
    <artifactId>mdbgEg</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.10</version>
        </dependency>
    </dependencies>
</project>
```

##	HelloMaven.java

```java
package com.daoapp.mdbg;

public class HelloMaven{
    public String sayHello(){
        System.out.println("hello:Maven");
        return "hello:Maven";
    }
}
```


##	TestHelloMaven.java


```java
package com.daoapp.mdbg;

import org.junit.*;
import static org.junit.Assert.*;

public class TestHelloMaven{
    public void TestsayHello(){
        HelloMaven hs = new HelloMaven();
        String helloInfo = hs.sayHello();
        assertEquals(helloInfo,"hello:Maven");
    }
}
```


##	command

```bash
mvn compile

mvn clean

mvn test
```

##  Maven project

*   prob1

        java -jar ScrumTimeCaptureMaintenence.jar

        And am getting the error:

        Can't execute jar- file: “no main manifest attribute”


*   **package main class into runnabled jar**
https://git.coding.net/shaalx/spring-demo.git
```bash
java cp XXX.jar CallApp
```

*   plugins

[http://www.tuicool.com/articles/6VBZZb6](http://www.tuicool.com/articles/6VBZZb6)