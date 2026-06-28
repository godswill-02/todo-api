package com.todo.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.todo.app.dto.request.TodoCreate;
import com.todo.app.dto.response.TodoResponse;
import com.todo.app.mappers.TodoMapper;
import com.todo.app.models.Content;
import com.todo.app.models.Todo;
import com.todo.app.models.enums.MediaType;

class TodoMapperTest {

    @Test
    void toTodoEntity_shouldCreateContentEntityWithProvidedValues() {
        TodoCreate request = new TodoCreate();
        request.setTitle("Write docs");
        request.setContent(MediaType.IMAGE);
        request.setContentText("https://example.com/image.png");

        Todo todo = TodoMapper.toTodoEntity(request);

        assertThat(todo.getContent()).isNotNull();
        assertThat(todo.getContent().getMediaType()).isEqualTo(MediaType.IMAGE);
        assertThat(todo.getContent().getContent()).isEqualTo("https://example.com/image.png");
    }

    @Test
    void toTodoResponse_shouldExposeContentDetails() {
        Todo todo = new Todo();
        todo.setTitle("Watch lecture");

        Content content = new Content();
        content.setMediaType(MediaType.VIDEO);
        content.setContent("https://example.com/video.mp4");
        todo.setContent(content);

        TodoResponse response = TodoMapper.toTodoResponse(todo);

        assertThat(response.getMediaType()).isEqualTo(MediaType.VIDEO);
        assertThat(response.getContentText()).isEqualTo("https://example.com/video.mp4");
    }
}
