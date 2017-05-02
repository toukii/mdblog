# Linux tools 
 
 - nmap
 - perf
 - curl
 - vim
 - httpstat
 - awk cut seq sed grep

 ## curl

 curl -H 'Content-Type:text/plain' --data-binary @jsonTest.md http://upload.daoapp.io/upload/jsonTest.md

 curl -X PUT --data @install.sh http://upload.daoapp.io/upload/install.sh

 curl -sSL http://upload.daoapp.io/loadfile/install.sh | sh

 ## [sh](bash.html)
   循环删除go源码🀄️的测试文件


```bash
find ./ -name *_test.go | xargs rm -rf
```

```
traceroute baidu.com
```

设置时间

```
date -s "2017-03-09 09:24:30"
```

```
du --max-depth=1 -m .
du -d1 -Bm .
```

### [linux 服务器模型](http://blog.csdn.net/lmh12506/article/details/7753978)

## grep&sort

```
grep -io a learn.md

ll -t | sort -rn -k 2 -t :
```

###  判断
```
# 判断目录是否存在
if [[ -d $dir ]]
# 判断文件是否存在
if [[ -f $file ]]
# 判断值是否不为空
if [[ -n $var ]]
# 判断是否有执行权限
if [[ -x $bash ]]
```