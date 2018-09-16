package Simpl;

public enum BALL_RESULT {
	DOT_BALL(0),
	ONE_RUN(1),
	TWO_RUNS(2),
	THREE_RUNS(3),
	FOUR_RUNS(4),
	FIVE_RUNS(5),
	SIX_RUNS(6),
	OUT(7);
	
	private int ballValue;

    private BALL_RESULT(int ballValue) {
        this.ballValue = ballValue;
    }
    
    public int getBalValue() {
    	return ballValue;
    }
}
