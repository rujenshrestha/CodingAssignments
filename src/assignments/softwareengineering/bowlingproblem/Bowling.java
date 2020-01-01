package assignments.softwareengineering.bowlingproblem;

import java.util.Scanner;

public class Bowling {
	Frame[] frames = new Frame[10];
	int totalScore=0;
	final static int numberOfFrames=10;
	
	public static void main(String[] args){
		Bowling b = new Bowling();
		int score = b.bowling();
		System.out.println("Total Score:"+score);
	}
	public int bowling(){	 

		for(int i=0;i<numberOfFrames;i++){
			frames[i] = new Frame();
			frames[i].setFrameNo(i+1);
			System.out.println("FRAME:"+frames[i].getFrameNo());
			frames[i] = setFrameScore(frames[i]);
			
			if(i>0){
				if(i>1){
					if(frames[i-1].isStrike()){
						if(frames[i-2].isStrike()){
							if(frames[i].isStrike()){
								frames[i-2].setFrameScore(30);
								System.out.println("FRAME "+frames[i-2].getFrameNo()+"Score(STRIKE): "+frames[i-2].getFrameScore());
								totalScore = totalScore + frames[i-2].getFrameScore();
								if(i==numberOfFrames-1){
									frames[i-1].setFrameScore(frames[i-1].getFrameScore()+frames[i].getRoll()[0]+frames[i].getRoll()[1]);
									System.out.println("FRAME "+frames[i-1].getFrameNo()+"Score(STRIKE): "+frames[i-1].getFrameScore());
									totalScore = totalScore + frames[i-1].getFrameScore();
								}
								System.out.println("TotalScore till now:"+totalScore);
							}else{
								frames[i-2].setFrameScore(20+frames[i].getRoll()[0]);
								System.out.println("FRAME "+frames[i-2].getFrameNo()+"Score(STRIKE): "+frames[i-2].getFrameScore());
								frames[i-1].setFrameScore(10+frames[i].getRoll()[0]+frames[i].getRoll()[1]);
								System.out.println("FRAME "+frames[i-1].getFrameNo()+"Score(STRIKE): "+frames[i-1].getFrameScore());
								totalScore = totalScore + frames[i-2].getFrameScore()+frames[i-1].getFrameScore();
								System.out.println("TotalScore till now:"+totalScore);
							}
						}else{
							if(!frames[i].isStrike()){
								frames[i-1].setFrameScore(10+frames[i].getRoll()[0]+frames[i].getRoll()[1]);
								System.out.println("FRAME "+frames[i-1].getFrameNo()+"Score(STRIKE): "+frames[i-1].getFrameScore());
								totalScore = totalScore + frames[i-1].getFrameScore();
								System.out.println("TotalScore till now:"+totalScore);
							}else if(i==numberOfFrames-1){
								frames[i-1].setFrameScore(10+frames[i].getRoll()[0]+frames[i].getRoll()[1]);
								System.out.println("FRAME "+frames[i-1].getFrameNo()+"Score(STRIKE): "+frames[i-1].getFrameScore());
								totalScore = totalScore + frames[i-1].getFrameScore();
								System.out.println("TotalScore till now:"+totalScore);
							}
						}
						
					}else if(frames[i-1].isSpare()){
						frames[i-1].setFrameScore(10+frames[i].getRoll()[0]);
						System.out.println("FRAME "+frames[i-1].getFrameNo()+"Score(SPARE): "+frames[i-1].getFrameScore());
						totalScore = totalScore + frames[i-1].getFrameScore();
						System.out.println("TotalScore till now:"+totalScore);
					}
				}else{ 
					if(frames[i-1].isStrike()){
						if(!frames[i].isStrike()){
							frames[i-1].setFrameScore(frames[i-1].getFrameScore()+frames[i].getFrameScore());
							System.out.println("FRAME "+frames[i-1].getFrameNo()+" Score(STRIKE): "+frames[i-1].getFrameScore());
							totalScore = totalScore + frames[i-1].getFrameScore();
							System.out.println("TotalScore till now:"+totalScore);
						}
					}else if(frames[i-1].isSpare()){
						frames[i-1].setFrameScore(frames[i-1].getFrameScore()+frames[i].getRoll()[0]);
						System.out.println("FRAME "+frames[i-1].getFrameNo()+" Score(SPARE): "+frames[i-1].getFrameScore());
						totalScore = totalScore + frames[i-1].getFrameScore();
						System.out.println("TotalScore till now:"+totalScore);
					}
				}
				
			}
			

			if(!frames[i].isStrike() && !frames[i].isSpare()){
				System.out.println("FRAME "+frames[i].getFrameNo()+" Score: "+frames[i].getFrameScore());
				System.out.println("-------------------------------------------------");
				totalScore = totalScore + frames[i].getFrameScore();
				System.out.println("TotalScore till now:"+totalScore);
			
			}else if(frames[i].isStrike()){
				System.out.println("STRIKE!!!\n-------------------------------------------------");
				if(i==numberOfFrames-1){
					frames[i] = bonusRoll(frames[i]);
					System.out.println("FRAME "+frames[i].getFrameNo()+" Score: "+frames[i].getFrameScore());
					totalScore = totalScore + frames[i].getFrameScore();
					System.out.println("TotalScore till now:"+totalScore);
				}
				
			}else if(frames[i].isSpare()){
				System.out.println("SPARE \n-------------------------------------");
				if(i==numberOfFrames-1){
					frames[i] = bonusRoll(frames[i]);
					System.out.println("FRAME "+frames[i].getFrameNo()+" Score: "+frames[i].getFrameScore());
					totalScore = totalScore + frames[i].getFrameScore();
					System.out.println("TotalScore till now:"+totalScore);
				}
			}	
		}
		return totalScore;
	}
	
	
	public Frame setFrameScore(Frame frame){
		int rollIndex=1;
		int roll;
		int temp1 = 10;
		int temp2=0;
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<=1;i++){
			
			System.out.print("ROLL "+rollIndex+":");
			roll = sc.nextInt();
			temp2 = temp1;
			temp1 = temp1 - roll;
			if(temp1<0 && !(frame.getFrameNo()==10 && frame.getRoll()[0]==10)){
				System.out.println("Please enter valid no.");
				roll = 0;
				rollIndex--;
				if(i>0){
					i--;
				}
				temp1 = temp2;
			}else{
				frame.setRoll(i,roll);
				if(roll==10){
					frame.setStrike(true);
					if(frame.getFrameNo()!=10){
						break;
					}
				}
			}	
			rollIndex++;
		}
		frame.setFrameScore(frame.getRoll()[0]+frame.getRoll()[1]);	
		if((frame.getRoll()[0]>0 && frame.getRoll()[1]>0) && frame.getFrameScore()==10){
			frame.setSpare(true);
		}
		return frame;
	}
	
	public Frame bonusRoll(Frame frame){
		int bonusRoll;
		System.out.print("ROLL 3:");
		Scanner sc = new Scanner(System.in);
		bonusRoll = sc.nextInt();
		if(bonusRoll==10){
			frame.setStrike(true);
		}
		frame.setFrameScore(frame.getFrameScore()+bonusRoll);	
		sc.close();
		return frame;
	}

}
