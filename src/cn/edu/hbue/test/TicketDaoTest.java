package cn.edu.hbue.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cn.edu.hbue.dao.TicketDao;

class TicketDaoTest {

	@Test
	void testSelectTicketID() {
//		assertEquals(1, TicketDao.selectTicketID("K599"));
	}
	
	@Test
	void testRefundTicket() {
		assertEquals(true, TicketDao.refundTicket(1));
	}
}
