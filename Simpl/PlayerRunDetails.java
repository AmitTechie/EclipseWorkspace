package Simpl;

enum PLAYER_STATUS{
	OUT,
	NOT_OUT
}

public class PlayerRunDetails {
	private int totalBallsPlayed = 0;
	private int totalRuns = 0;
	private PLAYER_STATUS player_status;
	
	public int getTotalBallsPlayed() {
		return totalBallsPlayed;
	}
	public void setTotalBallsPlayed(int totalBallsPlayed) {
		this.totalBallsPlayed += totalBallsPlayed;
	}
	public int getTotalRuns() {
		return totalRuns;
	}
	public void setTotalRuns(int totalRuns) {
		this.totalRuns += totalRuns;
	}
	public PLAYER_STATUS getPlayerStatus() {
		return player_status;
	}
	public void setPlayerStatus(PLAYER_STATUS player_status) {
		this.player_status = player_status;
	}
}
