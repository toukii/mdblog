# CI

## [jenkins ci](http://www.linuxidc.com/Linux/2016-11/137397.htm)


## gitlab

```
docker run \
    --hostname gitlab \
    --publish 443:443 --publish 80:80 --publish 22:22 \
    --name gitlab \
    --volume /Users/toukii/path/gitlab/valume/config:/etc/gitlab \
    --volume /Users/toukii/path/gitlab/valume/logs:/var/log/gitlab \
    --volume /Users/toukii/path/gitlab/valume/data:/var/opt/gitlab \
    twang2218/gitlab-ce-zh
```


## jenkins

```
 docker run \
 -p 8080:8080 \
 --restart=always \
 --name jenkins \
 -v /Users/toukii/path/jenkins/valume:/var/jenkins_home \
 jenkins
```
