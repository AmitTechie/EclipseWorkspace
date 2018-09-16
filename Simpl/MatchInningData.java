package Simpl;

import java.util.HashMap;
import java.util.Map;

public class MatchInningData {
	
	private Map<Player, PlayerRunDetails> playersScoreDetails;
	//private Map<Player, PlayerBowlingDetails> playerBowlingDetails; // this is not required in our problem statement so leaving it unimplemented
	private int totalScore = 0;
	private int totalBallsPlayed = 0;
	private int outPlayersCount = 0;
	
	public void updatePlayerRun(Player player, int run, PLAYER_STATUS player_status) {
		
		if(playersScoreDetails == null) {
			playersScoreDetails = new HashMap<>();
		}
		
		PlayerRunDetails playerRunDetails = playersScoreDetails.getOrDefault(player, new PlayerRunDetails());
		playerRunDetails.setTotalRuns(playerRunDetails.getTotalRuns()+run);
		playerRunDetails.setTotalBallsPlayed(playerRunDetails.getTotalBallsPlayed()+1);
		playerRunDetails.setPlayerStatus(player_status);
		playersScoreDetails.put(player, playerRunDetails);
		if(player_status.equals(PLAYER_STATUS.OUT)) {
			setOutPlayersCount(getOutPlayersCount()+1);
		}
		
		setTotalScore(getTotalScore() + run);
		totalBallsPlayed++;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int run) {
		this.totalScore = run;
	}

	public int getTotalBallsPlayed() {
		return totalBallsPlayed;
	}

	public void setTotalBallsPlayed(int totalBallsPlayed) {
		this.totalBallsPlayed += totalBallsPlayed;
	}

	public int getOutPlayersCount() {
		return outPlayersCount;
	}

	public void setOutPlayersCount(int outPlayersCount) {
		this.outPlayersCount = outPlayersCount;
	}
	
	public void displayPayersScore() {
		for(Player player: playersScoreDetails.keySet()) {
			PlayerRunDetails playerRunDetails = playersScoreDetails.get(player);
			String playerScoreCard = player.getPlayerName() + " - " + playerRunDetails.getTotalRuns() + " ("+playerRunDetails.getTotalBallsPlayed()+" balls)";
			System.out.println(playerScoreCard);
		}
	}
	
}
