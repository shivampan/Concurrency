package com.shivam.mergeSortedMultithreaded;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MergerSorter implements Callable<List<Integer>> {

    private List<Integer> listToSort;
    private ExecutorService executorService;

    public MergerSorter(List<Integer> listToSort,ExecutorService executorService) {
        this.listToSort = listToSort;
        this.executorService = executorService;
    }
    @Override
    public List<Integer> call() throws Exception {
        //Merge Sortlogic
        int n=listToSort.size();
        if(n<=1)return listToSort;

        List<Integer> lefthalf=new ArrayList<Integer>();
        List<Integer> righthalf=new ArrayList<Integer>();

        for(int i=0;i<n/2;i++){
            lefthalf.add(listToSort.get(i));
        }
        for(int i=n/2;i<n;i++){
            righthalf.add(listToSort.get(i));
        }
        //sort left and right halves in different threads.

        MergerSorter leftMergeSorter = new MergerSorter(lefthalf,executorService);//task is created
        MergerSorter rightMergeSorter = new MergerSorter(righthalf,executorService);//task is created.

       //ExecutorService executorService= Executors.newCachedThreadPool();//when you dont know the size of the pool you can use this exector.newCachepool()
        Future<List<Integer>> leftSortedListFuture=executorService.submit(leftMergeSorter);
        Future<List<Integer>> rightSortedListFuture=executorService.submit(rightMergeSorter);

        List<Integer> leftSortedList=leftSortedListFuture.get(); //Blocking call
        List<Integer> rightSortedList=rightSortedListFuture.get(); //Blocking call

        int i=0,j=0;
        List<Integer> sortedList=new ArrayList<>();
        while(i<leftSortedList.size() && j<rightSortedList.size()){
            if(leftSortedList.get(i)<rightSortedList.get(j)){
                sortedList.add(leftSortedList.get(i));
                i++;
            }
            else{
                sortedList.add(rightSortedList.get(j));
                j++;
            }
        }
        while(i<leftSortedList.size()){
            sortedList.add(leftSortedList.get(i));
            i++;
        }
        while(j<rightSortedList.size()){
            sortedList.add(rightSortedList.get(j));
            j++;
        }

        return sortedList;
    }
}
