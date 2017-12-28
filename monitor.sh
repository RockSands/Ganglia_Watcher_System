#!/bin/sh
# SCRIPT: X86_Performance monitor script
# AUTHOR: zhanglei
# DATE: 2012

DATE=`date`
USER=`whoami`
PWD=`pwd`
echo $PWD

if [ -n "$1" ]; then
        PWD=$1
fi

if [ -n "$2" ]; then
        USER=$2
fi

cd $PWD

#startup crontab
crontab -l 2>/dev/null > $PWD/pm_cron

PID=`ps -fu $USER|grep Ganglia_Watcher_System|grep -v grep|awk '{print $2}'|wc -l`
CRONID=`cat pm_cron | grep $PWD/startup.sh`

if ( [ -z "$CRONID" ] ||[ "$PID" -eq "0" ] || [ "$PID" -eq "3" ]) ; then
     echo '*/5 * * * *' $PWD'/startup.sh >' $PWD'/test.log' >> $PWD/pm_cron
     crontab $PWD/pm_cron
     echo "Performance crontab is start at $DATE by $USER." | tee -a $PWD/app_log.log
else
     echo "Ganglia_Watcher_System app is already exist at $DATE ."     
fi
rm -r $PWD/pm_cron
