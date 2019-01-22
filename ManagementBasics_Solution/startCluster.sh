gfsh start locator --name=locator1
ssh machine2 "gfsh start server --name=server1 --locators=machine1[10334]"
ssh machine3 "gfsh start server --name=server2 --locators=machine1[10334]"
ssh machine4 "gfsh start server --name=server3 --locators=machine1[10334]"
gfsh start server --name=server4 --locators=machine1[10334]

gfsh -e "connect" -e "list members"

gfsh -e "connect" -e "import data --region=BookMaster --file=../bookMasterData.gfd --member=server4"
gfsh -e "connect" -e "import data --region=Customer --file=../customerData.gfd --member=server4"
