package Simpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerProbability {

	// 0 index will represent as dot ball   =>  value at index 0, will represent as probability for a dot ball
	// 1 index will represent as 1 run		=>  value at index 1, will represent as probability for getting 1 run from the ball
	// 2 index will represent as 2 run		=>  value at index 2, will represent as probability for getting 2 run from the ball
	// 3 index will represent as 3 run		=>  value at index 3, will represent as probability for getting 3 run from the ball
	// 4 index will represent as 4 run		=>  value at index 4, will represent as probability for getting 4 run from the ball
	// 5 index will represent as 5 run		=>  value at index 5, will represent as probability for getting 5 run from the ball
	// 6 index will represent as 6 run		=>  value at index 6, will represent as probability for getting 6 run from the ball
	// 7 index will represent as out		=>  value at index 7, will represent as probability for getting out of the ball	
	private int[] probabilityList;

	//mappedProbability array will be having
	private BALL_RESULT[] mappedProbability; 
	private RandomNumberGenerator randomNumberGenerator;

	public PlayerProbability(String player){
		probabilityList = PlayerProbabilityData.getProbailityList(player);

		if(probabilityList != null) {
			
			//initialize randomNumberGenerator
			randomNumberGenerator = new RandomNumberGenerator();

			//distribute probability..
			mappedProbability = new BALL_RESULT[100];
			int mappedProbabilityIndex = 0;
			for(int i=0; i<probabilityList.length; i++) {

				switch(i) {
				case 0:
				{
					//probability of dot ball..
					int dotBallProbability = probabilityList[i];
					for(int j=0; j<dotBallProbability; j++) {
						mappedProbability[mappedProbabilityIndex++] = BALL_RESULT.DOT_BALL;
					}
					break;
				}
				case 1:
				{
					//probability of 1 run..
					int oneRunProbability = probabilityList[i];
					for(int j=0; j<oneRunProbability; j++) {
						mappedProbability[mappedProbabilityIndex++] = BALL_RESULT.ONE_RUN;
					}
				}
				break;
				case 2:
				{
					//probability of 2 run..
					int twoRunsProbability = probabilityList[i];
					for(int j=0; j<twoRunsProbability; j++) {
						mappedProbability[mappedProbabilityIndex++] = BALL_RESULT.TWO_RUNS;
					}
				}
				break;
				case 3:
				{
					//probability of 3 run..
					int threeRunsProbability = probabilityList[i];
					for(int j=0; j<threeRunsProbability; j++) {
						mappedProbability[mappedProbabilityIndex++] = BALL_RESULT.THREE_RUNS;
					}
				}
				break;
				case 4:
				{
					//probability of 4 run..
					int fourRunsProbability = probabilityList[i];
					for(int j=0; j<fourRunsProbability; j++) {
						mappedProbability[mappedProbabilityIndex++] = BALL_RESULT.FOUR_RUNS;
					}
				}
				break;
				case 5:
				{
					//probability of 5 run..
					int fiveRunsProbability = probabilityList[i];
					for(int j=0; j<fiveRunsProbability; j++) {
						mappedProbability[mappedProbabilityIndex++] = BALL_RESULT.FIVE_RUNS;
					}
				}
				break;
				case 6:	
				{
					//probability of 4 run..
					int sixRunsProbability = probabilityList[i];
					for(int j=0; j<sixRunsProbability; j++) {
						mappedProbability[mappedProbabilityIndex++] = BALL_RESULT.SIX_RUNS;
					}
				}
				break;
				case 7:
				{

					//probability of out..
					int outProbability = probabilityList[i];
					for(int j=0; j<outProbability; j++) {
						mappedProbability[mappedProbabilityIndex++] = BALL_RESULT.OUT;
					}
				}
				}
			}			
		}

	}

	public BALL_RESULT decideBallResult(Ball ball) {

		//assuming all balls are valid, invalid ball is out of score for this problem statement
		return mappedProbability[randomNumberGenerator.getRandomNumber()];
	}
}
