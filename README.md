# HLS_REST_SDK

## 项目介绍

### 概述
`HLS_REST_SDK` 是一个用于与和利时 工业时序数据库 进行交互的 RESTful API 软件开发工具包（SDK）。该 SDK 提供了一组封装好的 API 客户端和相关服务类，帮助开发者快速、高效地调用 GE 系统的各种功能接口。通过使用本 SDK，开发者可以轻松实现对数据收集器、报警系统、标签管理以及系统配置等模块的操作。

### 主要功能
1. **API 封装**：提供了一系列 API 客户端和枚举类，用于定义和调用 GE 系统的各个功能接口。
2. **实体类封装**：包含各个模块的实体类，用于封装 API 请求和响应的数据。
3. **Invoker 类**：提供了多个 Invoker 类，用于封装具体的 API 调用逻辑，简化了 API 的调用过程。
4. **常量类**：包含了 API 枚举类和模块枚举类，方便管理和使用各种 API 接口。
5. **配置类**：用于配置 API 客户端的相关参数，如认证信息、超时时间等。
6. **核心类**：包含核心类 `ApiClient`，用于执行 API 请求并处理响应。

### 项目引用
```pom
<dependency>
    <groupId>io.github.forget-the-bright</groupId>
    <artifactId>HLS_REST_SDK</artifactId>
    <version>1.0.0</version>
</dependency>
```
### springboot 配置
```yaml
# 和利时 工业时序数据库 配置信息
hls:
  datacollection:
    #API服务基础地址
    base-url: https://XXX.XX.XXX.X
    #客户端身份标识
    userid: XXX_XX
    #客户端密钥
    secretkey: XXX_XX
    #链接超时时间（毫秒） -1 代表不限制超时
    connection-timeout: -1
    #读取超时时间（毫秒） -1 代表不限制超时
    read-timeout: -1
    #访问令牌有效期（单位：秒）
    token-expire-seconds: 43199
    #缓存模式(Local,Redis) 默认Local,Redis 需要引入springboot-redis-stater
    cacheModel: Local
```
### Java Doc
[HLS_REST_SDK JavaDoc](https://javadoc.io/doc/io.github.forget-the-bright/HLS_REST_SDK)

## 项目模块及目录结构

### 项目模块
1. **Api 模块**
   - 包含 API 客户端和 API 枚举类，用于定义和调用 API 接口。
2. **Entity 模块**
   - 包含各个模块的实体类，用于封装 API 请求和响应的数据。
3. **Service 模块**
   - 包含各个模块的 Invoker 类，用于封装具体的 API 调用逻辑。
4. **Constant 模块**
   - 包含常量类，如 API 枚举类和模块枚举类。
5. **Config 模块**
   - 包含配置类，用于配置 API 客户端的相关参数。
6. **Core 模块**
   - 包含核心类，如 API 客户端 `ApiClient`。

### 目录结构
```cmd
HLS_REST_SDK
│  .gitignore
│  LICENSE
│  pom.xml
│  README.md
└─src
    └─main
        ├─java
        │  └─io
        │      └─github
        │          └─forget_the_bright
        │              │  Main.java
        │              │
        │              └─hls
        │                  ├─annotation
        │                  │      EnumParam.java
        │                  │
        │                  ├─config
        │                  │      ApiConfig.java
        │                  │      DataCollectionAutoConfiguration.java
        │                  │      EnumDeserializer.java
        │                  │      EnumParamArgumentResolver.java
        │                  │      MilliSecondDateSerializer.java
        │                  │      ResolverBeanPostProcessor.java
        │                  │
        │                  ├─constant
        │                  │  │  DataApiEnum.java
        │                  │  │  OAuthApiEnum.java
        │                  │  │  TagsApiEnum.java
        │                  │  │
        │                  │  ├─attach
        │                  │  │      ApiModule.java
        │                  │  │      AuthScheme.java
        │                  │  │      ParamPosition.java
        │                  │  │
        │                  │  └─common
        │                  │          Quality.java
        │                  │          StateCode.java
        │                  │          TagType.java
        │                  │
        │                  ├─core
        │                  │  │  ApiClient.java
        │                  │  │  ApiUtil.java
        │                  │  │  CacheHolder.java
        │                  │  │  LocalTimedCacheHolder.java
        │                  │  │  RedisCacheHolder.java
        │                  │  │  TokenHolder.java
        │                  │  │
        │                  │  └─print
        │                  │          PrintUtil.java
        │                  │
        │                  ├─entity
        │                  │  ├─request
        │                  │  │      HistorianRequest.java
        │                  │  │      TagNameListRequest.java
        │                  │  │      TagNameRequest.java
        │                  │  │
        │                  │  └─response
        │                  │      │  DataResult.java
        │                  │      │  DatasResult.java
        │                  │      │  TagsResult.java
        │                  │      │  TokenResult.java
        │                  │      │
        │                  │      └─base
        │                  │              BaseResult.java
        │                  │              BaseValue.java
        │                  │              DDBTagValue.java
        │                  │              DDBTagValueList.java
        │                  │              HDBTagValue.java
        │                  │              HDBTagValueList.java
        │                  │              OneTagHDBValue.java
        │                  │              TagName.java
        │                  │              TagNameList.java
        │                  │              TokenData.java
        │                  │
        │                  ├─exception
        │                  │      ApiException.java
        │                  │
        │                  └─service
        │                          HLSApiInvoker.java
        │
        └─resources
            └─META-INF
                    spring.factories


```

## 各个方法类的功能

### `ApiClient.java`
- **功能**：通用 API 客户端，用于执行 API 请求。
- **方法**：
  - `execute(ApiModule module, Enum<?> apiEnum, Object body)`：执行 API 请求，带请求体。
  - `execute(ApiModule module, Enum<?> apiEnum, Map<String, Object> params)`：执行 API 请求，带查询参数。
  - `execute(ApiModule module, Enum<?> apiEnum, Map<String, Object> params, Object body)`：执行 API 请求，带查询参数和请求体。
  - `buildUrl(ApiModule module, Enum<?> apiEnum)`：构建完整 URL。
  - `createRequest(Enum<?> apiEnum, String url, Map<String, Object> params, Object body)`：创建 HTTP 请求。
  - `fillMessage(HttpRequest request, ParamPosition paramPosition, String url, Map<String, Object> params, Object body)`：填充请求消息。
  - `replacePathParams(String url, Map<?, ?> params)`：替换 URL 中的路径参数。
  - `addAuthHeader(HttpRequest request, ApiModule module)`：添加认证头。
  - `handleResponse(HttpRequest request)`：处理 HTTP 响应。
  - `getPathFromEnum(Enum<?> apiEnum)`：从枚举中获取路径。
  - `getMethodFromEnum(Enum<?> apiEnum)`：从枚举中获取 HTTP 方法。
  - `getPrimaryParamPositionFromEnum(Enum<?> apiEnum)`：从枚举中获取主参数位置。
  - `getSecondaryParamPositionFromEnum(Enum<?> apiEnum)`：从枚举中获取次参数位置。
  - `getReturnType(Enum<?> apiEnum)`：从枚举中获取返回类型。

### `HLSApiInvoker.java`
- **功能**：提供调用 HLS API 的工具类。
- **方法**：
  - `queryAllTags()`：查询所有标签。
  - `getDDBTagValue(TagNameListRequest tagNameListRequest)`：获取 DDB 标签值。
  - `getHDBTagValue(HistorianRequest historianRequest)`：获取 HDB 标签值。
  - `getHDBTagValue(Date startTime, Date endTime, Boolean needQueryBound, Boolean needQueryAVG, Boolean needQueryMIN, Boolean needQueryMAX, Long intervalBySecond, String... tagNames)`：获取 HDB 标签值。
  - `getHDBTagValueBound(Date startTime, Date endTime, Long intervalBySecond, String... tagNames)`：获取 HDB 标签的边界值。
  - `getHDBTagValueBound(Date startTime, Date endTime, Long intervalBySecond, Collection<String> tagNames)`：获取 HDB 标签的边界值。
  - `getHDBTagValueMax(Date startTime, Date endTime, Long intervalBySecond, Collection<String> tagNames)`：获取 HDB 标签的最大值。
  - `getHDBTagValueMax(Date startTime, Date endTime, Long intervalBySecond, String... tagNames)`：获取 HDB 标签的最大值。
  - `getHDBTagValueMin(Date startTime, Date endTime, Long intervalBySecond, Collection<String> tagNames)`：获取 HDB 标签的最小值。
  - `getHDBTagValueMin(Date startTime, Date endTime, Long intervalBySecond, String... tagNames)`：获取 HDB 标签的最小值。
  - `getHDBTagValueAvg(Date startTime, Date endTime, Long intervalBySecond, Collection<String> tagNames)`：获取 HDB 标签的平均值。
  - `getHDBTagValueAvg(Date startTime, Date endTime, Long intervalBySecond, String... tagNames)`：获取 HDB 标签的平均值。
  - `getHDBTagValueAll(Date startTime, Date endTime, Long intervalBySecond, String... tagNames)`：获取 HDB 标签的所有值。
  - `getHDBTagValueAll(Date startTime, Date endTime, Long intervalBySecond, Collection<String> tagNames)`：获取 HDB 标签的所有值。

### `ApiUtil.java`
- **功能**：提供与 API 交互的实用工具方法。
- **方法**：
  - `isNullExec(T obj, Function<T, R> func)`：如果对象为 null 或空，则返回 null；否则执行给定的函数并返回其结果。
  - `isNullExec(T obj, Consumer<T> func)`：如果对象不为 null 或空，则执行给定的消费者函数。
  - `isNullExec(T obj, Supplier<R> func)`：如果对象不为 null 或空，则执行给定的提供者函数并返回其结果；否则返回 null。
  - `deConStructDatasResult(DatasResult datasResult, Function<OneTagHDBValue, String> getMethod)`：将 DatasResult 对象中的 HDBTagValue 列表解析为以标签名称为键、BaseValue 列表为值的映射。
  - `deConStructHdbTagValues(List<HDBTagValue> hdbTagValueList, Function<OneTagHDBValue, String> getMethod)`：将 HDBTagValue 列表解析为以标签名称为键、BaseValue 列表为值的映射。
  - `deConStructOneTagHdbTagValues(List<OneTagHDBValue> oneTagHDBValueList, Function<OneTagHDBValue, String> getMethod)`：将 OneTagHDBValue 列表解析为 BaseValue 列表。
  - `deConStructDdbTagValueList(List<DDBTagValue> ddbTagValueList)`：将 DDBTagValue 列表解析为以标签名称为键、值为值的映射。
