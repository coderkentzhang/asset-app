package org.lite;
import java.io.IOException;
import java.math.BigInteger;

import org.fisco.bcos.web3j.crypto.CipherException;
import org.fisco.bcos.web3j.crypto.ExtendedRawTransaction;
import org.fisco.bcos.web3j.crypto.ExtendedTransactionEncoder;
import org.fisco.bcos.web3j.crypto.RawTransaction;
import org.fisco.bcos.web3j.rlp.RlpString;
import org.fisco.bcos.web3j.utils.ByteUtil;
import org.lite.AccountUtils;
import org.lite.AccountUtils.AccountEntity;

import com.alibaba.druid.sql.visitor.functions.Length;
public class TestTxCodec {

	private static BigInteger gasPrice = new BigInteger("30000000");
	private static BigInteger gasLimit = new BigInteger("30000000");
	
	public static void main(String[] args) throws IOException, CipherException {
		// TODO Auto-generated method stub
		AccountUtils au = new AccountUtils("d://blockchain//accounts");
		AccountEntity ae = au.loadAccount("pyaccount", "123456");
		System.out.println(ae.toDetail());
        BigInteger randomid = new BigInteger("500");
        BigInteger blockLimit = new BigInteger("501");

        
        /*
  	  public static ExtendedRawTransaction createTransaction(
            BigInteger randomid,
            BigInteger gasPrice,
            BigInteger gasLimit,
            BigInteger blockLimit,
            String to,
            BigInteger value,
            String data,
            BigInteger chainId,
            BigInteger groupId,
            String extraData) 
         * */
        String to ="";
        String data = "abcdefg";
        BigInteger chainId = new BigInteger("1");
        BigInteger groupId = new BigInteger("1");
        String extraData = "";
        ExtendedRawTransaction tx = ExtendedRawTransaction.createTransaction(
                randomid, 
                gasPrice,
                gasLimit, 
                blockLimit,
                to,
                BigInteger.ZERO, /*value not use*/
                data,
                chainId,
                groupId,
                extraData
        		);
        byte[] encodeResult =  ExtendedTransactionEncoder.encode(tx);
        System.out.println("encodelen: " +encodeResult.length);
        System.out.println("encode Result:"+ByteUtil.toHexString(encodeResult));
        byte[] signResult =ExtendedTransactionEncoder.signMessage(tx, ae.credentials);
        System.out.println("sign Result:"+ByteUtil.toHexString(signResult));
        
        RlpString rlpstr =  RlpString.create(groupId);
        System.out.println(rlpstr.getBytes().length);

		
	}

}
