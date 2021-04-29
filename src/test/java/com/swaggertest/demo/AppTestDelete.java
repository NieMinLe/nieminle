package com.swaggertest.demo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.fastjson.JSONObject;
import com.cheetah.extend.result.ListResultSet;
import com.cheetah.extend.result.ResultSet;
import com.tz.edu.vip.power.stub.ListenAccessFacadeAPI;
import com.tz.edu.vip.power.stub.dto.CourseAndStatusDTO;
import com.tz.edu.vip.power.stub.dto.ListenCourseAccessDTO;
import java.util.Arrays;

/**
 * 测试 vip 新加的dubbo 接口
 */
public class AppTestDelete {

    public static void main(String[] args) throws Exception{
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("test-consumer");
        application.setOwner("ruifeng");

        // 连接注册中心配置 zookeeper://test-zk1.tzict.cn:2181?backup=test-zk2.tzict.cn:2181,test-zk3.tzict.cn:2181
        RegistryConfig registry1 = new RegistryConfig();
        registry1.setAddress("zookeeper://test-zk1.tzict.cn:2181");
        RegistryConfig registry2= new RegistryConfig();
        registry2.setAddress("zookeeper://test-zk2.tzict.cn:2181");
        RegistryConfig registry3 = new RegistryConfig();
        registry3.setAddress("zookeeper://test-zk3.tzict.cn:2181");

        // 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接
        // 引用远程服务
        ReferenceConfig<ListenAccessFacadeAPI> reference = new ReferenceConfig<ListenAccessFacadeAPI>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        reference.setApplication(application);
        reference.setRegistries(Arrays.asList(registry1,registry2,registry3));
        //reference.setRegistry(registry1);
        reference.setInterface(ListenAccessFacadeAPI.class);
        reference.setVersion("1.0.0");

        // 和本地bean一样使用xxxService
        ListenAccessFacadeAPI permissionFacadeAPI = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        // ListResultSet<ListenCourseAccessDTO> resultSet = permissionFacadeAPI.getCourseIdAndStatusByUserId(19367114L,10520L);
        // System.out.println("关键字=-=-=-=-=-="+ JSONObject.toJSONString(resultSet));
        //        ListResultSet<CourseApplyDTO> resultSet = permissionFacadeAPI.qryCourseApplyPoByCourseIds(Arrays.asList(100L));
        //        System.out.println("qryCourseApplyPoByCourseIds ============== " + JSON.toJSONString(resultSet));

        //        BatchUserAccessParam param = new BatchUserAccessParam();
        //        param.setCourseId(192L);
        //        param.setUserIds(Arrays.asList(789661L,789662L));
        //        ResultSet<Integer> resultSet = permissionFacadeAPI.selectStudentHasClassAccess(param);
        //        System.out.println("selectStudentHasClassAccess ============== " + JSON.toJSONString(resultSet));
    }


}