package com.winmanboo.space.im.server.core;

import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.InetAddress;

/**
 * @author winmanboo
 * @date 2024/7/29 12:07
 */
@SpringBootTest(classes = TestApplication.class)
class TestApplication {

    @Test
    @SneakyThrows
    void testIp() {
        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }
    
    @Test
    void testIdGenerator() {
        short workerId = 0;
        IdGeneratorOptions options = new IdGeneratorOptions(workerId);
        YitIdHelper.setIdGenerator(options);
        System.out.println(YitIdHelper.nextId());
        System.out.println(YitIdHelper.nextId());
        System.out.println(YitIdHelper.nextId());
        System.out.println(YitIdHelper.nextId());
        System.out.println(YitIdHelper.nextId());
    }
}
