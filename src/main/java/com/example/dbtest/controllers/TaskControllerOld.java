package com.example.dbtest.controllers;

import com.example.dbtest.entity.Task;
import com.example.dbtest.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/task")
public class TaskControllerOld {

    private final TaskService taskService;

    @Autowired
    public TaskControllerOld(TaskService taskService) {
        this.taskService = taskService;
    }

//    //INDEX
//    @GetMapping
//    public ModelAndView task(
//            //@ModelAttributeでtaskFormは自動的にインスタンス化される　
//            //htmlに反映させるにはmav.addObjectが必須
//            @ModelAttribute("taskForm") TaskForm taskForm,
//            ModelAndView mav) {
//
//        taskForm.setNewTask(true);
//        List<Task> list = taskService.findAll();
//        mav.addObject("taskForm", taskForm);
//        mav.addObject("list", list);
//        mav.addObject("title", "タスク一覧");
//
//        mav.setViewName("task");
//        return mav;
//    }

    // Modelを引数にした場合、自動的にModelAttributeに登録されます
    // POJOの場合、ModelAttributeアノテーションは不要です。名称: TaskForm -> taskForm

    //INDEX
    @GetMapping
    public String task(TaskForm taskForm, Model model) {

        taskForm.setNewTask(true);
        List<Task> list = taskService.findAll();
        
        //これはrequest.setAttributeを呼び出してる？
        model.addAttribute("list", list);
        model.addAttribute("title", "タスク一覧");

        return "task";
    }

//    //INSERT
//    @PostMapping
//    public ModelAndView insert(
//            @Validated TaskForm taskForm, //ヴァリデーションはフォームクラスに対して行う
//            BindingResult result,
//            ModelAndView mav) {
//
//        Task task = makeTask(taskForm);
//        //redirect、失敗したらそのままHTML表示
//        if (!result.hasErrors()) {
//            taskService.save(task);
//            mav.setViewName("redirect:/task");
//        } else {
//            taskForm.setNewTask(true);
//            mav.addObject("taskForm", taskForm);
//            List<Task> list = taskService.findAll();
//            mav.addObject("list", list);//sessionに格納
//            mav.addObject("title", "タスク一覧 afterInsert");
//            mav.setViewName("task");
//        }
//        return mav;
//    }
    
    //INSERT
    @PostMapping
    public String insert(
    	TaskForm taskForm, //ヴァリデーションはフォームクラスに対して行う
        BindingResult result,
        Model model) {

        Task task = makeTask(taskForm);
        //redirect、失敗したらそのままHTML表示
        if (!result.hasErrors()) {
            taskService.save(task);
            return "redirect:/task";
        } else {
            taskForm.setNewTask(true);
            model.addAttribute("taskForm", taskForm);
            List<Task> list = taskService.findAll();
            model.addAttribute("list", list);//sessionに格納
            model.addAttribute("title", "タスク一覧 afterInsert");
            return "task";
        }
    }

//    //Before UPDATE
//    @GetMapping("/{id}")//編集ページ
//    public ModelAndView showUpdate(
//            @ModelAttribute("taskForm") TaskForm taskForm,
//            @PathVariable Integer id,
//            ModelAndView mav) {
//
//        Optional<TaskForm> form = taskService.getTaskForm(id);
//
//        if (!form.isPresent()) {
//            mav.setViewName("redirect:/task");
//            return mav;
//            //return new ModelAndView("redirect:/test")
//        }
//
//        mav.addObject("taskId", id);
//        mav.addObject("taskForm", form.get());
//        List<Task> list = taskService.findAll();
//        mav.addObject("list", list);
//        mav.addObject("title", "更新フォーム");
//
//        mav.setViewName("task");
//        return mav;
//    }
    
    //Before UPDATE
    @GetMapping("/{id}")//編集ページ
    public String showUpdate(
    	TaskForm taskForm,
        @PathVariable Integer id,
        Model model) {

        Optional<TaskForm> form = taskService.getTaskForm(id);

        if (!form.isPresent()) {
            return "redirect:/task";
        }

        model.addAttribute("taskId", id);
        model.addAttribute("taskForm", form.get());
        List<Task> list = taskService.findAll();
        model.addAttribute("list", list);
        model.addAttribute("title", "更新フォーム");

        return "task";
    }

//    /**
//     * UPDATE
//     * @param id
//     * @param taskForm
//     * @param mav
//     * @return
//     */
//    @PostMapping("/update/{id}")
//    public ModelAndView update(@PathVariable Integer id, @ModelAttribute("taskForm") TaskForm taskForm, ModelAndView mav) {
//        Task task = makeTask(id, taskForm);
//        taskService.save(task);
//        mav.setViewName("redirect:/task/" + id + "/?complete");
//        return mav;
//    }
    
    /**
     * UPDATE
     * @param id
     * @param taskForm
     * @param mav
     * @return
     */
    @PostMapping("/update/{id}")
    public String update(
    	@PathVariable Integer id, 
    	TaskForm taskForm, 
    	Model model) {
    	
        Task task = makeTask(id, taskForm);
        taskService.save(task);
        return "redirect:/task/" + id + "/?complete";
    }


//    /**
//     * DELETE
//     * @param id
//     * @param mav
//     * @return
//     */
//    @PostMapping("/delete/{id}")
//    public ModelAndView delete(@PathVariable Integer id, ModelAndView mav) {
//        taskService.deleteById(id);
//        mav.setViewName("redirect:/task");
//        return mav;
//    }
    
    /**
     * DELETE
     * @param id
     * @param mav
     * @return
     */
    @PostMapping("/delete/{id}")
    public String delete(
    	@PathVariable Integer id,
    	Model model) {
    	
        taskService.deleteById(id);
        return "redirect:/task";
    }

    private Task makeTask(TaskForm taskForm) {
        return new Task(taskForm.getTypeId(), taskForm.getTitle(), taskForm.getDetail(), taskForm.getDeadline());
    }

    private Task makeTask(int id, TaskForm taskForm) {
        return new Task(id, taskForm.getTypeId(), taskForm.getTitle(), taskForm.getDetail(), taskForm.getDeadline());
    }


}
