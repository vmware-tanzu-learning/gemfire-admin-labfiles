export LOCATOR_HOST="ec2-3-86-195-226.compute-1.amazonaws.com"

gfsh start locator --name=locator1 --jmx-manager-hostname-for-clients=$LOCATOR_HOST

ssh machine2 "gfsh start server --name=server1 --locators=machine1[10334]"
ssh machine3 "gfsh start server --name=server2 --locators=machine1[10334]"
ssh machine4 "gfsh start server --name=server3 --locators=machine1[10334]"
gfsh start server --name=server4 --locators=machine1[10334]

gfsh -e "connect" -e "list members"

gfsh -e "connect" -e "import data --region=BookMaster --file=../bookMasterData.gfd --member=server4"
gfsh -e "connect" -e "import data --region=Customer --file=../customerData.gfd --member=server4"
