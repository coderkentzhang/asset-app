package org.lite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.List;

import org.fisco.bcos.web3j.crypto.CipherException;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.lite.AccountUtils.AccountEntity;
import org.lite.AddrTableWorker.PermissionEventEventResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestAddr {

	AccountUtils au = new AccountUtils("d:/blockchain/accounts");
	Web3client client = new Web3client();
	AccountEntity alice ,bob,carl,kent;
	
	public void loadAcccounts() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException, IOException
	{
		alice = au.createAccount("alice","a123456");
		System.out.println(alice.toDetail());
		bob = au.createAccount("bob","b123456");
		System.out.println(bob.toDetail());
		carl = au.createAccount("carl","c123456");
		System.out.println(carl.toDetail());
		kent = au.createAccount("kent","k123456");
		System.out.println(kent.toDetail());
	
	}
	ContractHelper contractHelper = new ContractHelper("d:/blockchain/contracts.txt");
	public void testDeploy() throws Exception
	{
		
		AccountEntity ae = null;
		AddrTableWorker worker = null;
		ae = alice;
		try {

			worker = AddrTableWorker.deploy(client.getWeb3j(), ae.credentials, client.getGasProvider()).send();
			System.out.println(ae.alias + "  deploy:" + worker.getContractAddress());
			contractHelper.recordAddr("AddrTableWorker", worker.getContractAddress());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testUserTablePermission() throws Exception
	{
		System.out.println("\n---now test user table permission");
		AccountEntity ae = null;
		AddrTableWorker worker = null;
		String workerAddress = contractHelper.loadAddr("AddrTableWorker");
		System.out.println ("contract address: "+workerAddress);
		AddrTableWorker workerForAlice = AddrTableWorker.load(workerAddress, client.getWeb3j(), alice.credentials, client.getGasProvider());
		AddrTableWorker workerForBob = AddrTableWorker.load(workerAddress, client.getWeb3j(), bob.credentials, client.getGasProvider());
		String name = "";
		BigInteger balance = new BigInteger(String.valueOf(776688));
		String city = "shenzhen";
		
		try
		{
			System.out.println("\n---------alice check permission");
			TransactionReceipt receipt = workerForAlice.dectectAddr().send();
			
			List<PermissionEventEventResponse> lstEvents = workerForAlice.getPermissionEventEvents(receipt);
	    	System.out.println(receipt.getOutput());
			PermissionEventEventResponse response = lstEvents.get(0);
			System.out.println("retcode : " + response.ret+",sender: "+response.sender+",origin: "+response.orgin+",memo: "+response.memo);
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			System.out.println("\n---------bob check permission");
			TransactionReceipt receipt = workerForBob.dectectAddr().send();
			List<PermissionEventEventResponse> lstEvents = workerForBob.getPermissionEventEvents(receipt);
			PermissionEventEventResponse response = lstEvents.get(0);
			System.out.println("retcode : " + response.ret+",sender: "+response.sender+",origin: "+response.orgin+",memo: "+response.memo);
			return ;
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		
		try
		{
			name = "alice996";
			System.out.println("\n---------alice create item");
			TransactionReceipt receipt =  workerForAlice.create(name, balance, city).send();
	    	List<AddrTableWorker.CreateEventEventResponse> lstEvents = workerForAlice.getCreateEventEvents(receipt);
	    	AddrTableWorker.CreateEventEventResponse response = lstEvents.get(0);
	
	    	System.out.println("transaction orgin:"+alice.credentials.getAddress());
	    	System.out.println(response.name+";"+response.city+";"+response.memo);
	    	Tuple3<BigInteger, BigInteger, String> resTuple3 = workerForAlice.select(name).send();
	    	System.out.println(resTuple3);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		try {
			name ="bob007";
			System.out.println("\n---------bob create item");
			TransactionReceipt receipt = workerForBob.create(name, balance, city).send();
	    	List<AddrTableWorker.CreateEventEventResponse> lstEvents = workerForBob.getCreateEventEvents(receipt);
	    	AddrTableWorker.CreateEventEventResponse response = lstEvents.get(0);
	    	System.out.println("transaction orgin:"+bob.credentials.getAddress());
	    	System.out.println(response.name+";"+response.city+";"+response.memo);
	    	Tuple3<BigInteger, BigInteger, String> resTuple3 = workerForAlice.select(name).send();
	    	System.out.println(resTuple3);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static  String loadJson(String filename) throws IOException
	{
	     File f = new File(filename);
	     InputStreamReader reader = new InputStreamReader(  
                new FileInputStream(f)); // 建立一个输入流对象reader  
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
        String line = "";  
        line = br.readLine();  
        StringBuffer sb = new StringBuffer();
        
        while (line != null) {
       	 sb.append(line).append("\n");
            line = br.readLine(); // 一次读入一行数据  
        }  
        
        return sb.toString();
	}
	public static void jsonTest() throws JsonParseException, JsonMappingException, IOException
	{
		String jsonstr = "[ {\"type\": \"int256\", \"data\": \"99\"},{\"type\": \"string\",\"data\": \"success\"}]";
		
		ContractDecoderEntity cde =  new ContractDecoderEntity(jsonstr);
		System.out.println(cde.listToString());
		
		
		jsonstr = loadJson("d:/blockchain/jsontest.json");
		cde =  new ContractDecoderEntity(jsonstr);
		System.out.println(cde.listToString());
		System.out.println(cde.entity_list.get(0).dataAsInt()); 
         
	   
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if(true)
		{
			jsonTest();
			return;
		}
		try
		{
			TestAddr pt = new TestAddr();
			pt.client.initialize(1);
			pt.loadAcccounts();
			pt.testDeploy();
			pt.testUserTablePermission();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		System.exit(0);
	}

}
