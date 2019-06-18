pragma solidity ^0.4.24;


contract ItemFixed
{
     event item_created(string name,uint256 balance);     
	
     mapping(string=>uint256) itemmap;

     function create(string n,uint256 b) public returns (int)
     {

		itemmap[n]=b;
		emit item_created(n,b);
		return 0;

     }
     function get_balance(string name) public constant returns(uint256)
     {
        return itemmap[name];
     }

}
