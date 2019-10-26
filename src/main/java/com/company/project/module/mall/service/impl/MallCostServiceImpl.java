package com.company.project.module.mall.service.impl;

import com.company.project.configurer.datasource.annotation.DataSource;
import com.company.project.module.mall.dao.MallCostMapper;
import com.company.project.module.mall.model.MallCost;
import com.company.project.module.mall.service.MallCostService;
import com.company.project.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by company.chen on 2019/10/26.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MallCostServiceImpl extends AbstractService<MallCost> implements MallCostService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MallCostMapper mallCostMapper;

    @Override
    @DataSource("slave")
    public void saveone(MallCost mallCost) {
        mallCostMapper.insert(mallCost);
        System.out.println("insert slave datasource success");
    }
}
