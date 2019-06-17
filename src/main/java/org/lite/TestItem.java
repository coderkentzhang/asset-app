package org.lite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.fisco.bcos.asset.client.AssetClient;
import org.fisco.bcos.asset.contract.Asset;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.CipherException;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.crypto.WalletUtils;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tuples.generated.Tuple4;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.lite.ItemWorker.Item_createdEventResponse;
import org.lite.ItemWorkerV2.ItemV2_createdEventResponse;
import org.lite.ItemWorkerV3.ItemV3_createdEventResponse;

import org.lite.ItemWorkerV3.ItemV3_migratedEventResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.google.common.io.Files;

public class TestItem {
	static Logger logger = LoggerFactory.getLogger(TestItem.class);

	private Web3j web3j;

	private Credentials credentials;

	public Web3j getWeb3j() {
		return web3j;
	}

	public void setWeb3j(Web3j web3j) {
		this.web3j = web3j;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	
	public ContractHelper contractHelper  = new ContractHelper( "d://tmp//itemworkeraddr.properties" );
	public static  String credentialsFilePath = "d:/tmp";
	//
	public String workerAddress = "";
	public String workerAddressV2 = "";
	public String workerAddressV3 ="";
	
	public ItemWorker   itemworker = null;
	public ItemWorkerV2 itemworkerV2 = null;
	public ItemWorkerV3 itemworkerV3 = null;
	
	private static BigInteger gasPrice = new BigInteger("30000000");
	private static BigInteger gasLimit = new BigInteger("30000000");
	
	//input password and a simple filename(alice.json ,etc) to create a new account

	
	
	public void initialize() throws Exception {

		// init the Service
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		Service service = context.getBean(Service.class);
		service.run();

		ChannelEthereumService channelEthereumService = new ChannelEthereumService();
		channelEthereumService.setChannelService(service);
		Web3j web3j = Web3j.build(channelEthereumService, 1);

			
		// init Credentials
		//Credentials credentials = Credentials.create(Keys.createEcKeyPair());
		Credentials credentials = WalletUtils.loadCredentials("123456", credentialsFilePath+"/alice.json");
		setCredentials(credentials);
		System.out.println("transaction account: "+ credentials.getAddress());
		System.out.println("transaction pubkey: " + credentials.getEcKeyPair().getPublicKey());
		System.out.println("transaction privbkey: " + credentials.getEcKeyPair().getPrivateKey());
		setWeb3j(web3j);

		logger.debug(" web3j is " + web3j + " ,credentials is " + credentials);
	}
	
	public void  loadWorker() throws Exception
	{
		this.workerAddress = contractHelper.loadAddr("worker");
		System.out.println("load worker on adddress : "+workerAddress);
		itemworker = ItemWorker.load(workerAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
		System.out.println("load worker on adddress ,result: "+itemworker.getContractAddress());
	}
	
	public void  loadWorkerV2() throws Exception
	{
		this.workerAddressV2 = contractHelper.loadAddr("workerV2");
		System.out.println("load workerV2 on adddress : "+workerAddressV2);
		itemworkerV2 = ItemWorkerV2.load(workerAddressV2, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
		System.out.println("load workerV2 on adddress ,result: "+itemworkerV2.getContractAddress());
	}
	
	public void  loadWorkerV3() throws Exception
	{
		this.workerAddressV3 = contractHelper.loadAddr("workerV3");
		System.out.println("load workerV3 on adddress : "+workerAddressV3);
		itemworkerV3 = ItemWorkerV3.load(workerAddressV3, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
		System.out.println("load workerV3 on adddress ,result: "+itemworkerV3.getContractAddress());
	}
	
	
	public void deploy() {

		try {
			ItemWorker itemworker = ItemWorker.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
			System.out.println(" deploy V1 success, contract address is " + itemworker.getContractAddress());
			this.workerAddress = itemworker.getContractAddress();
			contractHelper.recordAddr("worker",itemworker.getContractAddress());
			
			ItemWorkerV2 itemworkerV2 = ItemWorkerV2.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
			System.out.println(" deploy V2 success, contract address is " + itemworkerV2.getContractAddress());
			this.workerAddressV2 = itemworkerV2.getContractAddress();
			contractHelper.recordAddr("workerV2",itemworkerV2.getContractAddress());
			
			
			ItemWorkerV3 itemworkerV3 = ItemWorkerV3.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
			System.out.println(" deploy V3 success, contract address is " + itemworkerV3.getContractAddress());
			this.workerAddressV3 = itemworkerV2.getContractAddress();
			contractHelper.recordAddr("workerV3",itemworkerV3.getContractAddress());			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(" deploy ItemWorkers contract failed, error message is  " + e.getMessage());
		}
	}
	
	public int  createItem(String name,BigInteger balance) throws Exception
	{
		System.out.println("---------->testing V1");
		TransactionReceipt receipt = itemworker.create(name,balance).send();
		List<Item_createdEventResponse> lstEvents = itemworker.getItem_createdEvents(receipt);
		
		Item_createdEventResponse response = lstEvents.get(0);
		System.out.println("-----------Receipt event start-----------------");
		System.out.println("address :"+response.itemAddr);
		System.out.println("name    :"+response.name);
		System.out.println("balance :"+response.balance.intValue());
		System.out.println("-----------Receipt event end-----------------");
		Tuple2<String, BigInteger> itemdata = itemworker.get_item(response.itemAddr).send();
		System.out.println(itemdata);
		return 0;
	}
	
	
	public int  createItemV2(String name,BigInteger balance) throws Exception
	{
		
		System.out.println("---------->testing V2");
		TransactionReceipt receipt = itemworkerV2.create(name,balance).send();
		List<ItemWorkerV2.ItemV2_createdEventResponse> lstEvents = itemworkerV2.getItemV2_createdEvents(receipt);
		
		ItemWorkerV2.ItemV2_createdEventResponse response = lstEvents.get(0);
		System.out.println("-----------Receipt event start-----------------");
		System.out.println("retcode :"+response.retcode);
		System.out.println("address :"+response.itemAddr);
		System.out.println("name    :"+response.name);
		System.out.println("balance :"+response.balance.intValue());
		System.out.println("memo    :"+response.memo);
		System.out.println("-----------Receipt event end-----------------");
		if(response.retcode.intValue() == 0)
		{
			Tuple2<String, BigInteger> itemdata = itemworkerV2.get_item(response.itemAddr).send();
			if(itemdata!=null)
			{
				System.out.println(itemdata);
			}
			else {
				
				System.out.println("no item by name:"+name);
			}
		}else {
			System.out.println("create item error,go to end");
		}
		return 0;
	}
	
	
	public int testV1V2(String name,BigInteger balance) throws Exception
	{
		TransactionReceipt receipt = itemworker.create(name,balance).send();
		List<Item_createdEventResponse> lstEvents = itemworker.getItem_createdEvents(receipt);
		Item_createdEventResponse response = lstEvents.get(0);
		System.out.println("after V1 create,new item address :"+response.itemAddr);
		System.out.println("item name: " +response.name+",balance: "+response.balance);
		
		//try get item by V2
		itemworkerV2.get_item(response.itemAddr);
		Tuple2<String, BigInteger> itemdata = itemworkerV2.get_item(response.itemAddr).send();
		if(itemdata!=null)
		{
			System.out.println("get the item by V2:"+itemdata);
		}
		
		return 0;
	}
	
	
	public int testV2V3(String name,BigInteger balance,String city) throws Exception
	{
		
		//create a ItemData V2
		TransactionReceipt receipt = itemworkerV2.create(name,balance).send();
		List<ItemV2_createdEventResponse> lstEvents = itemworkerV2.getItemV2_createdEvents(receipt);
		ItemV2_createdEventResponse response = lstEvents.get(0);
		System.out.println("after V2 create,new item address :"+response.itemAddr);
		System.out.println("item name: " +response.name+",balance: "+response.balance);
		
		//try migrate V2 to V3
		System.out.println("migrate from address: "+response.itemAddr);
		receipt = this.itemworkerV3.create_byitemV1(response.itemAddr,city).send();
		List<ItemV3_migratedEventResponse> lstEventsV3 = itemworkerV3.getItemV3_migratedEvents(receipt);
		ItemV3_migratedEventResponse responseV3 = lstEventsV3.get(0);
		System.out.println("after migrate to V3: " +" new itemaddr: "+responseV3.addressV3+",memo: "+response.memo);
		System.out.println("from V2 Address:" +responseV3.addressV1);
		
		Tuple4<String, BigInteger, String, String> itemdataV3 =  itemworkerV3.get_item(responseV3.addressV3).send();
		System.out.println("migrate result itemV3 = "+itemdataV3);
		return 0;
	}	
	
    public int testItemTableWorker() throws Exception
    {
    	String name="abcdefgefege11";//+System.currentTimeMillis();
    	String contractName = "itemtableworker";
    	ItemTableWorker itw;
    	//if(false)
    	{
	    	itw = ItemTableWorker.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
	    	System.out.println("deploy result: " + itw.getContractAddress());
	    	contractHelper.recordAddr(contractName, itw.getContractAddress());
	    	TransactionReceipt receipt =  itw.create(name, new BigInteger(String.valueOf(System.currentTimeMillis() %10000)),"szcenter").send();
	    	List<ItemTableWorker.CreateEventEventResponse> lstEvents = itw.getCreateEventEvents(receipt);
	    	ItemTableWorker.CreateEventEventResponse response = lstEvents.get(0);
	    	System.out.println(response.memo);
    	}
    	itw = ItemTableWorker.load( contractHelper.loadAddr(contractName),web3j, credentials,  new StaticGasProvider(gasPrice, gasLimit));
    	Tuple3<BigInteger, BigInteger, byte[]> item = itw.select(name).send();
    	System.out.println(name+","+item);
    	System.out.println(new String(item.getValue3()));
    	return 0;
    }
	
	
	public static void main(String[] args) throws Exception{
	
		
		// TODO Auto-generated method stub
		TestItem tester= new TestItem();
		if(args.length >=1&&args[0].compareTo("newaccount")==0)
		{			
			AccountUtils au = new AccountUtils(TestItem.credentialsFilePath);
		   au.createAccount("alice","123456" );
		  System.out.print("create account done");
		  //tester.initialize();
		  return;
		}
		
		
		
		tester.initialize();
		if(args.length >=1)
		{
			if(args[0].compareTo("deploy") == 0)
			{
				tester.deploy();
				return ;
			}
			

		}
		if(true)
		{
		tester.testItemTableWorker();
		return ;
		}
		
		
		tester.loadWorker();
		tester.loadWorkerV2();
		tester.loadWorkerV3();
		
		int rv =(int) Math.round( Math.random() * 100000000);
	
		//tester.createItem("atest", new BigInteger(String.valueOf(rv)) );
		
		//int rv2 =(int) Math.round( Math.random() * 1000);
		//tester.createItemV2("btest",new BigInteger(String.valueOf(rv2)));
		//tester.testV1V2("V1V2test",new BigInteger(String.valueOf(9988)));
		tester.testV2V3("V2V3test", new BigInteger(String.valueOf(7766)), "shenzhen");
	}

}
