package com.company.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.project.dao.BookMapper;
import com.company.project.model.Book;
import com.company.project.service.BookService;


@Service
public class BookServiceImpl implements BookService {


	// 注入Service依赖
	@Autowired
	private BookMapper bookDao;

	@Override
	public Book getById(long bookId) {
		return bookDao.queryById(bookId);
	}

	
}
