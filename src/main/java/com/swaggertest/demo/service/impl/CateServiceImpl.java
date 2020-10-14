package com.swaggertest.demo.service.impl;

import com.google.common.collect.Lists;
import com.swaggertest.demo.dao.CateDao;
import com.swaggertest.demo.domain.dto.CateDTO;
import com.swaggertest.demo.domain.po.CatePO;
import com.swaggertest.demo.service.CateService;
import com.swaggertest.demo.system.enums.EnumOfDimensionDrillDown;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class CateServiceImpl implements CateService {

    @Resource
    private CateDao cateDao;

    @Override
    public List<CateDTO> threeLevelPullDown() {
        List<CatePO> list = threeLevelPullDownToMapper(null,Lists.newArrayList(0L),EnumOfDimensionDrillDown.LEVEL1_ORG);
        List<CateDTO> dtos = reversal(list);

        return dtos;
    }


    public List<CatePO> threeLevelPullDownToMapper(List<CatePO> list,List<Long> pidList, EnumOfDimensionDrillDown drillDown){
        if(CollectionUtils.isEmpty(list)){
            list = cateDao.threeLevelPullDown(pidList);
            list.forEach(v -> v.setDrillDown(drillDown));
            List<Long> pids = list.stream().map(CatePO::getSubjectId).collect(Collectors.toList());
            threeLevelPullDownToMapper(list,pids,next(drillDown));
        }else{

            List<CatePO> lister = cateDao.threeLevelPullDown(pidList);
            if(null == lister){
                return list;
            }
            lister.forEach(v -> v.setDrillDown(drillDown));

            Map<Long,List<CatePO>> map = lister.stream().collect(Collectors.groupingBy(CatePO::getPid));

            list.forEach(v ->{
                map.forEach((key,value) ->{
                    if(v.getSubjectId().equals(key)){
                        v.setLowerLevel(value);
                        value.forEach(v2 -> v2.setDrillDown(drillDown));
                        threeLevelPullDownToMapper(value,value.stream().map(CatePO::getSubjectId).collect(Collectors.toList()), next(drillDown));
                    }
                });
            });


        }

        return list;
    }

    public EnumOfDimensionDrillDown next(EnumOfDimensionDrillDown drillDown){
        EnumOfDimensionDrillDown lastDrillDown;

        switch (drillDown){
            case LEVEL1_ORG:
                lastDrillDown = EnumOfDimensionDrillDown.LEVEL2_ORG;
                break;
            case LEVEL2_ORG:
                lastDrillDown = EnumOfDimensionDrillDown.LEVEL3_ORG;
                break;
            default:
                lastDrillDown = EnumOfDimensionDrillDown.LEVEL1_ORG;
                break;
        }
        return lastDrillDown;
    }


    public List<CateDTO> reversal(List<CatePO> catePOS){
        List<CateDTO> cateDTOS = new ArrayList<>();

        CateDTO cateDTO ;
        for (CatePO catePO : catePOS) {
            cateDTO = new CateDTO();
            BeanUtils.copyProperties(catePO,cateDTO);
            cateDTOS.add(cateDTO);
        }

        return cateDTOS;
    }



}
