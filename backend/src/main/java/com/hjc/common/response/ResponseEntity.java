package com.hjc.common.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hjc.enums.CodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 统一返回类型
 * @param code 状态码
 * @param data 响应数据
 * @param message 消息
 * @param <T> 类型
 */
@Schema(title = "统一返回类型")
public record ResponseEntity<T>(@Schema(title = "状态码", example = "200") int code,
                                @Schema(title = "消息") String message,
                                @Schema(title = "响应数据") T data) {
    //请求成功
    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<>(CodeEnum.SUCCESS.getCode(), "请求成功", data);
    }

    //请求成功 无data
    public static <T> ResponseEntity<T> success() {
        return ResponseEntity.success(null);
    }

    //请求失败
    public static <T> ResponseEntity<T> failure(int code, String message) {
        return new ResponseEntity<>(code, message, null);
    }

    public static <T> ResponseEntity<T> failure(CodeEnum errorCode, String message) {
        return new ResponseEntity<>(errorCode.getCode(), message, null);
    }

    public static <T> ResponseEntity<T> failure(CodeEnum errorCode) {
        return new ResponseEntity<>(errorCode.getCode(), errorCode.getMessage(), null);
    }
    public static <T> ResponseEntity<T> failure() {
        return new ResponseEntity<>(CodeEnum.PARAMS_ERROR.getCode(), CodeEnum.PARAMS_ERROR.getMessage(), null);
    }
    //转化为JSON字符串
    public String asJsonString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
