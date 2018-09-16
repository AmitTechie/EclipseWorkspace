package Simpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Team {
	// considering batting order [0 to 10]. First player will be on strike as opener. Alternatively we can have bating skill ratings for each players and 
	// can sort the players list based on batting skills(higher bating skill as first player), sorted list we can consider as batting player order.
	// Similarly as batting skill we can have players bowling skill and we can sort players based on bowling skill (higher bowling skill as first player), sorted
	// list we can consider as bowling order. To set the bowlers limit, we can have bowlers number per team.
	private String teamName;
	public static final int teamSize = 11;
	private int bowlerCount;

	private List<Player> players; 
	
	Team(String name){
		this.teamName = name;
		this.players = new ArrayList<>(teamSize);
	}
	
	public boolean addPlayer(Player player) {

		if(players.size() == teamSize || players.contains(player)) {
			return false;
		}
		
		return players.add(player);
	}

	public boolean addPlayers(List<Player> players) {
		
		int existingPlayerCount = this.players != null ? this.players.size(): 0;
		
		if(players == null || players.isEmpty() || existingPlayerCount+players.size() > teamSize) {
			return false;
		}
		
		//check for duplicate players
		Set<Player> set = new HashSet<>();
		for(Player player: players) {
			if(set.contains(player)) {
				//duplicate players found in the players list..
				return false;
			}
			set.add(player);
		}
		
		return this.players.addAll(players);
	}
	
	
	public String getTeamName() {
		return teamName;
	}

	public boolean isTeamSizeProper() {
		return players.size() == teamSize;
	}
	
	public int getBowlerCount() {
		return bowlerCount;
	}

	public void setBowlerCount(int bowlerCount) {
		this.bowlerCount = bowlerCount;
	}
	
	public Player getPlayer(int playerIndex) {
		if(playerIndex < 0 || playerIndex >= teamSize) {
			return null;
		}
		
		return players.get(playerIndex);
	}
}
