package io.github.forget_the_bright.hls.entity.response.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 表示标签数据值列表的对象。
 * 包含了一个标签数据值列表。
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since  2025/3/19 上午10:49
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class DDBTagValueList {

    /**
     * 标签数据值列表。
     */
    @JSONField(name = "DDBTagValueList")
    private List<DDBTagValue> ddbTagValueList;
}
