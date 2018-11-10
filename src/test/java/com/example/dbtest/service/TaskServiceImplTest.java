package com.example.dbtest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.dbtest.controllers.TaskForm;
import com.example.dbtest.entity.Task;

/**
 *
 */
@SpringJUnitConfig //ExtendWith　contentConfigration SpringBootでJunit5を使う
@SpringBootTest //毎回サーバ起動
@ActiveProfiles("unit")//application-unit.ymlのunitを対応（DBの設定を読み込む）
@DisplayName("TaskServiceImplの結合テスト")
class TaskServiceImplTest {
    @Autowired
    private TaskService taskService;

    @Test
    @DisplayName("全件検索のテスト")
    void testFindAllCheckCount() {
        List<Task> list = taskService.findAll();

        //Tasksテーブルに入っている3件が取得できているか確認
        assertEquals( 3, list.size());
    }
    
    @Test
    @DisplayName("タスクが取得できない場合のテスト")
    void testGetTaskFormReturnNull() {//単体テストと名前が同じになるのはOK?
        Optional<TaskForm> taskO = taskService.getTaskForm(0);

        assertNull(taskO.orElse(null), "結果がNULLではありません");
    }
    
    @Test
    @DisplayName("1件のタスクが取得できた場合のテスト")
    void testGetTaskFormReturnOne() {
        Optional<TaskForm> task = taskService.getTaskForm(1);
        
        //idが1の場合、タイトルはwash dishes2
        assertEquals( "wash dishes2", task.get().getTitle());
    }
    


}