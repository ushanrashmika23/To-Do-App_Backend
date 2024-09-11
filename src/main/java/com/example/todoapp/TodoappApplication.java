package com.example.todoapp;

import com.example.todoapp.DTO.TodoDTO;
import com.example.todoapp.Entity.TodoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoappApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoappApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public TodoEntity todoEntity() {
        return new TodoEntity();
    }
    @Bean
    public TodoDTO todoDTO() {
		return new TodoDTO();
	}
}
