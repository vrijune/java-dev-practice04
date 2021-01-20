package ictgradschool.industry.q2;

/**
 * Listener (Observer) interface to be implemented by classes that are 
 * interested in changes to share prices. An instance of a class that 
 * implements this interface can be registered with a StockTracker. Whenever
 * the StockTracker generates a share price change event, registered 
 * StockTrackerListener objects are notified. 
 *
 */
public interface StockTrackerListener {
	
	/**
	 * Informs a StockTrackerListener object of a change to a share price.
	 * 
	 * @param companyName the name of the company whose shared price has changed.
	 * @param priceChange the delta in the share price.
	 * @param priceIncrease true if the share price has risen by priceChange, 
	 *        false if it has fallen.
	 */
	void update(String companyName, int priceChange, boolean priceIncrease);
}
