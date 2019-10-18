package com.company.project.core.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义系统异常类
 */
@Getter
@Setter
public class ExceptionResultInfo extends Exception {

	// 系统统一使用的结果类，包括了 提示信息类型和信息内容
	private ResultInfo resultInfo;

	public ExceptionResultInfo(ResultInfo resultInfo) {
		super(resultInfo.getMessage());
		this.resultInfo = resultInfo;
	}

	

}
