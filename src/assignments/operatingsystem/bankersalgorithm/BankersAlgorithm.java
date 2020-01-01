package assignments.operatingsystem.bankersalgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BankersAlgorithm {
	
	private static int numberOfProcess=0;
	private static int numberOfResource=0;
	private static int[] availableMatrix;
	private static Process[] p;
	private static String[] data = new String[20];
	private static String[] processSequence;
	private String state="SYSTEM IS UNSAFE";
	private int currentRow=0;
	

	public static void main(String[] args) {
		BankersAlgorithm b = new BankersAlgorithm();
		
		b.readInputFile();
		b.setMatrix(data);
		b.checkState();
		b.writeToOutputFile();
		
	}
	
	
	public void readInputFile(){			
		String line="";
		int i=0;
		File inputFile = new File("D:/InputFile.txt");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
				while((line = br.readLine())!=null){
					if(!line.trim().isEmpty()){
						data[i] = line;
						i++;
					}
				}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException:"+e);	
		}catch (IOException e) {
			System.out.println("IOException:"+e);
		}
	}
	
	
	public void checkState(){
		boolean exec;
		int sequence=0;
		processSequence = new String[numberOfProcess];
		
		while(true){
			if(allFinish()){
				System.out.println("System is safe");
				state = "SYSTEM IS SAFE";
				break;
			}else{
				for(int i=0;i<numberOfProcess;i++){
					exec = true;
					if(!p[i].isFinish()){
						int[] temp = p[i].getNeedMatrix();
						for(int j=0;j<numberOfResource;j++){
							if(temp[j] > availableMatrix[j]){
								System.out.println("entered for P"+i);
								exec = false;
							}
						}
						if(exec){
							int[] allocated = p[i].getAllocMatrix();
							for(int k=0;k<numberOfResource;k++){
								availableMatrix[k] = availableMatrix[k]+allocated[k];
							}
							p[i].setFinish(true);
							System.out.println("Process to execute:"+p[i].getProcessName());
							processSequence[sequence] = p[i].getProcessName();
							sequence++;
						}
					}	
				}
			}	
		}
	}
	
	public void setMatrix(String[] data){
		numberOfProcess = Integer.parseInt(data[0]);
		numberOfResource = Integer.parseInt(data[1]);
		currentRow=2;
		int i=currentRow;
		p = new Process[numberOfProcess];
		int k=0;
		for(int j=0;j<numberOfProcess;j++){
			p[j] = new Process(numberOfResource,"P"+String.valueOf(j));
		}
		while(i<(currentRow+numberOfProcess)){
			p[k].setAllocationMatrix(data[i]);
			i++;
			k++;
		}
		currentRow=i;
		k=0;
		while(i<(currentRow+numberOfProcess)){
			p[k].setMaxMatrix(data[i]);
			i++;
			k++;
		}
		currentRow=i;
		k=0;
		availableMatrix = new int[numberOfResource];
		while(i<currentRow+1){
			setAvailableMatrix(data[i]);
			i++;
		}
		for(int j=0;j<numberOfProcess;j++){
			p[j].setNeedMatrix(numberOfResource);
		}
	}
	

	
	public void setAvailableMatrix(String line){
		String[] values = line.split(" ");
		for(int i=0;i<values.length;i++){
			try{
			availableMatrix[i] = Integer.parseInt(values[i]);
			}catch(NumberFormatException numErr){
				System.out.println("NumberFormatException:"+numErr);
			}
		}
	}
	
	public boolean allFinish(){
		boolean allFin = true;
		for(int i=0;i<numberOfProcess;i++){
			if(!p[i].isFinish()){
				allFin = false;
			}
		}
		System.out.println("allFinish:"+allFin);
		return allFin;
	}
	
	public void writeToOutputFile(){
		File outputFile = new File("D:/OutputFile.txt");
		StringBuilder sb = new StringBuilder("");
		try {
			FileWriter fw = new FileWriter(outputFile);
			sb.append("Need Matrix:");
			for(int i=0;i<numberOfProcess;i++){
				int[] need = p[i].getNeedMatrix();
				sb.append("\r\nP"+i);
				for(int j=0;j<numberOfResource;j++){
					sb.append(" "+need[j]);
				}
			}
			sb.append("\r\n Process Sequence:");
			for(int i=0;i<numberOfProcess;i++){
				sb.append(" "+processSequence[i]);
			}
			sb.append("\r\nSTATE:"+state);
			fw.append(sb);
			fw.close();
		} catch (IOException e) {
			System.out.println("IOException:"+e);
		}
	}

}
