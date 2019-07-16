#!/usr/bin/env bash
if [[ $# -ne 1 ]] ; then
    echo 'Wrong parameteer count'
    exit 1
fi
if [[ ! -d $1 ]] ; then
    echo "[$1]" 'is not a directory'
    exit 1
fi
wc -l $1/*.txt|sort -n|grep -v ' total$'|tail -1l >result.txt
