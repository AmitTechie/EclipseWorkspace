package Simpl;

public class InningTeam {
	private Team team;
	private TeamBattingDetails teamBattingDetails;
	private TeamBowlingDetails teamBowlingDetails;
	
	public InningTeam(Team team) {
		this.team = team;
		this.teamBattingDetails = new TeamBattingDetails(Team.teamSize);
		this.teamBowlingDetails = new TeamBowlingDetails(team.getBowlerCount());
	}
	
	public Team getTeam() {
		return this.team;
	}
	
	public Player getOnStrikePlayer() {
	
		return team.getPlayer(teamBattingDetails.getOnStrikePlayerIndex());
	
	}

	public Player getOffStrikePlayer() {
		
		return team.getPlayer(teamBattingDetails.getOffStrikePlayerIndex());

	}

	public boolean changePlayersStikeOrder() {
		
		return teamBattingDetails.changeStrike();
	}
	
	public boolean markAsOutPlayer(Player player) {
		
		return teamBattingDetails.setPlayerAsOut(teamBattingDetails.getOnStrikePlayerIndex()); 
		
	}
	
	public Player getNextBatsMan() {
		
		Player nextPlayer = team.getPlayer(teamBattingDetails.getNextBatsmanIndex());
		if(nextPlayer == null) {
			//it means there is no batsman left in the team, so batting is over for the team..
			
			return null;
		}
		
		if(teamBattingDetails.getOnStrikePlayerIndex() == -1) {
			//On strike player is the last player who got out so mart onStrikePlayerIndex as nextBatsManIndex..
			return null;
		}
		return null;
		
	}
	
	public boolean isBattingDone() {
		return teamBattingDetails.isBattingDone();
	}

}
