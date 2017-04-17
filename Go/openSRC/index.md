# go开源代码学习

## 1. [groupcache](https://github.com/golang/groupcache)

 - [参考中文博客](http://blog.csdn.net/npc_hp110/article/details/48036765)

groupcache提供了一个分布式k／v缓存解决方案。根据数据的k，计算hash（一致性哈希），读取数据：缓存在local节点或远端节点（Peer）。groupcache设计了相关接口，可以对方法进行扩展，如：
 
 1. 数据编码器，可以选择高性能的protobuf；

 2. 扩展Cache为LRU，LFU等Cache；

另外，groupcache还提供了热数据扩散、防止Load“惊群效应”、控制缓存内存大小（一般地，仅做到限制缓存的数量）等。
