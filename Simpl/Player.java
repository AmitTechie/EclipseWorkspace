package Simpl;

public class Player {
	private String playerName;
	private int batingSkill; // 10 is highest, 1 is lowest
	private int bowligSkill; // 10 is highest, 1 is lowest
	private PlayerProbability playerProbability;
	
	public Player(String name) {
		super();
		this.playerName = name;
		this.batingSkill = 5;
		playerProbability = new PlayerProbability(name);
	}
	
	public Player(String name, int battingSkill, int bowlingSkill) {
		super();
		this.playerName = name;
		this.batingSkill = battingSkill;
		playerProbability = new PlayerProbability(name);
	}
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getBatingSkill() {
		return batingSkill;
	}

	public void setBatingSkill(int batingSkill) {
		this.batingSkill = batingSkill;
	}

	public int getBowligSkill() {
		return bowligSkill;
	}

	public void setBowligSkill(int bowligSkill) {
		this.bowligSkill = bowligSkill;
	}

	public BALL_RESULT playBall(Ball ball) {
		
		//no check for INVALID, NO_BALL or WIDE ball, as invalid balls are out of scope for this problem statement 
		//System.out.println(playerName);
		
		ball.setBatsman(this);
		return playerProbability.decideBallResult(ball);
		
	}

}
