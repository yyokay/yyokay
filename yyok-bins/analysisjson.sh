#!/usr/bin/env bash
jsonInput=/ddhome/tmp/log
jsonOutput=/ddhome/tmp/output
mkdir -p $jsonInput $jsonOutput

lasttimefile=`ls -l $jsonInput | grep hf-lib-other-logconsume.log | tail -n 1 | awk '{print $9}'`
for line in $(cat ${jsonOutput}/${lasttimefile});do

echo $line
done





