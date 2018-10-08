#! /bin/bash

# load Customer data into Customer region

echo "******************************"
echo "start loading Customer region"
echo "******************************"

gfsh run --file=management_scripts/loadCustomerData.gfsh

