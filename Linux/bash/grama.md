# bash

```
#!/bin/sh
# 脚本名称
echo $0
# 第一个参数
echo $1
# 所有参数
echo $@
# 参数格式
echo $#
# 上个命令的返回值
$?
# if
if [[ $# -le 1 ]]
then
  echo "less than 1"
else
  echo "ok"
fi

if [[ "$1" = "0" ]]
then
	echo "first arg is 0"
elif [[ "$1" = "1" ]]
then
	echo "first arg is bigger than 0"
else
	echo "first arg is litter than 0"
fi

# case
case "$1" in
	root|admin)
echo "root auth"
;;
	foo|bar)
echo "not root"
;;
	*)
echo "nil user"
;;
esac

## loop
# while
count=1
while test $count -le 10
do
	echo $count
	count=$[$count+1]
done

## dead loop
# while :
# do
# 	echo -n "."
# 	sleep 1
# done

# for
for((i=0;i<10;i++))
do
	echo $i
done

for i in `seq 1 2 10`
do
	echo $i
done

for i in {1..10}
do
	echo $i
done

# array
# 声明
declare -a arr
# 赋值
arr[0]=1
arr[2]=a
# 第一个元素
echo ${arr[0]}
# 所有元素
echo ${arr[*]}
# 长度
echo ${#arr[*]}


```

#__执行顺序：别名－特殊内建命令－函数－内建命令－外部命令__

[参考](http://www.cnblogs.com/end/archive/2012/09/25/2701915.html)

## 练习：git项目清理

```
#!/bin/sh
for file in `ls`
do
	if [[ -d $file ]]
	then
		echo "$file is a dir"
		cd $file
		git gc --force
		cd ..
	fi
done
```
