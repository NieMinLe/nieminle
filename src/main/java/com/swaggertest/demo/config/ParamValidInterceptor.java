package com.swaggertest.demo.config;

import com.swaggertest.demo.interfacea.NieNotNull;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

@Component
public class ParamValidInterceptor implements HandlerInterceptor {

    @Resource
    LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取所有参数名称
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(handlerMethod.getMethod());
            // 获取所有请求参数
            Map<String, String[]> paramMap = paramMap(request);
            int paramIndex = 0;
            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
            if (methodParameters.length > 0
                    && (Objects.isNull(parameterNames) || parameterNames.length == 0)) {
                throw new RuntimeException("参数名为空");
            }
            for (MethodParameter methodParameter : methodParameters) {
                System.out.println(parameterNames[paramIndex]);
                NieNotNull notNull = methodParameter.getParameterAnnotation(NieNotNull.class);
                if (isBaseType(methodParameter.getParameterType())
                        && Objects.nonNull(notNull)
                        && Objects.isNull(paramMap.get(parameterNames[paramIndex]))) {
                    throw new RuntimeException("参数["+ parameterNames[paramIndex] +"]不能为空");
                }
                if (!isBaseType(methodParameter.getParameterType())) {
                    Class<?> parameterType = methodParameter.getParameterType();
                    Field[] fields = parameterType.getDeclaredFields();
                    for (Field field : fields) {
                        paramValidator(field, paramMap);
                    }
                }

                paramIndex ++;
            }
        }
        return true;
    }

    public void paramValidator(Field parameterField, Map<String, String[]> paramMap) {
        parameterField.setAccessible(true);
        Class<?> parameterType = parameterField.getType();
        if (isBaseType(parameterType)) {
            String fieldName = parameterField.getName();
            NieNotNull fieldNotNull = parameterField.getAnnotation(NieNotNull.class);
            if (Objects.nonNull(fieldNotNull) && Objects.isNull(paramMap.get(fieldName))) {
                throw new RuntimeException("参数["+ fieldName +"]不能为空");
            }
        } else {
            Field[] fields = parameterType.getDeclaredFields();
            for (Field field : fields) {
                paramValidator(field, paramMap);
            }
        }
    }

    public boolean isBaseType(Class<?> parameterType) {
        switch (parameterType.getName()) {
            case "java.lang.Integer":
            case "java.lang.Long":
            case "java.lang.Short":
            case "java.lang.Byte":
            case "java.lang.Boolean":
            case "java.lang.Character":
            case "java.lang.Float":
            case "java.lang.Double":
            case "java.lang.String":
            case "java.util.Date":

                return true;
            default: return false;
        }
    }

    public Map<String, String[]> paramMap(HttpServletRequest request) {
        return request.getParameterMap();
    }

}
