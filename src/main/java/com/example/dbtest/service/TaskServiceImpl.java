package com.example.dbtest.service;

import com.example.dbtest.controllers.TaskForm;
import com.example.dbtest.entity.Task;
import com.example.dbtest.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

	private final TaskRepository repository;

	@Autowired
	public TaskServiceImpl(TaskRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Task> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<TaskForm> getTaskForm(int id) {
		Optional<Task> task = repository.findById(id);
		if(!task.isPresent()) {
			return Optional.ofNullable(null);
		}
		TaskForm form = new TaskForm(
				task.get().getTypeId(),
				task.get().getTitle(),
				task.get().getDetail(),
				task.get().getDeadline(),
				false);
		return Optional.ofNullable(form);
	}

	@Override
	public void save(Task task) {
		repository.saveAndFlush(task);
	}

	@Override
	public void deleteById(int id) {
		Optional<Task> task =  repository.findById(id);
		System.out.println(task.isPresent());
		if(task.isPresent()) {
			repository.deleteById(id);
		}

	}

}
