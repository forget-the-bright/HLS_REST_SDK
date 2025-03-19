package io.github.forget_the_bright.hls.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 表示标签名称请求的对象。
 * 包含了标签名称信息。
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/19 上午11:36
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TagNameRequest {

    /**
     * 标签名称。
     */
    @JSONField(name = "TagName")
    private String tagName;
}
