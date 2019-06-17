package org.lite;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.request.BcosFilter;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
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
public class ItemWorkerV2 extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50610d82806100206000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063057744a51461005157806346f81a8714610114575b600080fd5b34801561005d57600080fd5b50610092600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061019b565b6040518080602001838152602001828103825284818151815260200191508051906020019080838360005b838110156100d85780820151818401526020810190506100bd565b50505050905090810190601f1680156101055780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b34801561012057600080fd5b50610185600480360381019080803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190505050610341565b6040518082815260200191505060405180910390f35b60606000808390508073ffffffffffffffffffffffffffffffffffffffff1663c6ea59b96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401600060405180830381600087803b15801561020757600080fd5b505af115801561021b573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f82011682018060405250602081101561024557600080fd5b81019080805164010000000081111561025d57600080fd5b8281019050602081018481111561027357600080fd5b815185600182028301116401000000008211171561029057600080fd5b50509291905050508173ffffffffffffffffffffffffffffffffffffffff16634d9b3d5d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156102fc57600080fd5b505af1158015610310573d6000803e3d6000fd5b505050506040513d602081101561032657600080fd5b81019080805190602001909291905050509250925050915091565b60008060048451101561049a577fce2c65f562fbd795e62ace3dd8fc0729914b4f73fd4c57856b41b1d526bd1c6d7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff60008686604051808581526020018473ffffffffffffffffffffffffffffffffffffffff1681526020018060200183815260200180602001838103835285818151815260200191508051906020019080838360005b838110156104005780820151818401526020810190506103e5565b50505050905090810190601f16801561042d5780820380516001836020036101000a031916815260200191505b50838103825260158152602001807f6e616d65206c656e67746820746f6f2073686f72740000000000000000000000815250602001965050505050505060405180910390a17fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff91506107af565b620f42408311156105f1577fce2c65f562fbd795e62ace3dd8fc0729914b4f73fd4c57856b41b1d526bd1c6d7ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffe60008686604051808581526020018473ffffffffffffffffffffffffffffffffffffffff1681526020018060200183815260200180602001838103835285818151815260200191508051906020019080838360005b8381101561055757808201518184015260208101905061053c565b50505050905090810190601f1680156105845780820380516001836020036101000a031916815260200191505b50838103825260108152602001807f62616c616e636520746f6f206d75636800000000000000000000000000000000815250602001965050505050505060405180910390a17ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffe91506107af565b83836105fb6107b6565b8080602001838152602001828103825284818151815260200191508051906020019080838360005b8381101561063e578082015181840152602081019050610623565b50505050905090810190601f16801561066b5780820380516001836020036101000a031916815260200191505b509350505050604051809103906000f08015801561068d573d6000803e3d6000fd5b5090507fce2c65f562fbd795e62ace3dd8fc0729914b4f73fd4c57856b41b1d526bd1c6d6000828686604051808581526020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018060200183815260200180602001838103835285818151815260200191508051906020019080838360005b8381101561073857808201518184015260208101905061071d565b50505050905090810190601f1680156107655780820380516001836020036101000a031916815260200191505b508381038252601c8152602001807f737563636573732c72656d656d62657220746865206164647265737300000000815250602001965050505050505060405180910390a1600091505b5092915050565b604051610590806107c7833901905600608060405234801561001057600080fd5b5060405161059038038061059083398101806040528101908080518201929190602001805190602001909291905050508160009080519060200190610056929190610065565b5080600181905550505061010a565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100a657805160ff19168380011785556100d4565b828001600101855582156100d4579182015b828111156100d35782518255916020019190600101906100b8565b5b5090506100e191906100e5565b5090565b61010791905b808211156101035760008160009055506001016100eb565b5090565b90565b610477806101196000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680634d9b3d5d146100675780638a42ebe914610092578063bfe1197b14610105578063c6ea59b91461019c575b600080fd5b34801561007357600080fd5b5061007c61022c565b6040518082815260200191505060405180910390f35b34801561009e57600080fd5b50610103600480360381019080803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190505050610236565b005b34801561011157600080fd5b5061011a610258565b6040518080602001838152602001828103825284818151815260200191508051906020019080838360005b83811015610160578082015181840152602081019050610145565b50505050905090810190601f16801561018d5780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b3480156101a857600080fd5b506101b1610304565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101f15780820151818401526020810190506101d6565b50505050905090810190601f16801561021e5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6000600154905090565b816000908051906020019061024c9291906103a6565b50806001819055505050565b6060600080600154818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156102f55780601f106102ca576101008083540402835291602001916102f5565b820191906000526020600020905b8154815290600101906020018083116102d857829003601f168201915b50505050509150915091509091565b606060008054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561039c5780601f106103715761010080835404028352916020019161039c565b820191906000526020600020905b81548152906001019060200180831161037f57829003601f168201915b5050505050905090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106103e757805160ff1916838001178555610415565b82800160010185558215610415579182015b828111156104145782518255916020019190600101906103f9565b5b5090506104229190610426565b5090565b61044891905b8082111561044457600081600090555060010161042c565b5090565b905600a165627a7a72305820cad06bd8e3c8b6f02fb634d341e64566cf9982ab035debc8387668bd2d0d519e0029a165627a7a72305820c2057e9facbefe069077349498d42ec0da760037eb14e1ae8a77a791331700950029";

    public static final String FUNC_GET_ITEM = "get_item";

    public static final String FUNC_CREATE = "create";

    public static final Event ITEMV2_CREATED_EVENT = new Event("itemV2_created", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected ItemWorkerV2(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ItemWorkerV2(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ItemWorkerV2(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ItemWorkerV2(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<Tuple2<String, BigInteger>> get_item(String addr) {
        final Function function = new Function(FUNC_GET_ITEM, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple2<String, BigInteger>>(
                new Callable<Tuple2<String, BigInteger>>() {
                    @Override
                    public Tuple2<String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> create(String n, BigInteger b) {
        final Function function = new Function(
                FUNC_CREATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(b)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void create(String n, BigInteger b, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(b)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public List<ItemV2_createdEventResponse> getItemV2_createdEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ITEMV2_CREATED_EVENT, transactionReceipt);
        ArrayList<ItemV2_createdEventResponse> responses = new ArrayList<ItemV2_createdEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ItemV2_createdEventResponse typedResponse = new ItemV2_createdEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.retcode = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.itemAddr = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.balance = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.memo = (String) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ItemV2_createdEventResponse> itemV2_createdEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, ItemV2_createdEventResponse>() {
            @Override
            public ItemV2_createdEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ITEMV2_CREATED_EVENT, log);
                ItemV2_createdEventResponse typedResponse = new ItemV2_createdEventResponse();
                typedResponse.log = log;
                typedResponse.retcode = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.itemAddr = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.name = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.balance = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.memo = (String) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ItemV2_createdEventResponse> itemV2_createdEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ITEMV2_CREATED_EVENT));
        return itemV2_createdEventFlowable(filter);
    }

    @Deprecated
    public static ItemWorkerV2 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ItemWorkerV2(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ItemWorkerV2 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ItemWorkerV2(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ItemWorkerV2 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ItemWorkerV2(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ItemWorkerV2 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ItemWorkerV2(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ItemWorkerV2> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ItemWorkerV2.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ItemWorkerV2> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ItemWorkerV2.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<ItemWorkerV2> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ItemWorkerV2.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ItemWorkerV2> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ItemWorkerV2.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ItemV2_createdEventResponse {
        public Log log;

        public BigInteger retcode;

        public String itemAddr;

        public String name;

        public BigInteger balance;

        public String memo;
    }
}
