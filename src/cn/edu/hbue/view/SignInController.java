package cn.edu.hbue.view;

import cn.edu.hbue.MainApp;
import cn.edu.hbue.dao.UserDao;
import cn.edu.hbue.model.User;
import cn.edu.hbue.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;  
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;  

/**
 * @author czqmike
 * @see 如果要获得登录时的username，请使用SingInController.getUsername()方法
 * 登陆窗口view-controller类，负责控件的加载和响应
 */
public class SignInController {
	@FXML
	private TextField usernameField;
	@FXML
	private TextField passwordField;
	@FXML
	private ChoiceBox<String> cb;
	@FXML
	private Button SignIn;
	@FXML
	private Button SignUp;
	
	private static Stage SignUpStage;
	
	private static String username;
	
	/*
	 * 在FXML文件加载完后自动被系统调用
	 * 设置登陆按钮移入移出的特效
	 */
	@FXML
	private void initialize() {
		cb.setItems(FXCollections.observableArrayList("用户", "管理员"));
		cb.getSelectionModel().select(0);
//		cb.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
//				->System.out.println(newValue));
		SignIn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e)->
				SignIn.setBorder(new Border(new BorderStroke(Color.BLUE, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
		SignIn.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, (MouseEvent e)->
				SignIn.setBorder(Border.EMPTY));
	}
	 
	@FXML
	private void handleSignInButton() {
		User u = null;
		u = UserDao.selectUser(usernameField.getText());

		if ( !u.getPassword().equals(passwordField.getText())) {
			AlertUtil.showAlert(AlertType.WARNING, "用户不存在或密码错误！");
		} else {
			SignInController.username = usernameField.getText();
			AlertUtil.showAlert(AlertType.INFORMATION, "登陆成功！");
			MainApp.showPrimaryWindow();
		}
	}
	
	/*
	 * 创建模态对话框，防止SignUpStage被多次初始化
	 */
	@FXML
	private void handleSignUpButton() {
		try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            SignUpStage = new Stage();
            SignUpStage.setTitle("注册");
            SignUpStage.initModality(Modality.APPLICATION_MODAL);
            SignUpStage.setScene(new Scene(anotherRoot));
            SignUpStage.centerOnScreen();
            SignUpStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
	}
	
	/*
	 * 实现当用户输完密码后按ENTER即可登陆的功能。
	 */
	@FXML
	private void handlePasswordField() {
		passwordField.setOnKeyPressed((KeyEvent event)->{
			if (event.getCode() == KeyCode.ENTER) {
				handleSignInButton();
			}
		});
	}
	
	public static Stage getSignUpStage() {
		return SignUpStage;
	}
	public static String getUsername() {
		return username;
	}
}
