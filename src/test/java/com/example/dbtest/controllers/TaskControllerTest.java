package com.example.dbtest.controllers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
@SpringJUnitConfig
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration
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
    	MvcResult result = this.mvc.perform(get("/task")) // URL指定 ResultActionsを取得
                .andExpect(status().isOk()) // レスポンスコードをテスト
                .andExpect(view().name("task")) // returnされたview名をテスト
                .andExpect(model().attributeExists("list"))
                .andExpect(model().attribute("title", "タスク一覧"))
                .andReturn(); // model属性をテスト

    	TaskForm resultForm = (TaskForm) result.getModelAndView().getModel().get("taskForm");

        // then 
        assertEquals(resultForm.isNewTask(),true);

    }
    
	@Test
	@DisplayName("CSRFが効いているか確認するテスト")
    @WithMockUser 
    public void testCSRF() throws Exception {
    	
    	mvc.perform(post("/task"))
    	.andExpect(status().isForbidden());
    	
    }


	@Test
	@DisplayName("titleが空でバリデーションが機能するか確認するテスト")
    @WithMockUser 
    public void testPostValidation1() throws Exception {
    	
		TaskForm taskForm = new TaskForm();
	    taskForm.setTitle("");;
    	
    	mvc.perform(post("/task")
    			.with(SecurityMockMvcRequestPostProcessors.csrf())
    			.flashAttr("taskForm",taskForm))
    	.andExpect(status().isOk())
    	.andExpect(model().hasErrors())
        .andExpect(model().attribute("taskForm", taskForm))
        .andExpect(view().name("task"));
    	
    }

	@Test
	@DisplayName("バリデーションを通過するテスト")
    @WithMockUser 
    public void testPostValidation2() throws Exception {
    	
		TaskForm taskForm = new TaskForm();
		taskForm.setTypeId(1);
	    taskForm.setTitle("サンプルタイトル");;
	    taskForm.setDetail("サンプル詳細");
	    taskForm.setDeadline( LocalDateTime.of(2019, 11, 10, 13, 0));
    	
    	mvc.perform(post("/task")
    			.with(SecurityMockMvcRequestPostProcessors.csrf())
    			.flashAttr("taskForm",taskForm))
    	.andExpect(status().is3xxRedirection());//status()でStatusResultMatchersを取得している
    	
    }
	
	@Test
	@DisplayName("存在しないidでGetするとリダイレクトするか確認するテスト")
    @WithMockUser 
    public void testGetUpdateForm() throws Exception {
    	
		mvc.perform(get("/task/3"))
    	.andExpect(status().is3xxRedirection());
    	
    }
	
	@Test
	@DisplayName("存在しないidでUpdateするとリダイレクトするか確認するテスト")
    @WithMockUser 
    public void tesUpdateInvalidId() throws Exception {
		
		TaskForm taskForm = new TaskForm();
		taskForm.setTypeId(1);
	    taskForm.setTitle("サンプルタイトル");;
	    taskForm.setDetail("サンプル詳細");
	    taskForm.setDeadline( LocalDateTime.of(2019, 11, 10, 13, 0));
	    
		mvc.perform(post("/task/update/3")
				.with(SecurityMockMvcRequestPostProcessors.csrf())
    			.flashAttr("taskForm",taskForm))
    	.andExpect(status().is3xxRedirection());
    	
    }

}