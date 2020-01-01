package assignments.analysisofalgorithms.parallelquicksort.sort;

import java.util.logging.Level;
import java.util.logging.Logger;
 
 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
/**
 *
 * @author Armand
 */
public class MyFirstRunnable implements Runnable {
    int [] arr;
    int l;
    int r;
 
    public MyFirstRunnable(int[] arr, int l, int r) {
        this.arr = arr;
        this.l = l;
        this.r = r;
 
    }
 
    @Override
    public void run() {
        QuickSort sort = new QuickSort();
        try {
            QuickSort.quickSort(arr, l, r);
        } catch (InterruptedException ex) {
            Logger.getLogger(MyFirstRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}