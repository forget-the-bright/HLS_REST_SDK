package io.github.forget_the_bright.hls.service;

import io.github.forget_the_bright.hls.constant.DataApiEnum;
import io.github.forget_the_bright.hls.constant.TagsApiEnum;
import io.github.forget_the_bright.hls.constant.attach.ApiModule;
import io.github.forget_the_bright.hls.core.ApiClient;
import io.github.forget_the_bright.hls.entity.request.HistorianRequest;
import io.github.forget_the_bright.hls.entity.request.TagNameListRequest;
import io.github.forget_the_bright.hls.entity.response.DataResult;
import io.github.forget_the_bright.hls.entity.response.DatasResult;
import io.github.forget_the_bright.hls.entity.response.TagsResult;

/**
 * 提供调用HLS API的工具类。
 * 包含了查询所有标签、获取DDB标签值和获取HDB标签值的方法。
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/19
 */
public class HLSApiInvoker {

    /**
     * 查询所有标签。
     *
     * @return 包含标签信息的 {@link TagsResult} 对象
     */
    public static TagsResult queryAllTags() {
        return ApiClient.execute(
                ApiModule.TAGS,
                TagsApiEnum.QUERY_All_TAGS
        );
    }

    /**
     * 获取DDB标签值。
     *
     * @param tagNameListRequest 包含标签名称列表的请求对象
     * @return 包含DDB标签值的 {@link DataResult} 对象
     */
    public static DataResult getDDBTagValue(TagNameListRequest tagNameListRequest) {
        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.Get_DDB_TAGVALUE_POST,
                tagNameListRequest
        );
    }

    /**
     * 获取HDB标签值。
     *
     * @param historianRequest 包含历史数据请求参数的请求对象
     * @return 包含HDB标签值的 {@link DatasResult} 对象
     */
    public static DatasResult getHDBTagValue(HistorianRequest historianRequest) {
        return ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.Get_HDB_TAGVALUE_POST,
                historianRequest
        );
    }
}
