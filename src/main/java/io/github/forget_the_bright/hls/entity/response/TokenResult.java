package io.github.forget_the_bright.hls.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.forget_the_bright.hls.entity.response.base.BaseResult;
import io.github.forget_the_bright.hls.entity.response.base.TokenData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 表示令牌结果的对象。
 * 继承自 {@link BaseResult}，包含令牌数据。
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/19 上午11:21
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TokenResult extends BaseResult {

    /**
     * 令牌数据。
     */
    @JSONField(name = "Data")
    private TokenData data;
}
