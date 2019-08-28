package blackjack.core.views;

/**
 * This class represents the User Interface for
 * a Blackjack game. This class consists of UI elements,
 * the Blackjack Engine, and a Stats Tracker. This class provides
 * functionality for building out the Blackjack UI and managing the UI
 * as a game of Blackjack is played.
 */

import javax.swing.*;

import blackjack.core.*;
import blackjack.core.util.StatsTracker;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

@SuppressWarnings("serial")
public class BlackjackMainView extends JPanel implements ActionListener {	
	private enum HandOutcome {
		WIN, LOSE, PUSH, BUST, BLACKJACK
	}
	
	//Constants
	public static final String LABEL_WAGER_25 = "$25";
	public static final String LABEL_WAGER_50 = "$50";
	public static final String LABEL_WAGER_100 = "$100";
	public static final String LABEL_WAGER_PLACED = "Wager Placed";
	public static final String LABEL_PLAYERS_WALLET = "Player's Wallet";	
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
	
    private static final DecimalFormat moneyFormat = new DecimalFormat("$0.00");
    private static final StatsTracker stats = new StatsTracker();
    private Hand dealer = new Hand(), player = new Hand();   
	private BlackjackEngine engine = new BlackjackEngine(dealer,player);	
	private int handCounter = 0, handWager = 0, playerWallet = 500;
	private HandOutcome handOutcome;
	
	//UI Elements
	private JPanel buttonPanel = new JPanel(), wagerButtonPanel = new JPanel(), dealerGrid = new JPanel(), 
			dealerCardPanel = new JPanel(),	playerGrid = new JPanel(), playerCardPanel = new JPanel();
	private JLabel dealerLabel = new JLabel(), playerLabel = new JLabel(), HandOutcomeLbl = new JLabel(),
			wagerPlacedLbl = new JLabel(), playerWalletLabel = new JLabel(), playerCardOne, playerCardTwo, 
			dealerCardHidden, dealerCardTwo;
	private final JLabel dealerPreGameFill = new JLabel(new ImageIcon("images/green_pregame_fill.png"));
	private final JLabel playerPreGameFill = new JLabel(new ImageIcon("images/green_pregame_fill.png"));
	private JLabel gameDescriptionLbl = new JLabel();
			
	private JButton hitBtn = new JButton(), dealBtn = new JButton(), standBtn = new JButton(),
			playAgainBtn = new JButton(), displayStatsBtn = new JButton(), wager25Btn = new JButton(),
			wager50Btn = new JButton(), wager100Btn = new JButton();

	/*
	 * Initialize the UI
	 */
	public BlackjackMainView () {		
		setLayout(new GridBagLayout());
		setBackground(new Color(0, 122, 0));
		GridBagConstraints c = new GridBagConstraints();
		
		// Hand Outcome
		HandOutcomeLbl.setText(" ");
		HandOutcomeLbl.setHorizontalAlignment(JLabel.CENTER);
		HandOutcomeLbl.setFont(new Font("Tahoma", Font.BOLD, 60));
		HandOutcomeLbl.setForeground(new Color(0, 122, 0));
						
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		c.weightx = 0.5;
		c.weighty = 0.5;   
		c.gridx = 1;
		c.gridy = 0;
		add(HandOutcomeLbl, c);
		
		// Wager Placed
		wagerPlacedLbl.setText(LABEL_WAGER_PLACED + ": " + moneyFormat.format(handWager));
		wagerPlacedLbl.setHorizontalAlignment(JLabel.CENTER);
		wagerPlacedLbl.setFont(new Font("Tahoma", Font.BOLD, 20));	
		wagerPlacedLbl.setForeground(Color.WHITE);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		add(wagerPlacedLbl, c);	
		
		// Wager Buttons				
		wager25Btn.setText(LABEL_WAGER_25);
		wager25Btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		wager25Btn.addActionListener(this);
				
		wager50Btn.setText(LABEL_WAGER_50);
		wager50Btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		wager50Btn.addActionListener(this);
		
		wager100Btn.setText(LABEL_WAGER_100);
		wager100Btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		wager100Btn.addActionListener(this);
				
		wagerButtonPanel.setLayout(new FlowLayout());
		wagerButtonPanel.setBackground(new Color(0, 122, 0));
		wagerButtonPanel.add(wager25Btn);
		wagerButtonPanel.add(wager50Btn);
		wagerButtonPanel.add(wager100Btn);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		add(wagerButtonPanel, c);
		
		// Player Wallet
		playerWalletLabel.setText(LABEL_PLAYERS_WALLET + ": " + moneyFormat.format(playerWallet));
		playerWalletLabel.setHorizontalAlignment(JLabel.CENTER);
		playerWalletLabel.setFont(new Font("Tahoma", Font.BOLD, 20));	
		playerWalletLabel.setForeground(Color.WHITE);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		add(playerWalletLabel, c);
				
		// Main Button Panel
		dealBtn.setText(LABEL_DEAL_CARDS);
		dealBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		dealBtn.addActionListener(this);
		dealBtn.setEnabled(false);		
		
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
		c.gridx = 1;
		c.gridy = 2;
		add(buttonPanel, c);
		
		// Game Description
		gameDescriptionLbl.setText(buildGameDescription());
		gameDescriptionLbl.setHorizontalAlignment(JLabel.CENTER);
		gameDescriptionLbl.setFont(new Font("Tahoma", Font.BOLD, 15));	
		gameDescriptionLbl.setForeground(new Color(184, 188, 10));
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		add(gameDescriptionLbl, c);
				
		// Dealer Grid
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
		c.gridx = 1;
		c.gridy = 3;
		add(dealerGrid, c);
		
		// Player Grid
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
		c.gridx = 1;
		c.gridy = 4;
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
	
	private String buildGameDescription() {
		StringBuilder str = new StringBuilder();
		str.append("<html><body><p style=\"font-size:15px\">");
		str.append("Welcome to Mike's lite version<br>");
		str.append("of Blackjack. To start a hand,<br>");
		str.append("you must first select a Wager<br>");
		str.append("Amount. After a wager is<br>");
		str.append("selected, you may deal the<br>");
		str.append("initial cards and play the hand.<br>");
		str.append("Once a hand is completed, click<br>");
		str.append("Play Again to start a new hand.<br>");
		str.append("You may view stats for the game<br>");
		str.append("anytime after the first hand is<br>");
		str.append("completed by clicking Display Game<br>");
		str.append("Stats. Enjoy the game!</p></body></html>");
		
		return str.toString();
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
	
			toggleMainButtonsEnabled(false, true, true, false, (displayStatsBtn.isEnabled() ? true : false));
				
			if(playerDealtBlackjack()) {
				setHandOutcome(HandOutcome.BLACKJACK);
				applyHandPayment();
				formatHandOutcomeLbl();
				toggleMainButtonsEnabled(false, false, false, true, true);
								
				stats.addStatsForHand(handCounter, getHandWager(), dealer.getInHandValue(), player.getInHandValue(), getPlayerWalletAmount(), getHandOutcomeLbl());
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
				applyHandPayment();
				formatHandOutcomeLbl();
				toggleMainButtonsEnabled(false, false, false, true, true);				
								
				stats.addStatsForHand(handCounter, getHandWager(), dealer.getInHandValue(), player.getInHandValue(), getPlayerWalletAmount(), getHandOutcomeLbl());
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
			applyHandPayment();
			formatHandOutcomeLbl();
			toggleMainButtonsEnabled(false, false, false, true, true);
						
			stats.addStatsForHand(handCounter, getHandWager(), dealer.getInHandValue(), player.getInHandValue(), getPlayerWalletAmount(), getHandOutcomeLbl());
		} catch(Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	/*
	 * Reset the game so that a new hand can be played
	 */
	private void playAgain() {
		handOutcome = null;
		handWager = 0;
		dealer = new Hand();
		player = new Hand();		
		engine = new BlackjackEngine(dealer, player);		
		
		wagerPlacedLbl.setText(LABEL_WAGER_PLACED + ": " + moneyFormat.format(handWager));
		dealerLabel.setText(LABEL_DEALER + ": ");
		playerLabel.setText(LABEL_PLAYER + ": ");			
		HandOutcomeLbl.setText(" ");
		HandOutcomeLbl.setForeground(new Color(0, 122, 0));
		
		dealerCardPanel.removeAll();			
		dealerCardPanel.add(dealerPreGameFill);
		dealerCardPanel.repaint();
		
		playerCardPanel.removeAll();
		playerCardPanel.add(playerPreGameFill);
		playerCardPanel.repaint();
		
		toggleWagerButtonsEnabled(true);
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
			
			if(source == wager25Btn) {
				setHandWager(25);
				//toggleWagerButtons(false);
			} else if(source == wager50Btn) {
				setHandWager(50);
				//toggleWagerButtons(false);
			} else if(source == wager100Btn) {
				setHandWager(100);
				//toggleWagerButtons(false);
			} else if(source == dealBtn) {
				dealInitialCards();
			} else if(source == hitBtn) {
				initiatePlayerHit();
			}  else if(source == standBtn) {
				initiateDealerSimulation();
			}  else if(source == playAgainBtn) {
				playAgain();
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
	 * Formats the HandOutcomeLbl JLabel
	 */
	private void formatHandOutcomeLbl() {
		HandOutcomeLbl.setText(getHandOutcomeLbl());
		HandOutcomeLbl.setForeground(new Color(184, 188, 10));
		HandOutcomeLbl.setVisible(true);
	}
		
	/*
	 * Set the hand wager
	 */
	private void setHandWager(int handWager) {
		toggleWagerButtonsEnabled(false);
		
		if(playerWallet < handWager) {
			JOptionPane.showMessageDialog(null, "<html>Insufficient funds available to make a wager!<br><br> The game has ended. Click the Display Game Stats button to view the game stats.</html>");
			toggleMainButtonsEnabled(false, false, false, false, (displayStatsBtn.isEnabled() ? true : false));
		} else { 
			this.handWager = handWager;
			wagerPlacedLbl.setText(LABEL_WAGER_PLACED + ": " + moneyFormat.format(handWager));			
		}
	}
	
	/*
	 * Retrieve the hand wager
	 */
	private int getHandWager() {
		return handWager;
	}
	
	/*
	 * Retrieve the player wallet amount
	 */
	private int getPlayerWalletAmount() {
		return playerWallet;
	}
	
	/*
	 * Toggles the wager buttons and the appropriate main buttons
	 */
	private void toggleWagerButtonsEnabled(boolean enabled) {
		wager25Btn.setEnabled(enabled);
		wager50Btn.setEnabled(enabled);
		wager100Btn.setEnabled(enabled);
		
		if(!enabled) {
			toggleMainButtonsEnabled(!enabled, enabled, enabled, enabled, (displayStatsBtn.isEnabled() ? true : enabled));
		} else {
			toggleMainButtonsEnabled(!enabled, !enabled, !enabled, !enabled, (displayStatsBtn.isEnabled() ? true : enabled));
		}		
	}
	
	/*
	 * Enable/Disable the various buttons
	 */
	private void toggleMainButtonsEnabled(boolean dealEnabled, boolean hitEnabled, boolean standEnabled, boolean playAgainEnabled, boolean displayStatsEnabled) {
		dealBtn.setEnabled(dealEnabled);
		hitBtn.setEnabled(hitEnabled);
		standBtn.setEnabled(standEnabled);
		playAgainBtn.setEnabled(playAgainEnabled);
		displayStatsBtn.setEnabled(displayStatsEnabled);
	}
	
	/*
	 * Applies payment/deduction of the hand wager to the 
	 * player's wallet
	 */
	private void applyHandPayment() {
		HandOutcome outcome = getHandOutcome();
		int wager = getHandWager();
		
		switch(outcome) {
			case BLACKJACK: playerWallet += (wager * 1.5); break;
			case WIN:  		playerWallet += wager; break;
			case LOSE: 		
			case BUST: 		playerWallet -= wager; break;
			case PUSH:		break;
		}
		
		playerWalletLabel.setText(LABEL_PLAYERS_WALLET + ": " + moneyFormat.format(playerWallet));
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