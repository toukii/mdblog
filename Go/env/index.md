# Golang 环境搭建

## [快速搭建 fro linux](quick-install.html)

## [源码安装](source-install.html)

### install 

`
wget http://www.golangtc.com/static/go/1.6.2/go1.6.2.linux-386.tar.gz -O go1.6.tar.gz 
tar -zxvf go1.6.tar.gz 
export HOME=/home/shaalx 
export GOROOT=$HOME/go6 
mv go $GOROOT/ 
`


### env 

`
export HOME=/home/shaalx 
echo "export HOME=/home/shaalx" >> $HOME/.bashrc 
export GOROOT=$HOME/go 
echo "export GOROOT=$HOME/go" >> $HOME/.bashrc 
mkdir -p $HOME/GOPATH 
export GOPATH=$HOME/GOPATH 
echo "export GOPATH=$HOME/GOPATH" >> $HOME/.bashrc 
mkdir -p $GOPATH/bin $GOPATH/src $GOPATH/pkg 
export GOBIN=$GOPATH/bin 
echo 'export GOBIN=$GOPATH/bin' >> $HOME/.bashrc 
echo 'export PATH=$PATH:$GOBIN:$GOROOT/bin' >> $HOME/.bashrc source $HOME/.bashrc 
`


1.	需要用到GCC等工具，Windows用户可安装__[tdm-gcc](http://sourceforge.net/projects/tdm-gcc/)__,将gcc的bin目录添加至$PATH下。

## [交叉编译](cross.html)


## [开发环境配置](dev-env.html)
