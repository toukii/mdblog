#	Docker start

>sudo docker -d

##	Q1

```bash
cgroup is not mounted
```

##	A1

```bash
vim /etc/fstab

none        /sys/fs/cgroup        cgroup        defaults    0    0
```

>reboot


## use docker without sudo


```bash
# Add the docker group if it doesn't already exist.
sudo groupadd docker

# Add the connected user "${USER}" to the docker group.
# Change the user name to match your preferred user.
# You may have to logout and log back in again for
# this to take effect.
sudo gpasswd -a ${USER} docker

# Restart the docker daemon.
sudo service docker restart
```


切换当前会话到新 group 或者重启 X 会话


```
newgrp - docker
```

>OR


```
pkill X
```    

注意，最后一步是必须的，否则因为 groups 命令获取到的是缓存的组信息，刚添加的组信息未能生效，所以 docker images 执行时同样有错。 

## docker run

* [docker run](http://blog.csdn.net/junjun16818/article/details/38423391)



## https://c.163.com SSH connect to container

* create SSH key, download screte key


* [securecrt](https://www.vandyke.com/download/securecrt/download.html) download


* connect to the container

