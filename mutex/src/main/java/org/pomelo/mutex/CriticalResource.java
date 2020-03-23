package org.pomelo.mutex;

public class CriticalResource {

    private Lock lock;

    private int bean;

    public CriticalResource(int bean){
        lock = new Mutex();
        this.bean = bean;
    }

    public synchronized int count(){
        return bean;
    }

    Boolean takeResource(){

        try {
            lock.acquire();
            if(bean >0){
                bean --;

//                lock.release();
                return true;
            }
        } catch (InterruptedException e) {

        }finally {
            lock.release();
        }
        return false;

    }
}
