#!/bin/sh

#author:	zhanglei
#version:	1.0

#重启前先结束旧进程
kill `cat $PWD/server.pid`
>$PWD/server.pid
sleep 3;

kill -9 `ps -ef | grep Ganglia_Watcher_System | head -1 | awk '{print $2}'` 

#打印重启时间
echo "restart Ganglia_Watcher_System at " `date` >> backLog
nohup java -jar Ganglia_Watcher_System.jar >> backLog &

#输出进程号到日志文件，用来结束进程
echo $! > $PWD/server.pid

exit 0
