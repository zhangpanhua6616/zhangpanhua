package com.soft1841.sm.controller;

import cn.hutool.db.Entity;
import com.soft1841.sm.dao.AdminDAO;
import com.soft1841.sm.entity.Admin;
import com.soft1841.sm.utils.DAOFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.SQLException;

import java.util.*;

public class AdminController implements Initializable {
    @FXML
    private FlowPane adminPane;
    private AdminDAO adminDAO = DAOFactory.getAdminDAOInstance();
    List<Entity> adminList = new ArrayList<>();
    @FXML
    private TextField adminWordsField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            adminList = adminDAO.selectAdmin();
            System.out.println(adminList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showAdmins(adminList);
    }
    //通过循环遍历readerList集合，创建HBox来显示每个读者信息
    private void showAdmins(List<Entity> adminList){
        ObservableList<Node> observableList = adminPane.getChildren ();
        adminPane.getChildren ().removeAll ( observableList );
        for (Entity entity:adminList) {
            HBox hBox = new HBox();
            hBox.setPrefSize(400,250);
            hBox.setSpacing(10);
            hBox.setPadding(new Insets(10,10,10,10));
            hBox.getStyleClass().add("box");
            //创建左侧垂直布局,用来放头像和角色
            VBox leftBox = new VBox();
            leftBox.setSpacing(20);
            leftBox.setAlignment(Pos.TOP_CENTER);
            ImageView avatarImg =new ImageView(new Image(entity.getStr("avatar")));
            avatarImg.setFitWidth(80);
            avatarImg.setFitHeight(80);
            Circle circle = new Circle ();
            circle.setCenterX ( 40 );
            circle.setCenterY ( 40 );
            circle.setRadius ( 40 );
            avatarImg.setClip ( circle );

            leftBox.getChildren().add(avatarImg);


            VBox rightBox = new VBox (  );
            rightBox.setSpacing ( 20 );
            rightBox.setAlignment ( Pos.TOP_CENTER );
            Label nameLabel = new Label ( entity.getStr ( "name" ) );
            Label passwordLabel = new Label ( entity.getStr ( "password" ) );

            Button delBtn = new Button ( "删除" );
            delBtn.getStyleClass().add("warning-theme");
            delBtn.getStyleClass().add("btn-basic");
            delBtn.getStyleClass().add("btn-radius-large");
            delBtn.setPrefSize(80,40);
            delBtn.setOnAction ( event -> {
                Alert alert = new Alert ( Alert.AlertType.CONFIRMATION );
                alert.setTitle ( "确认对话框" );
                alert.setContentText ( "确认要删除这行记录吗？" );
                Optional<ButtonType> result = alert.showAndWait ();
                if (result.get () == ButtonType.OK){
                    long id = entity.getLong ( "admin_id" );
                    try {
                        adminDAO.deleteById( id );
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    adminPane.getChildren ().remove ( hBox );
                }

            } );
            delBtn.getStyleClass ().add ( "orange-theme" );
            delBtn.setPrefSize ( 80,50);

            rightBox.getChildren().addAll(nameLabel,delBtn);
            hBox.getChildren().add(leftBox);
            hBox.getChildren ().add ( rightBox );
            adminPane.getChildren ().add ( hBox );
        }
        try {
            adminList = adminDAO.selectAdmin ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    //新增读者方法
    public void addAdmin() throws SQLException{
        //创建一个Reader对象
        Admin admin = new Admin();
        //新建一个舞台
        Stage stage = new Stage();
        stage.setTitle("新增管理员界面");
        //创建一个垂直布局，用来放新增用户的各个组件
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(20,10,10,10));
        TextField nameField = new TextField("请输入姓名");


        //新增按钮
        Button addBtn = new Button("新增");
        addBtn.getStyleClass().add("blue-theme");
        vBox.getChildren().addAll(nameField,addBtn);
        Scene scene = new Scene(vBox,600,380);
        scene.getStylesheets().add("/css/style.css");
        stage.setScene(scene);
        stage.show();
        //点击新增按钮，将界面数据封装成一个Reader对象，写入数据库
        addBtn.setOnAction(event ->{
            String nameString = nameField.getText().trim();

            System.out.println(admin.getName()+admin);
            try {
                adminDAO.insertAdmin(admin);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stage.close();
            //重新读取数据显示
            try {
                adminList = adminDAO.selectAdmin();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            showAdmins(adminList);
        });
    }

}