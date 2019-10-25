package com.company.project.core.exception;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.company.project.core.Result;
import com.company.project.core.model.ExceptionResult;
import com.company.project.module.book.dto.AppointExecution;
import com.company.project.module.book.enums.AppointStateEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * <p>
 * Title: ExceptionResolverCustom
 * </p>
 * <p>
 * Description:全局异常处理器
 * </p>
 * <p>
 * Company: www.itcast.com
 * </p>
 * 
 * @author 苗润土
 * @date 2014年11月26日下午5:36:56
 * @version 1.0
 */
@Getter
@Setter
public class ExceptionResolverCustom implements HandlerExceptionResolver {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	// json转换器
	// 将异常信息转json
	private HttpMessageConverter<ExceptionResult> jsonMessageConverter;

	// 前端控制器调用此方法执行异常处理
	// handler，执行的action类就包装了一个方法（对应url的方法）
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		// 输出 异常信息
		ex.printStackTrace();
		// 转成springmvc底层对象（就是对action方法的封装对象，只有一个方法）
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		// 取出方法
		Method method = handlerMethod.getMethod();

		// 判断方法是否返回json
		// 只要方法上有responsebody注解表示返回json
		// 查询method是否有responsebody注解
		ResponseBody responseBody = AnnotationUtils.findAnnotation(method, ResponseBody.class);
		if (responseBody != null) {
			// 将异常信息转json输出
			return this.resolveJsonException(request, response, handlerMethod, ex);

		}
		// 这里说明action返回的是jsp页面

		// 解析异常
		ExceptionResult exceptionResultInfo = resolveExceptionCustom(ex);

		// 将异常信息在异常页面显示
		request.setAttribute("exceptionResultInfo", exceptionResultInfo.getResult());

		// 转向错误页面
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exceptionResultInfo", exceptionResultInfo.getResult());
		modelAndView.setViewName("error");// 逻辑视图名
		return modelAndView;
	}

	// 异常信息解析方法
	private ExceptionResult resolveExceptionCustom(Exception ex) {
		Result resultInfo = null;
		if (ex instanceof ExceptionResult) {
			// 抛出的是系统自定义异常
			resultInfo = ((ExceptionResult) ex).getResult();
		} else if (ex instanceof UnauthorizedException){
			resultInfo.setMessage("没有权限");

		}else {
			// 重新构造“未知错误”异常
			resultInfo = new Result();
			resultInfo.setType(Result.TYPE_RESULT_FAIL);
			resultInfo.setData(new AppointExecution(AppointStateEnum.INNER_ERROR.getState(), AppointStateEnum.INNER_ERROR));
			resultInfo.setMessage("未知错误！" + AppointStateEnum.INNER_ERROR.getStateInfo());
		}

		return new ExceptionResult(resultInfo);

	}

	// 将异常信息转json输出
	private ModelAndView resolveJsonException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		// 解析异常
		ExceptionResult exceptionResultInfo = resolveExceptionCustom(ex);
		HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
		try {
			// 将exceptionResultInfo对象转成json输出
			jsonMessageConverter.write(exceptionResultInfo, MediaType.APPLICATION_JSON, outputMessage);
		} catch (HttpMessageNotWritableException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return new ModelAndView();

	}

}
