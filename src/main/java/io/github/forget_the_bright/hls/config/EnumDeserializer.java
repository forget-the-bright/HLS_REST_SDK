package io.github.forget_the_bright.hls.config;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * TODO
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/19 下午6:13
 */
public class EnumDeserializer implements ObjectDeserializer {
    @Override
    public Object deserialze(DefaultJSONParser parser, Type type, Object o) {
        // 获取当前解析的JSON对象
        Object obj = parser.parseObject(Object.class);
        if (obj == null) {
            return null;
        }
        Class<?> typeClass = getTypeClass(type);
        if (!typeClass.isEnum()) {
            throw new IllegalArgumentException("EnumDeserializer Type is not an enum: " + type);
        }
        Object[] enumConstants = typeClass.getEnumConstants();
        for (Object enumConstant : enumConstants) {
            if (enumConstant.toString().equals(obj.toString())) {
                return enumConstant;
            }
        }
        return null;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }

    public static Class<?> getTypeClass(Type type) {
        if (type instanceof Class) {
            return (Class<?>) type;
        } else if (type instanceof ParameterizedType) {
            return (Class<?>) ((ParameterizedType) type).getRawType();
        } else if (type instanceof GenericArrayType) {
            // 对于数组类型，获取其组件类型的 Class，然后创建数组 Class
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            return Array.newInstance(getTypeClass(componentType), 0).getClass();
        } else {
            // 其他类型（如 TypeVariable 或 WildcardType）可能无法直接转换为 Class
            throw new IllegalArgumentException("Type cannot be converted to Class: " + type);
        }
    }

}
