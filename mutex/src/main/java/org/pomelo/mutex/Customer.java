package org.pomelo.mutex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class Customer implements Runnable{


    private CriticalResource resource;

    private String name;

    private volatile int result;

    private CountDownLatch latch;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Customer(CriticalResource resource, String name, CountDownLatch latch){
        this.name = name;
        this.resource = resource;
        result = 0;
        this.latch = latch;
    }

    @Override
    public void run() {

        int i = 0;
        while(resource.takeResource()){
            i++;
            logger.info("{} take resource success", name);
        }

        logger.info("{} took resources account:"+i, name);
        result = result + i;
        latch.countDown();
    }
}
