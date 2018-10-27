package com.example.dbtest.service;

import com.example.dbtest.controllers.TaskForm;
import com.example.dbtest.repositories.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TaskServiceImplの単体テスト")
class TaskServiceImplUnitTest {
    @Mock // モック(stub)クラス
    private TaskRepository taskRepository;

    @InjectMocks // テスト対象クラス
    private TaskServiceImpl taskServiceImpl;

    @Test // テストケース
    @DisplayName("タスクが取得できない場合のテスト")
        // テスト名
    void test() {
        // モッククラスのI/Oをセット
        when(taskRepository.findById(1)).thenReturn(Optional.empty());

        // サービスを実行
        Optional<TaskForm> taskO = taskServiceImpl.getTaskForm(1);

        // モックの指定メソッドの実行回数を検査
        verify(taskRepository, times(1)).findById(1);

        // 戻り値の検査
        assertNull(taskO.orElse(null), "結果がNULLではありません");
    }

}