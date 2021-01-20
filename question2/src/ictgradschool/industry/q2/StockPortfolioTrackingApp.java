package ictgradschool.industry.q2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import ictgradschool.industry.q2.portfolio.StockPortfolio;


/**
 * Application program that displays a stock portfolio - a collection of stocks 
 * held by the user. The user can instruct the program to monitor the share
 * prices of their stocks. In monitoring mode, the program processes updated 
 * share prices and updates the user's stock portfolio. The new share prices 
 * are displayed in a JTable view that provides a visual representation of the 
 * stock portfolio. In addition, in a JTextArea, the program displays each share
 * price update. 
 *
 */
public class StockPortfolioTrackingApp extends JPanel {	
	
	/**
	 * Constructor method to initialise the application.
	 */
	public StockPortfolioTrackingApp() {
		// Create key objects: the user's StockPortfolio and the StockTracker
		// that is responsible for monitoring share prices.
		StockPortfolio portfolio = new StockPortfolio();
		StockTracker stockTracker = new StockTracker();
		
		// Create key GUI view objects.
		JTable tableView = new JTable();
		JTextArea sharePriceLog = new JTextArea();
		sharePriceLog.setEditable(false);
		
		// YOUR CODE STARTS HERE
		//
		// YOUR CODE ENDS HERE
		
		// Register a listener on the StockTracker so that when a share price
		// changes, the StockPortfolio is updated.
		stockTracker.addStockTrackerListener(new StockTrackerListener() {
			@Override
			public void update(String companyName, int priceChange,
					boolean priceIncrease) {
				portfolio.adjustStockPrice(companyName, priceChange);
			}
		});
		
		// Register a listener on the StockTracker so that the JTextArea log
		// can be updated to show share prices changes as they occur.
		stockTracker.addStockTrackerListener(new StockTrackerListener() {
			@Override
			public void update(String companyName, int priceChange, boolean priceIncrease) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("Price change for ");
				buffer.append(companyName);
				if(priceIncrease) {
					buffer.append(" up ");
				} else {
					buffer.append(" down ");
				}
				buffer.append(priceChange);
				buffer.append(" cents\n");
				sharePriceLog.append(buffer.toString());
			}
		});
		
		// Create the Monitoring on/off buttons.
		JRadioButton on = new JRadioButton("On");
		JRadioButton off = new JRadioButton("Off", true);
		ButtonGroup monitoringGroup = new ButtonGroup();
		monitoringGroup.add(on);
		monitoringGroup.add(off);
		
		// When the on button is clicked, ask the StockTracker to start
		// monitoring.
		on.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stockTracker.startMonitoring();
			}
		});
		
		// When the off button is clicked, stop monitoring.
		off.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stockTracker.stopMonitoring();
			}
		});
		
		// Layout the GUI using intermediate containers, layout managers and
		// scrollbars as appropriate.
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(on);
		buttonPanel.add(off);
		buttonPanel.setBorder(BorderFactory.createTitledBorder("Monitoring"));
		
		JScrollPane scrollPaneForLog = new JScrollPane();
		scrollPaneForLog.setViewportView(sharePriceLog);
		
		JScrollPane scrollPaneForTable = new JScrollPane();
		scrollPaneForTable.setViewportView(tableView);
		scrollPaneForTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		JPanel viewsPanel = new JPanel();
		viewsPanel.setLayout(new GridLayout(2, 1));
		viewsPanel.add(scrollPaneForTable);
		viewsPanel.add(scrollPaneForLog);
		
		setLayout(new BorderLayout());
		add(viewsPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(500,600));
	}

	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Stock Porfolio Tracker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		JComponent contentPane = new StockPortfolioTrackingApp();
		frame.add(contentPane);
		
		// Display the window.
		frame.pack();
        frame.setLocationRelativeTo(null); 
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}

