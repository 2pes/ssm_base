package com.company.project.test.book.servcie;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.company.project.module.book.dto.AppointExecution;
import com.company.project.module.book.service.BookService;
import com.company.project.test.BaseTest;

public class BookServiceImplTest extends BaseTest {

	@Autowired
	private BookService bookService;

	@Test
	public void testAppoint() throws Exception {
		long bookId = 1001;
		long studentId = 12345678910L;
		AppointExecution execution = bookService.appoint(bookId, studentId);
		System.out.println(execution);
	}

}
