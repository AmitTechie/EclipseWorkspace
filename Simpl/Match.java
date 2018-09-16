package Simpl;

enum MATCH_TYPE{
	T20,
	ONE_DAY,
	TEST_MATCH
}

public class Match{

	private InningTeam firstInningTeam; //considering the team who is doing batting first..
	private InningTeam secondInningTeam; //considering the team who is doing batting in second inning..
	private MatchData matchData;
	
	public Match() {
		
	}

	public Match(Team team1, Team team2, MATCH_TYPE match_type, int overs) {

		if(team1 == null || !team1.isTeamSizeProper() || team2 == null || !team2.isTeamSizeProper() || team1 == team2) {
			throw new IllegalArgumentException("Invalid team");
		}
		
		if(match_type.equals(MATCH_TYPE.T20) && overs != 20) {
			throw new IllegalArgumentException("Invalid over size");
		}

		if(matchData == null) {
			matchData = new MatchData();
		}

		this.matchData.setMatchType(match_type);
		this.matchData.setOversToPlay(overs);
		this.firstInningTeam = new InningTeam(team1);
		this.secondInningTeam = new InningTeam(team2);
	}

	public boolean setOvers(int overs) {
		if(this.matchData.getMatchType().equals(MATCH_TYPE.T20) && overs != 20) {
			return false; //invalid overs
		}
		
		this.matchData.setOversToPlay(overs);
		return true;
	}
	
	public boolean addTeam(Team team) {

		if(firstInningTeam != null && secondInningTeam != null) {
			return false;
		}

		if(!team.isTeamSizeProper() || (firstInningTeam != null && firstInningTeam.getTeam() == team) || (secondInningTeam != null && secondInningTeam.getTeam() == team)) {
			return false;
		}

		if(firstInningTeam == null) {
			this.firstInningTeam = new InningTeam(team);
		}

		if(secondInningTeam == null) {
			this.secondInningTeam = new InningTeam(team);
		}

		return true;
	}
	
	//just to simulate the match as per given problem statement. I wrote the Match api's such that you can't directly manipulate data but you have to
	//run scenarios to put the required data. So in this API I'm creating scenarios to move the match till simulation point.
	public void setFirstInningMatchDataForSecondInningSimulation() {
		if(matchData == null) {
			matchData = new MatchData();
		}

		//As target run for second inning is 40 runs to win, so first inning team runs would be 39 runs
		//so to get 39 runs from first inning, simplest way I thought is to make 0 runs in 18.5 overs and make 3 run on 18th over last ball and make 36 run
		//in the last 20th over by hitting 6 on all balls.
		for(int over=0; over < matchData.getOversToPlay(); over++) {
			
			for(int ball = 1; ball <= 6; ball++) {
				
				if(over == 19) {
					//hit all six
					matchData.updatePlayerScore(firstInningTeam.getOnStrikePlayer(), 6, PLAYER_STATUS.NOT_OUT);
				}else{
					if (over == 18 && ball == 6) {
						matchData.updatePlayerScore(firstInningTeam.getOnStrikePlayer(), 3, PLAYER_STATUS.NOT_OUT);
						//odd run, so change the strike
						firstInningTeam.changePlayersStikeOrder();
					}else {
						// 0 runs for first 18.5 overs
						matchData.updatePlayerScore(firstInningTeam.getOnStrikePlayer(), 0, PLAYER_STATUS.NOT_OUT);
					}
				}
			}
			//over end, change the strike
			firstInningTeam.changePlayersStikeOrder();
		}
		
		System.out.println(firstInningTeam.getTeam().getTeamName()+" Score: "+ matchData.getFirstInningTotalScore());
		
		// As target run to win the match for second team is 40. 
		// So I'm considering that out of 20 overs, first 16 overs played by first 7 players of Bangalore teams and they scored 0 runs and all got out.
		// Now we have 4 players () left in bating lines and 4 overs are left to score 40 runs.
		
		for(int over=0; over <= 15; over++) {
			
			for(int ball = 1; ball <= 6; ball++) {
				
				if(over == 15 && ball == 6) {
					//player out on 15th over's last ball
					matchData.updatePlayerScore(secondInningTeam.getOnStrikePlayer(), 0, PLAYER_STATUS.OUT);
					secondInningTeam.markAsOutPlayer(secondInningTeam.getOnStrikePlayer());
				}else{
					if (over == 14) {
						//all 6 players out in 14th over
						matchData.updatePlayerScore(secondInningTeam.getOnStrikePlayer(), 0, PLAYER_STATUS.OUT);
						secondInningTeam.markAsOutPlayer(secondInningTeam.getOnStrikePlayer());
					}else {
						// 0 runs for first 14.5 overs
						matchData.updatePlayerScore(secondInningTeam.getOnStrikePlayer(), 0, PLAYER_STATUS.NOT_OUT);
					}
				}
			}
			//over end, change the strike
			secondInningTeam.changePlayersStikeOrder();
		}
		
	}
	
	public boolean isFirstInningOver() {
		if (matchData.isFirstInningDone()) {
			return true;
		}
		return false;
	}
	
	public boolean isSecondInningOver() {
		if (matchData.isSecondInningDone()) {
			return true;
		}
		return false;
	}
	
	
	private void playInning(InningTeam batingTeam) {
		
		for(int over=0; over < matchData.getOversToPlay(); over++) {
			
			if ((batingTeam.equals(firstInningTeam) && isFirstInningOver()) || (batingTeam.equals(secondInningTeam)) && isSecondInningOver()) {
				break;
			}
			
			for(int ballCount = 1; ballCount <= MatchData.ballCountPerOver; ballCount++) {
				Ball ball = new Ball();
				ball.setBallNumber(ballCount);
				ball.setOver(over);
				
				//ball.setBowler(secondInningTeam.getBowler()); not required as per problem statement
				BALL_RESULT ball_result = batingTeam.getOnStrikePlayer().playBall(ball);
				
				
				if(ball_result.equals(BALL_RESULT.OUT)) {
					//player is out
					ball.setPlayerStatus(PLAYER_STATUS.OUT);
					ball.setRunFromThisBall(0);
					//mark player as out
					batingTeam.markAsOutPlayer(batingTeam.getOnStrikePlayer());
				}else if(ball_result.equals(BALL_RESULT.ONE_RUN) || ball_result.equals(BALL_RESULT.THREE_RUNS) || ball_result.equals(BALL_RESULT.FIVE_RUNS)) {
					ball.setRunFromThisBall(ball_result.getBalValue());
					//change strike
					batingTeam.changePlayersStikeOrder();
				}else if(ball_result.equals(BALL_RESULT.DOT_BALL) || ball_result.equals(BALL_RESULT.TWO_RUNS) || ball_result.equals(BALL_RESULT.FOUR_RUNS) || ball_result.equals(BALL_RESULT.SIX_RUNS) || ball_result.equals(BALL_RESULT.DOT_BALL)){
					ball.setRunFromThisBall(ball_result.getBalValue());
					//don't change strike
				}
				
				matchData.updateInningData(ball);
				if ((batingTeam.equals(firstInningTeam) && isFirstInningOver()) || (batingTeam.equals(secondInningTeam)) && isSecondInningOver()) {
					break;
				}
			}
			//over end, change the strike
			batingTeam.changePlayersStikeOrder();
		}		
	}

	private void playFirstInning() {
		
		if(isFirstInningOver()) {
			return;
		}
		
		System.out.println("\n Team "+firstInningTeam.getTeam().getTeamName() +" batting started.. \n\n");
		
		playInning(firstInningTeam);

		String oversPlayed = matchData.getFirstInningBallsPlayed()/MatchData.ballCountPerOver +"."+ matchData.getFirstInningBallsPlayed()%MatchData.ballCountPerOver;
		System.out.println("\n Team "+firstInningTeam.getTeam().getTeamName() +" total score: "+ matchData.getFirstInningTotalScore()+ " after "+oversPlayed+" with total wicket "+matchData.getFirstInningWickets()+" \n\n");
	}

	
	
	private void playSecondInning() {
		
		if(isSecondInningOver()) {
			return;
		}
		
		int targetRunToWin = matchData.getFirstInningTotalScore() + 1;
		System.out.println(secondInningTeam.getTeam().getTeamName() + " needs " + targetRunToWin + " runs to win from " + matchData.getOversToPlay()+" overs \n\n");
		
		matchData.broadcastSecondInningTargetScore();

		playInning(secondInningTeam);
	}
	
	public void declareMatchResult() {
		if(!isFirstInningOver() || !isSecondInningOver()) {
			System.out.println("Matching is not over yet..");
			return;
		}

		matchData.declareWinner(firstInningTeam.getTeam(), secondInningTeam.getTeam());
	}
	
	public void startMatch() {
		// TODO Auto-generated method stub
		if(firstInningTeam == null || secondInningTeam == null) {
			throw new RuntimeException("Invalid teams to start the Match");
		}
		
		playFirstInning();
		playSecondInning();
		declareMatchResult();		
	}

}
