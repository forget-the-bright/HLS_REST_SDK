package io.github.forget_the_bright.hls.entity.response.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 表示标签名称及其描述的对象。
 * 包含了标签的名称和描述信息。
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since  2025/3/18 下午5:28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TagName {

    /**
     * 标签的描述。
     */
    @JSONField(name = "TagDes")
    private String tagDes;

    /**
     * 标签的名称。
     */
    @JSONField(name = "TagName")
    private String tagName;
}
