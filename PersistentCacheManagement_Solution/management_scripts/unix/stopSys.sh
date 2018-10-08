#! /bin/bash

# 1) stops all servers
# 2) stop locator1

echo "******************"
echo "stopping server"
echo "stopping locator1"
echo "******************"

gfsh run --file=management_scripts/stopSys.gfsh

