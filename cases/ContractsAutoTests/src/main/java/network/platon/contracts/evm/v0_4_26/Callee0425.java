package network.platon.contracts.evm.v0_4_26;

import com.alaya.abi.solidity.EventEncoder;
import com.alaya.abi.solidity.TypeReference;
import com.alaya.abi.solidity.datatypes.Address;
import com.alaya.abi.solidity.datatypes.Event;
import com.alaya.abi.solidity.datatypes.Function;
import com.alaya.abi.solidity.datatypes.Type;
import com.alaya.abi.solidity.datatypes.generated.Uint256;
import com.alaya.crypto.Credentials;
import com.alaya.protocol.Web3j;
import com.alaya.protocol.core.DefaultBlockParameter;
import com.alaya.protocol.core.RemoteCall;
import com.alaya.protocol.core.methods.request.PlatonFilter;
import com.alaya.protocol.core.methods.response.Log;
import com.alaya.protocol.core.methods.response.TransactionReceipt;
import com.alaya.tx.Contract;
import com.alaya.tx.TransactionManager;
import com.alaya.tx.gas.GasProvider;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://github.com/PlatONnetwork/client-sdk-java/releases">platon-web3j command line tools</a>,
 * or the com.alaya.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/PlatONnetwork/client-sdk-java/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 0.13.2.1.
 */
public class Callee0425 extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50610184806100206000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630c55699c1461005c578063371303c0146100875780635a3617561461009e575b600080fd5b34801561006857600080fd5b506100716100c9565b6040518082815260200191505060405180910390f35b34801561009357600080fd5b5061009c6100cf565b005b3480156100aa57600080fd5b506100b361014f565b6040518082815260200191505060405180910390f35b60005481565b60008081548092919060010191905055507fb0333e0e3a6b99318e4e2e0d7e5e5f93646f9cbf62da1587955a4092bf7df6e733600054604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390a1565b600080549050905600a165627a7a72305820a56c9dc8bb73f1bf533fcff3e0bdf1fa7bfc540e6318b4cf8df5e9213e4ac5c90029";

    public static final String FUNC_X = "x";

    public static final String FUNC_INC = "inc";

    public static final String FUNC_GETCALLEEX = "getCalleeX";

    public static final Event EVENTNAME_EVENT = new Event("EventName", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    protected Callee0425(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider, Long chainId) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider, chainId);
    }

    protected Callee0425(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, Long chainId) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider, chainId);
    }

    public RemoteCall<BigInteger> x() {
        final Function function = new Function(FUNC_X, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> inc() {
        final Function function = new Function(
                FUNC_INC, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getCalleeX() {
        final Function function = new Function(FUNC_GETCALLEEX, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public List<EventNameEventResponse> getEventNameEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(EVENTNAME_EVENT, transactionReceipt);
        ArrayList<EventNameEventResponse> responses = new ArrayList<EventNameEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            EventNameEventResponse typedResponse = new EventNameEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.seder = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.x = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<EventNameEventResponse> eventNameEventObservable(PlatonFilter filter) {
        return web3j.platonLogObservable(filter).map(new Func1<Log, EventNameEventResponse>() {
            @Override
            public EventNameEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(EVENTNAME_EVENT, log);
                EventNameEventResponse typedResponse = new EventNameEventResponse();
                typedResponse.log = log;
                typedResponse.seder = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.x = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<EventNameEventResponse> eventNameEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        PlatonFilter filter = new PlatonFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EVENTNAME_EVENT));
        return eventNameEventObservable(filter);
    }

    public static RemoteCall<Callee0425> deploy(Web3j web3j, Credentials credentials, GasProvider contractGasProvider, Long chainId) {
        return deployRemoteCall(Callee0425.class, web3j, credentials, contractGasProvider, BINARY,  "", chainId);
    }

    public static RemoteCall<Callee0425> deploy(Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, Long chainId) {
        return deployRemoteCall(Callee0425.class, web3j, transactionManager, contractGasProvider, BINARY,  "", chainId);
    }

    public static Callee0425 load(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider, Long chainId) {
        return new Callee0425(contractAddress, web3j, credentials, contractGasProvider, chainId);
    }

    public static Callee0425 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, Long chainId) {
        return new Callee0425(contractAddress, web3j, transactionManager, contractGasProvider, chainId);
    }

    public static class EventNameEventResponse {
        public Log log;

        public String seder;

        public BigInteger x;
    }
}
