package com.company.project.core.model;

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
public class SubmitResultInfo {

	// 操作结果信息
	private ResultInfo resultInfo;

}
