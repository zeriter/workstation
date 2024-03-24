package com.workstation.modules.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workstation.modules.quartz.domain.entity.QuartzJob;
import com.workstation.modules.quartz.mapper.QuartzJobMapper;
import com.workstation.modules.quartz.service.ITaskService;
import org.springframework.stereotype.Service;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 任务业务处理类
 * @date 2024/3/2 23:08 星期六
 */
@Service
public class TaskServiceImpl extends ServiceImpl<QuartzJobMapper, QuartzJob> implements ITaskService {
}
