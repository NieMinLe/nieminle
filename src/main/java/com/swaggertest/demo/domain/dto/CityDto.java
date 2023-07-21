package com.swaggertest.demo.domain.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CityDto implements Serializable {

    /**
     * 省编码
     */
    private String provinceCode;

    /**
     * 市编码
     */
    private String cityCode;

    /**
     * 区域/县编码
     */
    private String areaCode;

    /**
     * 乡镇编码
     */
    private String townCode;

    public static CityDto from (String test, List<CityDto> cityDtosList){
        CityDto cityDto = new CityDto();
        String[] subString = test.split("-");
        System.out.println("输出subString的值"+JSONObject.toJSONString(subString));
        String provinceCode;
        String cityCode;
        String areaCode;
        String townCode;
        if(subString.length == 1){
            provinceCode = subString[0];
            cityDto = cityDtosList.stream().filter(v -> provinceCode.equals(v.getProvinceCode())
                    && "1".equals(v.getCityCode())).collect(Collectors.toList()).get(0);
        }else if(subString.length == 2){
            provinceCode = subString[0];
            cityCode = subString[1];
            cityDto = cityDtosList.stream().filter(v -> provinceCode.equals(v.getProvinceCode())
                    && cityCode.equals(v.getCityCode())
                    && "1".equals(v.getAreaCode())).collect(Collectors.toList()).get(0);
        }else if(subString.length == 3){
            provinceCode = subString[0];
            cityCode = subString[1];
            areaCode = subString[2];
            cityDto = cityDtosList.stream().filter(v -> provinceCode.equals(v.getProvinceCode())
                    && cityCode.equals(v.getCityCode())
                    && areaCode.equals(v.getAreaCode())
                    && "1".equals(v.getTownCode())).collect(Collectors.toList()).get(0);
        }else if(subString.length == 4){
            provinceCode = subString[0];
            cityCode = subString[1];
            areaCode = subString[2];
            townCode = subString[3];
            cityDto = cityDtosList.stream().filter(v -> provinceCode.equals(v.getProvinceCode())
                    && cityCode.equals(v.getCityCode())
                    && areaCode.equals(v.getAreaCode())
                    && townCode.equals(v.getTownCode())).collect(Collectors.toList()).get(0);
        }

        return cityDto;
    }

}
