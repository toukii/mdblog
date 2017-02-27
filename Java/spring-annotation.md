# Spring Annotation

## @RestController

返回内容作为HttpResponse的body部分，响应浏览器。


## @Controller

与RestController不同，返回内容作为模板名称，渲染后返回给浏览器。模板地址：src/resources/templates/xxx.html


## @ResponseBody

配合Controller使用，其返回值直接作为HttpResponse的Body部分，响应浏览器。

```java
@Controller
@RequestMapping("/user")  
public class Hello {  
  
    @ResponseBody
    @RequestMapping("/{id}")  
    public String view(@PathVariable("id") Long id,Model model) {
    	System.out.println(id);
    	model.addAttribute("name", id);
    	return "hello";
    }
```

访问 /user/123， 网页响应为hello；


```java
@Controller
@RequestMapping("/user")  
public class Hello {  
  
    @RequestMapping("/{id}")  
    public String view(@PathVariable("id") Long id,Model model) {
    	System.out.println(id);
    	model.addAttribute("name", id);
    	return "hello";
    }
```

访问 /user/123， 网页响应为hello.html

_2016/03/11_