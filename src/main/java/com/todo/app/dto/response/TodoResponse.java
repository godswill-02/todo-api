package com.todo.app.dto.response;


import com.todo.app.models.enums.MediaType;

import lombok.Data;

@Data
public class TodoResponse {
    private Long id;
    private String title;
    private MediaType content;
    private boolean isCompleted;
    private String createdAt;
    private String updatedAt;
}
