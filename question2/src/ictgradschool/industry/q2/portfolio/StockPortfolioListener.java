package ictgradschool.industry.q2.portfolio;

/**
 * Listener (Observer) interface to be implemented by classes that are 
 * interested in changes to a StockPortfolio. An instance of a class that 
 * implements this interface can be registered with a StockPortfolio. Whenever
 * a StockPortfolio is changed (e.g. a Stock within the portfolio changes its
 * share price), registered StockPortfolioListener objects are notified. 
 *
 */
public interface StockPortfolioListener {
	
	/**
	 * Notifies a StockPortfolioListener that a Stock within the portfolio has 
	 * been updated.
	 * 
	 * @param stock the Stock object within the portfolio that has changed.
	 */
	void update(Stock stock);
}
