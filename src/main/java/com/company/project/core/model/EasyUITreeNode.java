package com.company.project.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class EasyUITreeNode<T> implements Serializable {

	/** 树节点id，可选属性 */
	private String id;

	/** 节点名称 */
	private String text;

	/** 原始对象 */
	private Object attributes;

	/** 是否展开 open closed */
	private String state;

	/** 树节点图标，非必需 */
	private String iconCls;
	/** 孩子节点 */
	private List<EasyUITreeNode> children;
	/**
	 /**
	 * 是否选中
	 */
	private boolean checked;
	/**
	 * URL地址
	 * */
	private String url;

	/** 父ID */
	private String pid;
}
