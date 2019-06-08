package com.mantou.boot.utils;

import com.alibaba.fastjson.JSON;
import springfox.documentation.spring.web.json.Json;

/**
 * 基于fastjson的DTO转换工具类
 *
 * @author mantou
 */
public class DTOUtil {

    /**
     * 字段名相同的DTO对象转换
     * @param obj   需要转换的对象
     * @param newClazz  需要转换的Class
     * @param <T>   泛型, 返回接口的类型
     * @return T
     */
    public static <T> T DTOConvert(Object obj, Class<T> newClazz){

        String jsonString = JSON.toJSONString(obj);
        return JSON.parseObject(jsonString, newClazz);
    }
}
