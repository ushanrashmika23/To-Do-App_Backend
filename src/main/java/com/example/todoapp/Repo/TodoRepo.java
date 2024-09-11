package com.example.todoapp.Repo;

import com.example.todoapp.Entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<TodoEntity,Integer>{

}
