package com.todo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.app.models.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
}
