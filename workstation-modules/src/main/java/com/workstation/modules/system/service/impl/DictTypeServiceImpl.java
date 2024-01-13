package com.workstation.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workstation.modules.system.domain.entity.DictType;
import com.workstation.modules.system.mapper.DictTypeMapper;
import com.workstation.modules.system.service.IDictTypeService;
import org.springframework.stereotype.Service;

/**
 * @author ZERITER-ZHANG
 * @version 0.0.1
 * @description 字典详情业务处理类
 * @date 2024/1/11 23:11 星期四
 */
@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements IDictTypeService {
}
