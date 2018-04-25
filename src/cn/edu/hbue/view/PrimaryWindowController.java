package cn.edu.hbue.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author czqmike
 * 主窗口（登陆后进入的窗口）负责提供进入买票窗口和退票窗口的按钮
 * 以及加载相对应的FXML文件
 */
public class PrimaryWindowController {
//	private MainApp mainApp;
	
	/**
     * 构造函数在initialize()之前被调用
     */
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void handleSelect() {
		try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("SelectDialog.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("查询车票");
            anotherStage.setScene(new Scene(anotherRoot));
            anotherStage.centerOnScreen();
            anotherStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
	}
	
	@FXML
	private void handleMyTickect() {
		try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("MyTicketDialog.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("我的车票");
            anotherStage.setScene(new Scene(anotherRoot));
            anotherStage.centerOnScreen();
            anotherStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
	}
}
