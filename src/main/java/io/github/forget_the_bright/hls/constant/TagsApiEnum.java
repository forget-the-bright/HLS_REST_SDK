package io.github.forget_the_bright.hls.constant;

import io.github.forget_the_bright.hls.constant.attach.ParamPosition;
import io.github.forget_the_bright.hls.entity.response.TagsResult;
import lombok.Getter;
import lombok.ToString;
import cn.hutool.http.Method;

/**
 * Tags 模块接口枚举 (Tags API Enum)
 * <p>
 * 该枚举类定义了 Tags 模块的所有接口及其相关信息
 */
@ToString
@Getter
public enum TagsApiEnum {

    /**
     * 查询标签列表
     * <p>通过给定的 nameMask 检索合格的标签名称列表</p>
     */
    QUERY_All_TAGS(
            "查询所有标签列表",
            "/ddb/read_alltagname",
            Method.GET,
            ParamPosition.NONE,
            ParamPosition.NONE,
            null,
            TagsResult.class);


    private final String desc;
    private final String path;
    private final Method method;
    private final ParamPosition primaryParamPosition;
    private final ParamPosition secondaryParamPosition;
    private final Class<?> entityType;
    private final Class<?> resultType; // 返回值实体类

    TagsApiEnum(String desc, String path, Method method,
                ParamPosition primaryParamPosition, ParamPosition secondaryParamPosition,
                Class<?> entityType,
                Class<?> resultType) {
        this.desc = desc;
        this.path = path;
        this.method = method;
        this.primaryParamPosition = primaryParamPosition;
        this.secondaryParamPosition = secondaryParamPosition;
        this.entityType = entityType;
        this.resultType = resultType;
    }
}
