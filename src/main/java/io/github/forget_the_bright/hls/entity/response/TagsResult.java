package io.github.forget_the_bright.hls.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.forget_the_bright.hls.entity.response.base.BaseResult;
import io.github.forget_the_bright.hls.entity.response.base.TagNameList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * This class represents the result of a tags query response.
 * 该类表示标签查询响应的结果。
 *
 * @author wanghao(helloworlwh@163.com)
 * @since 2025/03/05 16:19
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TagsResult extends BaseResult {

    /**
     * Tags: A list of tags returned by the server.
     * 标签列表：服务器返回的标签列表。
     */
    @JSONField(name = "Data")
    private TagNameList data;
}
