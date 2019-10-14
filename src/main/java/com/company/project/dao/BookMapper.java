package com.company.project.dao;

import com.company.project.entity.Book;

public interface BookMapper {

	/**
	 * 通过ID查询单本图书
	 * 
	 * @param id
	 * @return
	 */
	Book queryById(long id);


}
