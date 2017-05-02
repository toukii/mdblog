#!/bin/sh
# 脚本名称
echo $0
# 第一个参数
echo $1
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

# loop

count=1
while test $count -le 100
do
	echo $count
	count=$[$count+1]
done