package cn.edu.hbue.view;

import java.time.LocalDate;
import java.time.LocalDateTime;

import cn.edu.hbue.dao.OrderDao;
import cn.edu.hbue.dao.TicketCountDao;
import cn.edu.hbue.dao.TicketDao;
import cn.edu.hbue.dao.TrainDao;
import cn.edu.hbue.model.Order;
import cn.edu.hbue.model.TicketCount;
import cn.edu.hbue.model.Train;
import cn.edu.hbue.util.AlertUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

/**
 * @author czqmike
 * 买票窗口view-controller类，负责控件的加载和响应
 */
public class SelectDialogController {
	@FXML
	private TextField startStationField;
	@FXML
	private TextField endStationField;
	@FXML
	private TextField trainNumberField;
	@FXML
	private TextField ticketIdField;
	
	@FXML
	private DatePicker startDate;
	
	@FXML
	private Button selectButton;
	@FXML
	private Button purchaseButton;
	@FXML
	private Button selectByTrainNumberButton;
	@FXML
	private Button selectByTicketIdButton;
	
	@FXML
	private TableView<Train> trainTable;
	@FXML
	private TableColumn<Train, String> trainNumberColumn;
	@FXML
	private TableColumn<Train, String> startStationColumn;
	@FXML
	private TableColumn<Train, String> endStationColumn;
	@FXML
	private TableColumn<Train, String> startTimeColumn;//start_time本身为LocalDateTime类型
	@FXML
	private TableColumn<Train, String> timeCostColumn;//time_cost本身为int类型
	@FXML
	private TableColumn<Train, Double> priceColumn;
	
	private ObservableList<Train> data = null;
	private Train selectedTrain;
	
	/*
	 * 绑定每一列的数据
	 * 并且给表格添加监听器，每当被选择时，将被选择的数据赋值给selectedTrain
	 * 并设置购买按钮可以被点击（购买按钮初始为不可点击状态）
	 */
	@FXML
	private void initialize() {
		trainNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getTrainNumberProperty());
		startStationColumn.setCellValueFactory(cellData -> cellData.getValue().getStartStationProperty());
		endStationColumn.setCellValueFactory(cellData -> cellData.getValue().getEndStationProperty());
		startTimeColumn.setCellValueFactory(cellData -> cellData.getValue().getStartTimeProperty());
		timeCostColumn.setCellValueFactory(cellData -> cellData.getValue().getTimeCostProperty());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
		
		trainTable.getSelectionModel().selectedItemProperty().addListener(
        				(observable, oldValue, newValue) -> {
        				selectedTrain = newValue; 
						purchaseButton.setDisable(false);
						});
	}
	
	/*
	 * 车站查询的查询按钮之句柄
	 */
	@FXML
	private void handleSelectButton() {
		String start_station = startStationField.getText();
		String end_station = endStationField.getText();
		LocalDate start_date = startDate.getValue();
		
		//参数正确性检测
		if (start_station.equals("") || end_station.equals("")) {
			AlertUtil.showAlert(AlertType.WARNING, "起始站和终点站不能为空！");
			return;
		}
		if (start_date == null) {
			AlertUtil.showAlert(AlertType.WARNING, "请选择出发日期！");
			return;
		}
		
		data = TrainDao.SelectTrain(start_station, end_station, start_date);
		
		trainTable.setItems(data);
		
		if (data.isEmpty()) {
			purchaseButton.setDisable(true);//重置购买按钮为不可击
			AlertUtil.showAlert(AlertType.INFORMATION, "很抱歉，没有此躺列车");
			return;
		}
	}
	
	/*
	 * 如果这个车次还有余票，则更改ticket_count表并且插入一条记录到order表中,
	 * 然后置ticket表中这张表的bought为true
	 * 否则，提示用户尚无余票
	 */
	@FXML
	private void handlePurchaseButton() {
		TicketCount tc = TicketCountDao.SelectTicketCount(selectedTrain.getTrain_number());
		if (tc.getRemain() == 0) {
			AlertUtil.showAlert(AlertType.INFORMATION, "很抱歉，这趟列车暂无余票");
		} else {
			TicketCountDao.orderTicket(tc);
			
			Order order = new Order();
			order.setTicket_id(TicketDao.selectTicketID(selectedTrain.getTrain_number()));
			order.setUsername(SignInController.getUsername());
			order.setTime(LocalDateTime.now());
			order.setStation(selectedTrain.getStart_station());
			
			OrderDao.insertIntoOrder(order);
			
			TicketDao.purchaseTicket(order.getTicket_id());
			
			AlertUtil.showAlert(AlertType.INFORMATION, "购买成功！");
		}
	}
	
	/*
	 * 车次查询的查询按钮之句柄
	 */
	@FXML
	private void handleSelectByTrainNumber() {
		String train_number = trainNumberField.getText();
		if (train_number.equals("")) {
			AlertUtil.showAlert(AlertType.WARNING, "车次号不能为空！");
			return ;
		}
		
		data = TrainDao.SelectByTrainNumber(train_number);
		
		trainTable.setItems(data);
		
		if (data.isEmpty()) {
			purchaseButton.setDisable(true);//重置购买按钮为不可击
			AlertUtil.showAlert(AlertType.INFORMATION, "很抱歉，没有此躺列车");
			return;
		}
	}
	
	/*
	 * 车票查询的查询按钮之句柄
	 */
	@FXML
	private void handleSelectByTicketId() {
		String ticket_id = ticketIdField.getText();
		if (ticket_id.equals("")) {
			AlertUtil.showAlert(AlertType.WARNING, "车次号不能为空！");
			return ;
		}
		
		//将String的ticket_id 转换为 int
		int id = 0;
		try {
			id = Integer.parseInt(ticket_id);
		} catch (Exception e) {
			AlertUtil.showAlert(AlertType.WARNING, "车次号不合法！");
			return ;
		}
		
		data = TrainDao.SelectByTicketId(id);
		
		trainTable.setItems(data);
		
		if (data.isEmpty()) {
			purchaseButton.setDisable(true);//重置购买按钮为不可击
			AlertUtil.showAlert(AlertType.INFORMATION, "很抱歉，没有此躺列车");
			return;
		}
	}
}










