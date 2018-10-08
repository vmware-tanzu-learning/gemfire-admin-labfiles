#!/bin/bash

# locator must be running before running this script to start servers

# start server1, server2, server3 through gfsh
echo "********************"
echo "starting server1"
echo "starting server2"
echo "starting server3"
echo "********************"
gfsh run --file=management_scripts/startServer1.gfsh &
gfsh run --file=management_scripts/startServer2.gfsh &
gfsh run --file=management_scripts/startServer3.gfsh

# Wait for all background processess to complete
wait

#output running members
gfsh run --file=management_scripts/listMembers.gfsh

