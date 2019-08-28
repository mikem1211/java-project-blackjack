package blackjack.core;

/**
 * This class represents a deck of 52 cards and provides functionality
 * to construct the deck, deal a random card, and retrieve the current
 * size of the deck.
 */

import java.util.*;
import javax.swing.*;

public class Deck {
	private static Random random = new Random();
	private ArrayList<Card> deckList = new ArrayList<Card>();
  
	/*
	 * Constructs a deck of 52 games
	 */
	public Deck() {
		deckList.add(new Card(new ImageIcon("images/2S.png"), Card.Suit.SPADE, Card.Rank.TWO));
		deckList.add(new Card(new ImageIcon("images/3S.png"), Card.Suit.SPADE, Card.Rank.THREE));
		deckList.add(new Card(new ImageIcon("images/4S.png"), Card.Suit.SPADE, Card.Rank.FOUR));
		deckList.add(new Card(new ImageIcon("images/5S.png"), Card.Suit.SPADE, Card.Rank.FIVE));
		deckList.add(new Card(new ImageIcon("images/6S.png"), Card.Suit.SPADE, Card.Rank.SIX));
		deckList.add(new Card(new ImageIcon("images/7S.png"), Card.Suit.SPADE, Card.Rank.SEVEN));
		deckList.add(new Card(new ImageIcon("images/8S.png"), Card.Suit.SPADE, Card.Rank.EIGHT));
		deckList.add(new Card(new ImageIcon("images/9S.png"), Card.Suit.SPADE, Card.Rank.NINE));
		deckList.add(new Card(new ImageIcon("images/10S.png"), Card.Suit.SPADE, Card.Rank.TEN));
		deckList.add(new Card(new ImageIcon("images/JS.png"), Card.Suit.SPADE, Card.Rank.JACK));
		deckList.add(new Card(new ImageIcon("images/QS.png"), Card.Suit.SPADE, Card.Rank.QUEEN));
		deckList.add(new Card(new ImageIcon("images/KS.png"), Card.Suit.SPADE, Card.Rank.KING));
		deckList.add(new Card(new ImageIcon("images/AS.png"), Card.Suit.SPADE, Card.Rank.ACE));
			
		deckList.add(new Card(new ImageIcon("images/2H.png"), Card.Suit.HEART, Card.Rank.TWO));
		deckList.add(new Card(new ImageIcon("images/3H.png"), Card.Suit.HEART, Card.Rank.THREE));
		deckList.add(new Card(new ImageIcon("images/4H.png"), Card.Suit.HEART, Card.Rank.FOUR));
		deckList.add(new Card(new ImageIcon("images/5H.png"), Card.Suit.HEART, Card.Rank.FIVE));
		deckList.add(new Card(new ImageIcon("images/6H.png"), Card.Suit.HEART, Card.Rank.SIX));
		deckList.add(new Card(new ImageIcon("images/7H.png"), Card.Suit.HEART, Card.Rank.SEVEN));
		deckList.add(new Card(new ImageIcon("images/8H.png"), Card.Suit.HEART, Card.Rank.EIGHT));
		deckList.add(new Card(new ImageIcon("images/9H.png"), Card.Suit.HEART, Card.Rank.NINE));
		deckList.add(new Card(new ImageIcon("images/10H.png"), Card.Suit.HEART, Card.Rank.TEN));
		deckList.add(new Card(new ImageIcon("images/JH.png"), Card.Suit.HEART, Card.Rank.JACK));
		deckList.add(new Card(new ImageIcon("images/QH.png"), Card.Suit.HEART, Card.Rank.QUEEN));
		deckList.add(new Card(new ImageIcon("images/KH.png"), Card.Suit.HEART, Card.Rank.KING));
		deckList.add(new Card(new ImageIcon("images/AH.png"), Card.Suit.HEART, Card.Rank.ACE));
		
		deckList.add(new Card(new ImageIcon("images/2C.png"), Card.Suit.CLUB, Card.Rank.TWO));
		deckList.add(new Card(new ImageIcon("images/3C.png"), Card.Suit.CLUB, Card.Rank.THREE));
		deckList.add(new Card(new ImageIcon("images/4C.png"), Card.Suit.CLUB, Card.Rank.FOUR));
		deckList.add(new Card(new ImageIcon("images/5C.png"), Card.Suit.CLUB, Card.Rank.FIVE));
		deckList.add(new Card(new ImageIcon("images/6C.png"), Card.Suit.CLUB, Card.Rank.SIX));
		deckList.add(new Card(new ImageIcon("images/7C.png"), Card.Suit.CLUB, Card.Rank.SEVEN));
		deckList.add(new Card(new ImageIcon("images/8C.png"), Card.Suit.CLUB, Card.Rank.EIGHT));
		deckList.add(new Card(new ImageIcon("images/9C.png"), Card.Suit.CLUB, Card.Rank.NINE));
		deckList.add(new Card(new ImageIcon("images/10C.png"), Card.Suit.CLUB, Card.Rank.TEN));
		deckList.add(new Card(new ImageIcon("images/JC.png"), Card.Suit.CLUB, Card.Rank.JACK));
		deckList.add(new Card(new ImageIcon("images/QC.png"), Card.Suit.CLUB, Card.Rank.QUEEN));
		deckList.add(new Card(new ImageIcon("images/KC.png"), Card.Suit.CLUB, Card.Rank.KING));
		deckList.add(new Card(new ImageIcon("images/AC.png"), Card.Suit.CLUB, Card.Rank.ACE));
			
		deckList.add(new Card(new ImageIcon("images/2D.png"), Card.Suit.DIAMOND, Card.Rank.TWO));
		deckList.add(new Card(new ImageIcon("images/3D.png"), Card.Suit.DIAMOND, Card.Rank.THREE));
		deckList.add(new Card(new ImageIcon("images/4D.png"), Card.Suit.DIAMOND, Card.Rank.FOUR));
		deckList.add(new Card(new ImageIcon("images/5D.png"), Card.Suit.DIAMOND, Card.Rank.FIVE));
		deckList.add(new Card(new ImageIcon("images/6D.png"), Card.Suit.DIAMOND, Card.Rank.SIX));
		deckList.add(new Card(new ImageIcon("images/7D.png"), Card.Suit.DIAMOND, Card.Rank.SEVEN));
		deckList.add(new Card(new ImageIcon("images/8D.png"), Card.Suit.DIAMOND, Card.Rank.EIGHT));
		deckList.add(new Card(new ImageIcon("images/9D.png"), Card.Suit.DIAMOND, Card.Rank.NINE));
		deckList.add(new Card(new ImageIcon("images/10D.png"), Card.Suit.DIAMOND, Card.Rank.TEN));
		deckList.add(new Card(new ImageIcon("images/JD.png"), Card.Suit.DIAMOND, Card.Rank.JACK));
		deckList.add(new Card(new ImageIcon("images/QD.png"), Card.Suit.DIAMOND, Card.Rank.QUEEN));
		deckList.add(new Card(new ImageIcon("images/KD.png"), Card.Suit.DIAMOND, Card.Rank.KING));
		deckList.add(new Card(new ImageIcon("images/AD.png"), Card.Suit.DIAMOND, Card.Rank.ACE));
	}
     
	/*
	 * Return and remove a random card from the deck
	 */
	public Card dealCard() throws Exception {
		if (deckList.isEmpty())
			throw new Exception("Deck is Empty!");
		
		return deckList.remove(random.nextInt((deckList.size() - 1)));
	}	
}