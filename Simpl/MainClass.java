package Simpl;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
		
		System.out.println("MATCH is ready to play");
		match.setFirstInningMatchDataForSecondInningSimulation();
		
		match.startMatch();
	}

}
