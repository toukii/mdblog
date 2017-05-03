# redis 分布式锁实现

```java
public void releaseLock(String key){
	try {
		jedisCluster.getObject().del(key);
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public Boolean getLock(String key, long t1) {

  try {
	Long setnx = 0L;
	int n = 0;
	while(setnx <= 0L){
		setnx = jedisCluster.getObject().setnx(key, t1+"");;
		if (setnx == 1L) { // 获得锁
			return true;
		}
		String t2str = jedisCluster.getObject().get(key);
		if (StringUtil.isNullorEmpty(t2str)) { // GET nil
			// 尝试获得锁
			String t22str=jedisCluster.getObject().getSet(key,t1+"");
			if (StringUtil.isNullorEmpty(t22str)) { // 获得锁
				return true;
			}
		}else{
			long t2 = Long.parseLong(t2str);
			if (t1 - t2 > 10000L) {// 超时
				// 尝试获得锁
				String t22str=jedisCluster.getObject().getSet(key,t1+"");
				if ((t2str).equals(t22str)) { // 获得锁
					return true;
				}
			}
		}
		
		if (n++ > 100) {
			return false;
		}
		Thread.sleep(100);
	}
  } catch (Exception e) {
		e.printStackTrace();
		return false;
  }
```