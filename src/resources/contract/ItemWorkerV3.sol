pragma solidity ^0.4.24;
import "./ItemDataV3.sol";
import "./ItemData.sol"


contract ItemWorkerV3
{
     event  itemV3_created(int retcode,address itemAddr,string name,uint256 balance,string city,string memo);     
     event  itemV3_migrated(int retcode,address addressV1,address addressV3,string memo);
	

     function create(string n,uint256 b,string c) public returns (int)
     {

	if (bytes(n).length <4)
        {
	  emit itemV3_created(-1,0x0,n,b,c,"name length too short");
	  return -1;
	}
        if(b > 1000000)
        {
          emit itemV3_created(-2,0x0,n,b,c,"balance too much");
	  return -2;
        }
	ItemDataV3 data =  new ItemDataV3(n,b,c);
	emit itemV3_created(0,data,n,b,c,"itemV3 create success,remember the address");
	return 0;

     }


     function create_byitemV1(address addrV1,string c) public returns (int)
     {
         ItemDataV3 item = new ItemDataV3("",0,c);
         return migrateItem(addrV1,item);
     }
 
     function migrateItem(address addrV1,ItemDataV3 itemV3) public returns (int)
     {
       ItemData itemdataV1 = ItemData(addrV1);
       if(itemdataV1 == address(0) )
       {
          emit itemV3_migrated(-1,addrV1,itemV3,"itemdata V1 not found");
          return -1;
       }

       itemV3.set( itemdataV1.getname(),itemdataV1.getbalance(),itemV3.getcity(),addrV1);
       emit itemV3_migrated(0,addrV1,itemV3,"migration V3 done");
       return 0;
    }
    

     function get_item(address addr) public constant returns (string,uint256,string,address){
         ItemDataV3 data = ItemDataV3(addr);
         return data.get_item();
     }

}
