![alt text](https://r.51gjj.com/webpublic/images/2018727/Jue32ytr0kfa.png "51gjj Logo")

大数据开发项目-授权认证
==============

项目简介
=========

大数据微服: 请求资源 携带认证信息  注销



目标
-----------
1.　大数据采集(存储,共享交换,计算)　---ETL

    1.1　元数据管理
    1.2  数仓管理
    
2.　大数据处理  　　　　　　　　---ETL

    2.1. 大数据在线处理(spark,flink)
    2.2. 大数据离线处理(spark,ＭＲ,Ｈive)
    2.3. 删除相关,缺失值填充处理,字符串替换,数据连接,数据转换,数据保存,
    2.4. 数据标准化
    2.5. 
    
3.　大数据业务支持(人物画像,推荐系统,在线训练) ---开发工程师

    3.1 大数据　- 标的
    3.2 大数据　- 建模
    3.3 大数据　- 分析.统计    
    3.4 大数据　- 挖掘
    3.5 大数据　- 机器学习
    
4.　大数据协同调度    　　---开发工程师  

    4.1　大数据生态数据
    4.2　大数据数据关联
    4.3　大数据

5.　大数据检索引挚 　---开发工程师 
    
 
6.　大数据智能         　　　---开发工程师  
    
 
 
    
目录
-----------

项目源码: [gitlab.com https://t-git.51gjj.com/xuanwu/cat.git](https://t-git.51gjj.com/xuanwu/cat.git)（cat）

开发说明:

   * [一、命名风格] 文件名须反映出其实现了什么类 – 包括大小写.(简洁)
   * [二、名称定义] 驼峰格式分割单词：类名（以及类别、协议名）应首字母大写;方法;变量名应该以小写字母开头;常量大写;包小写。
   * [三、代码格式] code style formatter.
   * [四、OOP规约] 当一个类有多个构造方法，或者多个同名方法，这些方法应该按顺序放置在一起;加强对静态类的管理
   * [五、集合处理].
   * [六、并发处理].       
   * [七、注释规约].
   * [七、注释规约].
   * [九、README.md] 一定要写，先写业务逻辑，再开发.
   
开发架构（hfs）:

   * hf-bins－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－shell or
   * hf-docs－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－项目文档
   * hf-etcs－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－项目配置
   * hf-libs－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－业务模块
   * hf-projects－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－项目
   * hf-shares－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－底层依赖
   * hf-share-utils－－－－－－－－－－－－－－－－－－－－－－－－－－－底层依赖util包
   
开发工具:

   * JDK1.8 click the link ＆ down the [jdk-8u192-linux-x64.rpm](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) file and install（rpm -ivh jdk-8u192-linux-x64.rpm） default dir /usr/java/.
   * IntelliJ IDEA IDEA 2018 tar down [IntelliJ IDEA IDEA 2018 for linux](https://www.jetbrains.com/idea/download/download-thanks.html?platform=linux).
   * IntelliJ IDEA IDEA 2018 exe down [IntelliJ IDEA IDEA 2018 for windows](https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows).
   * IntelliJ IDEA [IntelliJ IDEA 2018 注册码](http://idea.lanyus.com/)
   * Download [Eclipse Technology](http://www.eclipse.org/downloads/)
   * Download [Apache Maven 3.6.0](http://mirrors.hust.edu.cn/apache/maven/maven-3/3.6.0/binaries/apache-maven-3.6.0-bin.tar.gz) 
   * [Gitlab](https://t-git.51gjj.com/) plz register(联系王先生).
   * Open the Haddop [WebHDFS](http://xuanwu.51gjj.com:50070/dfshealth.html)，plz copy (http://xuanwu.51gjj.com:50070) .
   * Open the YARN [MapReduce](http://101.37.14.199:8188)，plz copy (http://101.37.14.199:8188) .
   * Open the Hbase [Hbase Master](http://101.37.14.63:60010) & [Hbase RegionServer](http://118.31.173.146:16030)，plz copy (http://101.37.14.63:60010,http://118.31.173.146:16030) .
   * Open the Hive [webUi](http://xuanwu.51gjj.com:9903)，plz copy (http://xuanwu.51gjj.com:9903) .   
   * Open the Hue [Hue Wen-UI](http://xuanwu.51gjj.com:9901)，plz copy (um:superuser;pwd:mgNTS0EMshqhcQBa) .  
   * Open the Spark [Spark Wen-UI](http://xuanwu.51gjj.com:9901)，plz copy (um:superuser;pwd:mgNTS0EMshqhcQBa) .  
   * Open the Azkaban [webUi](http://xuanwu.51gjj.com:9902)，plz copy (http://xuanwu.51gjj.com:9902) .   
   * Open the ketter [ketter.tar](http://xuanwu.51gjj.com:50070/dfshealth.html)，plz copy (http://xuanwu.51gjj.com:50070) .  
   * Open the NEXUS [Center Jianbing ](http://47.97.3.131:18081/nexus/content/repositories/central/)，plz copy (http://47.97.3.131:18081/nexus/content/repositories/central/) . 


开发环境:

   * /etc/profile.
   * /etc/hosts.
   * /etc/selinux/config
   * /etc/resolv.conf.
   * yum -y install ntp
   * ntpdate cn.pool.ntp.org
   * echo "ulimit -SHn 102400" >> /etc/rc.local
   * /etc/security/limits.conf
   * systemctl disable firewalld.service 
   * systemctl stop firewalld.service
   * /etc/sysctl.conf
   * /sbin/sysctl -p
   * /root/.vimrc
   
版本要求：

        <java.version>1.8</java.version>
        <scala.version>2.11.8</scala.version>
        <spark.version>2.4.0</spark.version>
        <spark.scala.version>2.11</spark.scala.version>
        <hadoop.version>3.1.1</hadoop.version>
        <hbase.version>2.1.1</hbase.version>
        <hive.version>1.2.1</hive.version>
        <kafka.version>2.1.0</kafka.version><!--kafka_2.11-2.1.0-->
        <spring-kafka.version>2.1.5.RELEASE</spring-kafka.version>
        <spring-data.version>2.1.3.RELEASE</spring-data.version>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <log4j.version>1.2.12</log4j.version>
        <slf4j.version>1.7.25</slf4j.version>   
   

Using dev evn
----------------

   * YARN ENV [YARN CLUSTER](http://101.37.14.199:8188): http://101.37.14.199:8188 
   
   * WEBHDFS ENV [HDFS CLUSTER](http://xuanwu.51gjj.com:50070): http://xuanwu.51gjj.com:50070

   * WEBHDFS ENV [WEBHDFS CLUSTER](http://101.37.14.63:50070): http://101.37.14.63:50070

   * HBASE ENV [Backup Masters](http://101.37.14.199:50070): http://101.37.14.199:50070
   
   * HBASE ENV [Backup Masters](http://101.37.14.199:60010): http://101.37.14.199:60010

   * HBASE ENV [RegionServer](http://118.31.173.146:16030): http://118.31.173.146:16030

   * HBASE ENV [Master](http://101.37.14.63:60010): http://101.37.14.63:60010

   * SPARK ENV [SPARK-MASTER](http://101.37.14.199:8080): http://101.37.14.199:8080

   * SPARK ENV [SPARK-SHELL](http://101.37.14.199:8081): http://101.37.14.199:8081
   
   * HUE ENV [HUE](http://xuanwu.51gjj.com:9901/hue): http://xuanwu.51gjj.com:9901/hue (UN:superuser PWD:mgNTS0EMshqhcQBa)

   * KAFKA ENV [KAFKA IP]: 10.80.176.146:9092,10.80.67.106:9092 #47.97.47.214:9092,47.97.3.131:9092   #
   
   * ZK ENV [ZK IP：PORT]：10.80.58.161:2181,10.80.59.53:2181,10.28.140.96:2181,10.80.176.146:2181,10.80.67.106:2181

   * Hive ENV [HIVE CLUSTER](http://101.37.14.63:10002): http://101.37.14.63:10002
   
Getting Started
---------------
Add the development packages, build and get the development server running:
```
git clone https://t-git.51gjj.com/xuanwu/hfs.git

```
Now Hadoop system should be running on cluster ! The shell is ``starthadoopcluster.sh``.
usernames=('dda' 'ddb' 'ddc' 'dde' 'ddf')
bh=/ddhome/bin
apps=('zookeeper' 'hadoop' 'hbase' 'hive' 'spark')
for un in ${usernames[@]};do
    ssh $un "$bh/zookeeper/bin/zkServer.sh start;exit"
done
for un in ${usernames[@]};do
echo "==============$un================"
    ssh $un "
    source /etc/profile
    #timedatectl set-ntp yes
    #ntpdate -u cn.pool.ntp.org
    #hwclock --systohc --localtime
    #clock -w
    #systemctl disable firewalld
    #systemctl stop firewalld.service
    $bh/zookeeper/bin/zkServer.sh status
    $bh/hadoop/bin/hdfs --daemon start journalnode
    if [ $un == 'dda' ];then
        $bh/hadoop/sbin/start-all.sh
       # hdfs haadmin -transitionToStandby -forcemanual nna
       # hdfs haadmin -transitionToStandby -forcemanual nnb
        hdfs haadmin -getServiceState nna
        hdfs haadmin -getServiceState nnb
        hdfs haadmin -transitionToActive --forcemanual nna
        hdfs haadmin -getServiceState nna
        hdfs haadmin -getServiceState nnb
        $bh/hbase/bin/hbase-daemon.sh start master
        $bh/hive/bin/hive --service metastore 1>/dev/null 2>&1 &
        $bh/hive/bin/hive --service hiveserver2 1>/dev/null 2>&1 &
    fi
    if [ $un == 'ddb' ];then
        $bh/hadoop/sbin/start-yarn.sh
        $bh/hadoop/sbin/mr-jobhistory-daemon.sh start historyserver
        yarn rmadmin -getServiceState rma
        $bh/hbase/bin/hbase-daemon.sh start master
        $bh/spark/sbin/start-all.sh
    fi
    if [ $un == 'ddc' ];then
        $bh/hadoop/sbin/start-yarn.sh
        $bh/hadoop/sbin/mr-jobhistory-daemon.sh start historyserver
        yarn rmadmin -getServiceState rmb
        $bh/hbase/bin/hbase-daemon.sh start regionserver
    fi
    if [ $un == 'dde' -o $un == 'ddf' ]
    then
        $bh/hbase/bin/hbase-daemon.sh start regionserver
        $bh/kafka/bin/kafka-server-start.sh -daemon $bh/kafka/config/server.properties
    fi
    jps
    exit"
done
then the cluster off

Community
-----------
   * User group: http://groups.google.com/a/cloudera.org/group/hue-user
   * Jira: https://issues.cloudera.org/browse/HUE
   * Reviews: https://review.cloudera.org/dashboard/?view=to-group&group=hue (repo 'hue-rw')


License
-----------
Apache License, Version 2.0
http://www.apache.org/licenses/LICENSE-2.0



《构建微服务架构》微服务化开发平台，具有统一授权、认证后台管理系统，其中包含具备用户管理、资源权限管理、网关API管理等多个模块，支持多业务系统并行开发，可以作为后端服务的开发脚手架。代码简洁，架构清晰，适合学习和直接项目中使用。核心技术采用Spring Boot2以及Spring Cloud (Finchley.M8)相关核心组件，前端采用vue-element-admin组件。
 学习教程



	项目中用到的技术有：
	
	springboot 快速搭建项目
	
	eureka 服务注册（发现）
	
	consul 服务注册（发现），consul单独开consul分支，默认eureka
	
	springcloud config/Apollo 配置，apollo会开单独分支，目前未做
	
	ribbon rest请求客户端负载平衡器，springboot自带
	
	feign rest请求声明性REST客户端，基于ribbon
	
	Hystrix 断路器
	
	turbine 聚合多个实例Hystrix指标流
	
	zuul 路由器和过滤器
	
	Sleuth 分布式跟踪
	
	Zipkin 结合Sleuth实现链路跟踪
	
	项目启动顺序：
	
	eureka/consul -> config -> 剩下其他的服务``
	
	能看到nginx欢迎界面说明,nginx安装成功
	
	hadoop　hbase　

