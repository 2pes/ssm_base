package com.company.project.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 预约图书实体
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Appointment {

	private long bookId;// 图书ID

	private long studentId;// 学号

	private Date appointTime;// 预约时间

	// 多对一的复合属性
	private Book book;// 图书实体
	

}
