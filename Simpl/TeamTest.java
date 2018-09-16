package Simpl;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class TeamTest {
	
	@Test
	public void teamAddSamePlayerMulitpleTimeTest() {
		
		Team team = new Team("Rockers");
		Player player = new Player("Amit");
		assertEquals(true, team.addPlayer(player));
		assertEquals(false, team.addPlayer(player));
	}

	@Test
	public void teamSizeNotProperTest() {
		
		Team team = new Team("Rockers");
		Player player = new Player("Amit");
		team.addPlayer(player);
		assertEquals(false, team.isTeamSizeProper());
	}

	@Test
	public void teamSizeProperTest() {
		
		Team team = new Team("Rockers1");
		assertEquals(true, team.addPlayers(Arrays.asList(new Player("player1"), new Player("player2"), new Player("player3"), 
				new Player("player4"), new Player("player5"), new Player("player6"), new Player("player7"), 
				new Player("player8"), new Player("player9"), new Player("player10"), new Player("player11"))));
		assertEquals(true, team.isTeamSizeProper());
	}
}
