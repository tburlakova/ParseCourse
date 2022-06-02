package com.project.parsecourse.config;

import com.project.parsecourse.contracts.ParseCourse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

@Configuration
public class AppConfig {

    @Value("${contract.private-key}")
    private String privateKey;

    @Value("${contract.contract-address}")
    private String contractAddress;

    @Value("${contract.node-url}")
    private String url;

    @Bean
    public ParseCourse getParseCourse() {
        Web3j web3j = Web3j.build(new HttpService(url));  // defaults to http://localhost:8545/
        Credentials credentials = Credentials.create(privateKey);
        ContractGasProvider contractGasProvider = new DefaultGasProvider();

        return ParseCourse.load(contractAddress, web3j, credentials, contractGasProvider);
    }
}


//public class AppConfig {
//
//    @Value("${contract.private-key}")
//    private String privateKey;
//
//    @Value("${contract.node-url}")
//    private String url;
//
//    @Value("${contract.contract-address}")
//    private String contractAddress;
//
//    @Bean
//    public ParseCourse getParseCourse() {
//        Web3j web3j = Web3j.build(new HttpService("https://data-seed-prebsc-1-s1.binance.org:8545/"));  // defaults to http://localhost:8545/
//        Credentials credentials = Credentials.create("21a787e9d4f017684350acc2d220041e4a2d25f3fec947d3bd8c0cfd7d2038a3");
//        ContractGasProvider contractGasProvider = new DefaultGasProvider();
//
//        return ParseCourse.load(contractAddress, web3j, credentials, contractGasProvider);
//    }
//}

