package io.github.forget_the_bright.hls.entity.response.base;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * This class represents the base result for API responses.
 * 该类表示API响应的基础结果，包含通用的错误码和错误信息。
 *
 * @author wanghao(helloworlwh@163.com)
 * @since 2025/03/05 17:01
 */
public class BaseResult {

    private int code;


    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
