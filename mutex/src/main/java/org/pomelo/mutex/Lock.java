package org.pomelo.mutex;

public interface Lock {

    void acquire() throws InterruptedException;
    void release();
}
