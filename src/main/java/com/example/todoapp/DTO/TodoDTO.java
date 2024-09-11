package com.example.todoapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoDTO {
    private int todoID;
    private String todo;
    private boolean todoDone;
    private String todoDate;
    private String todoTime;
}
