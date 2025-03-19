package io.github.forget_the_bright.hls.constant.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @Description TODO
 * @Author wanghao(helloworlwh @ 163.com)
 * @Date 2025/3/19 下午2:41
 */
public enum TagType {
    TAG_NAME_NOT_EXIST(-3, "标签名不存在"),
    BOOL(0, "BOOL"),
    BYTE(1, "BYTE"),
    WORD(2, "WORD"),
    DWORD(3, "DWORD"),
    SBYTE(4, "SBYTE"),
    SWORD(5, "SWORD"),
    SDWORD(6, "SDWORD"),
    SINGLE(7, "SINGLE"),
    DOUBLE(8, "DOUBLE"),
    STRING(18, "STRING");

    private final int code;
    private final String description;

    TagType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // 静态方法，通过code值找到对应的枚举
    public static TagType fromCode(int code) {
        for (TagType tagType : TagType.values()) {
            if (tagType.getCode() == code) {
                return tagType;
            }
        }
        throw new IllegalArgumentException("未知的标签类型代码: " + code);
    }

    @JsonValue
    @Override
    public String toString() {
        return String.valueOf(code);
    }
}

