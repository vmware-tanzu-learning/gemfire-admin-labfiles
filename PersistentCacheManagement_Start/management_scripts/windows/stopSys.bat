echo "******************"
echo "stopping server"
echo "stopping locator1"
echo "******************"

gfsh run --file=management_scripts\stopSys.gfsh

echo "**************************"
echo "verify no members running"
echo "**************************"

gfsh run --file=management_scripts\listmembers.gfsh

echo "**********************************"
echo " complete list of running members"
echo "**********************************"
