package com.example.backend.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

/**
 * 封装的Result类
 * ①返回时不暴露内容。
 * ②统一各种情况下的返回。
 */

public class Result {
    private Boolean isSuccess;
    private String errorMessage;
    private Object data;
    private Long dataNumber;

    @Contract(" -> new")
    public static @NotNull Result success() {
        return new Result(true, null, null, null);
    }

    @Contract("_ -> new")
    public static @NotNull Result success(Object data) {
        return new Result(true, null, data, null);
    }

    @Contract("_, _ -> new")
    public static @NotNull Result success(List<?> data, Long dataNumber) {
        return new Result(true, null, data, dataNumber);
    }

    @Contract("_ -> new")
    public static @NotNull Result fail(String errorMessage) {
        return new Result(false, errorMessage, null, null);
    }

    @Contract("_, _ -> new")
    public static @NotNull Result fail(String errorMessage, Object data) {
        return new Result(false, errorMessage, data, null);
    }

    @Contract("_, _, _ -> new")
    public static @NotNull Result fail(String errorMessage, List<?> data, Long dataNumber) {
        return new Result(false, errorMessage, data, dataNumber);
    }
}
