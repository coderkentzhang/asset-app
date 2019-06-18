package org.lite;

import java.io.File;
import java.io.IOException;

import java.nio.file.StandardCopyOption;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;

import org.fisco.bcos.web3j.crypto.CipherException;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.WalletUtils;

import com.alibaba.druid.util.Base64;
import com.google.common.io.Files;

import io.netty.handler.codec.base64.Base64Encoder;
import jnr.ffi.Struct.int16_t;

/*create /load / manager accounts 
 * store in keystore format , user's password protection
 *  * */

public class AccountUtils {
	
	public class AccountEntity
	{
		public String alias="";
		public Credentials credentials;
		public String toDetail()
		{
			StringBuffer sb = new StringBuffer();
			sb.append("alias : ").append(alias).append(" ; ");
			sb.append("address: ").append( credentials.getAddress()).append("\n");
			sb.append("pubkey: " ).append( credentials.getEcKeyPair().getPublicKey().toString(16)).append("\n");
			sb.append("privkey: " ).append( credentials.getEcKeyPair().getPrivateKey().toString(16));
			return sb.toString();
		}
	}
	
	/*a disk path for store keystore files
	 * eg: in windows: d:/blockchain/accounts;
	 *     in linux :  /usr/local/accounts
	 *      or others,depends on users env
	 * */
	String keystoreFilePath = "";
	private HashMap<String , AccountEntity> accounts = new HashMap<String , AccountEntity>();
	
    /*must construct by a path*/
	public AccountUtils(String keystoreFilePath)
	{
		this.keystoreFilePath = keystoreFilePath;
	}

    public String getKeystoreFilePath() {
		return keystoreFilePath;
	}
	public void setKeystoreFilePath(String keystoreFilePath) {
		this.keystoreFilePath = keystoreFilePath;
	}

     /*account mapping functions*/
	public void register(AccountEntity account)
	{
		this.accounts.put(account.alias,account);
	}
	public AccountEntity getAccount(String alias)
	{
		return accounts.get(alias);
	}

	 /*create account and register to mapping*/
   public AccountEntity createAccount(String alias,String password,boolean register) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException, IOException
   {
	   String filename = alias+".keystore";
	   AccountEntity account = new AccountEntity();
	   account.alias = alias;
	   account.credentials = this.createKeystore(filename, password);
	   if(register) {
		   this.register(account);
	   }
	   return account;
   }
   
   public AccountEntity createAccount(String alias,String password) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException, IOException
   {
	   return this.createAccount(alias, password,true);
   }
	
   /*load account from keystore*/
   public AccountEntity loadAccount(String alias,String password,boolean register) throws IOException, CipherException
   {
	   String filePath = this.keystoreFilePath+"/"+alias+".keystore";
	   Credentials credentials = WalletUtils.loadCredentials(password, filePath);
	   AccountEntity ai = new AccountEntity();
	   ai.alias = alias;
	   ai.credentials =credentials;
	   if(register) {
		   this.register(ai);
	   }
	   return ai;
   }
   
   public AccountEntity loadAccount(String alias,String password) throws IOException, CipherException
   {
	   return this.loadAccount(alias, password,true);
   }
   
   /*only use this for all account use the SAME password
    * or input null password to print all accounts on disk
    * */
   public int loadAll(String password) throws IOException, CipherException
   {
	   File file=new File(this.keystoreFilePath);
	   int counter = 0;
       for(File temp:file.listFiles()){
         
           String fileName = temp.getName();
           if(fileName.endsWith(".keystore"))
           {
        	   counter ++;
    		   String alias = fileName.substring(0,fileName.lastIndexOf("."));
        	   if(password !=null)
        	   {
           		   AccountEntity account = this.loadAccount(alias, password);
        		   this.register(account);
        	   }else {
        		   //only print the alias
        		   System.out.println("account : " +alias);
        	   }
           }
       }
       return counter;
   }
   
   /*create a keystore in spec path,if file exist ,just return exists account*/
	public Credentials createKeystore(String filename,String password) 
			throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException, IOException
	{
		String filePath = keystoreFilePath;
		String resultFile = filePath+"/"+filename;
		File f = new File(resultFile);
		if(f.exists())
		{
			Credentials credentials = WalletUtils.loadCredentials(password, resultFile);
			System.out.println("File exist:"+f.getAbsolutePath());
			return credentials;
		}
		
		File fp = new File(filePath);
		if(!fp.exists())
		{
			fp.mkdirs();
		}

		//create a new walletFile
		String walletFile = WalletUtils.generateNewWalletFile(password,fp,false);
		//move to the simple filename,easy to remember
		
		Files.move(new File(filePath+"/"+walletFile), new File(resultFile));
		Credentials credentials = WalletUtils.loadCredentials(password, resultFile);
		//Credentials credentials = WalletUtils.loadCredentials(password, resultFile);
		//System.out.println("address: "+ credentials.getAddress());
		//System.out.println("pubkey: " + credentials.getEcKeyPair().getPublicKey().toString(16));
		//System.out.println("privkey: " + credentials.getEcKeyPair().getPrivateKey().toString(16));
		return credentials;
	}
	
	
	public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException, IOException {
		
		String keyString = "00d9142aa30ff9896267ac85eaa73feb90a56b658835b8a8fb08c4d60fd270e628";
		System.out.println(keyString);
	
		Credentials credentials = Credentials.create(keyString);
		
		System.out.println("addr:"+credentials.getAddress());
		System.out.println("pubkey:"+credentials.getEcKeyPair().getPublicKey().toString(16));
		System.out.println("privkey:"+credentials.getEcKeyPair().getPrivateKey().toString(16));

		AccountUtils au = new AccountUtils( "d:/blockchain/accounts");
		AccountEntity ac = au.loadAccount("pyaccount", "123456");
		if(ac!=null||true)
		{
			System.out.println("pyaccount:"+ac.toDetail());
			System.exit(0);
		}
		AccountEntity account;
		account = au.createAccount("alice", "a123456");
		account = au.createAccount("bob", "b123456");
		account = au.createAccount("carl", "c123456");
		account = au.createAccount("kent", "k123456");
		
		System.out.println("\nLIST ALL\n");
		for (String key : au.accounts.keySet()) {
			account = au.accounts.get(key);
			System.out.println(account.toDetail());
		}
		
		System.out.println("\nLOAD one by one\n");
		account = au.loadAccount("alice", "a123456");
		System.out.println(account.toDetail());

		account = au.loadAccount("bob", "b123456");
		System.out.println(account.toDetail());
		
		account = au.loadAccount("carl", "c123456");
		System.out.println(account.toDetail());
		//account = au.loadAccount("carl", "c1234567");
		//System.out.println(account.toDetail());
		System.out.println("\nLOAD all\n");
		au.loadAll(null);
	}

}
