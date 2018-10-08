start /b gfsh run --file=management_scripts\startServer1.gfsh
start /b gfsh run --file=management_scripts\startServer2.gfsh
start /b gfsh run --file=management_scripts\startServer3.gfsh

echo "Watch for all three servers to start"
pause
gfsh run --file=management_scripts\listMembers.gfsh
