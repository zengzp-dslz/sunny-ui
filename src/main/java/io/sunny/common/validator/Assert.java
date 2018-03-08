package io.sunny.common.validator;

import org.apache.commons.lang.StringUtils;

import io.sunny.common.exception.RRException;

/**
 * 数据校验
 * @author zdy
 * 
 * @date 2017年1月19日 上午10:10:10
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }
}
