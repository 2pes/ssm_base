package com.company.project.test.datasource;

import com.company.project.configurer.datasource.MultiDataSource;
import com.company.project.module.book.model.Book;
import com.company.project.module.book.service.BookService;
import com.company.project.module.mall.model.MallCost;
import com.company.project.module.mall.service.MallCostService;
import com.company.project.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Chen
 * @created 2019-10-26-15:12.
 */
public class DatasourceTest extends BaseTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private MallCostService mallCostService;

    /**
     * 使用工具类切换数据源
     */
    @Test
    public void test() {
        List<Book> booklist = bookService.getList();
        booklist.forEach(book -> System.out.println(book.toString()));
        MultiDataSource.setDataSourceKey("slave");
        List<MallCost> costList = mallCostService.findAll();
        MultiDataSource.toDefault();
        costList.forEach(cost -> System.out.println(cost.toString()));
    }

    /**
     * 注解切换数据源
     */
    @Test
    public void testDynamicDataSource() {
        List<Book> booklist = bookService.getList();
        booklist.forEach(book -> System.out.println(book.toString()));
        MallCost mallCost = new MallCost();
        mallCost.setId("11111");
        mallCost.setMoney("11111");
        mallCostService.saveone(mallCost);

    }

}
