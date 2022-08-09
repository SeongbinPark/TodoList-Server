package com.example.demo.reposeitory;

import com.example.demo.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Retention;


@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
