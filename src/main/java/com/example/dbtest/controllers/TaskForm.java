package com.example.dbtest.controllers;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
