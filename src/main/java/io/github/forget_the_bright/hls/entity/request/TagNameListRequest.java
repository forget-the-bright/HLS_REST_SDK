package io.github.forget_the_bright.hls.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 表示标签名称列表请求的对象。
 * 包含了一个标签名称请求列表。
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/19 上午11:35
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TagNameListRequest {

    /**
     * 标签名称请求列表。
     */
    @JSONField(name = "TagNameList")
    private List<TagNameRequest> tagNameList;
}
