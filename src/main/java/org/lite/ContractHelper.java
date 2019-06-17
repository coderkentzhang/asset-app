package org.lite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ContractHelper {
	

	public  ContractHelper(String af)
	{
		this.addressFile = af;
	}
	String addressFile =""; 
	public void recordAddr(String name,String address) throws FileNotFoundException, IOException {
		//Resource contractResource = new ClassPathResource("contract.properties");
		File file = new File(this.addressFile);
		if(!file.exists())
		{
			file.createNewFile();
		}

		Properties prop = new Properties();
		prop.load(new FileInputStream(file));
		prop.setProperty(name, address);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		prop.store(fileOutputStream, "contract address");
		
	}
	
	public String loadAddr(String name) throws Exception {
		// load  contact address from contract.properties
		Properties prop = new Properties();
		File file = new File(addressFile);
		//prop.load(contractResource.getInputStream());
		prop.load(new FileInputStream(file));
		String contractAddress = prop.getProperty(name);
		if (contractAddress == null || contractAddress.trim().equals("")) {
			throw new Exception(" load  contract address failed, please deploy it first. ");
		}
		return contractAddress;
	}
	
}
