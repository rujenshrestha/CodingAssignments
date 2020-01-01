package assignments.operatingsystem.fibonacci;
import java.util.Scanner;

public class Fibonacci{
	
	public static void main(String[] args){
		
		System.out.println(args);
		Thread t1 = new Thread(new Runnable() {
	        @Override
	        public void run() {
	    		Scanner sc = new Scanner(System.in);
	    		System.out.println("Input the maximum number");
	    		int maxInt = sc.nextInt();
	            fibonacci(0,1,maxInt);
	        }
		});
		t1.start();
		t1.interrupt();
	}

	
	public static void fibonacci(int num1, int num2, int maxNum){
		int temp = num2;
		
		if(num2 >= maxNum){
			System.out.print(num1);
			return;
		}else{
			System.out.print(num1+" ");
			fibonacci(temp,num2+num1,maxNum);
		}
		try{}catch(NumberFormatException e){}
		

	}
	

}
