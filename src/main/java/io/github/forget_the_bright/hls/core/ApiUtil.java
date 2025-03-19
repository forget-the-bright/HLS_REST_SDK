
package io.github.forget_the_bright.hls.core;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.Tuple;
import cn.hutool.core.lang.func.Func1;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.github.forget_the_bright.hls.entity.response.DatasResult;
import io.github.forget_the_bright.hls.entity.response.base.BaseValue;
import io.github.forget_the_bright.hls.entity.response.base.DDBTagValue;
import io.github.forget_the_bright.hls.entity.response.base.HDBTagValue;
import io.github.forget_the_bright.hls.entity.response.base.OneTagHDBValue;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * ApiUtil 类提供了与 API 交互的实用工具方法。
 * <p>
 * 这些方法主要用于简化 API 请求的构建和执行，提供了一些通用的功能，如参数验证、请求构建等。
 * </p>
 *
 * @author wanghao
 * @version 1.0
 * @see io.github.forget_the_bright.hls.core.ApiClient
 * @see io.github.forget_the_bright.hls.constant.DataApiEnum
 * @since 2023-10-01
 */
public class ApiUtil {

    /**
     * 如果对象为 null 或空，则返回 null；否则执行给定的函数并返回其结果。
     *
     * @param <T>  输入对象的类型
     * @param <R>  函数返回值的类型
     * @param obj  输入对象
     * @param func 处理对象的函数
     * @return 如果对象为 null 或空，则返回 null；否则返回函数的执行结果
     */
    public static <T, R> R isNullExec(T obj, Function<T, R> func) {
        if (ObjectUtil.isEmpty(obj)) {
            return null;
        } else {
            return func.apply(obj);
        }
    }

    /**
     * 如果对象不为 null 或空，则执行给定的消费者函数。
     *
     * @param <T>  输入对象的类型
     * @param obj  输入对象
     * @param func 处理对象的消费者函数
     */
    public static <T> void isNullExec(T obj, Consumer<T> func) {
        if (ObjectUtil.isNotEmpty(obj)) {
            func.accept(obj);
        }
    }

    /**
     * 如果对象不为 null 或空，则执行给定的提供者函数并返回其结果；否则返回 null。
     *
     * @param <T>  输入对象的类型
     * @param <R>  提供者函数返回值的类型
     * @param obj  输入对象
     * @param func 提供者函数
     * @return 如果对象不为 null 或空，则返回提供者函数的执行结果；否则返回 null
     */
    public static <T, R> R isNullExec(T obj, Supplier<R> func) {
        if (ObjectUtil.isNotEmpty(obj)) {
            return func.get();
        } else {
            return null;
        }
    }


    /**
     * 将 DatasResult 对象中的 HDBTagValue 列表解析为以标签名称为键、BaseValue 列表为值的映射。
     *
     * <p>
     * 该方法通过调用 {@link #deConStructHdbTagValues(List, Function)} 方法，
     * 将 DatasResult 中的 HDBTagValue 列表转换为 Map 结构。
     * </p>
     *
     * @param datasResult 包含数据结果的 DatasResult 对象
     * @param getMethod   用于从 OneTagHDBValue 中提取值的函数
     * @return 包含解析结果的 Map，键为标签名称，值为 BaseValue 列表
     */
    public static Map<String, List<BaseValue>> deConStructDatasResult(DatasResult datasResult, Function<OneTagHDBValue, String> getMethod) {
        return deConStructHdbTagValues(datasResult.getData().getHdbTagValueList(), getMethod);
    }

    /**
     * 将 HDBTagValue 列表解析为以标签名称为键、BaseValue 列表为值的映射。
     *
     * <p>
     * 该方法通过流式操作对 HDBTagValue 列表进行处理，使用 {@link HDBTagValue#getTagName()}
     * 获取标签名称作为键，并调用 {@link #deConStructOneTagHdbTagValues(List, Function)} 方法
     * 将每个 HDBTagValue 的 OneTagHDBValue 列表转换为 BaseValue 列表作为值。
     * </p>
     *
     * @param hdbTagValueList 包含多个 HDBTagValue 的列表
     * @param getMethod       用于从 OneTagHDBValue 中提取值的函数
     * @return 包含解析结果的 Map，键为标签名称，值为 BaseValue 列表
     */
    public static Map<String, List<BaseValue>> deConStructHdbTagValues(List<HDBTagValue> hdbTagValueList, Function<OneTagHDBValue, String> getMethod) {
        return hdbTagValueList.stream().collect(Collectors.toMap(HDBTagValue::getTagName, hdbTagValue ->
                deConStructOneTagHdbTagValues(
                        hdbTagValue.getOneTagHDBValueList(),
                        getMethod
                )));
    }

    /**
     * 将 OneTagHDBValue 列表解析为 BaseValue 列表。
     *
     * <p>
     * 该方法通过流式操作对 OneTagHDBValue 列表进行处理，将每个 OneTagHDBValue 转换为一个 BaseValue 对象，
     * 并设置其时间和值属性。值会通过提供的函数提取并进行修剪（去除首尾空白字符）。
     * </p>
     *
     * @param oneTagHDBValueList 包含多个 OneTagHDBValue 的列表
     * @param getMethod          用于从 OneTagHDBValue 中提取值的函数
     * @return 包含解析结果的 BaseValue 列表
     */
    public static List<BaseValue> deConStructOneTagHdbTagValues(List<OneTagHDBValue> oneTagHDBValueList, Function<OneTagHDBValue, String> getMethod) {
        return oneTagHDBValueList
                .stream()
                .map(oneTagHDBValue ->
                        new BaseValue()
                                .setTime(oneTagHDBValue.getTagValueTime())
                                .setValue(StrUtil.trim(getMethod.apply(oneTagHDBValue)))
                )
                .collect(Collectors.toList());
    }

    /**
     * 将 DDBTagValue 列表解析为以标签名称为键、值为值的映射。
     *
     * <p>
     * 该方法通过流式操作对 DDBTagValue 列表进行处理，使用 {@link DDBTagValue#getTagName()}
     * 获取标签名称作为键，并提取对应的值进行修剪（去除首尾空白字符）作为值。
     * </p>
     *
     * @param ddbTagValueList 包含多个 DDBTagValue 的列表
     * @return 包含解析结果的 Map，键为标签名称，值为对应的值
     */
    public Map<String, String> deConStructDdbTagValueList(List<DDBTagValue> ddbTagValueList) {
        return ddbTagValueList.stream().collect(Collectors.toMap(DDBTagValue::getTagName, ddbTagValue -> StrUtil.trim(ddbTagValue.getTagValue())));
    }

}
