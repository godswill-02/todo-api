package com.todo.app.controllers.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.app.dto.request.TodoCreate;
import com.todo.app.dto.request.TodoUpdate;
import com.todo.app.dto.response.ApiResponse;
import com.todo.app.dto.response.TodoResponse;
import com.todo.app.services.TodoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    private final TodoService todoService;

    TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TodoResponse>>> getTodos() {
        return ResponseEntity.ok(new ApiResponse<>(true, todoService.getAllTodos(), "Todos retrieved successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoResponse>> getTodoById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, todoService.getTodoById(id), "Todo retrieved successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TodoResponse>> createTodo(@Valid @RequestBody TodoCreate todo) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(true, todoService.createTodo(todo), "Todo created successfully"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoResponse>> updateTodo(@PathVariable Long id, @Valid @RequestBody TodoUpdate todo) {
        return ResponseEntity.ok(new ApiResponse<>(true, todoService.updateTodo(id, todo), "Todo updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Todo deleted", "Todo deleted successfully"));
    }
}
