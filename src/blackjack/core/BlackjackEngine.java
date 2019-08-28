package blackjack.core;

/**
 * This class represents the engine of a Blackjack game.
 * The engine constists of a dealer's Hand, a player's Hand,
 * and a Deck of cards. This engine provides functionality to
 * Construct a new game of Blackjack, deal the initial cards for
 * a hand, provide Hits to the player's hand, and simulate the
 * dealer's hand.
 */

public class BlackjackEngine {
	Hand dealerHand;    
	Hand playerHand;    
	Deck deck;   
	
	/*
	 * Construct a new game of Blackjack
	 */
	public BlackjackEngine(Hand dealerHand, Hand playerHand) {
		this.dealerHand = dealerHand;
		this.playerHand = playerHand;
		deck = new Deck();		
	}
	
	/*
	 * Deal the initial cards of a single hand
	 */
	public void dealInitialCards() throws Exception { 
		dealerHand.addCard(deck);
		dealerHand.addCard(deck);
		playerHand.addCard(deck);
		playerHand.addCard(deck);
    }
	
	/*
	 * Provide a hit to the requesting Hand
	 */
	public Card hit(Hand requestingHand) throws Exception {
		return requestingHand.addCard(deck);
	}
	
	/*
	 * Simulate the dealer's hand
	 */
	public Hand simulateDealerHand() throws Exception {
		Hand result = dealerHand;

		while(dealerHand.getInHandValue() <= 16) {
			dealerHand.addCard(deck);
		}

		return result;
	}	
}