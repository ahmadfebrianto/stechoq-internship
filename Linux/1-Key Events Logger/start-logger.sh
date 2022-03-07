#!/bin/sh

output_file=""

if [ $# -eq 0 ]; then
    output_file="/tmp/keypressed.log"
else
    output_file=$1
fi

for k in $(xinput | grep -Eio "[a-z].*k.*id=.*slave +keyboard" | grep -vi 'virtual' | grep -iEo 'id=[0-9]+' | cut -d '=' -f 2) ; do
    echo "--- Logging started at $(date +'%Y-%m-%d %H:%M:%S') ---" >> $output_file
    nohup xinput test $k >> $output_file 2>&1 &
done
