package io.github.forget_the_bright.hls.entity.response.base;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.forget_the_bright.hls.config.EnumDeserializer;
import io.github.forget_the_bright.hls.constant.common.TagType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * TODO
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/19 下午6:01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class HDBTagValue {
    /**
     * 索引。
     */
    @JSONField(name = "Index")
    private Integer index;

    /**
     * 标签类型。
     */
    @JSONField(name = "TagType",deserializeUsing = EnumDeserializer.class)
    private TagType tagType;

    /**
     * 单个标签的历史数据值列表。
     */
    @JSONField(name = "OneTagHDBValueList")
    private List<OneTagHDBValue> oneTagHDBValueList;
}
