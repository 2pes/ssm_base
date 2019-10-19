package com.company.project.core.model;

import java.util.ArrayList;
import java.util.List;

import com.company.project.core.Result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 数据查询列表结果
 * 
 * @author Thinkpad
 *
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DataGridResult {

	public DataGridResult(Result result) {
		this.result = result;
	}

	// 操作结果信息
	private Result result;

	// 总条数
	private int total;

	// 结果集
	private List rows = new ArrayList();

	// 总计告诉footer
	private List footer = new ArrayList();

}
