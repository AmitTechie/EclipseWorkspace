package Simpl;

public class ScoreBoard {
	
	private int targetRun;
	private int targetOver;
	
	public void setTargetRunAndOvers(int targetRun, int targetOver) {
		
		if(targetRun <= 0 || targetOver <= 0) {
			throw new IllegalArgumentException("invalid targetRun or targetOver");
		}
		
		this.targetRun = targetRun;
		this.targetOver = targetOver;
	}
}
