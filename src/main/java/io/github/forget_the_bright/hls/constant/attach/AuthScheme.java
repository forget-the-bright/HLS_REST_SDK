package io.github.forget_the_bright.hls.constant.attach;

/**
 * @Description TODO
 * @Author wanghao(helloworlwh @ 163.com)
 * @since 2025/3/3 下午2:52
 */

import lombok.ToString;

/**
 * HTTP 认证方案类型枚举
 * <p>包含主流认证协议头标识及说明，符合RFC 规范定义</p>
 */
@ToString
public enum AuthScheme {

    /**
     * 基本认证（RFC 7617）
     */
    BASIC("Authorization","Basic", "基础认证方案，使用Base64编码的用户名:密码"),

    /**
     * Bearer令牌认证（RFC 6750）
     */
    BEARER("Authorization","Bearer", "基于令牌的认证方案，常用于OAuth2/JWT"),

    /**
     * 摘要认证（RFC 7616，已弃用）
     */
    DIGEST("Authorization","Digest", "基于质询响应的摘要认证，替代Basic方案"),

    /**
     * HOBA认证（RFC 7486）
     */
    HOBA("Authorization","HOBA", "基于HTTP的Origin-Bound认证方案"),

    /**
     * 双向TLS认证（RFC 5246）
     */
    MUTUAL("Authorization","Mutual", "双向TLS客户端证书认证"),

    /**
     * AWS签名认证
     */
    AWS4_HMAC("Authorization","AWS4-HMAC-SHA256", "Amazon Web Services签名方案"),

    /**
     * NTLM认证（微软方案）
     */
    NTLM("Authorization","NTLM", "Windows NT LAN Manager认证"),

    /**
     * Negotiate认证（RFC 4178）
     */
    NEGOTIATE("Authorization","Negotiate", "SPNEGO协商认证协议"),

    /**
     * SCRAM认证（RFC 7804）
     */
    SCRAM("Authorization","SCRAM", "可插拔认证模块方案"),

    /**
     * VAPID认证（RFC 8292）
     */
    VAPID("Authorization","vapid", "自愿应用服务器识别协议"),
    /**
     * 通用认证类型
     */
    COMMON("token","", "通用"),
    /**
     * 未知认证类型
     */
    UNKNOWN("","", "未识别的认证方案"),
    /**
     * 无授权类型
     */
    NONE("","", "无授权类型");

    private final String type;
    private final String hearder;
    private final String description;

    AuthScheme(String hearder,String type, String description) {
        this.type = type;
        this.hearder = hearder;
        this.description = description;
    }

    /**
     * 根据请求头解析认证类型
     *
     * @param headerValue Authorization请求头值（如"Bearer xxxx"）
     * @return 对应的认证方案枚举
     */
    public static AuthScheme fromHeader(String headerValue) {
        if (headerValue != null) {
            String[] parts = headerValue.split(" ", 2);
            for (AuthScheme scheme : values()) {
                if (scheme.type.equalsIgnoreCase(parts[0])) {
                    return scheme;
                }
            }
        }
        return UNKNOWN;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
    public String getHearder() {
        return hearder;
    }
}

