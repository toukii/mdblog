# IP

## ip <==> long

```
#include <stdio.h> 

//--------------------------------------------------------------------- 
unsigned long ip2long(const char* ip){ 
  unsigned char a, b, c, d; 
  sscanf(ip, "%hhu.%hhu.%hhu.%hhu", &a, &b, &c, &d); 
  return ((a << 24) | (b << 16) | (c << 8) | d); 
} 

//--------------------------------------------------------------------- 
void long2ip(unsigned long ip, char buf[]){ 
  int i = 0; 
  unsigned long tmp[4] = {0}; 

  for(i = 0; i < 4; i++){ 
    tmp[i] = ip & 255; 
    ip = ip >> 8; 
  } 
  sprintf(buf, "%lu.%lu.%lu.%lu", tmp[3], tmp[2], tmp[1], tmp[0]); 
} 

//--------------------------------------------------------------------- 
int main(){ 
  char* ip = "192.168.1.2"; 
  char buf[16] = {0}; 
  unsigned long ip_long = 0; 

  ip_long = ip2long(ip); 
  printf("%lun", ip_long); 
   
  long2ip(ip_long, buf); 
  puts(buf); 

  return 0; 
} 
```

## 127.0.1.1

```
#include <unistd.h>
#include <netdb.h>  //gethostbyname
#include <arpa/inet.h>  //ntohl
#include <iostream>
#include <stdio.h>
using namespace std;

int get_local_ip(int& ip) {
    char hostname[128];
    int ret = gethostname(hostname, sizeof(hostname));
    if (ret == -1){
        return -1;
    }
    struct hostent *hent;
    hent = gethostbyname(hostname);
    if (NULL == hent) {
        return -1;
    }
    // cout<<ho
    //直接取h_addr_list列表中的第一个地址h_addr
    ip = ntohl(((struct in_addr*)hent->h_addr)->s_addr);
    cout<<(struct in_addr*)hent->h_addr<<endl;
    int i;
    for(i=0; hent->h_addr_list[i]; i++) {
       uint32_t u = ntohl(((struct in_addr*)hent->h_addr_list[i])->s_addr);
       std::cout << u << std::endl;
    }
    return 0;
}

void long2ip(unsigned long ip, char buf[]){ 
  int i = 0; 
  unsigned long tmp[4] = {0}; 

  for(i = 0; i < 4; i++){ 
    tmp[i] = ip & 255; 
    ip = ip >> 8; 
  } 
  sprintf(buf, "%lu.%lu.%lu.%lu", tmp[3], tmp[2], tmp[1], tmp[0]); 
  cout<<buf<<endl;
} 

int main() {
    int ip;
    int ret = get_local_ip(ip);
    if (ret == 0) {
        cout << ip << endl;
        char buf[20];
        long2ip(ip,buf);
    } else {
        cerr << "wrong" << endl;
    }

    return 0;
}
```

## 172.17.0.1 [docker0]


```
#include <stdio.h>
#include <sys/ioctl.h>
#include <sys/socket.h>
#include <unistd.h>
#include <sys/types.h>
#include <netdb.h>
#include <net/if.h>
#include <arpa/inet.h>
#include <iostream>
using namespace std;
int get_local_ip(unsigned long& ip)
{
    int sfd, intr;
    struct ifreq buf[16];
    struct ifconf ifc;
    sfd = socket(AF_INET, SOCK_DGRAM, 0); 
    if (sfd < 0)
        return -1;
    ifc.ifc_len = sizeof(buf);
    ifc.ifc_buf = (caddr_t)buf;
    if (ioctl(sfd, SIOCGIFCONF, (char *)&ifc))
        return -1;
    intr = ifc.ifc_len / sizeof(struct ifreq);
    while (intr-- > 0 && ioctl(sfd, SIOCGIFADDR, (char *)&buf[intr]));
    close(sfd);
    ip = ntohl(((struct sockaddr_in*)(&buf[intr].ifr_addr))->sin_addr.s_addr);
    return 0;
}


void long2ip(unsigned long ip, char buf[]){ 
  int i = 0; 
  unsigned long tmp[4] = {0}; 

  for(i = 0; i < 4; i++){ 
    tmp[i] = ip & 255; 
    ip = ip >> 8; 
  } 
  sprintf(buf, "%lu.%lu.%lu.%lu", tmp[3], tmp[2], tmp[1], tmp[0]); 
  cout<<buf<<endl;
} 

int main() {
    unsigned long ip = 0;
    int ret = get_local_ip(ip);
    if (ret == 0) {

        cout << ip << endl;
        char buf[20];
        long2ip(ip,buf);
    } else {
        cout << "err" << endl;
    }
    return 0;
}
```

## 192.168.1.114


```
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <net/if.h>
#include <arpa/inet.h>
#include <sys/ioctl.h>
 
 
 
//获取地址
//返回IP地址字符串
//返回：0=成功，-1=失败
int getlocalip(char* outip)
{
    int i=0;
    int sockfd;
    struct ifconf ifconf;
    char buf[512];
    struct ifreq *ifreq;
    char* ip;
    //初始化ifconf
    ifconf.ifc_len = 512;
    ifconf.ifc_buf = buf;
 
    if((sockfd = socket(AF_INET, SOCK_DGRAM, 0))<0)
    {
        return -1;
    }
    ioctl(sockfd, SIOCGIFCONF, &ifconf);    //获取所有接口信息
    close(sockfd);
    //接下来一个一个的获取IP地址
    ifreq = (struct ifreq*)buf;
 
    for(i=(ifconf.ifc_len/sizeof(struct ifreq)); i>0; i--)
    {
        ip = inet_ntoa(((struct sockaddr_in*)&(ifreq->ifr_addr))->sin_addr);
        //排除127.0.0.1，继续下一个
        if(strcmp(ip,"127.0.0.1")==0)
        {
            ifreq++;
            continue;
        }
        strcpy(outip,ip);
        return 0;
    }
 
    return -1;
}
 
//获取地址
//返回MAC地址字符串
//返回：0=成功，-1=失败
int get_mac(char* mac)
{
    struct ifreq tmp;
    int sock_mac;
    char mac_addr[30];
    sock_mac = socket(AF_INET, SOCK_STREAM, 0);
    if( sock_mac == -1)
    {
        perror("create socket fail\n");
        return -1;
    }
    memset(&tmp,0,sizeof(tmp));
    strncpy(tmp.ifr_name,"eth0",sizeof(tmp.ifr_name)-1 );
    if( (ioctl( sock_mac, SIOCGIFHWADDR, &tmp)) < 0 )
    {
        printf("mac ioctl error\n");
        return -1;
    }
    sprintf(mac_addr, "%02x:%02x:%02x:%02x:%02x:%02x",
            (unsigned char)tmp.ifr_hwaddr.sa_data[0],
            (unsigned char)tmp.ifr_hwaddr.sa_data[1],
            (unsigned char)tmp.ifr_hwaddr.sa_data[2],
            (unsigned char)tmp.ifr_hwaddr.sa_data[3],
            (unsigned char)tmp.ifr_hwaddr.sa_data[4],
            (unsigned char)tmp.ifr_hwaddr.sa_data[5]
            );
    close(sock_mac);
    memcpy(mac,mac_addr,strlen(mac_addr));
    return 0;
}
 
int main(void)
{
    char ip[20];
    char mac[17];
 
    if ( getlocalip( ip ) == 0 )
    {
        printf("本机IP地址是： %s\n", ip );
    }
    else
    {
        printf("无法获取本机IP地址");
    }
 
    if(get_mac(mac) == 0)
    {
        printf("本机MAC地址是： %s\n", mac);
    }
    else
    {
        printf("无法获取本机MAC地址");
    }
    return 0;
}
```