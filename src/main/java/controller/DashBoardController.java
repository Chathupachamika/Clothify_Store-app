package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {
    @FXML
    private ImageView imgIcon;
    @FXML
    private Label txtDate;

    @FXML
    private Label txtTime;
    @FXML
    void btnLogout(ActionEvent event) throws IOException {
        Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/login.fxml"))));
            stage.show();
        Stage stage2 = new Stage();
        try {
            stage2.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashboard.fxml"))));
            stage2.show();
            stage2.close();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnOrderManagement(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/order.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnProductManagement(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/product.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnReports(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/report.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSupplierManagement(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/supplier.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnEmployeeManagement(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/employee.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadDateandTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = f.format(date);
        txtDate.setText(dateNow);

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            int hour = now.getHour();

            txtTime.setText(String.format("%02d : %02d : %02d", now.getHour(), now.getMinute(), now.getSecond()));

            String icon = "";
            if (hour >= 0 && hour < 12) {
                icon = "/img/morning.png";
            } else if (hour >= 12 && hour < 16) {
                icon = "/img/afternoon.png";
            } else if (hour >= 16 && hour < 19) {
                icon = "/img/pngtree-good-evening-with-sun-sticker-png-image_8550250.png";
            } else if (hour >= 19 && hour < 24) {
                icon = "/img/night.png";
            }

            imgIcon.setImage(new Image(getClass().getResourceAsStream(icon)));
           // System.out.println("Current icon: " + icon);

        }),
                new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateandTime();
    }
}
