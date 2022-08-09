package com.example.demo.model.controller;


import com.example.demo.model.TodoEntity;
import com.example.demo.model.TodoRequest;
import com.example.demo.model.TodoResponse;
import com.example.demo.model.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/")
public class TodoController {

    private final TodoService service;

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request) {
        System.out.println("CREATE");

        if (ObjectUtils.isEmpty(request.getTitle())) {
            return ResponseEntity.badRequest().build();
        }

        if (ObjectUtils.isEmpty(request.getOrder())) {
            request.setOrder(0L);
        }

        if (ObjectUtils.isEmpty(request.getCompleted())) {
            request.setCompleted(false);
        }

        TodoEntity result = this.service.add(request);

        return ResponseEntity.ok(new TodoResponse(result));

    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id) {
        TodoEntity entity = service.searchById(id);
        return ResponseEntity.ok(new TodoResponse(entity));
    }


    @GetMapping
    public ResponseEntity<List<TodoResponse>> readAll() {
        List<TodoResponse> list = service.searchAll().stream().map(TodoResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request) {
        TodoEntity todoEntity = this.service.updateById(id, request);
        return ResponseEntity.ok(new TodoResponse(todoEntity));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        this.service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        this.service.deleteAll();
        return ResponseEntity.ok().build();
    }
}
