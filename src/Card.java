/**
 * This class represents a playing Card. A card consists of an image,
 * the card's suit, the card's rank, and the card's associated Blackjack 
 * numeric value. This class provides functionality to construct a card, 
 * manually set the number value of the card, and retrieve any of the card
 * related variables.
 */

import javax.swing.*;

public class Card {	
	enum Suit {
		SPADE,
		HEART,
		CLUB,
		DIAMOND
	}
	
	enum Rank {
		TWO,
		THREE,
		FOUR,
		FIVE,
		SIX,
		SEVEN,
		EIGHT,
		NINE,
		TEN,
		JACK,
		QUEEN,
		KING,
		ACE
	}

	protected ImageIcon cardImg;
	protected Suit cardSuit;
	protected Rank cardRank;
	protected int cardValue; 
	
	public Card() {
		cardImg = null;
		cardValue = 0;
		cardSuit = null;
		cardRank = null;
	}	
	
	/*
	 * Construct a card with the provided image, suit, and rank
	 */
	public Card(ImageIcon cardImg, Suit cardSuit, Rank cardRank) {
		this.cardImg = cardImg;
		this.cardSuit = cardSuit;
		this.cardRank = cardRank;
		  
		switch (cardRank) {
			case TWO: 	cardValue = 2; break;
		  	case THREE: cardValue = 3; break;
		  	case FOUR:	cardValue = 4; break;
		  	case FIVE:	cardValue = 5; break;
		  	case SIX: 	cardValue = 6; break;
		  	case SEVEN: cardValue = 7; break;
		  	case EIGHT: cardValue = 8; break;
		  	case NINE: 	cardValue = 9; break;
		  	case TEN:	
		  	case JACK:
		  	case QUEEN:
		  	case KING:	cardValue = 10; break;
		  	case ACE: 	cardValue = 11; break;
		}
	}
  	
	/*
	 * Retrieve the card image
	 */
	public ImageIcon getCardImage() {
		return cardImg;
	}
	
	/*
	 * Retrieve the card suit
	 */
	public Suit getCardSuit() {
		return cardSuit;
	}
	
	/*
	 * Retrieve the card rank
	 */
	public Rank getCardRank() {
		return cardRank;
	}
	
	/*
	 * Retrieve the card value
	 */
	public int getCardValue() {
		return cardValue;
	}

	/*
	 * Set the card value
	 */
	public void setvalue(int val) {
		  cardValue = val;
	}
}