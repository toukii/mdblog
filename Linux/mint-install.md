#	Linux Mint Install

##	Download

*	_[http://www.linuxmint.com/download.php](http://www.linuxmint.com/download.php)_

##	Make USB Mirror

	install

##	Input Method Setting1

	sudo apt-get install fcitx fcitx-table-wubi-large fcitx-frontend-all fcitx-frontend-gtk2 fcitx-frontend-gtk3 fcitx-frontend-qt4 fcitx-config-gtk fcitx-ui-classic fcitx-module-kimpanel fcitx-module-dbus libopencc1 fcitx-libs-qt

	
安装之后，注销或重启，然后下载搜狗的 deb 包进行安装。安装之后，记得在 首选项--输入法 配置工具中，配置一下，选择 fcitx，然后重启电脑，即可。


##	Input Method Setting2

	sudo add-apt-repository ppa:fcitx-team/nightly
	sudo apt-get update
	sudo apt-get install fcitx
	安装完成后注销，重新登陆。

	在软件管理器中搜索安装输入特定语言所需的组件：
	(1)简体中文拼音：fcitx-sunpinyin（Sunpinyin输入法引擎的fcitx封装）或fcitx-googlepinyin或fcitx-pinyin。
	(2)简体中文五笔：fcitx-table-wubi（五笔输入法）或fcitx-table-wbpy（五笔拼音输入法）。
	(3)繁体中文：fcitx-table-cangjie。
	(4)通用的输入法码表: fcitx-table*，如：fcitx-table-erbi（二笔输入法）。

	将fcitx设为默认：    
	LinuxMint默认的输入法框架是IBUS，所以要将默认输入法框架改为fcitx。打开终端，输入“sudo 
	im-config”，回车，输入用户密码回车后即可打开输入法配置对话窗口，单击“OK”，在下一个弹出的窗口中单击“Yes”，在接下来弹出的窗口中选中“fcitx”，单击“OK”。注销重新登录后即可。

	一般情况下系统会自动将fcitx设为开机启动，否则，在“系统设置-开机和关机-自动启动”中添加fcitx。

	如果fcitx无法正常使用，在软件管理器(mintInstall)中检查一下fcitx的依赖是否安装齐全（下面的包是针对KDE桌面的，Mint17尚未出KDE版的，所以搜索不到的忽略即可）：dialog，fcitx-bin，fcitx-config-
	common，fcitx-config-gtk，fcitx-data，fcitx-frontend-all，fcitx-frontend-
	gtk2，fcitx-frontend-gtk3，fcitx-frontend-qt4，fcitx-libs，fcitx-module-
	dbus，fcitx-module-kimpanel，fcitx-module-lua，fcitx-module-x11，fcitx-
	modules，fcitx-ui-classic，im-config，libopencc1，libpresage-
	data，libpresage1，libtinyxml2.6.2，presage。此外建议安装的软件包：fcitx-tools，fcitx-
	m17n，kdebase-bin，plasma-widgets-addons。

[reference>>](http://zhidao.baidu.com/link?url=vylQrvcsetmc18FzfJOpS0CEd8pqX9ys7pqtBWfW6Qlv-6cgVkaot4jgV4tjAzZZwJ6rHWx_3A7YVEAyhqnLwMEvpuLiNsMXAuHpITbKmgy)


_2015-11-27_


-----------------------------------

##	env setting

>vim ~/.bashrc

>source ~/.bashrc


*	**go**

```bash
export GOROOT=/home/shaalx/go
export GOPATH=/home/shaalx/GOPATH
export PATH=$GOROOT/bin:$GOPATH/bin:$PATH
```

*	**java**

```bash
export JAVA_HOME=/home/shaalx/Documents/jdk1.8.0_65
export PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
```

_2015-11-30_
