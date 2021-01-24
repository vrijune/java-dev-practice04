package ictgradschool.industry.q2.portfolio;

import javax.swing.table.AbstractTableModel;

public class StockPortfolioAdapter extends AbstractTableModel implements StockPortfolioListener {

    private static String[] columnNames = {"Company Name", "Numbers of shares", "Buy prices","Current value"};


    private StockPortfolio stockPortfolio;
    public StockPortfolioAdapter(StockPortfolio stockPortfolio) {
        this.stockPortfolio=stockPortfolio;
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
        StockPortfolio result = stockPortfolio;
        Object value = null;
        switch (column) {
            case 0:
                value = result.;
                break;
            case 1:
                value = result._studentSurname;
                break;
            case 2:
                value = result._studentForename;
                break;
            case 3:
                value = result.getAssessmentElement(StudentResult.AssessmentElement.Exam);
                break;
            case 4:
                value = result.getAssessmentElement(StudentResult.AssessmentElement.Test);
                break;
            case 5:
                value = result.getAssessmentElement(StudentResult.AssessmentElement.Assignment);
                break;
            case 6:
                value = result.getAssessmentElement(StudentResult.AssessmentElement.Overall);
                break;
        }
        return value;

    }

    @Override
    public void update(Stock stock) {
        this.stockPortfolio = stockPortfolio;


    }
}
