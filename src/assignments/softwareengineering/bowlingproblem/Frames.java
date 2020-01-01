package assignments.softwareengineering.bowlingproblem;

public class Frames {
	Frame[] frames = new Frame[10];
	
	public Frame[] getFrames() {
		return frames;
	}
	public void setFrames(Frame[] frames) {
		this.frames = frames;
	}
	public Frames(){
		for(int i=0;i<10;i++){
			this.frames[i] = new Frame();
		}
	}
}
