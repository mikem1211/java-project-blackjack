package blackjack.core;

/**
 * This class represents a Hand of Blackjack. A Hand consists of a list
 * of Cards in the Hand and the Hand's total number value. This class 
 * provides functionality to construct a Hand, retrieve the Hand's list of Cards,
 * retrieve the Hand's numeric value, add a Card to the Hand, and check to see
 * if an Ace contained in the Hand needs to have its value reduced from 11 to 1 when
 * a new Card is dealt to the Hand.
 */

import java.util.*;

public class Hand {
	protected ArrayList<Card> cards;
	protected int inHandValue;
	
	/*
	 * Construct a Hand
	 */
	public Hand() {
		cards = new ArrayList<Card>();
		inHandValue = 0;		
	}

	/*
	 * Return the hand's list of cards
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}	

	/*
	 * Return the hand's numeric value
	 */
	public int getInHandValue() {
		return inHandValue;
	}	
	
	/*
	 * Deal a random card from the provided Deck and
	 * add the Card to the hand
	 */
	public Card addCard(Deck deck) throws Exception {
		Card newCard = deck.dealCard();
		cards.add(newCard);  
		inHandValue += newCard.getCardValue();
     
		//Check to see if the player has an 
		//Ace and needs to have their hand reduced
		checkForAceReduction();
	       
		return newCard;
	}
	
	/*
	 * If the newly dealt card takes the hand's value
	 * to more than 21, check to see if there's an Ace in
	 * the hand that can be reduced from 11 to 1
	 */
	private void checkForAceReduction() {
		if((inHandValue) > 21) {
			for (Card c : cards) {
				if(c.getCardRank().equals(Card.Rank.ACE) && c.getCardValue() == 11) {
					c.setvalue(1);
					inHandValue -= 10;
					break;				  
				}
			}			          
		}
	}
}