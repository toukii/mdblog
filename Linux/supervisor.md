# supervisor

 - 1. Ubuntu 安装 supervisor

```
apt-get install supervisor
```

 - 2. 启动supervisor

```
supervisord
```

 - 3. 配置启动

`/etc/supervisor/conf.d/hw.conf`

```
[program:hw] // 定义进程名字
command=~/gopath/bin/web-hw // 进程二进制目录
directory=/gopath // 进程运行目录
user=root // 运行进程的用户
```


例如，hello-world 程序的配置：


```
go get github.com/toukii/web-hw 
// 生成hw可运行程序
```


 - 4. 启动

```
supervisorctl start hw
```

 - 5. 开机启动

每次开机之后，需要启动supervisord。（因为我是强制关机的，都要启动supervisord）


 - 6. 测试

 kill掉web-hw进程之后，又被拉起来了。


