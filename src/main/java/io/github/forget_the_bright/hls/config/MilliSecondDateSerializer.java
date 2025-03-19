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
 * @Description TODO
 * @Author wanghao(helloworlwh @ 163.com)
 * @Date 2025/3/19 下午3:03
 */
public class MilliSecondDateSerializer implements ObjectSerializer, ObjectDeserializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        Date date = (Date) object;
        //时间戳（秒）
        out.writeLong(date.getTime() / 1000);
    }

    @Override
    public Date deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        // 读取时间戳
        Long timestamp = parser.parseObject(Long.class);
        // 将秒级时间戳转换为毫秒级
        return new Date(timestamp * 1000);
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
