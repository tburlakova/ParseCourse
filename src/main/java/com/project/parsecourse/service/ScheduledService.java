package com.project.parsecourse.service;

import com.project.parsecourse.Main;
import com.project.parsecourse.contracts.ParseCourse;
import com.project.parsecourse.enums.CurrencyEnum;
import com.project.parsecourse.enums.IntervalsTableEnum;
import com.project.parsecourse.repository.ParseCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.logging.Logger;

@Service
@EnableScheduling
public class ScheduledService {

    private final static int TIMEOUT = 10000;
    private final static int ONE_MINUTE = 60000;
    private final static int FIVE_MINUTES = 300000;
    private final static int TEN_MINUTES = 600000;
    private final static int ONE_HOUR = 3600000;
    private final static int ONE_DAY = 86400000;

    static Logger LOGGER = Logger.getLogger(Main.class.getName());;

    @Autowired
    ParseCourseRepository repository;
    private final ParseCourse smartContract;

    public ScheduledService(ParseCourse smartContract) {
        this.smartContract = smartContract;
    }

    private Map<CurrencyEnum, BigInteger> monitor() throws InterruptedException, ExecutionException, TimeoutException {
        Map<CurrencyEnum, BigInteger> coins = new HashMap<>();
        CompletableFuture<BigInteger> bnbCall = smartContract.getBNB().sendAsync();
        CompletableFuture<BigInteger> btcCall = smartContract.getBTC().sendAsync();
        CompletableFuture<BigInteger> ethCall = smartContract.getETH().sendAsync();
        BigInteger bnb = bnbCall.get(TIMEOUT, TimeUnit.MILLISECONDS);
        BigInteger btc = btcCall.get(TIMEOUT, TimeUnit.MILLISECONDS);
        BigInteger eth = ethCall.get(TIMEOUT, TimeUnit.MILLISECONDS);

        coins.put(CurrencyEnum.BNB, bnb);
        coins.put(CurrencyEnum.BTC, btc);
        coins.put(CurrencyEnum.ETH, eth);

        return coins;
    }

    @Scheduled(fixedRate = ONE_MINUTE)
    public void monitor1() throws InterruptedException, ExecutionException, TimeoutException {
        LOGGER.info("Running one minute monitoring");
        Map<CurrencyEnum, BigInteger> map = monitor();
        Timestamp now = Timestamp.from(Instant.now());
        map.forEach((currency, cost) -> repository.save(IntervalsTableEnum.ONE_MINUTE.tableName, currency, now, cost));

    }

    @Scheduled(fixedRate = FIVE_MINUTES)
    public void monitor2() throws InterruptedException, ExecutionException, TimeoutException {
        LOGGER.info("Running five minutes monitoring");
        Map<CurrencyEnum, BigInteger> map = monitor();
        Timestamp now = Timestamp.from(Instant.now());
        map.forEach((currency, cost) -> repository.save(IntervalsTableEnum.FIVE_MINUTES.tableName, currency, now, cost));
    }

    @Scheduled(fixedRate = TEN_MINUTES)
    public void monitor3() throws InterruptedException, ExecutionException, TimeoutException {
        LOGGER.info("Running ten minutes monitoring");
        Map<CurrencyEnum, BigInteger> map = monitor();
        Timestamp now = Timestamp.from(Instant.now());
        map.forEach((currency, cost) -> repository.save(IntervalsTableEnum.TEN_MINUTES.tableName, currency, now, cost));
    }

    @Scheduled(fixedRate = ONE_HOUR)
    public void monitor4() throws InterruptedException, ExecutionException, TimeoutException {
        LOGGER.info("Running one hour monitoring");
        Map<CurrencyEnum, BigInteger> map = monitor();
        Timestamp now = Timestamp.from(Instant.now());
        map.forEach((currency, cost) -> repository.save(IntervalsTableEnum.ONE_HOUR.tableName, currency, now, cost));
    }

    @Scheduled(fixedRate = ONE_DAY)
    public void monitor5() throws InterruptedException, ExecutionException, TimeoutException {
        LOGGER.info("Running one day monitoring");
        Map<CurrencyEnum, BigInteger> map = monitor();
        Timestamp now = Timestamp.from(Instant.now());
        map.forEach((currency, cost) -> repository.save(IntervalsTableEnum.ONE_DAY.tableName, currency, now, cost));
    }

}
