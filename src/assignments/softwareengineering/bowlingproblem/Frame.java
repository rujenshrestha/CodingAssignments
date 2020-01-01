package assignments.softwareengineering.bowlingproblem;

public class Frame {
	
	
	private int frameNo;
	private int[] roll = new int[2];
	private int frameScore;
	private boolean isStrike;
	private boolean isSpare;
	private boolean frameScoreSet;
	
	public boolean isFrameScoreSet() {
		return frameScoreSet;
	}
	public void setFrameScoreSet(boolean frameScoreSet) {
		this.frameScoreSet = frameScoreSet;
	}
	public boolean isStrike() {
		return isStrike;
	}
	public void setStrike(boolean isStrike) {
		this.isStrike = isStrike;
	}
	public boolean isSpare() {
		return isSpare;
	}
	public void setSpare(boolean isSpare) {
		this.isSpare = isSpare;
	}
	public int getFrameNo() {
		return frameNo;
	}
	public void setFrameNo(int frameNo) {
		this.frameNo = frameNo;
	}
	public int getFrameScore() {
		return frameScore;
	}
	public void setFrameScore(int frameScore) {
		this.frameScore = frameScore;
	}
	public int[] getRoll() {
		return roll;
	}
	public void setRoll(int rollNo,int roll) {
		this.roll[rollNo] = roll;
	}
	public void setRoll(int[] roll) {
		this.roll = roll;
	}
	
}
