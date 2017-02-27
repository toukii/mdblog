

## 安装UCS

	sudo add-apt-repository ppa:ubuntu-touch-community-dev/ppa
	sudo apt-get update  
	sudo apt-get install ucs 
	sudo apt-get install ucs=2.0 

	sudo add-apt-repository ppa:nik90/nstrain

### ucs报错

	ucs search state


### 试了没效果

__怀疑是bzr的问题，排除了：不是__

```
chown -R $user:$user ~/.bzr.log ~/.bazaar/
sudo add-apt-repository ppa:bzr/beta
sudo add-apt-repository ppa:bzr/ppa
sudo apt-get install bzr=2.5.1-0
https://launchpad.net/~bzr/+archive/ubuntu/ppa/+files/bzr_2.5.1.orig.tar.gz
```

__bzr version__


```
$ wget http://launchpad.net/bzr/2.1/2.1.0/+download/bzr-2.1.0.tar.gz
$ tar xzf bzr-2.1.0.tar.gz
$ cd bzr-2.1.0
$ sudo python setup.py install
python setup.py install build_ext --allow-python-fallback
```

### 定位到python版本问题

	
	python -V 2>&1 | awk '{print $2}'

```
sudo add-apt-repository ppa:fkrull/deadsnakes
sudo apt-get update
sudo apt-get install python3.3
```

_or_


```
sudo apt-get update
sudo apt-get install python3.4
```

	rm /usr/bin/python  

	ln -s /usr/bin/python3.3 /usr/bin/python
	ln -s /usr/bin/python3.4 /usr/bin/python

### 版本太高，需要用2.6版本

```
sudo apt-get install python2.6
rm /usr/bin/python
ln -s /usr/bin/python2.6 /usr/bin/python
```

### 但缺失argparse

http://stackoverflow.com/questions/15330175/how-can-i-get-argparse-in-python-2-6

https://pypi.python.org/pypi/argparse

http://stackoverflow.com/questions/7446187/no-module-named-pkg-resources

http://stackoverflow.com/questions/15093444/importerror-no-module-named-argparse

_没效果中。。。_

	sudo apt-get install python-argparse

> Try one of these:

	python setup.py install

	easy_install argparse

	pip install argparse

	putting argparse.py in some directory listed in sys.path should also work


sudo apt-get install python-pip
sudo apt-get install python-pkg_resources
## 学到的知识

### 卸载软件

	sudo apt-get purge pkg-name
	sudo apt-get autoremove
	sudo apt-get clean

### 源

	sudo add-apt-repository ppa:user/ppa-name

	sudo add-apt-repository -r ppa:user/ppa-name



```
 87  sudo ln -s /usr/bin/python /usr/bin/python3.4
   88  sudo ln -s /usr/bin/python3.4 /usr/bin/python
   89  python
   90  ucs search a
   91  whereis python
   92  ucs version
   93  bzr version
   94  sudo apt-get install python2.6
   95  ls
   96  whereis python
   97  sudo rm -rf /usr/bin/python
   98  sudo ln -s /usr/bin/python /usr/bin/python2.6
   99  sudo ln -s /usr/bin/python2.6 /usr/bin/python
  100  python
  101  ucs search a
  102  sudo apt-get install python-argparse
  103  ucs search a
  104  sudo apt-get install argparse
  105  sudo apt-get install python-argparse
  106  whereis libpython2.7
  107  where is argparse
  108  whereis argparse
  109  ucs search a
  110  python setup.py install
  111  pip
  112  sudo apt-get install python-
  113  whereis python2.6
  114  cd /usr/lib/python2.7/
  115  ls
  116  sudo python setup.py install
  117  pip
  118  pip install argparse
  119  sudo apt-get install pkg_resources
  120  sudo apt-get install python-pkg_resources
  121  wget https://pypi.python.org/packages/18/dd/e617cfc3f6210ae183374cd9f6a26b20514bbb5a792af97949c5aacddf0f/argparse-1.4.0.tar.gz#md5=08062d2ceb6596fcbc5a7e725b53746f
  122  ls
  123  cd
  124  wget https://pypi.python.org/packages/18/dd/e617cfc3f6210ae183374cd9f6a26b20514bbb5a792af97949c5aacddf0f/argparse-1.4.0.tar.gz#md5=08062d2ceb6596fcbc5a7e725b53746f
  125  ls
  126  tar -zxvf argparse-1.4.0.tar.gz 
  127  ls
  128  cd argparse-1.4.0/
  129  ls
  130  sudo python setup.py install
  131  sudo apt-get install python-setuptools
  132  sudo python setup.py install
  133  easy_install argparse
  134  wget https://bootstrap.pypa.io/ez_setup.py -O - | python
  135* sudo wget https://bootstrap.pypa.io/ez_setup.py -O 
  136  wget https://bootstrap.pypa.io/ez_setup.py -O - 
  137  ls
  138  unzip setuptools-23.1.0.zip 
  139  sudo apt-get install unzip
  140  ls
  141  unzip setuptools-23.1.0.zip 
  142  ls
  143  cd setuptools-23.1.0/
  144  ls
  145  sudo python setup.py install
  146  cd ..
  147  ls
  148  chmox +x setup.py 
  149  sudo chmox +x setup.py 
  150  sudo chmod +x setup.py 
  151  ll
  152  sudo python setup.py install
  153  ucs search a

```

sudo apt-get install --reinstall python-pkg-resources


import logging
logging.basicConfig()