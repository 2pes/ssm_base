package com.company.project.module.mall.service.impl;

import com.company.project.module.mall.dao.MallItemsMapper;
import com.company.project.module.mall.model.MallItems;
import com.company.project.module.mall.service.MallItemsService;
import com.company.project.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 * Created by company.chen on 2019/10/21.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MallItemsServiceImpl extends AbstractService<MallItems> implements MallItemsService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MallItemsMapper mallItemsMapper;

}
