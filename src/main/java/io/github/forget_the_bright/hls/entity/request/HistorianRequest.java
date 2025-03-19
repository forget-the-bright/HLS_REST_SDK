package io.github.forget_the_bright.hls.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.forget_the_bright.hls.config.MilliSecondDateSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 用于请求历史数据的对象。
 * 包含了查询的时间范围、间隔以及需要查询的数据类型（最大值、最小值、平均值、边界值）和标签名称列表。
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/19 上午11:44
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class HistorianRequest {
    /**
     * 查询的开始时间。
     */
    @JSONField(name = "startTime", serializeUsing = MilliSecondDateSerializer.class)
    private Date startTime;

    /**
     * 查询的结束时间。
     */
    @JSONField(name = "endTime", serializeUsing = MilliSecondDateSerializer.class)
    private Date endTime;

    /**
     * 查询的时间间隔，单位为毫秒。
     */
    private Long interval;

    /**
     * 是否需要查询最大值。
     */
    @JSONField(name = "NeedQueryMAX")
    private Boolean needQueryMAX;

    /**
     * 是否需要查询最小值。
     */
    @JSONField(name = "NeedQueryMIN")
    private Boolean needQueryMIN;

    /**
     * 是否需要查询平均值。
     */
    @JSONField(name = "NeedQueryAVG")
    private Boolean needQueryAVG;

    /**
     * 是否需要查询边界值。
     */
    @JSONField(name = "NeedQueryBound")
    private Boolean needQueryBound;

    /**
     * 需要查询的标签名称列表。
     */
    @JSONField(name = "TagNameList")
    private List<TagNameRequest> tagNameList;
}
