pragma solidity ^0.4.24;
import "./ItemData.sol";


contract ItemWorkerV2
{
     event itemV2_created(int retcode,address itemAddr,string name,uint256 balance,string memo);     
	

     function create(string n,uint256 b) public returns (int)
     {

	if (bytes(n).length <4)
        {
	  itemV2_created(-1,0x0,n,b,"name length too short");
	  return -1;
	}
        if(b > 1000000)
        {
          itemV2_created(-2,0x0,n,b,"balance too much");
	  return -2;
        }
	ItemData data =  new ItemData(n,b);
	itemV2_created(0,data,n,b,"success,remember the address");
	return 0;
     }

     function get_item(address addr) public constant returns (string ,uint256){
         ItemData data = ItemData(addr);
         return (data.getname(),data.getbalance());
     }

}
