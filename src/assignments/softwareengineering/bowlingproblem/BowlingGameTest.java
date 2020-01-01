package assignments.softwareengineering.bowlingproblem;

import static org.junit.Assert.*;

import org.junit.Test;

public class BowlingGameTest {

	@Test
	public void testShowFrameScoreNormal() {
		BowlingGame bowling = new BowlingGame();
		String actual = bowling.showFrameScore(4,3);
		String expected = "7";
		assertEquals(expected,actual);
	}
	
	@Test
	public void testShowFrameScoreStrike() {
		BowlingGame bowling = new BowlingGame();
		String actual = bowling.showFrameScore(0,10);
		String expected = "STRIKE";
		assertEquals(expected,actual);
	}
	
	@Test
	public void testShowFrameScoreSpare() {
		BowlingGame bowling = new BowlingGame();
		String actual = bowling.showFrameScore(6,4);
		String expected = "SPARE";
		assertEquals(expected,actual);
	}


	@Test
	public void testBowlingNormal() {
		Frames frames = new Frames();
		BowlingGame bowling = new BowlingGame();
		frames.getFrames()[0].setFrameScore(6);
		frames.getFrames()[1].setFrameScore(8);
		frames.getFrames()[2].setFrameScore(7);
		frames.getFrames()[3].setFrameScore(5);
		frames.getFrames()[4].setFrameScore(2);
		frames.getFrames()[5].setFrameScore(6);
		frames.getFrames()[6].setFrameScore(3);
		frames.getFrames()[7].setFrameScore(3);
		frames.getFrames()[8].setFrameScore(1);
		frames.getFrames()[9].setFrameScore(8);
		bowling.setFrames(frames);
		int actual = bowling.bowling();
		int expected = 49;
		assertEquals(expected,actual);
	}
	
	@Test
	public void testBowlingPerfectGame() {
		Frames frames = new Frames();
		BowlingGame bowling = new BowlingGame();
		frames.getFrames()[0].setFrameScore(30);
		frames.getFrames()[1].setFrameScore(30);
		frames.getFrames()[2].setFrameScore(30);
		frames.getFrames()[3].setFrameScore(30);
		frames.getFrames()[4].setFrameScore(30);
		frames.getFrames()[5].setFrameScore(30);
		frames.getFrames()[6].setFrameScore(30);
		frames.getFrames()[7].setFrameScore(30);
		frames.getFrames()[8].setFrameScore(30);
		frames.getFrames()[9].setFrameScore(30);
		bowling.setFrames(frames);
		int actual = bowling.bowling();
		int expected = 300;
		assertEquals(expected,actual);
	}

}
