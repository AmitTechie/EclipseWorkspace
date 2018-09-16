package Simpl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerTest {
	
	@Test
	public void playerBattingSkillTest() {
		
		Player player = new Player("TestPlayer");
		
		/*TEST1*/
		//test for null/invalid ball
		assertEquals(player.playBall(null), BALL_RESULT.DOT_BALL);

		/*TEST2   this is based on players probability data*/
		//test for a valid ball. This suppose to hit 6 for every ball. 
		//This player's six hitting probability is set as 100%. Means on each ball this player will hit 6
		//lets test for 75 balls
		int ballCount = 75;
		while(ballCount-- > 0) {
			assertEquals(player.playBall(new Ball()), BALL_RESULT.SIX_RUNS);
		}
	}
}
