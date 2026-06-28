package com.todo.app.dto.response;

import com.todo.app.models.enums.MediaType;

import lombok.Data;

@Data
public class TodoResponse {
    private Long id;
    private String title;
    private MediaType mediaType = MediaType.TEXT;
    private String contentText = "";
    private boolean completed;
    private String createdAt;
    private String updatedAt;
}
