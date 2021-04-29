package com.swaggertest.demo.utils;

import cn.hutool.core.lang.Snowflake;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SnowFlakeUtil {
    private Snowflake snowflake;

    @PostConstruct
    public void init() {
        long workerId = 0L;
        try {
            String ipInStr = InetAddress.getLocalHost().getHostAddress();
            String[] ip = ipInStr.split("\\.");
            long a = Integer.parseInt(ip[0]);
            long b = Integer.parseInt(ip[1]);
            long c = Integer.parseInt(ip[2]);
            long d = Integer.parseInt(ip[3]);

            workerId = a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
        } catch (UnknownHostException e) {
            log.error("fail to get host address", e);
            System.exit(1);
        }
        // 因为目前项目比较简单，即使是多节点部署，也不区分数据中心
        long datacenterId = 1L;
        snowflake = new Snowflake(workerId % 32, datacenterId);
    }

    public long generateMessageId() {
        return snowflake.nextId();
    }

    public String generateComplaintTaskNo() {
        return "CT" + snowflake.nextId();
    }
}
