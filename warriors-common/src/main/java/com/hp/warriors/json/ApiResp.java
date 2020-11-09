package com.hp.warriors.json;

import com.google.common.collect.Maps;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResp<T> {

    private static final String CODE_SUCCESS = "20000";

    private static final String MESSAGE_SUCCESS = "success";

    private static final String CODE_ERROR = "40000";

    private String code;

    private String message;

    private T data;

    public static <T> ApiResp<T> success(String key, Object value) {
        Map<String, Object> data = Maps.newHashMap();
        data.put(key, value);
        return new ApiResp(CODE_SUCCESS, "success", data);
    }

    public static <T> ApiResp<T> success(T data) {
        return new ApiResp(CODE_SUCCESS, "success", data);
    }


    public static <T> ApiResp<T> success() {
        return new ApiResp(CODE_SUCCESS, "success", "success");
    }

    public static <T> ApiResp<T> error(String code, String message) {
        return new ApiResp(code, message, "error");
    }

}
