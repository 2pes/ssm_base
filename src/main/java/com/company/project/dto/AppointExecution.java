package com.company.project.dto;

import java.util.Date;

import com.company.project.enums.AppointStateEnum;
import com.company.project.model.Appointment;
import com.company.project.model.Book;
import com.company.project.model.Appointment.AppointmentBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 封装预约执行后结果
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AppointExecution {

	// 图书ID
	private long bookId;

	// 秒杀预约结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	// 预约成功对象
	private Appointment appointment;

	// 预约失败的构造器
	public AppointExecution(long bookId, AppointStateEnum stateEnum) {
		this.bookId = bookId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 预约成功的构造器
	public AppointExecution(long bookId, AppointStateEnum stateEnum, Appointment appointment) {
		this.bookId = bookId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.appointment = appointment;
	}

}
