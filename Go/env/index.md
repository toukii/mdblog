#	Golang 源码安装

--------------------------

##	Go 1.4版本及之前版本安装

1.	需要用到GCC等工具，Windows用户可安装__[tdm-gcc](http://sourceforge.net/projects/tdm-gcc/)__,将gcc的bin目录添加至$PATH下。

2.	源码安装:

		$GOROOT/src/make.bash

3.	环境变量配置(Linux)

		export $GOROOT=/app/go
		export $GOPATH=/app/gopath
		export $GOBIN=$GOPATH/bin
		PATH=$GOROOT/bin:$GOPATH/bin:$PATH

##	Go 1.5版本安装

_Go 1.5 版本实现自举，需要旧版本的Go编译器，如 Go 1.4.2_

1.	安装旧版本的Go,参照Go1.4的安装。

2.	环境变量配置(Linux)

		export CC=C:\tmd-gcc\bin\gcc.exe #Windows
		export CGO_ENABLED=1
		mv /app/go /app/go1.4 # Elder version
		export GOROOT_BOOTSTRAP=/app/go1.4

		export $GOROOT=/app/go
		export $GOPATH=/app/gopath
		export $GOBIN=$GOPATH/bin
		PATH=$GOROOT/bin:$GOPATH/bin:$PATH
	
3.	源码安装

		$GOROOT/src/make.bash


----------------------------

##	交叉编译


1.	compile：编译所有目标环境

		CGO_ENABLED=0
		GOOS=linux
		GOARCH=amd64
		$GOROOT/src/make.bash 
	
	_or_ 

		$GOROOT/src/all.bash

2.	build: go build

		CGO_ENABLED=0
		GOOS=linux
		GOARCH=amd64
		go build

3.	cross compile


		//linux
		CGO_ENABLED=1 GOOS=linux GOARCH=amd64 ./make.bash --no-clean
		CGO_ENABLED=1 GOOS=linux GOARCH=386 ./make.bash --no-clean
		 
		//嵌入式linux
		CGO_ENABLED=1 GOOS=linux GOARCH=arm ./make.bash --no-clean
		 
		//嵌入式freebsd
		CGO_ENABLED=1 GOOS=freebse GOARCH=amd64 ./make.bash --no-clean
		CGO_ENABLED=1 GOOS=freebse GOARCH=386 ./make.bash --no-clean
		//mac os
		CGO_ENABLED=1 GOOS=darwin GOARCH=amd64 ./make.bash --no-clean
		CGO_ENABLED=1 GOOS=darwin GOARCH=386 ./make.bash --no-clean
