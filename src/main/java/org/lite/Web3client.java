package org.lite;

import java.math.BigInteger;

import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.methods.response.BlockNumber;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Web3client {

	private static BigInteger gasPrice = new BigInteger("30000000");
	private static BigInteger gasLimit = new BigInteger("30000000");
	static Logger logger = LoggerFactory.getLogger(TestItem.class);
	private Web3j web3j;
	private int groupId = 0;
	private Service service;

	public  Web3client() {}
	public  Web3client(int groupId) throws Exception {
		
		this.initialize(groupId);
	}
	
	
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

	private Credentials credentials;
	
	public void initialize(int groupId) throws Exception {

		// init the Service
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		service = context.getBean(Service.class);
		service.run();
		ChannelEthereumService channelEthereumService = new ChannelEthereumService();
		channelEthereumService.setChannelService(service);
		web3j = Web3j.build(channelEthereumService, groupId);
		this.groupId = groupId;

	}
	
	public static void main(String[] args) throws Exception {
		Web3client client = new Web3client(1);
		BlockNumber bn = client.web3j.getBlockNumber().send();
	    System.out.println("block height: "+ bn.getBlockNumber().intValue());
	    System.exit(0);

	}
	
	public StaticGasProvider getGasProvider()
	{
		return new StaticGasProvider(gasPrice,gasLimit);
	}
	public  BigInteger getGasPrice() {
		return gasPrice;
	}
	public  void setGasPrice(BigInteger gasPrice) {
		Web3client.gasPrice = gasPrice;
	}
	public static BigInteger getGasLimit() {
		return gasLimit;
	}
	public static void setGasLimit(BigInteger gasLimit) {
		Web3client.gasLimit = gasLimit;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

}
