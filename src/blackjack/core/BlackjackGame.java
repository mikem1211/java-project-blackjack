package blackjack.core;

import blackjack.core.views.BlackjackMainView;

/**
 * This class creates an instance of a Blackjack game
 * and displays the game.
 */
public class BlackjackGame {
	public static void main(String[] args) {
		BlackjackMainView frame = new BlackjackMainView();
		frame.display();
    }
}