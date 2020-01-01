package assignments.operatingsystem.bankersalgorithm;

public class Process {
	
	private int[] allocMatrix;
	private int[] maxMatrix;
	private int[] needMatrix;
	private boolean finish = false;
	private String processName="";
	
	public String getProcessName() {
		return processName;
	}

	public int[] getAllocMatrix() {
		return this.allocMatrix;
	}

	public int[] getMaxMatrix() {
		return maxMatrix;
	}

	public int[] getNeedMatrix() {
		return needMatrix;
	}
	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	
	public Process(int numberOfResource, String pName){
		allocMatrix = new int[numberOfResource];
		maxMatrix = new int[numberOfResource];
		needMatrix = new int[numberOfResource];
		processName = pName;
	}
	
	public void setAllocationMatrix(String line){
		String[] values = line.split(" ");
		for(int i=0;i<values.length;i++){
			try{
			this.allocMatrix[i] = Integer.parseInt(values[i]);
			}catch(NumberFormatException numErr){
				System.out.println("NumberFormatException:"+numErr);
			}
		}
	}
	
	public void setMaxMatrix(String line){
		String[] values = line.split(" ");
		for(int i=0;i<values.length;i++){
			try{
			this.maxMatrix[i] = Integer.parseInt(values[i]);
			}catch(NumberFormatException numErr){
				System.out.println("NumberFormatException:"+numErr);
			}
		}
	}
	
	
	
	public void setNeedMatrix(int numberOfResource){
		for(int i=0; i<numberOfResource; i++){
				this.needMatrix[i] = this.maxMatrix[i] - this.allocMatrix[i];
		}
	}
	
	

}
