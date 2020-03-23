package org.pomelo.mutex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        CriticalResource resource = new CriticalResource(100000);
        List<String> list = new LinkedList<String>();
        CountDownLatch latch = new CountDownLatch(1000);
        int totalSum = 0;


        for(int i =0 ; i<1000; i++) {
            list.add("孙悟饭"+i);
        }

        for(String name : list){
            new Thread(new Customer(resource, name, latch),  name+"-Thread").start();
        }


        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("完成");
    }
}
