package com.winmanboo.space.im.server.core.utils;

import lombok.experimental.UtilityClass;

/**
 * @author winmanboo
 * @date 2024/7/29 12:00
 */
@UtilityClass
public class Hooker {

    /**
     * 添加一个 JVM hook 函数进行优雅关闭
     *
     * @param r 执行逻辑
     */
    public static void gracefulShutdown(Runnable r) {
        // FIXME: 自定义线程工厂创建线程
        Runtime.getRuntime().addShutdownHook(new Thread(r));
    }
}
