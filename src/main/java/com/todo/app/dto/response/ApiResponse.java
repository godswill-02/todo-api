package com.todo.app.dto.response;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private boolean status;
    private T data;
    private String message;

    public ApiResponse(boolean status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
