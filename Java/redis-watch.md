# Redis watch 实现秒杀

## redis watch命令
```
watch foo
multi
set foo bar
exec
```

执行情况如下所示：

```
127.0.0.1:6379> watch foo
OK
127.0.0.1:6379> multi
OK
127.0.0.1:6379> set foo bar
QUEUED
127.0.0.1:6379> exec
1) OK
127.0.0.1:6379> watch foo
OK
127.0.0.1:6379> multi
OK
127.0.0.1:6379> set foo bar1
QUEUED
127.0.0.1:6379> set foo bar2
QUEUED
127.0.0.1:6379> exec
1) OK
2) OK
127.0.0.1:6379> get foo
"bar2"
```

若在exec之前有另外一个线程修改了foo，则exec的结果为`nil`:

```
127.0.0.1:6379> set foo bar3
QUEUED
127.0.0.1:6379> exec
(nil)
```

表明这次尝试修改foo失败。


## 秒杀功能

 - 设奖品数量为10，用户来秒抢。

 - 在高并发情况下，必有SecKill（称以上一次尝试修改foo值为SecKill）失败的情况，所以要循环SecKill，但要设置最大循环次数，超过最大次数认为秒杀失败；最好在每次循环中sleep几毫秒；

 - 若exec返回OK，说明这次秒杀成功，竞争资源数要减1；当资源数小于等于0时，活动结束。

 以上的伪代码如下所示：

```
var foo 10

func SecKill(string foo) bool{
    redis.watch(foo)
	redis.multi()
	intV:= int(foo) - 1
	oldV:= redis.getset(foo,intV+"")
	return oldV > 0 && "OK" == redis.exec()[0]
}


func Kill(string userid){
	for i:=0; i<20; i++{
		if SecKill(foo){
			fmt.Println(userid,"秒杀成功！")
			return ;
		}
		time.Sleep(2)
	}
	fmt.Println(userid, "秒杀失败！")
}
```

## [jedisTemplate 使用](http://www.cnblogs.com/softidea/p/5720938.html)
