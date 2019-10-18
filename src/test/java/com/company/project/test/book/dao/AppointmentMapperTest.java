package com.company.project.test.book.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.company.project.module.book.dao.AppointmentMapper;
import com.company.project.module.book.model.Appointment;
import com.company.project.test.BaseTest;

public class AppointmentMapperTest extends BaseTest {
	@Autowired
	private AppointmentMapper appointmentDao;

	@Test
	public void testInsertAppointment() throws Exception {
		long bookId = 1000;
		long studentId = 12345678910L;
		int insert = appointmentDao.insertAppointment(bookId, studentId);
		System.out.println("insert=" + insert);
	}

	@Test
	public void testQueryByKeyWithBook() throws Exception {
		long bookId = 1000;
		long studentId = 12345678910L;
		Appointment appointment = appointmentDao.queryByKeyWithBook(bookId, studentId);
		System.out.println(appointment);
		System.out.println(appointment.getBook());
	}


}
