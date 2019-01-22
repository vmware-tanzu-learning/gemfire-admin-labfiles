export LOCATOR_HOST="ec2-3-86-195-226.compute-1.amazonaws.com"

gfsh start locator --name=locator1 --J=-Djava.rmi.server.hostname=$LOCATOR_HOST

ssh machine2 "gfsh start server --name=server1 --locators=machine1[10334]" &
ssh machine3 "gfsh start server --name=server2 --locators=machine1[10334]" &
ssh machine4 "gfsh start server --name=server3 --locators=machine1[10334]" &
gfsh start server --name=server4 --locators=machine1[10334] &

# Wait for all background processess to complete
wait

gfsh -e "connect" -e "list members"
