1. 使用return返回（适用于数值）
myfun() {
 return $(( 5 + 1 )); 
}
 myfun
 RESULTS=$?
 echo $RESULTS
这里的$?保存着函数运行的结果。

2. 使用echo（用echo返回结果，同样适用于数值）
myfun() {
 echo $(( 5 + 1 ));
}
RESULTS=$(myfun)
echo $RESULTS
3. 近似全局变量的用法
myfun() {
 foo=$(( 5 + 1 )); 
}
myfun
RESULTS=$foo
echo $RESULTS
这里的$foo充当了全局变量的角色，将返回值从函数内带到函数外。

4. 使用地址传递参数

myfun() {
 eval $1="\$(( 5 + 1 ))"; 
}
myfun RESULTS
echo $RESULTS
这里使用了一个函数参数$1带出返回值，这个方法非常类似与C++中的引用和C中的参数传址。从一个侧面反应了Bash在参数传递的时候应该都是传递的内存地址。

5. 使用地址传递（适用于数值）
myfun() {
 let $1=5+1; 
}
myfun RESULTS
echo $RESULTS

当然，以上这些方法可以单独使用，也可以结合使用。比如可以将传址和使用echo的方法结合起来：
 

function myfunc()
 {
 local  __resultvar=$1
 local  myresult='some value' 
 if [[ "$__resultvar" ]]; then
 eval $__resultvar="'$myresult'" 
 else 
 echo "$myresult"
 fi 
}   
myfunc result 
echo $result 
result2=$(myfunc) 
echo $result2
