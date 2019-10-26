package com.company.project.module.mall.service;
import com.company.project.module.mall.model.MallCost;
import com.company.project.core.Service;


/**
 * Created by company.chen on 2019/10/26.
 */
public interface MallCostService extends Service<MallCost> {

    void saveone(MallCost mallCost);
}
