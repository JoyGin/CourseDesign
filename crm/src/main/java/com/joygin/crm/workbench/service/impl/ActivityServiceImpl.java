package com.joygin.crm.workbench.service.impl;

import com.joygin.crm.utils.SqlSessionUtil;
import com.joygin.crm.vo.PaginationVO;
import com.joygin.crm.workbench.dao.ActivityDao;
import com.joygin.crm.workbench.domain.Activity;
import com.joygin.crm.workbench.service.ActivityService;

import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {

    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

    public boolean save(Activity activity) {

        boolean flag = true;

        int count = activityDao.save(activity);
        System.out.println("count:"+count);
        if(count != 1){
            flag = false;
        }

        return flag;
    }

    public PaginationVO<Activity> pageList(Map<String, Object> map) {

        //获取总数total
        int total = activityDao.getTotalByCondition(map);

        //获取dataList
        List<Activity> dataList = activityDao.getActivityListByCondition(map);

        //封装到vo
        PaginationVO<Activity> vo = new PaginationVO<Activity>();
        vo.setTotal(total);
        vo.setDataList(dataList);

        //返回vo
        return vo;
    }
}
