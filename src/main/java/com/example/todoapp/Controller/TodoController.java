package com.example.todoapp.Controller;

import com.example.todoapp.DTO.ResponseDTO;
import com.example.todoapp.DTO.TodoDTO;
import com.example.todoapp.Service.TodoService;
import com.example.todoapp.Util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/todo")
public class TodoController {

    @Autowired
    private TodoDTO todoDTO;
    @Autowired
    private TodoService todoService;
    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping(value = "add")
    public ResponseEntity adTodo(@RequestBody TodoDTO todoDTO) {
        try {

            String res = todoService.addTodo(todoDTO);
            if (res.equals("00")) {
                responseDTO.setResponse(VarList.RSP_SUCCESS, "Todo Added", todoDTO);
                return new ResponseEntity(responseDTO, HttpStatus.OK);
            } else if (res.equals("06")) {
                responseDTO.setResponse(VarList.RSP_DUPLICATE, "Todo Already Exist", todoDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setResponse(VarList.RSP_ERROR, "Error Occurred", todoDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setResponse(VarList.RSP_ERROR, "Error Occurred", e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "all")
    public ResponseEntity getAllTodo() {
        try {
            List<TodoDTO> todoList = todoService.getAllTodo();
            if (todoList == null) {
                responseDTO.setResponse(VarList.RSP_SUCCESS, "Empty List", null);
                return new ResponseEntity(responseDTO, HttpStatus.OK);
            } else {
                responseDTO.setResponse(VarList.RSP_SUCCESS, "Todo List", todoList);
                return new ResponseEntity(responseDTO, HttpStatus.OK);
            }

        } catch (Exception e) {
            responseDTO.setResponse(VarList.RSP_ERROR, "Error Occurred", e.getMessage());
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity deleteTodo(@PathVariable int id) {
        try {
            String res = todoService.deleteTodo(id);
            if (res.equals(VarList.RSP_SUCCESS)) {
                responseDTO.setResponse(VarList.RSP_SUCCESS, "Todo Deleted @" + id, null);
                return new ResponseEntity(responseDTO, HttpStatus.OK);
            } else if (res.equals(VarList.RSP_NO_DATA_FOUND)) {
                responseDTO.setResponse(VarList.RSP_NO_DATA_FOUND, "Todo Not Found @" + id, null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setResponse(VarList.RSP_ERROR, "Error Occurred @" + id, null);
                return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            responseDTO.setResponse(VarList.RSP_ERROR, "Error Occurred @" + id, e.getMessage());
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "get/{id}")
    public ResponseEntity getTodoById(@PathVariable int id) {
        try {
            TodoDTO todo = todoService.getTodoById(id);
            if (todo != null) {
                responseDTO.setResponse(VarList.RSP_SUCCESS, "Todo Found @" + id, todo);
                return new ResponseEntity(responseDTO, HttpStatus.OK);
            } else if (todo == null) {
                responseDTO.setResponse(VarList.RSP_NO_DATA_FOUND, "Todo Not Found @" + id, null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setResponse(VarList.RSP_ERROR, "Error Occurred @" + id, null);
                return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            responseDTO.setResponse(VarList.RSP_ERROR, "Error Occurred @" + id, e.getMessage());
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "doneStatus/{id}")
    public ResponseEntity updateTodoStatus(@PathVariable int id) {
        try {
            String res = todoService.updateTodoStatus(id);
            if (res.equals(VarList.RSP_SUCCESS)) {
                responseDTO.setResponse(VarList.RSP_SUCCESS, "Todo Updated @" + id, null);
                return new ResponseEntity(responseDTO, HttpStatus.OK);
            } else if (res.equals(VarList.RSP_NO_DATA_FOUND)) {

                responseDTO.setResponse(VarList.RSP_NO_DATA_FOUND, "Todo Not Found @" + id, null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setResponse(VarList.RSP_ERROR, "Error Occurred @" + id, null);
                return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            responseDTO.setResponse(VarList.RSP_ERROR, "Error Occurred @" + id, e.getMessage());
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
