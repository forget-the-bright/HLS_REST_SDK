# GE REST SDK

## 项目介绍

### 概述
`GE_REST_SDK` 是一个用于与GE Proficy Historian工业时序数据库 进行交互的 RESTful API 软件开发工具包（SDK）。该 SDK 提供了一组封装好的 API 客户端和相关服务类，帮助开发者快速、高效地调用 GE 系统的各种功能接口。通过使用本 SDK，开发者可以轻松实现对数据收集器、报警系统、标签管理以及系统配置等模块的操作。

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
    <artifactId>GE_REST_SDK</artifactId>
    <version>1.0.3</version>
</dependency>
```
### springboot 配置
```yaml
# GE Proficy Historian工业时序数据库 配置信息
ge:
  datacollection:
    #API服务基础地址
    base-url: https://XXX.XX.XXX.X
    #客户端身份标识
    client-id: XXX_XX
    #客户端密钥
    client-secret: XXX_XX
    #接口认证用户名
    username: XXX_XX
    #接口认证密码
    password: XXX_XX
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
[GE_REST_SDK JavaDoc](https://javadoc.io/doc/io.github.forget-the-bright/GE_REST_SDK)

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
GE_REST_SDK\
├── src\
│   ├── main\
│   │   ├── java\
│   │   │   └── io\github\forget_the_bright\ge\
│   │   │       ├── constant\
│   │   │       │   ├── ApiModule.java
│   │   │       │   ├── CalculationMode.java
│   │   │       │   ├── CollectorsApiEnum.java
│   │   │       │   ├── DataApiEnum.java
│   │   │       │   ├── Direction.java
│   │   │       │   ├── FilterMode.java
│   │   │       │   ├── OAuthApiEnum.java
│   │   │       │   ├── Quality.java
│   │   │       │   ├── ReturnDataFields.java
│   │   │       │   ├── SamplingMode.java
│   │   │       │   ├── SystemsApiEnum.java
│   │   │       │   └── TagsApiEnum.java
│   │   │       ├── config\
│   │   │       │   └── ApiConfig.java
│   │   │       ├── core\
│   │   │       │   ├── ApiClient.java
│   │   │       │   └── ApiUtil.java
│   │   │       ├── entity\
│   │   │       │   ├── request\
│   │   │       │   │   ├── alarm\
│   │   │       │   │   │   ├── AlarmDeleteBackupEntity.java
│   │   │       │   │   │   ├── AlarmNotificationInfoEntity.java
│   │   │       │   │   │   └── AlarmQueryInfoEntity.java
│   │   │       │   │   ├── collectors\
│   │   │       │   │   │   └── CollectorEntity.java
│   │   │       │   │   ├── data\
│   │   │       │   │   │   ├── SampledEntity.java
│   │   │       │   │   │   ├── TagDataCreationEntity.java
│   │   │       │   │   │   └── TrendEntity.java
│   │   │       │   │   ├── systems\
│   │   │       │   │   │   ├── HistorianServerEntity.java
│   │   │       │   │   │   └── HorizontalScalabilityEntity.java
│   │   │       │   │   └── tags\
│   │   │       │   │       └── TagCommentEntity.java
│   │   │       │   └── response\
│   │   │       │       └── BaseResult.java
│   │   │       ├── exception\
│   │   │       │   └── ApiException.java
│   │   │       ├── service\
│   │   │       │   ├── AlarmApiInvoker.java
│   │   │       │   ├── CollectorsApiInvoker.java
│   │   │       │   ├── DataApiInvoker.java
│   │   │       │   ├── SystemsApiInvoker.java
│   │   │       │   └── TagsApiInvoker.java
│   │   │       └── utils\
│   │   │           └── HaoUtil.java
│   │   └── resources\
│   │       └── application.properties
│   └── test\
│       └── java\
│           └── io\github\forget_the_bright\ge\
│               └── test\
│                   └── ApiClientTest.java
├── README.md
└── pom.xml

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

### `AlarmApiInvoker.java`
- **功能**：报警服务 API 调用器，提供报警服务的相关操作接口。
- **方法**：
  - `createBackUp(AlarmDeleteBackupEntity entity)`：创建报警备份。
  - `createAlarms(AlarmNotificationInfoEntity entity)`：创建新报警。
  - `deleteAlarms(AlarmDeleteBackupEntity entity)`：删除指定报警。
  - `queryAlarmsByParam(AlarmQueryInfoEntity query)`：根据条件查询报警。
  - `restoreAlarmsBackUp(String path)`：从备份恢复报警。

### `CollectorsApiInvoker.java`
- **功能**：收集器服务 API 调用器，提供收集器服务的相关操作接口。
- **方法**：
  - `setAzureLogLevel(CollectorEntity body)`：设置 Azure 调试信息日志级别。
  - `bufferControl(CollectorEntity body)`：删除或移动缓冲区文件。
  - `createNewInstance(String body)`：创建收集器实例。
  - `deleteCollectorInstance(CollectorEntity body)`：删除收集器实例。
  - `deleteOfflineInstance(CollectorEntity body)`：删除离线收集器实例。
  - `collectorDetails()`：查看收集器详细信息。
  - `editInstance(String body)`：更新收集器实例。
  - `historianNodeChange(CollectorEntity body)`：更新收集器的服务器节点。
  - `instanceDetails(String interfaceName)`：获取收集器实例详细信息。
  - `messageCompression(CollectorEntity body)`：启用或禁用消息压缩。
  - `collectorRunningMode(String interfaceName)`：查看收集器运行模式。
  - `pauseCollection(String interfaceName)`：暂停收集器数据收集。
  - `rebuildCollectorsList(String interfaceName)`：重建收集器列表。
  - `restartCollector(CollectorEntity body)`：重新启动收集器。
  - `resumeCollection(String interfaceName)`：恢复收集器数据收集。
  - `setDebugMode(CollectorEntity body)`：设置收集器调试模式。
  - `startCollector(CollectorEntity body)`：启动收集器。
  - `collectorStatus(String interfaceName)`：获取收集器状态。
  - `stopCollector(CollectorEntity body)`：停止收集器。
  - `collectorVersion(String interfaceName)`：获取收集器的版本号。
  - `collectorVersionByPath(String interfaceName)`：获取收集器的版本号（路径参数）。
  - `collectorManagerList()`：获取收集器关联的代理机器列表。
  - `installComponentDetails(String collectorType, String collectorSubType, String machine)`：获取收集器所在计算机安装组件的详细信息。
  - `localOpcAeServers(String machine)`：获取指定机器上安装的 OPC 警报和事件服务器列表。
  - `localOpcHdaServers(String machine)`：查看指定机器上安装的 OPC HDA 服务器列表。
  - `localOpcServers(String machine)`：查看指定机器上安装的 OPC 服务器列表。
  - `offlineCollectors()`：获取离线收集器列表。

### `DataApiInvoker.java`
- **功能**：数据服务 API 调用器，提供数据服务的相关操作接口。
- **方法**：
  - `getCalculatedByRequestParam(String tagNames, Integer count, Date start, Date end, CalculationMode calculationMode, Long intervalMs)`：根据请求参数获取计算结果。
  - `getCalculatedByRequestParamPost(TagNamesEntity tagNamesEntity, Integer count, Date start, Date end, CalculationMode calculationMode, Long intervalMs)`：通过请求参数和请求体获取计算数据。
  - `getCalculatedByPathVariablePost(TagNamesEntity tagNamesEntity, Date start, Date end, CalculationMode calculationMode, Integer count, Long intervalMs)`：通过路径变量和请求体获取计算数据。
  - `getCalculatedByPathVariable(String tagNames, Date start, Date end, CalculationMode calculationMode, Integer count, Long intervalMs)`：通过路径变量获取计算数据。
  - `configureQueryResultByRequestParam(Integer maxDataQueryResultSize)`：根据请求参数配置查询结果。
  - `configureQueryResultByPathVariable(Integer maxDataQueryResultSize)`：根据路径变量配置查询结果。
  - `createTagData(TagDataCreationEntity tagDataCreationEntity)`：创建数据点位标签数据。
  - `getCurrentValueByRequestParam(String tagNames)`：根据请求参数查询当前值数据。
  - `getCurrentValuePost(TagNamesEntity tagNamesEntity)`：查询当前值数据。
  - `getCurrentValueByPathVariable(String tagNames)`：根据路径变量查询当前值数据。
  - `getInterpolatedByRequestParam(String tagNames, Date start, Date end, Integer count, Long intervalMs)`：根据请求参数查询标签列表的插值。
  - `getInterpolatedByRequestParamPost(TagNamesEntity tagNamesEntity, Date start, Date end, Integer count, Long intervalMs)`：查询标签列表的插值。
  - `getInterpolatedByPathVariablePost(TagNamesEntity tagNamesEntity, Date start, Date end, Integer count, Long intervalMs)`：查询标签列表的插值。
  - `getInterpolatedByPathVariable(String tagNames, Date start, Date end, Integer count, Long intervalMs)`：根据路径变量查询标签列表的插值。
  - `getRawDataByRequestParam(String tagNames, Date start, Date end, Direction direction, Integer count)`：根据请求参数查询原始数据。
  - `getRawDataByRequestParamPost(TagNamesEntity tagNamesEntity, Date start, Date end, Direction direction, Integer count)`：查询原始数据。
  - `getRawDataByPathVariablePost(TagNamesEntity tagNamesEntity, Date start, Date end, Direction direction, Integer count)`：查询原始数据。
  - `getRawDataByPathVariable(String tagNames, Date start, Date end, Direction direction, Integer count)`：根据路径变量查询原始数据。
  - `getSampledByRequestParam(CalculationMode calculationMode, Integer count, Direction direction, Date end, String filterExpression, FilterMode filterMode, Long intervalMs, Long queryModifier, ReturnDataFields returnDataFields, SamplingMode samplingMode, Date start, String tagNames)`：根据请求参数获取采样数据。
  - `getSampledByRequestParamPost(SampledEntity sampledEntity)`：查询采样数据。
  - `getTrendDataByRequestParam(CalculationMode calculationMode, Integer count, Direction direction, Date end, String filterExpression, FilterMode filterMode, Long intervalMs, Long queryModifier, SamplingMode samplingMode, Date start, String statisticsItemFilter, String tagNames)`：根据请求参数获取趋势数据。
  - `getTrendDataByRequestParamPost(TrendEntity trendEntity)`：查询趋势数据。


### `SystemsApiInvoker.java`
- **功能**：系统服务 API 调用器，提供系统服务的相关操作接口。
- **方法**：
  - `updateDataStore(String dataStoreName, String body)`：更新数据存储。
  - `deleteDataStore(HorizontalScalabilityEntity body)`：删除数据存储。
  - `getDataStores(String dataStoreMask)`：获取数据存储列表。
  - `addDataStore(HorizontalScalabilityEntity body)`：添加数据存储。
  - `getDhsMachines(String storageName)`：获取 DHS 机器列表。
  - `updateDhsService(String dHSServiceName, String body)`：更新 DHS 服务信息。
  - `getDhsServices(String dHSServiceMask, Boolean withReason)`：获取 DHS 服务列表。
  - `getServers(String loginuser)`：获取指定用户下的服务器信息。
  - `addHistorianServer(HistorianServerEntity body)`：添加服务器并归当前用户管理。
  - `deleteHistorianServer(HistorianServerEntity body)`：删除服务器。
  - `modifyHistorianServer(HistorianServerEntity body)`：修复服务器。
  - `getServerType()`：获取服务器类型信息。
  - `addMachine(HorizontalScalabilityEntity body)`：添加机器。
  - `deleteMachine(HorizontalScalabilityEntity body)`：删除机器。
  - `createMirrorGroup(HorizontalScalabilityEntity body)`：创建镜像组。
  - `renameMirrorGroup(HorizontalScalabilityEntity body)`：更新镜像组。
  - `deleteMirrorGroup(HorizontalScalabilityEntity body)`：删除镜像组。
  - `addMirrorMachine(HorizontalScalabilityEntity body)`：添加镜像机器。
  - `removeMirrorMachine(HorizontalScalabilityEntity body)`：删除镜像机器。
  - `getPerfTagData(String perfTagName, String perfTagType, String name, Date start, Date end, Long intervalMs)`：获取系统的读取速率和接收速率。
  - `getServerProperties()`：获取服务器属性列表。
  - `getDataStoresByStorage(String storageName)`：获取数据存储列表。
  - `updateDefaultDataStore(String storageName, String body)`：更新默认数据存储。
  - `getStorages(String storageMask)`：获取存储位置列表。
  - `getSystemStats()`：获取系统统计信息。

### `TagsApiInvoker.java`
- **功能**：标签服务 API 调用器，提供标签服务的相关操作接口。
- **方法**：
  - `queryTagsByParams(int maxNumber, String nameMask)`：根据指定参数查询标签列表。
  - `queryTagsByPath(int maxNumber, String nameMask)`：根据指定参数查询标签列表。
  - `addTagComment(TagCommentEntity tagCommentEntity)`：添加标签注释。
  - `addSingleTag(String tagName)`：添加单个标签。
  - `addBatchTags(String tagNames)`：批量添加标签。
  - `getCommentsByQuery(Date begin, Date end, String tagNames)`：根据查询条件获取标签注释。
  - `getCommentsByPath(Date begin, Date end, String tagNames)`：根据路径参数获取标签注释。
  - `getTagPropertiesPath(String tagName)`：根据路径参数获取标签属性。
  - `getTagPropertiesPathBody(String tagName, String body)`：根据路径参数和请求体获取标签属性。
  - `updateTagProperties(String tagName, String body)`：更新标签属性。
  - `renameTag(Boolean truerename, String oldTagName, String newTagName)`：重命名标签。
  - `deleteTag(Boolean permanentDelete, String tagName)`：删除指定标签。
  - `advancedSearchTags(String calctype, String collectiondisabled, String collectioninterval, String collectorcompression, String collectorname, String collectortype, String comment, String datastorename, String datatype, String description, String egudescription, String enumeratedset, String hasalias, String isstale, String lastmodified, String lastmodifieduser, String numberofelements, String pageno, String pagesize, String sourceaddress, String tagname, String userdefinedtypename)`：执行标签列表高级查询。

