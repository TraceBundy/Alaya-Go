pragma solidity ^0.7.1;
import "./BaseAbstract.sol";

/**
 * @author qudong
 * @dev 2019/12/23
 * 基础合约（抽象合约是否可以继承抽象合约）
 */

abstract contract AbstractContractDSubclass is AbstractContractParentClass {

     function setParentNameD(string memory name) public {
         myName = name;
     }

     function dSubClassName() public virtual view returns (string memory dSubName);
 }
