package io.github.forget_the_bright.hls.constant.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 表示系统状态码的枚举类。
 * 每个枚举常量包含一个状态码和相应的描述信息。
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/19 下午2:33
 */
public enum StateCode {
    /**
     * 操作成功。
     */
    SUCCESS(0, "成功"),

    /**
     * 查询超时。
     */
    QUERY_TIMEOUT(4, "查询超时"),

    /**
     * 内存不足。
     */
    OUT_OF_MEMORY(5, "内存不足"),

    /**
     * JSON格式错误。
     */
    JSON_FORMAT_ERROR(3014, "json格式错误"),

    /**
     * 参数错误，某些字段不存在。
     */
    PARAMETER_ERROR(3015, "参数错误，某些字段不存在"),

    /**
     * 起始时间不能大于结束时间。
     */
    START_TIME_CAN_NOT_OVER_END_TIME(3016, "起始时间不能大于结束时间"),

    /**
     * 数据压缩失败。
     */
    DATA_COMPRESSION_FAILURE(3017, "数据压缩失败"),

    /**
     * 查询点数超过最大限制。
     */
    QUERY_TAG_NUMBER_OVER_MAX_LIMIT(3018, "查询点数超过最大限制"),

    /**
     * 查询时间段超过最大限制。
     */
    QUERY_TIME_RANGE_OVER_MAX_LIMIT(3019, "查询时间段超过最大限制"),

    /**
     * 查询点值数超过最大限制。
     */
    QUERY_ONE_TAG_VALUE_COUNT_OVER_MAX_LIMIT(3020, "查询点值数超过最大限制"),

    /**
     * 参数类型错误。
     */
    PARAMETER_TYPE_ERROR(3021, "参数类型错误"),

    /**
     * 参数值错误。
     */
    PARAMETER_VALUE_ERROR(3022, "参数值错误"),

    /**
     * 查询字段全为否。
     */
    NEED_QUERY_FIELDS_ARE_ALL_FALSE(3023, "查询字段全为否"),

    /**
     * 查询标签点列表为空。
     */
    QUERY_TAG_LIST_IS_EMPTY(3024, "查询标签点列表为空"),

    /**
     * 开始结束时间，晚于当前时间。
     */
    START_OR_END_TIME_IS_LATER_THAN_CURRENT_TIME(3025, "开始结束时间，晚于当前时间"),

    /**
     * 无效的URL。
     */
    NOT_FOUND(4001, "无效的URL"),

    /**
     * Head中Content type不是json。
     */
    CONTENT_TYPE_IS_NOT_JSON(4002, "Head中Content type不是json"),

    /**
     * 无效的token。
     */
    UNAUTHORIZED_TOKEN_IS_INVALID(4004, "无效的token"),

    /**
     * 返回的内容转json失败。
     */
    REPLY_CONTENT_ERROR_TO_JSON(4005, "返回的内容转json失败"),

    /**
     * 返回的内容解压失败。
     */
    LZO1X_DECOMPRESS_SAFE_FAILED(4006, "返回的内容解压失败"),

    /**
     * 协议组态未绑定历史库。
     */
    DO_NOT_BIND_HDB(4007, "协议组态未绑定历史库");

    private final int code;
    private final String description;

    /**
     * 构造函数，用于初始化状态码和描述。
     *
     * @param code        状态码
     * @param description 状态码描述
     */
    StateCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 获取状态码。
     *
     * @return 状态码
     */
    public int getCode() {
        return code;
    }

    /**
     * 获取状态码描述。
     *
     * @return 状态码描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 通过状态码查找对应的枚举常量。
     *
     * @param code 状态码
     * @return 对应的枚举常量
     * @throws IllegalArgumentException 如果状态码不存在
     */
    public static StateCode fromCode(int code) {
        for (StateCode errorCode : StateCode.values()) {
            if (errorCode.getCode() == code) {
                return errorCode;
            }
        }
        throw new IllegalArgumentException("未知的错误代码: " + code);
    }

    /**
     * 将状态码转换为字符串。
     *
     * @return 状态码的字符串表示
     */
    @JsonValue
    @Override
    public String toString() {
        return String.valueOf(code);
    }
}
