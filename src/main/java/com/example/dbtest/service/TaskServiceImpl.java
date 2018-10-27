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

    /**
     * コンストラクタ
     * @param repository
     */
	@Autowired // コンストラクタインジェクション
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
			return Optional.empty(); // emptyを使うのが望ましいです
		}
        // mapを使ってスマートに記述
		return task.map(tsk ->
                new TaskForm(tsk.getTypeId(), tsk.getTitle(), tsk.getDetail(), tsk.getDeadline(), false));
	}

	@Override
	public void save(Task task) {
		repository.saveAndFlush(task);
	}

	@Override
	public void deleteById(int id) {
		if(repository.findById(id).isPresent()) { // 不要な変数宣言の省略
			repository.deleteById(id);
		}
	}
}
