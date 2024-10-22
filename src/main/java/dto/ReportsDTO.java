package dto;

import java.util.Date;
import java.util.Map;

public class ReportsDTO {
    private String id;
    private Date saleDate;
    private double totalAmount;
    private String category;
    private String reportType;
    private double totalSales;
    private Map<String, Double> salesByCategory;
    private Map<String, Double> salesTrends;
   
    public ReportsDTO(String id, Date saleDate, Double totalAmount, String category, String reportType, double totalSales, Map<String, Double> salesByCategory, Map<String, Double> salesTrends) {
        this.id = id;
        this.saleDate = saleDate;
        this.totalAmount = totalAmount;
        this.category = category;
        this.reportType = reportType;
        this.totalSales = totalSales;// Set a default if needed
        this.salesByCategory = salesByCategory;
        this.salesTrends = salesTrends;
    }

    public double getTotalSales() {
        return totalSales;
    }
    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }
    public String getReportType() {
        return reportType;
    }
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Date getSaleDate() {
        return saleDate;
    }
    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Map<String, Double> getSalesByCategory() {
        return salesByCategory;
    }
    public void setSalesByCategory(Map<String, Double> salesByCategory) {
        this.salesByCategory = salesByCategory;
    }
    public Map<String, Double> getSalesTrends() {
        return salesTrends;
    }
    public void setSalesTrends(Map<String, Double> salesTrends) {
        this.salesTrends = salesTrends;
    }
    

    @Override
    public String toString() {
        return "Report{" +
                "id='" + id + '\'' +
                ", saleDate=" + saleDate +
                ", totalAmount=" + totalAmount +
                ", category='" + category + '\'' +
                ", reportType='" + reportType + '\'' +
                ", totalSales=" + totalSales +
                ", salesByCategory='" + salesByCategory + '\'' +
                ", salesTrends='" + salesTrends + '\'' +
                '}';
    }
}