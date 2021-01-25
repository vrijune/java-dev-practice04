package ictgradschool.industry.q2.portfolio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ictgradschool.industry.q2.CompanyRegistry;

public class StockPortfolio {
	
	private List<Stock> _portfolioIndex;
	private Map<String,Stock> _portfolio;
	
	private List<StockPortfolioListener> _listeners;
	
	/**
	 * Create a ShareProcePortfolio containing a collection of shares. 
	 */
	public StockPortfolio() {
		Stock[] stocks = {
				new Stock(CompanyRegistry.COMPANY_NAMES[ 0], 100, 314, 389),
				new Stock(CompanyRegistry.COMPANY_NAMES[ 1], 45, 205, 490),
				new Stock(CompanyRegistry.COMPANY_NAMES[ 2], 260, 105, 197),
				new Stock(CompanyRegistry.COMPANY_NAMES[ 3], 50, 455, 305),
				new Stock(CompanyRegistry.COMPANY_NAMES[ 4], 73, 126, 572),
				new Stock(CompanyRegistry.COMPANY_NAMES[ 5], 32, 359, 68),
				new Stock(CompanyRegistry.COMPANY_NAMES[ 6], 26, 450, 937),
				new Stock(CompanyRegistry.COMPANY_NAMES[ 7], 212, 224, 296),
				new Stock(CompanyRegistry.COMPANY_NAMES[ 8], 38, 302, 449),
				new Stock(CompanyRegistry.COMPANY_NAMES[ 9], 280, 202, 301),
				new Stock(CompanyRegistry.COMPANY_NAMES[10], 80, 90, 10),
				new Stock(CompanyRegistry.COMPANY_NAMES[11], 140, 220, 443),
				new Stock(CompanyRegistry.COMPANY_NAMES[12], 55, 232, 389),
				new Stock(CompanyRegistry.COMPANY_NAMES[13], 20, 217, 298),
				new Stock(CompanyRegistry.COMPANY_NAMES[14], 60, 312, 476),
				new Stock(CompanyRegistry.COMPANY_NAMES[15], 72, 241, 265),
				new Stock(CompanyRegistry.COMPANY_NAMES[16], 28, 718, 1615),
				new Stock(CompanyRegistry.COMPANY_NAMES[17], 122, 732, 605)
		};
		
		_portfolioIndex = new ArrayList<Stock>(Arrays.asList(stocks));
		_portfolio = new HashMap<String,Stock>();
		
		for(Stock stock : _portfolioIndex) {
			_portfolio.put(stock.getCompany(), stock);
		}
		
		_listeners = new ArrayList<StockPortfolioListener>();
	}
	
	/**
	 * Returns the portfolio's current value.
	 */
	public int getPorfolioValue() {
		int totalValue = 0;
		
		for(Stock stock : _portfolioIndex) {
			totalValue += stock.getCurrentValue();
		}
		return totalValue;
	}




	public int getNumberOfStocks() {
		return _portfolioIndex.size();
	}
	
	public Stock getStockAt(int index) {
		return _portfolioIndex.get(index);
	}
	
	public int getIndexFor(int stock) {
		return _portfolioIndex.indexOf(stock);
	}
	
	public void addStockPortfolioListener(StockPortfolioListener listener) {
		_listeners.add(listener);
	}
	
	public void adjustStockPrice(String companyName, int priceChange) {
		Stock stock = _portfolio.get(companyName);
		
		if(stock != null) {
			int proposedValue = stock.getCurrentValue() + priceChange;
			if(proposedValue > 0) {
				stock.setCurrentValue(proposedValue);
				
				// Notify any registered listeners.
				for(StockPortfolioListener listener : _listeners) {
					listener.update(stock);
				}
			}
		}
	}

}
