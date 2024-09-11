package com.example.todoapp.Entity;

import com.example.todoapp.Util.UserFunction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "todo")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int todoID;
    private String todo;
    private boolean todoDone;
    private String todoDate;
    private String todoTime;

    @PreUpdate
    @PrePersist
    public void updateDateTime() {
        this.todoDate = UserFunction.getDate();
        this.todoTime = UserFunction.getTime();
    }
}
