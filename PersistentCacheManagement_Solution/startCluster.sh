export LOCATOR_HOST="ec2-3-86-195-226.compute-1.amazonaws.com"

gfsh start locator --name=locator1 --J=-Djava.rmi.server.hostname=$LOCATOR_HOST

ssh machine2 "gfsh start server --name=server1 --locators=machine1[10334]" &
ssh machine3 "gfsh start server --name=server2 --locators=machine1[10334]" &
ssh machine4 "gfsh start server --name=server3 --locators=machine1[10334]" &
gfsh start server --name=server4 --locators=machine1[10334] &

# Wait for all background processess to complete
wait

echo "Server start complete. Current member list:"
gfsh -e "connect" -e "list members"

echo "Creating regions if they don't exist and loading data. Be sure you have your data files in $GEMFIRE_WORKING_DIR"
gfsh -e "run --file=createRegions.gfsh"

java com.bookshop.buslogic.BookLoader
java com.bookshop.buslogic.CustomerLoader
