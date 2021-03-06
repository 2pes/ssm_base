package com.company.project.module.book.web;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.project.core.Result;
import com.company.project.module.book.dto.AppointExecution;
import com.company.project.module.book.model.Book;
import com.company.project.module.book.service.LibraryBookService;

@Controller
@RequestMapping("/library/book") // url:/模块/资源/{id}/细分 /seckill/list
public class LibraryBookController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LibraryBookService bookService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@RequiresPermissions("items:query")
	private String list(Model model) {
		List<Book> list = bookService.getList();
		model.addAttribute("list", list);
		return "module/book/list";
	}

	@RequestMapping(value = "/{bookId}/detail", method = RequestMethod.GET)
	private String detail(@PathVariable("bookId") Long bookId, Model model) {
		if (bookId == null) {
			return "redirect:/book/list";
		}
		Book book = bookService.getById(bookId);
		if (book == null) {
			return "forward:/book/list";
		}
		model.addAttribute("book", book);
		return "module/book/detail";
	}

	// ajax json
	@RequestMapping(value = "/{bookId}/appoint", method = RequestMethod.POST, produces = {
			"application/json; charset=utf-8" })
	@ResponseBody
	private Result<AppointExecution> appoint(@PathVariable("bookId") Long bookId,
			@RequestParam("studentId") Long studentId) throws Exception {
		if (studentId == null || studentId.equals("")) {
			return new Result<>(false, "学号不能为空");
		}
		// AppointExecution execution = bookService.appoint(bookId,
		// studentId);//错误写法，不能统一返回，要处理异常（失败）情况
		AppointExecution execution = bookService.appoint(bookId, studentId);

		return new Result<AppointExecution>(true, execution);
	}

}
