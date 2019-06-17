pragma solidity ^0.4.24;
import "./ItemData.sol";
contract ItemDataV3{
    string name;
    string  city;
    uint256 balance;
    address addressV1; 

    constructor(string n,uint b,string c)
     {
          name=n;
          balance=b;
	  city = c;
	  addressV1 = address(0);
     }
    
    function getname() constant public returns(string){
        return name;
    }
    function getbalance() constant public returns(uint256){
        return balance;
    }

    function getcity() constant public returns(string){
        return city;
    }

    function get_item() constant public returns(string,uint256,string,address)
    {
            return (name,balance,city,addressV1);
    }

    function set(string n,uint256 b,string c,address addrV1) public{
    	name = n;
        balance = b;
        city = c;
        addressV1 = addrV1;
    }
    
}
