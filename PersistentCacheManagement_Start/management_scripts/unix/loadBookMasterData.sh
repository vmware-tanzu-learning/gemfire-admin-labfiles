#! /bin/bash

# load BookMaster data into BookMaster region

echo "**************************"
echo "start loading BookMaster region"
echo "**************************"

gfsh run --file=management_scripts/loadBookMasterData.gfsh

