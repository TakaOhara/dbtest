package com.example.dbtest.service;


import com.example.dbtest.controllers.TaskForm;
import com.example.dbtest.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

	List<Task> findAll();
		
	Optional<TaskForm> getTaskForm(int id);

	void save(Task task);
	
	void deleteById(int id);
}
