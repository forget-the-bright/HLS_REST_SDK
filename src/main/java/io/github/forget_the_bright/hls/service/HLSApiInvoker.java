package io.github.forget_the_bright.hls.service;

import cn.hutool.core.util.ObjectUtil;
import io.github.forget_the_bright.hls.constant.DataApiEnum;
import io.github.forget_the_bright.hls.constant.TagsApiEnum;
import io.github.forget_the_bright.hls.constant.attach.ApiModule;
import io.github.forget_the_bright.hls.core.ApiClient;
import io.github.forget_the_bright.hls.entity.request.HistorianRequest;
import io.github.forget_the_bright.hls.entity.request.TagNameListRequest;
import io.github.forget_the_bright.hls.entity.request.TagNameRequest;
import io.github.forget_the_bright.hls.entity.response.DataResult;
import io.github.forget_the_bright.hls.entity.response.DatasResult;
import io.github.forget_the_bright.hls.entity.response.TagsResult;
import io.github.forget_the_bright.hls.entity.response.base.DDBTagValue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 提供调用 HLS API 的工具类。
 *
 * <p>该类封装了与 HLS 系统交互的多个方法，包括查询所有标签、获取 DDB 标签值和获取 HDB 标签值。</p>
 *
 * <p><strong>主要功能：</strong></p>
 * <ul>
 *     <li>查询所有标签信息。</li>
 *     <li>获取 DDB 标签值。</li>
 *     <li>获取 HDB 标签值。</li>
 * </ul>
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/19
 */
public class HLSApiInvoker {

    /**
     * 查询所有标签。
     *
     * <p>此方法调用 HLS API 查询所有可用的标签信息，并返回包含标签信息的 {@link TagsResult} 对象。</p>
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
     * 获取 DDB 标签值。
     *
     * <p>此方法根据提供的 {@link TagNameListRequest} 请求对象，调用 HLS API 获取指定标签的 DDB 标签值，并返回包含这些值的 {@link DataResult} 对象。</p>
     *
     * @param tagNameListRequest 包含标签名称列表的请求对象
     * @return 包含 DDB 标签值的 {@link DataResult} 对象
     */
    public static DataResult getDDBTagValue(TagNameListRequest tagNameListRequest) {
        DataResult dataResult = ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.Get_DDB_TAGVALUE_POST,
                tagNameListRequest
        );
        List<TagNameRequest> tagNameList = tagNameListRequest.getTagNameList();
        List<DDBTagValue> ddbTagValueList = dataResult.getData().getDdbTagValueList();
        for (int i = 0; i < ddbTagValueList.size(); i++) {
            DDBTagValue ddbTagValue = ddbTagValueList.get(i);
            ddbTagValue.setTagName(tagNameList.get(i).getTagName());
        }
        return dataResult;
    }

    /**
     * 获取 HDB 标签值。
     *
     * <p>此方法根据提供的 {@link HistorianRequest} 请求对象，调用 HLS API 获取指定标签的历史数据（HDB 标签值），并返回包含这些值的 {@link DatasResult} 对象。</p>
     *
     * @param historianRequest 包含历史数据请求参数的请求对象
     * @return 包含 HDB 标签值的 {@link DatasResult} 对象
     */
    public static DatasResult getHDBTagValue(HistorianRequest historianRequest) {
        DatasResult datasResult = ApiClient.execute(
                ApiModule.DATA,
                DataApiEnum.Get_HDB_TAGVALUE_POST,
                historianRequest
        );
        List<TagNameRequest> tagNameList = historianRequest.getTagNameList();
        datasResult.getData().getHdbTagValueList().forEach(hdbTagValue -> {
            hdbTagValue.setTagName(tagNameList.get(hdbTagValue.getIndex()).getTagName());
        });
        return datasResult;
    }

    /**
     * 获取 HDB 标签值。
     *
     * <p>此方法接收查询的时间范围、间隔、数据类型需求以及标签名称数组，并构建一个 {@link HistorianRequest} 对象，然后调用 {@link #getHDBTagValue(HistorianRequest)} 方法获取 HDB 标签值。</p>
     *
     * @param startTime        查询的开始时间。
     * @param endTime          查询的结束时间。
     * @param needQueryBound   是否需要查询边界值。
     * @param needQueryAVG     是否需要查询平均值。
     * @param needQueryMIN     是否需要查询最小值。
     * @param needQueryMAX     是否需要查询最大值。
     * @param intervalBySecond 查询的时间间隔（单位为秒）。
     * @param tagNames         需要查询的标签名称数组。
     * @return 包含 HDB 标签值的 {@link DatasResult} 对象。
     */
    public static DatasResult getHDBTagValue(Date startTime,
                                             Date endTime,
                                             Boolean needQueryBound,
                                             Boolean needQueryAVG,
                                             Boolean needQueryMIN,
                                             Boolean needQueryMAX,
                                             Long intervalBySecond,
                                             String... tagNames) {
        HistorianRequest historianRequest = HistorianRequest.buildRequest(startTime, endTime, intervalBySecond, needQueryAVG, needQueryMIN, needQueryMAX, needQueryBound, tagNames);
        return getHDBTagValue(historianRequest);
    }

    /**
     * 获取 HDB 标签的边界值。
     *
     * <p>此方法是一个便捷方法，用于仅查询指定标签的边界值。它接收查询的时间范围、间隔以及标签名称数组，并调用 {@link #getHDBTagValue(Date, Date, Boolean, Boolean, Boolean, Boolean, Long, String...)} 方法获取边界值。</p>
     *
     * @param startTime        查询的开始时间。
     * @param endTime          查询的结束时间。
     * @param intervalBySecond 查询的时间间隔（单位为秒）。
     * @param tagNames         需要查询的标签名称数组。
     * @return 包含 HDB 标签边界值的 {@link DatasResult} 对象。
     */
    public static DatasResult getHDBTagValueBound(Date startTime, Date endTime, Long intervalBySecond, String... tagNames) {
        HistorianRequest historianRequest = HistorianRequest.buildRequest(startTime, endTime, intervalBySecond,
                false, false, false, true, tagNames);
        return getHDBTagValue(historianRequest);
    }

    /**
     * 根据指定的时间范围、间隔和标签名称获取历史数据中的边界值
     *
     * @param startTime        开始时间
     * @param endTime          结束时间
     * @param intervalBySecond 时间间隔（秒）
     * @param tagNames         标签名称集合
     * @return 返回包含边界值的DatasResult对象
     */
    public static DatasResult getHDBTagValueBound(Date startTime, Date endTime, Long intervalBySecond, Collection<String> tagNames) {
        HistorianRequest historianRequest = HistorianRequest.buildRequest(startTime, endTime, intervalBySecond,
                false, false, false, true, tagNames);
        return getHDBTagValue(historianRequest);
    }

    /**
     * 根据指定的时间范围、间隔和标签名称获取历史数据中的最大值
     *
     * @param startTime        开始时间
     * @param endTime          结束时间
     * @param intervalBySecond 时间间隔（秒）
     * @param tagNames         标签名称集合
     * @return 返回包含最大值的DatasResult对象
     */
    public static DatasResult getHDBTagValueMax(Date startTime, Date endTime, Long intervalBySecond, Collection<String> tagNames) {
        HistorianRequest historianRequest = HistorianRequest.buildRequest(startTime, endTime, intervalBySecond,
                false, false, true, false, tagNames);
        return getHDBTagValue(historianRequest);
    }

    /**
     * 根据指定的时间范围、间隔和标签名称获取历史数据中的最大值
     *
     * @param startTime        开始时间
     * @param endTime          结束时间
     * @param intervalBySecond 时间间隔（秒）
     * @param tagNames         标签名称数组
     * @return 返回包含最大值的DatasResult对象
     */
    public static DatasResult getHDBTagValueMax(Date startTime, Date endTime, Long intervalBySecond, String... tagNames) {
        HistorianRequest historianRequest = HistorianRequest.buildRequest(startTime, endTime, intervalBySecond,
                false, false, true, false, tagNames);
        return getHDBTagValue(historianRequest);
    }

    /**
     * 根据指定的时间范围、间隔和标签名称获取历史数据中的最小值
     *
     * @param startTime        开始时间
     * @param endTime          结束时间
     * @param intervalBySecond 时间间隔（秒）
     * @param tagNames         标签名称集合
     * @return 返回包含最小值的DatasResult对象
     */
    public static DatasResult getHDBTagValueMin(Date startTime, Date endTime, Long intervalBySecond, Collection<String> tagNames) {
        HistorianRequest historianRequest = HistorianRequest.buildRequest(startTime, endTime, intervalBySecond,
                false, true, false, false, tagNames);
        return getHDBTagValue(historianRequest);
    }

    /**
     * 根据指定的时间范围、间隔和标签名称获取历史数据中的最小值
     *
     * @param startTime        开始时间
     * @param endTime          结束时间
     * @param intervalBySecond 时间间隔（秒）
     * @param tagNames         标签名称数组
     * @return 返回包含最小值的DatasResult对象
     */
    public static DatasResult getHDBTagValueMin(Date startTime, Date endTime, Long intervalBySecond, String... tagNames) {
        HistorianRequest historianRequest = HistorianRequest.buildRequest(startTime, endTime, intervalBySecond,
                false, true, false, false, tagNames);
        return getHDBTagValue(historianRequest);
    }

    /**
     * 根据指定的时间范围、间隔和标签名称获取历史数据中的平均值
     *
     * @param startTime        开始时间
     * @param endTime          结束时间
     * @param intervalBySecond 时间间隔（秒）
     * @param tagNames         标签名称集合
     * @return 返回包含平均值的DatasResult对象
     */
    public static DatasResult getHDBTagValueAvg(Date startTime, Date endTime, Long intervalBySecond, Collection<String> tagNames) {
        HistorianRequest historianRequest = HistorianRequest.buildRequest(startTime, endTime, intervalBySecond,
                true, false, false, false, tagNames);
        return getHDBTagValue(historianRequest);
    }

    /**
     * 根据指定的时间范围、间隔和标签名称获取历史数据中的平均值
     *
     * @param startTime        开始时间
     * @param endTime          结束时间
     * @param intervalBySecond 时间间隔（秒）
     * @param tagNames         标签名称数组
     * @return 返回包含平均值的DatasResult对象
     */
    public static DatasResult getHDBTagValueAvg(Date startTime, Date endTime, Long intervalBySecond, String... tagNames) {
        HistorianRequest historianRequest = HistorianRequest.buildRequest(startTime, endTime, intervalBySecond,
                true, false, false, false, tagNames);
        return getHDBTagValue(historianRequest);
    }

    /**
     * 根据指定的时间范围、间隔和标签名称获取历史数据中的所有值
     *
     * @param startTime        开始时间
     * @param endTime          结束时间
     * @param intervalBySecond 时间间隔（秒）
     * @param tagNames         标签名称数组
     * @return 返回包含所有值的DatasResult对象
     */
    public static DatasResult getHDBTagValueAll(Date startTime, Date endTime, Long intervalBySecond, String... tagNames) {
        HistorianRequest historianRequest = HistorianRequest.buildRequest(startTime, endTime, intervalBySecond,
                true, true, true, true, tagNames);
        return getHDBTagValue(historianRequest);
    }

    /**
     * 根据指定的时间范围、间隔和标签名称获取历史数据中的所有值
     *
     * @param startTime        开始时间
     * @param endTime          结束时间
     * @param intervalBySecond 时间间隔（秒）
     * @param tagNames         标签名称集合
     * @return 返回包含所有值的DatasResult对象
     */
    public static DatasResult getHDBTagValueAll(Date startTime, Date endTime, Long intervalBySecond, Collection<String> tagNames) {
        HistorianRequest historianRequest = HistorianRequest.buildRequest(startTime, endTime, intervalBySecond,
                true, true, true, true, tagNames);
        return getHDBTagValue(historianRequest);
    }
}
