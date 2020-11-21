package com.swaggertest.demo.exception;

import com.swaggertest.demo.webApi.ApiResult;
import com.swaggertest.demo.webApi.ResultCode;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 控制层所有Advice类，捕获处理所有抛出的异常.
 * @author nieminle
 */

@RestControllerAdvice
@Slf4j
public class CustomException {

    @ResponseBody
    @ExceptionHandler(value = java.lang.NullPointerException.class)
    public Map<String,Object> nullException(Exception e, HttpServletRequest request) {
        System.setProperty("sun.net.client.defaultConnectTimeout",String.valueOf(10000));
        System.setProperty("sun.net.client.defaultReadTimeout",String.valueOf(10000));
        System.setProperty("sun.net.client.defaultConnectTimeout",String.valueOf(10000));
        System.setProperty("sun.net.client.defaultReadTimeout",String.valueOf(10000));

        Map<String,Object> map = new HashMap();
        map.put("code",ResultCode.SYSTEM_INNER_ERROR);
        map.put("msg","空指针异常");
        map.put("报空指针的地址",request.getRequestURI());
        map.put("具体的位置",e);
        return map;
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ApiResult indexOutException(Exception e, HttpServletRequest request) {
        return ApiResult.failure(ResultCode.SYSTEM_INNER_ERROR,"这个是下标越界哦",Boolean.FALSE);
    }

}
