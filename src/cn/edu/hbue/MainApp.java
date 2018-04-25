package cn.edu.hbue;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	private static Stage primaryStage;
    private AnchorPane SignIn;
    private static AnchorPane PrimaryWindow;
    
	@Override
	public void start(Stage primaryStage) {
		MainApp.primaryStage = primaryStage;
		MainApp.primaryStage.setTitle("登录");
		
		showSignIn();
		
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	private void showSignIn() {
		   try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class
	                    .getResource("view/SignIn.fxml"));
	            SignIn = (AnchorPane)loader.load();

	            // Show the scene containing the signIn window.
	            Scene scene = new Scene(SignIn);
	            primaryStage.setScene(scene);
	            primaryStage.centerOnScreen();
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public static void showPrimaryWindow() {
		 try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class
	                    .getResource("view/PrimaryWindow.fxml"));
	            PrimaryWindow = (AnchorPane)loader.load();

	            // Show the scene containing the signIn window.
	            Scene scene = new Scene(PrimaryWindow);
	            primaryStage.setScene(scene);
            	primaryStage.setTitle("车票管理系统");
            	primaryStage.centerOnScreen();
            
                primaryStage.setScene(scene);
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}













