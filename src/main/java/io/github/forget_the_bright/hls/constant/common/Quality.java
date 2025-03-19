package io.github.forget_the_bright.hls.constant.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 数据质量枚举类型，表示数据的质量状态。
 * Data quality enumeration type, representing the quality status of data.
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/5 下午2:59
 */
public enum Quality {
    /**
     * 质量差
     * Bad quality
     */
    BAD(0),

    /**
     * 质量好
     * Good quality
     */
    GOOD(1);

    /**
     * 枚举值
     */
    private final int value;

    /**
     * 枚举构造函数
     *
     * @param value 枚举值
     */
    Quality(int value) {
        this.value = value;
    }

    /**
     * 获取枚举值
     *
     * @return 枚举值
     */
    public int getValue() {
        return value;
    }

    /**
     * 根据值获取枚举实例
     *
     * @param value 枚举值
     * @return 枚举实例
     * @throws IllegalArgumentException 如果值不对应任何枚举实例
     */
    public static Quality fromValue(int value) {
        for (Quality quality : Quality.values()) {
            if (quality.getValue() == value) {
                return quality;
            }
        }
        throw new IllegalArgumentException("未知的质量值: " + value);
    }
    @JsonValue
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

