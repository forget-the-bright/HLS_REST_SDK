package io.github.forget_the_bright.hls.entity.response.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 表示历史数据库标签数据值列表的对象。
 * 包含了索引、标签类型以及单个标签的历史数据值列表。
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since  2025/3/19 上午10:53
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class HDBTagValueList {

    @JSONField(name = "HDBTagValueList")
    List<HDBTagValue> hdbTagValueList;
}
