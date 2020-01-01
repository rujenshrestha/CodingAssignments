package assignments.softwareengineering.bowlingproblem;

import static org.junit.Assert.*;

import org.junit.Test;

public class BowlingTest {

	@Test
	public void testBowling() {
		Bowling bowl = new Bowling();
		int actual = bowl.bowling();
		int expected = 300;
		assertEquals(expected,actual);
		
	}

	@Test
	public void testSetFrameScore() {
		Bowling bowl = new Bowling();
		Frame frame = new Frame();
		frame = bowl.setFrameScore(frame);
		int expected = 8;
		int actual = frame.getFrameScore();
		assertEquals(expected,actual);
		
	}

	@Test
	public void testBonusRoll() {
		Bowling bowl = new Bowling();
		Frame frame = new Frame();
		frame = bowl.bonusRoll(frame);
		int expected = 10;
		int actual = frame.getFrameScore();
		assertEquals(expected,actual);
	}

}
