#!/bin/bash
set -euo pipefail
cd "$(dirname "$0")/.."
mkdir -p logs backups
{
 echo 'Student: Pan Eduard | IT2-2312'
 date --iso-8601=seconds
 whoami
 cat /etc/os-release
 uname -a
 lscpu
 free -h
 df -h
 df -h --output=source,avail,target
} | tee logs/Pan_Eduard_system_report.log
