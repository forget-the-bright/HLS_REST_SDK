
package io.github.forget_the_bright.hls.core;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;

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
}
