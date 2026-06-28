package com.todo.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.todo.app.dto.request.TodoCreate;
import com.todo.app.dto.response.TodoResponse;
import com.todo.app.exceptions.TodoNotFoundException;
import com.todo.app.mappers.TodoMapper;
import com.todo.app.models.Content;
import com.todo.app.models.Todo;
import com.todo.app.models.enums.MediaType;
import com.todo.app.repositories.TodoRepository;

@Service
public class TodoService implements ITodoService {

    private final TodoRepository todoRepository;

    TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<TodoResponse> getAllTodos() {
        final List<Todo> todos = todoRepository.findAll();
        return todos.stream().map(TodoMapper::toTodoResponse).toList();
    }

    @Override
    public TodoResponse getTodoById(Long id) {
        final Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new TodoNotFoundException(id));
        return TodoMapper.toTodoResponse(todo);
    }

    @Override
    public TodoResponse createTodo(TodoCreate todo) {
        final Todo newTodo = TodoMapper.toTodoEntity(todo);
        final Todo savedTodo = todoRepository.save(newTodo);
        return TodoMapper.toTodoResponse(savedTodo);
    }

    @Override
    public TodoResponse updateTodo(Long id, TodoCreate todo) {
        final Todo existingTodo = todoRepository.findById(id)
            .orElseThrow(() -> new TodoNotFoundException(id));
        existingTodo.setTitle(todo.getTitle());
        existingTodo.setCompleted(todo.isCompleted());

        if (existingTodo.getContent() == null) {
            existingTodo.setContent(new Content());
        }

        existingTodo.getContent().setMediaType(todo.getContent() != null ? todo.getContent() : MediaType.TEXT);
        existingTodo.getContent().setContent(todo.getContentText() != null ? todo.getContentText() : "");

        final Todo updatedTodo = todoRepository.save(existingTodo);
        return TodoMapper.toTodoResponse(updatedTodo);
    }

    @Override
    public void deleteTodo(Long id) {
        final Todo existingTodo = todoRepository.findById(id)
            .orElseThrow(() -> new TodoNotFoundException(id));
        todoRepository.delete(existingTodo);
    }
}
