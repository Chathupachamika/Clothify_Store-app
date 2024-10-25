package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import entity.Reports;
import service.ReportService;
import service.impl.ReportServiceImpl;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static service.impl.ReportServiceImpl.*;

public class ReportController implements Initializable {

    private Reports report;
    @FXML
    private PieChart PieChart;

    @FXML
    private PieChart PieChart2;

    @FXML
    private PieChart PieChart21;

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    private LineChart<Number, Double> lineChart2;

    @FXML
    private LineChart<Number, Double> lineChartDaily;
    @FXML
    private LineChart<Number, Number> lineChartAnnually;
    @FXML
    private JFXComboBox<?> cmbYear;

    @FXML
    void btnGo(ActionEvent event) {
        generateAnnualPieChart(String.valueOf(cmbYear.getValue()));
        updateLineChartAnnually(String.valueOf(cmbYear.getValue()));
    }
    private final ReportService supplierService = new ReportServiceImpl();
    List<Reports> allProducts = supplierService.getAllReports();

    @FXML
    void btnExportToPDF(ActionEvent event) {
        showAlert(Alert.AlertType.INFORMATION, "Success", "Processing Export to pdf... wait....!");
        }

    @FXML
    void btnRefersh(ActionEvent event) {
        refreshall();
        generateAnnualPieChart(String.valueOf(cmbYear.getValue()));
        updateLineChartAnnually(String.valueOf(cmbYear.getValue()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshall();
    }
    private void refreshall(){
        generateReport();
        generatePie();
        generatePie2();
        updateLineChartyear();
    }
    private void generateReport(){
        for (Reports reports : allProducts) {
            String id = reports.getId();
            Date saleDate = reports.getSaleDate();
            Double totalAmount = reports.getTotalAmount();
            String category = reports.getCategory();
            String reportType = reports.getReportType();
            double totalSales = reports.getTotalSales();
            Map<String, Double> salesByCategory = Map.of(
                    "Gents", calculateSalesByCategory("Gents"),
                    "Ladies", calculateSalesByCategory("Ladies"),
                    "Kids", calculateSalesByCategory("Kids"),
                    "Others", calculateSalesByCategory("Others")
            );
            Map<String, Double> salesTrends = Map.of(
                    "January", calculateSalesByMonth("January"),
                    "February", calculateSalesByMonth("February"),
                    "March", calculateSalesByMonth("March"),
                    "April", calculateSalesByMonth("April"),
                    "May", calculateSalesByMonth("May"),
                    "June", calculateSalesByMonth("June"),
                    "July", calculateSalesByMonth("July"),
                    "August", calculateSalesByMonth("August"),
                    "September", calculateSalesByMonth("September"),
                    "October", calculateSalesByMonth("October")
            );
            Reports report = new Reports(id, saleDate, totalAmount, category, reportType, totalSales, salesByCategory, salesTrends);
            updateLineChart(report.getSalesTrends());
            updateLineChart2(report.getSalesByCategory());
        }
    }
    private void updateLineChart2(Map<String, Double> salesByCategory) {
        lineChart2.getData().clear();
        XYChart.Series<Number, Double> series = new XYChart.Series<>();
        series.setName("Sales by Category");
        Map<String, Integer> categoryToIndex = Map.of(
                "Gents", 0,
                "Ladies", 1,
                "Kids", 2,
                "Others", 3
        );
        for (Map.Entry<String, Double> entry : salesByCategory.entrySet()) {
            String category = entry.getKey();
            Double salesAmount = entry.getValue();
            Integer index = categoryToIndex.get(category);
            if (index != null) {
                series.getData().add(new XYChart.Data<>(index, salesAmount));
            }
        }
        lineChart2.getXAxis().setLabel("Categories");
        lineChart2.getYAxis().setLabel("Total Amount");
        lineChart2.getData().add(series);
    }
    private void updateLineChart(Map<String, Double> salesTrends) {
        lineChart.getData().clear();
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Sales Trend");
        int timeIndex = 1;
        for (Map.Entry<String, Double> entry : salesTrends.entrySet()) {
            String month = entry.getKey();
            Double salesAmount = entry.getValue();
            int monthIndex = getMonthIndex(month);
            series.getData().add(new XYChart.Data<>(monthIndex, salesAmount));
        }
        lineChart.getData().add(series);
    }
    public static int getMonthIndex(String month) {
        switch (month) {
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
            default:
                return 0; // Invalid month
        }
    }
    private void generatePie(){
        PieChart.getData().clear();
        report = new Reports("Report_ID", new Date(), 0.0, "General", "Monthly Sales Report", 0.0, null,null );
        report.setSalesByCategory(Map.of(
                "January", calculateSalesByMonth("January"),
                "February", calculateSalesByMonth("February"),
                "March", calculateSalesByMonth("March"),
                "April", calculateSalesByMonth("April"),
                "May", calculateSalesByMonth("May"),
                "June", calculateSalesByMonth("June"),
                "July", calculateSalesByMonth("July"),
                "August", calculateSalesByMonth("August"),
                "September", calculateSalesByMonth("September"),
                "October", calculateSalesByMonth("October")
        ));
        Map<String, Double> salesByCategory = report.getSalesByCategory();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Double> entry : salesByCategory.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        PieChart.setData(pieChartData);
        for (PieChart.Data data : pieChartData) {
            data.getNode().setOnMouseEntered(e -> {
                Tooltip tooltip = new Tooltip(data.getName() + ": " + data.getPieValue());
                Tooltip.install(data.getNode(), tooltip);
            });
        }
    }
    private void generatePie2(){
        PieChart2.getData().clear();
        report = new Reports("Report_ID", new Date(), 0.0, "General", "Monthly Sales Report", 0.0, null,null );
        report.setSalesByCategory(Map.of(
                "Gents", calculateSalesByCategory("Gents"),
                "Ladies", calculateSalesByCategory("Ladies"),
                "Kids", calculateSalesByCategory("Kids"),
                "Others", calculateSalesByCategory("Others")
        ));
        Map<String, Double> salesByCategory = report.getSalesByCategory();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Double> entry : salesByCategory.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        PieChart2.setData(pieChartData);
        for (PieChart.Data data : pieChartData) {
            data.getNode().setOnMouseEntered(e -> {
                Tooltip tooltip = new Tooltip(data.getName() + ": " + data.getPieValue());
                Tooltip.install(data.getNode(), tooltip);
            });
        }

    }
    private void generateAnnualPieChart(String year) {
        PieChart21.getData().clear();
        Map<String, Double> annualSalesData = ReportServiceImpl.calculateSalesByYear(year);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Double> entry : annualSalesData.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        PieChart21.setData(pieChartData);
        for (PieChart.Data data : pieChartData) {
            data.getNode().setOnMouseEntered(e -> {
                Tooltip tooltip = new Tooltip(data.getName() + ": " + data.getPieValue());
                Tooltip.install(data.getNode(), tooltip);
            });
        }
    }
    private void updateLineChartAnnually(String year) {
        Map<String, Double> salesTrends = calculateSalesByYear(year);
        lineChartAnnually.getData().clear();
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Sales Trend " + year);
        for (Map.Entry<String, Double> entry : salesTrends.entrySet()) {
            String month = entry.getKey();
            Double salesAmount = entry.getValue();
            int monthIndex = getMonthIndex(month);
            series.getData().add(new XYChart.Data<>(monthIndex, salesAmount));
        }
        lineChartAnnually.getData().add(series);
    }
    private void updateLineChartyear() {
        Map<String, Double> salesByYear = ReportServiceImpl.calculateTotalSalesForYears();
        lineChartDaily.getData().clear();
        XYChart.Series<Number, Double> series = new XYChart.Series<>();
        series.setName("Total Sales by Year");

        Map<String, Integer> categoryToIndex = Map.of(
                "2020", 0,
                "2021", 1,
                "2022", 2,
                "2023", 3,
                "2024", 4
        );
        for (Map.Entry<String, Double> entry : salesByYear.entrySet()) {
            String yearStr = entry.getKey();
            Double totalSalesAmount = entry.getValue();
            int year = categoryToIndex.get(yearStr);

            series.getData().add(new XYChart.Data<>(year, totalSalesAmount));
        }
        lineChartDaily.getXAxis().setLabel("Year");
        lineChartDaily.getYAxis().setLabel("Total Amount");
        lineChartDaily.getData().add(series);
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().setStyle("-fx-background-color: #FFFAF0; -fx-font-family: 'Verdana'; " +
                "-fx-font-size: 14px; -fx-text-fill: #333;");
        alert.getDialogPane().lookupButton(ButtonType.OK).setStyle("-fx-background-color: #033E3E; " +
                "-fx-text-fill: white; " +
                "-fx-font-weight: bold;");
        alert.showAndWait();

    }
}
