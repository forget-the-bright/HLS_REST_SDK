package io.github.forget_the_bright;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.github.forget_the_bright.hls.config.ApiConfig;
import io.github.forget_the_bright.hls.core.ApiClient;
import io.github.forget_the_bright.hls.core.LocalTimedCacheHolder;
import io.github.forget_the_bright.hls.core.TokenHolder;
import io.github.forget_the_bright.hls.core.print.PrintUtil;
import io.github.forget_the_bright.hls.entity.request.HistorianRequest;
import io.github.forget_the_bright.hls.entity.request.TagNameListRequest;
import io.github.forget_the_bright.hls.entity.request.TagNameRequest;
import io.github.forget_the_bright.hls.entity.response.DataResult;
import io.github.forget_the_bright.hls.entity.response.DatasResult;
import io.github.forget_the_bright.hls.entity.response.TagsResult;
import io.github.forget_the_bright.hls.service.HLSApiInvoker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {
        //TIP 当文本光标位于高亮显示的文本处时按 <shortcut actionId="ShowIntentionActions"/>
        // 查看 IntelliJ IDEA 建议如何修正。
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP 按 <shortcut actionId="Debug"/> 开始调试代码。我们已经设置了一个 <icon src="AllIcons.Debugger.Db_set_breakpoint"/> 断点
            // 但您始终可以通过按 <shortcut actionId="ToggleLineBreakpoint"/> 添加更多断点。
            System.out.println("i = " + i);
            PrintUtil.BLUE.Println("i = " + i);
        }
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setUserid("httpIO");
        apiConfig.setSecretkey("Http#9527");
        new ApiClient(apiConfig);
        TokenHolder tokenHolder = new TokenHolder(new LocalTimedCacheHolder(apiConfig), apiConfig);
        //tokenHolder.setToken("eyJhbGciOiJSUzI1NiIsImprdSI6Imh0dHBzOi8vSlhUWS0xNzYtOS91YWEvdG9rZW5fa2V5cyIsImtpZCI6ImtleS1pZC0xIiwidHlwIjoiSldUIn0.eyJqdGkiOiJiZTBmOThlNjQ5Nzc0MjllODVjMWYzYTlhZjM2MzBhOSIsInN1YiI6ImVmNzMyNDM1LWNmNzAtNGZiOS04NDEzLTk3YTY0YTg1MWVhMiIsInNjb3BlIjpbImloX2FyY2hpdmVfYWRtaW5zIiwiaWhfdW5hdWRpdGVkX3dyaXRlcnMiLCJpaF91bmF1ZGl0ZWRfbG9naW5zIiwiaWhfYXVkaXRlZF93cml0ZXJzIiwiaGlzdG9yaWFuX3Jlc3RfYXBpLnJlYWQiLCJpaF9yZWFkZXJzIiwiaWhfdGFnX2FkbWlucyIsImhpc3Rvcmlhbl9yZXN0X2FwaS53cml0ZSIsImhpc3Rvcmlhbl9lbnRlcnByaXNlLnVzZXIiLCJoaXN0b3JpYW5fcmVzdF9hcGkuYWRtaW4iLCJpaF9jb2xsZWN0b3JfYWRtaW5zIiwiaGlzdG9yaWFuX2VudGVycHJpc2UuYWRtaW4iLCJpaF9zZWN1cml0eV9hZG1pbnMiXSwiY2xpZW50X2lkIjoiaGlzdG9yaWFuX3B1YmxpY19yZXN0X2FwaSIsImNpZCI6Imhpc3Rvcmlhbl9wdWJsaWNfcmVzdF9hcGkiLCJhenAiOiJoaXN0b3JpYW5fcHVibGljX3Jlc3RfYXBpIiwicmV2b2NhYmxlIjp0cnVlLCJncmFudF90eXBlIjoicGFzc3dvcmQiLCJ1c2VyX2lkIjoiZWY3MzI0MzUtY2Y3MC00ZmI5LTg0MTMtOTdhNjRhODUxZWEyIiwib3JpZ2luIjoidWFhIiwidXNlcl9uYW1lIjoiSlhUWS0xNzYtOS5hZG1pbiIsImVtYWlsIjoiYWRtaW5AZ2UuY29tIiwiYXV0aF90aW1lIjoxNzQxMTY2MzE5LCJyZXZfc2lnIjoiZGQ0YzA2YjIiLCJpYXQiOjE3NDExNjYzMTksImV4cCI6MTc0MTIwOTUxOSwiaXNzIjoiaHR0cHM6Ly9KWFRZLTE3Ni05L3VhYS9vYXV0aC90b2tlbiIsInppZCI6InVhYSIsImF1ZCI6WyJoaXN0b3JpYW5fZW50ZXJwcmlzZSIsImhpc3Rvcmlhbl9wdWJsaWNfcmVzdF9hcGkiLCJoaXN0b3JpYW5fcmVzdF9hcGkiXX0.aBUW0fjlxbhNmPpD5n4MpRj2k0h7GfwsCEsOX_gb2hl6apMT-Rjd5uGSUwaeGEectxUaSnqjPYs-kd1EPuZEP4ATTF2vm6k4LV_yRAhG8yr7ZeZUUbj-IX1M3Jq-FRCXNAicLcouNGSbBMrzo01oDnM99cNjxuEWDcQ6CPnSVUk8RJPfn1hzY5Z5rfYfYJQLXYhOtQDbMTVDWQVuVvqnpV-dCUPwyZzfBtcxBJAc5pGTm4tK7LQUWkUiMbAGWMUYCrWyiVxxWRukb5AaEQdbDyJlyEGyAD9Wje8HoC6Ry2-lgd6mEVlh1CFlveZSy6oNOJwHUZovhEUACVIqYl3ISA");
        System.out.println(TokenHolder.getValidToken());

        // JSONObject jsonObject = CollectorsApiInvoker.collectorDetails();
        //System.out.println(jsonObject);

        //TagsResult jsonObject1 = TagsApiInvoker.queryTagsByPath(10, "*");
        TagsResult tagsResult = HLSApiInvoker.queryAllTags();

        TagNameListRequest tagNameListRequest = new TagNameListRequest();
        List<TagNameRequest> collect = tagsResult.getData().getTagNameList().stream()
                .map(tagName -> new TagNameRequest().setTagName(tagName.getTagName()))
                .collect(Collectors.toList());
        tagNameListRequest.setTagNameList(collect);
        DataResult ddbTagValue = HLSApiInvoker.getDDBTagValue(tagNameListRequest);

        HistorianRequest historianRequest = new HistorianRequest();
        historianRequest.setStartTime(DateTime.of("2025-03-18 15:16:50", "yyyy-MM-dd HH:mm:ss"));
        historianRequest.setEndTime(DateTime.of("2025-03-18 15:16:53", "yyyy-MM-dd HH:mm:ss"));
        historianRequest.setInterval(1L);
        historianRequest.setNeedQueryAVG(true);
        historianRequest.setNeedQueryBound(true);
        historianRequest.setNeedQueryMAX(true);
        historianRequest.setNeedQueryMIN(true);
        historianRequest.setTagNameList(new ArrayList<TagNameRequest>(){{
            add(new TagNameRequest().setTagName("TEST_AI_27"));
        }});
        DatasResult hdbTagValue = HLSApiInvoker.getHDBTagValue(historianRequest);
        //String json = "{\"Quality\": 3}"; // 假设的JSON字符串
        //DataSampleEntity item = JSON.parseObject(json, DataSampleEntity.class);
        System.out.println(JSONObject.toJSONString(tagsResult, SerializerFeature.WriteEnumUsingToString));
        System.out.println(JSONObject.toJSONString(ddbTagValue, SerializerFeature.WriteEnumUsingToString));
        System.out.println(JSONObject.toJSONString(hdbTagValue, SerializerFeature.WriteEnumUsingToString));
    }

}