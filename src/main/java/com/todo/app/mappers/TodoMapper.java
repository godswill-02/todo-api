package com.todo.app.mappers;

import com.todo.app.dto.request.TodoCreate;
import com.todo.app.dto.response.TodoResponse;
import com.todo.app.models.Todo;
import com.todo.app.models.enums.MediaType;

public class TodoMapper {

    public static TodoResponse toTodoResponse(Todo todo) {
        TodoResponse response = new TodoResponse();
        response.setId(todo.getId());
        response.setTitle(todo.getTitle());
        response.setContent(todo.getContent());
        response.setCompleted(todo.isCompleted());
        response.setCreatedAt(todo.getCreatedAt().toString());
        response.setUpdatedAt(todo.getUpdatedAt().toString());
        return response;
    }

    public static Todo toTodoEntity(TodoCreate response) {
        Todo todo = new Todo();
        todo.setTitle(response.getTitle());
        todo.setContent(response.getContent() != null ? response.getContent() : MediaType.TEXT);
        todo.setCompleted(response.isCompleted());
        return todo;
    }
    
}
