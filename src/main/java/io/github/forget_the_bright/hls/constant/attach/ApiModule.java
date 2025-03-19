package io.github.forget_the_bright.hls.constant.attach;

// ApiModule.java
public enum ApiModule {
    DATA("data", "数据管理", "", AuthScheme.COMMON),
    TAGS("tags", "标签管理", "", AuthScheme.COMMON),
    OAUTH("oauth", "oAuth管理", "", AuthScheme.NONE);

    private final String code;
    private final String description;
    private final String contextPath;
    private final AuthScheme authType;

    ApiModule(String code, String description, String contextPath, AuthScheme authType) {
        this.code = code;
        this.description = description;
        this.contextPath = contextPath;
        this.authType = authType;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getContextPath() {
        return contextPath;
    }

    public AuthScheme getAuthType() {
        return authType;
    }
}
