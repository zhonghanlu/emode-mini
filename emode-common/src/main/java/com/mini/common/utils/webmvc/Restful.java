package com.mini.common.utils.webmvc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhl
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restful<T> {

    public static RestfulBuilder<Void> SUCCESS() {
        return Restful.<Void>builder().code(200).msg("操作成功");
    }

    public static <T> RestfulBuilder<T> OBJECT(T data) {
        return Restful.<T>builder().code(200).msg("操作成功").object(data);
    }

    public static RestfulBuilder ERROR_PARAMS() {
        return Restful.builder().code(400).msg("参数错误");
    }

    public static RestfulBuilder ERROR_SERVER() {
        return Restful.builder().code(500).msg("服务器错误");
    }

    public static RestfulBuilder NOT_FOUNT() {
        return Restful.builder().code(404).msg("数据不存在");
    }

    public static RestfulBuilder ERROR_LOGIN() {
        return Restful.builder().code(403).msg("未登录");
    }

    public static RestfulBuilder LOGIN_EXPIRE() {
        return Restful.builder().code(405).msg("登陆已失效");
    }

    public static RestfulBuilder FORBIDDEN() {
        return Restful.builder().code(401).msg("无权限，禁止访问");
    }

    @JsonProperty("code")
    private int code;

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("data")
    private T object;

    @JsonProperty("info")
    private Object info;

    @JsonProperty("requestId")
    private String requestId;

}
