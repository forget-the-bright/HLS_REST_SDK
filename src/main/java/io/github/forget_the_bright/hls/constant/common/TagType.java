package io.github.forget_the_bright.hls.constant.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 标签类型枚举类，用于定义系统中支持的标签类型及其对应的代码和描述。
 * <p>
 * 每个标签类型都有一个唯一的代码值和描述信息，通过该类可以方便地进行标签类型的管理和转换。
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/19 下午2:41
 */
public enum TagType {

    /**
     * 标签名不存在的错误类型。
     */
    TAG_NAME_NOT_EXIST(-3, "标签名不存在"),

    /**
     * 布尔类型标签。
     */
    BOOL(0, "BOOL"),

    /**
     * 字节类型标签。
     */
    BYTE(1, "BYTE"),

    /**
     * 单字类型标签。
     */
    WORD(2, "WORD"),

    /**
     * 双字类型标签。
     */
    DWORD(3, "DWORD"),

    /**
     * 有符号字节类型标签。
     */
    SBYTE(4, "SBYTE"),

    /**
     * 有符号单字类型标签。
     */
    SWORD(5, "SWORD"),

    /**
     * 有符号双字类型标签。
     */
    SDWORD(6, "SDWORD"),

    /**
     * 单精度浮点数类型标签。
     */
    SINGLE(7, "SINGLE"),

    /**
     * 双精度浮点数类型标签。
     */
    DOUBLE(8, "DOUBLE"),

    /**
     * 字符串类型标签。
     */
    STRING(18, "STRING");

    /**
     * 标签类型的代码值。
     */
    private final int code;

    /**
     * 标签类型的描述信息。
     */
    private final String description;

    /**
     * 构造函数，初始化标签类型。
     *
     * @param code        标签类型的代码值
     * @param description 标签类型的描述信息
     */
    TagType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 获取标签类型的代码值。
     *
     * @return 标签类型的代码值
     */
    public int getCode() {
        return code;
    }

    /**
     * 获取标签类型的描述信息。
     *
     * @return 标签类型的描述信息
     */
    public String getDescription() {
        return description;
    }

    /**
     * 根据代码值查找对应的标签类型。
     *
     * @param code 标签类型的代码值
     * @return 对应的标签类型枚举值
     * @throws IllegalArgumentException 如果找不到匹配的标签类型，则抛出异常
     */
    public static TagType fromCode(int code) {
        for (TagType tagType : TagType.values()) {
            if (tagType.getCode() == code) {
                return tagType;
            }
        }
        throw new IllegalArgumentException("未知的标签类型代码: " + code);
    }

    /**
     * 覆盖 toString 方法，返回标签类型的代码值。
     * 主要用于 JSON 序列化时将枚举值转换为代码值。
     *
     * @return 标签类型的代码值
     */
    @JsonValue
    @Override
    public String toString() {
        return String.valueOf(code);
    }
}
