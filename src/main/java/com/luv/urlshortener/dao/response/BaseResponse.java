package com.luv.urlshortener.dao.response;

import com.luv.urlshortener.dao.request.LoginRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse<T> {

    private T data;
    private String statusMessage;//TODO: Implement Status Codes and Document the meaning

    public static <D> BaseResponse<D> success() {
        return new BaseResponse<>(null,  "success");
    }

    public static <D> BaseResponse<D> failure() {
        return new BaseResponse<>(null, "failure");
    }

    public static <D> BaseResponse<D> success(D data) {
        return new BaseResponse<>(data,"success");
    }

    public static <D> BaseResponse<D> failure(D data) {
        return new BaseResponse<>(data, "failure");
    }

    public static <D> BaseResponse<D> success(D data, String statusMessage) {
        return new BaseResponse<>(data, statusMessage);
    }

    public static <D> BaseResponse<D> failure(D data, String statusMessage) {
        return new BaseResponse<>(data, statusMessage);
    }
}