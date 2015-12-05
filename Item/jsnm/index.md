#	[Jsnm][1]

---------------------


__json mapping for map[string]interface{}__

提供缓存的json解析器，初衷是提高重复解析json速度。


##	数据结构设计

```go
type Jsnm struct {
	raw_data interface{}
	map_data MapData
	arr_data []*Jsnm
	cache    map[string]*Jsnm
}

type RawData struct {
	raw interface{}
}

type MapData map[string]interface{}
```

**RawData是原始数据；MapData是可以转换为map[string]interface{}的RawData；cache是缓存数据，有重合路径时，可以提高访问速度。**

##	核心函数

**Get**

```go

func (j *Jsnm) Get(path ...string) *Jsnm {
	if j == nil || len(path) <= 0 {
		return j
	}
	// first step: get data from cache
	if nil == j.cache {
		j.cache = make(map[string]*Jsnm)
	} else {
		if cache_data, ok := j.cache[path[0]]; ok {
			// fmt.Printf("******cache %s*****\n", path)
			if len(path) == 1 {
				return cache_data
			} else {
				return cache_data.Get(path[1:]...)
			}
		}
	}
	// second step: get data from mapdata
	if j.map_data == nil {
		j.map_data = make(MapData)
		if map_data, ok := j.raw_data.(map[string]interface{}); ok {
			j.map_data = map_data
		} else {
			return nil
		}
	}
	cur, ok := j.map_data[path[0]]
	if !ok {
		return nil
	}
	// third step: cache the data
	will_cache_data := NewJsnm(cur)
	j.cache[path[0]] = will_cache_data
	if len(path) == 1 {
		return will_cache_data
	}
	return will_cache_data.Get(path[1:]...)
}
```
_Example_

```go
jm.Get("Friends").Get("Age")
// NCGet should be after the Get
fon := jm.Get("Friends").NCGet("One").NCGet("Name")
```

-------------------------------

**Arr**

```go
func (j *Jsnm) Arr() []*Jsnm {
	if j == nil {
		return nil
	}
	if j.arr_data != nil {
		return j.arr_data
	}
	arr, ok := (j.raw_data).([]interface{})
	if !ok {
		return nil
	}
	ret := make([]*Jsnm, 0, len(arr))
	for _, vry := range arr {
		ret = append(ret, NewJsnm(vry))
	}
	j.arr_data = ret
	return ret
}

// Get the i-th index from array
func (j *Jsnm) ArrLoc(i int) *Jsnm {
	if j == nil {
		return nil
	}
	if nil != j.arr_data {
		if i >= len(j.arr_data) {
			return nil
		}
		return j.arr_data[i]
	}
	arr, ok := (j.raw_data).([]interface{})
	if !ok {
		return nil
	}
	if i >= len(arr) {
		return nil
	}
	return NewJsnm(arr[i])
}
```
_Example_

```go
jm.Get("Loc").Arr[0].Get("Name")
arr1 := jm.NCGet("Loc").ArrLoc(1).RawData().String()
```

**具体的类型转换，可在RawData中添加函数实现。**

_Example_

```go
age := jm.Get("Friends").Get("Age").MustInt64()
fmt.Println(age)
```


##	Benchmark

性能测试结果如图所示：
![Bench][2]

Gsj(_go-simplejson_)

代码还需优化，性能还有提升的空间。

_2015-11-25_

 [1]: https://github.com/shaalx/jsnm "jsnm"
 [2]: http://7xku3c.com1.z0.glb.clouddn.com/jsnm-benchmark.png "jsnm-bench"
