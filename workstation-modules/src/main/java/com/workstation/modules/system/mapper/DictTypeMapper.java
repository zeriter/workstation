package com.workstation.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workstation.modules.system.domain.entity.DictType;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 字典详情持久层
 * @date 2024/1/11 23:00 星期四
 */
@Mapper
public interface DictTypeMapper extends BaseMapper<DictType> {
}
