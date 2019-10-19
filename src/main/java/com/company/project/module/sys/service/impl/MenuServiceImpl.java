package com.company.project.module.sys.service.impl;

import com.company.project.module.sys.dao.MenuMapper;
import com.company.project.module.sys.model.Menu;
import com.company.project.module.sys.service.MenuService;
import com.company.project.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 * Created by company.chen on 2019/10/19.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl extends AbstractService<Menu> implements MenuService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MenuMapper menuMapper;

}
