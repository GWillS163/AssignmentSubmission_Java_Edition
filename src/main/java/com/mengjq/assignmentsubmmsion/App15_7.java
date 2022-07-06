package com.mengjq.assignmentsubmmsion;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ScrollPane;
public class App15_7 extends Application
{
    private TextField tf=new TextField();
    private PasswordField pf=new PasswordField();
    private TextArea ta=new TextArea("未登录");
    @Override
    public void start(Stage Stage)
    {
        GridPane rootGP=new GridPane();
        final Label lab1=new Label("用户名：");
        final Label lab2=new Label("密  码：");
        tf.setPromptText("输入用户名");
        pf.setPromptText("输入密码");
        rootGP.add(lab1,0,0);
        rootGP.add(tf,1,0);
        rootGP.add(lab2,0,1);
        rootGP.add(pf,1,1);
        final ScrollPane scro=new ScrollPane(ta);
        ta.setPrefColumnCount(15);
        ta.setEditable(false);
        pf.setOnAction(e->
                {
                    ArrayList databases = new ArrayList();
                    user user1 = new user("tim","123");
                    databases.add(user1);
                    user user2 = new user("mary","121");
                    databases.add(user2);
                    user user3 = new user("bill","321");
                    databases.add(user3);
                    user user4 = new user("carl","110");
                    databases.add(user4);
                    for(int i=0;i<=databases.size()-1;i++) {
                        if(tf.getText().equals(((user) databases.get(i)).getname()) && pf.getText().equals(((user) databases.get(i)).getpsw()))
                        {
                            ta.setEditable(true);
                            ta.setWrapText(true);
                            ta.setStyle("-fx-text-fill:red");
                            ta.setText("恭喜你！！\n登录成功");
                        }
                    }
                }

        );
        rootGP.add(scro,0,3,4,3);
        Scene scene=new Scene(rootGP,190,120);
        Stage.setTitle("登录");
        Stage.setScene(scene);
        Stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}