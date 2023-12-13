package org.xtazxz.base.service.component;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBatisPlusBaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

  protected Logger logger = LoggerFactory.getLogger(this.getClass());

}
