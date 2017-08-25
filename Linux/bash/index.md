# bash

## [基本语法](grama.html)

## [返回值](return.html)

## [eval](eval.html)

## [技巧学习](learn.html)

 - 显示行号

 	nl file
 	cat -n file

 - vim快速定位行

 `:n` 	

 - 回到上次目录

 	cd -

 - 终端显示中文

```
export LC_ALL='zh_CN.utf8'
```

```
dockerpid=$(sudo docker ps | grep 40 | awk '{print $1}')
if [ -n "$dockerpid" ]
then
  echo docker-stop-$dockerpid
  sudo docker stop $dockerpid
  echo $dockerpid > service_dockerpid.log
  ./serviceCmd > service.log 2>&1 &
  tail -f service.log
else
  ps -ef | grep serviceCmd | awk '{print $2}' | xargs kill -9
  echo docker-start-`cat service_dockerpid.log`
  cat service_dockerpid.log | xargs sudo docker start
fi

```
