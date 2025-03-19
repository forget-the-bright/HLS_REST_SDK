package io.github.forget_the_bright.hls.entity.request;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.annotation.JSONField;
import io.github.forget_the_bright.hls.config.MilliSecondDateSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用于请求历史数据的对象。
 *
 * <p>该类封装了查询历史数据所需的所有参数，包括时间范围、查询间隔以及需要查询的数据类型（最大值、最小值、平均值、边界值）。
 * 同时支持通过标签名称列表指定需要查询的具体数据项。</p>
 *
 * <p><strong>主要功能：</strong></p>
 * <ul>
 *     <li>设置查询的开始时间和结束时间。</li>
 *     <li>设置查询的时间间隔（单位为毫秒）。</li>
 *     <li>指定是否需要查询最大值、最小值、平均值或边界值。</li>
 *     <li>提供静态方法用于构建完整的 {@code HistorianRequest} 对象。</li>
 * </ul>
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/19 上午11:44
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class HistorianRequest {

    /**
     * 查询的开始时间。
     *
     * <p>此字段表示历史数据查询的时间范围的起始点。</p>
     */
    @JSONField(name = "startTime", serializeUsing = MilliSecondDateSerializer.class)
    private Date startTime;

    /**
     * 查询的结束时间。
     *
     * <p>此字段表示历史数据查询的时间范围的终止点。</p>
     */
    @JSONField(name = "endTime", serializeUsing = MilliSecondDateSerializer.class)
    private Date endTime;

    /**
     * 查询的时间间隔，单位为毫秒。
     *
     * <p>此字段定义了查询结果的时间粒度。</p>
     */
    private Long interval;

    /**
     * 是否需要查询最大值。
     *
     * <p>如果此字段为 {@code true}，则在查询结果中包含最大值。</p>
     */
    @JSONField(name = "NeedQueryMAX")
    private Boolean needQueryMAX;

    /**
     * 是否需要查询最小值。
     *
     * <p>如果此字段为 {@code true}，则在查询结果中包含最小值。</p>
     */
    @JSONField(name = "NeedQueryMIN")
    private Boolean needQueryMIN;

    /**
     * 是否需要查询平均值。
     *
     * <p>如果此字段为 {@code true}，则在查询结果中包含平均值。</p>
     */
    @JSONField(name = "NeedQueryAVG")
    private Boolean needQueryAVG;

    /**
     * 是否需要查询边界值。
     *
     * <p>如果此字段为 {@code true}，则在查询结果中包含边界值。</p>
     */
    @JSONField(name = "NeedQueryBound")
    private Boolean needQueryBound;

    /**
     * 需要查询的标签名称列表。
     *
     * <p>此字段是一个包含多个标签名称的对象列表，每个对象表示一个具体的查询目标。</p>
     */
    @JSONField(name = "TagNameList")
    private List<TagNameRequest> tagNameList;

    /**
     * 构建一个完整的 {@code HistorianRequest} 对象。
     *
     * <p>此方法接收查询的时间范围、间隔、数据类型需求以及标签名称集合，并返回一个配置完成的请求对象。</p>
     *
     * @param startTime        查询的开始时间。
     * @param endTime          查询的结束时间。
     * @param intervalBySecond 查询的时间间隔（单位为秒）。
     * @param needQueryAVG     是否需要查询平均值。
     * @param needQueryMIN     是否需要查询最小值。
     * @param needQueryMAX     是否需要查询最大值。
     * @param needQueryBound   是否需要查询边界值。
     * @param tagNames         需要查询的标签名称集合。
     * @return 配置完成的 {@code HistorianRequest} 对象。
     * @throws RuntimeException 如果标签名称集合为空或所有查询需求均为 {@code false}。
     */
    public static HistorianRequest buildRequest(Date startTime,
                                                Date endTime,
                                                Long intervalBySecond,
                                                Boolean needQueryAVG,
                                                Boolean needQueryMIN,
                                                Boolean needQueryMAX,
                                                Boolean needQueryBound,
                                                Collection<String> tagNames) {
        if (CollUtil.isEmpty(tagNames)) {
            throw new RuntimeException("tagNames不能为空");
        }
        needQueryBound = ObjectUtil.defaultIfNull(needQueryBound, false);
        needQueryAVG = ObjectUtil.defaultIfNull(needQueryAVG, false);
        needQueryMIN = ObjectUtil.defaultIfNull(needQueryMIN, false);
        needQueryMAX = ObjectUtil.defaultIfNull(needQueryMAX, false);

        if (!needQueryAVG && !needQueryMIN && !needQueryMAX && !needQueryBound) {
            throw new RuntimeException("needQueryBound,needQueryAVG,needQueryMIN,needQueryMAX不能同时为false");
        }
        List<TagNameRequest> tagNameList = tagNames.stream()
                .map(tagName -> new TagNameRequest().setTagName(tagName))
                .collect(Collectors.toList());
        HistorianRequest historianRequest = new HistorianRequest();
        historianRequest.setTagNameList(tagNameList);
        historianRequest.setStartTime(startTime);
        historianRequest.setEndTime(endTime);
        historianRequest.setInterval(intervalBySecond);
        historianRequest.setNeedQueryBound(needQueryBound);
        historianRequest.setNeedQueryAVG(needQueryAVG);
        historianRequest.setNeedQueryMAX(needQueryMIN);
        historianRequest.setNeedQueryMIN(needQueryMAX);
        return historianRequest;
    }

    /**
     * 构建一个完整的 {@code HistorianRequest} 对象。
     *
     * <p>此方法与另一个重载方法类似，但接收的是变长参数形式的标签名称列表。</p>
     *
     * @param startTime        查询的开始时间。
     * @param endTime          查询的结束时间。
     * @param intervalBySecond 查询的时间间隔（单位为秒）。
     * @param needQueryAVG     是否需要查询平均值。
     * @param needQueryMIN     是否需要查询最小值。
     * @param needQueryMAX     是否需要查询最大值。
     * @param needQueryBound   是否需要查询边界值。
     * @param tagNames         需要查询的标签名称数组。
     * @return 配置完成的 {@code HistorianRequest} 对象。
     * @throws RuntimeException 如果标签名称数组为空或所有查询需求均为 {@code false}。
     */
    public static HistorianRequest buildRequest(Date startTime,
                                                Date endTime,
                                                Long intervalBySecond,
                                                Boolean needQueryAVG,
                                                Boolean needQueryMIN,
                                                Boolean needQueryMAX,
                                                Boolean needQueryBound,
                                                String... tagNames) {
        return buildRequest(startTime, endTime, intervalBySecond, needQueryAVG, needQueryMIN, needQueryMAX, needQueryBound, Arrays.asList(tagNames));
    }
}

