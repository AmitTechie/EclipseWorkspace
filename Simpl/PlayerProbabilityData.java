package Simpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerProbabilityData {
	
	private static Map<String, int[]> playerProbabilityData;
	
	static {
		playerProbabilityData = new HashMap<>();
		
		//fill probability data (in %) for player in sequence (probability of dot ball, 
		// probability of 1 run, probability of 2 runs, probability of 3 runs, probability of 4 runs, 
		// probability of 5 runs, probability of 6 runs, probability of out)

		playerProbabilityData.put("Kirat", new int[] {5, 30, 25, 10, 15, 1, 9, 5}); 
		playerProbabilityData.put("N.S Nodhi", new int[] {10, 40, 20, 5, 10, 1, 4, 10}); 
		playerProbabilityData.put("R Rumrah", new int[] {20, 30, 15, 5, 5, 1, 4, 20}); 
		playerProbabilityData.put("Shashi Henra", new int[] {30, 25, 5, 0, 5, 1, 4, 30}); 
	}
	
	public static int[] getProbailityList(String playerName){
		if(playerName == null) {
			return null;
		}
		return playerProbabilityData.getOrDefault(playerName, new int[] {39, 33, 8, 5, 4, 1, 1, 9});
	}
	
}
