package com.example.todoapp.Service;

import com.example.todoapp.DTO.TodoDTO;
import com.example.todoapp.Entity.TodoEntity;
import com.example.todoapp.Repo.TodoRepo;
import com.example.todoapp.Util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TodoService {
    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private TodoEntity todoEntity;
    @Autowired
    private ModelMapper modelMapper;

    public String addTodo(TodoDTO todoDTO) {
        if (todoRepo.existsById(todoDTO.getTodoID())) {
            return VarList.RSP_DUPLICATE;
        } else {
            todoRepo.save(modelMapper.map(todoDTO, TodoEntity.class));
            return VarList.RSP_SUCCESS;
        }
    }


    public List<TodoDTO> getAllTodo() {
        if (todoRepo.findAll().size() > 0) {
            List<TodoEntity> todos = todoRepo.findAll();
            return modelMapper.map(todos, new TypeToken<ArrayList<TodoDTO>>() {
            }.getType());
        } else {
            return null;
        }
    }

    public String deleteTodo(int id) {
        if (todoRepo.existsById(id)) {
            todoRepo.deleteById(id);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public TodoDTO getTodoById(int id) {
        if (todoRepo.existsById(id)) {
            TodoEntity todo = todoRepo.findById(id).get();
            return modelMapper.map(todo, TodoDTO.class);
        } else {
            return null;
        }
    }
}
