package com.shivam.mergeSortedMultithreaded;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MergerSorter implements Callable<List<Integer>> {

    private List<Integer> listToSort;
    public MergerSorter(List<Integer> listToSort) {
        this.listToSort = listToSort;
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

        MergerSorter leftMergeSorter = new MergerSorter(lefthalf);//task is created
        MergerSorter rightMergeSorter = new MergerSorter(righthalf);//task is created.


        return null;
    }
}
