package assignments.analysisofalgorithms.parallelquicksort.sort;

import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
/**
 *
 * @author Armand
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        int i;
        int size = 1000;
        int[] array = new int[size] ;
 
        Random randomGenerator = new Random();
         
        for (i = 0; i < array.length; ++i){
          int randomInt = randomGenerator.nextInt(size);
          array[i] = randomInt;
            
        }
                
        QuickSort.quickSort(array);
       
        System.out.println();

    }
}