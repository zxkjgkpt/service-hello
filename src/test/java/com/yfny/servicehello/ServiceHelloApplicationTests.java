package com.yfny.servicehello;

import com.yfny.servicecommon.generator.entity.RequestInfo;
import com.yfny.servicecommon.generator.invoker.TestInvoker;
import com.yfny.servicecommon.generator.invoker.base.Invoker;
import com.yfny.servicecommon.util.CodeInfoUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.MethodParameter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Modifier;
import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles
@SpringBootTest
public class ServiceHelloApplicationTests {

    @Test
    public void contextLoads() {
        List<RequestInfo> requestInfoList = CodeInfoUtils.getRequestInfos();
        for (RequestInfo requestInfo : requestInfoList) {
            System.out.println("****************************************************");
            System.out.println("url: " + requestInfo.getUrl());
            System.out.println("methodName: " + requestInfo.getMethodName());
            System.out.println("requestMethod: " + requestInfo.getRequestMethod());
            String requestMethod = requestInfo.getRequestMethod();
            for (MethodParameter parameter : requestInfo.getRequestParams()) {
                System.out.println("requestParams: " + parameter.getParameter().getName() + "  requestParamsType: " + parameter.getParameter().getType());
            }
            System.out.println("****************************************************");
        }
    }

    @Test
    public void initUnitTest() {
        Invoker invoker = new TestInvoker.Builder()
                .build();
        invoker.execute();
    }

}

