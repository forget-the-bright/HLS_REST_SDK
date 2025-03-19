package io.github.forget_the_bright.hls.core;

import io.github.forget_the_bright.hls.config.ApiConfig;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * Redis 缓存持有者，使用 Spring Data Redis 的 RedisTemplate 实现。
 * <p>
 * 该类根据配置中的 token 过期时间设置缓存的过期时间，并提供基本的缓存操作方法。
 * 默认情况下，缓存会在 token 过期前 5 秒自动刷新。
 *
 * @param <K> 缓存键的类型
 * @param <V> 缓存值的类型
 * @author wanghao (helloworlwh @ 163.com)
 * @since 2025-03-10
 */
public class RedisCacheHolder<K, V> implements CacheHolder<K, V> {

    /**
     * Spring Data Redis 的 RedisTemplate 实例，用于与 Redis 进行交互。
     */
    private final RedisTemplate<K, V> redisTemplate;

    /**
     * 配置类，包含 token 过期时间等配置信息。
     */
    private final ApiConfig config;

    /**
     * 默认提前刷新 Token 的时间缓冲，单位为毫秒。
     */
    private static final long DEFAULT_EXPIRE_BUFFER = 5000; // 提前 5 秒刷新 Token

    /**
     * 构造函数，初始化 RedisTemplate 和配置类。
     *
     * @param redisTemplate Spring Data Redis 的 RedisTemplate 实例
     * @param config        配置类，包含 token 过期时间等配置信息
     */
    public RedisCacheHolder(RedisTemplate<K, V> redisTemplate, ApiConfig config) {
        this.redisTemplate = redisTemplate;
        this.config = config;
    }

    /**
     * 根据键获取缓存值。
     *
     * @param key 缓存键
     * @return 缓存值，如果不存在则返回 null
     */
    @Override
    public V get(K key) {
        ValueOperations<K, V> ops = redisTemplate.opsForValue();
        return ops.get(key);
    }

    /**
     * 根据键移除缓存值。
     *
     * @param key 缓存键
     */
    @Override
    public void remove(K key) {
        redisTemplate.delete(key);
    }

    /**
     * 将键值对存入缓存，并设置过期时间。
     *
     * @param key     缓存键
     * @param object  缓存值
     * @param timeout 过期时间，单位为毫秒
     */
    @Override
    public void put(K key, V object, long timeout) {
        ValueOperations<K, V> ops = redisTemplate.opsForValue();
        ops.set(key, object, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 将键值对存入缓存，使用默认的 token 过期时间减去提前刷新缓冲时间作为过期时间。
     *
     * @param key    缓存键
     * @param object 缓存值
     */
    @Override
    public void put(K key, V object) {
        ValueOperations<K, V> ops = redisTemplate.opsForValue();
        ops.set(key, object, TimeUnit.SECONDS.toMillis(config.getTokenExpireSeconds()) - DEFAULT_EXPIRE_BUFFER, TimeUnit.MILLISECONDS);
    }
}
