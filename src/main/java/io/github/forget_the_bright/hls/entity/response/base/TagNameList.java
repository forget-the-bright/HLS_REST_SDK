package io.github.forget_the_bright.hls.entity.response.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 表示标签名称列表的对象。
 * 包含了一个标签名称列表。
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/18 下午5:27
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TagNameList {

    /**
     * 标签名称列表。
     */
    @JSONField(name = "TagNameList")
    private List<TagName> tagNameList;
}
