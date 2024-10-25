package controller;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class LogInController {

    @FXML
    private Label forgetLink;

    @FXML
    private JFXRadioButton rdioRemember;

    @FXML
    private Label registerLink;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    private SessionFactory factory;

    public LogInController() {
        factory = new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
    }
    public String extractNameFromEmail(String email) {
        if (email == null || !email.contains("@")) {
            return "User";
        }
        return email.split("@")[0];
    }

    @FXML
    void btnLogIn(ActionEvent event) {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        boolean isAuthenticated = authenticateUser(email, password);

        if (isAuthenticated) {
            showAlert(Alert.AlertType.INFORMATION, "Success",
                    " HELLO " + extractNameFromEmail(email) + " !..... WELCOME TO CLOTHIFY STORE ... " +
                            " HAVE A NICE DAY !......");
            loadDashboard(event);
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid email or password" +
                    "... Use Sign up with Google...");
            System.out.println("Invalid email or password");
        }
    }

    private boolean authenticateUser(String email, String password) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            User user = (User) session.createQuery("FROM User WHERE email = :email AND password = :password")
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .uniqueResult();
            session.getTransaction().commit();
            return user != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    private void loadDashboard(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashboard.fxml"))));
            stage.show();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSingUpGoogle(ActionEvent event) {
        showAlert(Alert.AlertType.INFORMATION, "Success", "Google sign-up in progress...");
        System.out.println("Google sign-up in progress...");
        loadDashboard(event);
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

