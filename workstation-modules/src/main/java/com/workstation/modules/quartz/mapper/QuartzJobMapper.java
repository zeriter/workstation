package com.workstation.modules.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workstation.modules.quartz.domain.entity.QuartzJob;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description @TODO
 * @date 2024/2/29 10:31 星期四
 **/
@Mapper
public interface QuartzJobMapper extends BaseMapper<QuartzJob> {
    List<QuartzJob> findByIsPauseIsFalse();
}
