package com.swaggertest.demo.controller.thread;

import com.google.common.collect.Lists;
import com.swaggertest.demo.domain.dto.PmsCategoryDTO;
import com.swaggertest.demo.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class XCTest1 {

    @Autowired
    private static CateService cateService;

    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<String> name = Lists.newArrayList("我的名字");
        useCompletableFuture(name);
    }

    @PostConstruct
    private static List<PmsCategoryDTO> useCompletableFuture(List<String> name) throws InterruptedException, ExecutionException {
//        System.out.println("CompletableFuture");
//        List<PmsCategoryDTO> list = new ArrayList<>();
//        name.forEach(item ->{
//            list.addAll(CompletableFuture.supplyAsync(() -> cateService.qryAll(item)).getNow(null));
//
//        });
        return null;
    }

    public static Void work(String name) {
        cateService.qryAll(name);
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(10));
        } catch (InterruptedException e) {
        }
        System.out.println(name + " ends at " + LocalTime.now());
        return null;
    }

//            CompletableFuture<Void> futureA = CompletableFuture.supplyAsync((() -> work(item)));
//            CompletableFuture<Void> futureB = CompletableFuture.supplyAsync(() -> work(item));
//            try {
//                futureA.runAfterEither(futureB, () -> work(item)).get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
}
