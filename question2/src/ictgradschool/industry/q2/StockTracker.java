package ictgradschool.industry.q2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.Timer;

/**
 * 
 * StockTracker simulates a network feed that supplies events describing 
 * updates to share prices.
 * 
 * A StockTracker object can be started and stopped. Between being started and 
 * stopped, the tracker is in monitoring mode where it is able to generate 
 * events. To receive events, a StockTrackerListener must be registered with 
 * the StockTracker. StockTrackerListener is a simple interface that allows
 * the StockTracker to notify interested parties in share price changes.
 * 
 * Internally, StockTracker uses a Timer when in monitoring mode. Each second 
 * the Timer ticks and the StockTracker generates a random share price event.
 * Any registered StockTrackerListeners are then notified of the change in 
 * share price.
 *
 */
public class StockTracker {

	private Timer _timer;
	private List<StockTrackerListener> _listeners;
	
	public StockTracker() {
		_timer = new Timer(1000, new Tracker());
		_listeners = new ArrayList<StockTrackerListener>();
	}
	
	/**
	 * Puts the StockTracker in monitoring mode. The StockTracker will now 
	 * start to supply share price change events.
	 */
	public void startMonitoring() {
		_timer.start();
	}
	
	/**
	 * Stops the StockTracker from generating and firing share price change
	 * events.
	 */
	public void stopMonitoring() {
		_timer.stop();
	}
	
	/**
	 * Registers a StockTrackerListener with the StockTracker.
	 */
	public void addStockTrackerListener(StockTrackerListener listener) {
		_listeners.add(listener);
	}
	
	/*
	 * Helper class that uses a Timer as the stimulus for creating share price 
	 * change events. In response to each tick of the Timer, a share price 
	 * change event is generated and broadcast to registered 
	 * StockTrackerListeners.
	 */
	private class Tracker implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Simulate a stock value change.
			int company = ThreadLocalRandom.current().nextInt(0, CompanyRegistry.COMPANY_NAMES.length);
			int priceChange = ThreadLocalRandom.current().nextInt(1, 21);
			int upOrDown = ThreadLocalRandom.current().nextInt(0, 4);
			
			// Notify any registered listeners of the stock price change.
			for(StockTrackerListener listener : _listeners) {
				listener.update(CompanyRegistry.COMPANY_NAMES[company], priceChange, upOrDown > 0);
			}
		}
	}
}
