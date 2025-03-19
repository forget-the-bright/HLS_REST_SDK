package io.github.forget_the_bright.hls.entity.response.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.forget_the_bright.hls.config.EnumDeserializer;
import io.github.forget_the_bright.hls.config.MilliSecondDateSerializer;
import io.github.forget_the_bright.hls.constant.common.Quality;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 表示单个标签的历史数据值的对象。
 * 包含了标签的质量信息、时间戳以及不同类型的标签值（平均值、边界值、最大值、最小值）。
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/19 上午10:54
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class OneTagHDBValue {

    /**
     * 标签的边界质量。
     */
    @JSONField(name = "Quality_Bound", deserializeUsing = EnumDeserializer.class)
    private Quality qualityBound;

    /**
     * 标签的最大值质量。
     */
    @JSONField(name = "Quality_MAX", deserializeUsing = EnumDeserializer.class)
    private Quality qualityMAX;

    /**
     * 标签的最小值质量。
     */
    @JSONField(name = "Quality_MIN", deserializeUsing = EnumDeserializer.class)
    private Quality qualityMIN;

    /**
     * 标签值的时间戳。
     */
    @JSONField(name = "TagValueTime", deserializeUsing = MilliSecondDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date tagValueTime;

    /**
     * 标签的平均值。
     */
    @JSONField(name = "TagValue_AVG")
    private String tagValueAVG;

    /**
     * 标签的边界值。
     */
    @JSONField(name = "TagValue_Bound")
    private String tagValueBound;

    /**
     * 标签的最大值。
     */
    @JSONField(name = "TagValue_MAX")
    private String tagValueMAX;

    /**
     * 标签的最小值。
     */
    @JSONField(name = "TagValue_MIN")
    private String tagValueMIN;
}
