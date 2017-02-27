## 第一种方法

```
JAVA_HOME=/xxx/myapp/jdk
PATH=$PATH:$JAVA_HOME/bin
CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export JAVA_HOME PATH CLASSPATH
source .bash_profile
```


## 第二种方法


```
vi /etc/profile（永久生效）
source /etc/profile ，立即生效
```