package assignments.softwareengineering.bowlingproblem;

import java.util.Scanner;

public class BowlingGame {

	static Frames frames = new Frames();
	static int numberOfFrames =10; 
	static int frameIndex=0;
	static int score=0;
	
	public static Frames getFrames() {
		return frames;
	}
	public static void setFrames(Frames frames) {
		BowlingGame.frames = frames;
	}
	
	public static void main(String[] args) {
		BowlingGame bowling = new BowlingGame();
		int roll1=0;
		int roll2=0;
		int roll3=0;
		int totalScore=0;
		int scoreTillNow=0;
		String frameScore="";
		Scanner sc = new Scanner(System.in);
		
		for(int i=0;i<numberOfFrames;i++){
			frames.getFrames()[frameIndex].setFrameNo(i+1);
			System.out.println("FRAME "+frames.getFrames()[frameIndex].getFrameNo()+":");
			System.out.print("Enter Roll1:");
			roll1 = sc.nextInt();
			if(roll1!=10 || i==numberOfFrames-1){
				System.out.print("Enter Roll2:");
				roll2 = sc.nextInt();
			}
			if((i==numberOfFrames-1) && (roll1+roll2>=10)){
				System.out.print("Enter Roll3:");
				roll3 = sc.nextInt();
			}
			frames.getFrames()[frameIndex].setRoll(0, roll1);
			frames.getFrames()[frameIndex].setRoll(1, roll2);
			
			frameScore = bowling.showFrameScore(roll1,roll2);
			System.out.println("roll 3 value:"+roll3);
			System.out.println("----------------------------------------------------------\n");
			scoreTillNow = bowling.updateScore();
			System.out.println("Frame"+(i+1)+" Score:"+frameScore);
			frameIndex++;
		}
		System.out.println("Frame" +frames.getFrames()[9].getFrameNo()+"Score: "+frames.getFrames()[9].getFrameScore());
		totalScore =  bowling.bowling();
		if(roll3>0){
			frames.getFrames()[9].setFrameScore(frames.getFrames()[9].getFrameScore()+roll3);
			System.out.println("$$$$$Frame "+frames.getFrames()[9].getFrameNo()+"Score: "+frames.getFrames()[9].getFrameScore());
		}
		System.out.println("TotalScore:"+totalScore);

	}
	
	
	public String showFrameScore(int roll1, int roll2){
		String scoreVal="";
	
		if(roll1 == 10 || roll2 == 10){
			frames.getFrames()[frameIndex].setStrike(true);
			scoreVal="STRIKE";
		}else if(roll1 + roll2 == 10){
			frames.getFrames()[frameIndex].setSpare(true);
			scoreVal="SPARE";
		}else{
			frames.getFrames()[frameIndex].setFrameScore(roll1+roll2);
			scoreVal = String.valueOf(frames.getFrames()[frameIndex].getFrameScore());
			score = score + frames.getFrames()[frameIndex].getFrameScore();
		}
		return scoreVal;
	}
	
	


	public int updateScore(){
		if(frameIndex>0){
			if(frameIndex>1){
				if(frames.getFrames()[frameIndex-1].isStrike()){
					if(frames.getFrames()[frameIndex-2].isStrike()){
						if(frames.getFrames()[frameIndex].isStrike()){
							frames.getFrames()[frameIndex-2].setFrameScore(30);
							System.out.println("FRAME"+frames.getFrames()[frameIndex-2].getFrameNo()+"Score:"+frames.getFrames()[frameIndex-2].getFrameScore());
							score = score+ frames.getFrames()[frameIndex-2].getFrameScore();
							if(frameIndex==numberOfFrames-1){
								frames.getFrames()[frameIndex-1].setFrameScore(frames.getFrames()[frameIndex-1].getFrameScore()+
										frames.getFrames()[frameIndex].getRoll()[0]+frames.getFrames()[frameIndex].getRoll()[1]);
								System.out.println("FRAME"+frames.getFrames()[frameIndex-1].getFrameNo()+"Score:"+frames.getFrames()[frameIndex-1].getFrameScore());
								score = score+ frames.getFrames()[frameIndex-1].getFrameScore();
							}
							
						}else{
							frames.getFrames()[frameIndex-2].setFrameScore(20+frames.getFrames()[frameIndex].getRoll()[0]);
							frames.getFrames()[frameIndex-1].setFrameScore(10+frames.getFrames()[frameIndex].getRoll()[0]+
									frames.getFrames()[frameIndex].getRoll()[1]);
							System.out.println("FRAME"+frames.getFrames()[frameIndex-2].getFrameNo()+"Score:"+frames.getFrames()[frameIndex-2].getFrameScore());
							System.out.println("FRAME"+frames.getFrames()[frameIndex-1].getFrameNo()+"Score:"+frames.getFrames()[frameIndex-1].getFrameScore());
							score = score+ frames.getFrames()[frameIndex-2].getFrameScore() + 
									frames.getFrames()[frameIndex-1].getFrameScore();
						}
					}else{
						if(!frames.getFrames()[frameIndex].isStrike()){
							frames.getFrames()[frameIndex-1].setFrameScore(10+frames.getFrames()[frameIndex].getRoll()[0]+
									frames.getFrames()[frameIndex].getRoll()[1]);
							System.out.println("FRAME"+frames.getFrames()[frameIndex-1].getFrameNo()+"Score:"+frames.getFrames()[frameIndex-1].getFrameScore());
							score = score+ frames.getFrames()[frameIndex-1].getFrameScore();
						}else if(frameIndex==numberOfFrames-1){
							frames.getFrames()[frameIndex-1].setFrameScore(10+frames.getFrames()[frameIndex].getRoll()[0]+
									frames.getFrames()[frameIndex].getRoll()[1]);
							frames.getFrames()[frameIndex].setFrameScore(frames.getFrames()[frameIndex].getRoll()[0]+
									frames.getFrames()[frameIndex].getRoll()[1]);
							System.out.println("FRAME"+frames.getFrames()[frameIndex-1].getFrameNo()+"Score:"+frames.getFrames()[frameIndex-1].getFrameScore());
							System.out.println("##############"+frames.getFrames()[frameIndex].getFrameNo()+frames.getFrames()[frameIndex].getFrameScore());
							score = score+ frames.getFrames()[frameIndex-1].getFrameScore();
							score = score+ frames.getFrames()[frameIndex].getFrameScore();
						}
					}	
				}else if(frames.getFrames()[frameIndex-1].isSpare()){
					frames.getFrames()[frameIndex-1].setFrameScore(10+frames.getFrames()[frameIndex].getRoll()[0]);
					System.out.println("FRAME"+frames.getFrames()[frameIndex-1].getFrameNo()+"Score:"+frames.getFrames()[frameIndex-1].getFrameScore());
					score = score+ frames.getFrames()[frameIndex-1].getFrameScore();
				}
			}else{ 
				if(frames.getFrames()[frameIndex-1].isStrike()){
					if(!frames.getFrames()[frameIndex].isStrike()){
						frames.getFrames()[frameIndex-1].setFrameScore(frames.getFrames()[frameIndex-1].getFrameScore()+
								frames.getFrames()[frameIndex].getFrameScore());
						System.out.println("FRAME"+frames.getFrames()[frameIndex-1].getFrameNo()+"Score:"+frames.getFrames()[frameIndex-1].getFrameScore());
						score = score+ frames.getFrames()[frameIndex-1].getFrameScore();
					}
				}else if(frames.getFrames()[frameIndex-1].isSpare()){
					frames.getFrames()[frameIndex-1].setFrameScore(10+frames.getFrames()[frameIndex].getRoll()[0]);
					System.out.println("FRAME"+frames.getFrames()[frameIndex-1].getFrameNo()+"Score:"+frames.getFrames()[frameIndex-1].getFrameScore());
					score = score+ frames.getFrames()[frameIndex-1].getFrameScore();
				}
			}
			
		}
		return score;
	}
	
	public int bowling(){
		int totalScore=0;
		for(int i=0;i<numberOfFrames;i++){
			totalScore = totalScore + frames.getFrames()[i].getFrameScore();
		}
		return totalScore;
	}
	

}
