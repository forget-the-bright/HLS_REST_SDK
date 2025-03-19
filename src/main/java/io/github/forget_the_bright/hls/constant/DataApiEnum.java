package io.github.forget_the_bright.hls.constant;

import io.github.forget_the_bright.hls.constant.attach.ParamPosition;

import io.github.forget_the_bright.hls.entity.request.HistorianRequest;
import io.github.forget_the_bright.hls.entity.request.TagNameListRequest;
import io.github.forget_the_bright.hls.entity.response.DataResult;
import io.github.forget_the_bright.hls.entity.response.DatasResult;
import lombok.Getter;
import lombok.ToString;
import cn.hutool.http.Method;

@ToString
@Getter
public enum DataApiEnum {

    Get_DDB_TAGVALUE_POST(
            "实时库读取标签实时值",
            "/ddb/read_ddbtagvalue",
            Method.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            TagNameListRequest.class,
            DataResult.class),


    Get_HDB_TAGVALUE_POST(
            "历史库读取标签历史值",
            "/hdb/read_hdbtagvalue",
            Method.POST,
            ParamPosition.BODY,
            ParamPosition.NONE,
            HistorianRequest.class,
            DatasResult.class);

    private final String desc;
    private final String path;
    private final Method method;
    private final ParamPosition primaryParamPosition;
    private final ParamPosition secondaryParamPosition;
    private final Class<?> entityType;
    private final Class<?> resultType; // 返回值实体类

    DataApiEnum(String desc, String path, Method method, ParamPosition primaryParamPosition, ParamPosition secondaryParamPosition, Class<?> entityType,
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