package com.joygin.crm.workbench.service.impl;

import com.joygin.crm.settings.dao.UserDao;
import com.joygin.crm.settings.domain.User;
import com.joygin.crm.utils.SqlSessionUtil;
import com.joygin.crm.vo.PaginationVO;
import com.joygin.crm.workbench.dao.ActivityDao;
import com.joygin.crm.workbench.dao.ActivityRemarkDao;
import com.joygin.crm.workbench.domain.Activity;
import com.joygin.crm.workbench.domain.ActivityRemark;
import com.joygin.crm.workbench.service.ActivityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {

    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    private ActivityRemarkDao activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

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

    public boolean delete(String[] ids) {

        boolean flag = true;

        //查询需要删除的备注数量
        int count1 = activityRemarkDao.getCountByActivityIds(ids);

        //删除关联备注，返回删除数量
        int count2 = activityRemarkDao.deleteByActivityIds(ids);

        if(count1 != count2){
            flag = false;
        }

        //删除市场活动
        int count = activityDao.delete(ids);
        if(count != ids.length){
            flag = false;
        }

        return flag;
    }

    public Map<String, Object> getUserListAndActivity(String id) {

        //取uList
        List<User> uList = userDao.getUserList();

        //取Activity
        Activity a =activityDao.getById(id);

        //打包成activity并返回
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("uList",uList);
        map.put("a",a);
        return map;
    }

    public boolean update(Activity activity) {
        boolean flag = true;

        int count = activityDao.update(activity);
        System.out.println("count:"+count);
        if(count != 1){
            flag = false;
        }

        return flag;
    }

    public Activity detail(String id) {

        Activity activity = activityDao.detail(id);
        return activity;
    }

    public List<ActivityRemark> getRemarkListByAid(String activityId) {
        List<ActivityRemark> arList = activityRemarkDao.getRemarkListByAid(activityId);
        return arList;
    }

    public boolean deleteRemark(String id) {
        boolean flag = true;

        int count = activityRemarkDao.deleteById(id);
        if(count != 1){
            flag = false;
        }
        return flag;
    }

    public boolean saveRemark(ActivityRemark ar) {
        boolean flag = true;

        int count = activityRemarkDao.saveRemark(ar);

        if(count != 1){
            flag = false;
        }

        return flag;
    }

    public boolean updateRemark(ActivityRemark ar) {

        boolean flag = true;

        int count = activityRemarkDao.updateRemark(ar);

        if(count!=1){

            flag = false;

        }

        return flag;
    }
}
