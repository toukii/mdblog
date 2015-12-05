# [mdbg](http://github.com/shaalx/mdbg)

--------------------------------


>[![flag](http://7xku3c.com1.z0.glb.clouddn.com/blog_24.ico "red flag")][1]

>[**markdown blog**][1]

## http://mdblog.daoapp.io

_Powered by [DaoCloud][8] [Github][9]_

--------------------------------

##	Technology

--------------------------------

###	Markdown ==> Html

第三方开源库：

>[github.com/shurcooL/github_flavored_markdown][2]

封装了一下，源码见 

>[github.com/shaalx/mdbgEg][3]

工具用法 :

>md -f [README.md] -r -d .static

		-f file   Marddown_file(.md is not necessary)
		-r redo
		-d dir    output_dir

--------------------------------

###	Webhook

源码参考 :

>[github.com/shaalx/webhooks][4]

>[github.com/qiniu/webhook][5]

--------------------------------

###	CSRF预防

验证了request的UserAgent，安全系数不是很高，或者使用http.Referer，然而Github未提供该参数。

--------------------------------

### callback优化

``` go
// Webhooks callback
func callback(rw http.ResponseWriter, req *http.Request) {
	fmt.Printf("Refer:%s\n", req.Referer())
	fmt.Printf("req:%#v\n", req)

	usa := req.UserAgent()
	fmt.Printf("UserAgent:%s\n", usa)
	if !strings.Contains(usa, "GitHub-Hookshot/") {
		fmt.Println("CSRF Attack!")
		http.Redirect(rw, req, "/", 302)
		return
	}
	b, err := ioutil.ReadAll(req.Body)
	if goutils.CheckErr(err) {
		return
	}
	if !strings.Contains(goutils.ToString(b), ".md") {
		fmt.Println("No .md changes.")
		return
	}
	exc_cmd.Reset("git pull origin master:master").Execute()
	exc_cmd.Reset("rdr").Execute()
}
```

##	Dockerfile

```go
FROM golang

WORKDIR /app/gopath/mdbg
ENV GOPATH /app/gopath
RUN git clone --depth 1 git://github.com/shaalx/mdbg.git .

RUN go get github.com/shaalx/mdbgEg/md
RUN mv $GOPATH/bin/md /bin/md

RUN go get github.com/shaalx/mdbgEg/rdr
RUN mv $GOPATH/bin/rdr /bin/rdr

RUN go get github.com/shaalx/mdbgEg
RUN mv $GOPATH/bin/mdbgEg /bin/mdbgEg

EXPOSE 80

CMD ["/bin/mdbgEg"]
```

--------------------------------

### All pkgs

>[github.com/shaalx/mdbg][0]

>[github.com/shurcooL/github_flavored_markdown][2]

>[github.com/shaalx/mdbgEg][3]

>[github.com/shaalx/webhooks][4]

>[github.com/qiniu/webhook][5]

>[github.com/everfore/exc][6]

>[github.com/shaalx/goutils][7]

  [0]: http://github.com/shaalx/mdbg "mdbg"
  [1]: http://mdblog.daoapp.io/	"mdblog.daoapp.io"
  [2]: https://github.com/shurcooL/github_flavored_markdown "github_flavored_markdown"
  [3]: https://github.com/shaalx/mdbgEg "mdbgEg"
  [4]: https://github.com/shaalx/webhooks "webhooks"
  [5]: https://github.com/qiniu/webhook "webhook"
  [6]: https://github.com/everfore/exc "exc"
  [7]: https://github.com/shaalx/goutils "goutils"
  [8]: https://www.daocloud.io/ "daocloud"
  [9]: https://github.com "github"

##	License

*	MIT License

_2015-11-20_


*	以上内容仅供参考，最新项目进展请移步[mdbgEg](https://github.com/shaalx/mdbgEg).

	<small>_2015-11-29_</small>