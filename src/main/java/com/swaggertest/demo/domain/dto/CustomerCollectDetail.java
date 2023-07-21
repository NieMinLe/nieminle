package com.swaggertest.demo.domain.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author yujiamin
 * @since 2023/7/5
 */
@Data
public class CustomerCollectDetail {

    private String customerName;

    private String customerType;

    private List<String> storeImgs;

    private List<First> businessScopes;

    //反射获取对象所有信息
    public static Map<String, String> test(CustomerCollectDetail detail) {
        Class<?> aClass = detail.getClass();
        Field[] fields = aClass.getDeclaredFields();
        Map<String,String> hashMap = new LinkedHashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Object obj;
            try {
                obj = field.get(detail);
            }catch (Exception e){
                obj = null;
            }
            Class<?> type = field.getType();
            String value = "";
            if (type.equals(String.class) || type.equals(Integer.class) || type.equals(BigDecimal.class)) {
                value = obj == null ? null : obj.toString();
                hashMap.put(name,value);
            }else {
                value = obj == null ? null : JSON.toJSONString(obj);
                hashMap.put(name,value);
            }
        }
        return hashMap;
    }
//    Class<?> clazz = CustomerArchive.class;
//                    try{
//        //获取字段
//        Field nameField = clazz.getDeclaredField(k);
//        // 设置字段可访问
//        nameField.setAccessible(true);
//        String value = StringUtils.isNotBlank(v.getCorrectValue()) ? v.getCorrectValue() : v.getNewValue();
//        if(StringUtils.isNotBlank(value)){
//            nameField.set(newArchive,value);
//        }
//    }catch(Exception e){}

    //通过反射获取枚举类所有字段和内容,传枚举.class
    private static List<EnumDTO> getEnumListMethod(Class<?> enumClass){
    List<EnumDTO> list = new ArrayList<>();
    if (enumClass.isEnum()) {
        Object[] enumConstants = enumClass.getEnumConstants();
        for (Object enumConstant : enumConstants) {
            try {
                Field codeField = enumClass.getDeclaredField("code");
                Field msgField = enumClass.getDeclaredField("msg");
                codeField.setAccessible(true);
                msgField.setAccessible(true);
                Object code = codeField.get(enumConstant);
                Object msg = msgField.get(enumConstant);
                list.add(new EnumDTO(msg.toString(),code.toString()));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    return list;
}
}
