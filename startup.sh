#!/bin/sh
#
# SCRIPT: X86_Performance_STARTUP_SCRIPT
# AUTHOR: zhanglei
# DATE: 2012

USER=`whoami`
DATE=`date`
PWD=`pwd`
echo $PWD
echo $USER
echo $DATE

if [ -n "$1" ]; then
	PWD=$1
fi

if [ -n "$2" ]; then
	USER=$2
fi
echo $PWD

PID=`ps -fu$USER|grep Ganglia_Watcher_System-jar-with-dependencies|grep -v grep|grep -v ps|wc -l`

if [ "$PID" -eq "0" ]; then
	echo "Ganglia_Watcher_System-jar-with-dependencies is Start up at $DATE by $USER ."	
	cd $PWD
	nohup java -jar -Xms64m -Xmx128m Ganglia_Watcher_System-jar-with-dependencies.jar >> /dev/null 2>&1 &
	
else
	echo "Ganglia_Watcher_System-jar-with-dependencies app is already exist at $DATE ." 
	echo "Ganglia_Watcher_System-jar-with-dependencies application is start up already."
fi



