pragma solidity ^0.4.24;

contract ItemData{
    string name;
    uint256 balance;


    constructor(string n,uint256 b)
     {
          name=n;
          balance=b;

     }

    function getname() constant public returns(string){
        return name;
    }
    function getbalance() constant public returns(uint256){
        return balance;
    }

    function set(string n,uint256 b) public{
    	name = n;
        balance = b;
    }
    function get_item() public constant returns(string ,uint256)
    {
         return (name,balance);
    }
}
