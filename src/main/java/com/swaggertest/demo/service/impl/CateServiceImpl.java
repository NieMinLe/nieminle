package com.swaggertest.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.swaggertest.demo.dao.CateDao;
import com.swaggertest.demo.domain.dto.CateDTO;
import com.swaggertest.demo.domain.dto.PmsCategoryDTO;
import com.swaggertest.demo.domain.po.CatePO;
import com.swaggertest.demo.service.CateService;
import com.swaggertest.demo.system.enums.EnumOfDimensionDrillDown;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CateServiceImpl implements CateService {

    @Resource
    private CateDao cateDao;

    public static Long catId = -1L;

    private static Random random = new Random();

    @Override
    public List<CateDTO> threeLevelPullDown() {
        List<CatePO> list = threeLevelPullDownToMapper(null, Lists.newArrayList(0L),EnumOfDimensionDrillDown.LEVEL1_ORG);
        List<CateDTO> dtos = reversal(list);

        return dtos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insetPullDown(PmsCategoryDTO cateDTO) {
        cateDao.insetPullDown(cateDTO);
    }

    @Override
    public List<PmsCategoryDTO> qryByName(PmsCategoryDTO name) {
        return cateDao.qryByName(name);
    }

    @SneakyThrows
    @Override
    public void qryAll(String name) {
        System.out.println(name + "开始");
        PmsCategoryDTO dto = new PmsCategoryDTO();
        dto.setName(name);
        List<PmsCategoryDTO> list = this.setBomByItemCode(dto);
        this.setBomByItem(list);

        System.out.println("合并前的数据"+ JSONObject.toJSONString(list));
        List<PmsCategoryDTO> test = this.hebing(list);
        System.out.println(name + "结束");
    }

    @Override
    public List<PmsCategoryDTO> qryDate(Integer day) {
        return cateDao.qryDate(day);
    }

    public List<PmsCategoryDTO> hebing(List<PmsCategoryDTO> list){
        //获取所有的子集合
        List<PmsCategoryDTO> childrenList = new ArrayList<>();
        list.forEach(item ->{
            childrenList.addAll(item.getChildren());
        });
        //将原来的数组通过名字去重
        List<PmsCategoryDTO> newList = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(PmsCategoryDTO ::getName))), ArrayList::new));
        //去重后的子集
        List<PmsCategoryDTO> childrenListQu = childrenList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(PmsCategoryDTO ::getName))), ArrayList::new));
        newList.forEach(item ->{
                if(item.getProductUnit() != null){
                    List<PmsCategoryDTO> realSon = new ArrayList<>();
                    childrenListQu.forEach(son ->{
                        if(son.getSubName().equals(item.getName())){
                            realSon.add(son);
                        }
                    });
                    item.setChildren(realSon);
                    this.hebing(childrenList);
                }
        });
        return newList;
    }

    public List<PmsCategoryDTO> setBomByItemCode(PmsCategoryDTO dto){
        List<PmsCategoryDTO> dcecBomTiers = cateDao.qryAll(dto.getName());
        if(dcecBomTiers.size() > 0){
            for (PmsCategoryDTO bomTier : dcecBomTiers) {
                //给第一层赋值
                bomTier.setTier(dto.getTier()+1);
                bomTier.setSubName(dto.getSubName());
                bomTier.setUpMoney(dto.getUpMoney());
                //递归查询下一层
                if(Objects.nonNull(bomTier.getProductUnit())) {
                    PmsCategoryDTO subDcecBomTier = new PmsCategoryDTO();
                    subDcecBomTier.setName(bomTier.getProductUnit());
                    subDcecBomTier.setTier(bomTier.getTier());
                    subDcecBomTier.setSubName(bomTier.getName());
                    subDcecBomTier.setUpMoney(bomTier.getMoney());
                    bomTier.setChildren(this.setBomByItemCode(subDcecBomTier));
                }
            }
        }

        return dcecBomTiers;
    }

    public void setBomByItem(List<PmsCategoryDTO> dcecBomTiers){
        dcecBomTiers.forEach(item ->{
            if(CollectionUtils.isEmpty(item.getChildren())){
                if(Objects.nonNull(item.getProductUnit())){
                    PmsCategoryDTO children = new PmsCategoryDTO();
                    children.setName(item.getProductUnit());
                    children.setTier(item.getTier()+1);
                    children.setChildren(Lists.newArrayList());
                    children.setSubName(item.getName());
                    children.setUpMoney(item.getMoney());
                    children.setCatId(catId --);
                    item.setChildren(Lists.newArrayList(children));
                }
            }else{
                setBomByItem(item.getChildren());
            }
        });
    }



    public List<CatePO> threeLevelPullDownToMapper(List<CatePO> list, List<Long> pidList, EnumOfDimensionDrillDown drillDown){
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
