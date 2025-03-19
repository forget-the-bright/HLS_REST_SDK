package io.github.forget_the_bright.hls.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.forget_the_bright.hls.entity.response.base.BaseResult;
import io.github.forget_the_bright.hls.entity.response.base.DDBTagValueList;
import io.github.forget_the_bright.hls.entity.response.base.HDBTagValueList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * This class represents the result of a data query response.
 * 该类表示数据查询响应的结果。
 *
 * @author wanghao(helloworlwh@163.com)
 * @since 2025/03/05 16:23
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class DatasResult extends BaseResult {

    /**
     * Data: A list of data items returned by the server.
     * 数据列表：服务器返回的数据项列表。
     */
    private HDBTagValueList data;
}
