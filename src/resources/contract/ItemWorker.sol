pragma solidity ^0.4.24;
import "./ItemData.sol";


contract ItemWorker
{
     event item_created(address itemAddr,string name,uint256 balance);     
	

     function create(string n,uint256 b) public returns (int)
     {

		ItemData data =  new ItemData(n,b);
		emit item_created(data,n,b);
		return 0;

     }

     function get_item(address addr) public constant returns(string ,uint256)
     {
           ItemData item = ItemData(addr);
           return item.get_item();
      
     }

     

}
