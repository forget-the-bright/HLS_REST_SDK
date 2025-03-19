package io.github.forget_the_bright.hls.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import io.github.forget_the_bright.hls.annotation.EnumParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Method;
import java.util.NoSuchElementException;

/**
 * 解析带有 {@link EnumParam} 注解的方法参数的解析器。
 * 该解析器从请求中获取参数值，并将其转换为对应的枚举类型。
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/6 下午3:22
 */
@Slf4j
public class EnumParamArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 判断方法参数是否带有 {@link EnumParam} 注解。
     *
     * @param parameter 方法参数
     * @return 如果参数带有 {@link EnumParam} 注解，返回 true；否则返回 false
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(EnumParam.class);
    }

    /**
     * 解析带有 {@link EnumParam} 注解的方法参数。
     *
     * @param parameter     方法参数
     * @param mavContainer  ModelAndView 容器
     * @param webRequest    Web 请求
     * @param binderFactory Web 数据绑定工厂
     * @return 解析后的枚举对象
     * @throws Exception 如果解析过程中发生异常
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 获取 EnumParam 注解
        EnumParam annotation = parameter.getParameterAnnotation(EnumParam.class);
        // 获取参数名称
        String name = parameter.getParameterName();
        // 从请求中获取参数值
        String value = webRequest.getParameter(name);

        String convertMethod = annotation.value();

        // 获取参数的类信息
        Class<?> parameterType = parameter.getParameterType();
        if (!parameterType.isEnum()) {
            throw new IllegalArgumentException("EnumParam注解只能用于枚举类型");
        }
        if (StrUtil.isBlank(value)) {
            throw new IllegalArgumentException("EnumParam所注解的参数值不能为空");
        }

        Object[] enumConstants = parameterType.getEnumConstants();
        if (enumConstants == null || enumConstants.length == 0) {
            throw new NoSuchElementException("枚举类型无可用常量");
        }

        for (Object enumConstant : enumConstants) {
            Enum<?> anEnum = (Enum<?>) enumConstant;
            if (StrUtil.isNotEmpty(convertMethod)) {
                Method method = ReflectUtil.getMethod(parameterType, convertMethod);
                if (method != null) {
                    Object result = method.invoke(enumConstant);
                    if (result != null && ObjectUtil.equals(result + "", value)) {
                        return anEnum;
                    }
                }
            } else {
                if (String.valueOf(anEnum.ordinal()).equals(value)) {
                    return anEnum;
                }
            }
        }

        throw new NoSuchElementException("EnumParam所注解的参数值找不到对应的枚举");
    }
}
