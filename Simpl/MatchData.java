package Simpl;

import java.util.HashMap;

public class MatchData {
	private MatchInningData matchFirstInningData;
	private MatchInningData matchSecondInningData;
	private int oversToPlay;
	private MATCH_TYPE match_type;
	public static final int ballCountPerOver = 6;
	public static final int playerCountPerTeam = 11;

	public MatchData(){
		this.matchFirstInningData = new MatchInningData();
		this.matchSecondInningData = new MatchInningData();
	}


	public boolean isFirstInningDone() {
		return matchFirstInningData.getTotalBallsPlayed() == oversToPlay*ballCountPerOver || matchFirstInningData.getOutPlayersCount() == (playerCountPerTeam -1);
	}

	public boolean isSecondInningDone() {
		if(matchSecondInningData.getTotalBallsPlayed() == oversToPlay*ballCountPerOver || 
				matchSecondInningData.getOutPlayersCount() == (playerCountPerTeam -1)  || (matchFirstInningData.getTotalScore() > 0 && 
						matchSecondInningData.getTotalScore() > matchFirstInningData.getTotalScore())) {
			return true;
		}
		return false;
	}

	public int getOversToPlay() {
		return oversToPlay;
	}

	public void setOversToPlay(int oversToPlay) {
		this.oversToPlay = oversToPlay;
	}

	public MATCH_TYPE getMatchType() {
		return match_type;
	}

	public void setMatchType(MATCH_TYPE match_type) {
		this.match_type = match_type;
	}

	public void updateInningData(Ball ball) {
		if(ball == null) {
			return;
		}

		//get data for batting team
		updatePlayerScore(ball.getBatsman(), ball.getRunFromThisBall(), ball.getPlayerStatus());

		//we can get data for bowling team as well but out of score as per problem statement

		//broadcast commentary..
		broadcastCommentary(ball);
	}

	public void broadcastCommentary(Ball ball) {

		if(ball == null) {
			return;
		}
		
		//broadcast ball commentary..
		{
			String run = ball.getRunFromThisBall() > 1 ? "runs " : "run ";
			String commentaryDataPerBall = ball.getOver()+"."+ball.getBallNumber() + " " + ball.getBatsman().getPlayerName() + " scores "+ run + ball.getRunFromThisBall();
			System.out.println(commentaryDataPerBall);
		}


		if (!isFirstInningDone()) {
			broadcastFirstInningScore();
		}else if(!isSecondInningDone() && matchSecondInningData.getTotalBallsPlayed() > 0) {
			//broadcast second team target after over end
			broadcastSecondInningTargetScore();
		}
	}
	
	//return the first inning out players count
	public int getFirstInningWickets() {
		return matchFirstInningData.getOutPlayersCount();
	}

	//return the balls played by first inning team
	public int getFirstInningBallsPlayed() {
		return matchFirstInningData.getTotalBallsPlayed();
	}
	
	//return the balls played by second inning team
	public int getSecondInningBallsPlayed() {
		return matchSecondInningData.getTotalBallsPlayed();
	}
	
	//return the second inning out players count
	public int getSeconInningWickets() {
		return matchSecondInningData.getOutPlayersCount();
	}
	
	
	
	public void broadcastFirstInningScore() {
		if(matchFirstInningData.getTotalBallsPlayed() > 0 && matchFirstInningData.getTotalBallsPlayed() % ballCountPerOver == 0) {
			System.out.println("Total score: "+matchFirstInningData.getTotalScore()+" after "+ matchFirstInningData.getTotalBallsPlayed()/ballCountPerOver);
		}
	}
	
	public void broadcastSecondInningTargetScore() {
		if(matchSecondInningData.getTotalBallsPlayed() % ballCountPerOver == 0) {
			int oversLeft = oversToPlay - matchSecondInningData.getTotalBallsPlayed()/ballCountPerOver;
			int runsToWin = matchFirstInningData.getTotalScore() - matchSecondInningData.getTotalScore() + 1;
			String commentryDataPerOver = oversLeft + " overs left." + runsToWin +" runs to win";
			System.out.println(commentryDataPerOver);
		}
	}
	
	public void updatePlayerScore(Player player, int run, PLAYER_STATUS player_status) {

		if (!isFirstInningDone()) {
			matchFirstInningData.updatePlayerRun(player, run, player_status);
		}else {
			matchSecondInningData.updatePlayerRun(player, run, player_status);
			//System.out.println(player.getPlayerName() + " "+ " run: "+ run + " player status: "+ player_status);
		}
	}

	public int getFirstInningTotalScore() {
		return matchFirstInningData.getTotalScore();
	}

	public int getSecondInningTotalScore() {
		return matchSecondInningData.getTotalScore();
	}

	public boolean isSecondTeamWinner() {

		if(isFirstInningDone() && isSecondInningDone()) {
			if(matchFirstInningData.getTotalScore() < matchSecondInningData.getTotalScore()) {
				return true;
			}
		}

		return false;
	}

	public boolean isFirstTeamWinner() {

		if(isFirstInningDone() && isSecondInningDone()) {
			if(matchFirstInningData.getTotalScore() > matchSecondInningData.getTotalScore()) {
				return true;
			}
		}

		return false;
	}

	public boolean isMatchTie() {
		if(isFirstInningDone() && isSecondInningDone()) {
			if(matchFirstInningData.getTotalScore() == matchSecondInningData.getTotalScore()) {
				return true;
			}
		}
		return false;
	}

	public String declareWinner(Team team1, Team team2) {

		if(!isFirstInningDone() || !isSecondInningDone()) {
			return null;
		}

		String matchResult = null;

		if(isFirstTeamWinner()) {

			int runDifference = getFirstInningTotalScore() - getSecondInningTotalScore();
			matchResult = team1.getTeamName() + " won by " + runDifference + " runs";

			//display match result..
			System.out.println(matchResult);

			matchFirstInningData.displayPayersScore();

		}else if(isSecondTeamWinner()) {

			int wicketDifferece = 10 - matchSecondInningData.getOutPlayersCount();
			String wicket = wicketDifferece > 1 ? wicketDifferece + " wickets " : wicketDifferece + " wicket ";
			int ballsLeft = oversToPlay*ballCountPerOver - matchSecondInningData.getTotalBallsPlayed();
			matchResult = team2.getTeamName() + " won by " + wicket + "and " + ballsLeft + " balls remaining";

			//display match result..
			System.out.println("\n"+matchResult);

			matchSecondInningData.displayPayersScore();

		}else if(isMatchTie()) {

			matchResult = "Match tie between "+team1.getTeamName()+ " and "+team2.getTeamName();

			//display match result..
			System.out.println(matchResult);

			matchSecondInningData.displayPayersScore();

		}

		return matchResult;

	}
}
