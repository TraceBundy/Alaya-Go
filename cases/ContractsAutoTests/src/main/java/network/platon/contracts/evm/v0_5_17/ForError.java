package network.platon.contracts.evm.v0_5_17;

import com.alaya.abi.solidity.TypeReference;
import com.alaya.abi.solidity.datatypes.Bool;
import com.alaya.abi.solidity.datatypes.Function;
import com.alaya.abi.solidity.datatypes.Type;
import com.alaya.crypto.Credentials;
import com.alaya.protocol.Web3j;
import com.alaya.protocol.core.RemoteCall;
import com.alaya.tx.Contract;
import com.alaya.tx.TransactionManager;
import com.alaya.tx.gas.GasProvider;
import java.util.Arrays;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://github.com/PlatONnetwork/client-sdk-java/releases">platon-web3j command line tools</a>,
 * or the com.alaya.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/PlatONnetwork/client-sdk-java/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 0.13.2.1.
 */
public class ForError extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50610150806100206000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c8063895e3ada1461003b578063ec56ae5d1461005d575b600080fd5b61004361007f565b604051808215151515815260200191505060405180910390f35b6100656100a4565b604051808215151515815260200191505060405180910390f35b60008061008a6100c9565b90508060000160009054906101000a900460ff1691505090565b6000806100af6100f5565b90508060000160009054906101000a900460ff1691505090565b60008090505b600115158160000160009054906101000a900460ff16151514156100f2576100cf565b90565b60005b600190508060000160009054906101000a900460ff1615610118576100f8565b9056fea265627a7a72315820aba5f0cf3ddc4a323fec3e15917f0b147bc88ed96a39262e2a173700ace5429164736f6c63430005110032";

    public static final String FUNC_GETFORCONTROLRES = "getForControlRes";

    public static final String FUNC_GETFORCONTROLRES1 = "getForControlRes1";

    protected ForError(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider, Long chainId) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider, chainId);
    }

    protected ForError(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, Long chainId) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider, chainId);
    }

    public RemoteCall<Boolean> getForControlRes() {
        final Function function = new Function(FUNC_GETFORCONTROLRES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Boolean> getForControlRes1() {
        final Function function = new Function(FUNC_GETFORCONTROLRES1, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public static RemoteCall<ForError> deploy(Web3j web3j, Credentials credentials, GasProvider contractGasProvider, Long chainId) {
        return deployRemoteCall(ForError.class, web3j, credentials, contractGasProvider, BINARY,  "", chainId);
    }

    public static RemoteCall<ForError> deploy(Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, Long chainId) {
        return deployRemoteCall(ForError.class, web3j, transactionManager, contractGasProvider, BINARY,  "", chainId);
    }

    public static ForError load(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider, Long chainId) {
        return new ForError(contractAddress, web3j, credentials, contractGasProvider, chainId);
    }

    public static ForError load(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, Long chainId) {
        return new ForError(contractAddress, web3j, transactionManager, contractGasProvider, chainId);
    }
}
