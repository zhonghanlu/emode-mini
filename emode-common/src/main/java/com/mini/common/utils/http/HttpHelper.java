package com.mini.common.utils.http;

import com.mini.common.exception.service.EModeServiceException;
import com.mini.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhl
 * @create 2024/5/23 9:32
 */
@Slf4j
public class HttpHelper {

    private HttpHelper() {

    }

    /**
     * post 无参
     *
     * @param requestUrl  请求地址
     * @param requestJson 请求json体
     */
    public static void requestServerVoid(String requestUrl, String requestJson) {
        requestServerReturn(requestUrl, requestJson);
    }

    /**
     * post 有参数
     *
     * @param requestUrl  请求地址
     * @param requestJson 请求json体
     */
    public static HttpResponse requestServerReturn(String requestUrl, String requestJson) {
        String result = "";
        HttpResponse httpResponse;
        try {
            result = HttpClientUtil.doPostJson(requestUrl, requestJson);
            httpResponse = JsonUtils.parseObject(result, HttpResponse.class);
            assert httpResponse != null;
            if (200 != httpResponse.getCode()) {
                throw new EModeServiceException("调用服务回调异常error:" + httpResponse.getMsg());
            }
        } catch (Exception e) {
            log.error("调用his服务异常,json:{}", requestJson);
            throw new EModeServiceException("调用服务处理异常" + e.getMessage());
        } finally {
            log.info("调用服务结果；请求地址：{},入参：{}，结果：{}", requestUrl, requestJson, result);
        }
        return httpResponse;
    }


}
