package com.soft1841.sm.controller;
/**
 * 管理系统登录
 * @author 张攀华
 * date 2018-12-25
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField accountField;
    @FXML
    private PasswordField passwordField;
    public void login() throws Exception {
        String account = accountField.getText().trim();
        String password = passwordField.getText().trim();
        if ("123456".equals(account) && "123456".equals(password)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setContentText("登录成功!");
            alert.showAndWait();
            Stage mainStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            BorderPane root = fxmlLoader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/css/style.css");
            mainStage.setTitle("super-market");
            mainStage.setMaximized(true);
            mainStage.setScene(scene);
            mainStage.getIcons().add(new Image("/img/img.jpg"));
            mainStage.show();
            Stage loginStage = (Stage) accountField.getScene().getWindow();
            loginStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setContentText("账号或密码错误，登录失败!");
            alert.showAndWait();
        }
    }
}
