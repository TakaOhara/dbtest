package com.example.dbtest.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class TaskForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private int typeId;

    @NotEmpty (message = "{TaskForm.title.NotEmpty}")//"空白は不可")
    private String title;

    private String detail;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime deadline;

    public boolean isNewTask;

}
