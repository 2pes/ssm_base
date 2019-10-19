package com.company.project.core.model;

import com.company.project.core.Result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 系统提交结果结果类型
 * 
 * @author Thinkpad
 *
 */
@Builder
@AllArgsConstructor	
@Getter
@Setter
public class SubmitResult {

	// 操作结果信息
	private Result result;

}
