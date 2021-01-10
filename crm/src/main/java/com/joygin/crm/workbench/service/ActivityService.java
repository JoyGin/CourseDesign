package com.joygin.crm.workbench.service;

import com.joygin.crm.vo.PaginationVO;
import com.joygin.crm.workbench.domain.Activity;

import java.util.Map;

public interface ActivityService {
    boolean save(Activity activity);

    PaginationVO<Activity> pageList(Map<String, Object> map);
}
