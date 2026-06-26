package com.todo.app.dto.request;

import com.todo.app.models.enums.MediaType;

import lombok.Data;

@Data
public class TodoCreate {
    private String title;
    private MediaType content;
    private boolean isCompleted;
}
