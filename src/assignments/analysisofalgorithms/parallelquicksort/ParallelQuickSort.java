package assignments.analysisofalgorithms.parallelquicksort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelQuickSort extends RecursiveAction{

	private static final long serialVersionUID = 1L;
	private static final int MAX_ARRAY_VALUE = 500;
	private static final int ARRAY_SIZE = 500;
	private static int[] data = new int[ARRAY_SIZE];
	private static Random rand = new Random();
	
	private int[] array;
    private int leftindex;
    private int rightindex;

    public ParallelQuickSort(int[] array) {
        this.array = array;
        leftindex = 0;
        rightindex = this.array.length - 1;
    }

    public ParallelQuickSort(int[] array, int leftIndex, int rightIndex) {
        this.array = array;
        this.leftindex = leftIndex;
        this.rightindex = rightIndex;
    }
    
    public static void main(String[] args) {
        
    	for (int i = 0; i < data.length; i++) {
            int num = rand.nextInt((MAX_ARRAY_VALUE) + 1);
            data[i] = num;
        }
    	

        ParallelQuickSort parallelQS = new ParallelQuickSort(data);
        parallelQS.displayArray(data);

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(parallelQS);
        pool.shutdown();

        parallelQS.displayArray(data);
    }

    protected void compute() {
        if(leftindex < rightindex){ 
            int pivot = getPivotIndex(array, leftindex, rightindex);
            invokeAll(new ParallelQuickSort(array,leftindex, pivot),new ParallelQuickSort(array, pivot + 1, rightindex));
        }
    }

    public int getPivotIndex(int[] array, int bottomIndex, int topIndex) {
        int pivot = array[bottomIndex];
        int leftVal = bottomIndex - 1;
        int rightVal  = topIndex + 1;
        while (true){
            do {
            	rightVal--;
            }
            while (array[rightVal] > pivot);

            do {
            	leftVal++;
            }
            while (array[leftVal] < pivot);

            if (leftVal >= rightVal){ 
            	return rightVal;
            }
            
            int temp = array[leftVal];
            array[leftVal] = array[rightVal];
            array[rightVal] = temp;
        }
    }
    
    public void displayArray(int[] array){
    	 System.out.println("Array:\n" + Arrays.toString(array));
    }


}