package com.example.dbtest.service;

import com.example.dbtest.controllers.TaskForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 *
 */
@SpringJUnitConfig
@SpringBootTest
@ActiveProfiles("unit")
@DisplayName("TaskServiceImplの結合テスト")
class TaskServiceImplTest {
    @Autowired
    private TaskService taskService;

    @Test
    @DisplayName("タスクが取得できない場合のテスト")
    void test() {
        Optional<TaskForm> taskO = taskService.getTaskForm(1);

        assertNull(taskO.orElse(null), "結果がNULLではありません");
    }

}