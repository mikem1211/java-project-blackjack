/**
 * This class represents the User Interface for
 * a Blackjack game. This class consists of UI elements,
 * the Blackjack Engine, and a Stats Tracker. This class provides
 * functionality for building out the Blackjack UI and managing the UI
 * as a game of Blackjack is played.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class BlackjackMainView extends JPanel implements ActionListener {	
	private enum HandOutcome {
		WIN, LOSE, PUSH, BUST, BLACKJACK
	}
	
	//Constants
	public static final String LABEL_DEAL_CARDS = "Deal Cards";
    public static final String LABEL_HIT = "Hit";
    public static final String LABEL_STAND = "Stand";
    public static final String LABEL_PLAY_AGAIN = "Play Again";
    public static final String LABEL_DISPLAY_GAME_STATS = "Display Game Stats";
    public static final String LABEL_GAME_STATISTICS = "Game Statistics";
    public static final String LABEL_DEALER = "Dealer";
    public static final String LABEL_PLAYER = "Player";
    public static final String LABEL_WIN = "Win";
    public static final String LABEL_LOSE = "Lose";
    public static final String LABEL_PUSH = "Push";
    public static final String LABEL_BUST = "Bust";
    public static final String LABEL_BLACKJACK = "Blackjack";
	
	private Hand dealer = new Hand(), player = new Hand();   
	private BlackjackEngine engine = new BlackjackEngine(dealer,player);
	private StatsTracker stats = new StatsTracker();
	private int handCounter = 0;
	private HandOutcome handOutcome;
	
	//UI Elements
	private JPanel buttonPanel = new JPanel(), dealerGrid = new JPanel(), dealerCardPanel = new JPanel(), 
			playerGrid = new JPanel(), playerCardPanel = new JPanel();
	private JLabel dealerLabel = new JLabel(), playerLabel = new JLabel(),handOverStatusLbl = new JLabel(),
			playerCardOne, playerCardTwo, dealerCardHidden, dealerCardTwo, //dealerCardOne,
			dealerPreGameFill = new JLabel(new ImageIcon("images/green_pregame_fill.png")),
			playerPreGameFill = new JLabel(new ImageIcon("images/green_pregame_fill.png"));
	private JButton hitBtn = new JButton(), dealBtn = new JButton(), standBtn = new JButton(),
			playAgainBtn = new JButton(), displayStatsBtn = new JButton();	

	/*
	 * Initialize the UI
	 */
	public BlackjackMainView () {		
		setLayout(new GridBagLayout());
		setBackground(new Color(0, 122, 0));
		GridBagConstraints c = new GridBagConstraints();
		
		handOverStatusLbl.setText(" ");
		handOverStatusLbl.setHorizontalAlignment(JLabel.CENTER);
		handOverStatusLbl.setFont(new Font("Tahoma", Font.BOLD, 60));
		handOverStatusLbl.setForeground(new Color(0, 122, 0));
						
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		c.weightx = 0.5;
		c.weighty = 0.5;   
		c.gridx = 0;
		c.gridy = 0;
		add(handOverStatusLbl, c);
				
		dealBtn.setText(LABEL_DEAL_CARDS);
		dealBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		dealBtn.addActionListener(this);
				
		hitBtn.setText(LABEL_HIT);
		hitBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		hitBtn.addActionListener(this);
		hitBtn.setEnabled(false);
		
		standBtn.setText(LABEL_STAND);
		standBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		standBtn.addActionListener(this);
		standBtn.setEnabled(false);
		
		playAgainBtn.setText(LABEL_PLAY_AGAIN);
		playAgainBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		playAgainBtn.addActionListener(this);
		playAgainBtn.setEnabled(false); 	
		
		displayStatsBtn.setText(LABEL_DISPLAY_GAME_STATS);
		displayStatsBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		displayStatsBtn.addActionListener(this);
		displayStatsBtn.setEnabled(false); 		
    
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(new Color(0, 122, 0));
		buttonPanel.add(dealBtn);
		buttonPanel.add(hitBtn);
		buttonPanel.add(standBtn);
		buttonPanel.add(playAgainBtn);
		buttonPanel.add(displayStatsBtn);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		add(buttonPanel, c);		
				
		dealerLabel.setText(LABEL_DEALER + ": ");
		dealerLabel.setHorizontalAlignment(JLabel.CENTER);
		dealerLabel.setFont(new Font("Tahoma", Font.BOLD, 20));	
		dealerLabel.setForeground(Color.WHITE);		
		dealerCardPanel.setBackground(new Color(0, 122, 0));
		dealerCardPanel.add(dealerPreGameFill);		
		dealerGrid.setLayout(new GridLayout(2,1));
		dealerGrid.setBackground(new Color(0, 122, 0));
		dealerGrid.add(dealerLabel);
		dealerGrid.add(dealerCardPanel);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		add(dealerGrid, c);
		
		playerLabel.setText(LABEL_PLAYER + ": ");
		playerLabel.setHorizontalAlignment(JLabel.CENTER);
		playerLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		playerLabel.setForeground(Color.WHITE);		
		playerCardPanel.setBackground(new Color(0, 122, 0));
		playerCardPanel.add(playerPreGameFill);		
		playerGrid.setLayout(new GridLayout(2,1));
		playerGrid.setBackground(new Color(0, 122, 0));
		playerGrid.add(playerLabel);
		playerGrid.add(playerCardPanel);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		add(playerGrid, c);	   
	}

	/*
	 * Display the UI
	 */
	public void display() {
		JFrame gameFrame = new JFrame(LABEL_BLACKJACK);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setContentPane(this);
		gameFrame.setPreferredSize(new Dimension(1400, 1000));

		//Display the window.
		gameFrame.pack();
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);
    }
	
	/*
	 * Deal the initial cards a of new hand
	 */
	private void dealInitialCards() {		
		try {
			handCounter += 1;
			engine.dealInitialCards();	
					
			dealerCardHidden = new JLabel(new ImageIcon("images/gray_back.png"));
			dealerCardTwo = new JLabel(dealer.getCards().get(1).getCardImage());
			int initialDealerCardTwoValue = dealer.getCards().get(1).getCardValue();
		
			playerCardOne = new JLabel(player.getCards().get(0).getCardImage());
			playerCardTwo = new JLabel(player.getCards().get(1).getCardImage());
			
			dealerCardPanel.remove(dealerPreGameFill);
			dealerCardPanel.add(dealerCardHidden);
			dealerCardPanel.add(dealerCardTwo);
	
			playerCardPanel.remove(playerPreGameFill);
			playerCardPanel.add(playerCardOne);
			playerCardPanel.add(playerCardTwo);
	
			dealerLabel.setText(LABEL_DEALER + ": "+ initialDealerCardTwoValue);
			playerLabel.setText(LABEL_PLAYER + ": " + player.getInHandValue());
	
			toggleButtonsEnabled(false, true, true, false, false);
				
			if(playerDealtBlackjack()) {
				setHandOutcome(HandOutcome.BLACKJACK);
				formatHandOverStatusLbl();
				toggleButtonsEnabled(false, false, false, true, true);
								
				stats.addStatsForHand(handCounter, dealer.getInHandValue(), player.getInHandValue(), getHandOutcomeLbl());
			}			
		} catch(Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	/*
	 * Initiate a player hit
	 */
	private void initiatePlayerHit() {
		try {
			playerCardPanel.add(new JLabel(engine.hit(player).getCardImage()));
			playerCardPanel.repaint();
			playerLabel.setText(LABEL_PLAYER + ": " + player.getInHandValue());
 
			if(playerBusted()) {				
				setHandOutcome(HandOutcome.BUST);
				formatHandOverStatusLbl();
				toggleButtonsEnabled(false, false, false, true, true);
								
				stats.addStatsForHand(handCounter, dealer.getInHandValue(), player.getInHandValue(), getHandOutcomeLbl());
			}				
		} catch(Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	/*
	 * Initiate simulation of the dealer's hand
	 */
	private void initiateDealerSimulation() {
		try {
			dealerCardPanel.removeAll();				
			dealer = engine.simulateDealerHand();
							
			for(Card c : dealer.getCards()) {
				dealerCardPanel.add(new JLabel(c.getCardImage()));
			}				
					
			dealerLabel.setText(LABEL_DEALER + ": " + dealer.getInHandValue());
			playerLabel.setText(LABEL_PLAYER + ": " + player.getInHandValue());
				
			setHandOutcome(calculateHandOutcome());
			formatHandOverStatusLbl();
			toggleButtonsEnabled(false, false, false, true, true);
						
			stats.addStatsForHand(handCounter, dealer.getInHandValue(), player.getInHandValue(), getHandOutcomeLbl());
		} catch(Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	/*
	 * Reset the game so that a new hand can be played
	 */
	private void resetGame() {
		dealer = new Hand();
		player = new Hand();		
		engine = new BlackjackEngine(dealer, player);
		handOutcome = null;
		
		dealerLabel.setText(LABEL_DEALER + ": ");
		playerLabel.setText(LABEL_PLAYER + ": ");			
		handOverStatusLbl.setText(" ");
		handOverStatusLbl.setForeground(new Color(0, 122, 0));
		
		dealerCardPanel.removeAll();			
		dealerCardPanel.add(dealerPreGameFill);
		dealerCardPanel.repaint();
		
		playerCardPanel.removeAll();
		playerCardPanel.add(playerPreGameFill);
		playerCardPanel.repaint();
		
		toggleButtonsEnabled(true, false, false, false, true);
	}
	
	/*
	 * Display the stats recorded for the game
	 */
	private void displayStats() {		
		JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(new JLabel(stats.toString()));
		panel.add(Box.createVerticalGlue());
				
		JScrollPane jScrollPane = new JScrollPane(panel);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	
				
		JFrame window = new JFrame(LABEL_GAME_STATISTICS); 
		window.getContentPane().add(jScrollPane);		
		window.pack();		
		window.setSize(600,600);
		window.setLocationRelativeTo(this);
		window.setVisible(true);		
	}
	
	/*
	 * Action handler for the buttons
	 */
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o instanceof JButton) {
			JButton source = (JButton) o;
			
			if(source == dealBtn) {
				dealInitialCards();
			} else if(source == hitBtn) {
				initiatePlayerHit();
			}  else if(source == standBtn) {
				initiateDealerSimulation();
			}  else if(source == playAgainBtn) {
				resetGame();
			} else if(source == displayStatsBtn) {
				displayStats();
			}
		}		
	}
	
	/*
	 * Set the outcome of the hand
	 */
	private void setHandOutcome(HandOutcome handOutcome) {
		this.handOutcome = handOutcome;
	}
	
	/*
	 * Return the outcome of the hand
	 */
	private HandOutcome getHandOutcome() {
		return handOutcome;
	}
	
	/*
	 * Calculate the outcome of the hand.
	 * This is used when after the dealer's hand is simulated
	 */
	private HandOutcome calculateHandOutcome() {
	 	if((player.getInHandValue() < dealer.getInHandValue()) && dealer.getInHandValue() <= 21 )                 
			return HandOutcome.LOSE;
	    else if ((player.getInHandValue() == dealer.getInHandValue()) && dealer.getInHandValue() <= 21 )
	    	return HandOutcome.PUSH;
	    else
	    	return HandOutcome.WIN;
	}
	
	/*
	 * Retrieve a display String for the hand outcome
	 */
	private String getHandOutcomeLbl() {
		HandOutcome outcome = getHandOutcome();
		String retStr = "";
		
		if(outcome != null) {
			switch(outcome) {
				case WIN:  		retStr = LABEL_WIN.toUpperCase(); break;
				case LOSE: 		retStr = LABEL_LOSE.toUpperCase(); break;
				case PUSH: 		retStr = LABEL_PUSH.toUpperCase(); break;
				case BUST: 		retStr = LABEL_BUST.toUpperCase(); break;
				case BLACKJACK: retStr = LABEL_BLACKJACK.toUpperCase(); break;
			}
			
			retStr += "!!";
		}
		
		return retStr;
	}
	
	/*
	 * Formats the handOverStatusLbl JLabel
	 */
	private void formatHandOverStatusLbl() {
		handOverStatusLbl.setText(getHandOutcomeLbl());
		handOverStatusLbl.setForeground(new Color(184, 188, 10));
		handOverStatusLbl.setVisible(true);
	}
	
	/*
	 * Enable/Disable the various buttons
	 */
	private void toggleButtonsEnabled(boolean dealEnabled, boolean hitEnabled, boolean standEnabled, boolean playAgainEnabled, boolean displayStatsEnabled) {
		this.dealBtn.setEnabled(dealEnabled);
		this.hitBtn.setEnabled(hitEnabled);
		this.standBtn.setEnabled(standEnabled);
		this.playAgainBtn.setEnabled(playAgainEnabled);
		this.displayStatsBtn.setEnabled(displayStatsEnabled);
	}	
	
	/*
	 * Returns true is a play was dealt a BLACKJACK
	 */
	private boolean playerDealtBlackjack() {
		return (player.getInHandValue() == 21);
	}
	
	/*
	 * Returns true if a player busted their hand
	 */
	private boolean playerBusted() {
		return (player.getInHandValue() > 21);
	}
}