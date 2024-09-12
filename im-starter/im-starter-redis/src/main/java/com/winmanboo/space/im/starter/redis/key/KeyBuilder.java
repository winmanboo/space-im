package com.winmanboo.space.im.starter.redis.key;

/**
 * @author winmanboo
 * @date 2024/7/29 20:17
 */
public class KeyBuilder {

    private static final char LAST_CHAR = ':';
    private static final String SEPARATOR = ":";

    private final StringBuilder builder = new StringBuilder();

    public KeyBuilder appendKey(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }
        builder.append(key).append(SEPARATOR);
        return this;
    }

    String build() {
        if (builder.isEmpty()) {
            return "";
        }
        int lastIndex = builder.length() - 1;
        if (builder.charAt(lastIndex) != LAST_CHAR) {
            throw new IllegalArgumentException("append key must end with LAST_CHAR");
        }
        builder.deleteCharAt(lastIndex);
        return builder.toString();
    }
}
