package io.sunny.common.validator.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
