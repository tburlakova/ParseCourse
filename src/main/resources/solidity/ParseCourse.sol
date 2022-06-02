// SPDX-License-Identifier: MIT

pragma solidity ^0.8.0;

import '@thesolidchain/pancake-swap-periphery/contracts/interfaces/IPancakeRouter01.sol';

contract CourseParser {

    address private pancakeswap = 0x9Ac64Cc6e4415144C455BD8E4837Fea55603e5c3;

    address BUSD = 0x78867BbEeF44f2326bF8DDd1941a4439382EF2A7;
    address BTC = 0xDAcbdeCc2992a63390d108e8507B98c7E2B5584a;
    address BNB = 0x8BaBbB98678facC7342735486C851ABD7A0d17Ca;
    address ETH = 0x3e8410C4f9D36c27F4776a36Fb2f467587b6f639;

    address[] private BTC2BUSD;
    address[] private BNB2BUSD;
    address[] private ETH2BUSD;

    constructor() {

        BTC2BUSD.push(BTC);
        BTC2BUSD.push(BUSD);
        BNB2BUSD.push(BNB);
        BNB2BUSD.push(BUSD);
        ETH2BUSD.push(ETH);
        ETH2BUSD.push(BUSD);
    }

    function getBTC() external view returns (uint) {
         return IPancakeRouter01(pancakeswap).getAmountsOut(1, BTC2BUSD)[1];
    }

    function getBNB() external view returns (uint) {
        return IPancakeRouter01(pancakeswap).getAmountsOut(1, BNB2BUSD)[1];
    }

    function getETH() external view returns (uint) {
        return IPancakeRouter01(pancakeswap).getAmountsOut(1, ETH2BUSD)[1];
    }
}
