package com.example.demo.model.service;


import com.example.demo.model.TodoEntity;
import com.example.demo.model.TodoRequest;
import com.example.demo.reposeitory.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository repo;
    //spring data jpa 가 몰래 만들어준 구현체를 주입.

    public TodoEntity add(TodoRequest request) {
        TodoEntity todoEntity = new TodoEntity();

        todoEntity.setTitle(request.getTitle());
        todoEntity.setOrder(request.getOrder());
        todoEntity.setCompleted(request.getCompleted());

        return this.repo.save(todoEntity);
    }//요청내용 레포에 저장 후 전체 객체인 TodoEntity 반환.


    public TodoEntity searchById(Long id) {
        return repo.findById(id).get();
    }


    public List<TodoEntity> searchAll() {
        return repo.findAll();
    }

    public TodoEntity updateById(Long id, TodoRequest request) {
        TodoEntity entity = this.searchById(id);

        if (request.getTitle() != null) {
            entity.setTitle(request.getTitle());
        }
        if (request.getOrder() != null) {
            entity.setOrder(request.getOrder());
        }
        if (request.getCompleted() != null) {
            entity.setCompleted(request.getCompleted());
        }

        return repo.save(entity);

    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public void deleteAll() {
        repo.deleteAll();
    }
}
