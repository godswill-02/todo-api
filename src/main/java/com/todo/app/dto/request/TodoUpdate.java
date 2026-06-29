package com.todo.app.dto.request;


import com.todo.app.models.enums.MediaType;

import lombok.Data;

@Data
public class TodoUpdate {

    private String title;

    private Boolean completed;

    private String media;

    private MediaType mediaType;
}