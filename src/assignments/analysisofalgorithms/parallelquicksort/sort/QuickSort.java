package assignments.analysisofalgorithms.parallelquicksort.sort;

public class QuickSort
{
    private static Lock lock = new Lock();
    static int CUTOF = 10;
    public static long timeForThreads;
    public static long timeForNormal;
     
    public static void quickSort(int [] arr) throws InterruptedException
    {
        //CUTOF = arr.length;
        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        long stop = System.currentTimeMillis();
        long duration = (stop - start);
        timeForThreads = duration;
        System.out.println();
        System.out.println("Duration :" + duration + " miliseconds");
        //sequential
        start = System.currentTimeMillis();
        plain_quickSort(arr, 0, arr.length - 1);
        stop = System.currentTimeMillis();
        duration = (stop - start);
        timeForNormal = duration;
        System.out.println();
        System.out.println("Duration :" + duration + " miliseconds");
    }
 
    private static int selectPivot(int [] arr, int left, int right)
    {
        int center = (left + right) / 2;
        if(arr[center] < (arr[left]) )
            swap(arr, left, center);
        if(arr[right] < arr[left])
            swap(arr, left, right);
        if(arr[right] < arr[center])
            swap(arr, center, right);
 
        swap(arr, center, right - 1);
        return arr[right - 1];
    }
    static boolean split = false;
    //Critical section
    private static void swap(int [] arr, int a, int b)
    {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
 
    public static void quickSort(int [] a, int left, int right) throws InterruptedException
    {
        if(left + CUTOF <= right)
        {
            lock.lock();
            int pivot = selectPivot(a, left, right);
            lock.unlock();
            int i = left, j = right - 1;
            for(;;)
            {
                while( a[++i] < pivot){}
                while( a[--j] < pivot){}
                if(i<j)
                {
                    lock.lock();
                    swap(a, i, j);
                    lock.unlock();
                }
                else
                    break;
            }
            lock.lock();
            swap(a, i, right-1);
            lock.unlock();
            if(!split)
            {
                Thread thread1 = new Thread(new MyFirstRunnable(a, left, i - 1));
                thread1.start();
                Thread thread2 = new Thread(new MyFirstRunnable(a, i + 1, right));
                thread2.start();
                thread1.join();
                thread2.join();
                 
                split = true;
 
                insertionSort(a, left, right);
            }
            else
            {
                quickSort(a, left, i - 1);
                quickSort(a, i + 1, right);
            }
        }
        else
        {
           insertionSort(a, left, right);
        }
    }
     private static void plain_quickSort(int [] a, int left, int right) throws InterruptedException
    {
        if(left + CUTOF <= right)
        {
            int pivot = selectPivot(a, left, right);
            int i = left, j = right - 1;
            for(;;)
            {
                while( a[++i] < pivot){}
                while( a[--j] < pivot){}
                if(i<j)
                    swap(a, i, j);
                else
                    break;
            }
            swap(a, i, right-1);
 
            quickSort(a, left, i - 1);
            quickSort(a, i + 1, right);
        }
        else
        {
            lock.lock();
            insertionSort(a, left, right);
            lock.unlock();
        }
    }
      
    public static void insertionSort(int [] arr, int l, int r)
    {
        int j;
 
        for(int p = l; p <= r; p++)
        {
            int tmp = arr [ p ];
            for (j = p; j > 0 && tmp < arr[j-1]; j--)
            {
                arr[j] = arr[j-1];
            }
            arr[j] = tmp;
        }
    }
    
    public static class Lock{
    	 
        private boolean isLocked = false;
 
        public synchronized void lock() throws InterruptedException{
            while(isLocked){
                wait();
            }
            isLocked = true;
        }
 
        public synchronized void unlock(){
            isLocked = false;
            notify();
        }
    }
}