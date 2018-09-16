package Simpl;

public class Ball {

	private int ballNumber;
	private Player bowler; // who thrown this ball
	private Player batsman; // who played this ball
	private int runFromThisBall;
	private PLAYER_STATUS player_status = PLAYER_STATUS.NOT_OUT; //batsman status on this ball, for example if batsman is out on this ball then player_status will be set  as OUT otherwise NOT_OUT
	
	public int getRunFromThisBall() {
		return runFromThisBall;
	}
	public void setRunFromThisBall(int runFromThisBall) {
		this.runFromThisBall = runFromThisBall;
	}
	public Player getBatsman() {
		return batsman;
	}
	public void setBatsman(Player batsman) {
		this.batsman = batsman;
	}
	public Player getBowler() {
		return bowler;
	}
	public void setBowler(Player bowler) {
		this.bowler = bowler;
	}

	public int getBallNumber() {
		return ballNumber;
	}
	public void setBallNumber(int ballNumber) {
		this.ballNumber = ballNumber;
	}
	public PLAYER_STATUS getPlayerStatus() {
		return player_status;
	}
	public void setPlayerStatus(PLAYER_STATUS player_status) {
		this.player_status = player_status;
	}
}
