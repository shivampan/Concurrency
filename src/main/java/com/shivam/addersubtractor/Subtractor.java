package com.shivam.addersubtractor;

public class Subtractor implements Runnable {
    Count count;

    Subtractor(Count count) {
        this.count = count;
    }
    public void run() {
        for (int i = 0; i < 100000; i++) {
            count.value-=i;
        }
    }
}
