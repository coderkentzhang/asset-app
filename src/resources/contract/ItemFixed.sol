pragma solidity ^0.4.24;


contract ItemFixed
{
     event item_created(address itemAddr,string name,uint256 balance);     
	
     mapping(string=>uint256) itemmap;

     function create(string n,uint256 b) public returns (int)
     {

	itemmap[n]=b;
	return 0;

     }
     function get_balance(string name) public constant returns(uint256)
     {
        return itemmap[name];
     }

}
