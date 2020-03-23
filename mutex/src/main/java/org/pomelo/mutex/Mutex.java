package org.pomelo.mutex;

public class Mutex implements Lock{

    Object target;

    @Override
    public synchronized void acquire() throws InterruptedException {

        while(target != null){
            wait();
        }
        target = Thread.currentThread();
    }

    @Override
    public synchronized void release() {

        if(target != null && target == Thread.currentThread()){
            target = null;
            notify();
        }
    }
}
