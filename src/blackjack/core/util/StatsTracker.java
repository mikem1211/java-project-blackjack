package blackjack.core.util;

/**
 * This class represents a stats tracker for a game of
 * Blackjack. This class consists of a list a stats for each hand of 
 * Blackjack played. The stats tracked include the average hand value 
 * for both dealer and player, the win percentage of both dealer and player,
 * the percentage of hands that are pushes,
 * and various stats for each hand (Hand ID, Hand Wager, Dealer Total, Player Total,
 * Hand Outcome, Post-hand Player Wallet)
 */

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StatsTracker {
	private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
	private static final DecimalFormat moneyFormat = new DecimalFormat("$0.00");
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
	public void addStatsForHand(int handId, int handWager, int dealerTotal, int playerTotal, int playerChipCount, String handOutcome) {
		handStats.add(new HandStats(handId, handWager, dealerTotal, playerTotal, playerChipCount, handOutcome));
	}	
	
	/*
	 * Calculate the average points per hand for a dealer/player
	 */
	public String calcAvgPointsPerHand(boolean forPlayer) {
		double totalPoints = 0;		
				
		for(HandStats h : handStats) {
			totalPoints += forPlayer ? h.getPlayerTotal() : h.getDealerTotal();
		}
				      
		return decimalFormat.format(totalPoints / handStats.size());
	}
	
	/*
	 * Calculate the win percentage for a dealer/player
	 */
	public String calcWinPercentage(boolean forPlayer) {
		double totalWins = 0;
		
		for(HandStats h : handStats) {
			if(forPlayer) {
				if(h.getHandOutcome().equals("WIN!!") || h.getHandOutcome().equals("BLACKJACK!!")) {
					totalWins += 1;
				}
			} else {
				if(h.getHandOutcome().equals("LOSE!!") || h.getHandOutcome().equals("BUST!!")) {
					totalWins += 1;
				}
			}
		}
			    
		return (decimalFormat.format((totalWins / handStats.size()) * 100) + "%");	
	}
	
	/*
	 * Calculate the Push percentage for hands
	 */
	public String calcPushPercentage() {
		double totalPushes = 0;
		
		for(HandStats h : handStats) {
			if(h.getHandOutcome().equals("PUSH!!")) {
				totalPushes += 1;
			}
		}
			    
		return (decimalFormat.format((totalPushes / handStats.size()) * 100) + "%");	
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
		str.append("Hand Push Percentage: <font color = red>" + calcPushPercentage() + "</font><br>");
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
		private int handWager;
		private int dealerTotal;
		private int playerTotal;
		private int playerWallet;		
		private String handOutcome;
			
		/*
		 * Construct and record the stats for one hand
		 */
		public HandStats(int handId, int handWager, int dealerTotal, int playerTotal, int playerWallet, String handOutcome) {
			this.handId = handId;
			this.handWager = handWager;
			this.dealerTotal = dealerTotal;
			this.playerTotal = playerTotal;
			this.playerWallet = playerWallet;
			this.handOutcome = handOutcome;			
		}
				
		/*
		 * Retrieve the ID of the hand played
		 */
		public int getHandId() {
			return handId;
		}
		
		/*
		 * Retrieve the wager of the hand played
		 */
		public int getHandWager() {
			return handWager;
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
		 * Retreive the player's total for a hand
		 */
		public int getPlayerWallet() {
			return playerWallet;
		}		
		
		/*
		 * Retrieve a String representation of the stats for a hand
		 */
		public String toString() {
			StringBuilder str = new StringBuilder();
			str.append("Hand ID: " + getHandId() + "<br>");
			str.append("Hand Wager: <font color = red>" + moneyFormat.format(getHandWager()) + "</font><br>");
			str.append("Dealer Total: <font color = red>" + getDealerTotal() + "</font><br>");
			str.append("Player Total: <font color = red>" + getPlayerTotal() + "</font><br>");
			str.append("Hand Outcome: <font color = red>" + getHandOutcome().substring(0, (getHandOutcome().length() - 2)) + "</font><br>" );
			str.append("Post-Hand Player Wallet: <font color = red>" + moneyFormat.format(getPlayerWallet()) + "</font><br><br>");
				
			return str.toString();		
		}
	}
}