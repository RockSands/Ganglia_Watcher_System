#!/bin/sh
#
# SCRIPT: X86_Performance_STARTUP_SCRIPT
# AUTHOR: zhanglei
# DATE: 2012

PWD=`pwd`
DATE=`date`
USER=`whoami`
echo $USER
echo $DATE

PID=`ps -fu$USER|grep Ganglia_Watcher_System-jar-with-dependencies |grep -v grep| awk '{print $2}'`

echo $PID
if [ -n "$PID" ]; then
kill $PID

echo "\n Ganglia_Watcher_System-jar-with-dependencies is shut down sucessfully"
echo "\n Ganglia_Watcher_System-jar-with-dependencies is shut down sucessfully on $DATE " >> backlog 

else
echo "No process found."
fi

