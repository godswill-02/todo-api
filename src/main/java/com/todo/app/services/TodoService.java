package com.todo.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.todo.app.dto.request.TodoCreate;
import com.todo.app.dto.request.TodoUpdate;
import com.todo.app.dto.response.TodoResponse;
import com.todo.app.exceptions.TodoNotFoundException;
import com.todo.app.mappers.TodoMapper;
import com.todo.app.models.Content;
import com.todo.app.models.Todo;
import com.todo.app.models.enums.MediaType;
import com.todo.app.repositories.TodoRepository;
import com.todo.app.validators.TodoContentValidator;

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
        TodoContentValidator.validate(
                todo.getMediaType(),
                todo.getMedia());
        final Todo newTodo = TodoMapper.toTodoEntity(todo);
        System.out.println(newTodo);
        final Todo savedTodo = todoRepository.save(newTodo);
        return TodoMapper.toTodoResponse(savedTodo);
    }

    @Override
    public TodoResponse updateTodo(Long id, TodoUpdate todo) {
        final Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));

        if (todo.getTitle() != null) {
            existingTodo.setTitle(todo.getTitle());
        }

        if (todo.getCompleted() != null) {
            existingTodo.setCompleted(todo.getCompleted());
        }

        if (todo.getMedia() != null || todo.getMediaType() != null) {

            if (existingTodo.getContent() == null) {
                existingTodo.setContent(new Content());
            }

            if (todo.getMediaType() != null) {
                existingTodo.getContent().setMediaType(todo.getMediaType());
            }

            if (todo.getMedia() != null) {
                existingTodo.getContent().setMedia(todo.getMedia());
            }
        }

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
