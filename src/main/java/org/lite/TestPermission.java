package org.lite;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

import org.fisco.bcos.web3j.crypto.CipherException;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.lite.AccountUtils.AccountEntity;

public class TestPermission {

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
	public void testDeployPermission() throws Exception
	{
		
		AccountEntity ae = null;
		ItemTableWorker itemworker = null;
		ae = alice;
		try {

			itemworker = ItemTableWorker.deploy(client.getWeb3j(), ae.credentials, client.getGasProvider()).send();
			System.out.println(ae.alias + "  deploy:" + itemworker.getContractAddress());
			contractHelper.recordAddr("itemtableworker", itemworker.getContractAddress());
			ae = bob;
			itemworker = ItemTableWorker.deploy(client.getWeb3j(), ae.credentials, client.getGasProvider()).send();
			System.out.println(ae.alias + "  deploy:" + itemworker.getContractAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testUserTablePermission() throws Exception
	{
		System.out.println("\n---now test user table permission");
		AccountEntity ae = null;
		ItemTableWorker itemworker = null;
		String workerAddress = contractHelper.loadAddr("itemtableworker");
		System.out.println ("contract address: "+workerAddress);
		ItemTableWorker itemworkerForAlice = ItemTableWorker.load(workerAddress, client.getWeb3j(), alice.credentials, client.getGasProvider());
		ItemTableWorker itemworkerForBob = ItemTableWorker.load(workerAddress, client.getWeb3j(), bob.credentials, client.getGasProvider());
		String name = "";
		BigInteger balance = new BigInteger(String.valueOf(776688));
		String city = "shenzhen";
		try
		{
			name = "alice996";
			System.out.println("\n---------alice create item");
			TransactionReceipt receipt =  itemworkerForAlice.create(name, balance, city).send();
	    	List<ItemTableWorker.CreateEventEventResponse> lstEvents = itemworkerForAlice.getCreateEventEvents(receipt);
	    	ItemTableWorker.CreateEventEventResponse response = lstEvents.get(0);
	    	
	    	System.out.println("transaction orgin:"+alice.credentials.getAddress());
	    	System.out.println(response.name+";"+response.city+";"+response.memo);
	    	Tuple3<BigInteger, BigInteger, byte[]> resTuple3 = itemworkerForAlice.select(name).send();
	    	System.out.println(resTuple3);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		try {
			name ="bob007";
			System.out.println("\n---------bob create item");
			TransactionReceipt receipt = itemworkerForBob.create(name, balance, city).send();
	    	List<ItemTableWorker.CreateEventEventResponse> lstEvents = itemworkerForBob.getCreateEventEvents(receipt);
	    	ItemTableWorker.CreateEventEventResponse response = lstEvents.get(0);
	    	System.out.println("transaction orgin:"+bob.credentials.getAddress());
	    	System.out.println(response.name+";"+response.city+";"+response.memo);
	    	Tuple3<BigInteger, BigInteger, byte[]> resTuple3 = itemworkerForAlice.select(name).send();
	    	System.out.println(resTuple3);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			TestPermission pt = new TestPermission();
			pt.client.initialize(1);
			pt.loadAcccounts();
			pt.testDeployPermission();
			pt.testUserTablePermission();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		System.exit(0);
	}

}
