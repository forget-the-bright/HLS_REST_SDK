package io.github.forget_the_bright.hls.entity.response.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 表示令牌数据的对象。
 * 包含了令牌信息。
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since  2025/3/19 上午11:22
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TokenData {

    /**
     * 令牌信息。
     */
    private String token;
}
