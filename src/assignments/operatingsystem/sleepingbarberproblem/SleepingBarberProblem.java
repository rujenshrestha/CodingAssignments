package assignments.operatingsystem.sleepingbarberproblem;

import java.util.concurrent.Semaphore;

public class SleepingBarberProblem extends Thread {

 public static boolean barberSleep = true;
 public static boolean barberJobFinish = false;
 public static int customerNo = 1;
 public static final int totalNumberOfSeats = 4;
 public static int numberOfAvailableSeats = totalNumberOfSeats;
 
 public static Semaphore accessCustomer = new Semaphore(0);
 public static Semaphore accessBarber = new Semaphore(0);
 public static Semaphore accessWaitingRoomSeats = new Semaphore(1);
 
 public static void main(String args[]) {

	 SleepingBarberProblem s = new SleepingBarberProblem();

	 Thread barberThread = new Thread(new Runnable(){
		 @Override
		 public void run(){
			 s.barberProcess();		  
		 }
	 });
	 barberThread.start();
	 
	 try {
		sleep(2000);
	 }catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	 }
	 Thread[] customerThread = new Thread[11];
	 for(int i = 0; i < 11; i++){
		 customerNo = i+1;
		 customerThread[i] = new Thread(new Runnable(){
			 @Override
			 public void run(){
				 s.customerProcess(customerNo,true,true);
			 }
		 });
		 
		 customerThread[i].start();
		 try {
			 sleep(2500);
		 } catch (InterruptedException ex) {
		 }
		 
	 }
 }

 
 public void barberProcess(){
	 while(true){
		    try{
		    	if(barberSleep){
		    		System.out.println("Barber is sleeping...");
		    		sleep(1000);
		    	}else if(!barberSleep && (numberOfAvailableSeats == totalNumberOfSeats) && !barberJobFinish){
		    		sleep(2500);
		    		barberJobFinish = true;
		    		System.out.println("There are no more customers. The barber has gone to sleep.");	
		    		return;
		    	}else{
		    		accessCustomer.acquire();
		    		accessWaitingRoomSeats.acquire();
		    		numberOfAvailableSeats++;
		    		accessBarber.release();
		    		accessWaitingRoomSeats.release();
		    		System.out.print("Barber is cutting hair...");
		    		sleep(5000);
		    	}
		    
		    }catch(Exception e){
		    	System.out.println("Exception in barberProcess:"+e);
		    }
	}
 }
 
 public void customerProcess(int custNo, boolean noHaircut, boolean insideShop){
	 
	 while (noHaircut && insideShop){
	    try{
	    	if(barberSleep){
	    		barberSleep = false;
	    		System.out.println("Customer "+custNo+" woke up the barber");
	    	}
	    	accessWaitingRoomSeats.acquire();
	    	if (numberOfAvailableSeats > 0) { 
	    		System.out.println("Customer "+custNo+" took a seat");
	    		numberOfAvailableSeats--;
	    		accessCustomer.release();
	    		accessWaitingRoomSeats.release();
	    		try {
	    			
	    			accessBarber.acquire();
	    			System.out.println("Customer "+custNo+" is getting a haircut");
	    			noHaircut = false;
	    			insideShop = false;
	    			sleep(1500);
	    			System.out.println("Customer "+custNo+" got a haircut and left.");
	    			sleep(3500);
	    			
	    			
	    		}catch(InterruptedException ex){
	    		}
	    	}else{
	    		System.out.println("There are no available seats. Customer "+custNo+" has left the barbershop.");
	    		accessWaitingRoomSeats.release();
	    		insideShop = false;
	    	}
	    }catch(Exception e){
	    	System.out.println("Exception in customerProcess:"+e);
	    }
	 }
 }
 
}