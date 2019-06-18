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
public class ItemWorkerV3 extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50611c2a806100206000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063057744a51461006757806307573aed146101c957806333d7e51f14610240578063e257a371146102dd575b600080fd5b34801561007357600080fd5b506100a8600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506103aa565b6040518080602001858152602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838103835287818151815260200191508051906020019080838360005b83811015610124578082015181840152602081019050610109565b50505050905090810190601f1680156101515780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b8381101561018a57808201518184015260208101905061016f565b50505050905090810190601f1680156101b75780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b3480156101d557600080fd5b5061022a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610521565b6040518082815260200191505060405180910390f35b34801561024c57600080fd5b506102c7600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610b7c565b6040518082815260200191505060405180910390f35b3480156102e957600080fd5b50610394600480360381019080803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610c46565b6040518082815260200191505060405180910390f35b6060600060606000808590508073ffffffffffffffffffffffffffffffffffffffff1663bfe1197b6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401600060405180830381600087803b15801561041a57600080fd5b505af115801561042e573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f82011682018060405250608081101561045857600080fd5b81019080805164010000000081111561047057600080fd5b8281019050602081018481111561048657600080fd5b81518560018202830111640100000000821117156104a357600080fd5b50509291906020018051906020019092919080516401000000008111156104c957600080fd5b828101905060208101848111156104df57600080fd5b81518560018202830111640100000000821117156104fc57600080fd5b5050929190602001805190602001909291905050509450945094509450509193509193565b600080839050600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16141561067c577f9e276b8f35e2ac8e6c066c530a711edcc719fbff69372e56ca9a7c6c09695c0f7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8585604051808481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825260158152602001807f6974656d64617461205631206e6f7420666f756e64000000000000000000000081525060200194505050505060405180910390a17fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff9150610b75565b8273ffffffffffffffffffffffffffffffffffffffff166321eaed338273ffffffffffffffffffffffffffffffffffffffff1663c6ea59b96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401600060405180830381600087803b1580156106fc57600080fd5b505af1158015610710573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f82011682018060405250602081101561073a57600080fd5b81019080805164010000000081111561075257600080fd5b8281019050602081018481111561076857600080fd5b815185600182028301116401000000008211171561078557600080fd5b50509291905050508373ffffffffffffffffffffffffffffffffffffffff16634d9b3d5d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156107f157600080fd5b505af1158015610805573d6000803e3d6000fd5b505050506040513d602081101561081b57600080fd5b81019080805190602001909291905050508673ffffffffffffffffffffffffffffffffffffffff1663496cf0e96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401600060405180830381600087803b15801561089057600080fd5b505af11580156108a4573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f8201168201806040525060208110156108ce57600080fd5b8101908080516401000000008111156108e657600080fd5b828101905060208101848111156108fc57600080fd5b815185600182028301116401000000008211171561091957600080fd5b5050929190505050886040518563ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001858152602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838103835287818151815260200191508051906020019080838360005b838110156109c95780820151818401526020810190506109ae565b50505050905090810190601f1680156109f65780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b83811015610a2f578082015181840152602081019050610a14565b50505050905090810190601f168015610a5c5780820380516001836020036101000a031916815260200191505b509650505050505050600060405180830381600087803b158015610a7f57600080fd5b505af1158015610a93573d6000803e3d6000fd5b505050507f9e276b8f35e2ac8e6c066c530a711edcc719fbff69372e56ca9a7c6c09695c0f60008585604051808481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825260118152602001807f6d6967726174696f6e20563320646f6e6500000000000000000000000000000081525060200194505050505060405180910390a1600091505b5092915050565b600080600083610b8a611296565b808060200184815260200180602001838103835260008152602001602001838103825284818151815260200191508051906020019080838360005b83811015610be0578082015181840152602081019050610bc5565b50505050905090810190601f168015610c0d5780820380516001836020036101000a031916815260200191505b50945050505050604051809103906000f080158015610c30573d6000803e3d6000fd5b509050610c3d8482610521565b91505092915050565b600080600485511015610e0c577f7525a62a0742b4be13b06ca0acbb826d56b6591061751fd4180a0c18a5c641bb7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff6000878787604051808681526020018573ffffffffffffffffffffffffffffffffffffffff168152602001806020018481526020018060200180602001848103845287818151815260200191508051906020019080838360005b83811015610d0a578082015181840152602081019050610cef565b50505050905090810190601f168015610d375780820380516001836020036101000a031916815260200191505b50848103835285818151815260200191508051906020019080838360005b83811015610d70578082015181840152602081019050610d55565b50505050905090810190601f168015610d9d5780820380516001836020036101000a031916815260200191505b50848103825260158152602001807f6e616d65206c656e67746820746f6f2073686f727400000000000000000000008152506020019850505050505050505060405180910390a17fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff915061128e565b620f4240841115610fd0577f7525a62a0742b4be13b06ca0acbb826d56b6591061751fd4180a0c18a5c641bb7ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffe6000878787604051808681526020018573ffffffffffffffffffffffffffffffffffffffff168152602001806020018481526020018060200180602001848103845287818151815260200191508051906020019080838360005b83811015610ece578082015181840152602081019050610eb3565b50505050905090810190601f168015610efb5780820380516001836020036101000a031916815260200191505b50848103835285818151815260200191508051906020019080838360005b83811015610f34578082015181840152602081019050610f19565b50505050905090810190601f168015610f615780820380516001836020036101000a031916815260200191505b50848103825260108152602001807f62616c616e636520746f6f206d756368000000000000000000000000000000008152506020019850505050505050505060405180910390a17ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffe915061128e565b848484610fdb611296565b808060200184815260200180602001838103835286818151815260200191508051906020019080838360005b83811015611022578082015181840152602081019050611007565b50505050905090810190601f16801561104f5780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b8381101561108857808201518184015260208101905061106d565b50505050905090810190601f1680156110b55780820380516001836020036101000a031916815260200191505b5095505050505050604051809103906000f0801580156110d9573d6000803e3d6000fd5b5090507f7525a62a0742b4be13b06ca0acbb826d56b6591061751fd4180a0c18a5c641bb600082878787604051808681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018481526020018060200180602001848103845287818151815260200191508051906020019080838360005b8381101561118957808201518184015260208101905061116e565b50505050905090810190601f1680156111b65780820380516001836020036101000a031916815260200191505b50848103835285818151815260200191508051906020019080838360005b838110156111ef5780820151818401526020810190506111d4565b50505050905090810190601f16801561121c5780820380516001836020036101000a031916815260200191505b508481038252602a8152602001807f6974656d56332063726561746520737563636573732c72656d656d626572207481526020017f68652061646472657373000000000000000000000000000000000000000000008152506040019850505050505050505060405180910390a1600091505b509392505050565b604051610958806112a7833901905600608060405234801561001057600080fd5b50604051610958380380610958833981018060405281019080805182019291906020018051906020019092919080518201929190505050826000908051906020019061005d9291906100c6565b5081600281905550806001908051906020019061007b9291906100c6565b506000600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050505061016b565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061010757805160ff1916838001178555610135565b82800160010185558215610135579182015b82811115610134578251825591602001919060010190610119565b5b5090506101429190610146565b5090565b61016891905b8082111561016457600081600090555060010161014c565b5090565b90565b6107de8061017a6000396000f30060806040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806321eaed3314610072578063496cf0e91461014b5780634d9b3d5d146101db578063bfe1197b14610206578063c6ea59b91461033c575b600080fd5b34801561007e57600080fd5b50610149600480360381019080803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506103cc565b005b34801561015757600080fd5b50610160610448565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101a0578082015181840152602081019050610185565b50505050905090810190601f1680156101cd5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156101e757600080fd5b506101f06104ea565b6040518082815260200191505060405180910390f35b34801561021257600080fd5b5061021b6104f4565b6040518080602001858152602001806020018473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838103835287818151815260200191508051906020019080838360005b8381101561029757808201518184015260208101905061027c565b50505050905090810190601f1680156102c45780820380516001836020036101000a031916815260200191505b50838103825285818151815260200191508051906020019080838360005b838110156102fd5780820151818401526020810190506102e2565b50505050905090810190601f16801561032a5780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b34801561034857600080fd5b5061035161066b565b6040518080602001828103825283818151815260200191508051906020019080838360005b83811015610391578082015181840152602081019050610376565b50505050905090810190601f1680156103be5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b83600090805190602001906103e292919061070d565b5082600281905550816001908051906020019061040092919061070d565b5080600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050505050565b606060018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104e05780601f106104b5576101008083540402835291602001916104e0565b820191906000526020600020905b8154815290600101906020018083116104c357829003601f168201915b5050505050905090565b6000600254905090565b6060600060606000806002546001600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16838054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105ba5780601f1061058f576101008083540402835291602001916105ba565b820191906000526020600020905b81548152906001019060200180831161059d57829003601f168201915b50505050509350818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106565780601f1061062b57610100808354040283529160200191610656565b820191906000526020600020905b81548152906001019060200180831161063957829003601f168201915b50505050509150935093509350935090919293565b606060008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107035780601f106106d857610100808354040283529160200191610703565b820191906000526020600020905b8154815290600101906020018083116106e657829003601f168201915b5050505050905090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061074e57805160ff191683800117855561077c565b8280016001018555821561077c579182015b8281111561077b578251825591602001919060010190610760565b5b509050610789919061078d565b5090565b6107af91905b808211156107ab576000816000905550600101610793565b5090565b905600a165627a7a723058209d7c89d0ac364626f6bb1ff705af8dae88e97753e97846901cacbe02a4df44010029a165627a7a72305820b95fdef9019361a376491223c4eba563c42efcb34701584223f30150b892fa590029";

    public static final String FUNC_GET_ITEM = "get_item";

    public static final String FUNC_MIGRATEITEM = "migrateItem";

    public static final String FUNC_CREATE_BYITEMV1 = "create_byitemV1";

    public static final String FUNC_CREATE = "create";

    public static final Event ITEMV3_CREATED_EVENT = new Event("itemV3_created", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event ITEMV3_MIGRATED_EVENT = new Event("itemV3_migrated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected ItemWorkerV3(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ItemWorkerV3(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ItemWorkerV3(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ItemWorkerV3(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<Tuple4<String, BigInteger, String, String>> get_item(String addr) {
        final Function function = new Function(FUNC_GET_ITEM, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addr)), 
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

    public RemoteCall<TransactionReceipt> migrateItem(String addrV1, String itemV3) {
        final Function function = new Function(
                FUNC_MIGRATEITEM, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addrV1), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(itemV3)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void migrateItem(String addrV1, String itemV3, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_MIGRATEITEM, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addrV1), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(itemV3)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public RemoteCall<TransactionReceipt> create_byitemV1(String addrV1, String c) {
        final Function function = new Function(
                FUNC_CREATE_BYITEMV1, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addrV1), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(c)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void create_byitemV1(String addrV1, String c, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATE_BYITEMV1, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(addrV1), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(c)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public RemoteCall<TransactionReceipt> create(String n, BigInteger b, String c) {
        final Function function = new Function(
                FUNC_CREATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(b), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(c)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void create(String n, BigInteger b, String c, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(n), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(b), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(c)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public List<ItemV3_createdEventResponse> getItemV3_createdEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ITEMV3_CREATED_EVENT, transactionReceipt);
        ArrayList<ItemV3_createdEventResponse> responses = new ArrayList<ItemV3_createdEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ItemV3_createdEventResponse typedResponse = new ItemV3_createdEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.retcode = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.itemAddr = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.balance = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.city = (String) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.memo = (String) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ItemV3_createdEventResponse> itemV3_createdEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, ItemV3_createdEventResponse>() {
            @Override
            public ItemV3_createdEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ITEMV3_CREATED_EVENT, log);
                ItemV3_createdEventResponse typedResponse = new ItemV3_createdEventResponse();
                typedResponse.log = log;
                typedResponse.retcode = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.itemAddr = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.name = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.balance = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.city = (String) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.memo = (String) eventValues.getNonIndexedValues().get(5).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ItemV3_createdEventResponse> itemV3_createdEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ITEMV3_CREATED_EVENT));
        return itemV3_createdEventFlowable(filter);
    }

    public List<ItemV3_migratedEventResponse> getItemV3_migratedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ITEMV3_MIGRATED_EVENT, transactionReceipt);
        ArrayList<ItemV3_migratedEventResponse> responses = new ArrayList<ItemV3_migratedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ItemV3_migratedEventResponse typedResponse = new ItemV3_migratedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.retcode = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.addressV1 = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.addressV3 = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.memo = (String) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ItemV3_migratedEventResponse> itemV3_migratedEventFlowable(BcosFilter filter) {
        return web3j.logFlowable(filter).map(new io.reactivex.functions.Function<Log, ItemV3_migratedEventResponse>() {
            @Override
            public ItemV3_migratedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ITEMV3_MIGRATED_EVENT, log);
                ItemV3_migratedEventResponse typedResponse = new ItemV3_migratedEventResponse();
                typedResponse.log = log;
                typedResponse.retcode = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.addressV1 = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.addressV3 = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.memo = (String) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ItemV3_migratedEventResponse> itemV3_migratedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        BcosFilter filter = new BcosFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ITEMV3_MIGRATED_EVENT));
        return itemV3_migratedEventFlowable(filter);
    }

    @Deprecated
    public static ItemWorkerV3 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ItemWorkerV3(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ItemWorkerV3 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ItemWorkerV3(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ItemWorkerV3 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ItemWorkerV3(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ItemWorkerV3 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ItemWorkerV3(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ItemWorkerV3> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ItemWorkerV3.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ItemWorkerV3> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ItemWorkerV3.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<ItemWorkerV3> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ItemWorkerV3.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ItemWorkerV3> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ItemWorkerV3.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ItemV3_createdEventResponse {
        public Log log;

        public BigInteger retcode;

        public String itemAddr;

        public String name;

        public BigInteger balance;

        public String city;

        public String memo;
    }

    public static class ItemV3_migratedEventResponse {
        public Log log;

        public BigInteger retcode;

        public String addressV1;

        public String addressV3;

        public String memo;
    }
}
