#	GITHUB

##	ssh setting

```bash
ssh-keygen -rsa -C "account@mail.com"
\\13
\\13
\\13
```

*	填写email地址，然后一直“回车”ok 打开本地...ssh\id_rsa.pub文件。此文件里面内容为刚才生成人密钥。

*	SSH KEY ADD

	copy .id_rsa.pub to [SSH SETTING]

	登陆github系统。点击右上角的 Account Settings--->SSH Public keys ---> add another public keys

	把你本地生成的密钥复制到里面（key文本框中）， 点击 add key 就ok了

*	Test
	
		ssh -T git@github.com

	如果提示：Hi defnngj You've successfully authenticated, but GitHub does not provide shell access. 说明你连接成功了