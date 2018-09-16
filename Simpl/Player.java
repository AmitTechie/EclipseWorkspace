package Simpl;

public class Player {
	private String playerName;
	private PlayerProbability playerProbability;
	
	public Player(String name) {
		super();
		this.playerName = name;
		playerProbability = new PlayerProbability(name);
	}
		
	public String getPlayerName() {
		return playerName;
	}

	public BALL_RESULT playBall(Ball ball) {
		if(ball == null) {
			//assume null ball means dot ball
			return BALL_RESULT.DOT_BALL;
		}
		
		//no check for NO_BALL or WIDE ball, as invalid balls are out of scope for this problem statement 
		
		ball.setBatsman(this);
		return playerProbability.decideBallResult(ball);
		
	}

}
