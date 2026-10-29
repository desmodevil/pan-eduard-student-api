#!/bin/bash
set -euo pipefail
cd "$(dirname "$0")/.."
mkdir -p scripts logs backups src
touch logs/Pan_Eduard_system_report.log
chmod 744 scripts/Pan_Eduard_system_report.sh
./scripts/Pan_Eduard_system_report.sh
backup="backups/Pan_Eduard_README_$(date +%Y%m%d_%H%M%S).md"
cp README.md "$backup"
ls -l scripts/Pan_Eduard_system_report.sh "$backup"
cmp README.md "$backup"
find . -maxdepth 3 -not -path './.git/*' -not -path './build/*' -print
ps aux
pgrep -af java || true
tail -n 5 logs/Pan_Eduard_system_report.log
grep 'Pan' logs/Pan_Eduard_system_report.log | tee logs/Pan_Eduard_log_analysis.txt
