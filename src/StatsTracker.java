/**
 * This class represents a stats tracker for a game of
 * Blackjack. This class consists of a list a stats for each hand of 
 * Blackjack played. The stats tracked include the average hand value 
 * for both dealer and player, the win percentage of both dealer and player,
 * and various stats for each hand (Hand ID, Dealer Total, Player Total, Hand Outcome)
 */

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StatsTracker {
	private ArrayList<HandStats> handStats;	
	
	/*
	 * Construct a Stats Tracker
	 */
	public StatsTracker() {
		this.handStats = new ArrayList<HandStats>();
	}	
	
	/*
	 * Record the stats for a single hand
	 */
	public void addStatsForHand(int handId, int dealerTotal, int playerTotal, String handOutcome) {
		handStats.add(new HandStats(handId, dealerTotal, playerTotal, handOutcome));
	}	
	
	/*
	 * Calculate the average points per hand for a dealer/player
	 */
	public String calcAvgPointsPerHand(boolean forPlayer) {
		double totalPoints = 0;		
				
		for(HandStats h : handStats) {
			totalPoints += forPlayer ? h.getPlayerTotal() : h.getDealerTotal();
		}
						
		DecimalFormat df = new DecimalFormat("0.00");      
		return df.format(totalPoints / handStats.size());
	}
	
	/*
	 * Calculate the win percentage for a dealer/player
	 */
	public String calcWinPercentage(boolean forPlayer) {
		double totalWins = 0;
		
		for(HandStats h : handStats) {
			if(forPlayer) {
				if(h.getHandOutcome().equals("WIN!!") || h.getHandOutcome().contentEquals("BLACKJACK!!")) {
					totalWins += 1;
				}
			} else {
				if(!(h.getHandOutcome().equals("WIN!!")) && !(h.getHandOutcome().contentEquals("BLACKJACK!!"))) {
					totalWins += 1;
				}
			}
		}
		
		DecimalFormat df = new DecimalFormat("0.00");      
		return (df.format((totalWins / handStats.size()) * 100) + "%");	
	}
	
	/*
	 * Retrieve a String representation of all of the stats for a game
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("<html><body><p style=\"font-size:15px\">");
		str.append("Dealer Average Points per Hand: <font color = red>" + calcAvgPointsPerHand(false) + "</font><br>");
		str.append("Player Average Points per Hand: <font color = red>" + calcAvgPointsPerHand(true) + "</font><br><br>");
		str.append("Dealer Win Percentage: <font color = red>" + calcWinPercentage(false) + "</font><br>");
		str.append("Player Win Percentage: <font color = red>" + calcWinPercentage(true) + "</font><br>");
		str.append("</p><br><br><p style=\"font-size:12px\">");		
		
		for(HandStats h : handStats) {
			str.append(h.toString());
		}		
		
		str.append("</p></body></html>");
				
		return str.toString();		
	}
	
	/**
	 * This is a nested class that represents the stats for one hand of Blackjack	 
	 */
	private static class HandStats {
		private int handId;
		private int dealerTotal;
		private int playerTotal;
		private String handOutcome;
			
		/*
		 * Construct and record the stats for one hand
		 */
		public HandStats(int handId, int dealerTotal, int playerTotal, String handOutcome) {
			this.handId = handId;
			this.dealerTotal = dealerTotal;
			this.playerTotal = playerTotal;
			this.handOutcome = handOutcome;
		}
				
		/*
		 * Retrieve the ID of the hand played
		 */
		public int getHandId() {
			return handId;
		}
				
		/*
		 * Retrieve the dealer's total for a hand
		 */
		public int getDealerTotal() {
			return dealerTotal;
		}
			
		/*
		 * Retreive the player's total for a hand
		 */
		public int getPlayerTotal() {
			return playerTotal;
		}
			
		/*
		 * Retrieve the outcome of the hand
		 */
		public String getHandOutcome() {
			return handOutcome;
		}
		
		/*
		 * Retrieve a String representation of the stats for a hand
		 */
		public String toString() {
			StringBuilder str = new StringBuilder();
			str.append("Hand ID: " + getHandId() + "<br>");
			str.append("Dealer Total: <font color = red>" + getDealerTotal() + "</font><br>");
			str.append("Player Total: <font color = red>" + getPlayerTotal() + "</font><br>");
			str.append("Hand Outcome: <font color = red>" + getHandOutcome().substring(0, (getHandOutcome().length() - 2)) + "</font><br><br>" );
				
			return str.toString();		
		}
	}
}