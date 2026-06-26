package com.todo.app.controllers.v1;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.app.dto.request.TodoCreate;
import com.todo.app.dto.response.TodoResponse;
import com.todo.app.services.TodoService;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    TodoService todoService;
    TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> getTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodoById(Long id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(TodoCreate todo) {
        return ResponseEntity.ok(todoService.createTodo(todo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(Long id, TodoCreate todo) {
        return ResponseEntity.ok(todoService.updateTodo(id, todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
