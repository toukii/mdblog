# db

## [表关联查询](sql.html)

* sql 学习：表关联查询

## [sql 优化](optimal.html)

* MySQL索引优化、查询优化和存储优化经验

## oracle

```
Error: ORA-00932: inconsistent datatypes: expected - got CLOB
```

[不能在CLOB上distinct](http://www.cnblogs.com/pilang/archive/2012/02/28/2371784.html)

## redis

### [redis 分布式锁](redis-lock.html)

##  [redis-watch](redis-watch.html)

*	redis watch 实现秒杀

--------------------------------------

### 1. [Redis为什么使用单进程单线程方式也这么快](http://www.cnblogs.com/syyong/p/6231326.html)

Redis采用的是基于内存的采用的是单进程单线程模型的KV数据库，由C语言编写。官方提供的数据是可以达到100000+的qps。这个数据不比采用单进程多线程的同样基于内存的KV数据库Memcached差。

__Redis快的主要原因是：__

	完全基于内存
	数据结构简单，对数据操作也简单
	使用多路 I/O 复用模型

### 2. [事务](http://blog.csdn.net/basycia/article/details/52175429)

Redis的基本事务需要用到MULTI命令和EXEC命令，这种事务可以让一个客户端在不被其他客户端打断的情况下执行多个命令。这种事务与关系型数据库的能够执行回滚的事务不同，redis中，只要被MULTI和EXEC包围住的命令将一个接一个的执行，直达执行完毕后才会处理其他客户端的命令。

　　1、WATCH key key2[key3…]  监视key，假若在事务执行之前key数据有更改，则事务将会失败

　　2、UNWATCH  取消watch监视的所有key。假若已经执行了exec或者discard，那么就不用再执行unwatch了，因为exec是执行事务，此时watch效果已经生效；discard命令则是取消事务

　　3、MULTI  标记一个事务的开始

　　4、EXEC  执行事务块中的命令，并返回事务块中每条命令的结果。假若有被监视的key有修改则，则事务将被打断

　　5、DISCARD  取消事务

## [mongodb](http://www.cnblogs.com/no7dw/archive/2013/05/17/3083419.html)

## [GEO地理结构](geo.html)

## [redis, mongodb 应用场景](useful.html)