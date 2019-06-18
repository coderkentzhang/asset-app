pragma solidity ^0.4.24;

import "./Table.sol";

contract ItemTableWorker {

    event createEvent(int256 ret, string name, uint256 balance,string city,string memo);
   // event transferEvent(int256 ret, string from, string to, uint256 amount,string memo);
	
	string tableName;
        constructor() public {
        // 构造函数中创建t_asset表
		tableName = "t_itemdata";
        createTable();
    }

    function createTable() private {
        TableFactory tf = TableFactory(0x1001); 
		//表字段列： name，balance ,city
        tf.createTable(tableName, "name", "balance,city");

    }

    function openTable() private returns(Table) {
	    TableFactory tf = TableFactory(0x1001); 
        Table  table = tf.openTable(tableName);
        return table;
    }
    
    function select(string name) public constant returns(int256,uint256,bytes32) {
        // 打开表
        Table table  = openTable();
        // 查询
        Entries entries = table.select(name, table.newCondition());
        if (0 == uint256(entries.size())) {
            return (-1, 0,"");
        } else {
            Entry entry = entries.get(0);
            return (0, uint256(entry.getInt("balance")),entry.getBytes32("city") );
        }
    }
	
	function is_exist(string name) public constant returns(int256 )
	{
	     Table table =   openTable();
	     Entries entries = table.select(name, table.newCondition());
         uint256 balance = 0;
         if (0 == uint256(entries.size())) {
            return (0);
         } else {
            Entry entry = entries.get(0);
            return (1);
        }
	}
    
    function create(string name, uint256 balance,string city) public returns(int256){
        int256 ret_code = 0;
        int256 ret= 0;
        uint256 temp_balance = 0;
		
        Table table = openTable();		
        // 查询账号是否存在
        ret = is_exist(name);
        if(ret != 0) {
	      ret_code = -1;
	      emit createEvent(ret_code, name,0,"","name exists ,can't create again");
	 	   return ret_code;
   	    }
		
        Entry entry = table.newEntry();
        entry.set("name", name);
        entry.set("balance", int256(balance));
	    entry.set("city", city);
        int count = table.insert(name, entry);
        if (count == 1) {
            ret_code = 0;
			emit createEvent(ret_code, name, balance,city,"create success");
        } else {
           ret_code = -2;
           emit createEvent(ret_code, name, balance,city,"create error");
		}
        return ret_code;
    }

}

