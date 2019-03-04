export LOCATOR_HOST=$(hostname)
export LOCATOR_PORT=10334
export GEMFIRE_WORKING_DIR=/var/gemfire

gfsh -e "start locator --name=locator1 --dir=$GEMFIRE_WORKING_DIR/locator1"

ssh machine2 "gfsh -e 'start server --name=server1 --dir=$GEMFIRE_WORKING_DIR/server1 --locators=$LOCATOR_HOST[$LOCATOR_PORT]'" &
ssh machine3 "gfsh -e 'start server --name=server2 --dir=$GEMFIRE_WORKING_DIR/server2 --locators=$LOCATOR_HOST[$LOCATOR_PORT]'" &
ssh machine4 "gfsh -e 'start server --name=server3 --dir=$GEMFIRE_WORKING_DIR/server3 --locators=$LOCATOR_HOST[$LOCATOR_PORT]'" &

# Start one on the local machine
gfsh -e "start server --name=server4 --dir=$GEMFIRE_WORKING_DIR/server4 --locators=$LOCATOR_HOST[$LOCATOR_PORT]'" &

echo "Starting servers in parallel: Waiting for server start to complete"
wait

echo "Server start complete. Current member list:"

gfsh -e "connect" -e "list members"

echo "Creating regions if they don't exist and loading data. Be sure you have your data files in $GEMFIRE_WORKING_DIR"
gfsh -e "run --file=createRegions.gfsh"
gfsh -e "run --file=importData.gfsh"
