package Simpl;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class MainClass {

	public static void main(String[] args) {
		
		//Unit tests..comment the below line to stop unit tests
		UnitTestDriver.runUnitTests();

		Team team1 = new Team("Chennai");
		team1.addPlayer(new Player("Chennai_Player1"));
		team1.addPlayer(new Player("Chennai_Player2"));
		team1.addPlayer(new Player("Chennai_Player3"));
		team1.addPlayer(new Player("Chennai_Player4"));
		team1.addPlayer(new Player("Chennai_Player5"));
		team1.addPlayer(new Player("Chennai_Player6"));
		team1.addPlayer(new Player("Chennai_Player7"));
		team1.addPlayer(new Player("Chennai_Player8"));
		team1.addPlayer(new Player("Chennai_Player9"));
		team1.addPlayer(new Player("Chennai_Player10"));
		team1.addPlayer(new Player("Chennai_Player11"));

		Team team2 = new Team("Bangalore");
		team2.addPlayer(new Player("Bangalore_Player1"));
		team2.addPlayer(new Player("Bangalore_Player2"));
		team2.addPlayer(new Player("Bangalore_Player3"));
		team2.addPlayer(new Player("Bangalore_Player4"));
		team2.addPlayer(new Player("Bangalore_Player5"));
		team2.addPlayer(new Player("Bangalore_Player6"));
		team2.addPlayer(new Player("Bangalore_Player7"));
		team2.addPlayer(new Player("Kirat"));
		team2.addPlayer(new Player("N.S Nodhi"));
		team2.addPlayer(new Player("R Rumrah"));
		team2.addPlayer(new Player("Shashi Henra"));
		
		Match match = new Match(team1, team2, MATCH_TYPE.T20, 20);
		
		System.out.println("\nMATCH is ready to play\n");
		
		// comment the below code to simulate entire T20 match.
		// setFirstInningMatchDataForSecondInningSimulation() is taking the match to the point where Bangalore team would required 40 runs from 
		// four hours. If you comment the below code then it would simulate the entire T20 match from starting and you will see each bowl update
		// each over update.
		//match.setFirstInningMatchDataForSecondInningSimulation();
		
		match.startMatch();
	}

}
