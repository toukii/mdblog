# GEO

## geohash

geohash的思想是将二维的经纬度转换成一维的字符串，geohash有以下三个特点：

1、字符串越长，表示的范围越精确。编码长度为8时，精度在19米左右，而当编码长度为9时，精度在2米左右。

2、字符串相似的表示距离相近，利用字符串的前缀匹配，可以查询附近的地理位置。这样就实现了快速查询某个坐标附近的地理位置。

3、geohash计算的字符串，可以反向解码出原来的经纬度。

这三个特性让geohash特别适合表示二维hash值。这篇文章：[GeoHash核心原理解析](http://www.cnblogs.com/LBSer/p/3310455.html)详细的介绍了geohash的原理。


## [mongodb geo](http://www.cnblogs.com/gaopeng527/p/5437697.html)

## [redis geo](http://blog.csdn.net/opensure/article/details/51375961)

