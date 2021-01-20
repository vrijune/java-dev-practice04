package ictgradschool.industry.q2.portfolio;

/**
 * Simple class to represent a stock in which a user has shares.
 * 
 * A Stock is described by a company name, the number of shares the user has 
 * in the company, the value of the share price when the shares were bought, 
 * and the present share price value.  
 *
 */
public class Stock {
	private String _company;
	private int _numberOfShares;
	private int _valuePerShareWhenPurchased;
	private int _currentValuePerShare;
	
	/**
	 * Creates a Stock instance.
	 */
	public Stock(String company, int numberOfShares, int buyPrice, int currentValue) {
		_company = company;
		_numberOfShares = numberOfShares;
		_valuePerShareWhenPurchased = buyPrice;
		_currentValuePerShare = currentValue;
	}
	
	/**
	 * Returns the Stock's company name.
	 */
	public String getCompany() {
		return _company;
	}
	
	/**
	 * Returns the number of shares in the company.
	 */
	public int getNumberOfShares() {
		return _numberOfShares;
	}
	
	/**
	 * Returns the Stock's share price when purchased.
	 */
	public int getBoughtPrice() {
		return _valuePerShareWhenPurchased;
	}
	
	/**
	 * Returns the current share price for the Stock.
	 */
	public int getCurrentValue() {
		return _currentValuePerShare;
	}
	
	/**
	 * Sets the current share price for the stock. This method has package 
	 * visibility because a Stock instance should be modified only be the 
	 * StockPortfolio to which it belongs. All Stock change requests are
	 * funnelled through the StockPortfolio, which ensures that any 
	 * StockPortfolioListeners are notifed of changes to a StockPortfolio's
	 * Stocks. 
	 */
	protected void setCurrentValue(int currentValue) {
		_currentValuePerShare = currentValue;
	}
}
