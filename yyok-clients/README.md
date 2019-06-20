![alt text](https://r.51gjj.com/webpublic/images/2018727/Jue32ytr0kfa.png "51gjj Logo")

大数据开发项目--文档
==============


项目简介
=========

大数据微服:



目标
-----------
数据仓库分层：
        ods 层（源数据层）：缓冲数据，源数据的直接映射
        dws
	        dwo 层--（对 ods 源数据层的数据进行清洗和预处理）：基础数据层，数据拉链处理、分区处理
	        dwc 层--数据分类（聚合）如 base+部门
	dim 

	dm
	        dmd 层--部门指标（对 dw 层数据 进行统计一些指标数据）：通用聚合
	        dmf 层--第三方（数据业务接口）API，长宽表
	        dmg 层--领导指标（对 dm 层指标数据 进行指标数据的业务汇总分析）：高度聚合
	
	
	/tao　饕餮的tao
    
   -----------------------------------------------------------
    
    /tao/ods   #原数据存放
    
   -----------------------------------------------------------
    
    /tao/dwo   #原数据对应据清洗后数据存放并提供大数据开发所用
   -----------------------------------------------------------
    
    /tao/dwc   #基础数据维度表存放
   -----------------------------------------------------------
    user_info
    product_info
    com_info
    
    -----------------------------------------------------------
    
    /tao/mid   #宽维度表存放，数据来源于dwc或dwo层
    user_mid
    
    -----------------------------------------------------------
    
    /tao/dm    #需求维度宽表存放，数据来源于dwc，dwo或mid层
    user_dm
    -----------------------------------------------------------
    
    
    原则：自下而上应用，dm面对用户层，不能以同层数据为基础。
    
    -----------------------------------------------------------
    
    TODO　待添加其他目录
    
    -----------------------------------------------------------
    
    DWO层之后（包括DWO）列分隔符用^A（ascii码0x01）
    行分割符号\n
    
    -----------------------------------------------------------
    如有加字段都行后追加，
    -----------------------------------------------------------
   

五大维度： 人口属性：（性别，年龄，地域）
	  商业属性：（收入，职业， 所属行业，）
	  消费特征：（房子购买，洗车购买，快消购买，美妆购买）
	  生活形态：（生活习惯，用户偏好，用户行为，娱乐，爱好，社交）
	  CRM：     （客户，会员，生命价值）

基本信息：用户ID、用户名、密码、性别、手机号、邮箱、年龄、户籍省份、身份证编号、注册时间
用户所填信息计算得到的指标： 
生日、星座、城市等级、手机前几位、手机运营商、邮件运营商 

用户调查表得到：学历、收入、职业、婚姻、是否有小孩、是否有车有房、使用手机品牌、收货地址。 

根据算法得到: 
身高、体重、性别模型、孩子性别概率、潜在汽车用户概率、是否孕妇、孩子年龄概率、手机品牌、更换手机频率、是否有小孩，是否有车，使用手机档次，疑似马甲标准、疑似马甲账号数、用户忠诚度、用户购物类型。

======================================== 


---------------------------------------------------------------------

用户标签定义与权重
===================



--------------------------------------------------------------------

模型算法
===================

--性别模型
用户自己也填写了性别，但仍然要用算法算一次性别


性别	男女标志	                                                                       判断策略

用户性别	1男 0女 -1未识别	                                                1、商品性别得分 2、用户购买上述商品计算用户性别等得分 3、最优算法训练阈值，根据阈值判断

孩子性别	0仅有男孩 1仅有女孩 2男女都有 3无法识别	1、选择男孩女孩商品 2、确定用户购买商品的男女性别比例 3、训练阈值，判断孩子性别，同用户性别类似


性别验证方法 
随机抽样几千条数据让客服打电话确认。 
与用户自己填的性别做对比，确认百分比。
---------------------------------

--用户汽车模型
	Tables	                 Are	                 Cool
	用户是否有车	1有 0没有 -1未识别	根据用户购买汽车相关产品，判断是否有车
	潜在汽车用户	1有 0没有 -1未识别	用户浏览或搜索汽车，用户数据判断

---------------------------------

--用户忠诚度模型
Tables	                                   Are	                                                                Cool

用户忠诚度	1忠诚型用户 2偶尔型用户 3投资型用户 4游览型用户 -1未识别	总体规则是判断+聚类算法 1、游览用户型：只游览不购买的 2、购买天数大于一定天数的为忠诚用户3、购买天数小于一定天数，大部分是有优惠才购买的4、其他类型根据购买天数，购买最后一次距今时间，购买金额进行聚类
---------------------------------

--用户身高尺码模型
	Tables	                 Are	                                                     Cool

	男性身高尺码	xxx-xxx身高段，-1未识别	                用户购买服装鞋帽等用户判断

	男性身材	              1偏瘦、2标准、3偏胖4肥胖、-1未识别	用户购买服装鞋帽等用户判断

	女性身高尺码           xxx-xxx身高段，-1未识别	                用户购买服装鞋帽等用户判断

	女性身材	             1偏瘦、2标准、3偏胖4肥胖、-1未识别	用户购买服装鞋帽等用户判断

---------------------------------
--用户马甲标志模型
	马甲是指一个用户注册多个账号
	多次访问地址相同的用户账号是同一个人所有
	同一台手机登陆多次的用户是同一个人所有
	收货手机号相同的账号同一个人所有
---------------------------------

--手机相关标签模型
	对于手机营销参考意义比较大
	使用手机品牌: 最常用手机直接得到
	使用手机品牌档次：根据档次维表
	使用多少种不同的手机：手机登陆情况
	更换手机频率（月份):按时间段看手机登陆情况

---------------------------------------------------------------------

1.用户基本属性表
    目的：时间分区中每天的数据应为每天新增用户和每天访问用户
        1.用户基本属性表  dmd.user_info（时间分区）
        2.用户基本属性表  dmf.user_info（时间分区）
        3.用户基本属性表  dmg.user_info（时间分区）

---------------------------------------------------------------------

数据仓库分层：
        DMB 层（源数据层）：缓冲数据，源数据的直接映射
        DMF 层（对 DMB 源数据层的数据进行清洗和预处理）：基础数据层，数据拉链处理、分区处理
        DMG 层（对 DMF 层数据 进行统计一些指标数据）：通用聚合
        DMA 层（对 DMG 层指标数据 进行指标数据的业务汇总分析）：高度聚合

数据仓库分层：
        BDM 层（源数据层）：缓冲数据，源数据的直接映射
        FDM 层（对 BDM 源数据层的数据进行清洗和预处理）：基础数据层，数据拉链处理、分区处理
        GDM 层（对 FDM 层数据 进行统计一些指标数据）：通用聚合
        ADM 层（对 GDM 层指标数据 进行指标数据的业务汇总分析）：高度聚合

---------------------------------------------------------------------

1.客户基本属性表
    SQL目的：时间分区中每天的数据应为每天新增用户和每天访问用户
        1.客户基本属性表 DMB层 bdm.bdm_user（时间分区）
        2.客户基本属性表 DMF层 fdm.fdm_user_wide（时间分区）
        3.客户基本属性表 DMG层 gdm.gdm_user_basic（时间分区）
    
2.订单表
        1.订单 主要信息表
                1.订单 主要信息表 BDM层 bdm.bdm_order（时间分区）
                2.订单 主要信息表 FDM层 fdm.fdm_order（时间分区）
                
        2.订单 详细信息表
                1.订单 详细信息表 BDM层 bdm.bdm_order_desc（时间分区）
                2.订单 详细信息表 FDM层 fdm.fdm_order_desc（时间分区）
                
        3.订单表 GDM层
                订单宽表 GDM层 gdm.gdm_order（时间分区）
                订单宽表 GDM层 = 订单 主要信息表 FDM层 + 订单 详细信息表 FDM层 
                
3.订单中 商品信息表
        1.订单中 商品信息表 BDM层 bdm.bdm_order_goods（时间分区）
        2.订单中 商品信息表 FDM层 fdm.fdm_order_goods（时间分区）

4.客户订单地址表
        客户订单地址表 GDM层 gdm.gdm_user_order_addr_model
        
5.购物车中 商品信息表
        1.购物车中 商品信息表 BDM层 bdm.bdm_order_cart（时间分区）
        2.购物车中 商品信息表 FDM层 fdm.fdm_order_cart（时间分区）
        
6.客户消费模型表（订单+购物车） GDM层（临时表）
        没有时间分区，但是每次都会先 drop 临时表，再 CREATE 临时表 as select ...
        
        1.临时表01（订单）
            客户消费模型表 GDM层 gdm.gdm_user_consume_order_temp_01（临时表01）统计订单相关指标
            客户消费模型表 GDM层 = 订单表 GDM层（时间分区） + 订单中商品信息表 FDM层（时间分区） + 客户订单地址表 GDM层
            
        2.临时表02（购物车）
            客户消费模型表 GDM层 gdm.gdm_user_consume_order_temp_02（临时表02） 统计购物车相关指标 
            客户消费模型表 GDM层 = 购物车中商品信息表 FDM层（时间分区）
            
        3.临时表03（订单）
            客户消费模型表 GDM层 gdm.gdm_user_consume_order_temp_03（临时表03） 统计订单中常用收货地址和常用支付方式 
            客户消费模型表 GDM层 = 订单表 GDM层（时间分区）+ 订单表 GDM层（时间分区）（订单表自身和自身union all）
            
        4.临时表04
            gdm.gdm_user_consume_order_temp_100（临时表04） = 客户消费模型表 GDM层（临时表01） + 客户消费模型表 GDM层（临时表02） 
            目的：仅为合并 订单表 和 购物车表 中所相同的的user_id为一个分组，并且最终合并结果中的每条数据均为不同的user_id

7.客户消费模型表（订单+购物车） GDM层
        SQL目的：统计 订单 和 购物车 中相关消费指标。
        当前该表中的“近30/60/90天的XX”等指标 仅为时间分区中每天的指标数据，因此还必须根据 where dt >= date_add(昨天日期时间, -29/-60/-90) 条件进行统计， 
        这样才能统计出真正的“近30/60/90天的XX”指标数据。
        客户消费模型表 GDM层 gdm.gdm_user_consume_order（时间分区）
        客户消费模型表 GDM层 = 临时表01 + 临时表02 + 临时表03 + 临时表04

8.订单中 商品类目表 GDM层（临时表）
        SQL目的：统计用户订单中所消费的商品分类情况，即可得知用户偏向于消费何种分类的商品
        没有时间分区，但是每次都会先 drop 临时表，再 CREATE 临时表 as select ...
        
        订单中 商品类目表 GDM层 gdm.gdm_user_buy_category_temp（临时表）
        订单中 商品类目表 GDM层 = 订单 主要信息表 FDM层（时间分区） + 订单中商品信息表 FDM层（时间分区）

9.购物车中 商品类目表 GDM层（临时表）
        SQL目的：统计用户购物车中成功支付消费的商品分类情况，即可得知用户偏向于消费何种分类的商品
        没有时间分区，但是每次都会先 drop 临时表，再 CREATE 临时表 as select ...

        购物车中 商品类目表 GDM层 gdm.gdm_user_cart_category_temp（临时表）
        购物车中 商品类目表 GDM层 = 购物车中商品信息表 FDM层（时间分区） + 订单中商品信息表 FDM层（时间分区）

10.商品类目码表 GDM层（1级/2级/3级分类详细）
        商品类目码表 GDM层 gdm.gdm_category_code

11.客户消费商品的 每级分类的 类目详细表（订单+购物车） GDM层（临时表）（客户喜好消费的商品分类）
        SQL目的：根据三级分类ID得出二级分类和一级分类的ID和名称
        没有时间分区，但是每次都会先 drop 临时表，再 CREATE 临时表 as select ...
        
        客户消费商品的 每级分类的 类目详细表 GDM层 gdm.gdm_user_category_total（临时表）
        客户消费商品的 每级分类的 类目详细表 GDM层 = 订单中 商品类目表 GDM层（临时表）+ 购物车中 商品类目表 GDM层（临时表）+ 商品类目码表 GDM层
        
12.客户消费商品的 每级分类的 类目总表（订单+购物车） GDM层（客户喜好消费的商品分类）
        SQL目的：汇总 用户订单中所消费的商品分类情况 和 用户购物车中成功支付消费的商品分类情况，最终得知用于总体偏向于消费何种分类的商品。
        当前该表中的“近30/60/90天的XX”等指标 仅为时间分区中每天的指标数据，因此还必须根据 where dt >= date_add(昨天日期时间, -29/-60/-90) 条件进行统计， 
        这样才能统计出真正的“近30/60/90天的XX”指标数据。
        客户消费商品的 每级分类的 类目总表 GDM层 gdm.gdm_user_buy_category（时间分区）
        客户消费商品的 每级分类的 类目总表 GDM层 = 客户消费商品的 每级分类的 类目详细表 GDM层（临时表） + 订单中 商品类目表 GDM层（临时表）+ 购物车中 商品类目表 GDM层（临时表）

13.用户上网轨迹表 
        SQL目的：以同一个用户ID作为同一个分组的情况下，同时可根据session_id和cookie_id的不同，计算出该同一用户当天的访问次数和每次访问的停留时长
        1.用户上网轨迹表 BDM层（PC端/网页端）
                用户上网轨迹表 BDM层 bdm.bdm_user_pc_click_log（时间分区）
                用户上网轨迹表 FDM层 fdm.fdm_user_pc_pageview（时间分区）
                
        2.用户上网轨迹表 BDM层（APP端）
                用户上网轨迹表 BDM层 bdm.bdm_user_app_click_log（时间分区）
                用户上网轨迹表 FDM层 fdm.fdm_user_app_pageview（时间分区）

14.近30天 PC端/网页端 访问最常用的指标（临时表）
        SQL目的：在同一个用户ID分组下，可对应多个不同 ip/cookie/浏览器名/系统名    的分组的情况下，统计出近30天之内的使用次数情况
        统计近30天之内指标的方式：where dt >= date_add(昨天日期时间, -29)
        没有时间分区，但是每次都会先 drop 临时表，再 CREATE 临时表 as select ...

        近30天 PC端 访问最常用的指标 GDM层 gdm.gdm_user_visit_temp_01（临时表）
        近30天 PC端 访问最常用的指标 GDM层 = 用户上网轨迹表 FDM层（时间分区）
        
15.用户访问模型表 GDM层
        用户访问模型表 GDM层 gdm.gdm_user_visit（时间分区）
        用户访问模型表 GDM层 = 客户基本属性表 FDM层（时间分区）
                     + 用户上网轨迹表（PC端/网页端） FDM层（无需时间分区，因为进行了时间日期比较）
                     + 近30天 PC端 访问最常用的指标 GDM层（临时表）
                     + 用户上网轨迹表（APP端） FDM层（无需时间分区，因为进行了时间日期比较）
                               
16.用户画像宽表 ADM层
    每天汇总出的用户画像表仅包含当天的数据，因此还需要和之前汇总好的用户画像表进行新的合并操作后，才算真正的最新数据的用户画像表
        用户画像宽表 ADM层 adm.adm_personas（时间分区）
        用户画像宽表 ADM层 = 用户基本属性表 GDM层 gdm.gdm_user_basic（时间分区）（第 1 张表）
                   + 客户消费模型表 GDM层 gdm.gdm_user_consume_order（时间分区）（第 7 张表 = 2 + 3 + 4 + 5 + 6）
                   + 客户喜好消费的商品分类模型表 GDM层 gdm.gdm_user_buy_category（时间分区）（第 12 张表 = 8 + 9 + 10 + 11）
                   + 用户访问模型表 GDM层 gdm.gdm_user_visit（时间分区）（第 15 张表 = 13 + 14）

17.用户标签表
    可根据 客户消费模型表 + 客户喜好消费的商品分类模型表 + 用户访问模型表 分析出 该人的购买喜好、购买习惯、购买实力，从而推荐相似的商品给该用户

数据仓库分层：
        BDM 层（源数据层）：缓冲数据，源数据的直接映射
        FDM 层（对 BDM 源数据层的数据进行清洗和预处理）：基础数据层，数据拉链处理、分区处理
        GDM 层（对 FDM 层数据 进行统计一些指标数据）：通用聚合
        ADM 层（对 GDM 层指标数据 进行指标数据的业务汇总分析）：高度聚合

---------------------------------------------------------------------

用户基本属性表 GDM 层（对 FDM 层数据 进行统计一些指标数据）

    SQL目的：时间分区中每天的数据应为每天新增用户和每天访问用户

    --用户画像-客户基本属性模型表 ods 层（对 FDM 层数据 进行统计一些指标数据）

    create database if not exists ods;

    create table if not exists ods.user_info( 
    user_id string                ,--用户ID
    user_name string            ,--用户登陆名
    user_sex  string            ,--用户性别
    user_birthday string        ,--用户生日
    user_age  bigint            ,--用户年龄
    constellation string        ,--用户星座
    province string                ,--省份
    city string                    ,--城市
    city_level string            ,--城市等级
    hex_mail string                ,--邮箱
    op_mail string                ,--邮箱运营商
    hex_phone string            ,--手机号
    fore_phone string            ,--手机前3位
    op_phone string                ,--手机运营商
    add_time timestamp            ,--注册时间
    login_ip string                ,--登陆ip地址
    login_source string            ,--登陆来源
    request_user string            ,--邀请人
    total_mark bigint            ,--会员积分
    used_mark bigint            ,--已使用积分
    level_name string            ,--会员等级名称
    blacklist bigint            ,--用户黑名单
    is_married bigint            ,--婚姻状况
    education string            ,--学历
    monthly_money double        ,--收入
    profession string            ,--职业
    sex_model bigint            ,--性别模型
    is_pregnant_woman bigint    ,--是否孕妇
    is_have_children bigint        ,--是否有小孩
    children_sex_rate double    ,--孩子性别概率
    children_age_rate double    ,--孩子年龄概率
    is_have_car bigint            ,--是否有车
    potential_car_user_rate double,--潜在汽车用户概率
    phone_brand string            ,--使用手机品牌
    phone_brand_level string    ,--使用手机品牌档次
    phone_cnt bigint            ,--使用多少种不同的手机
    change_phone_rate bigint    ,--更换手机频率
    majia_flag string            ,--马甲标志
    majie_account_cnt bigint,        --马甲账号数量
    loyal_model bigint,        --用户忠诚度
    shopping_type_model bigint    ,    --用户购物类型
    figure_model bigint,        --身材
    stature_model bigint,        --身高
    dw_date timestamp
    ) partitioned by (dt string);

---------------------------------------------------------------------

客户基本属性模型表 BDM层（源数据层）
 
    --客户基本属性模型表BDM层（源数据层）
    create database if not exists bdm;

    create external table if not exists bdm.bdm_user( 
    user_id string                ,--用户ID
    user_name string            ,--用户登陆名
    user_sex  string            ,--用户性别
    user_birthday string        ,--用户生日
    user_age  bigint            ,--用户年龄
    constellation string        ,--用户星座
    province string            ,--省份
    city string            ,--城市
    city_level string            ,--城市等级
    hex_mail string            ,--邮箱
    op_mail string            ,--邮箱运营商
    hex_phone string            ,--手机号
    fore_phone string            ,--手机前3位
    op_phone string            ,--手机运营商
    add_time string            ,--注册时间
    login_ip string            ,--登陆ip地址
    login_source string        ,--登陆来源
    request_user string        ,--邀请人
    total_mark bigint            ,--会员积分
    used_mark bigint            ,--已使用积分
    level_name string            ,--会员等级名称
    blacklist bigint            ,--用户黑名单
    is_married bigint            ,--婚姻状况
    education string            ,--学历
    monthly_money double        ,--收入
    profession string            --职业
    ) partitioned by (dt string)
    row format delimited fields terminated by ','
    location '/business/bdm/bdm_user' ; //外部分区表

    //添加分区
    alter table bdm.bdm_user add partition (dt='2017-01-01');
    //把BDM层源数据文件导入到 BDM层 外部分区表中
    hdfs dfs -put /root/source_data/bdm_user.txt /business/bdm/bdm_user/dt=2017-01-01

---------------------------------------------------------------------
客户基本属性表 FDM层（对 BDM 源数据层的数据进行清洗和预处理）

    --客户基本属性表 FDM层（对 BDM 源数据层的数据进行清洗和预处理）
    create database if not exists fdm;

    create table if not exists fdm.fdm_user_wide( 
    user_id string                ,--用户ID
    user_name string            ,--用户登陆名
    user_sex  string            ,--用户性别
    user_birthday string        ,--用户生日
    user_age  bigint            ,--用户年龄
    constellation string        ,--用户星座
    province string                ,--省份
    city string                    ,--城市
    city_level string            ,--城市等级
    hex_mail string                ,--邮箱
    op_mail string                ,--邮箱运营商
    hex_phone string            ,--手机号
    fore_phone string            ,--手机前3位
    op_phone string                ,--手机运营商
    add_time string                ,--注册时间
    login_ip string                ,--登陆ip地址
    login_source string            ,--登陆来源
    request_user string            ,--邀请人
    total_mark bigint            ,--会员积分
    used_mark bigint            ,--已使用积分
    level_name string            ,--会员等级名称
    blacklist bigint            ,--用户黑名单
    is_married bigint            ,--婚姻状况
    education string            ,--学历
    monthly_money double        ,--收入
    profession string            ,--职业
    dw_date  timestamp
    ) partitioned by (dt string);

---------------------------------------------------------------------

    --加载 客户基本属性模型表BDM层（源数据层）数据 到 客户基本属性表 FDM层
    insert overwrite table fdm.fdm_user_wide partition(dt='2017-01-01')
    select 
    t.user_id,
    t.user_name,
    t.user_sex,
    t.user_birthday,
    t.user_age,
    t.constellation,
    t.province,
    t.city,
    t.city_level,
    t.hex_mail,
    t.op_mail,
    t.hex_phone,
    t.fore_phone,
    t.op_phone,
    t.add_time,
    t.login_ip,
    t.login_source,
    t.request_user,
    t.total_mark,
    t.used_mark,
    t.level_name,
    t.blacklist,
    t.is_married,
    t.education,
    t.monthly_money,
    t.profession, 
    from_unixtime(unix_timestamp())  dw_date   // 作为操作时间：返回 Unix时间戳(Unix timestamp)，表示将 Unix 时间戳字符串进行格式化
    from bdm.bdm_user t where dt='2017-01-01';

---------------------------------------------------------------------

用户画像-客户基本属性模型表 GDM层（对 FDM 层数据 进行统计一些指标数据）

    --用户画像-客户基本属性模型表 GDM层（对 FDM 层数据 进行统计一些指标数据）
    create database if not exists gdm;

    create  table if not exists gdm.gdm_user_basic( 
    user_id string                ,--用户ID
    user_name string            ,--用户登陆名
    user_sex  string            ,--用户性别
    user_birthday string        ,--用户生日
    user_age  bigint            ,--用户年龄
    constellation string        ,--用户星座
    province string                ,--省份
    city string                    ,--城市
    city_level string            ,--城市等级
    hex_mail string                ,--邮箱
    op_mail string                ,--邮箱运营商
    hex_phone string            ,--手机号
    fore_phone string            ,--手机前3位
    op_phone string                ,--手机运营商
    add_time string                ,--注册时间
    login_ip string                ,--登陆ip地址
    login_source string            ,--登陆来源
    request_user string            ,--邀请人
    total_mark bigint            ,--会员积分
    used_mark bigint            ,--已使用积分
    level_name string            ,--会员等级名称
    blacklist bigint            ,--用户黑名单
    is_married bigint            ,--婚姻状况
    education string            ,--学历
    monthly_money double        ,--收入
    profession string            ,--职业
    //下面的该些指标需要通过人工智能算法实现计算得出
    sex_model bigint            ,--性别模型
    is_pregnant_woman bigint    ,--是否孕妇
    is_have_children bigint        ,--是否有小孩
    children_sex_rate double    ,--孩子性别概率
    children_age_rate double    ,--孩子年龄概率
    is_have_car bigint            ,--是否有车
    potential_car_user_rate double,--潜在汽车用户概率
    phone_brand string            ,--使用手机品牌
    phone_brand_level string    ,--使用手机品牌档次
    phone_cnt bigint            ,--使用多少种不同的手机
    change_phone_rate bigint    ,--更换手机频率
    majia_flag string            ,--马甲标志
    majie_account_cnt bigint    ,--马甲账号数量
    loyal_model bigint            ,--用户忠诚度
    shopping_type_model bigint    ,--用户购物类型
    figure_model bigint            ,--身材
    stature_model bigint        ,--身高
    dw_date timestamp  // Unix时间戳(Unix timestamp)
    ) partitioned by (dt string);

---------------------------------------------------------------------

    --加载 FDM 层数据（对 FDM 层数据 进行统计一些指标数据） 到 GDM 层
    insert overwrite table  gdm.gdm_user_basic partition(dt='2017-01-01') 
    select 
    t.user_id,
    t.user_name,
    t.user_sex,
    t.user_birthday,
    t.user_age,
    t.constellation,
    t.province,
    t.city,
    t.city_level,
    t.hex_mail,
    t.op_mail,
    t.hex_phone,
    t.fore_phone,
    t.op_phone,
    t.add_time,
    t.login_ip,
    t.login_source,
    t.request_user,
    t.total_mark,
    t.used_mark,
    t.level_name,
    t.blacklist,
    t.is_married,
    t.education,
    t.monthly_money,
    t.profession, 
    //下面的该些指标需要通过人工智能算法实现计算得出
    null sex_model,        --数据挖掘模型-开始
    null is_pregnant_woman,
    null is_have_children,
    null children_sex_rate,
    null children_age_rate,
    null is_have_car,
    null potential_car_user_rate,
    null phone_brand,
    null phone_brand_level,
    null phone_cnt,
    null change_phone_rate,
    null majia_flag,
    null majie_account_cnt,
    null loyal_model,
    null shopping_type_model,
    null figure_model,
    null stature_model,        --数据挖掘模型-结束
    from_unixtime(unix_timestamp())  dw_date // 作为操作时间：返回 Unix时间戳(Unix timestamp)，表示将 Unix 时间戳字符串进行格式化
    from (select * from fdm.fdm_user_wide where dt='2017-01-01') t;


===========  如下 各维度表 最终合并为 客户消费模型表 ============  
订单表
        1.订单 主要信息表
                1.订单 主要信息表 BDM层 bdm.bdm_order（时间分区）
                2.订单 主要信息表 FDM层 fdm.fdm_order（时间分区）
                
        2.订单 详细信息表
                1.订单 详细信息表 BDM层 bdm.bdm_order_desc（时间分区）
                2.订单 详细信息表 FDM层 fdm.fdm_order_desc（时间分区）
                
        3.订单表 GDM层
                订单宽表 GDM层 gdm.gdm_order（时间分区）
                订单宽表 GDM层 = 订单 主要信息表 FDM层 + 订单 详细信息表 FDM层 
----------------------------------------
订单中 商品信息表
        1.订单中 商品信息表 BDM层 bdm.bdm_order_goods（时间分区）
        2.订单中 商品信息表 FDM层 fdm.fdm_order_goods（时间分区）

----------------------------------------

客户订单地址表
        客户订单地址表 GDM层 gdm.gdm_user_order_addr_model

----------------------------------------
        
购物车中 商品信息表
        1.购物车中 商品信息表 BDM层 bdm.bdm_order_cart（时间分区）
        2.购物车中 商品信息表 FDM层 fdm.fdm_order_cart（时间分区）
----------------------------------------
客户消费模型表（订单+购物车） GDM层（临时表）
        没有时间分区，但是每次都会先 drop 临时表，再 CREATE 临时表 as select ...
        
        1.临时表01（订单）
            客户消费模型表 GDM层 gdm.gdm_user_consume_order_temp_01（临时表01）统计订单相关指标
            客户消费模型表 GDM层 = 订单表 GDM层（时间分区） + 订单中商品信息表 FDM层（时间分区） + 客户订单地址表 GDM层
            
        2.临时表02（购物车）
            客户消费模型表 GDM层 gdm.gdm_user_consume_order_temp_02（临时表02） 统计购物车相关指标 
            客户消费模型表 GDM层 = 购物车中商品信息表 FDM层（时间分区）
            
        3.临时表03（订单）
            客户消费模型表 GDM层 gdm.gdm_user_consume_order_temp_03（临时表03） 统计订单中常用收货地址和常用支付方式 
            客户消费模型表 GDM层 = 订单表 GDM层（时间分区）+ 订单表 GDM层（时间分区）（订单表自身和自身union all）
            
        4.临时表04
            gdm.gdm_user_consume_order_temp_100（临时表04） = 客户消费模型表 GDM层（临时表01） + 客户消费模型表 GDM层（临时表02） 
            目的：仅为合并 订单表 和 购物车表 中所相同的的user_id为一个分组，并且最终合并结果中的每条数据均为不同的user_id

----------------------------------------

客户消费模型表（订单+购物车） GDM层
        SQL目的：统计 订单 和 购物车 中相关消费指标。
        当前该表中的“近30/60/90天的XX”等指标 仅为时间分区中每天的指标数据，因此还必须根据 where dt >= date_add(昨天日期时间, -29/-60/-90) 条件进行统计， 
        这样才能统计出真正的“近30/60/90天的XX”指标数据。
        客户消费模型表 GDM层 gdm.gdm_user_consume_order（时间分区）
        客户消费模型表 GDM层 = 临时表01 + 临时表02 + 临时表03 + 临时表04

====================== 订单表 ========================= 
订单表
        1.订单 主要信息表
                1.订单 主要信息表 BDM层 bdm.bdm_order（时间分区）
                2.订单 主要信息表 FDM层 fdm.fdm_order（时间分区）
                
        2.订单 详细信息表
                1.订单 详细信息表 BDM层 bdm.bdm_order_desc（时间分区）
                2.订单 详细信息表 FDM层 fdm.fdm_order_desc（时间分区）
                
        3.订单表 GDM层
                订单宽表 GDM层 gdm.gdm_order（时间分区）
                订单宽表 GDM层 = 订单 主要信息表 FDM层 + 订单 详细信息表 FDM层 
----------------------------------------

订单宽表 模型开发 BDM 层（源数据层）

    --订单主要信息表 BDM 层（源数据层）
    create database if not exists bdm;

    create external table if not exists bdm.bdm_order(
    order_id string,        --订单ID
    order_no string,        --订单号
    order_date string,    --订单日期
    user_id     string,        --用户ID
    user_name string,        --登录名
    order_money double,    --订单金额
    order_type string,    --订单类型
    order_status string,    --订单状态
    pay_status string,    --支付状态
    pay_type string,        --支付方式  1、在线支付，2、货到付款
    order_source string,    --订单来源
    update_time timestamp    --订单更新时间
    ) partitioned by (dt string)
    row format delimited fields terminated by ','
    lines terminated by '\n' //以某字符来分割每行
    location '/business/bdm_order';

    //添加分区
    alter table bdm.bdm_order add partition (dt='2017-01-01') location '/business/bdm_order/dt=2017-01-01';

    //把 BDM层源数据文件 导入到 BDM层 外部分区表中
    hdfs dfs -put /root/source_data/bdm_order.txt /business/bdm_order/dt=2017-01-01/

----------------------------------------
订单 主要信息表 FDM层（对 BDM 源数据层的数据进行清洗和预处理）
    --订单主要信息表FDM层
    create database if not exists fdm;

    create table if not exists fdm.fdm_order(
    order_id string,        --订单ID
    order_no string,        --订单号
    order_date string,    --订单日期
    user_id     string,        --用户ID
    user_name string,        --登录名
    order_money double,    --订单金额
    order_type string,    --订单类型
    order_status string,    --订单状态
    pay_status string,    --支付状态
    pay_type string,        --支付方式  1、在线支付，2、货到付款
    order_source string,    --订单来源
    update_time timestamp,    --订单更新时间
    dw_date timestamp
    ) partitioned by (dt string);

----------------------------------------

    --加载 订单主要信息模型表BDM层（源数据层）数据 到 订单主要信息表 FDM层  
    insert overwrite table fdm.fdm_order partition(dt='2017-01-01')
    select 
    t.order_id,        --订单ID
    t.order_no,        --订单号
    t.order_date,        --订单日期
    t.user_id,        --用户ID
    t.user_name,    --登录名
    t.order_money,    --订单金额
    t.order_type,    --订单类型
    t.order_status,    --订单状态
    t.pay_status,    --支付状态
    t.pay_type,        --支付方式
    t.order_source,    --订单来源
    t.update_time timestamp,    --订单更新时间
    from_unixtime(unix_timestamp())  dw_date   // 作为操作时间：返回 Unix时间戳(Unix timestamp)，表示将 Unix 时间戳字符串进行格式化
    from bdm.bdm_order t where dt='2017-01-01';

----------------------------------------

订单 详细信息表 BDM 层（源数据层）
     create database if not exists bdm;

    create external table if not exists bdm.bdm_order_desc(
    order_id string,        --订单ID
    order_no string,        --订单号
    consignee string,        --收货人姓名
    area_id string,        --收货人地址ID
    area_name string,        --地址ID对应的地址段
    address string,        --收货人地址
    mobile string,        --收货人手机号
    telphone string,        --收货人电话
    coupon_id bigint,        --使用代金券ID
    coupon_money double,    --使用代金券金额
    carriage_money double,    --运费
    create_time timestamp,    --创建时间
    update_time timestamp,    --更新时间
    dw_date timestamp
    )partitioned by (dt string)
    row format delimited fields terminated by ','
    location '/business/bdm_order_desc';

    //添加分区
    alter table bdm.bdm_order_desc add partition (dt='2017-01-01') location '/business/bdm_order_desc/dt=2017-01-01';

    //把 BDM层源数据文件 导入到 BDM层 外部分区表中
    hdfs dfs -put /root/source_data/bdm_order_desc.txt /business/bdm_order_desc/dt=2017-01-01

----------------------------------------

订单 详细信息表 FDM层（对 BDM 源数据层的数据进行清洗和预处理）
    create database if not exists fdm; 

    create table if not exists fdm.fdm_order_desc(
    order_id string,        --订单ID
    order_no string,        --订单号
    consignee string,        --收货人姓名
    area_id string,        --收货人地址ID
    area_name string,        --地址ID对应的地址段
    address string,        --收货人地址
    mobile string,        --收货人手机号
    telphone string,        --收货人电话
    coupon_id bigint,        --使用代金券ID
    coupon_money double,    --使用代金券金额
    carriage_money double,    --运费
    create_time timestamp,    --创建时间
    update_time timestamp,    --更新时间
    dw_date timestamp
    ) partitioned by (dt string);

----------------------------------------  

 --加载 订单详细信息模型表 BDM层（源数据层）数据 到 订单详细信息表 FDM层  
    insert overwrite table fdm.fdm_order_desc partition(dt='2017-01-01')
    select 
    t.order_id,        --订单ID
    t.order_no,        --订单号
    t.consignee,        --收货人姓名
    t.area_id,        --收货人地址ID
    t.area_name,        --地址ID对应的地址段
    t.address,        --收货人地址
    t.mobile,        --收货人手机号
    t.telphone,        --收货人电话
    t.coupon_id,        --使用代金券ID
    t.coupon_money,        --使用代金券金额
    t.carriage_money,        --运费
    t.create_time,        --创建时间
    t.update_time,        --更新时间
    from_unixtime(unix_timestamp())  dw_date    // 作为操作时间：返回 Unix时间戳(Unix timestamp)，表示将 Unix 时间戳字符串进行格式化
    from bdm.bdm_order_desc t where dt='2017-01-01';

----------------------------------------

订单宽表模型 GDM 层（对 FDM 层数据 进行统计一些指标数据）
    --订单宽表模型    
    create database if not exists gdm;

    create table if not exists gdm.gdm_order(
    order_id string,--订单ID
    order_no string,--订单号
    order_date string,--订单日期
    user_id string,--用户ID
    user_name string,--登录名
    order_money double,--订单金额
    order_type string,--订单类型
    order_status string,--订单状态
    pay_status string,--支付状态
    pay_type string,--支付方式  1、在线支付，2、货到付款
    order_source string,--订单来源
    consignee string,--收货人姓名
    area_id string,--收货人地址ID
    area_name string,--地址ID对应的地址段（粒度到县）
    address string,--收货人地址（手工填写的地址）
    mobile string,--收货人手机号
    telphone string,--收货人电话
    coupon_id bigint,--使用代金券ID
    coupon_money double,--使用代金券金额
    carriage_money double,--运费
    create_time timestamp,--创建时间
    update_time timestamp,--更新时间
    dw_date timestamp --操作时间
    ) partitioned by (dt string);

----------------------------------------

    （关联查询）把 订单 主要信息表 FDM层 和 订单 详细信息表 FDM层 对同一订单信息 进行连接查询后返回的数据 加载到 订单宽表 FDM层
    insert overwrite table gdm.gdm_order partition(dt='2017-01-01') 
    select 
    a.order_id,        --订单ID
    a.order_no,        --订单号
    a.order_date,    --订单日期
    a.user_id,        --用户ID
    a.user_name,    --用户名
    a.order_money,    --订单金额
    a.order_type,    --订单类型
    a.order_status,    --订单状态
    a.pay_status,    --支付类型
    a.pay_type,        --支付方式
    a.order_source,    --订单来源
    b.consignee,    --收货人姓名
    b.area_id,        --收货人地址ID
    b.area_name,    --地址ID对应的地址段
    b.address,        --收货人地址
    b.mobile,       --收货人手机号
    b.telphone,     --收货人电话
    b.coupon_id,    --使用代金券ID
    b.coupon_money, --使用代金券金额
    b.carriage_money,--运费
    b.create_time,  --创建时间
    b.update_time,  --更新时间
    from_unixtime(unix_timestamp()) dw_date  // 作为操作时间：返回 Unix时间戳(Unix timestamp)，表示将 Unix 时间戳字符串进行格式化
    from (select * from fdm.fdm_order where dt='2017-01-01') a  //订单 主要信息表 FDM层
    join (select * from fdm.fdm_order_desc where dt='2017-01-01') b //订单 详细信息表 FDM层
    on a.order_id=b.order_id;  //以订单号ID作为两表连接查询的连接条件
 

=================== 订单中 商品信息表 ======================== 
订单中 商品信息表
        1.订单中 商品信息表 BDM层 bdm.bdm_order_goods（时间分区）
        2.订单中 商品信息表 FDM层 fdm.fdm_order_goods（时间分区）

----------------------------------------

订单商品信息表 BDM 层（源数据层）
    -----订单商品信息表 BDM 层（源数据层）
    create database if not exists bdm;

    create external table if not exists bdm.bdm_order_goods(
    user_id string,--用户ID
    order_id string,--订单ID
    order_no string,--订单号
    sku_id bigint,--SKU编号
    sku_name string,--SKU名称
    goods_id bigint,--商品编号
    goods_no string,--商品货号
    goods_sn string,--商品条码
    goods_name string,--商品名称
    goods_amount bigint,--商品数量
    size_id bigint,--尺码编号
    size_name string,--尺码名称
    colour_id bigint,--颜色ID
    shop_id string,--店铺编号
    shop_name string,--店铺名称
    curr_price double,--售卖价格
    market_price double,--市场价格
    discount double,--折扣比例
    cost_price double,--成本价格
    cost_type string,--成本类型
    warehouse string,--所在仓库
    first_cart bigint,-- 一级分类ID
    first_cart_name string,-- 一级分类名称
    second_cart bigint,-- 二级分类ID
    second_cart_name string,-- 二级分类名称
    third_cart bigint,-- 三级分类ID
    third_cart_name string,-- 三级分类名称
    dw_date timestamp
    ) partitioned by (dt string)
    row format delimited fields terminated by ','
    lines terminated by '\n'
    location '/business/bdm_order_goods';

    //添加分区
    alter table bdm.bdm_order_goods add partition (dt='2017-01-01') location '/business/bdm_order_goods/dt=2017-01-01';

    //把 BDM层源数据文件 导入到 BDM层 外部分区表中
    hdfs dfs -put /root/source_data/bdm_order_goods.txt /business/bdm_order_goods/dt=2017-01-01

----------------------------------------
订单商品信息表 FDM 层（对 BDM 源数据层的数据进行清洗和预处理）
    -----订单商品信息表 FDM 层（对 BDM 源数据层的数据进行清洗和预处理）
    create database if not exists fdm;

    create  table if not exists fdm.fdm_order_goods(
    user_id     string,--用户ID 
    order_id string,--订单ID
    order_no string,--订单号
    sku_id bigint,--SKU编号
    sku_name string,--SKU名称
    goods_id bigint,--商品编号
    goods_no string,--商品货号
    goods_sn string,--商品条码
    goods_name string,--商品名称
    goods_amount bigint,--商品数量
    size_id bigint,--尺码编号
    size_name string,--尺码名称
    colour_id bigint,--颜色ID
    shop_id string,--店铺编号
    shop_name string,--店铺名称
    curr_price double,--售卖价格
    market_price double,--市场价格
    discount double,--折扣比例
    cost_price double,--成本价格
    cost_type string,--成本类型
    warehouse string,--所在仓库
    first_cart bigint,-- 一级分类ID
    first_cart_name string,-- 一级分类名称
    second_cart bigint,-- 二级分类ID
    second_cart_name string,-- 二级分类名称
    third_cart bigint,-- 三级分类ID
    third_cat_name string,-- 三级分类名称
    dw_date timestamp
    ) partitioned by (dt string);

----------------------------------------

    --加载 订单商品信息表 BDM层（源数据层）数据 到 订单商品信息表 FDM层  
    insert overwrite table fdm.fdm_order_goods partition(dt='2017-01-01')
    select
    t.user_id,--用户ID  
    t.order_id,--订单ID
    t.order_no,--订单号
    t.sku_id,--SKU编号
    t.sku_name,--SKU名称
    t.goods_id,--商品编号
    t.goods_no,--商品货号
    t.goods_sn,--商品条码
    t.goods_name,--商品名称
    t.goods_amount,--商品数量
    t.size_id,--尺码编号
    t.size_name,--尺码名称
    t.colour_id,--颜色ID
    t.shop_id,--店铺编号
    t.shop_name,--店铺名称
    t.curr_price,--售卖价格
    t.market_price,--市场价格
    t.discount,--折扣比例
    t.cost_price,--成本价格
    t.cost_type,--成本类型
    t.warehouse,--所在仓库
    t.first_cart,-- 一级分类ID
    t.first_cart_name,-- 一级分类名称
    t.second_cart,-- 二级分类ID
    t.second_cart_name,-- 二级分类名称
    t.third_cart,-- 三级分类ID
    t.third_cart_name,-- 三级分类名称
    from_unixtime(unix_timestamp()) dw_date 
    from bdm.bdm_order_goods t where dt='2017-01-01';
 

====================== 客户订单地址表 =================

客户订单地址表
        客户订单地址表 GDM层 gdm.gdm_user_order_addr_model

----------------------------------------

客户订单地址模型表（用于标识是在哪里下单的）GDM 层（对 FDM 层数据 进行统计一些指标数据）
    create database if not exists gdm;

    create  table if not exists gdm.gdm_user_order_addr_model(
    user_id string,        -- 客户ID
    order_addr bigint,    -- 1表示学校、2表示单位、3表示家里
    user_order_flag string    -- 关联标识
    )
    row format delimited fields terminated by ','
    lines terminated by '\n';

    load data local inpath '/root/source_data/gdm_user_order_addr_model.txt' overwrite into table gdm.gdm_user_order_addr_model;

 

====================== 购物车中 商品信息表 =================
购物车中 商品信息表
        1.购物车中 商品信息表 BDM层 bdm.bdm_order_cart（时间分区）
        2.购物车中 商品信息表 FDM层 fdm.fdm_order_cart（时间分区）
----------------------------------------

 购物车订单表 BDM 层（源数据层）
    create database if not exists bdm;

    create external table if not exists bdm.bdm_order_cart(
    id bigint,--ID
    session_id string,--sessionID
    user_id string,--用户ID
    goods_id string,--商品ID
    goods_num bigint,--商品数量
    add_time string,--商品加入时间
    cancle_time string,--商品取消时间
    sumbit_time string,--商品提交时间
    dw_date timestamp
    ) partitioned by (dt string)
    row format delimited fields terminated by ','
    lines terminated by '\n'
    location '/business/bdm_order_cart';

    //添加分区 
    alter table bdm.bdm_order_cart add partition (dt='2017-01-01') location '/business/bdm_order_cart/dt=2017-01-01';

    //把 BDM层源数据文件 导入到 BDM层 外部分区表中
    hdfs dfs -put /root/source_data/bdm_order_cart.txt /business/bdm_order_cart/dt=2017-01-01

----------------------------------------

购物车订单表 FDM 层（对 BDM 源数据层的数据进行清洗和预处理）
    create database if not exists fdm;

    create  table  if not exists fdm.fdm_order_cart(
    id bigint,--ID
    session_id string,--sessionID
    user_id string,--用户ID
    goods_id string,--商品ID
    goods_num bigint,--商品数量
    add_time string,--商品加入时间
    cancle_time string,--商品取消时间
    sumbit_time string,--商品提交时间
    dw_date timestamp
    ) partitioned by (dt string);

----------------------------------------

    --加载 购物车订单表 BDM层（源数据层）数据 到 购物车订单表 FDM层  
    insert overwrite table fdm.fdm_order_cart partition(dt='2017-01-01')
    select 
    t.id,--ID
    t.session_id,--sessionID
    t.user_id,--用户ID
    t.goods_id,--商品ID
    t.goods_num ,--商品数量
    t.add_time,--商品加入时间
    t.cancle_time,--商品取消时间
    t.sumbit_time,--商品提交时间
    from_unixtime(unix_timestamp())  dw_date 
    from bdm.bdm_order_cart t where dt='2017-01-01';

================= 客户消费模型表：临时表 ====================
客户消费模型表（订单+购物车） GDM层（临时表）
        没有时间分区，但是每次都会先 drop 临时表，再 CREATE 临时表 as select ...
        
        1.临时表01（订单）
            客户消费模型表 GDM层 gdm.gdm_user_consume_order_temp_01（临时表01）统计订单相关指标
            客户消费模型表 GDM层 = 订单表 GDM层（时间分区） + 订单中商品信息表 FDM层（时间分区） + 客户订单地址表 GDM层
            
        2.临时表02（购物车）
            客户消费模型表 GDM层 gdm.gdm_user_consume_order_temp_02（临时表02） 统计购物车相关指标 
            客户消费模型表 GDM层 = 购物车中商品信息表 FDM层（时间分区）
            
        3.临时表03（订单）
            客户消费模型表 GDM层 gdm.gdm_user_consume_order_temp_03（临时表03） 统计订单中常用收货地址和常用支付方式 
            客户消费模型表 GDM层 = 订单表 GDM层（时间分区）+ 订单表 GDM层（时间分区）（订单表自身和自身union all）
            
        4.临时表04
            gdm.gdm_user_consume_order_temp_100（临时表04） = 客户消费模型表 GDM层（临时表01） + 客户消费模型表 GDM层（临时表02） 
            目的：仅为合并 订单表 和 购物车表 中所相同的的user_id为一个分组，并且最终合并结果中的每条数据均为不同的user_id

----------------------------------------

客户消费订单模型表 GDM 层（对 FDM 层数据 进行统计一些指标数据）：

    --统计订单相关指标（GDM 层的 临时表 gdm_user_consume_order_temp_01） 
    drop table if exists gdm.gdm_user_consume_order_temp_01;
 
    第一个版本：（上课时的该版本有所缺失，订单商品信息表没有WHERE+时间分区）
        CREATE TABLE gdm.gdm_user_consume_order_temp_01临时表 AS   
        SELECT ...FROM (SELECT ... FROM gdm.gdm_order订单宽表 WHERE dt = '2017-01-01') t 
        LEFT JOIN (SELECT ... FROM fdm.fdm_order_goods订单商品信息表) t1 ON (t.order_id = t1.order_id) //order_id 订单ID
        LEFT JOIN (SELECT ... FROM gdm.gdm_user_order_addr_model客户订单位置表) t2 ON (t.user_id = t2.user_id) //user_id 用户ID
        GROUP BY t.user_id ;

    第二个版本：（该版本才为正确，订单商品信息表的WHERE加上了时间分区）
        CREATE TABLE gdm.gdm_user_consume_order_temp_01临时表 AS   
        SELECT ...FROM (SELECT ... FROM gdm.gdm_order订单宽表 WHERE dt = '2017-01-01') t 
        LEFT JOIN (SELECT ... FROM fdm.fdm_order_goods订单商品信息表 WHERE dt = '2017-01-01') t1 ON (t.order_id = t1.order_id) //order_id 订单ID
        LEFT JOIN (SELECT ... FROM gdm.gdm_user_order_addr_model客户订单位置表) t2 ON (t.user_id = t2.user_id) //user_id 用户ID
        GROUP BY t.user_id ;

    //下面很长的SQL语句的结构总结为上面的5行SQL结构
    CREATE TABLE gdm.gdm_user_consume_order_temp_01 AS 
    SELECT 
      t.user_id,
      MIN(order_date) first_order_time,        --第一次消费时间：获取所有订单时间然后使用MIN()即能获取第一次消费时间
      MAX(order_date) last_order_time,        --最近一次消费时间：获取所有订单时间然后使用MAX()即能获取最近一次消费时间
      DATEDIFF(MIN(order_date), '2017-01-01') first_order_ago,    --首单距今时间：使用“2017-01-01”减去 MIN(订单时间) 即能获取首单距今时间
      DATEDIFF(MAX(order_date), '2017-01-01') last_order_ago,    --尾单距今时间：使用“2017-01-01”减去 MAX(订单时间) 即能获取尾单距今时间
      SUM(  //SUM()：累加30天内的所有订单的总次数 
        CASE
          WHEN t.dat_30 = 1 //1 表示订单是近30天内的
          AND t.order_flag = 0  //0 表示订单为非退拒的
          THEN 1 
        END
      ) month1_hg_order_cnt,        --近30天购买次数（不含退拒）
      SUM(  //SUM()：累加30天内的所有订单的总金额 
        CASE
          WHEN t.dat_30 = 1   //1 表示订单是近30天内的
          AND t.order_flag = 0   //0 表示订单为非退拒的
          THEN t.order_money   //返回订单购买金额
        END
      ) month1_hg_order_amt,        --近30天购买金额（不含退拒）
      SUM(  //SUM()：累加60天内的所有订单的总次数
        CASE
          WHEN t.dat_60 = 1 
          AND t.order_flag = 0 
          THEN 1 
        END
      ) month2_hg_order_cnt,        --近60天购买次数（不含退拒）
      SUM(  //SUM()：累加60天内的所有订单的总金额
        CASE
          WHEN t.dat_60 = 1 
          AND t.order_flag = 0 
          THEN t.order_money 
        END
      ) month2_hg_order_amt,        --近60天购买金额（不含退拒）
      SUM(   //SUM()：累加90天内的所有订单的总次数
        CASE
          WHEN t.dat_90 = 1 
          AND t.order_flag = 0 
          THEN 1 
        END
      ) month3_hg_order_cnt,        --近90天购买次数（不含退拒）
      SUM(  //SUM()：累加90天内的所有订单的总金额
        CASE
          WHEN t.dat_90 = 1 
          AND t.order_flag = 0 
          THEN t.order_money 
        END
      ) month3_hg_order_amt,        --近90天购买金额（不含退拒）
      SUM(dat_30) month1_order_cnt,    --近30天购买次数（含退拒）
      SUM(
        CASE
          WHEN t.dat_30 = 1 
          THEN t.order_money 
        END
      ) month1_order_amt,        --近30天购买金额（含退拒）
      SUM(dat_60) month2_order_cnt,    --近60天购买次数（含退拒）
      SUM(
        CASE
          WHEN t.dat_60 = 1 
          THEN t.order_money 
        END
      ) month2_order_amt,        --近60天购买金额（含退拒）
      SUM(dat_90) month3_order_cnt,    --近90天购买次数（含退拒）
      SUM(
        CASE
          WHEN t.dat_90 = 1 
          THEN t.order_money 
        END
      ) month3_order_amt,        --近90天购买金额（含退拒）
      MAX(t.order_money) max_order_amt,    --最大消费金额 //获取出所有的订单消费金额，然后使用MAX()获取最大的订单消费金额
      MIN(t.order_money) min_order_amt,    --最小消费金额 //获取出所有的订单消费金额，然后使用MIX()获取最小的订单消费金额
      SUM(  //SUM()：累加所有非退拒的订单的总数
        CASE
          WHEN t.order_flag = 0  //0 表示订单为非退拒的
          THEN 1
        END
      ) total_order_cnt,        --累计消费次数（不含退拒）
      SUM( //SUM()：累加所有非退拒的订单的总金额
        CASE
          WHEN t.order_flag = 0 
          THEN t.order_money  //返回订单购买金额
        END
      ) total_order_amt,                --累计消费金额（不含退拒）
      SUM(coupon_money) total_coupon_amt,        --累计使用代金券金额 //SUM()：累加所有代金券金额
      SUM(t.order_money) / COUNT(1) user_avg_amt,    --客单价（含退拒）：客单价表示每个订单平均价格，通过所有商品总价格除以总订单数得出每个订单平均价格    
      //SUM()：累加所有订单总金额。COUNT(1)：统计订单的总数
      0 month3_user_avg_amt,            --近90天的客单价（含退拒）
      0 common_address,            --常用收获地址
      0 common_paytype,            --常用支付方式
      0 month1_cart_cnt,            --最近30天购物车次数
      0 month1_cart_goods_cnt,            --最近30天购物车商品件数
      0 month1_cart_submit_cnt,        --最近30天购物车提交商品件数
      0 month1_order_rate,            --最近30天购物车成功率
      0 month1_cart_cancle_cnt,        --最近30天购物车放弃件数
     SUM(  //SUM()：累加所有退货商品的数量
        CASE
          WHEN t.order_status = 3  //订单状态为3表示退货
          THEN t1.goods_amount    //退货商品数量
        END
      ) return_cnt,            --退货商品数量
      SUM(  //SUM()：累加所有退货商品的金额
        CASE
          WHEN t.order_status = 3   //订单状态为3表示退货
          THEN t.order_money  //退货商品金额
        END
      ) return_amt,            --退货商品金额
      SUM(   //SUM()：累加所有拒收商品的数量
        CASE
          WHEN t.order_status = 4 //订单状态为4表示拒收
          THEN t1.goods_amount  //拒收商品数量
        END
      ) reject_cnt,            --拒收商品数量
      SUM(  //SUM()：累加所有拒收商品的金额
        CASE
          WHEN t.order_status = 4 //订单状态为4表示拒收
          THEN t.order_money   //拒收商品金额
        END
      ) reject_amt,            --拒收商品金额
      MAX(  //MAX()获取最大的退货商品时间
        CASE
          WHEN t.order_status = 3  //订单状态为3表示退货
          THEN t.order_date    //退货商品时间
        END
      ) last_return_time,        --最近一次退货时间
      SUM(  //SUM()：累加所有学校下单的总数
        CASE
          WHEN t2.order_addr = 1 
          THEN 1 
        END
      ) school_order_cnt,        --学校下单总数
      SUM(   //SUM()：累加所有单位下单的总数
        CASE
          WHEN t2.order_addr = 2 
          THEN 1 
        END
      ) company_order_cnt,        --单位下单总数
      SUM(  //SUM()：累加所有家里下单的总数
        CASE
          WHEN t2.order_addr = 3 
          THEN 1 
        END
      ) home_order_cnt,        --家里下单总数
      SUM(  //SUM()：累加所有上午下单的总数
        CASE
          WHEN t.order_hour >= 8 
          AND t.order_hour <= 11 
          THEN 1 
        END
      ) forenoon_order_cnt,        --上午下单总数
      SUM(//SUM()：累加所有下午下单的总数
        CASE
          WHEN t.order_hour >= 12 
          AND t.order_hour <= 18 
          THEN 1 
        END
      ) afternoon_order_cnt,        --下午下单总数
      SUM(//SUM()：累加所有晚上下单的总数
        CASE
          WHEN t.order_hour >= 19 
          AND t.order_hour <= 22 
          THEN 1 
        END
      ) night_order_cnt,        --晚上下单总数
      SUM(//SUM()：累加所有凌晨下单的总数
        CASE
          WHEN t.order_hour >= 23 
          or t.order_hour <= 7 
          THEN 1 
        END
      ) morning_order_cnt         --凌晨下单总数 
    FROM
      (SELECT a.*,
        (
          CASE  //如果order_date订单时间 大于“2017-01-01减去29天”并且小于“2017-01-01”的话，那么表示该订单便是（dat_30）30天之内的订单，那么便返回 1 
            WHEN order_date >= DATE_SUB('2017-01-01', 29) 
            AND order_date <= '2017-01-01' 
            THEN 1 
          END
        ) dat_30,
        (
          CASE  //如果order_date订单时间 大于“2017-01-01减去59天”并且小于“2017-01-01”的话，那么表示该订单便是（dat_60）60天之内的订单，那么便返回 1 
            WHEN order_date >= DATE_SUB('2017-01-01', 59) 
            AND order_date <= '2017-01-01' 
            THEN 1 
          END
        ) dat_60,
        (
          CASE  //如果order_date订单时间 大于“2017-01-01减去89天”并且小于“2017-01-01”的话，那么表示该订单便是（dat_90）90天之内的订单，那么便返回 1 
            WHEN order_date >= DATE_SUB('2017-01-01', 89) 
            AND order_date <= '2017-01-01' 
            THEN 1 
          END
        ) dat_90,
        (
          CASE  //退货标识：3。拒收标识：4
            WHEN a.order_status IN (3, 4) 
            THEN 1 
            ELSE 0 
          END
        ) order_flag,        --退货与拒收标示
        HOUR(order_date) order_hour  //订单下单时的具体哪个小时，用于标识是上午、下午、晚上、凌晨下单
        FROM gdm.gdm_order a 
        WHERE dt = '2017-01-01'
      ) t 
      LEFT JOIN 
        (SELECT 
          order_id,  //订单ID
          goods_amount  //商品数量
        FROM
          fdm.fdm_order_goods) t1
        ON (t.order_id = t1.order_id) 
      LEFT JOIN 
        (SELECT 
          user_id,   //用户ID
          order_addr //订单地址：是在哪里下单的
        FROM
          gdm.gdm_user_order_addr_model) t2 
        ON (t.user_id = t2.user_id) 
    GROUP BY t.user_id ;
----------------------------------------

客户消费订单模型表-临时表02：统计购物车相关指标 GDM 层（对 FDM 层数据 进行统计一些指标数据） 

    1.MySQL表 购物车表 数据 分为 2种：
        1.当前仍在购买车中的商品
        2.已经不在购买车中的商品(包括 被移除的商品、成功交易的商品)

    2.MySQL表 购物车表中 每个商品的 重要字段结构：
        商品添加进购物车的时间
        商品移出购物车的时间
        商品成功支付的时间
        商品名(SKU名)
        商品数量

    3.Hive表 每天定时所统计的购物车表源数据中 分为 3种：
        1.当天购买车中 等待支付的商品
        2.当天购买车中 被移除的商品
        3.当天购买车中 成功交易的商品

    4.第一种统计版本（上课时所使用的版本）：
        CREATE TABLE 表名 AS SELECT FROM 表名 WHERE dt=昨天 and (前第30天 <= add_time <= 昨天) GROUP BY user_id
        SQL目的：仅统计 近30天之内 被添加进购物车的商品，其中包含等待支付的商品、被移除的商品、成功交易的商品，但不统计 30天之前 已经被添加进购物车的商品。
        
    5.第二种统计版本（推荐实际使用的版本）
        CREATE TABLE 表名 AS SELECT FROM 表名 WHERE dt=昨天 GROUP BY user_id
        SQL目的：只要是被添加进购物车的商品都进行统计，而不仅限 近30天之内 被添加进购物车的商品
 
    drop table if exists gdm.gdm_user_consume_order_temp_02;

    CREATE TABLE gdm.gdm_user_consume_order_temp_02 AS 
    SELECT 
      user_id,
      COUNT(1) month1_cart_cnt,        --最近30天购物车次数  //COUNT(1)：统计购物车中的 不同商品种类数（SKU数）
      SUM(goods_num) month1_cart_goods_cnt,    --最近30天购物车商品件数//SUM()：累加购物车中所有商品总数
      SUM( //SUM()：累加购物车中所提交的成功支付的商品总数
        CASE
          WHEN sumbit_time <> ''   // <> '' 表示不等于空字符串，即商品提交时间不等于
          THEN goods_num 
          ELSE 0 
        END
      ) month1_cart_submit_cnt,        --最近30天购物车提交的成功支付商品件数
      SUM(//通过商品提交的数量除以购物车中商品总数得出购物车中商品成交的成功率
        CASE
          WHEN sumbit_time <> ''  
          THEN goods_num 
          ELSE 0 
        END
      )/SUM(goods_num) month1_cart_rate,    --最近30天购物车成功率 //商品提交的成功支付的数量除以购物车中商品总数
     SUM( //SUM()：累加购物车中所取消商品的总数
        CASE
          WHEN cancle_time <> '' 
          THEN goods_num 
          ELSE 0 
        END
      ) month1_cart_cancle_cnt          --最近30天购物车放弃件数(所移除的商品总数) 
    FROM
      fdm.fdm_order_cart 
    WHERE dt = '2017-01-01' 
       //时间日期转化函数to_date：可把字符串时间转换为日期类型时间。
       //添加商品时间 大于“2017-01-01减去29天”并且小于“2017-01-01”的话，那么表示该商品便是（dat_30）30天之内添加到购物车中的商品
      AND to_date (add_time) >= DATE_SUB('2017-01-01', 29) 
      AND to_date (add_time) <= '2017-01-01' 
    GROUP BY user_id ;

----------------------------------------
客户消费订单模型表-临时表03：统计常用收货地址和常用支付方式 GDM 层（对 FDM 层数据 进行统计一些指标数据）

    drop table if exists gdm.gdm_user_consume_order_temp_03;
 
    create table gdm.gdm_user_consume_order_temp_03临时表 
    as select ... from (select ... from (select ... from gdm.gdm_order订单宽表 where dt = '2017-01-01' group by ...
                        union all 
                      select ... from gdm.gdm_order订单宽表 where dt = '2017-01-01' group by ...) ) ;
 
    //下面长长的SQL简化为上面的SQL结构，下面的SQL目的：取出每个收货地址分组中使用最多的收货地址，或每个支付方式分组中使用最多的支付方式
    create table gdm.gdm_user_consume_order_temp_03 as 
    select 
      t.user_id,
      t.con,
      t.type,
      t.cnt 
    from
      (select 
        b.user_id,
        b.con,
        b.type,
        b.cnt,         //type：代表收货地址address或支付类型pay_type。别名rn 表示每个分组中的顺序序号，从1开始。
        row_number() over(distribute by b.user_id, b.type //把相同用户id和相同收货地址address分为一组，或把相同用户id和相同支付类型pay_type分为一组
                sort by b.cnt, b.type desc) rn //先按照使用次数cnt进行降序排序，如果使用次数cnt相同则按照type收货地址或支付类型进行降序排序
        from
          (select //该SQL目的：统计每个用户对应所使用的每个收货地址的使用次数  //coalesce(字段, '') 如果字段列的值为null空的话，则替换为空字符串
            a.user_id, concat(coalesce(area_name, ''), coalesce(address, '')) con, //concat 把多个字段列数据 拼接为 一个字符串
            'address' type, //给收货地址字段address取别名为type  
            count(1) cnt  //统计每个用户对应所使用的每个收货地址的使用次数
            from gdm.gdm_order a 
            where dt = '2017-01-01' 
            //对每个用户进行分组的同时，还对每个用户所对应使用的每个收货地址进行分组，意思即把相同用户id和相同收货地址分为一组
            group by a.user_id, concat(coalesce(area_name, ''), coalesce(address, ''))
            union all  //合并两个或多个 SELECT 语句的结果集，并且不会对合并后的结果集进行去重
            select //该SQL目的：统计每个用户对应所使用的每个支付方式的使用次数
              a.user_id, a.pay_type con,
              'pay_type' type, //给支付类型字段pay_type取别名为type
              count(1) cnt  //统计每个用户对应所使用的每个支付方式的使用次数
            from gdm.gdm_order a 
            where dt = '2017-01-01' 
            //对每个用户进行分组的同时，还对每个用户所对应使用的每个支付方式进行分组，意思即把相同用户id和相同支付方式分为一组    
            group by a.user_id, a.pay_type 
           ) b
       ) t 
       where t.rn = 1 ; //别名rn 表示每个分组中的顺序序号，从1开始。此处取出每个收货地址分组中使用最多的收货地址，或每个支付方式分组中使用最多的支付方式

    注意：
        select a.user_id, concat(coalesce(area_name, ''), coalesce(address, '')) con, 'address' type, count(1) cnt 
        group by a.user_id, concat(coalesce(area_name, ''), coalesce(address, ''))  
        union all 
        select a.user_id, a.pay_type con, 'pay_type' type, count(1) cnt group by a.user_id, a.pay_type 
    上面的SQL语句解析如下：    
        select 用户id,拼接收货地址,收货地址,收货地址的使用次数 from 订单宽表 group by 用户id,拼接收货地址
        union all 
        select 用户id,支付方式,支付方式,支付方式的使用次数 from 订单宽表 group by 用户id,支付方式
    解析：为的是使用union all把两张表合并时，把收货地址字段address和支付类型字段pay_type都合并为一列，取该列名为type，
          那么便可以直接对type字段进行分组，也即是对收货地址字段address和支付类型字段pay_type同时进行各自的分组。



----------------------------------------

订单表 和 购物车表 整合（临时表）目的：仅为合并两表中所相同的的user_id为一个分组，并且最终合并结果中的每条数据均为不同的user_id
    drop table if exists gdm.gdm_user_consume_order_temp_100;

    create table gdm.gdm_user_consume_order_temp_100 as 
    select 
      a.user_id 
    from
      (select user_id 
       from gdm.gdm_user_consume_order_temp_01 //统计 订单 相关指标的 临时表01
       union all 
       select user_id 
       from gdm.gdm_user_consume_order_temp_02 //统计 购物车 相关指标的 临时表02
      ) a 
       group by a.user_id ;

==================== 客户消费模型表：汇总数据 ================== 
客户消费模型表（订单+购物车） GDM层
        SQL目的：统计 订单 和 购物车 中相关消费指标。
        当前该表中的“近30/60/90天的XX”等指标 仅为时间分区中每天的指标数据，因此还必须根据 where dt >= date_add(昨天日期时间, -29/-60/-90) 条件进行统计， 
        这样才能统计出真正的“近30/60/90天的XX”指标数据。
        客户消费模型表 GDM层 gdm.gdm_user_consume_order（时间分区）
        客户消费模型表 GDM层 = 临时表01 + 临时表02 + 临时表03 + 临时表04

----------------------------------------

客户消费订单表模型 GDM 层（对 FDM 层数据 进行统计一些指标数据）
    
    SQL目的：统计 订单 和 购物车 中相关消费指标。
    当前该表中的“近30/60/90天的XX”等指标 仅为时间分区中每天的指标数据，因此还必须根据 where dt >= date_add(昨天日期时间, -29/-60/-90) 条件进行统计， 
    这样才能统计出真正的“近30/60/90天的XX”指标数据。 

    create database if not exists gdm;    

    create  table if not exists gdm.gdm_user_consume_order(
    user_id                string,        --客户ID
    first_order_time            timestamp,    --第一次消费时间
    last_order_time            timestamp,    --最近一次消费时间
    first_order_ago            bigint,        --首单距今时间
    last_order_ago            bigint,        --尾单距今时间
    month1_hg_order_cnt        bigint,        --近30天购买次数（不含退拒）
    month1_hg_order_amt        double,        --近30天购买金额（不含退拒）
    month2_hg_order_cnt        bigint,        --近60天购买次数（不含退拒）
    month2_hg_order_amt        double,        --近60天购买金额（不含退拒）
    month3_hg_order_cnt        bigint,        --近90天购买次数（不含退拒）
    month3_hg_order_amt        double,        --近90天购买金额（不含退拒）
    month1_order_cnt            bigint,        --近30天购买次数（含退拒）
    month1_order_amt            double,        --近30天购买金额（含退拒）
    month2_order_cnt            bigint,        --近60天购买次数（含退拒）
    month2_order_amt            double,        --近60天购买金额（含退拒）
    month3_order_cnt            bigint,        --近90天购买次数（含退拒）
    month3_order_amt            double,        --近90天购买金额（含退拒）
    max_order_amt            double,        --最大消费金额
    min_order_amt            double,        --最小消费金额
    total_order_cnt            bigint,        --累计消费次数（不含退拒）
    total_order_amt            double,        --累计消费金额（不含退拒）
    user_avg_amt            double,        --客单价（含退拒）//客单价表示每个订单平均价格，通过所有商品总价格除以总订单数得出每个订单平均价格
    month3_user_avg_amt        double,        --近90天的客单价
    common_address            string,        --常用收货地址
    common_paytype            string,        --常用支付方式
    month1_cart_cnt            bigint,        --近30天购物车的次数
    month1_cart_goods_cnt        bigint,        --近30天购物车商品件数
    month1_cart_submit_cnt        bigint,        --近30天购物车提交商品件数(成功交易)
    month1_cart_rate            double,        --近30天购物车成功率，通过上面的 购物车提交商品总件数 除以 购物车商品总件数 得出
    month1_cart_cancle_cnt        double,        --近30天购物车放弃件数
    return_cnt            bigint,        --退货商品数量
    return_amt            double,        --退货商品金额
    reject_cnt            bigint,        --拒收商品数量
    reject_amt            double,        --拒收商品金额
    last_return_time            timestamp,    --最近一次退货时间
    school_order_cnt            bigint,        --学校下单总数
    company_order_cnt            bigint,        --单位下单总数
    home_order_cnt            bigint,        --家里下单总数
    forenoon_order_cnt        bigint,        --上午下单总数
    afternoon_order_cnt        bigint,        --下午下单总数
    night_order_cnt            bigint,        --晚上下单总数
    morning_order_cnt            bigint,        --凌晨下单总数
    dw_date                timestamp
    ) partitioned by (dt string);
----------------------------------------

    //加载数据到 GDM层的 客户消费订单表
    INSERT overwrite TABLE gdm.gdm_user_consume_order PARTITION (dt = '2017-01-01') 
    SELECT 
      t.user_id,            --客户ID
      t1.first_order_time,         --第一次消费时间
      t1.last_order_time,         --最近一次消费时间
      t1.first_order_ago,         --首单距今时间
      t1.last_order_ago,         --尾单距今时间
      t1.month1_hg_order_cnt,        --近30天购买次数（不含退拒）
      t1.month1_hg_order_amt,        --近30天购买金额（不含退拒）
      t1.month2_hg_order_cnt,        --近60天购买次数（不含退拒）
      t1.month2_hg_order_amt,        --近60天购买金额（不含退拒）
      t1.month3_hg_order_cnt,        --近90天购买次数（不含退拒）
      t1.month3_hg_order_amt,        --近90天购买金额（不含退拒）
      t1.month1_order_cnt,            --近30天购买次数（含退拒）
      t1.month1_order_amt,            --近30天购买金额（含退拒）
      t1.month2_order_cnt,            --近60天购买次数（含退拒）
      t1.month2_order_amt,            --近60天购买金额（含退拒）
      t1.month3_order_cnt,            --近90天购买次数（含退拒）
      t1.month3_order_amt,            --近90天购买金额（含退拒）
      t1.max_order_amt,               --最大消费金额
      t1.min_order_amt,               --最小消费金额
      t1.total_order_cnt,             --累计消费次数（不含退拒）
      t1.total_order_amt,             --累计消费金额（不含退拒）
      t1.user_avg_amt,                --客单价（含退拒）
      (
        CASE
          WHEN t1.month3_order_cnt <> 0  // <> 0 表示 不等于0
          THEN t1.month3_order_amt / t1.month3_order_cnt // 近90天购买金额 除以 近90天购买次数 = 近90天的客单价(含退拒)
          ELSE 0 
        END
      ) month3_user_avg_amt,          --近90天的客单价(含退拒)
      t3.common_address,              --常用收货地址
      t3.common_paytype,              --常用支付方式
      t2.month1_cart_cnt,             --近30天购物车的次数
      t2.month1_cart_goods_cnt,       --近30天购物车商品件数
      t2.month1_cart_submit_cnt,      --近30天购物车提交商品件数
      t2.month1_cart_rate,        --近30天购物车成功率
      t2.month1_cart_cancle_cnt,    --近30天购物车放弃件数
      t1.return_cnt,                     --退货商品数量
      t1.return_amt,                     --退货商品金额
      t1.reject_cnt,                     --拒收商品数量
      t1.reject_amt,                     --拒收商品金额
      t1.last_return_time,               --最近一次退货时间
      t1.school_order_cnt,               --学校下单总数
      t1.company_order_cnt,              --单位下单总数
      t1.home_order_cnt,                 --家里下单总数
      t1.forenoon_order_cnt,             --上午下单总数
      t1.afternoon_order_cnt,            --下午下单总数
      t1.night_order_cnt,                --晚上下单总数
      t1.morning_order_cnt,              --凌晨下单总数
      FROM_UNIXTIME(UNIX_TIMESTAMP())  dw_date // 作为操作时间：返回 Unix时间戳(Unix timestamp)，表示将 Unix 时间戳字符串进行格式化
    FROM                             
      gdm.gdm_user_consume_order_temp_100 t 
      LEFT JOIN gdm.gdm_user_consume_order_temp_01 t1 //统计 订单 相关指标的 临时表01
      ON (t.user_id = t1.user_id) 
      LEFT JOIN gdm.gdm_user_consume_order_temp_02 t2 //统计 购物车 相关指标的 临时表02
      ON (t.user_id = t2.user_id) 
      LEFT JOIN 
        (SELECT user_id,
                MAX(CASE
                  WHEN type = 'address' 
                  THEN con 
               END) common_address,
                MAX(CASE
                  WHEN type = 'pay_type' 
                  THEN con 
               END) common_paytype 
          FROM gdm.gdm_user_consume_order_temp_03 //统计 常用收货地址和常用支付方式 的临时表03
          group by user_id) t3 
      ON (t.user_id = t3.user_id);
        
 ================= 客户喜好消费的商品分类模型表 ================= 
订单中 商品类目表 GDM层（临时表）
        SQL目的：统计用户订单中所消费的商品分类情况，即可得知用户偏向于消费何种分类的商品
        没有时间分区，但是每次都会先 drop 临时表，再 CREATE 临时表 as select ...
        
        订单中 商品类目表 GDM层 gdm.gdm_user_buy_category_temp（临时表）
        订单中 商品类目表 GDM层 = 订单 主要信息表 FDM层（时间分区） + 订单中商品信息表 FDM层（时间分区）

-------------------------------

购物车中 商品类目表 GDM层（临时表）
        SQL目的：统计用户购物车中成功支付消费的商品分类情况，即可得知用户偏向于消费何种分类的商品
        没有时间分区，但是每次都会先 drop 临时表，再 CREATE 临时表 as select ...

        购物车中 商品类目表 GDM层 gdm.gdm_user_cart_category_temp（临时表）
        购物车中 商品类目表 GDM层 = 购物车中商品信息表 FDM层（时间分区） + 订单中商品信息表 FDM层（时间分区）

-------------------------------

商品类目码表 GDM层（1级/2级/3级分类详细）
        商品类目码表 GDM层 gdm.gdm_category_code

-------------------------------

客户消费商品的 每级分类的 类目详细表（订单+购物车） GDM层（临时表）（客户喜好消费的商品分类）
        SQL目的：根据三级分类ID得出二级分类和一级分类的ID和名称
        没有时间分区，但是每次都会先 drop 临时表，再 CREATE 临时表 as select ...
        
        客户消费商品的 每级分类的 类目详细表 GDM层 gdm.gdm_user_category_total（临时表）
        客户消费商品的 每级分类的 类目详细表 GDM层 = 订单中 商品类目表 GDM层（临时表）+ 购物车中 商品类目表 GDM层（临时表）+ 商品类目码表 GDM层
-------------------------------        

客户消费商品的 每级分类的 类目总表（订单+购物车） GDM层（客户喜好消费的商品分类）
        SQL目的：汇总 用户订单中所消费的商品分类情况 和 用户购物车中成功支付消费的商品分类情况，最终得知用于总体偏向于消费何种分类的商品。
        当前该表中的“近30/60/90天的XX”等指标 仅为时间分区中每天的指标数据，因此还必须根据 where dt >= date_add(昨天日期时间, -29/-60/-90) 条件进行统计， 
        这样才能统计出真正的“近30/60/90天的XX”指标数据。
        客户消费商品的 每级分类的 类目总表 GDM层 gdm.gdm_user_buy_category（时间分区）
        客户消费商品的 每级分类的 类目总表 GDM层 = 客户消费商品的 每级分类的 类目详细表 GDM层（临时表） + 订单中 商品类目表 GDM层（临时表） + 购物车中 商品类目表 GDM层（临时表）

================= 订单中 商品类目表 ================= 
订单中 商品类目表 GDM层（临时表）
        SQL目的：统计用户订单中所消费的商品分类情况，即可得知用户偏向于消费何种分类的商品
        没有时间分区，但是每次都会先 drop 临时表，再 CREATE 临时表 as select ...
        
        订单中 商品类目表 GDM层 gdm.gdm_user_buy_category_temp（临时表）
        订单中 商品类目表 GDM层 = 订单 主要信息表 FDM层（时间分区） + 订单中商品信息表 FDM层（时间分区）

-------------------------------

订单中客户购买情况 临时表 GDM 层（对 FDM 层数据 进行统计一些指标数据）
    
    --计算订单中客户购买情况
    drop table if exists gdm.gdm_user_buy_category_temp;

    SQL目的：统计用户订单中所消费的商品分类情况，即可得知用户偏向于消费何种分类的商品
    CREATE TABLE gdm.gdm_user_buy_category_temp AS 
    SELECT 
      a.user_id,  
      '' first_category_id,  //一级类目ID （一开始初始化为空字符串，可根据三级类目ID/名称可分别获取到一级和二级类目ID/名称）
      '' first_category_name,//一级类目名称
      '' second_category_id,//二级类目ID
      '' second_catery_name,//二级类目名称
      b.third_cart  third_category_id,//三级类目ID
      b.third_cat_name  third_category_name,//三级类目名称
      SUM(
        CASE
          WHEN a.dat_30 = 1  //dat_30 = 1表示该订单是在30天之内
          THEN b.goods_amount //满足上述条件之后，返回订单中所有商品的数量，最终SUM操作后便得到了近30天购物类目次数
        END
      ) month1_category_cnt,         --近30天购物类目次数
      SUM(
        CASE
          WHEN a.dat_30 = 1 //dat_30 = 1表示该订单是在30天之内
          //curr_price表示每个商品的单价，不存在则返回0；goods_amount获取到当前商品的数量；
          //然后curr_price*goods_amount得到该商品的购买总金额，最终SUM操作后便得到了近30天购物类目金额
          THEN COALESCE(b.curr_price,0) * COALESCE(b.goods_amount,0) //coalesce(字段, '') 如果字段列的值为null空的话，则替换为 0
        END
      ) month1_category_amt,         --近30天购物类目金额
      SUM(
        CASE
          WHEN a.dat_90 = 1 
          THEN b.goods_amount 
        END
      ) month3_category_cnt,        --近90天购物类目次数
      SUM(
        CASE
          WHEN a.dat_90 = 1 
          THEN COALESCE(b.curr_price, 0) * COALESCE(b.goods_amount, 0)
        END
      ) month3_category_amt,        --近90天购物类目金额
      SUM(
        CASE
          WHEN a.dat_180 = 1 
          THEN b.goods_amount 
        END
      ) month6_category_cnt,        --近180天购物类目次数
      SUM(
        CASE
          WHEN a.dat_180 = 1 
          THEN COALESCE(b.curr_price, 0) * COALESCE(b.goods_amount, 0)
        END
      ) month6_category_amt,                --近180天购物类目金额
      //下面的累计购物类目次数和累计购物类目金额都不做时间判断，直接累计所购买商品类目的数量和累计所购买商品的总金额
      SUM(b.goods_amount) total_category_cnt,        --累计购物类目次数
      SUM(COALESCE(b.curr_price, 0) * COALESCE(b.goods_amount, 0)) total_category_amt,    --累计购物类目金额
      MAX(a.order_date) last_category_time,                        --最后一次购买类目时间
      DATEDIFF(MAX(a.order_date), '2017-01-01') last_category_ago,                --最后一次购买类目距今天数 //前面的日期参数减去后面的日期参数
      FROM_UNIXTIME(UNIX_TIMESTAMP()) dw_date    // 作为操作时间：返回 Unix时间戳(Unix timestamp)，表示将 Unix 时间戳字符串进行格式化
    FROM
      (SELECT a.*,
        (CASE   //如果order_date订单时间 大于等于“2017-01-01减去29天”并且小于等于“2017-01-01”的话，那么表示该订单便是（dat_30）近30天标识，那么便返回1
            WHEN order_date >= DATE_SUB('2017-01-01', 29) 
            AND order_date <= '2017-01-01' 
            THEN
            1
          END
        ) dat_30,         --近30天标识
        (CASE //如果order_date订单时间 大于等于“2017-01-01减去89天”并且小于等于“2017-01-01”的话，那么表示该订单便是（dat_90）近90天标识，那么便返回1
            WHEN order_date >= DATE_SUB('2017-01-01', 89) 
            AND order_date <= '2017-01-01' 
            THEN 
            1
          END
        ) dat_90,        --近90天标识
        (CASE  //如果order_date订单时间 大于等于“2017-01-01减去179天”并且小于等于“2017-01-01”的话，那么表示该订单便是（dat_180）近180天标识，那么便返回1
            WHEN order_date >= DATE_SUB('2017-01-01', 179) 
            AND order_date <= '2017-01-01' 
            THEN 
            1
          END
        ) dat_180          --近180天标识
       FROM fdm.fdm_order a  //订单宽表
       WHERE dt = '2017-01-01'
            ) a 
    JOIN (SELECT * FROM fdm.fdm_order_goods WHERE dt = '2017-01-01') b //订单商品表
    ON (a.user_id = b.user_id) 
    GROUP BY a.user_id, b.third_cart, b.third_cat_name; //以用户ID、三级分类ID、三级分类名称一起作为分组条件


========================== 购物车中 商品类目表 ====================
购物车中 商品类目表 GDM层（临时表）
        SQL目的：统计用户购物车中成功支付消费的商品分类情况，即可得知用户偏向于消费何种分类的商品
        没有时间分区，但是每次都会先 drop 临时表，再 CREATE 临时表 as select ...

        购物车中 商品类目表 GDM层 gdm.gdm_user_cart_category_temp（临时表）
        购物车中 商品类目表 GDM层 = 购物车中商品信息表 FDM层（时间分区） + 订单中商品信息表 FDM层（时间分区）

-------------------------------

购物车中类目表 临时表 GDM 层（对 FDM 层数据 进行统计一些指标数据）

    1.SQL目的：统计用户购物车中成功支付消费的商品分类情况，即可得知用户偏向于消费何种分类的商品

    2.版本一：（上课时使用的版本）只统计近180天之内加入购物车中的商品，并且要求该购物车中的商品最终是成功支付的
        create table 表名 as select ... from (select * from 购物车中商品信息表 where dt=昨天 and 前第30天 <= add_time <= 昨天) 
                              left join (select * from 订单中商品信息表 where dt=昨天 group by 商品ID,三级类目ID) 
                              on 商品ID group by 用户ID,三级分类ID

    3.版本二：（推荐实际使用的版本）统计“只要加入购物车中后并且成功支付的”商品
        create table 表名 as select ... from (select * from 购物车中商品信息表 where dt=昨天) 
                              left join (select * from 订单中商品信息表 where dt=昨天 group by 商品ID,三级类目ID) 
                              on 商品ID group by 用户ID,三级分类ID

    --购物车中类目情况
    drop table if exists gdm.gdm_user_cart_category_temp;
 
    create table gdm.gdm_user_cart_category_temp as 
    select 
      a.user_id, //用户ID 
      b.third_cart, //三级类目ID
      sum(
        case //如果加入购物车时间add_time 大于等于“2017-01-01减去29天”并且小于等于“2017-01-01”的话，那么表示该订单便是近30天标识，那么便返回1，否则返回0
          when to_date (add_time) >= date_sub('2017-01-01', 29) 
          and to_date (add_time) <= '2017-01-01' 
          then 1  //满足条件返回1
          else 0  //不满足条件返回0
        end
      ) month1_cart_category_cnt, --近30天的购物车类目次数
      sum(
        case //如果加入购物车时间add_time 大于等于“2017-01-01减去89天”并且小于等于“2017-01-01”的话，那么表示该订单便是近90天标识，那么便返回1，否则返回0
          when to_date (add_time) >= date_sub('2017-01-01', 89) 
          and to_date (add_time) <= '2017-01-01' 
          then 1 
          else 0
        end
      ) month3_cart_category_cnt,  --近90天的购物车类目次数
      sum(
        case //如果加入购物车时间add_time 大于等于“2017-01-01减去179天”并且小于等于“2017-01-01”的话，那么表示该订单便是近180天标识，那么便返回1，否则返回0
          when to_date (add_time) >= date_sub('2017-01-01', 179) 
          and to_date (add_time) <= '2017-01-01' 
          then 1 
          else 0
        end
      ) month6_cart_category_cnt, --近180天的购物车类目次数
      count(1) total_category_cnt  --累计所有购物车类目次数
    from
      (select * from
        fdm.fdm_order_cart   //购物车订单表
       where dt = '2017-01-01'   //add_time 表示加入购物车时间；to_date表示转换为日期时间然后用于时间比用途
        and to_date (add_time) >= date_sub('2017-01-01', 179)  //表示当前时间2017-01-01减去197天，然后比较加入购物车时间add_time大于“2017-01-01减去197天” 
        and to_date (add_time) <= '2017-01-01'//表示购物车时间add_time小于当前时间2017-01-01，从而计算出加入加入购物车时间是否在当前时间的180天之内
       ) a 
     left join 
        (select goods_id, third_cart 
         from fdm.fdm_order_goods  //订单商品表
         where dt = '2017-01-01' 
         group by goods_id, third_cart //以商品ID 和 三级类目ID 一起来分组
         ) b 
      on (a.goods_id = b.goods_id)  //以商品ID作为连接条件
      group by user_id, b.third_cart ;  //以用户ID 和 三级类目ID 一起来分组
 

====================== 商品类目码表 =====================
商品类目码表 GDM层（1级/2级/3级分类详细）
        商品类目码表 GDM层 gdm.gdm_category_code

-------------------------------

商品类目码表 
    --------商品类目码表（外部表）--------
    create database if not exists gdm;
    create external table if not exists gdm.gdm_category_code(
    third_category_id bigint,    -- 三级分类ID
    third_category_name string,-- 三级分类名称
    second_category_id bigint,    -- 二级分类ID
    second_catery_name string,    -- 二级分类名称
    first_category_id bigint,    -- 一级分类ID
    first_category_name string -- 一级分类名称
    )row format delimited fields terminated by ','
    lines terminated by '\n'
    location  '/business/gdm_category_code';

    //把数据添加到商品类目码表（外部表）中
    load data local inpath '/root/source_data/gdm_category_code.txt' overwrite into table gdm.gdm_category_code;

 

=============== 客户消费商品的 每级分类的 类目详细表 ==============
客户消费商品的 每级分类的 类目详细表（订单+购物车） GDM层（临时表）（客户喜好消费的商品分类）
        SQL目的：根据三级分类ID得出二级分类和一级分类的ID和名称
        没有时间分区，但是每次都会先 drop 临时表，再 CREATE 临时表 as select ...
        
        客户消费商品的 每级分类的 类目详细表 GDM层 gdm.gdm_user_category_total（临时表）
        客户消费商品的 每级分类的 类目详细表 GDM层 = 订单中 商品类目表 GDM层（临时表）+ 购物车中 商品类目表 GDM层（临时表）+ 商品类目码表 GDM层

-------------------------------

用户所购买商品的所有类目总表 GDM层（临时表）：购物车中类目表 临时表 + 订单中客户购买情况 临时表 + 类目码表
    drop table if exists gdm.gdm_user_category_total;

    SQL目的：根据三级分类ID得出二级分类和一级分类的ID和名称
    create table gdm.gdm_user_category_total as 
    select 
      a.user_id,
      b.first_category_id,//一级类目ID
      b.first_category_name,//一级类目名称
      b.second_category_id,//二级类目ID
      b.second_catery_name,//二级类目名称
      a.third_category_id,//三级类目ID
      b.third_category_name //三级类目名称
    from
      (select user_id, third_category_id  //三级类目ID
       from gdm.gdm_user_buy_category_temp //订单中客户购买情况 临时表
       union all //合并
       select user_id, third_cart //三级类目ID
       from gdm.gdm_user_cart_category_temp //购物车中类目表 临时表
       ) a 
     left join gdm.gdm_category_code b //类目码表：可根据三级类目ID得到对应的一级、二级的类目ID/类目名称
     on ( a.third_category_id = b.third_category_id ) //以三级类目ID作为连接条件
     //以用户ID、一级的类目ID和类目名称、二级的类目ID和类目名称、三级的类目ID和类目名称 一起作为分组条件
     group by a.user_id, b.first_category_id, b.first_category_name, b.second_category_id, b.second_catery_name, a.third_category_id, b.third_category_name ;
      

====================== 客户喜好消费的商品分类模型表 =====================
客户消费商品的 每级分类的 类目总表（订单+购物车） GDM层（客户喜好消费的商品分类）
        SQL目的：汇总 用户订单中所消费的商品分类情况 和 用户购物车中成功支付消费的商品分类情况，最终得知用于总体偏向于消费何种分类的商品。
        当前该表中的“近30/60/90天的XX”等指标 仅为时间分区中每天的指标数据，因此还必须根据 where dt >= date_add(昨天日期时间, -29/-60/-90) 条件进行统计， 
        这样才能统计出真正的“近30/60/90天的XX”指标数据。
        客户消费商品的 每级分类的 类目总表 GDM层 gdm.gdm_user_buy_category（时间分区）
        客户消费商品的 每级分类的 类目总表 GDM层 = 客户消费商品的 每级分类的 类目详细表 GDM层（临时表） + 订单中 商品类目表 GDM层（临时表） + 购物车中 商品类目表 GDM层（临时表）

-------------------------------

客户购买类目表：根据用户所购买的商品分析用户都购买了哪些类目
     
    create database if not exists gdm;

    SQL目的：汇总 用户订单中所消费的商品分类情况 和 用户购物车中成功支付消费的商品分类情况，最终得知用于总体偏向于消费何种分类的商品
    当前该表中的“近30/60/90天的XX”等指标 仅为时间分区中每天的指标数据，因此还必须根据 where dt >= date_add(昨天日期时间, -29/-60/-90) 条件进行统计， 
    这样才能统计出真正的“近30/60/90天的XX”指标数据。 
    CREATE TABLE if not exists gdm.gdm_user_buy_category (
      user_id STRING,                 --客户ID
      first_category_id BIGINT,       --一级分类ID
      first_category_name STRING,     --一级分类名称
      second_category_id BIGINT,      --二分类ID
      second_catery_name STRING,      --二级分类名称
      third_category_id BIGINT,       --三级分类ID
      third_category_name STRING,     --三级分类名称
      month1_category_cnt BIGINT,     --近30天购物类目次数
      month1_category_amt STRING,     --近30天购物类目金额
      month3_category_cnt BIGINT,     --近90天购物类目次数
      month3_category_amt STRING,     --近90天购物类目金额
      month6_category_cnt BIGINT,     --近180天购物类目次数
      month6_category_amt STRING,     --近180天购物类目金额
      total_category_cnt BIGINT,      --累计购物类目次数
      total_category_amt STRING,      --累计购物类目金额
      month1_cart_category_cnt BIGINT,--近30天购物车类目次数
      month3_cart_category_cnt BIGINT,--近90天购物车类目次数
      month6_cart_category_cnt BIGINT,--近180天购物车类目次数
      total_cart_category_cnt BIGINT, --累计购物车类目次数
      last_category_time TIMESTAMP,   --最后一次购买类目时间
      last_category_ago BIGINT,       --最后一次购买类目距今天数
      dw_date TIMESTAMP
    ) partitioned BY (dt STRING) ;


    //加载数据
    INSERT overwrite TABLE gdm.gdm_user_buy_category PARTITION (dt = '2017-01-01') 
    SELECT 
      t.user_id,
      t.first_category_id,
      t.first_category_name,
      t.second_category_id,
      t.second_catery_name,
      t.third_category_id,
      t.third_category_name,
      t1.month1_category_cnt,
      t1.month1_category_amt,
      t1.month3_category_cnt,
      t1.month3_category_amt,
      t1.month6_category_cnt,
      t1.month6_category_amt,
      t1.total_category_cnt,
      t1.total_category_amt,
      t2.month1_cart_category_cnt,
      t2.month3_cart_category_cnt,
      t2.month6_cart_category_cnt,
      t2.total_category_cnt,
      t1.last_category_time,
      t1.last_category_ago,
      FROM_UNIXTIME(UNIX_TIMESTAMP()) dw_date // 作为操作时间：返回 Unix时间戳(Unix timestamp)，表示将 Unix 时间戳字符串进行格式化
    FROM gdm.gdm_user_category_total t  //购物车中类目表 临时表 和 订单中客户购买情况 临时表 所合并的整合表
    LEFT JOIN gdm.gdm_user_buy_category_temp t1  //订单中客户购买情况 临时表
    ON (t.user_id = t1.user_id AND t.third_category_id = t1.third_category_id) //三级类目ID作为连接条件
    LEFT JOIN gdm.gdm_user_cart_category_temp t2  //购物车中类目表 临时表
    ON (t.user_id = t2.user_id AND t.third_category_id = t2.third_cart) ;//三级类目ID作为连接条件
 
===============  用户访问模型表 ===============
用户上网轨迹表 
        SQL目的：以同一个用户ID作为同一个分组的情况下，同时可根据session_id和cookie_id的不同，计算出该同一用户当天的访问次数和每次访问的停留时长
        1.用户上网轨迹表 BDM层（PC端/网页端）
                用户上网轨迹表 BDM层 bdm.bdm_user_pc_click_log（时间分区）
                用户上网轨迹表 FDM层 fdm.fdm_user_pc_pageview（时间分区）
                
        2.用户上网轨迹表 BDM层（APP端）
                用户上网轨迹表 BDM层 bdm.bdm_user_app_click_log（时间分区）
                用户上网轨迹表 FDM层 fdm.fdm_user_app_pageview（时间分区）

--------------------------------------------

近30天 PC端/网页端 访问最常用的指标（临时表）
        SQL目的：在同一个用户ID分组下，可对应多个不同 ip/cookie/浏览器名/系统名    的分组的情况下，统计出近30天之内的使用次数情况
        统计近30天之内指标的方式：where dt >= date_add(昨天日期时间, -29)
        没有时间分区，但是每次都会先 drop 临时表，再 CREATE 临时表 as select ...

        近30天 PC端 访问最常用的指标 GDM层 gdm.gdm_user_visit_temp_01（临时表）
        近30天 PC端 访问最常用的指标 GDM层 = 用户上网轨迹表 FDM层（时间分区）
--------------------------------------------        

用户访问模型表 GDM层
        用户访问模型表 GDM层 gdm.gdm_user_visit（时间分区）
        用户访问模型表 GDM层 = 客户基本属性表 FDM层（时间分区）
                     + 用户上网轨迹表（PC端/网页端） FDM层（无需时间分区，因为进行了时间日期比较）
                     + 近30天 PC端 访问最常用的指标 GDM层（临时表）
                     + 用户上网轨迹表（APP端） FDM层（无需时间分区，因为进行了时间日期比较）

====================== 用户上网轨迹表 =====================
用户上网轨迹表 
        SQL目的：以同一个用户ID作为同一个分组的情况下，同时可根据session_id和cookie_id的不同，计算出该同一用户当天的访问次数和每次访问的停留时长
        1.用户上网轨迹表 BDM层（PC端/网页端）
                用户上网轨迹表 BDM层 bdm.bdm_user_pc_click_log（时间分区）
                用户上网轨迹表 FDM层 fdm.fdm_user_pc_pageview（时间分区）
                
        2.用户上网轨迹表 BDM层（APP端）
                用户上网轨迹表 BDM层 bdm.bdm_user_app_click_log（时间分区）
                用户上网轨迹表 FDM层 fdm.fdm_user_app_pageview（时间分区）

--------------------------------------------

用户上网轨迹表BDM层-- PC端/网页端 BDM 层（源数据层）
    create database if not exists bdm;

    create external table if not exists bdm.bdm_user_pc_click_log(
      session_id STRING,  --sessionID
      cookie_id STRING,   --cookieID
      visit_time STRING,  --请求访问的 日期时间点
      user_id STRING,     --请求访问的 用户ID
      visit_url STRING,   --请求访问的 URL
      visit_os STRING,    --请求访问的 操作系统
      browser_name STRING,--请求访问的 浏览器名称
      visit_ip STRING,    --请求访问的 ip
      province STRING,    --请求访问的 省份
      city STRING,        --请求访问的 城市
      page_id STRING,     --请求访问的 页面ID
      goods_id STRING,    --请求访问的 商品ID
      shop_id STRING      --请求访问的 商店ID
    ) partitioned by (dt string)
    row format delimited fields terminated by ','
    lines terminated by '\n'
    location '/business/bdm_user_pc_click_log';

    //添加分区
    alter table bdm.bdm_user_pc_click_log add partition (dt='2017-01-01') location '/business/bdm_user_pc_click_log/dt=2017-01-01';

    //把BDM层源数据文件导入到 BDM层 外部分区表中
    hdfs dfs -put /root/source_data/bdm_user_pc_click_log.txt /business/bdm_user_pc_click_log/dt=2017-01-01

--------------------------------------------

用户上网轨迹表FDM层-- PC端/网页端 FDM 层（对 BDM 源数据层的数据进行清洗和预处理）
    1.Cookie通过在客户端记录信息确定用户身份，Session通过在服务器端记录信息确定用户身份。
      Cookie保存在客户端浏览器中，而Session保存在服务器上。
    2.Cookie的内容主要包括：名字，值，过期时间，路径和域。路径与域一起构成cookie的作用范围。 
        1）Name 和 Value 属性由程序设定,默认值都是空引用。 
        2）Domain属性的默认值为当前URL的域名部分，不管发出这个cookie的页面在哪个目录下的。 
        3）Path属性的默认值是根目录，即 ”/” ，不管发出这个cookie的页面在哪个目录下的。可以由程序设置为一定的路径来进一步限制此cookie的作用范围。 
        4）Expires 属性，这个属性设置此Cookie 的过期日期和时间。
    3.SessionId保存在Cookie中，用户提交了表单时，浏览器会将用户的SessionId自动附加在HTTP头信息中。
    4.Session的生命周期：
        1.超时销毁：客户端不与服务器进行交互后，Session等到自动销毁的时间间隔便会自动销毁
        2.关闭浏览器/重新打开浏览器（Session销毁）：之前Cookie中存放的Sessionid就自动销毁了
        3.关闭浏览器/重新打开浏览器（Session定时销毁）：
            可以在登录的时候，手动把JSESSIONID保存到COOKIE中，并令其存在的生命周期更长（可以和session的生命周期保持一致），
            这样即使浏览器关闭，cookie也不会消失，JSESSIONID还在，重新打开浏览器后，服务器仍可再找到该session

    create database if not exists fdm;
    create  table if not exists fdm.fdm_user_pc_pageview(
      session_id STRING,  --sessionID
      cookie_id STRING,   --cookieID
      user_id STRING,     --用户ID
      in_time STRING,     --访问进入时间
      out_time STRING,    --访问离开时间
      stay_time STRING,   --访问停留时间（访问离开时间 减 访问进入时间）
      pv BIGINT,          --PV（访问网站页面的次数）
      visit_os STRING,    --操作系统
      browser_name STRING,--游览器名称
      visit_ip STRING,    --访问ip
      province STRING,    --省份
      city STRING         --城市
    ) partitioned BY (dt STRING);

  SQL目的：
    GROUP BY user_id + session_id + cookie_id 的作用：
        以同一个用户ID作为同一个分组的情况下，同时可根据session_id和cookie_id的不同，计算出该同一用户当天的访问次数和每次访问的停留时长
    GROUP BY session_id 的作用：
        当打开浏览器时自动创建Session，当关闭浏览器时自动销毁(超时销毁)Session，那么即可计算出每次用户在网站中停留的时长。
        当为新Session时，即作为新的网站中停留时长。同一个Session的创建时间作为访问进入时间，同一个Session的自动销毁(超时销毁)的时间作为访问离开时间。
    ----加载数据---
    INSERT overwrite TABLE fdm.fdm_user_pc_pageview PARTITION (dt = '2017-01-01') 
    SELECT 
      t.session_id,
      t.cookie_id,
      t.user_id,
      //同一个Session的创建时间作为访问进入时间，同一个Session的自动销毁(超时销毁)的时间作为访问离开时间
      MIN(visit_time) in_time, //把最小的请求访问的日期时间点作为访问进入时间
      MAX(visit_time) out_time,//把最大的请求访问的日期时间点作为访问离开时间
      (
        case
          WHEN MIN(visit_time) = MAX(visit_time) //如果访问进入时间等于访问离开时间的话，则设置默认5秒的访问停留时间，比如用户进入网站页面之后不点击任何页面则无法统计用户的访问停留时间
          then 5 
          else unix_timestamp(MAX(visit_time)) - unix_timestamp(MIN(visit_time)) //访问离开时间 减 访问进入时间 得出 访问停留时间
        end
      ) stay_time,
      COUNT(1) pv, //计算出该同一用户当天的访问次数和每次访问的停留时长，获取对应user_id/cookie_id/visit_ip的总PV数
      t.visit_os,
      t.browser_name,
      t.visit_ip,
      t.province,
      t.city 
    FROM
      bdm.bdm_user_pc_click_log t 
    WHERE dt = '2017-01-01' 
    GROUP BY t.session_id, 
      t.cookie_id,
      t.user_id,
      t.visit_os,
      t.browser_name,
      t.visit_ip,
      t.province,
      t.city ;

--------------------------------------------

用户上网轨迹表BDM层--APP端  BDM 层（源数据层）
    create database if not exists bdm;
    create external table if not exists bdm.bdm_user_app_click_log(
      user_id string,        --用户ID
      log_time string,       --访问时间
      phone_id string,       --手机ID，唯一标识一台设备
      visit_os string,       --操作系统 android、ios、wp
      os_version string,     --操作系统的版本
      app_name string,       --APP的名称
      app_version string,    --APP的版本
      device_token string,   --PUSH码，消息推送的
      visit_ip string,       --访问ip
      province string,       --省份
      city string            --城市
    ) partitioned by (dt string)
    row format delimited fields terminated by ','
    lines terminated by '\n'
    location '/business/bdm_user_app_click_log';

    //添加分区
    alter table bdm.bdm_user_app_click_log add partition (dt='2017-01-01') location '/business/bdm_user_app_click_log/dt=2017-01-01';

    //把BDM层源数据文件导入到 BDM层 外部分区表中
    hdfs dfs -put /root/source_data/bdm_user_app_click_log.txt /business/bdm_user_app_click_log/dt=2017-01-01

    device_token string PUSH码，消息推送用 
        Push指运营人员通过自己的产品或第三方工具对用户移动设备进行的主动消息推送。
        用户可以在移动设备锁定屏幕和通知栏看到push消息通知，通知栏点击可唤起APP并去往相应页面。
        我们平时在锁屏上看到的微信消息等等都属于APP消息推送行列。

--------------------------------------------

用户上网轨迹表FDM层--app端 FDM 层（对 BDM 源数据层的数据进行清洗和预处理）
    create database if not exists fdm;
    create  table if not exists fdm.fdm_user_app_pageview(
      user_id string,        --用户ID
      log_time string,       --访问时间 2016-12-12 10:20:30
      log_hour string,       --访问具体时间的小时数字：比如早上十点访问，那么小时数字为10，用于后期统计用户什么时间段来访问的
      phone_id string,       --手机ID，唯一标识一台设备
      visit_os string,       --操作系统 android、ios、wp
      os_version string,     --操作系统版本
      app_name string,       --APP的名称
      app_version string,    --APP的版本
      device_token string,   --PUSH码，消息推送的
      visit_ip string,       --访问ip
      province string,       --省份
      city string            --城市
    ) partitioned BY (dt STRING);


    ------加载数据-----
    INSERT overwrite TABLE fdm.fdm_user_app_pageview PARTITION (dt = '2017-01-01') 
    SELECT 
      t.user_id,
      t.log_time,
      HOUR(t.log_time) log_hour,  //通过 HOUR(具体日期时间) 获取具体日期时间的小时数字
      t.phone_id,
      t.visit_os,
      t.os_version,
      t.app_name,
      t.app_version,
      t.device_token,
      t.visit_ip,
      t.province,
      t.city 
    FROM
      bdm.bdm_user_app_click_log t 
    WHERE dt = '2017-01-01' ;


================ 近30天 PC端/网页端 访问最常用的指标 ===================
近30天 PC端/网页端 访问最常用的指标（临时表）
        SQL目的：在同一个用户ID分组下，可对应多个不同 ip/cookie/浏览器名/系统名    的分组的情况下，统计出近30天之内的使用次数情况
        统计近30天之内指标的方式：where dt >= date_add(昨天日期时间, -29)
        没有时间分区，但是每次都会先 drop 临时表，再 CREATE 临时表 as select ...

        近30天 PC端 访问最常用的指标 GDM层 gdm.gdm_user_visit_temp_01（临时表）
        近30天 PC端 访问最常用的指标 GDM层 = 用户上网轨迹表 FDM层（时间分区）
--------------------------------------------        

近30天 PC端/网页端 访问最常用的指标（临时表） BDM 层（源数据层）

    SQL目的：    
        row_number() over(distribute by 用户ID, ip/cookie/浏览器名/系统名 sort by 使用次数 desc) rn 
        在同一个用户ID分组下，可对应多个不同 ip/cookie/浏览器名/系统名    的分组的情况下，统计出近30天之内的使用次数情况
        统计近30天之内指标的方式：where dt >= date_add(昨天日期时间, -29)
    drop table if exists gdm.gdm_user_visit_temp_01;

    create table gdm.gdm_user_visit_temp_01 as 
    select 
      t.user_id,
      t.type, // type 为字符串："visit_ip"、"cookie_id"、"browser_name"浏览器名、"visit_os"系统名
      t.con, // con 分别有 IP地址、cookieID、浏览器名、系统名 的具体内容值
      t.cnt, // cnt 根据type的不同 分别是 IP地址、cookie、浏览器名、系统名 的使用次数
      t.rn 
    from
      (select 
        b.user_id,
        b.con,
        b.type,
        b.cnt,
        row_number() over(distribute by b.user_id, b.type sort by b.cnt desc) rn 
        //排序函数：row_number() over(distribute by(分桶字段名) sort by(排序字段名 ASC/DESC))
        from
        (select 
           a.user_id, a.visit_ip con, 'visit_ip' type, --近30天常用的IP地址
           count(1) cnt //近30天常用的IP地址的总访问记录数
         from
           fdm.fdm_user_pc_pageview a 
         where dt >= date_add('2017-01-01', -29) //当前时间2017-01-01 减去 29天 统计近30天的数据：用于最终统计每个IP所使用的次数
         group by a.user_id, a.visit_ip 
         union all 
         select 
            a.user_id, a.cookie_id con, 'cookie_id' type, --近30天常用的cookie
            count(1) cnt //近30天常用的cookie的总访问记录数
         from
            fdm.fdm_user_pc_pageview a 
         where dt >= date_add('2017-01-01', -29)  //当前时间2017-01-01 减去 29天 统计近30天的数据：用于最终统计每个cookie所使用的次数
         group by a.user_id, a.cookie_id 
         union all
         select 
            a.user_id, a.browser_name con, 'browser_name' type, --近30天常用的浏览器
            count(1) cnt //近30天常用的浏览器的总访问记录数
         from
            fdm.fdm_user_pc_pageview a 
         where dt >= date_add('2017-01-01', -29)  //当前时间2017-01-01 减去 29天 统计近30天的数据：用于最终统计每个浏览器所使用的次数
         group by a.user_id, a.browser_name 
         union all
         select 
            a.user_id, a.visit_os con, 'visit_os' type,  --近30天常用的系统
            count(1) cnt 
         from
            fdm.fdm_user_pc_pageview a 
         where dt >= date_add('2017-01-01', -29) //当前时间2017-01-01 减去 29天 统计近30天的数据：用于最终统计每个系统所使用的次数
         group by  a.user_id, a.visit_os
               )b
           )t ;





=================== 用户访问模型表 ====================  
用户访问模型表 GDM层
        用户访问模型表 GDM层 gdm.gdm_user_visit（时间分区）
        用户访问模型表 GDM层 = 客户基本属性表 FDM层（时间分区）
                     + 用户上网轨迹表（PC端/网页端） FDM层（无需时间分区，因为进行了时间日期比较）
                     + 近30天 PC端 访问最常用的指标 GDM层（临时表）
                     + 用户上网轨迹表（APP端） FDM层（无需时间分区，因为进行了时间日期比较）

--------------------------------------------      

生成客户访问模型表
    create database if not exists gdm;
    create  table if not exists gdm.gdm_user_visit(
    user_id string,              
    latest_pc_visit_date string,            
    latest_app_visit_date string,           
    latest_pc_visit_session string,         
    latest_pc_cookies string,               
    latest_pc_pv string,                    
    latest_pc_browser_name string,          
    latest_pc_visit_os string,              
    latest_app_name string,                 
    latest_app_visit_os string,             
    latest_visit_ip string,                 
    latest_city string,                     
    latest_province string,                 
    first_pc_visit_date string,             
    first_app_visit_date string,            
    first_pc_visit_session string,          
    first_pc_cookies string,                
    first_pc_pv string,                     
    first_pc_browser_name string,           
    first_pc_visit_os string,               
    first_app_name string,                  
    first_app_visit_os string,              
    first_visit_ip string,                  
    first_city string,                      
    first_province string,                  
    day7_app_cnt bigint,                    
    day15_app_cnt bigint,                   
    month1_app_cnt bigint,                  
    month2_app_cnt bigint,                  
    month3_app_cnt bigint,                  
    day7_pc_cnt bigint,                     
    day15_pc_cnt bigint,                    
    month1_pc_cnt bigint,                   
    month2_pc_cnt bigint,                   
    month3_pc_cnt bigint,                   
    month1_pc_days bigint,                  
    month1_pc_pv bigint,                    
    month1_pc_avg_pv bigint,                
    month1_pc_diff_ip_cnt bigint,           
    month1_pc_diff_cookie_cnt bigint,       
    month1_pc_common_ip string,             
    month1_pc_common_cookie string,         
    month1_pc_common_browser_name string,   
    month1_pc_common_os string,             
    month1_hour025_cnt bigint,              
    month1_hour627_cnt bigint,              
    month1_hour829_cnt bigint,              
    month1_hour10212_cnt bigint,            
    month1_hour13214_cnt bigint,            
    month1_hour15217_cnt bigint,            
    month1_hour18219_cnt bigint,            
    month1_hour20221_cnt bigint,            
    month1_hour22223_cnt bigint,            
    dw_date timestamp
    ) partitioned by (dt string);
 
    SQL中：
         1.MAX()：此处使用MAX()没有任何业务意义，因为使用了GROUP BY，因此为了语法不报错，所以才使用了MAX()等聚合函数来补全语法
         2.FROM 客户基本属性表 WHERE dt=昨天：后面LEFT JOIN的表都包含近30天/近90天的数据，目的是：仅计算昨天新增用户和访问用户的访问情况
         3.LEFT JOIN 用户上网轨迹表（PC端/网页端）
        1.第一版本：没有使用“where dt >= 前第90天”，而是使用“前第90天 <= in_time <= 昨天”，同样可以统计 近90天之内的表数据
           2.第二版本（推荐）：使用“where dt >= 前第90天”，即 where dt >= date_add('2017-01-01', -29)，可以达到更加快速地搜索查询表数据
         4.LEFT JOIN 用户上网轨迹表（APP端）
        1.第一版本：没有使用“where dt >= 前第90天”，而是使用“前第90天 <= log_time <= 昨天”，同样可以统计 近90天之内的表数据
           2.第二版本（推荐）：使用“where dt >= 前第90天”，即 where dt >= date_add('2017-01-01', -29)，可以达到更加快速地搜索查询表数据

    INSERT overwrite TABLE gdm.gdm_user_visit PARTITION (dt = '2017-01-01') 
    SELECT 
      t.user_id,--客户ID
      MAX(  //此处使用MAX()没有任何业务意义，因为使用了GROUP BY，因此为了语法不报错，所以才使用了MAX()等聚合函数来补全语法
        CASE
          WHEN pc.rn_desc = 1 //当取 pc端的 where rn_desc = 1 时表示取出每个分桶中的第一条数据，代表近90天之内最近一次PC端访问日期
          THEN pc.in_time //返回 近90天之内 最近一次PC端访问日期
        END
      ) latest_pc_visit_date,--近90天之内 最近一次PC端访问日期
      MAX(
        CASE
          WHEN app.rn_desc = 1 //当取 app端的 where rn_desc = 1 时表示取出每个分桶中的第一条数据，代表近90天之内最近一次APP端访问日期
          THEN app.log_time //返回 近90天之内 最近一次APP端访问日期
        END
      ) latest_app_visit_date,--近90天之内 最近一次APP端访问日期
      MAX(
        CASE
          WHEN pc.rn_desc = 1 
          THEN pc.session_id 
        END
      ) latest_pc_visit_session,--近90天之内 最近一次PC端访问的session
      MAX(
        CASE
          WHEN pc.rn_desc = 1 
          THEN pc.cookie_id 
        END
      ) latest_pc_cookies,--近90天之内 最近一次PC端访问的cookies
      MAX(
        CASE
          WHEN pc.rn_desc = 1 
          THEN pc.pv 
        END) latest_pc_pv,--近90天之内 最近一次PC端访问的PV
      MAX(
        CASE
          WHEN pc.rn_desc = 1 
          THEN pc.browser_name 
        END
      ) latest_pc_browser_name,--近90天之内 最近一次PC端访问使用的游览器
      MAX(
        CASE
          WHEN pc.rn_desc = 1 
          THEN pc.visit_os 
        END
      ) latest_pc_visit_os,--近90天之内 最近一次PC端访问使用的操作系统
      MAX(
        CASE
          WHEN app.rn_desc = 1 
          THEN app.app_name 
        END
      ) latest_app_name,--近90天之内 最近一次APP端访问app名称
      MAX(
        CASE
          WHEN app.rn_desc = 1 
          THEN app.visit_os 
        END
      ) latest_app_visit_os,--近90天之内 最近一次APP端访问使用的操作系统
      MAX(
        CASE
          WHEN pc.rn_desc = 1 
          AND app.rn_desc = 1 
          AND pc.in_time >= app.log_time  
          THEN pc.visit_ip
          WHEN pc.rn_desc = 1 
          AND app.rn_desc = 1 
          AND pc.in_time < app.log_time 
          THEN app.visit_ip  
        END
      ) latest_visit_ip,--近90天之内 最近一次访问IP(不分APP与PC)
      MAX(
        CASE
          WHEN pc.rn_desc = 1 //当取 pc端的 where rn_desc = 1 时表示取出每个分桶中的第一条数据，代表离当前最近一次的消费时间
          AND app.rn_desc = 1 //当取 app端的 where rn_desc = 1 时表示取出每个分桶中的第一条数据，代表离当前最近一次的消费时间
          AND pc.in_time >= app.log_time //如果 pc端的 最近的消费时间 大于等于 app端的 最近的消费时间，那么返回 最近一次的pc端消费时间
          THEN pc.city
          WHEN pc.rn_desc = 1 
          AND app.rn_desc = 1 
          AND pc.in_time < app.log_time 
          THEN app.city 
        END
      ) latest_city,--近90天之内 最近一次访问城市(不分APP与PC)
      MAX(
        CASE
          WHEN pc.rn_desc = 1 
          AND app.rn_desc = 1 
          AND pc.in_time >= app.log_time 
          THEN pc.province 
          WHEN pc.rn_desc = 1 
          AND app.rn_desc = 1 
          AND pc.in_time < app.log_time 
          THEN app.province 
        END
      ) latest_province,--近90天之内 最近一次访问省份(不分APP与PC)
      MAX(
        CASE
          WHEN pc.rn_asc = 1 //当取 pc端的 where rn_asc= 1 时表示取出每个分桶中的第一条数据，代表离当前最远的第一次消费时间
          THEN pc.in_time 
        END
      ) first_pc_visit_date,--近90天之内 第一次PC端访问日期
      MAX(
        CASE
          WHEN app.rn_asc = 1 
          THEN app.log_time 
        END
      ) first_app_visit_date,--近90天之内 第一次APP端访问日期
      MAX(
        CASE
          WHEN pc.rn_asc = 1 
          THEN pc.session_id 
        END
      ) first_pc_visit_session,--近90天之内 第一次PC端访问的session
      MAX(
        CASE
          WHEN pc.rn_asc = 1 
          THEN pc.cookie_id 
        END
      ) first_pc_cookies,--近90天之内 第一次PC端访问的cookies
      MAX(
        CASE
          WHEN pc.rn_asc = 1 
          THEN pc.pv 
        END) first_pc_pv,--近90天之内 第一次PC端访问的PV
      MAX(
        CASE
          WHEN pc.rn_asc = 1 
          THEN pc.browser_name 
        END
      ) first_pc_browser_name,--近90天之内 第一次PC端访问使用的游览器
      MAX(
        CASE
          WHEN pc.rn_asc = 1 
          THEN pc.visit_os 
        END
      ) first_pc_visit_os,--近90天之内 第一次PC端访问使用的操作系统
      MAX(
        CASE
          WHEN app.rn_asc = 1 
          THEN app.app_name 
        END
      ) first_app_name,--近90天之内 第一次APP端访问app名称
      MAX(
        CASE
          WHEN app.rn_asc = 1 
          THEN app.visit_os 
        END
      ) first_app_visit_os,--近90天之内 第一次APP端访问使用的操作系统
      MAX(
        CASE
          WHEN pc.rn_asc = 1 //当取 pc端的 where rn_asc= 1 时表示取出每个分桶中的第一条数据，代表离当前最远的第一次消费时间
          AND app.rn_asc = 1 //当取 app端的 where rn_asc= 1 时表示取出每个分桶中的第一条数据，代表离当前最远的第一次消费时间
          AND pc.in_time <= app.log_time //如果 pc端的 最近的消费时间 小于 app端的 最近的消费时间，那么返回 最远的第一次pc端的消费时间
          THEN pc.visit_ip 
          WHEN pc.rn_asc = 1 
          AND app.rn_asc = 1 
          AND pc.in_time > app.log_time 
          THEN app.visit_ip 
        END
      ) first_visit_ip,--近90天之内 第一次访问IP(不分APP与PC)
      MAX(
        CASE
          WHEN pc.rn_asc = 1 
          AND app.rn_asc = 1 
          AND pc.in_time <= app.log_time 
          THEN pc.city 
          WHEN pc.rn_asc = 1 
          AND app.rn_asc = 1 
          AND pc.in_time > app.log_time 
          THEN app.city 
        END
      ) first_city,--近90天之内 第一次访问城市(不分APP与PC)
      MAX(
        CASE
          WHEN pc.rn_asc = 1 
          AND app.rn_asc = 1 
          AND pc.in_time <= app.log_time 
          THEN pc.province 
          WHEN pc.rn_asc = 1 
          AND app.rn_asc = 1 
          AND pc.in_time > app.log_time 
          THEN app.province 
        END
      ) first_province,--近90天之内 第一次访问省份(不分APP与PC)
      SUM(
        CASE
          WHEN app.dat_7 = 1 
          THEN 1 //对每次返回的1都进行sum操作，便可以统计出 近7天APP端访问次数
        END) day7_app_cnt,--近7天APP端访问次数
      SUM(
        CASE
          WHEN app.dat_15 = 1 
          THEN 1 
        END) day15_app_cnt,--近15天APP端访问次数
      SUM(
        CASE
          WHEN app.dat_30 = 1 
          THEN 1 
        END) month1_app_cnt,--近30天APP端访问次数
      SUM(
        CASE
          WHEN app.dat_60 = 1 
          THEN 1 
        END) month2_app_cnt,--近60天APP端访问次数
      SUM(
        CASE
          WHEN app.dat_90 = 1 
          THEN 1 
        END) month3_app_cnt,--近90天APP端访问次数
      COUNT(
        CASE
          WHEN pc.dat_7 = 1 
          THEN pc.session_id 
        END
      ) day7_pc_cnt,--近7天PC端访问次数
      COUNT(
        CASE
          WHEN pc.dat_15 = 1 
          THEN pc.session_id 
        END
      ) day15_pc_cnt,--近15天PC端访问次数
      COUNT(
        CASE
          WHEN pc.dat_30 = 1 
          THEN pc.session_id 
        END
      ) month1_pc_cnt,--近30天PC端访问次数
      COUNT(
        CASE
          WHEN pc.dat_60 = 1 
          THEN pc.session_id 
        END
      ) month2_pc_cnt,--近60天PC端访问次数
      COUNT(
        CASE
          WHEN pc.dat_90 = 1 
          THEN pc.session_id 
        END
      ) month3_pc_cnt,--近90天PC端访问次数
      //substr(pc.in_time,0,10) 截取出 xxxx-xx-xx 的时间日期，然后count对去重后的 时间日期进行统计 访问天数
      COUNT(DISTINCT substr(pc.in_time,0,10)) month1_pc_days,--近30天PC端访问天数
      SUM(
        CASE
          WHEN pc.dat_30 = 1 
          THEN pc.pv 
        END) month1_pc_pv,--近30天PC端访问PV
      SUM(
        CASE
          WHEN pc.dat_30 = 1 
          THEN pc.pv   //近30天PC端访问PV总数除以近30天PC端访问天数得出近30天PC端访问每天平均访问PV
        END) / COUNT(DISTINCT substr(pc.in_time,0,10)) month1_pc_avg_pv,--近30天PC端访问平均PV
      MAX(b.month1_pc_diff_ip_cnt),--近30天PC端访问不同ip数
      MAX(b.month1_pc_diff_cookie_cnt),--近30天PC端访问不同的cookie数
      MAX(b.month1_pc_common_ip),--近30天PC端访问最常用ip
      MAX(b.month1_pc_common_cookie),--近30天PC端访问最常用的cookie
      MAX(b.month1_pc_common_browser_name),--近30天PC端访问最常用游览器
      MAX(b.month1_pc_common_os),--近30天PC端访问最常用的操作系统
      COUNT(
        CASE
          WHEN pc.visit_hour >= 0 //0点
          AND pc.visit_hour <= 5 //5点
          THEN pc.session_id 
        END
      ) month1_hour025_cnt,--近30天PC端0-5点访问次数
      COUNT(
        CASE
          WHEN pc.visit_hour >= 6 
          AND pc.visit_hour <= 7 
          THEN pc.session_id 
        END
      ) month1_hour627_cnt,--近30天PC端6-7点访问次数
      COUNT(
        CASE
          WHEN pc.visit_hour >= 8 
          AND pc.visit_hour <= 9 
          THEN pc.session_id 
        END
      ) month1_hour829_cnt,--近30天PC端8-9点访问次数
      COUNT(
        CASE
          WHEN pc.visit_hour >= 10 
          AND pc.visit_hour <= 12 
          THEN pc.session_id 
        END
      ) month1_hour10212_cnt,--近30天PC端10-12点访问次数
      COUNT(
        CASE
          WHEN pc.visit_hour >= 13 
          AND pc.visit_hour <= 14 
          THEN pc.session_id 
        END
      ) month1_hour13214_cnt,--近30天PC端13-14点访问次数
      COUNT(
        CASE
          WHEN pc.visit_hour >= 15 
          AND pc.visit_hour <= 17 
          THEN pc.session_id 
        END
      ) month1_hour15217_cnt,--近30天PC端15-17点访问次数
      COUNT(
        CASE
          WHEN pc.visit_hour >= 18 
          AND pc.visit_hour <= 19 
          THEN pc.session_id 
        END
      ) month1_hour18219_cnt,--近30天PC端18-19点访问次数
      COUNT(
        CASE
          WHEN pc.visit_hour >= 20 
          AND pc.visit_hour <= 21 
          THEN pc.session_id 
        END
      ) month1_hour20221_cnt,--近30天PC端20-21点访问次数
      COUNT(
        CASE
          WHEN pc.visit_hour >= 22 
          AND pc.visit_hour <= 23 
          THEN pc.session_id 
        END
      ) month1_hour22223_cnt,--近30天PC端22-23点访问次数
      FROM_UNIXTIME(UNIX_TIMESTAMP()) dw_date 
    FROM
      (SELECT user_id 
       FROM fdm.fdm_user_wide 
       WHERE dt = '2017-01-01' //此处使用“WHERE dt=昨天”，而后面LEFT JOIN的表都包含近30天/近90天的数据，目的是：仅计算昨天新增用户和访问用户的访问情况
            ) t 
    LEFT JOIN 
        (SELECT a.*,
          (
            CASE
              WHEN in_time >= DATE_SUB('2017-01-01', 6) //判断用户访问时间in_time大于等于“当前时间2017-01-01减去6天的”结果值并且小于等于当前时间2017-01-01，那么表示是在近7天之内访问的，便返回1，最终用于统计近7天PC端访问次数
              AND in_time <= '2017-01-01' 
              THEN 1 
            END
          ) dat_7,    --近7天pc端访问次数
          (
            CASE
              WHEN in_time >= DATE_SUB('2017-01-01', 14) //判断用户访问时间in_time大于等于“当前时间2017-01-01减去14天的”结果值并且小于等于当前时间2017-01-01，那么表示是在近15天之内访问的，便返回1，最终用于统计近15天PC端访问次数
              AND in_time <= '2017-01-01' 
              THEN 1 
            END
          ) dat_15,    --近15天pc端访问次数
          (
            CASE
              WHEN in_time >= DATE_SUB('2017-01-01', 29) //判断用户访问时间in_time大于等于“当前时间2017-01-01减去29天的”结果值并且小于等于当前时间2017-01-01，那么表示是在近30天之内访问的，便返回1，最终用于统计近30天PC端访问次数
              AND in_time <= '2017-01-01' 
              THEN 1 
            END
          ) dat_30,    --近30天pc端访问次数
          (
            CASE
              WHEN in_time >= DATE_SUB('2017-01-01', 59) 
              AND in_time <= '2017-01-01' 
              THEN 1 
            END
          ) dat_60,    --近60天pc端访问次数
          (
            CASE
              WHEN in_time >= DATE_SUB('2017-01-01', 89) 
              AND in_time <= '2017-01-01' 
              THEN 1 
            END
          ) dat_90,    --近90天pc端访问次数
          hour(in_time) visit_hour, //通过 HOUR(具体日期时间) 获取具体日期时间的小时数字，最终用于统计某个时间段的访问次数
          //排序函数：row_number() over(distribute by(分桶字段名) sort by(排序字段名 ASC/DESC))
          row_number() over(distribute BY a.user_id sort BY a.in_time DESC) rn_desc, //获取rn_desc列值的序号ID为1的话，表示获取用户近90天之内最近访问时间
          row_number() over(distribute BY a.user_id sort BY a.in_time ASC) rn_asc  //获取rn_desc列值的序号ID为1的话，表示获取用户近90天之内第一次访问时间
            FROM fdm.fdm_user_pc_pageview a  //pc端的用户访问记录
            // 前第90天 <= in_time <= 昨天：当前用户访问记录时间 大于等于 “当前时间2017-01-01 减去 89天的”结果值，并且小于等于当前时间2017-01-01 
            // 第一版本：没有使用“where dt >= 前第90天”，而是使用“前第90天 <= in_time <= 昨天”，同样可以统计 近90天之内的表数据
            // 第二版本（推荐）：使用“where dt >= 前第90天”，即 where dt >= date_add('2017-01-01', -29)，可以达到更加快速地搜索查询表数据
            // WHERE in_time >= DATE_ADD('2017-01-01', -89) AND in_time <= '2017-01-01' 
        WHERE dt >= date_add('2017-01-01', -29)
          ) pc ON (t.user_id = pc.user_id) 
    LEFT JOIN 
           (SELECT user_id,
          sum(
            CASE
              WHEN TYPE = 'visit_ip' 
              THEN cnt //返回一个月中 不同IP地址对应的总记录数
            END
          ) month1_pc_diff_ip_cnt,  //上述返回的 数据值作为 指标名称为 近30天PC端访问的不同IP数量
          MAX(     //此处使用MAX()没有任何业务意义，因为使用了GROUP BY，因此为了语法不报错，所以才使用了MAX()等聚合函数来补全语法
            CASE
              WHEN TYPE = 'visit_ip' and rn= 1 //获取排序分组中rn编号值为1的数据 表示获取最常用的IP地址。gdm_user_visit_temp_01临时表中对分组中数据使用的是desc降序，因此rn=1获取的是最大值。
              THEN con //返回一个月中 使用次数最多的 IP地址字段的值
            END
          ) month1_pc_common_ip,   //上述返回的 数据值 作为 指标名称为 近30天PC端访问的最常用的IP 
          sum(
            CASE
              WHEN TYPE = 'cookie_id'  //返回一个月中 不同cookie对应的总记录数
              THEN cnt //返回一个月中 不同cookie对应的总记录数
            END
          ) month1_pc_diff_cookie_cnt,//上述返回的 数据值作为 指标名称为 近30天PC端访问的不同cookie的数量
          MAX(
            CASE
              WHEN TYPE = 'cookie_id' and rn = 1//获取排序分组中rn编号值为1的数据 表示获取最常用的cookie。gdm_user_visit_temp_01临时表中对分组中数据使用的是desc降序，因此rn=1获取的是最大值。
              THEN con //返回一个月中 使用次数最多的 CookieID字段的值
            END
          ) month1_pc_common_cookie, //上述返回的 数据值作为 指标名称为 近30天PC端访问的最常用的cookie
          MAX(
            CASE
              WHEN TYPE = 'browser_name' and rn = 1//获取排序分组中rn编号值为1的数据 表示获取最常用的浏览器。gdm_user_visit_temp_01临时表中对分组中数据使用的是desc降序，因此rn=1获取的是最大值。
              THEN con //返回一个月中 使用次数最多的 浏览器名字
            END
          ) month1_pc_common_browser_name,//上述返回的 数据值作为 指标名称为 近30天PC端访问的最常用的浏览器
          MAX(
            CASE
              WHEN TYPE = 'visit_os' and rn = 1//获取排序分组中rn编号值为1的数据 表示获取最常用的操作系统。gdm_user_visit_temp_01临时表中对分组中数据使用的是desc降序，因此rn=1获取的是最大值。
              THEN con //返回一个月中 使用次数最多的 操作系统名字
            END
          ) month1_pc_common_os //上述返回的 数据值作为 指标名称为 近30天PC端访问的最常用的操作系统
        FROM gdm.gdm_user_visit_temp_01 
        GROUP BY user_id
            ) b ON (t.user_id = b.user_id) 
    LEFT JOIN 
            (SELECT a.*,
          (
            CASE
              WHEN log_time >= DATE_SUB('2017-01-01', 6) 
              AND log_time <= '2017-01-01' 
              THEN 1 
            END
          ) dat_7,    --近7天app端访问次数
          (
            CASE
              WHEN log_time >= DATE_SUB('2017-01-01', 14) 
              AND log_time <= '2017-01-01' 
              THEN 1 
            END
          ) dat_15,    --近15天app端访问次数
          (
            CASE
              WHEN log_time >= DATE_SUB('2017-01-01', 29) 
              AND log_time <= '2017-01-01' 
              THEN 1 
            END
          ) dat_30,    --近30天app端访问次数
          (
            CASE
              WHEN log_time >= DATE_SUB('2017-01-01', 59) 
              AND log_time <= '2017-01-01' 
              THEN 1 
            END
          ) dat_60,    --近60天app端访问次数
          (
            CASE
              WHEN log_time >= DATE_SUB('2017-01-01', 89) 
              AND log_time <= '2017-01-01' 
              THEN 1 
            END
          ) dat_90,    --近90天app端访问次数
          //排序函数：row_number() over(distribute by(分桶字段名) sort by(排序字段名 ASC/DESC))
          row_number() over (distribute BY a.user_id sort BY a.log_time DESC) rn_desc, //当取 where rn_desc = 1 时表示取出每个分桶中的第一条数据，代表近90天之内最近一次的访问时间
          row_number() over (distribute BY a.user_id sort BY a.log_time ASC) rn_asc  //当取 where rn_asc= 1 时表示取出每个分桶中的第一条数据，代表近90天之内第一次访问时间
            FROM fdm.fdm_user_app_pageview a  //app端的客户访问信息表
            // 前第90天 <= log_time <= 昨天：当前用户访问记录时间 大于等于 “当前时间2017-01-01 减去 89天的”结果值，并且小于等于当前时间2017-01-01 
            // 第一版本：没有使用“where dt >= 前第90天”，而是使用“前第90天 <= log_time <= 昨天”，同样可以统计 近90天之内的表数据
            // 第二版本（推荐）：使用“where dt >= 前第90天”，即 where dt >= date_add('2017-01-01', -29)，可以达到更加快速地搜索查询表数据
            // WHERE log_time >= DATE_ADD('2017-01-01', -89) AND log_time <= '2017-01-01' 
        WHERE dt >= date_add('2017-01-01', -29)
         ) app ON (t.user_id = app.user_id) GROUP BY t.user_id ;



================ 用户画像表 结构图================ 
用户画像表 ADM层
    每天汇总出的用户画像表仅包含当天的数据，因此还需要和之前汇总好的用户画像表进行新的合并操作后，才算真正的最新数据的用户画像表
        用户画像宽表 ADM层 adm.adm_personas（时间分区）
        用户画像宽表 ADM层 = 用户基本属性表 GDM层 gdm.gdm_user_basic（时间分区）（第 1 张表）
                   + 客户消费模型表 GDM层 gdm.gdm_user_consume_order（时间分区）（第 7 张表 = 2 + 3 + 4 + 5 + 6）
                   + 客户喜好消费的商品分类模型表 GDM层 gdm.gdm_user_buy_category（时间分区）（第 12 张表 = 8 + 9 + 10 + 11）
                   + 用户访问模型表 GDM层 gdm.gdm_user_visit（时间分区）（第 15 张表 = 13 + 14）

---------------------------------------------

用户标签表
    可根据 客户消费模型表 + 客户喜好消费的商品分类模型表 + 用户访问模型表 分析出 该人的购买喜好、购买习惯、购买实力，从而推荐相似的商品给该用户
 

================ 用户画像表 ================ 
用户画像 宽表：根据 用户基本属性表、客户消费订单表、客户购买类目表、用户访问信息表 生成 用户画像宽表
    create database if not exists adm; //ADM 层（对 GDM 层指标数据 进行指标数据的业务汇总分析）：高度聚合
    create table if not exists adm.adm_personas(
    user_id string            ,--用户ID
    user_name string            ,--用户登陆名
    user_sex  string            ,--用户性别
    user_birthday string        ,--用户生日
    user_age  bigint            ,--用户年龄
    constellation string        ,--用户星座
    province string            ,--省份
    city string            ,--城市
    city_level string            ,--城市等级
    hex_mail string            ,--邮箱
    op_mail string            ,--邮箱运营商
    hex_phone string            ,--手机号
    fore_phone string            ,--手机前3位
    op_phone string            ,--手机运营商
    add_time timestamp        ,--注册时间
    login_ip string            ,--登陆ip地址
    login_source string        ,--登陆来源
    request_user string        ,--邀请人
    total_mark bigint            ,--会员积分
    used_mark bigint            ,--已使用积分
    level_name string            ,--会员等级名称
    blacklist bigint            ,--用户黑名单
    is_married bigint            ,--婚姻状况
    education string            ,--学历
    monthly_money double        ,--收入
    profession string            ,--职业
    sex_model bigint            ,--性别模型
    is_pregnant_woman bigint        ,--是否孕妇
    is_have_children bigint        ,--是否有小孩
    children_sex_rate double        ,--孩子性别概率
    children_age_rate double        ,--孩子年龄概率
    is_have_car bigint        ,--是否有车
    potential_car_user_rate double    ,--潜在汽车用户概率
    phone_brand string        ,--使用手机品牌
    phone_brand_level string        ,--使用手机品牌档次
    phone_cnt bigint            ,--使用多少种不同的手机
    change_phone_rate bigint        ,--更换手机频率
    majia_flag string            ,--马甲标志
    majie_account_cnt bigint        ,--马甲账号数量
    loyal_model bigint        ,--用户忠诚度
    shopping_type_model bigint        ,--用户购物类型
    figure_model bigint        ,--身材
    stature_model bigint        ,--身高
    first_order_time timestamp,    --第一次消费时间
    last_order_time timestamp,        --最近一次消费时间
    first_order_ago bigint,        --首单距今时间
    last_order_ago bigint,        --尾单距今时间
    month1_hg_order_cnt bigint,        --近30天购买次数（不含退拒）
    month1_hg_order_amt double,        --近30天购买金额（不含退拒）
    month2_hg_order_cnt bigint,        --近60天购买次数（不含退拒）
    month2_hg_order_amt double,        --近60天购买金额（不含退拒）
    month3_hg_order_cnt bigint,        --近90天购买次数（不含退拒）
    month3_hg_order_amt double,        --近90天购买金额（不含退拒）
    month1_order_cnt bigint,            --近30天购买次数（含退拒）
    month1_order_amt double,            --近30天购买金额（含退拒）
    month2_order_cnt bigint,            --近60天购买次数（含退拒）
    month2_order_amt double,            --近60天购买金额（含退拒）
    month3_order_cnt bigint,            --近90天购买次数（含退拒）
    month3_order_amt double,            --近90天购买金额（含退拒）
    max_order_amt double,            --最大消费金额
    min_order_amt double,            --最小消费金额
    total_order_cnt bigint,            --累计消费次数（不含退拒）
    total_order_amt double,            --累计消费金额（不含退拒）
    user_avg_amt double,            --客单价（含退拒）（每一个顾客平均购买商品的金额，客单价也即是平均交易金额）
    month3_user_avg_amt double,        --近90天的客单价
    common_address string,            --常用收货地址
    common_paytype string,            --常用支付方式
    month1_cart_cnt bigint,            --近30天购物车的次数
    month1_cart_goods_cnt bigint,        --近30天购物车商品件数
    month1_cart_submit_cnt bigint,        --近30天购物车提交商品件数
    month1_cart_rate double,            --近30天购物车成功率
    month1_cart_cancle_cnt double,        --近30天购物车放弃件数
    return_cnt bigint,            --退货商品数量
    return_amt double,            --退货商品金额
    reject_cnt bigint,            --拒收商品数量
    reject_amt double,            --拒收商品金额
    last_return_time timestamp,        --最近一次退货时间
    school_order_cnt bigint,            --学校下单总数
    company_order_cnt bigint,            --单位下单总数
    home_order_cnt bigint,            --家里下单总数
    forenoon_order_cnt bigint,            --上午下单总数
    afternoon_order_cnt bigint,        --下午下单总数
    night_order_cnt bigint,            --晚上下单总数
    morning_order_cnt bigint,            --凌晨下单总数
    first_category_id BIGINT,       --一级分类ID
    first_category_name STRING,     --一级分类名称
    second_category_id BIGINT,      --二分类ID
    second_catery_name STRING,      --二级分类名称
    third_category_id BIGINT,       --三级分类ID
    third_category_name STRING,     --三级分类名称
    month1_category_cnt BIGINT,     --近30天购物类目次数
    month1_category_amt STRING,     --近30天购物类目金额
    month3_category_cnt BIGINT,     --近90天购物类目次数
    month3_category_amt STRING,     --近90天购物类目金额
    month6_category_cnt BIGINT,     --近180天购物类目次数
    month6_category_amt STRING,     --近180天购物类目金额
    total_category_cnt BIGINT,      --累计购物类目次数
    total_category_amt STRING,      --累计购物类目金额
    month1_cart_category_cnt BIGINT,--近30天购物车类目次数
    month3_cart_category_cnt BIGINT,--近90天购物车类目次数
    month6_cart_category_cnt BIGINT,--近180天购物车类目次数
    total_cart_category_cnt BIGINT, --累计购物车类目次数
    last_category_time TIMESTAMP,           --近90天之内最后一次购买类目时间
    last_category_ago BIGINT,               --近90天之内最后一次购买类目距今天数
    latest_pc_visit_date string,            --近90天之内最近一次PC端访问日期
    latest_app_visit_date string,           --近90天之内最近一次APP端访问日期
    latest_pc_visit_session string,         --近90天之内最近一次PC端访问的session
    latest_pc_cookies string,               --近90天之内最近一次PC端访问的cookies
    latest_pc_pv string,                    --近90天之内最近一次PC端访问的PV
    latest_pc_browser_name string,          --近90天之内最近一次PC端访问使用的游览器
    latest_pc_visit_os string,              --近90天之内最近一次PC端访问使用的操作系统
    latest_app_name string,                 --近90天之内最近一次APP端访问app名称
    latest_app_visit_os string,             --近90天之内最近一次APP端访问使用的操作系统
    latest_visit_ip string,                 --近90天之内最近一次访问IP(不分APP与PC)
    latest_city string,                     --近90天之内最近一次访问城市(不分APP与PC)
    latest_province string,                 --近90天之内最近一次访问省份(不分APP与PC)
    first_pc_visit_date string,             --近90天之内第一次PC端访问日期
    first_app_visit_date string,            --近90天之内第一次APP端访问日期
    first_pc_visit_session string,          --近90天之内第一次PC端访问的session
    first_pc_cookies string,                --近90天之内第一次PC端访问的cookies
    first_pc_pv string,                     --近90天之内第一次PC端访问的PV
    first_pc_browser_name string,           --近90天之内第一次PC端访问使用的游览器
    first_pc_visit_os string,               --近90天之内第一次PC端访问使用的操作系统
    first_app_name string,                  --近90天之内第一次APP端访问app名称
    first_app_visit_os string,              --近90天之内第一次APP端访问使用的操作系统
    first_visit_ip string,                  --近90天之内第一次访问IP(不分APP与PC)
    first_city string,                      --近90天之内第一次访问城市(不分APP与PC)
    first_province string,                  --近90天之内第一次访问省份(不分APP与PC)
    day7_app_cnt bigint,                    --近7天APP端访问次数
    day15_app_cnt bigint,                   --近15天APP端访问次数
    month1_app_cnt bigint,                  --近30天APP端访问次数
    month2_app_cnt bigint,                  --近60天APP端访问次数
    month3_app_cnt bigint,                  --近90天APP端访问次数    
    day7_pc_cnt bigint,                     --近7天PC端访问次数
    day15_pc_cnt bigint,                    --近15天PC端访问次数
    month1_pc_cnt bigint,                   --近30天PC端访问次数
    month2_pc_cnt bigint,                   --近60天PC端访问次数
    month3_pc_cnt bigint,                   --近90天PC端访问次数
    month1_pc_days bigint,                  --近30天PC端访问天数
    month1_pc_pv bigint,                    --近30天PC端访问PV
    month1_pc_avg_pv bigint,                --近30天PC端访问平均PV
    month1_pc_diff_ip_cnt bigint,           --近30天PC端访问不同ip数
    month1_pc_diff_cookie_cnt bigint,       --近30天PC端访问不同的cookie数
    month1_pc_common_ip string,             --近30天PC端访问最常用ip
    month1_pc_common_cookie string,         --近30天PC端访问最常用的cookie
    month1_pc_common_browser_name string,   --近30天PC端访问最常用游览器
    month1_pc_common_os string,             --近30天PC端访问最常用的操作系统
    month1_hour025_cnt bigint,              --近30天PC端0-5点访问次数
    month1_hour627_cnt bigint,              --近30天PC端6-7点访问次数
    month1_hour829_cnt bigint,              --近30天PC端8-9点访问次数
    month1_hour10212_cnt bigint,            --近30天PC端10-12点访问次数
    month1_hour13214_cnt bigint,            --近30天PC端13-14点访问次数
    month1_hour15217_cnt bigint,            --近30天PC端15-17点访问次数
    month1_hour18219_cnt bigint,            --近30天PC端18-19点访问次数
    month1_hour20221_cnt bigint,            --近30天PC端20-21点访问次数
    month1_hour22223_cnt bigint             --近30天PC端22-23点访问次数
    );  //第二版本：partitioned by (dt string);

 

    -----加载数据：根据 用户基本属性表、客户消费订单表、客户购买类目表、用户访问信息表 生成 用户画像宽表
    insert overwrite table adm.adm_personas //第二版本：insert overwrite table adm.adm_personas partition(dt=${partdt})
    select  
    a.user_id,
    a.user_name,
    a.user_sex,
    a.user_birthday,
    a.user_age,
    a.constellation,
    a.province,
    a.city,
    a.city_level,
    a.hex_mail,
    a.op_mail,
    a.hex_phone,
    a.fore_phone,
    a.op_phone,
    a.add_time,
    a.login_ip,
    a.login_source,
    a.request_user,
    a.total_mark,
    a.used_mark,
    a.level_name,
    a.blacklist,
    a.is_married,
    a.education,
    a.monthly_money,
    a.profession, 
    a.sex_model,
    a.is_pregnant_woman,
    a.is_have_children,
    a.children_sex_rate,
    a.children_age_rate,
    a.is_have_car,
    a.potential_car_user_rate,
    a.phone_brand,
    a.phone_brand_level,
    a.phone_cnt,
    a.change_phone_rate,
    a.majia_flag,
    a.majie_account_cnt,
    a.loyal_model,
    a.shopping_type_model,
    a.figure_model,
    a.stature_model,
    b.first_order_time,     
    b.last_order_time,     
    b.first_order_ago,     
    b.last_order_ago,     
    b.month1_hg_order_cnt,
    b.month1_hg_order_amt,
    b.month2_hg_order_cnt,
    b.month2_hg_order_amt,
    b.month3_hg_order_cnt,
    b.month3_hg_order_amt,
    b.month1_order_cnt,   
    b.month1_order_amt,   
    b.month2_order_cnt,   
    b.month2_order_amt,   
    b.month3_order_cnt,   
    b.month3_order_amt,   
    b.max_order_amt,      
    b.min_order_amt,      
    b.total_order_cnt,    
    b.total_order_amt,    
    b.user_avg_amt,       
    b.month3_user_avg_amt,    
    b.common_address,         
    b.common_paytype,         
    b.month1_cart_cnt,        
    b.month1_cart_goods_cnt,  
    b.month1_cart_submit_cnt, 
    b.month1_cart_rate, 
    b.month1_cart_cancle_cnt,
    b.return_cnt,             
    b.return_amt,             
    b.reject_cnt,             
    b.reject_amt,             
    b.last_return_time,       
    b.school_order_cnt,       
    b.company_order_cnt,      
    b.home_order_cnt,         
    b.forenoon_order_cnt,     
    b.afternoon_order_cnt,    
    b.night_order_cnt,        
    b.morning_order_cnt,      
    c.first_category_id,
    c.first_category_name,
    c.second_category_id,
    c.second_catery_name,
    c.third_category_id,
    c.third_category_name,
    c.month1_category_cnt,
    c.month1_category_amt,
    c.month3_category_cnt,
    c.month3_category_amt,
    c.month6_category_cnt,
    c.month6_category_amt,
    c.total_category_cnt,
    c.total_category_amt,
    c.month1_category_cnt,
    c.month3_category_cnt,
    c.month6_category_cnt,
    c.total_category_cnt,
    c.last_category_time,
    c.last_category_ago,
    d.latest_pc_visit_date,         
    d.latest_app_visit_date,        
    d.latest_pc_visit_session,      
    d.latest_pc_cookies,            
    d.latest_pc_pv,                 
    d.latest_pc_browser_name,       
    d.latest_pc_visit_os,           
    d.latest_app_name,              
    d.latest_app_visit_os,          
    d.latest_visit_ip,              
    d.latest_city,                  
    d.latest_province,              
    d.first_pc_visit_date,          
    d.first_app_visit_date,         
    d.first_pc_visit_session,       
    d.first_pc_cookies,             
    d.first_pc_pv,                  
    d.first_pc_browser_name,        
    d.first_pc_visit_os,            
    d.first_app_name,               
    d.first_app_visit_os,           
    d.first_visit_ip,               
    d.first_city,                   
    d.first_province,               
    d.day7_app_cnt,                 
    d.day15_app_cnt,                
    d.month1_app_cnt,               
    d.month2_app_cnt,               
    d.month3_app_cnt,               
    d.day7_pc_cnt,                  
    d.day15_pc_cnt,                 
    d.month1_pc_cnt,                
    d.month2_pc_cnt,                
    d.month3_pc_cnt,                
    d.month1_pc_days,               
    d.month1_pc_pv,                 
    d.month1_pc_avg_pv,             
    d.month1_pc_diff_ip_cnt,        
    d.month1_pc_diff_cookie_cnt,    
    d.month1_pc_common_ip,          
    d.month1_pc_common_cookie,      
    d.month1_pc_common_browser_name,
    d.month1_pc_common_os,          
    d.month1_hour025_cnt,           
    d.month1_hour627_cnt,           
    d.month1_hour829_cnt,           
    d.month1_hour10212_cnt,         
    d.month1_hour13214_cnt,         
    d.month1_hour15217_cnt,         
    d.month1_hour18219_cnt,         
    d.month1_hour20221_cnt,         
    d.month1_hour22223_cnt          
    from gdm.gdm_user_basic a //用户基本属性表
    left join gdm.gdm_user_consume_order b on a.user_id=b.user_id //客户消费订单表
    left join gdm.gdm_user_buy_category c on a.user_id=c.user_id //客户购买类目表
    left join gdm.gdm_user_visit d on a.user_id=d.user_id; //用户访问信息表
第二版本：
     from gdm.gdm_user_basic a where dt=${partdt}
    left join gdm.gdm_user_consume_order b where dt=${partdt} on a.user_id=b.user_id 
    left join gdm.gdm_user_buy_category c where dt=${partdt} on a.user_id=c.user_id 
    left join gdm.gdm_user_visit d where dt=${partdt} on a.user_id=d.user_id;

================== 每天的 用户画像表 进行汇总 ==================
beeline -u jdbc:hive2://node1:10000 -n root

1.每天用户画像表：
    adm.adm_personas_日期时间 即为 adm.adm_personas_${partdt}

--------------------------------------------

2.完整用户画像表：
    adm.adm_personas_complete 必须为 ORC表，因此这样才能实现 更新和删除功能
    如：create table adm.adm_personas_complete(。。。) clustered by(user_id) into 10 buckets stored as orc TBLPROPERTIES('transactional'='true');
    注意：adm.adm_personas_complete 多出了一个字段“rn bigint”，因为使用到row_number() over(distribute by user_id) rn，所以需要存储分组中多出的一个rn序号值，
          但是把 adm.adm_personas_complete 数据 insert select 到 adm.adm_personas_hbase HIVE-HBASE映射表时，并不把“rn”值也拷贝过去

--------------------------------------------

3.第一次 合并前天的用户画像表(adm.adm_personas_前天)数据 和 昨天的用户画像表(adm.adm_personas_昨天)数据 到 
  完整用户画像表(adm.adm_personas_complete)中
     1.创建 adm.adm_personas_temp1 临时表1：
        用于临时存储前天的用户画像表(adm.adm_personas_前天)数据，需要对“adm.adm_personas_前天”中相同用户的多条数据进行分组并取出同一个用户中的
        其中一条数据即可，因为同一个用户一天中会购买多个不同商品，因此同一个用户分组下有多条对应不同商品的数据
        insert into adm.adm_personas_temp1 select * from (select *,row_number() over(distribute by user_id) rn from adm.adm_personas_${yesterday}) t where t.rn=1;
 
    2.创建 adm.adm_personas_temp2 临时表2：
        用于临时存储前天的用户画像表(adm.adm_personas_昨天)数据，需要对“adm.adm_personas_昨天”中相同用户的多条数据进行分组并取出同一个用户中的
        其中一条数据即可，因为同一个用户一天中会购买多个不同商品，因此同一个用户分组下有多条对应不同商品的数据
        insert into adm.adm_personas_temp2 select * from (select *,row_number() over(distribute by user_id) rn from adm.adm_personas_${beforeTwoDays}) t where t.rn=1;

    3.创建 adm.adm_personas_complete_temp 临时表：
        用于合并 adm.adm_personas_temp1 临时表1 和 adm.adm_personas_temp2 临时表2 中的 相同用户的数据 
        到 dm.adm_personas_complete_temp 临时表中，然后再把 临时表1 和 临时表2 中的 不同用户的数据 
        再拷贝到 dm.adm_personas_complete_temp 临时表中，此时前天和昨天的数据都合并到一张临时表中
        insert into adm.adm_personas_complete_temp select * from adm.adm_personas_temp1 temp1 where temp1.user_id not in (select completeTemp.user_id from adm.adm_personas_complete_temp completeTemp);
        insert into adm.adm_personas_complete_temp select * from adm.adm_personas_temp2 temp2 where temp2.user_id not in (select completeTemp.user_id from adm.adm_personas_complete_temp completeTemp);

    4.将 adm.adm_personas_complete_temp 临时表 insert select 到 adm.adm_personas_complete完整用户画像表 即可
        insert into adm.adm_personas_complete select * from adm.adm_personas_complete_temp;

--------------------------------------------

4.第二次和第二次以后的每次合并 完整用户画像表adm.adm_personas_complete 和 昨天的用户画像表(adm.adm_personas_昨天)数据
    1.创建 adm.adm_personas_temp1 临时表1：
        用于临时存储前天的用户画像表(adm.adm_personas_昨天)数据，需要对“adm.adm_personas_昨天”中相同用户的多条数据进行分组并取出同一个用户中的
        其中一条数据即可，因为同一个用户一天中会购买多个不同商品，因此同一个用户分组下有多条对应不同商品的数据
        insert into adm.adm_personas_temp1 select * from (select *,row_number() over(distribute by user_id) rn from adm.adm_personas_${yesterday}) t where t.rn=1;

    2.创建 adm.adm_personas_complete_temp：
        用于把 完整用户画像表adm.adm_personas_complete 和 adm.adm_personas_temp1 临时表中 相同用户数据进行合并后 
        存储到 adm_personas_complete_temp，临时表1 中有部分用户数据 没有被合并到 adm_personas_complete_temp 中的话，
        代表这部分用户数据 是第一次购买的

    3.把 合并后的用户数据 从 完整用户画像表adm.adm_personas_complete中 删除 旧的用户数据
        delete from adm.adm_personas_complete where user_id in (select user_id from adm.adm_personas_complete_temp);

    4.把 adm.adm_personas_temp1 临时表1中 没有被合并的第一次购买的 用户数据 也拷贝到 adm.adm_personas_complete_temp表中
        insert into adm.adm_personas_complete_temp select * from adm.adm_personas_temp1 temp1 where temp1.user_id not in (select completeTemp.user_id from adm.adm_personas_complete_temp completeTemp);
    
    5.将 adm.adm_personas_complete_temp 临时表 insert select 到 adm.adm_personas_complete完整用户画像表 即可
        insert into adm.adm_personas_complete select * from adm.adm_personas_complete_temp;

--------------------------------------------

5.例子分析
    create table if not exists rimengshe.a1(user_id string,user_name string,num double) clustered by(user_id) into 10 buckets stored as orc TBLPROPERTIES('transactional'='true');
    create table if not exists rimengshe.a2(user_id string,user_name string,num double);
    drop table if exists rimengshe.temp1;
    create table if not exists rimengshe.temp1(user_id string,user_name string,num double);
    use rimengshe;
    show tables;

    insert into rimengshe.a1(user_id,user_name,num) values('1','guzhipeng',10.6);
    insert into rimengshe.a1(user_id,user_name,num) values('2','guzhipeng',5.5);
    insert into rimengshe.a1(user_id,user_name,num) values('3','guzhipeng',4.1);
    select * from rimengshe.a1;
 
    insert into rimengshe.a2(user_id,user_name,num) values('1','guzhipeng',10.6);
    insert into rimengshe.a2(user_id,user_name,num) values('2','guzhipeng',5.5);
    insert into rimengshe.a2(user_id,user_name,num) values('3','guzhipeng',4.1);
    insert into rimengshe.a2(user_id,user_name,num) values('4','guzhipeng',3.7);
    insert into rimengshe.a2(user_id,user_name,num) values('5','guzhipeng',1.0);
    select * from rimengshe.a2;

    insert into rimengshe.temp1 select A1.user_id,A1.user_name,A1.num+A2.num from rimengshe.a1 A1 join rimengshe.a2 A2 on A1.user_id=A2.user_id;
    select * from rimengshe.temp1;
    delete from rimengshe.a1 where user_id in (select user_id from rimengshe.temp1);
    select * from rimengshe.a1;
    insert into rimengshe.a1 select * from rimengshe.temp1;
    select * from rimengshe.a1;
    insert into rimengshe.a1 select * from rimengshe.a2 A2 where A2.user_id not in (select A1.user_id from rimengshe.a1 A1);
    select * from rimengshe.a1;

 

================== 建立hive/hbase关联表 ==================
create table hive_test(
    user_id STRING, # user_id/id 代表的是 hbase中的 rowkey 
    user_name STRING,
    ......
 )STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler'
 WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,f1:user_name,......列簇名:列名") # 指定hbase表中的列簇名:列名
 TBLPROPERTIES ("hbase.table.name" = "hbase表",  # hive表中指定所映射的hbase表
           "hbase.mapred.output.outputtable" = "hbase表"); 
 
在hive1.2.1 跟 hbase 0.98整合时，需要添加："hbase.mapred.output.outputtable" = "hbase表" 
如果不添加会报错：Must specify table name


建立hive/hbase关联表：hive与hbase整合过程

    CREATE TABLE if not exists adm.adm_personas_hbase (
      user_id STRING,
      user_name STRING,
      user_sex STRING,
      user_birthday STRING,
      user_age BIGINT,
      constellation STRING,
      province STRING,
      city STRING,
      city_level STRING,
      hex_mail STRING,
      op_mail STRING,
      hex_phone STRING,
      fore_phone STRING,
      op_phone STRING,
      add_time TIMESTAMP,
      login_ip STRING,
      login_source STRING,
      request_user STRING,
      total_mark BIGINT,
      used_mark BIGINT,
      level_name STRING,
      blacklist BIGINT,
      is_married BIGINT,
      education STRING,
      monthly_money DOUBLE,
      profession STRING,
      sex_model BIGINT,
      is_pregnant_woman BIGINT,
      is_have_children BIGINT,
      children_sex_rate DOUBLE,
      children_age_rate DOUBLE,
      is_have_car BIGINT,
      potential_car_user_rate DOUBLE,
      phone_brand STRING,
      phone_brand_level STRING,
      phone_cnt BIGINT,
      change_phone_rate BIGINT,
      majia_flag STRING,
      majie_account_cnt BIGINT,
      loyal_model BIGINT,
      shopping_type_model BIGINT,
      figure_model BIGINT,
      stature_model BIGINT,
      first_order_time TIMESTAMP,
      last_order_time TIMESTAMP,
      first_order_ago BIGINT,
      last_order_ago BIGINT,
      month1_hg_order_cnt BIGINT,
      month1_hg_order_amt DOUBLE,
      month2_hg_order_cnt BIGINT,
      month2_hg_order_amt DOUBLE,
      month3_hg_order_cnt BIGINT,
      month3_hg_order_amt DOUBLE,
      month1_order_cnt BIGINT,
      month1_order_amt DOUBLE,
      month2_order_cnt BIGINT,
      month2_order_amt DOUBLE,
      month3_order_cnt BIGINT,
      month3_order_amt DOUBLE,
      max_order_amt DOUBLE,
      min_order_amt DOUBLE,
      total_order_cnt BIGINT,
      total_order_amt DOUBLE,
      user_avg_amt DOUBLE,
      month3_user_avg_amt DOUBLE,
      common_address STRING,
      common_paytype STRING,
      month1_cart_cnt BIGINT,
      month1_cart_goods_cnt BIGINT,
      month1_cart_submit_cnt BIGINT,
      month1_cart_rate DOUBLE,
      month1_cart_cancle_cnt DOUBLE,
      return_cnt BIGINT,
      return_amt DOUBLE,
      reject_cnt BIGINT,
      reject_amt DOUBLE,
      last_return_time TIMESTAMP,
      school_order_cnt BIGINT,
      company_order_cnt BIGINT,
      home_order_cnt BIGINT,
      forenoon_order_cnt BIGINT,
      afternoon_order_cnt BIGINT,
      night_order_cnt BIGINT,
      morning_order_cnt BIGINT,
      first_category_id BIGINT,
      first_category_name STRING,
      second_category_id BIGINT,
      second_catery_name STRING,
      third_category_id BIGINT,
      third_category_name STRING,
      month1_category_cnt BIGINT,
      month1_category_amt STRING,
      month3_category_cnt BIGINT,
      month3_category_amt STRING,
      month6_category_cnt BIGINT,
      month6_category_amt STRING,
      total_category_cnt BIGINT,
      total_category_amt STRING,
      month1_cart_category_cnt BIGINT,
      month3_cart_category_cnt BIGINT,
      month6_cart_category_cnt BIGINT,
      total_cart_category_cnt BIGINT,
      last_category_time TIMESTAMP,
      last_category_ago BIGINT,
      latest_pc_visit_date STRING,
      latest_app_visit_date STRING,
      latest_pc_visit_session STRING,
      latest_pc_cookies STRING,
      latest_pc_pv STRING,
      latest_pc_browser_name STRING,
      latest_pc_visit_os STRING,
      latest_app_name STRING,
      latest_app_visit_os STRING,
      latest_visit_ip STRING,
      latest_city STRING,
      latest_province STRING,
      first_pc_visit_date STRING,
      first_app_visit_date STRING,
      first_pc_visit_session STRING,
      first_pc_cookies STRING,
      first_pc_pv STRING,
      first_pc_browser_name STRING,
      first_pc_visit_os STRING,
      first_app_name STRING,
      first_app_visit_os STRING,
      first_visit_ip STRING,
      first_city STRING,
      first_province STRING,
      day7_app_cnt BIGINT,
      day15_app_cnt BIGINT,
      month1_app_cnt BIGINT,
      month2_app_cnt BIGINT,
      month3_app_cnt BIGINT,
      day7_pc_cnt BIGINT,
      day15_pc_cnt BIGINT,
      month1_pc_cnt BIGINT,
      month2_pc_cnt BIGINT,
      month3_pc_cnt BIGINT,
      month1_pc_days BIGINT,
      month1_pc_pv BIGINT,
      month1_pc_avg_pv BIGINT,
      month1_pc_diff_ip_cnt BIGINT,
      month1_pc_diff_cookie_cnt BIGINT,
      month1_pc_common_ip string,
      month1_pc_common_cookie string,
      month1_pc_common_browser_name string,
      month1_pc_common_os string,
      month1_hour025_cnt BIGINT,
      month1_hour627_cnt BIGINT,
      month1_hour829_cnt BIGINT,
      month1_hour10212_cnt BIGINT,
      month1_hour13214_cnt BIGINT,
      month1_hour15217_cnt BIGINT,
      month1_hour18219_cnt BIGINT,
      month1_hour20221_cnt BIGINT,
      month1_hour22223_cnt BIGINT
    ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES (
      "hbase.columns.mapping" = ":key,
    basicInfo:user_name,
    basicInfo:user_sex,
    basicInfo:user_birthday,
    basicInfo:user_age,
    basicInfo:constellation,
    basicInfo:province,
    basicInfo:city,
    basicInfo:city_level,
    basicInfo:hex_mail,
    basicInfo:op_mail,
    basicInfo:hex_phone,
    basicInfo:fore_phone,
    basicInfo:op_phone,
    basicInfo:add_time,
    basicInfo:login_ip,
    basicInfo:login_source,
    basicInfo:request_user,
    basicInfo:total_mark,
    basicInfo:used_mark,
    basicInfo:level_name,
    basicInfo:blacklist,
    basicInfo:is_married,
    basicInfo:education,
    basicInfo:monthly_money,
    basicInfo:profession,
    basicInfo:sex_model,
    basicInfo:is_pregnant_woman,
    basicInfo:is_have_children,
    basicInfo:children_sex_rate,
    basicInfo:children_age_rate,
    basicInfo:is_have_car,
    basicInfo:potential_car_user_rate,
    basicInfo:phone_brand,
    basicInfo:phone_brand_level,
    basicInfo:phone_cnt,
    basicInfo:change_phone_rate,
    basicInfo:majia_flag,
    basicInfo:majie_account_cnt,
    basicInfo:loyal_model,
    basicInfo:shopping_type_model,
    basicInfo:figure_model,
    basicInfo:stature_model,
    order:first_order_time,
    order:last_order_time,
    order:first_order_ago,
    order:last_order_ago,
    order:month1_hg_order_cnt,
    order:month1_hg_order_amt,
    order:month2_hg_order_cnt,
    order:month2_hg_order_amt,
    order:month3_hg_order_cnt,
    order:month3_hg_order_amt,
    order:month1_order_cnt,
    order:month1_order_amt,
    order:month2_order_cnt,
    order:month2_order_amt,
    order:month3_order_cnt,
    order:month3_order_amt,
    order:max_order_amt,
    order:min_order_amt,
    order:total_order_cnt,
    order:total_order_amt,
    order:user_avg_amt,
    order:month3_user_avg_amt,
    order:common_address,
    order:common_paytype,
    order:month1_cart_cnt,
    order:month1_cart_goods_cnt,
    order:month1_cart_submit_cnt,
    order:month1_cart_rate,
    order:month1_cart_cancle_cnt,
    order:return_cnt,
    order:return_amt,
    order:reject_cnt,
    order:reject_amt,
    order:last_return_time,
    order:school_order_cnt,
    order:company_order_cnt,
    order:home_order_cnt,
    order:forenoon_order_cnt,
    order:afternoon_order_cnt,
    order:night_order_cnt,
    order:morning_order_cnt,
    category:first_category_id,
    category:first_category_name,
    category:second_category_id,
    category:second_catery_name,
    category:third_category_id,
    category:third_category_name,
    category:month1_category_cnt,
    category:month1_category_amt,
    category:month3_category_cnt,
    category:month3_category_amt,
    category:month6_category_cnt,
    category:month6_category_amt,
    category:total_category_cnt,
    category:total_category_amt,
    category:month1_cart_category_cnt,
    category:month3_cart_category_cnt,
    category:month6_cart_category_cnt,
    category:total_cart_category_cnt,
    category:last_category_time,
    category:last_category_ago,
    visit:latest_pc_visit_date,
    visit:latest_app_visit_date,
    visit:latest_pc_visit_session,
    visit:latest_pc_cookies,
    visit:latest_pc_pv,
    visit:latest_pc_browser_name,
    visit:latest_pc_visit_os,
    visit:latest_app_name,
    visit:latest_app_visit_os,
    visit:latest_visit_ip,
    visit:latest_city,
    visit:latest_province,
    visit:first_pc_visit_date,
    visit:first_app_visit_date,
    visit:first_pc_visit_session,
    visit:first_pc_cookies,
    visit:first_pc_pv,
    visit:first_pc_browser_name,
    visit:first_pc_visit_os,
    visit:first_app_name,
    visit:first_app_visit_os,
    visit:first_visit_ip,
    visit:first_city,
    visit:first_province,
    visit:day7_app_cnt,
    visit:day15_app_cnt,
    visit:month1_app_cnt,
    visit:month2_app_cnt,
    visit:month3_app_cnt,
    visit:day7_pc_cnt,
    visit:day15_pc_cnt,
    visit:month1_pc_cnt,
    visit:month2_pc_cnt,
    visit:month3_pc_cnt,
    visit:month1_pc_days,
    visit:month1_pc_pv,
    visit:month1_pc_avg_pv,
    visit:month1_pc_diff_ip_cnt,
    visit:month1_pc_diff_cookie_cnt,
    visit:month1_pc_common_ip,
    visit:month1_pc_common_cookie,
    visit:month1_pc_common_browser_name,
    visit:month1_pc_common_os,
    visit:month1_hour025_cnt,
    visit:month1_hour627_cnt,
    visit:month1_hour829_cnt,
    visit:month1_hour10212_cnt,
    visit:month1_hour13214_cnt,
    visit:month1_hour15217_cnt,
    visit:month1_hour18219_cnt,
    visit:month1_hour20221_cnt,
    visit:month1_hour22223_cnt"
    ) TBLPROPERTIES ("hbase.table.name" = "adm_personas_hbase_20170101",
                  "hbase.mapred.output.outputtable"="adm_personas_hbase_20170101"
               ) ;

    //把用户画像宽表的数据导入到 Hive中的 hive/hbase 的映射关联表 中，然后就可以查询到 hbase中的 hive/hbase 的映射关联表 也有数据了
    //Hive表 加载数据，数据来源于另一张hive表hive_source（此时必须启动yarn集群）
    insert overwrite table adm.adm_personas_hbase select * from adm.adm_personas;

=============== phoenix建立映射表 ==============
 create VIEW "adm_personas_hbase"(
"user_id" varchar(100) primary key,
"basicInfo"."user_name" VARCHAR(100),
"basicInfo"."user_sex"  VARCHAR(100),
"basicInfo"."user_birthday" VARCHAR(100),
"basicInfo"."user_age"  VARCHAR(100),
"basicInfo"."constellation" VARCHAR(100),
"basicInfo"."province" VARCHAR(100),
"basicInfo"."city" VARCHAR(100),
"basicInfo"."city_level" VARCHAR(100),
"basicInfo"."hex_mail" VARCHAR(100),
"basicInfo"."op_mail" VARCHAR(100),
"basicInfo"."hex_phone" VARCHAR(100),
"basicInfo"."fore_phone" VARCHAR(100),
"basicInfo"."op_phone" VARCHAR(100),
"basicInfo"."add_time" VARCHAR(100),
"basicInfo"."login_ip" VARCHAR(100),
"basicInfo"."login_source" VARCHAR(100),
"basicInfo"."request_user" VARCHAR(100),
"basicInfo"."total_mark" VARCHAR(100),
"basicInfo"."used_mark" VARCHAR(100),
"basicInfo"."level_name" VARCHAR(100),
"basicInfo"."blacklist" VARCHAR(100),
"basicInfo"."is_married" VARCHAR(100),
"basicInfo"."education" VARCHAR(100),
"basicInfo"."monthly_money" VARCHAR(100),
"basicInfo"."profession" VARCHAR(100),
"basicInfo"."sex_model" VARCHAR(100),
"basicInfo"."is_pregnant_woman" VARCHAR(100),
"basicInfo"."is_have_children" VARCHAR(100),
"basicInfo"."children_sex_rate" VARCHAR(100),
"basicInfo"."children_age_rate" VARCHAR(100),
"basicInfo"."is_have_car" VARCHAR(100),
"basicInfo"."potential_car_user_rate" VARCHAR(100),
"basicInfo"."phone_brand" VARCHAR(100),
"basicInfo"."phone_brand_level" VARCHAR(100),
"basicInfo"."phone_cnt" VARCHAR(100),
"basicInfo"."change_phone_rate" VARCHAR(100),
"basicInfo"."majia_flag" VARCHAR(100),
"basicInfo"."majie_account_cnt" VARCHAR(100),
"basicInfo"."loyal_model" VARCHAR(100),
"basicInfo"."shopping_type_model" VARCHAR(100),
"basicInfo"."figure_model" VARCHAR(100),
"basicInfo"."stature_model" VARCHAR(100),
"order"."first_order_time" VARCHAR(100),
"order"."last_order_time" VARCHAR(100),
"order"."first_order_ago" VARCHAR(100),
"order"."last_order_ago" VARCHAR(100),
"order"."month1_hg_order_cnt" VARCHAR(100),
"order"."month1_hg_order_amt" VARCHAR(100),
"order"."month2_hg_order_cnt" VARCHAR(100),
"order"."month2_hg_order_amt" VARCHAR(100),
"order"."month3_hg_order_cnt" VARCHAR(100),
"order"."month3_hg_order_amt" VARCHAR(100),
"order"."month1_order_cnt" VARCHAR(100),
"order"."month1_order_amt" VARCHAR(100),
"order"."month2_order_cnt" VARCHAR(100),
"order"."month2_order_amt" VARCHAR(100),
"order"."month3_order_cnt" VARCHAR(100),
"order"."month3_order_amt" VARCHAR(100),
"order"."max_order_amt" VARCHAR(100),
"order"."min_order_amt" VARCHAR(100),
"order"."total_order_cnt" VARCHAR(100),
"order"."total_order_amt" VARCHAR(100),
"order"."user_avg_amt" VARCHAR(100),
"order"."month3_user_avg_amt" VARCHAR(100),
"order"."common_address" VARCHAR(100),
"order"."common_paytype" VARCHAR(100),
"order"."month1_cart_cnt" VARCHAR(100),
"order"."month1_cart_goods_cnt" VARCHAR(100),
"order"."month1_cart_submit_cnt" VARCHAR(100),
"order"."month1_cart_rate" VARCHAR(100),
"order"."month1_cart_cancle_cnt" VARCHAR(100),
"order"."return_cnt" VARCHAR(100),
"order"."return_amt" VARCHAR(100),
"order"."reject_cnt" VARCHAR(100),
"order"."reject_amt" VARCHAR(100),
"order"."last_return_time" VARCHAR(100),
"order"."school_order_cnt" VARCHAR(100),
"order"."company_order_cnt" VARCHAR(100),
"order"."home_order_cnt" VARCHAR(100),
"order"."forenoon_order_cnt" VARCHAR(100),
"order"."afternoon_order_cnt" VARCHAR(100),
"order"."night_order_cnt" VARCHAR(100),
"order"."morning_order_cnt" VARCHAR(100),
"category"."first_category_id" VARCHAR(100),
"category"."first_category_name" VARCHAR(100),
"category"."second_category_id" VARCHAR(100),
"category"."second_catery_name" VARCHAR(100),
"category"."third_category_id" VARCHAR(100),
"category"."third_category_name" VARCHAR(100),
"category"."month1_category_cnt" VARCHAR(100),
"category"."month1_category_amt" VARCHAR(100),
"category"."month3_category_cnt" VARCHAR(100),
"category"."month3_category_amt" VARCHAR(100),
"category"."month6_category_cnt" VARCHAR(100),
"category"."month6_category_amt" VARCHAR(100),
"category"."total_category_cnt" VARCHAR(100),
"category"."total_category_amt" VARCHAR(100),
"category"."month1_cart_category_cnt" VARCHAR(100),
"category"."month3_cart_category_cnt" VARCHAR(100),
"category"."month6_cart_category_cnt" VARCHAR(100),
"category"."total_cart_category_cnt" VARCHAR(100),
"category"."last_category_time" VARCHAR(100),
"category"."last_category_ago" VARCHAR(100),
"visit"."latest_pc_visit_date" VARCHAR(100),
"visit"."latest_app_visit_date" VARCHAR(100),
"visit"."latest_pc_visit_session" VARCHAR(100),
"visit"."latest_pc_cookies" VARCHAR(100), 
"visit"."latest_pc_pv" VARCHAR(100),
"visit"."latest_pc_browser_name" VARCHAR(100),
"visit"."latest_pc_visit_os" VARCHAR(100),
"visit"."latest_app_name" VARCHAR(100),
"visit"."latest_app_visit_os" VARCHAR(100),
"visit"."latest_visit_ip" VARCHAR(100),
"visit"."latest_city" VARCHAR(100),
"visit"."latest_province" VARCHAR(100),
"visit"."first_pc_visit_date" VARCHAR(100),
"visit"."first_app_visit_date" VARCHAR(100),
"visit"."first_pc_visit_session" VARCHAR(100),
"visit"."first_pc_cookies" VARCHAR(100),
"visit"."first_pc_pv" VARCHAR(100),
"visit"."first_pc_browser_name" VARCHAR(100),
"visit"."first_pc_visit_os" VARCHAR(100),
"visit"."first_app_name" VARCHAR(100),
"visit"."first_app_visit_os" VARCHAR(100),
"visit"."first_visit_ip" VARCHAR(100),
"visit"."first_city" VARCHAR(100),
"visit"."first_province" VARCHAR(100),
"visit"."day7_app_cnt" VARCHAR(100),
"visit"."day15_app_cnt" VARCHAR(100),
"visit"."month1_app_cnt" VARCHAR(100),
"visit"."month2_app_cnt" VARCHAR(100),
"visit"."month3_app_cnt" VARCHAR(100),
"visit"."day7_pc_cnt" VARCHAR(100),
"visit"."day15_pc_cnt" VARCHAR(100),
"visit"."month1_pc_cnt" VARCHAR(100),
"visit"."month2_pc_cnt" VARCHAR(100),
"visit"."month3_pc_cnt" VARCHAR(100),
"visit"."month1_pc_days" VARCHAR(100),
"visit"."month1_pc_pv" VARCHAR(100),
"visit"."month1_pc_avg_pv" VARCHAR(100),
"visit"."month1_pc_diff_ip_cnt" VARCHAR(100),
"visit"."month1_pc_diff_cookie_cnt" VARCHAR(100),
"visit"."month1_pc_common_ip" VARCHAR(100),
"visit"."month1_pc_common_cookie" VARCHAR(100),
"visit"."month1_pc_common_browser_name" VARCHAR(100),
"visit"."month1_pc_common_os" VARCHAR(100),
"visit"."month1_hour025_cnt" VARCHAR(100),
"visit"."month1_hour627_cnt" VARCHAR(100),
"visit"."month1_hour829_cnt" VARCHAR(100),
"visit"."month1_hour10212_cnt" VARCHAR(100),
"visit"."month1_hour13214_cnt" VARCHAR(100),
"visit"."month1_hour15217_cnt" VARCHAR(100),
"visit"."month1_hour18219_cnt" VARCHAR(100),
"visit"."month1_hour20221_cnt" VARCHAR(100),
"visit"."month1_hour22223_cnt" VARCHAR(100)
);

=============== 通过 sh 脚本 创建 phoenix中的映射表 ==============
#!/bin/sh
#获取昨天的时间
yesterday=`date -d '-1 day' "+%Y%m%d"`

#指定运行哪天的数据
if [ $1 ];then
    yesterday=$1
fi

table=adm_personas_hbase

touch $table.sql
 
echo 'create VIEW "'$table'"(
"user_id" varchar(100) primary key,
"basicInfo"."user_name" VARCHAR(100),
"basicInfo"."user_sex"  VARCHAR(100),
"basicInfo"."user_birthday" VARCHAR(100),
"basicInfo"."user_age"  VARCHAR(100),
"basicInfo"."constellation" VARCHAR(100),
"basicInfo"."province" VARCHAR(100),
"basicInfo"."city" VARCHAR(100),
"basicInfo"."city_level" VARCHAR(100),
"basicInfo"."hex_mail" VARCHAR(100),
"basicInfo"."op_mail" VARCHAR(100),
"basicInfo"."hex_phone" VARCHAR(100),
"basicInfo"."fore_phone" VARCHAR(100),
"basicInfo"."op_phone" VARCHAR(100),
"basicInfo"."add_time" VARCHAR(100),
"basicInfo"."login_ip" VARCHAR(100),
"basicInfo"."login_source" VARCHAR(100),
"basicInfo"."request_user" VARCHAR(100),
"basicInfo"."total_mark" VARCHAR(100),
"basicInfo"."used_mark" VARCHAR(100),
"basicInfo"."level_name" VARCHAR(100),
"basicInfo"."blacklist" VARCHAR(100),
"basicInfo"."is_married" VARCHAR(100),
"basicInfo"."education" VARCHAR(100),
"basicInfo"."monthly_money" VARCHAR(100),
"basicInfo"."profession" VARCHAR(100),
"basicInfo"."sex_model" VARCHAR(100),
"basicInfo"."is_pregnant_woman" VARCHAR(100),
"basicInfo"."is_have_children" VARCHAR(100),
"basicInfo"."children_sex_rate" VARCHAR(100),
"basicInfo"."children_age_rate" VARCHAR(100),
"basicInfo"."is_have_car" VARCHAR(100),
"basicInfo"."potential_car_user_rate" VARCHAR(100),
"basicInfo"."phone_brand" VARCHAR(100),
"basicInfo"."phone_brand_level" VARCHAR(100),
"basicInfo"."phone_cnt" VARCHAR(100),
"basicInfo"."change_phone_rate" VARCHAR(100),
"basicInfo"."majia_flag" VARCHAR(100),
"basicInfo"."majie_account_cnt" VARCHAR(100),
"basicInfo"."loyal_model" VARCHAR(100),
"basicInfo"."shopping_type_model" VARCHAR(100),
"basicInfo"."figure_model" VARCHAR(100),
"basicInfo"."stature_model" VARCHAR(100),
"order"."first_order_time" VARCHAR(100),
"order"."last_order_time" VARCHAR(100),
"order"."first_order_ago" VARCHAR(100),
"order"."last_order_ago" VARCHAR(100),
"order"."month1_hg_order_cnt" VARCHAR(100),
"order"."month1_hg_order_amt" VARCHAR(100),
"order"."month2_hg_order_cnt" VARCHAR(100),
"order"."month2_hg_order_amt" VARCHAR(100),
"order"."month3_hg_order_cnt" VARCHAR(100),
"order"."month3_hg_order_amt" VARCHAR(100),
"order"."month1_order_cnt" VARCHAR(100),
"order"."month1_order_amt" VARCHAR(100),
"order"."month2_order_cnt" VARCHAR(100),
"order"."month2_order_amt" VARCHAR(100),
"order"."month3_order_cnt" VARCHAR(100),
"order"."month3_order_amt" VARCHAR(100),
"order"."max_order_amt" VARCHAR(100),
"order"."min_order_amt" VARCHAR(100),
"order"."total_order_cnt" VARCHAR(100),
"order"."total_order_amt" VARCHAR(100),
"order"."user_avg_amt" VARCHAR(100),
"order"."month3_user_avg_amt" VARCHAR(100),
"order"."common_address" VARCHAR(100),
"order"."common_paytype" VARCHAR(100),
"order"."month1_cart_cnt" VARCHAR(100),
"order"."month1_cart_goods_cnt" VARCHAR(100),
"order"."month1_cart_submit_cnt" VARCHAR(100),
"order"."month1_cart_rate" VARCHAR(100),
"order"."month1_cart_cancle_cnt" VARCHAR(100),
"order"."return_cnt" VARCHAR(100),
"order"."return_amt" VARCHAR(100),
"order"."reject_cnt" VARCHAR(100),
"order"."reject_amt" VARCHAR(100),
"order"."last_return_time" VARCHAR(100),
"order"."school_order_cnt" VARCHAR(100),
"order"."company_order_cnt" VARCHAR(100),
"order"."home_order_cnt" VARCHAR(100),
"order"."forenoon_order_cnt" VARCHAR(100),
"order"."afternoon_order_cnt" VARCHAR(100),
"order"."night_order_cnt" VARCHAR(100),
"order"."morning_order_cnt" VARCHAR(100),
"category"."first_category_id" VARCHAR(100),
"category"."first_category_name" VARCHAR(100),
"category"."second_category_id" VARCHAR(100),
"category"."second_catery_name" VARCHAR(100),
"category"."third_category_id" VARCHAR(100),
"category"."third_category_name" VARCHAR(100),
"category"."month1_category_cnt" VARCHAR(100),
"category"."month1_category_amt" VARCHAR(100),
"category"."month3_category_cnt" VARCHAR(100),
"category"."month3_category_amt" VARCHAR(100),
"category"."month6_category_cnt" VARCHAR(100),
"category"."month6_category_amt" VARCHAR(100),
"category"."total_category_cnt" VARCHAR(100),
"category"."total_category_amt" VARCHAR(100),
"category"."month1_cart_category_cnt" VARCHAR(100),
"category"."month3_cart_category_cnt" VARCHAR(100),
"category"."month6_cart_category_cnt" VARCHAR(100),
"category"."total_cart_category_cnt" VARCHAR(100),
"category"."last_category_time" VARCHAR(100),
"category"."last_category_ago" VARCHAR(100),
"visit"."latest_pc_visit_date" VARCHAR(100),
"visit"."latest_app_visit_date" VARCHAR(100),
"visit"."latest_pc_visit_session" VARCHAR(100),
"visit"."latest_pc_cookies" VARCHAR(100), 
"visit"."latest_pc_pv" VARCHAR(100),
"visit"."latest_pc_browser_name" VARCHAR(100),
"visit"."latest_pc_visit_os" VARCHAR(100),
"visit"."latest_app_name" VARCHAR(100),
"visit"."latest_app_visit_os" VARCHAR(100),
"visit"."latest_visit_ip" VARCHAR(100),
"visit"."latest_city" VARCHAR(100),
"visit"."latest_province" VARCHAR(100),
"visit"."first_pc_visit_date" VARCHAR(100),
"visit"."first_app_visit_date" VARCHAR(100),
"visit"."first_pc_visit_session" VARCHAR(100),
"visit"."first_pc_cookies" VARCHAR(100),
"visit"."first_pc_pv" VARCHAR(100),
"visit"."first_pc_browser_name" VARCHAR(100),
"visit"."first_pc_visit_os" VARCHAR(100),
"visit"."first_app_name" VARCHAR(100),
"visit"."first_app_visit_os" VARCHAR(100),
"visit"."first_visit_ip" VARCHAR(100),
"visit"."first_city" VARCHAR(100),
"visit"."first_province" VARCHAR(100),
"visit"."day7_app_cnt" VARCHAR(100),
"visit"."day15_app_cnt" VARCHAR(100),
"visit"."month1_app_cnt" VARCHAR(100),
"visit"."month2_app_cnt" VARCHAR(100),
"visit"."month3_app_cnt" VARCHAR(100),
"visit"."day7_pc_cnt" VARCHAR(100),
"visit"."day15_pc_cnt" VARCHAR(100),
"visit"."month1_pc_cnt" VARCHAR(100),
"visit"."month2_pc_cnt" VARCHAR(100),
"visit"."month3_pc_cnt" VARCHAR(100),
"visit"."month1_pc_days" VARCHAR(100),
"visit"."month1_pc_pv" VARCHAR(100),
"visit"."month1_pc_avg_pv" VARCHAR(100),
"visit"."month1_pc_diff_ip_cnt" VARCHAR(100),
"visit"."month1_pc_diff_cookie_cnt" VARCHAR(100),
"visit"."month1_pc_common_ip" VARCHAR(100),
"visit"."month1_pc_common_cookie" VARCHAR(100),
"visit"."month1_pc_common_browser_name" VARCHAR(100),
"visit"."month1_pc_common_os" VARCHAR(100),
"visit"."month1_hour025_cnt" VARCHAR(100),
"visit"."month1_hour627_cnt" VARCHAR(100),
"visit"."month1_hour829_cnt" VARCHAR(100),
"visit"."month1_hour10212_cnt" VARCHAR(100),
"visit"."month1_hour13214_cnt" VARCHAR(100),
"visit"."month1_hour15217_cnt" VARCHAR(100),
"visit"."month1_hour18219_cnt" VARCHAR(100),
"visit"."month1_hour20221_cnt" VARCHAR(100),
"visit"."month1_hour22223_cnt" VARCHAR(100)
);' > $table.sql

/root/phoenix/bin/psql.py node1,node2,node3:2181 $table.sql
 

========= 把 mysql中的 用户表/订单表 数据 导入到 Hive中 =========  
---------------- MySQL----------------

CREATE DATABASE IF NOT EXISTS adm default charset utf8 COLLATE utf8_general_ci;
use adm;

CREATE TABLE `userPortraitComplete` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `insert_update_date` varchar(10) NOT NULL DEFAULT '' COMMENT '插入或更新时间',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户编号',
  `username` varchar(100) NOT NULL DEFAULT '' COMMENT '用户名',
  `consume_prices` double NOT NULL DEFAULT 0 COMMENT '至今消费金额(所有订单金额总和)',
  `consume_numbers` int(11) NOT NULL DEFAULT 0 COMMENT '至今消费次数(订单数)',
  `product_name` varchar(255) NOT NULL DEFAULT '' COMMENT '商品名',
  `product_id` varchar(50) NOT NULL DEFAULT '' COMMENT '商品编号',
  `product_numbers` int(11) NOT NULL DEFAULT 0 COMMENT '购买数量',
  `mobile` varchar(50) NOT NULL DEFAULT '' COMMENT '手机号',
  `province` varchar(100) NOT NULL DEFAULT '' COMMENT '省份',
  `city` varchar(100) NOT NULL DEFAULT '' COMMENT '城市',
  PRIMARY KEY (`id`),
  KEY `userID_consumePrices` (`user_id`,`consume_prices`),
  KEY `userID_consumeNumbers` (`user_id`,`consume_numbers`),
  KEY `insertUpdateDate` (`insert_update_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='完整用户画像';

source /root/userPortraitComplete.sql;

现在Hive中创建 adm.rimengshe_adm_personas_complete_temp，再把mysql表userPortraitComplete数据导入到Hive表adm.rimengshe_adm_personas_complete_temp

sqoop import \
--connect jdbc:mysql://192.168.20.41:3306/adm \
--username root \
--password admin \
--table userPortraitComplete \
--hive-table adm.rimengshe_adm_personas_complete_temp \
--hive-import \
--m 1

----------------Hive----------------

select * from adm.rimengshe_adm_personas_complete_temp;
select * from adm.rimengshe_adm_personas_complete;


create database if not exists adm;

drop table if exists adm.rimengshe_adm_personas_complete_temp;

create table if not exists adm.rimengshe_adm_personas_complete_temp(
id bigint,
insert_update_date string,
user_id string,
username string,
consume_prices double,
consume_numbers bigint,
product_name string,
product_id string,
product_numbers bigint,
mobile string,
province string,
city string);

drop table if exists adm.rimengshe_adm_personas_complete;

create table if not exists adm.rimengshe_adm_personas_complete(
user_id string,
user_name string,
user_sex  string,
user_birthday string,
user_age  bigint,
constellation string,
province string,
city string,
city_level string,
hex_mail string,
op_mail string,
hex_phone string,
fore_phone string,
op_phone string,
add_time timestamp,
login_ip string,
login_source string,
request_user string,
total_mark bigint,
used_mark bigint,
level_name string,
blacklist bigint,
is_married bigint,
education string,
monthly_money double,
profession string,
sex_model bigint,
is_pregnant_woman bigint,
is_have_children bigint,
children_sex_rate double,
children_age_rate double,
is_have_car bigint,
potential_car_user_rate double,
phone_brand string,
phone_brand_level string,
phone_cnt bigint,
change_phone_rate bigint,
majia_flag string,
majie_account_cnt bigint,
loyal_model bigint,
shopping_type_model bigint,
figure_model bigint,
stature_model bigint,
first_order_time timestamp,
last_order_time timestamp,
first_order_ago bigint,
last_order_ago bigint,
month1_hg_order_cnt bigint,
month1_hg_order_amt double,
month2_hg_order_cnt bigint,
month2_hg_order_amt double,
month3_hg_order_cnt bigint,
month3_hg_order_amt double,
month1_order_cnt bigint,
month1_order_amt double,
month2_order_cnt bigint,
month2_order_amt double,
month3_order_cnt bigint,
month3_order_amt double,
max_order_amt double,
min_order_amt double,
total_order_cnt bigint,
total_order_amt double,
user_avg_amt double,
month3_user_avg_amt double,
common_address string,
common_paytype string,
month1_cart_cnt bigint,
month1_cart_goods_cnt bigint,
month1_cart_submit_cnt bigint,
month1_cart_rate double,
month1_cart_cancle_cnt double,
return_cnt bigint,
return_amt double,
reject_cnt bigint,
reject_amt double,
last_return_time timestamp,
school_order_cnt bigint,
company_order_cnt bigint,
home_order_cnt bigint,
forenoon_order_cnt bigint,
afternoon_order_cnt bigint,
night_order_cnt bigint,
morning_order_cnt bigint,
first_category_id BIGINT,
first_category_name STRING,
second_category_id BIGINT,
second_catery_name STRING,
third_category_id BIGINT,
third_category_name STRING,
month1_category_cnt BIGINT,
month1_category_amt STRING,
month3_category_cnt BIGINT,
month3_category_amt STRING,
month6_category_cnt BIGINT,
month6_category_amt STRING,
total_category_cnt BIGINT,
total_category_amt STRING,
month1_cart_category_cnt BIGINT,
month3_cart_category_cnt BIGINT,
month6_cart_category_cnt BIGINT,
total_cart_category_cnt BIGINT,
last_category_time TIMESTAMP,
last_category_ago BIGINT,
latest_pc_visit_date string,
latest_app_visit_date string,
latest_pc_visit_session string,
latest_pc_cookies string,
latest_pc_pv string,
latest_pc_browser_name string,
latest_pc_visit_os string,
latest_app_name string,
latest_app_visit_os string,
latest_visit_ip string,
latest_city string,
latest_province string,
first_pc_visit_date string,
first_app_visit_date string,
first_pc_visit_session string,
first_pc_cookies string,
first_pc_pv string,
first_pc_browser_name string,
first_pc_visit_os string,
first_app_name string,
first_app_visit_os string,
first_visit_ip string,
first_city string,
first_province string,
day7_app_cnt bigint,
day15_app_cnt bigint,
month1_app_cnt bigint,
month2_app_cnt bigint,
month3_app_cnt bigint,
day7_pc_cnt bigint,
day15_pc_cnt bigint,
month1_pc_cnt bigint,
month2_pc_cnt bigint,
month3_pc_cnt bigint,
month1_pc_days bigint,
month1_pc_pv bigint,
month1_pc_avg_pv bigint,
month1_pc_diff_ip_cnt bigint,
month1_pc_diff_cookie_cnt bigint,
month1_pc_common_ip string,
month1_pc_common_cookie string,
month1_pc_common_browser_name string,
month1_pc_common_os string,
month1_hour025_cnt bigint,
month1_hour627_cnt bigint,
month1_hour829_cnt bigint,
month1_hour10212_cnt bigint,
month1_hour13214_cnt bigint,
month1_hour15217_cnt bigint,
month1_hour18219_cnt bigint,
month1_hour20221_cnt bigint,
month1_hour22223_cnt bigint);

insert into adm.rimengshe_adm_personas_complete(user_id,user_name,total_order_amt,total_order_cnt,hex_phone,province,city) select user_id,username,consume_prices,consume_numbers,mobile,province,city from adm.rimengshe_adm_personas_complete_temp;







 
