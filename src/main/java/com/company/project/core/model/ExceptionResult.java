package com.company.project.core.model;

import com.company.project.core.Result;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 自定义系统异常类
 */
@Getter
@Setter
@NoArgsConstructor
public class ExceptionResult extends Exception {

	// 系统统一使用的结果类，包括了 提示信息类型和信息内容
	private Result result;
	public ExceptionResult(Result result) {
		super(result.getMessage());
		this.result = result;
	}
	public ExceptionResult(String message, Result result,Throwable cause) {
        super(message, cause);
        this.result = result;
    }
	

}
