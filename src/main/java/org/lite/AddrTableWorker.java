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
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
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
public class AddrTableWorker extends Contract {
    private static final String BINARY = "60806040523480156200001157600080fd5b506040805190810160405280600a81526020017f745f6974656d6461746100000000000000000000000000000000000000000000815250600090805190602001906200005f92919062000230565b50620000796200007f640100000000026401000000009004565b620002df565b600061100190508073ffffffffffffffffffffffffffffffffffffffff166356004b6a60006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018060200180602001848103845285818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015620001645780601f10620001385761010080835404028352916020019162000164565b820191906000526020600020905b8154815290600101906020018083116200014657829003601f168201915b5050848103835260048152602001807f6e616d65000000000000000000000000000000000000000000000000000000008152506020018481038252600c8152602001807f62616c616e63652c636974790000000000000000000000000000000000000000815250602001945050505050602060405180830381600087803b158015620001ef57600080fd5b505af115801562000204573d6000803e3d6000fd5b505050506040513d60208110156200021b57600080fd5b81019080805190602001909291905050505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200027357805160ff1916838001178555620002a4565b82800160010185558215620002a4579182015b82811115620002a357825182559160200191906001019062000286565b5b509050620002b39190620002b7565b5090565b620002dc91905b80821115620002d8576000816000905550600101620002be565b5090565b90565b6117a980620002ef6000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806336a554941461006757806363273b161461010e5780637fa148cf1461018b578063fcd7e3c1146101b6575b600080fd5b34801561007357600080fd5b506100f8600480360381019080803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061026d565b6040518082815260200191505060405180910390f35b34801561011a57600080fd5b50610175600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610aeb565b6040518082815260200191505060405180910390f35b34801561019757600080fd5b506101a0610e4f565b6040518082815260200191505060405180910390f35b3480156101c257600080fd5b5061021d600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506110e7565b604051808481526020018381526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001935050505060405180910390f35b6000806000806000806000809550600094506000935061028b61163c565b92506102968a610aeb565b94506000851415156103d1577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff95507fcb49392692818ccbce107b9d246d4729b350f32844060c37634542a16378a860868b60008060405180858152602001806020018481526020018373ffffffffffffffffffffffffffffffffffffffff16815260200180602001838103835286818151815260200191508051906020019080838360005b8381101561035757808201518184015260208101905061033c565b50505050905090810190601f1680156103845780820380516001836020036101000a031916815260200191505b508381038252601f8152602001807f6e616d6520657869737473202c63616e27742063726561746520616761696e00815250602001965050505050505060405180910390a1859650610ade565b8273ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561043557600080fd5b505af1158015610449573d6000803e3d6000fd5b505050506040513d602081101561045f57600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663e942b5168b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260048152602001807f6e616d6500000000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610532578082015181840152602081019050610517565b50505050905090810190601f16801561055f5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561057f57600080fd5b505af1158015610593573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16632ef8ba748a6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001838152602001828103825260078152602001807f62616c616e63650000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b15801561063f57600080fd5b505af1158015610653573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16632ef8ba748973ffffffffffffffffffffffffffffffffffffffff166040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001838152602001828103825260048152602001807f636974790000000000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b15801561071557600080fd5b505af1158015610729573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff166331afac368b846040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b838110156107e85780820151818401526020810190506107cd565b50505050905090810190601f1680156108155780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15801561083557600080fd5b505af1158015610849573d6000803e3d6000fd5b505050506040513d602081101561085f57600080fd5b81019080805190602001909291905050509050600181141561099d57600095507fcb49392692818ccbce107b9d246d4729b350f32844060c37634542a16378a860868b8b8b60405180858152602001806020018481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001838103835286818151815260200191508051906020019080838360005b8381101561092657808201518184015260208101905061090b565b50505050905090810190601f1680156109535780820380516001836020036101000a031916815260200191505b508381038252600e8152602001807f6372656174652073756363657373000000000000000000000000000000000000815250602001965050505050505060405180910390a1610ada565b7ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffe95507fcb49392692818ccbce107b9d246d4729b350f32844060c37634542a16378a860818b8b8b60405180858152602001806020018481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001838103835286818151815260200191508051906020019080838360005b83811015610a67578082015181840152602081019050610a4c565b50505050905090810190601f168015610a945780820380516001836020036101000a031916815260200191505b508381038252600c8152602001807f637265617465206572726f720000000000000000000000000000000000000000815250602001965050505050505060405180910390a15b8596505b5050505050509392505050565b6000806000806000610afb61163c565b93508373ffffffffffffffffffffffffffffffffffffffff1663e8434e39878673ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610b7e57600080fd5b505af1158015610b92573d6000803e3d6000fd5b505050506040513d6020811015610ba857600080fd5b81019080805190602001909291905050506040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015610c56578082015181840152602081019050610c3b565b50505050905090810190601f168015610c835780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b158015610ca357600080fd5b505af1158015610cb7573d6000803e3d6000fd5b505050506040513d6020811015610ccd57600080fd5b81019080805190602001909291905050509250600091508273ffffffffffffffffffffffffffffffffffffffff1663949d225d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610d4857600080fd5b505af1158015610d5c573d6000803e3d6000fd5b505050506040513d6020811015610d7257600080fd5b810190808051906020019092919050505060001415610d945760009450610e46565b8273ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b158015610e0457600080fd5b505af1158015610e18573d6000803e3d6000fd5b505050506040513d6020811015610e2e57600080fd5b81019080805190602001909291905050509050600194505b50505050919050565b600073156dff526b422b17c4f576e6c0b243179eaa840773ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610fe0577f028c2af0b0baef8c65aa9661b227dcb9346c7ea1913cf954d43adc4fdd12c6e77fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff3332604051808481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018281038252603a8152602001807f6e6f74207468652061646472657373203078313536646666353236623432326281526020017f313763346635373665366330623234333137396561613834303700000000000081525060400194505050505060405180910390a17fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff90506110e4565b7f028c2af0b0baef8c65aa9661b227dcb9346c7ea1913cf954d43adc4fdd12c6e760003332604051808481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018281038252603c8152602001807f6d6174636820746865206164647265737320307831353664666635323662343281526020017f326231376334663537366536633062323433313739656161383430370000000081525060400194505050505060405180910390a1600090505b90565b60008060008060008060006110fa61163c565b93508373ffffffffffffffffffffffffffffffffffffffff1663e8434e39898673ffffffffffffffffffffffffffffffffffffffff16637857d7c96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561117d57600080fd5b505af1158015611191573d6000803e3d6000fd5b505050506040513d60208110156111a757600080fd5b81019080805190602001909291905050506040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b8381101561125557808201518184015260208101905061123a565b50505050905090810190601f1680156112825780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b1580156112a257600080fd5b505af11580156112b6573d6000803e3d6000fd5b505050506040513d60208110156112cc57600080fd5b810190808051906020019092919050505092508273ffffffffffffffffffffffffffffffffffffffff1663949d225d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561134357600080fd5b505af1158015611357573d6000803e3d6000fd5b505050506040513d602081101561136d57600080fd5b8101908080519060200190929190505050600014156113be577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff600080829250819150809050965096509650611631565b8273ffffffffffffffffffffffffffffffffffffffff1663846719e060006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561142e57600080fd5b505af1158015611442573d6000803e3d6000fd5b505050506040513d602081101561145857600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663fda69fae6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260048152602001807f6369747900000000000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b15801561150b57600080fd5b505af115801561151f573d6000803e3d6000fd5b505050506040513d602081101561153557600080fd5b810190808051906020019092919050505090506103e78273ffffffffffffffffffffffffffffffffffffffff1663fda69fae6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260078152602001807f62616c616e636500000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b1580156115eb57600080fd5b505af11580156115ff573d6000803e3d6000fd5b505050506040513d602081101561161557600080fd5b8101908080519060200190929190505050828292509650965096505b505050509193909250565b600080600061100191508173ffffffffffffffffffffffffffffffffffffffff1663f23f63c960006040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018281038252838181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156117185780601f106116ed57610100808354040283529160200191611718565b820191906000526020600020905b8154815290600101906020018083116116fb57829003601f168201915b505092505050602060405180830381600087803b15801561173857600080fd5b505af115801561174c573d6000803e3d6000fd5b505050506040513d602081101561176257600080fd5b810190808051906020019092919050505090508092505050905600a165627a7a72305820479d2feb5c1d9e662792fca2b2bbf0da2452b0e2a17a8c04582b26debefda8c70029";

    public static final String FUNC_CREATE = "create";

    public static final String FUNC_IS_EXIST = "is_exist";

    public static final String FUNC_DECTECTADDR = "dectectAddr";

    public static final String FUNC_SELECT = "select";

    public static final Event PERMISSIONEVENT_EVENT = new Event("permissionEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event CREATEEVENT_EVENT = new Event("createEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected AddrTableWorker(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AddrTableWorker(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected AddrTableWorker(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected AddrTableWorker(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> create(String name, BigInteger balance, String city) {
        final Function function = new Function(
                FUNC_CREATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(balance), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(city)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void create(String name, BigInteger balance, String city, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(balance), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(city)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public RemoteCall<BigInteger> is_exist(String name) {
        final Function function = new Function(FUNC_IS_EXIST, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> dectectAddr() {
        final Function function = new Function(
                FUNC_DECTECTADDR, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void dectectAddr(TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_DECTECTADDR, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public RemoteCall<Tuple3<BigInteger, BigInteger, String>> select(String name) {
        final Function function = new Function(FUNC_SELECT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(name)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple3<BigInteger, BigInteger, String>>(
                new Callable<Tuple3<BigInteger, BigInteger, String>>() {
                    @Override
                    public Tuple3<BigInteger, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue());
                    }
                });
    }

    public List<PermissionEventEventResponse> getPermissionEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PERMISSIONEVENT_EVENT, transactionReceipt);
        ArrayList<PermissionEventEventResponse> responses = new ArrayList<PermissionEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PermissionEventEventResponse typedResponse = new PermissionEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.orgin = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.memo = (String) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PermissionEventEventResponse> permissionEventEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, PermissionEventEventResponse>() {
            @Override
            public PermissionEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PERMISSIONEVENT_EVENT, log);
                PermissionEventEventResponse typedResponse = new PermissionEventEventResponse();
                typedResponse.log = log;
                typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.orgin = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.memo = (String) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PermissionEventEventResponse> permissionEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PERMISSIONEVENT_EVENT));
        return permissionEventEventFlowable(filter);
    }

    public List<CreateEventEventResponse> getCreateEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CREATEEVENT_EVENT, transactionReceipt);
        ArrayList<CreateEventEventResponse> responses = new ArrayList<CreateEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CreateEventEventResponse typedResponse = new CreateEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.balance = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.city = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.memo = (String) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CreateEventEventResponse> createEventEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, CreateEventEventResponse>() {
            @Override
            public CreateEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CREATEEVENT_EVENT, log);
                CreateEventEventResponse typedResponse = new CreateEventEventResponse();
                typedResponse.log = log;
                typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.balance = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.city = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.memo = (String) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CreateEventEventResponse> createEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CREATEEVENT_EVENT));
        return createEventEventFlowable(filter);
    }

    @Deprecated
    public static AddrTableWorker load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AddrTableWorker(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static AddrTableWorker load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AddrTableWorker(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static AddrTableWorker load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new AddrTableWorker(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AddrTableWorker load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new AddrTableWorker(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<AddrTableWorker> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AddrTableWorker.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<AddrTableWorker> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(AddrTableWorker.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AddrTableWorker> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AddrTableWorker.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<AddrTableWorker> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AddrTableWorker.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class PermissionEventEventResponse {
        public Log log;

        public BigInteger ret;

        public String sender;

        public String orgin;

        public String memo;
    }

    public static class CreateEventEventResponse {
        public Log log;

        public BigInteger ret;

        public String name;

        public BigInteger balance;

        public String city;

        public String memo;
    }
}
