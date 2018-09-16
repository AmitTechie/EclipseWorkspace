package Simpl;

import java.util.HashMap;
import java.util.Map;

public class MatchInningData {
	
	private Map<Player, PlayerRunDetails> playersScoreDetails;
	//private Map<Player, PlayerBowlingDetails> playerBowlingDetails; // this is not required in our problem statement so leaving it unimplemented
	private int totalScore;
	private int totalBallsPlayed;
	private int outPlayersCount;
	
	public MatchInningData(){
		totalScore = 0;
		totalBallsPlayed = 0;
		outPlayersCount = 0;
	}
	
	public void updatePlayerRun(Player player, int run, PLAYER_STATUS player_status) {
		
		
		//System.out.println("player: "+ player.getPlayerName()+" run: "+ run+" status: "+ player_status);
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
		this.totalBallsPlayed = totalBallsPlayed;
	}

	public int getOutPlayersCount() {
		return this.outPlayersCount;
	}

	public void setOutPlayersCount(int outPlayersCount) {
		this.outPlayersCount = outPlayersCount;
	}
	
	public void displayPayersScore() {
		
		for(Player player: playersScoreDetails.keySet()) {
			PlayerRunDetails playerRunDetails = playersScoreDetails.get(player);
			
			String star = "";
			if(playerRunDetails.getPlayerStatus().equals(PLAYER_STATUS.NOT_OUT)) {
				star = "*";
			}
			
			String playerScoreCard = player.getPlayerName() + " - " + playerRunDetails.getTotalRuns() + star + " ("+playerRunDetails.getTotalBallsPlayed()+" balls)";
			System.out.println(playerScoreCard);
		}
	}
	
}
