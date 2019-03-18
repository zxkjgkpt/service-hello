package com.yfny.servicehello.service;

import com.yfny.servicecommon.redis.CacheEvict;
import com.yfny.servicecommon.redis.CacheKey;
import com.yfny.servicecommon.redis.Cacheable;
import com.yfny.servicecommon.redis.CommonCacheTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 示例Service实现
 * Created by jisongZhou on 2019/2/14.
 **/

@Service
public class ExampleHelloServiceImpl {

    @Value("${server.port}")
    String port;

    @Cacheable(expire = CommonCacheTime.ONE_DAY)
    public String hello(@CacheKey String name) {
        System.out.println("hello 没有执行缓存");
        return "service-hello -- hello " + name + " ,i am from port:" + port;
    }

    @Cacheable(expire = CommonCacheTime.ONE_DAY)
    public List<String> meet(@CacheKey String name) {
        List<String> list = new ArrayList<>();
        String[] peoples = {"张三", "李四", "王五"};
        for (String people : peoples) {
            String value = name + "和" + people + "碰面了。";
            list.add(value);
        }
        System.out.println("meet 没有执行缓存");
        return list;
    }

    @CacheEvict(listKeyPrefix = {"ExampleHelloServiceImpl_meet"},
            objectKeyPrefix = {"ExampleHelloServiceImpl_hello"},
            keyMode = CacheEvict.KeyMode.BASIC_UPDATE)
    public String goodbye(@CacheKey String name) {
        return "service-hello -- goodbye " + name + " ,i am from port:" + port;
    }
}
