package cn.edu.hbue.view;

import java.time.LocalDateTime;

import cn.edu.hbue.dao.MyTicketDao;
import cn.edu.hbue.dao.OrderDao;
import cn.edu.hbue.dao.RefundDao;
import cn.edu.hbue.dao.TicketCountDao;
import cn.edu.hbue.dao.TicketDao;
import cn.edu.hbue.model.MyTicket;
import cn.edu.hbue.model.Refund;
import cn.edu.hbue.util.AlertUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

/**
 * @author czqmike
 * MyTicketDialog的view-controller类，用于FXML文件的加载以及相应控件信息
 */
public class MyTicketDialogController {
	final private double charge = 0.95;//退票时，退票金额 = 原金额 * charge 
	
	@FXML
	private Button refundButton;
	@FXML
	private TableView<MyTicket> myTicketTable;
	@FXML
	private TableColumn<MyTicket, String> trainNumberColumn;
	@FXML
	private TableColumn<MyTicket, Integer> ticketIDColumn;
	@FXML
	private TableColumn<MyTicket, String> startStationColumn;
	@FXML
	private TableColumn<MyTicket, String> endStationColumn;
	@FXML
	private TableColumn<MyTicket, String> startTimeColumn;//start_time本身为LocalDateTime类型
	@FXML
	private TableColumn<MyTicket, String> timeCostColumn;//time_cost本身为int类型
	@FXML
	private TableColumn<MyTicket, Double> priceColumn;
	@FXML
	private TableColumn<MyTicket, String> seatNumberColumn;
	
	private ObservableList<MyTicket> data = null;
	
	private MyTicket selectedMyTicket;
	
	/*
	 * 完成表格列和数据的绑定
	 * 并且为表格增加监听器以获得被选中的MyTicket
	 */
	@FXML
	private void initialize() {
		trainNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getTrainNumberProperty());
		ticketIDColumn.setCellValueFactory(cellData -> cellData.getValue().getTicketIDProperty().asObject());
		startStationColumn.setCellValueFactory(cellData -> cellData.getValue().getStartStationProperty());
		endStationColumn.setCellValueFactory(cellData -> cellData.getValue().getEndStationProperty());
		startTimeColumn.setCellValueFactory(cellData -> cellData.getValue().getStartTimeProperty());
		timeCostColumn.setCellValueFactory(cellData -> cellData.getValue().getTimeCostProperty());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
		seatNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getSeatNumberProperty());
		
		myTicketTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
						selectedMyTicket = newValue;
						refundButton.setDisable(false);
		});
		
		data = MyTicketDao.SelectMyTicket(SignInController.getUsername());
		
		myTicketTable.setItems(data);
	}
	
	/*
	 * 单击退票按钮时，修改ticket, ticket_count, order表上的数据
	 * 并且为refund插入一条退票信息
	 */
	@FXML
	private void handleRefund() {
		//把被选中的Ticket.bought设置为false
		TicketDao.refundTicket(selectedMyTicket.getTicket_id());
		//
		TicketCountDao.refundTicket(TicketCountDao.SelectTicketCount(selectedMyTicket.getTrain_number()));
		OrderDao.deleteOrder(selectedMyTicket.getTicket_id(), SignInController.getUsername());
		
		Refund refund = new Refund();
		refund.setTicket_id(selectedMyTicket.getTicket_id());
		refund.setUsername(SignInController.getUsername());
		refund.setTime(LocalDateTime.now());
		refund.setPrice(selectedMyTicket.getPrice() * charge);
		
		RefundDao.insertIntoRefund(refund);
		
		data = MyTicketDao.SelectMyTicket(SignInController.getUsername());
		
		myTicketTable.setItems(data);
		
		refundButton.setDisable(true);			//设置按钮不可击，防止用户误操作
		AlertUtil.showAlert(AlertType.INFORMATION, "退票成功！");
	}
}





















