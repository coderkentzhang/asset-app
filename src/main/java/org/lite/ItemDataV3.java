package org.lite;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple4;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class ItemDataV3 extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50604051610958380380610958833981018060405281019080805182019291906020018051906020019092919080518201929190505050826000908051906020019061005d9291906100c6565b5081600281905550806001908051906020019061007b9291906100c6565b506000600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050505061016b565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061010757805160ff1916838001178555610135565b82800160010185558215610135579182015b82811115610134578251825591602001919060010190610119565b5b5090506101429190610146565b5090565b61016891905b8082111561016457600081600090555060010161014c565b5090565b90565b6107de8061017a6000396000f30060806040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806321eaed3314610072578063496cf0e91461014b5780634d9b3d5d146101db578063bfe1197b14610206578063c6ea59b91461033c575b600080fd5b34801561007e57600080fd5b50610149600480360381019080803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506103cc565b005b34801561015757600080fd5b50610160610448565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101a0578082015181840152602081019050610185565b50505050905090810190601f1680156101cd5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156101e757600080fd5b506101f06104ea565b6040518082815260200191505060405180910390f35b34801561021257600080fd5b5061021b6104f4565b6040518080602001858152602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838103835287818151815260200191508051906020019080838360005b8381101561029757808201518184015260208101905061027c565b50505050905090810190601f1680156102c45780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b838110156102fd5780820151818401526020810190506102e2565b50505050905090810190601f16801561032a5780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b34801561034857600080fd5b5061035161066b565b6040518080602001828103825283818151815260200191508051906020019080838360005b83811015610391578082015181840152602081019050610376565b50505050905090810190601f1680156103be5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b83600090805190602001906103e292919061070d565b5082600281905550816001908051906020019061040092919061070d565b5080600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050505050565b606060018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104e05780601f106104b5576101008083540402835291602001916104e0565b820191906000526020600020905b8154815290600101906020018083116104c357829003601f168201915b5050505050905090565b6000600254905090565b6060600060606000806002546001600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16838054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105ba5780601f1061058f576101008083540402835291602001916105ba565b820191906000526020600020905b81548152906001019060200180831161059d57829003601f168201915b50505050509350818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106565780601f1061062b57610100808354040283529160200191610656565b820191906000526020600020905b81548152906001019060200180831161063957829003601f168201915b50505050509150935093509350935090919293565b606060008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107035780601f106106d857610100808354040283529160200191610703565b820191906000526020600020905b8154815290600101906020018083116106e657829003601f168201915b5050505050905090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061074e57805160ff191683800117855561077c565b8280016001018555821561077c579182015b8281111561077b578251825591602001919060010190610760565b5b509050610789919061078d565b5090565b6107af91905b808211156107ab576000816000905550600101610793565b5090565b905600a165627a7a72305820c96e1daba7841f7f53c6d645a2c3dde9d4d2de385b6047110546c670e274c3060029";

    public static final String FUNC_SET = "set";

    public static final String FUNC_GETCITY = "getcity";

    public static final String FUNC_GETBALANCE = "getbalance";

    public static final String FUNC_GET_ITEM = "get_item";

    public static final String FUNC_GETNAME = "getname";

    @Deprecated
    protected ItemDataV3(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ItemDataV3(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ItemDataV3(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ItemDataV3(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> set(String n, BigInteger b, String c, String addrV1) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(b), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(c), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(addrV1)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void set(String n, BigInteger b, String c, String addrV1, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(b), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(c), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(addrV1)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public RemoteCall<String> getcity() {
        final Function function = new Function(FUNC_GETCITY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getbalance() {
        final Function function = new Function(FUNC_GETBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple4<String, BigInteger, String, String>> get_item() {
        final Function function = new Function(FUNC_GET_ITEM, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple4<String, BigInteger, String, String>>(
                new Callable<Tuple4<String, BigInteger, String, String>>() {
                    @Override
                    public Tuple4<String, BigInteger, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, BigInteger, String, String>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<String> getname() {
        final Function function = new Function(FUNC_GETNAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static ItemDataV3 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ItemDataV3(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ItemDataV3 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ItemDataV3(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ItemDataV3 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ItemDataV3(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ItemDataV3 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ItemDataV3(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ItemDataV3> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String n, BigInteger b, String c) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(b), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(c)));
        return deployRemoteCall(ItemDataV3.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<ItemDataV3> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String n, BigInteger b, String c) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(b), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(c)));
        return deployRemoteCall(ItemDataV3.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ItemDataV3> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String n, BigInteger b, String c) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(b), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(c)));
        return deployRemoteCall(ItemDataV3.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ItemDataV3> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String n, BigInteger b, String c) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(b), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(c)));
        return deployRemoteCall(ItemDataV3.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
