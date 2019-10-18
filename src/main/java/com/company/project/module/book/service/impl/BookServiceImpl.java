package com.company.project.module.book.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.core.exception.AppointException;
import com.company.project.core.exception.NoNumberException;
import com.company.project.core.exception.RepeatAppointException;
import com.company.project.core.model.ExceptionResultInfo;
import com.company.project.core.model.ResultInfo;
import com.company.project.module.book.dao.AppointmentMapper;
import com.company.project.module.book.dao.BookMapper;
import com.company.project.module.book.model.Appointment;
import com.company.project.module.book.model.Book;
import com.company.project.module.book.dto.AppointExecution;
import com.company.project.module.book.enums.AppointStateEnum;
import com.company.project.module.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 注入Service依赖
	@Autowired
	private BookMapper bookDao;
	@Autowired
	private AppointmentMapper appointmentDao;

	@Override
	public Book getById(long bookId) {
		return bookDao.queryById(bookId);
	}

	@Override
	public List<Book> getList() {
		return bookDao.queryAll(0, 1000);
	}

	@Override
	@Transactional
	/**
	 * 使用注解控制事务方法的优点： 1.开发团队达成一致约定，明确标注事务方法的编程风格
	 * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
	 * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
	 */
	public AppointExecution appoint(long bookId, long studentId) throws Exception {
		// 减库存
		int update = bookDao.reduceNumber(bookId);
		ResultInfo<AppointExecution> resultInfo = new ResultInfo<AppointExecution>();
		if (update <= 0) {// 库存不足
			// return new AppointExecution(bookId, AppointStateEnum.NO_NUMBER);//错误写法
			// 使用系统自定义异常类

			resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
			resultInfo.setData(new AppointExecution(bookId, AppointStateEnum.NO_NUMBER));
			resultInfo.setMessage("no number:"+AppointStateEnum.NO_NUMBER.getStateInfo());
			throw new ExceptionResultInfo(resultInfo);
		} else {
			// 执行预约操作
			int insert = appointmentDao.insertAppointment(bookId, studentId);
			if (insert <= 0) {// 重复预约
				// return new AppointExecution(bookId, AppointStateEnum.REPEAT_APPOINT);//错误写法
				resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
				resultInfo.setData(new AppointExecution(bookId, AppointStateEnum.REPEAT_APPOINT));
				resultInfo.setMessage("repeat appoint:"+AppointStateEnum.REPEAT_APPOINT.getStateInfo());
				throw new ExceptionResultInfo(resultInfo);
			} else {// 预约成功
				Appointment appointment = appointmentDao.queryByKeyWithBook(bookId, studentId);
				return new AppointExecution(bookId, AppointStateEnum.SUCCESS, appointment);
			}
		}
	}

}
