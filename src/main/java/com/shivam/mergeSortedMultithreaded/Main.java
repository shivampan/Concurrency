package com.shivam.mergeSortedMultithreaded;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args)throws ExecutionException, InterruptedException {
        List<Integer> lisToSort= List.of(3,2,1,4,6,8,7,5);

        ExecutorService executorService= Executors.newCachedThreadPool();
        MergerSorter mergerSorter=new MergerSorter(lisToSort,executorService);

        Future<List<Integer>> sortedListFuture =executorService.submit(mergerSorter);
        List<Integer> sortedList=sortedListFuture.get();
        System.out.println(sortedList);
    }
}
