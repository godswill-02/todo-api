package com.todo.app.dto.request;

import com.todo.app.models.enums.MediaType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TodoCreate {

    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotNull(message = "Content type cannot be null")
    private MediaType mediaType;
    @NotBlank(message = "Media cannot be blank")
    private String media;
    private boolean completed;
}
