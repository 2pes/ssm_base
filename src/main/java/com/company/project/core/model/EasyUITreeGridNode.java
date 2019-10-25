package com.company.project.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
public class EasyUITreeGridNode implements Serializable {

	/** 树节点id，可选属性 */
	private String id;

	/** 节点名称 */
	private String name;

	/**
	 * 激活状态
	 * */
	private Integer status;

	/** 原始对象 */
	private Object attributes;

	/** 是否展开 open closed */
	private String state;

	/** 图标 */
	private Object icon;

	/** URL*/
	private String url;

	/** 父ID */
	private String _parentId;

	/** 类型 */
	private Integer type;

	 /**
	 * 是否选中
	 */
	private boolean checked;
	
}
