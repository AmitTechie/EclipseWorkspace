package Simpl;

import java.util.Arrays;

import org.junit.Test;

public class MatchTest {

	@Test(expected = RuntimeException.class)
	public void matchStartWithNoTeamsTest(){
		
		Match match = new Match();
		match.startMatch();
		
	}
	
	@Test(expected = RuntimeException.class)
	public void createMatchWithIncompleteTeamsTest(){
		new Match(new Team("team1"), new Team("team2"), MATCH_TYPE.T20, 20);
	}

	@Test(expected = RuntimeException.class)
	public void createMatchWithNullTeamsTest(){
		new Match(null, new Team("team2"), MATCH_TYPE.T20, 20);
	}

	@Test(expected = RuntimeException.class)
	public void createMatchWithSameTeamsTest(){
		Team team = new Team("team");
		team.addPlayers(Arrays.asList(new Player("player1"), new Player("player2"), new Player("player3"), 
				new Player("player4"), new Player("player5"), new Player("player6"), new Player("player7"), 
				new Player("player8"), new Player("player9"), new Player("player10"), new Player("player11")));
		new Match(team, team, MATCH_TYPE.T20, 20);
	}
	
	@Test(expected = RuntimeException.class)
	public void createMatchWithWrongOversTeamsTest(){
		Team team1 = new Team("team1");
		team1.addPlayers(Arrays.asList(new Player("player1"), new Player("player2"), new Player("player3"), 
				new Player("player4"), new Player("player5"), new Player("player6"), new Player("player7"), 
				new Player("player8"), new Player("player9"), new Player("player10"), new Player("player11")));
		Team team2 = new Team("team2");
		team1.addPlayers(Arrays.asList(new Player("player12"), new Player("player13"), new Player("player14"), 
				new Player("player15"), new Player("player16"), new Player("player17"), new Player("player18"), 
				new Player("player19"), new Player("player20"), new Player("player21"), new Player("player22")));
		
		new Match(team1, team2, MATCH_TYPE.T20, 10);
	}


	@Test(expected = RuntimeException.class)
	public void createMatchSameTeamNamesTest(){
		Team team1 = new Team("team");
		team1.addPlayers(Arrays.asList(new Player("player1"), new Player("player2"), new Player("player3"), 
				new Player("player4"), new Player("player5"), new Player("player6"), new Player("player7"), 
				new Player("player8"), new Player("player9"), new Player("player10"), new Player("player11")));
		Team team2 = new Team("team");
		team1.addPlayers(Arrays.asList(new Player("player12"), new Player("player13"), new Player("player14"), 
				new Player("player15"), new Player("player16"), new Player("player17"), new Player("player18"), 
				new Player("player19"), new Player("player20"), new Player("player21"), new Player("player22")));
		
		new Match(team1, team2, MATCH_TYPE.T20, 10);
	}	
}
