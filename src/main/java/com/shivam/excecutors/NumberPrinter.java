package com.shivam.excecutors;

public class NumberPrinter implements Runnable {
   private int number;
   public NumberPrinter(int number) {
       this.number = number;
   }
   public void run() {
       System.out.println(number + "-Thread name: "+Thread.currentThread().getName());

   }
}
