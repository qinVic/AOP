package com.vic.aop.concurrent.test3;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * @author qinquan
 * @date 2019/11/9
 */
public class GuavaTest {

    public static void main(String[] args) {
        ConcurrentMap<Object, Object> objectObjectConcurrentMap = Maps.newConcurrentMap();
        String str = "zhang=san,li=si";
        Map<String, String> map = Splitter.on(",").withKeyValueSeparator("=").split(str);
        System.out.println(JSON.toJSONString(map));
    }

}
