package io.github.forget_the_bright.hls.config;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * 自定义日期序列化器和反序列化器，用于将日期对象转换为秒级时间戳，并从秒级时间戳还原为日期对象。
 * <p>
 * 该类实现了 {@link ObjectSerializer} 和 {@link ObjectDeserializer} 接口，以便在 FastJSON 序列化和反序列化过程中使用。
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/19 下午3:03
 */
public class MilliSecondDateSerializer implements ObjectSerializer, ObjectDeserializer {

    /**
     * 将日期对象序列化为秒级时间戳。
     *
     * @param serializer JSON 序列化器
     * @param object     要序列化的对象，必须是 {@link Date} 类型
     * @param fieldName  字段名称
     * @param fieldType  字段类型
     * @param features   特性标志
     * @throws IOException 如果序列化过程中发生 I/O 错误
     */
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        Date date = (Date) object;
        // 时间戳（秒）
        out.writeLong(date.getTime() / 1000);
    }

    /**
     * 将秒级时间戳反序列化为日期对象。
     *
     * @param parser    JSON 解析器
     * @param type      目标类型，必须是 {@link Date} 类型
     * @param fieldName 字段名称
     * @return 反序列化后的日期对象
     */
    @Override
    public Date deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        // 读取时间戳
        Long timestamp = parser.parseObject(Long.class);
        // 将秒级时间戳转换为毫秒级
        return new Date(timestamp * 1000);
    }

    /**
     * 获取 FastJSON 的快速匹配令牌。
     *
     * @return 快速匹配令牌，当前实现返回 0
     */
    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
