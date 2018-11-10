package com.example.dbtest.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitConfig//@ExtendWith(SpringExtension.class) + @ContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("unit")
@DisplayName("TaskControllerの結合テスト")
class TaskControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser // セキュリティを使っている場合はこれをつける
    public void testTask() throws Exception {
        this.mvc.perform(get("/task")) // URL指定
                .andExpect(status().isOk()) // レスポンスコードをテスト
                .andExpect(view().name("task")) // returnされたview名をテスト
                .andExpect(model().attribute("title", "タスク一覧")); // model属性をテスト

    }
    
    @Test
    @WithMockUser // セキュリティを使っている場合はこれをつける
    public void test() throws Exception {
    	
    	TaskForm taskForm = new TaskForm();
    	
    	mvc.perform((post("/task")).flashAttr("taskForm",taskForm))
        .andExpect(model().hasErrors())
        .andExpect(model().attribute("taskForm", taskForm))
        .andExpect(view().name("task"));
    	

    }


}