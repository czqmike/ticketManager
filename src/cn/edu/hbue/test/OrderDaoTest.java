package cn.edu.hbue.test;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import cn.edu.hbue.model.Order;

class OrderDaoTest {

	@Test
	void test() {
		Order order = new Order();
		order.setTicket_id(1);
		order.setUsername("test");
		order.setTime(LocalDateTime.now());
		order.setStation("武昌");
		
		
	}

}
