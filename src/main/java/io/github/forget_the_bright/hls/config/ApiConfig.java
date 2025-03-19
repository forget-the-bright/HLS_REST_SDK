package io.github.forget_the_bright.hls.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * API 配置信息实体类，用于绑定配置文件中的数据中心相关配置参数
 *
 * <p>通过 @ConfigurationProperties 注解绑定以 hls.datacollection 为前缀的配置项，
 * 支持从 application.yml/properties 等配置文件注入属性值</p>
 *
 * @author wanghao
 * @version 1.0
 * @since 2025-03-03
 */
@Data
@ConfigurationProperties(prefix = "hls.datacollection")
public class ApiConfig {
    /**
     * API服务基础地址
     * <p>默认值指向测试环境地址 http://10.10.7.177:9000</p>
     */
    private String baseUrl = "http://10.10.7.177:9000";

    /**
     * 接口认证用户名
     * <p>默认值为系统预设测试账号 httpIO</p>
     */
    private String userid = "httpIO";

    /**
     * 接口认证密码
     * <p>默认密码为 Http#9527</p>
     */
    private String secretkey = "Http#9527";


    /**
     * 访问令牌有效期（单位：秒）
     * <p>默认1800秒（约半小时），控制认证令牌的有效时长</p>
     */
    private long tokenExpireSeconds = 1800L;

    /**
     * 读取超时时间（毫秒）
     * -1 表示使用默认值 代表不限制超时时间
     * 此字段用于设置在读取数据时等待的时间如果超过这个时间没有读取到数据，则认为读取超时
     */
    private int readTimeout = -1;

    /**
     * 连接超时时间（毫秒）
     * -1 表示使用默认值 代表不限制超时时间
     * 此字段用于设置在建立连接时等待的时间如果超过这个时间未能建立连接，则认为连接超时
     */
    private int connectionTimeout = -1;

    /**
     * 缓存模式
     * <p>默认值为 Local，表示使用本地缓存模式</p>
     * <p>其他值为 Redis，表示使用Redis缓存模式</p>
     */
    private String cacheModel = "Local";
}
