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
            response.setContentText(todo.getContent().getContent());
        } else {
            response.setMediaType(MediaType.TEXT);
            response.setContentText("");
        }

        return response;
    }

    public static Todo toTodoEntity(TodoCreate request) {
        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setCompleted(request.isCompleted());

        Content content = new Content();
        content.setMediaType(request.getContent() != null ? request.getContent() : MediaType.TEXT);
        content.setContent(request.getContentText() != null ? request.getContentText() : "");
        todo.setContent(content);

        return todo;
    }
}
