package com.todo.app.mappers;

import com.todo.app.dto.request.TodoCreate;
import com.todo.app.dto.response.TodoResponse;
import com.todo.app.models.Content;
import com.todo.app.models.Todo;
import com.todo.app.models.enums.MediaType;

public class TodoMapper {

    public static TodoResponse toTodoResponse(Todo todo) {
        TodoResponse response = new TodoResponse();
        response.setId(todo.getId());
        response.setTitle(todo.getTitle());
        response.setCompleted(todo.isCompleted());
        response.setCreatedAt(todo.getCreatedAt() != null ? todo.getCreatedAt().toString() : null);
        response.setUpdatedAt(todo.getUpdatedAt() != null ? todo.getUpdatedAt().toString() : null);

        if (todo.getContent() != null) {
            response.setMediaType(todo.getContent().getMediaType());
            response.setMedia(todo.getContent().getMedia());
        } else {
            response.setMediaType(MediaType.TEXT);
            response.setMedia("");
        }

        return response;
    }

    public static Todo toTodoEntity(TodoCreate request) {
        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setCompleted(request.isCompleted() ? request.isCompleted() : false);

        Content content = new Content();
        content.setMediaType(request.getMediaType() != null ? request.getMediaType() : MediaType.TEXT);
        content.setMedia(request.getMedia() != null ? request.getMedia() : "");
        todo.setContent(content);

        return todo;
    }
}
