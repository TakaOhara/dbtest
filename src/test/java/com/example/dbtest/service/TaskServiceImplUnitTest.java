package com.example.dbtest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.dbtest.controllers.TaskForm;
import com.example.dbtest.entity.Task;
import com.example.dbtest.repositories.TaskRepository;

@ExtendWith(MockitoExtension.class)//P385 RunWithとの違いJunit4
@DisplayName("TaskServiceImplの単体テスト")
class TaskServiceImplUnitTest {
	

	
    @Mock // モック(stub)クラス ダミーオブジェクト
    private TaskRepository taskRepository;

    @InjectMocks // テスト対象クラス　モックを探す newする
    private TaskServiceImpl taskServiceImpl;
    
    @Test // テストケース
    @DisplayName("テーブルTaskの全件取得で0件の場合のテスト")
        // テスト名
    void testFindAllReturnEmptyList() {
    	
    	//モックから返すListに2つのTaskオブジェクトをセット
    	List<Task> list = new ArrayList<>();
    	 	
        // モッククラスのI/Oをセット（findAll()の型と異なる戻り値はNG）
        when(taskRepository.findAll()).thenReturn(list);

        // サービスを実行
        List<Task> actualList= taskServiceImpl.findAll();

        // モックの指定メソッドの実行回数を検査
        verify(taskRepository, times(1)).findAll();

        // 戻り値の検査(expected, actual)
        assertEquals(0, actualList.size());
        
    }
    
    @Test // テストケース
    @DisplayName("テーブルTaskの全件取得で2件の場合のテスト")
        // テスト名
    void testFindAllReturnList() {
    	
    	//モックから返すListに2つのTaskオブジェクトをセット
    	List<Task> list = new ArrayList<>();
    	Task task1 = new Task();
    	Task task2 = new Task();
    	list.add(task1);
    	list.add(task2);
    	
    	
        // モッククラスのI/Oをセット（findAll()の型と異なる戻り値はNG）
        when(taskRepository.findAll()).thenReturn(list);

        // サービスを実行
        List<Task> actualList= taskServiceImpl.findAll();

        // モックの指定メソッドの実行回数を検査
        verify(taskRepository, times(1)).findAll();

        // 戻り値の検査(expected, actual)
        assertEquals(2, actualList.size());
        
    }

    @Test // テストケース
    @DisplayName("タスクが取得できない場合のテスト")
        // テスト名
    void testGetTaskFormReturnNull() {
    	
        // モッククラスのI/Oをセット
        when(taskRepository.findById(0)).thenReturn(Optional.empty());

        // サービスを実行
        Optional<TaskForm> taskO = taskServiceImpl.getTaskForm(0);

        // モックの指定メソッドの実行回数を検査
        verify(taskRepository, times(1)).findById(0);

        // 戻り値の検査
        assertNull(taskO.orElse(null), "結果がNULLではありません");
        
    }
    
    @Test // テストケース
    @DisplayName("タスクを1件取得した場合のテスト")
        // テスト名
    void testGetTaskFormReturnOne() {
    	
    	//TaskFormをデフォルト値でインスタンス化
    	Task task = new Task();
    	Optional<Task> form  = Optional.ofNullable(task);
        // モッククラスのI/Oをセット
        when(taskRepository.findById(1)).thenReturn(form);

        // サービスを実行
        Optional<TaskForm> taskActual = taskServiceImpl.getTaskForm(1);

        // モックの指定メソッドの実行回数を検査
        verify(taskRepository, times(1)).findById(1);

        // 戻り値の検査(1件インスタンスが戻ってきていることを検査する方法は？
        //これでよいか？
        assertTrue( taskActual.get() instanceof TaskForm );//Optional.isPresent()を使用する
        
    }
    
    @Test // テストケース　単体テストではデータベースの例外は考えない
    @DisplayName("存在しないidの場合メソッドが実行されないことを確認するテスト")
        // テスト名
    void testDeleteByIdNotFound() {
    	
        // モッククラスのI/Oをセット
        when(taskRepository.findById(0)).thenReturn(Optional.empty());

        // サービスを実行
        taskServiceImpl.deleteById(0);

        // モックの指定メソッドの実行回数を検査
        verify(taskRepository, times(0)).deleteById(0);

        
    }
    
    
}