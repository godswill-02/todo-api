package com.todo.app.dto.request;

import com.todo.app.models.enums.MediaType;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class TodoCreate {

    private String title;
    private MediaType content = MediaType.TEXT;
    @Nullable
    private boolean isCompleted;
}
