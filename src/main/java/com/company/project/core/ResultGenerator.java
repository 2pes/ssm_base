package com.company.project.core;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
	private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

	public static Result genSuccessResult() {

		return new Result(ResultCode.SUCCESS, DEFAULT_SUCCESS_MESSAGE);

	}

	public static <T> Result<T> genSuccessResult(T data) {
		return new Result(ResultCode.SUCCESS, DEFAULT_SUCCESS_MESSAGE, data);
	}

	public static Result genFailResult(String message) {
		return new Result(ResultCode.FAIL, message);
	}

	public static Result genFailResult(ResultCode resultCode) {
		return new Result(resultCode, resultCode.getMessage());
	}
}
