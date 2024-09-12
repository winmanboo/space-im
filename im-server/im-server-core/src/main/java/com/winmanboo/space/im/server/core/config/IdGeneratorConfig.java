package com.winmanboo.space.im.server.core.config;

import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 分布式 id 生成器配置
 *
 * @author winmanboo
 * @date 2024/8/12 14:44
 */
@Configuration
@EnableConfigurationProperties(IdGeneratorProperties.class)
public class IdGeneratorConfig implements InitializingBean {

    @Resource
    private IdGeneratorProperties idGeneratorProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 初始化 id 生成器配置
        initIdGenerator();
    }

    private void initIdGenerator() {
        if (idGeneratorProperties.getWorkerId() == null) {
            throw new IllegalArgumentException("[IdGeneratorConfig] workerId——机器 id 不能为空！");
        }
        IdGeneratorOptions options = new IdGeneratorOptions(idGeneratorProperties.getWorkerId());
        if (idGeneratorProperties.getMethod() != null) {
            options.Method = idGeneratorProperties.getMethod();
        }
        if (idGeneratorProperties.getBaseTime() != null) {
            options.BaseTime = idGeneratorProperties.getBaseTime();
        }
        if (idGeneratorProperties.getWorkerIdBitLength() != null) {
            options.WorkerIdBitLength = idGeneratorProperties.getWorkerIdBitLength();
        }
        if (idGeneratorProperties.getSeqBitLength() != null) {
            options.SeqBitLength = idGeneratorProperties.getSeqBitLength();
        }
        if (idGeneratorProperties.getMaxSeqNumber() != null) {
            options.MaxSeqNumber = idGeneratorProperties.getMaxSeqNumber();
        }
        if (idGeneratorProperties.getMinSeqNumber() != null) {
            options.MinSeqNumber = idGeneratorProperties.getMinSeqNumber();
        }
        if (idGeneratorProperties.getTopOverCostCount() != null) {
            options.TopOverCostCount = idGeneratorProperties.getTopOverCostCount();
        }
        YitIdHelper.setIdGenerator(options);
    }
}
