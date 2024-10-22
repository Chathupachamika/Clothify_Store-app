package service.impl;

import controller.ReportController;
import db.DBConnection;
import dto.ReportsDTO;
import entity.Product;
import entity.Reports;
import org.hibernate.Session;
import org.hibernate.query.Query;
import service.ReportService;
import util.HibernateUtil;

import java.sql.*;
import java.util.Date;
import java.util.*;

public class ReportServiceImpl implements ReportService {

    private static Connection connection;

    public ReportServiceImpl() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reports> getAllReports() {
//        Session session = HibernateUtil.getSession();
//        session.beginTransaction();
//        List<Reports> products = session.createQuery("from Reports", Reports.class).list();
//        session.getTransaction().commit();
//        session.close();
//        return products;
        List<Reports> reports = new ArrayList<>();
        String query = "SELECT * FROM sales";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                String id = rs.getString("id");
                Date saleDate = rs.getDate("sale_date");
                double totalAmount = rs.getDouble("total_amount");
                String category = rs.getString("category");
                String reportType = "Monthly"; // Example static report type
                double totalSales = totalAmount; // Example logic for total sales
                Map<String, Double> salesByCategory = new HashMap<>();
                Map<String, Double> salesTrends = new HashMap<>();
                Reports report = new Reports(id, saleDate, totalAmount, category, reportType, totalSales, salesByCategory, salesTrends);
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }
    public static double calculateSalesByMonth(String month) {
        double totalSales = 0.0;
        int monthNumber = ReportController.getMonthIndex(month);
        String query = "SELECT SUM(total_amount) FROM sales WHERE MONTH(sale_date) = ?"; // Adjust table/column names as needed

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, monthNumber);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    totalSales = rs.getDouble(1); // Get the sum from the first column
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalSales;
    }
    public static double calculateSalesByCategory(String category) {
        double totalSales = 0.0;
        String query = "SELECT SUM(total_amount) FROM sales WHERE category = ?"; // Adjust table/column names as needed

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, category);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    totalSales = rs.getDouble(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalSales;
    }
    public static Map<String, Double> calculateSalesByYear(String year) {
        Map<String, Double> monthlySales = new HashMap<>();
        for (int month = 1; month <= 12; month++) {
            double totalSales = 0.0;
            String query = "SELECT SUM(total_amount) FROM sales WHERE YEAR(sale_date) = ? AND MONTH(sale_date) = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, Integer.parseInt(year));
                statement.setInt(2, month);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        totalSales = rs.getDouble(1);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            monthlySales.put(getMonthName(month), totalSales);
        }
        return monthlySales;
    }
    private static String getMonthName(int month) {
        String[] monthNames = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        return monthNames[month - 1];
    }

    public static Map<String, Double> calculateTotalSalesForYears() {

        Map<String, Double> totalSalesByYear = new HashMap<>();
        String query = "SELECT YEAR(sale_date), SUM(total_amount) FROM sales WHERE YEAR(sale_date) IN (2021, 2022, 2023, 2024) GROUP BY YEAR(sale_date)";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                int year = rs.getInt(1);
                double totalSales = rs.getDouble(2);
                totalSalesByYear.put(String.valueOf(year), totalSales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalSalesByYear;
    }

}

