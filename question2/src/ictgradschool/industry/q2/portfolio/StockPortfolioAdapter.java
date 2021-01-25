package ictgradschool.industry.q2.portfolio;

import javax.swing.table.AbstractTableModel;

public class StockPortfolioAdapter extends AbstractTableModel{

    private String[] columnNames = {"Company Name", "Numbers of shares", "Buy prices", "Current value"};


    private StockPortfolio stockPortfolio;

    public StockPortfolioAdapter(StockPortfolio stockPortfolio) {
        this.stockPortfolio = stockPortfolio;
    }


    @Override
    public int getRowCount() {
        return stockPortfolio.getNumberOfStocks();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Stock result = stockPortfolio.getStockAt(row);
        Object value = null;
        switch (column) {
            case 0:
                value = result.getCompany();
                break;
            case 1:
                value = result.getNumberOfShares();
                break;
            case 2:
                value = result.getBoughtPrice();
                break;
            case 3:
                value = result.getCurrentValue();
                break;

        }
        return value;

    }

    @Override
    public String getColumnName(int column) {
        String name = null;
        for (int i = 0; i < columnNames.length ; i++) {
            if (i == column){
                name = columnNames[i];
            }
        }
        return name;
    }


}
