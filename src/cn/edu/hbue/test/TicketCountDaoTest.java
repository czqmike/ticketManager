package cn.edu.hbue.test;

import org.junit.jupiter.api.Test;

import cn.edu.hbue.dao.TicketCountDao;

class TicketCountDaoTest {

	@Test
	void testSelect() {
//		assertEquals("K599", TicketCountDao.SelectTicketCount("K599").getTrain_number());
	}
	
	@Test
	void testOrder() {
//		TicketCountDao.orderTicket(TicketCountDao.SelectTicketCount("K599"));
	}
	
	@Test
	void testRefund() {
		TicketCountDao.refundTicket(TicketCountDao.SelectTicketCount("K599"));
	}
}
