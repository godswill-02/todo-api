package com.todo.app.services;

import java.util.List;

import com.todo.app.dto.request.TodoCreate;
import com.todo.app.dto.response.TodoResponse;

public interface ITodoService {
    public List<TodoResponse> getAllTodos();
    public TodoResponse getTodoById(Long id);
    public TodoResponse createTodo(TodoCreate todo);
    public TodoResponse updateTodo(Long id, TodoCreate todo);
    public void deleteTodo(Long id);
}
