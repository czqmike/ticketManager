package cn.edu.hbue.view;

import cn.edu.hbue.dao.UserDao;

import cn.edu.hbue.model.User;
import cn.edu.hbue.util.AlertUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * @author czqmike
 * 注册窗口view-controller类，负责控件的加载和响应
 */
public class SignUpController {
	@FXML
	private TextField NameField;
	@FXML
	private TextField UsernameField;
	@FXML
	private TextField PasswordField;
	@FXML
	private TextField PasswordRepeatField;
	@FXML
	private TextField PhoneNumberField;
	@FXML
	private TextField CitizenIDField;
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void handleCancel() {
		SignInController.getSignUpStage().close();
	}
	/*
	 * 注册按钮句柄
	 */
	@FXML
	private void handleSignUp() {
		String username = UsernameField.getText();
		String password = PasswordField.getText();
		String password_r = PasswordRepeatField.getText();
		String name = NameField.getText();
		String phone_number = PhoneNumberField.getText();
		String citizenID = CitizenIDField.getText();
		
		if (username.equals("") || password.equals("") || name.equals("") ||
				phone_number.equals("") || citizenID.equals("")) {
			AlertUtil.showAlert(AlertType.INFORMATION, "请将信息补充完整！");
			return ;
		}
		if (!password.equals(password_r)) {
			AlertUtil.showAlert(AlertType.WARNING, "两次输入密码不一致，请重新输入！");
			PasswordField.setText("");
			PasswordRepeatField.setText("");
			return ;
		}
		
		User u = new User(username, password, name, phone_number, citizenID);
		
		if (UserDao.insertIntoUser(u)) {
			AlertUtil.showAlert(AlertType.INFORMATION, "注册成功！");
			SignInController.getSignUpStage().close();
		} else {
			AlertUtil.showAlert(AlertType.WARNING, "用户名已存在，请重新输入用户名");
			UsernameField.setText("");
		}
	}
}

























