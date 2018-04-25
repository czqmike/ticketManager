package cn.edu.hbue.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @author czqmike
 * 简化显示对话框操作
 */
public class AlertUtil {
	public static void showAlert(AlertType type, String string) {
		Alert a = new Alert(type);
		a.setContentText(string);
		a.setHeaderText(null);
		a.showAndWait();
	}
}
