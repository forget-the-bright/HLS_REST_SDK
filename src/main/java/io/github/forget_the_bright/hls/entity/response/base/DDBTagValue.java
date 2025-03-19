package io.github.forget_the_bright.hls.entity.response.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.forget_the_bright.hls.config.EnumDeserializer;
import io.github.forget_the_bright.hls.config.MilliSecondDateSerializer;
import io.github.forget_the_bright.hls.constant.common.Quality;
import io.github.forget_the_bright.hls.constant.common.TagType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 表示单个标签的数据值的对象。
 * 包含了标签的质量、大小、类型、值以及时间戳。
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/19 上午10:50
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class DDBTagValue {

    /**
     * 标签的质量。
     */
    @JSONField(name = "Quality",deserializeUsing = EnumDeserializer.class)
    private Quality quality;

    /**
     * 标签的大小。
     */
    @JSONField(name = "TagSize")
    private String tagSize;

    /**
     * 标签的类型。
     */
    @JSONField(name = "TagType",deserializeUsing = EnumDeserializer.class)
    private TagType tagType;

    /**
     * 标签的值。
     */
    @JSONField(name = "TagValue")
    private String tagValue;

    /**
     * 标签值的时间戳。
     * 时间格式为 "yyyy-MM-dd HH:mm:ss"，时区为 GMT+8。
     */
    @JSONField(name = "TagValueTime",deserializeUsing = MilliSecondDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date tagValueTime;
}
