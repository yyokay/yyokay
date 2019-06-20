#!/bin/bash
TIMESTAMP=`date +%Y%m%d%H%M%S`
#1.1 CPU利用率巡检

#脚本功能描述：依据/proc/stat文件获取并计算CPU使用率
#
#CPU时间计算公式：CPU_TIME=user+system+nice+idle+iowait+irq+softirq
#CPU使用率计算公式：cpu_usage=(idle2-idle1)/(cpu2-cpu1)*100
#默认时间间隔
TIME_INTERVAL=5

LAST_CPU_INFO=$(cat /proc/stat | grep -w cpu | awk '{print $2,$3,$4,$5,$6,$7,$8}')
LAST_SYS_IDLE=$(echo $LAST_CPU_INFO | awk '{print $4}')
LAST_TOTAL_CPU_T=$(echo $LAST_CPU_INFO | awk '{print $1+$2+$3+$4+$5+$6+$7}')
sleep ${TIME_INTERVAL}
NEXT_CPU_INFO=$(cat /proc/stat | grep -w cpu | awk '{print $2,$3,$4,$5,$6,$7,$8}')
NEXT_SYS_IDLE=$(echo $NEXT_CPU_INFO | awk '{print $4}')
NEXT_TOTAL_CPU_T=$(echo $NEXT_CPU_INFO | awk '{print $1+$2+$3+$4+$5+$6+$7}')

#系统空闲时间
SYSTEM_IDLE=`echo ${NEXT_SYS_IDLE} ${LAST_SYS_IDLE} | awk '{print $1-$2}'`
#CPU总时间
TOTAL_TIME=`echo ${NEXT_TOTAL_CPU_T} ${LAST_TOTAL_CPU_T} | awk '{print $1-$2}'`
CPU_USAGE=`echo ${SYSTEM_IDLE} ${TOTAL_TIME} | awk '{printf "%.2f", 100-$1/$2*100}'`

# top -n 1 | grep Cpu

echo "CPU Usage:${CPU_USAGE}%"$time >> /data/local/cpu.log


#1.2 内存利用率巡检
mem_use_info=(`awk '/MemTotal/{memtotal=$2}/MemAvailable/{memavailable=$2}END{printf "%.2f %.2f %.2f",memtotal/1024/1024," "(memtotal-memavailable)/1024/1024," "(memtotal-memavailable)/memtotal*100}' /proc/meminfo`)

echo total:${mem_use_info[0]}G  used:${mem_use_info[1]}G  Usage:${mem_use_info[2]}%


#脚本功能描述：依据/proc/stat文件获取并计算CPU使用率
#
#CPU时间计算公式：CPU_TIME=user+system+nice+idle+iowait+irq+softirq
#CPU使用率计算公式：cpu_usage=(idle2-idle1)/(cpu2-cpu1)*100
#默认时间间隔
TIME_INTERVAL=5
time=$(date "+%Y-%m-%d %H:%M:%S")
LAST_CPU_INFO=$(cat /proc/stat | grep -w cpu | awk '{print $2,$3,$4,$5,$6,$7,$8}')
LAST_SYS_IDLE=$(echo $LAST_CPU_INFO | awk '{print $4}')
LAST_TOTAL_CPU_T=$(echo $LAST_CPU_INFO | awk '{print $1+$2+$3+$4+$5+$6+$7}')
sleep ${TIME_INTERVAL}
NEXT_CPU_INFO=$(cat /proc/stat | grep -w cpu | awk '{print $2,$3,$4,$5,$6,$7,$8}')
NEXT_SYS_IDLE=$(echo $NEXT_CPU_INFO | awk '{print $4}')
NEXT_TOTAL_CPU_T=$(echo $NEXT_CPU_INFO | awk '{print $1+$2+$3+$4+$5+$6+$7}')

#系统空闲时间
SYSTEM_IDLE=`echo ${NEXT_SYS_IDLE} ${LAST_SYS_IDLE} | awk '{print $1-$2}'`
#CPU总时间
TOTAL_TIME=`echo ${NEXT_TOTAL_CPU_T} ${LAST_TOTAL_CPU_T} | awk '{print $1-$2}'`
CPU_USAGE=`echo ${SYSTEM_IDLE} ${TOTAL_TIME} | awk '{printf "%.2f", 100-$1/$2*100}'`

echo "CPU Usage:${CPU_USAGE}%"$time >> /home/youcong/cpu.log


#1.3 文件系统巡检


#1.4 磁盘IO性能巡检



#1.5 网络连通性巡检
#
#通过/proc/net/dev获取执行网卡的信息
if [ $# -lt 2 ];then
  echo "Useage : `basename $0` eth0 1"
  exit 1
fi
#网卡名
eth=$1
#时间间隔（频率）
interval=$2

in_last=$(cat /proc/net/dev | grep ${eth} | sed -e "s/\(.*\)\:\(.*\)/\2/g" | awk '{print $1 }')
out_last=$(cat /proc/net/dev | grep ${eth} | sed -e "s/\(.*\)\:\(.*\)/\2/g" | awk '{print $9 }')

while true
    do
        sleep ${interval}
        in=$(cat /proc/net/dev | grep $eth | sed -e "s/\(.*\)\:\(.*\)/\2/g" | awk '{print $1 }')
        out=$(cat /proc/net/dev | grep $eth | sed -e "s/\(.*\)\:\(.*\)/\2/g" | awk '{print $9 }')

        traffic_in=`echo ${in} ${in_last} | awk '{printf "%.2f", ($1-$2)/1024}'`
        traffic_out=`echo ${out} ${out_last} | awk '{printf "%.2f", ($1-$2)/1024}'`
        current_time=$(date +"%F %H:%M:%S")

        echo "${current_time} -- IN: ${traffic_in} KByte/s     OUT: ${traffic_out} KByte/s"

        in_old=${in}
        out_old=${out}
   done


#1.6 机器的负载情况
uptime


#



#



#



#