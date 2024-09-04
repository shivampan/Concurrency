package com.shivam.addersubtractorlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Subtractor implements Runnable {
    Count count;
    Lock lock;
    Subtractor(Count count, Lock lock) {
        this.lock=lock;
        this.count = count;
    }


    @Override
    public void run() {
        for(int i = 1; i <= 100000; i++) {
            lock.lock();
            count.value-=i;
            lock.unlock();
        }
    }
}
