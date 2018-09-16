package Simpl;

import java.util.LinkedHashSet;

public class TeamBattingDetails {
	private int nextBatsmanIndex;
	private int onStrikePlayerIndex = 0;
	private int offStrikePlayerIndex = 1;
	private LinkedHashSet<Integer> outPlayerList;
	private boolean isBattingOver;
	private int battingLength;
	
	public TeamBattingDetails(int teamSize) {
		this.isBattingOver = false;
		this.battingLength = teamSize;
		this.outPlayerList = new LinkedHashSet<>();
		this.nextBatsmanIndex = 2;
		this.onStrikePlayerIndex = 0;
		this.offStrikePlayerIndex = 1;
	}
	

	public int getOnStrikePlayerIndex() {
		if(this.isBattingOver) {
			return -1; // invalid index as no more player left to bat
		}
		
		return onStrikePlayerIndex;
	}

	private boolean setOnStrikePlayerIndex(int playerIndex) {
		if(this.isBattingOver || outPlayerList.contains(playerIndex) || playerIndex < 0 || playerIndex >= battingLength) {
			return false; // as batting is over
		}
		
		onStrikePlayerIndex = playerIndex;
		return true;
	}
	
	public int getOffStrikePlayerIndex() {
		return offStrikePlayerIndex;
	}
	
	public boolean changeStrike() {
		
		if(isBattingOver) {
			return false;
		}
		
		int tmp = onStrikePlayerIndex;
		onStrikePlayerIndex = offStrikePlayerIndex;
		offStrikePlayerIndex = tmp;
		return true;
	}
	
	public int getNextBatsmanIndex() {
		if(isBattingOver) {
			return -1;
		}
		return nextBatsmanIndex;
	}
	
	private void updateNextBatsManIndex() {
		if(!isBattingOver) {
			nextBatsmanIndex++;
		}
	}
	
	public boolean setPlayerAsOut(int playerIndex) {
		
		boolean result = outPlayerList.add(playerIndex);
		
		if(result == true) {
			isBattingOver = outPlayerList.size() == this.battingLength-1 ? true : false;
		}
		
		//set next batsman on strike
		if(!isBattingOver) {
			setOnStrikePlayerIndex(getNextBatsmanIndex());
			updateNextBatsManIndex();
		}
		
		//System.out.println("Out player: "+ playerIndex + " next on strile player: "+ getOnStrikePlayerIndex());
		return result;
	}
	
	public int getOutPlayerCount() {
		return outPlayerList.size();
	}
	
	public boolean isBattingDone() {
		return isBattingOver;
	}
}
