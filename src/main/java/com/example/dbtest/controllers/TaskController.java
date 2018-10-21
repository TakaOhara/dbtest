package com.example.dbtest.controllers;

import com.example.dbtest.entity.Task;
import com.example.dbtest.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

//    //INDEX
//    @GetMapping
//    public ModelAndView task(
//            //@ModelAttributeでInstructorFormは自動的にインスタンス化される　
//            //htmlに反映させるにはmav.addObjectが必須
//            @ModelAttribute("taskForm") TaskForm taskForm,
//            ModelAndView mav) {
//
//        //instructorForm.setNewInstructor(true);
//        //mav.addObject("instructorForm", instructorForm);
//        taskForm.setNewTask(true);
//        List<Task> list = taskService.findAll();
//        mav.addObject("taskForm", taskForm);
//        mav.addObject("list", list);
//        mav.addObject("title", "タスク一覧");
//
//        mav.setViewName("task");
//        return mav;
//    }

    // TODO Modelを引数にした場合、自動的にModelAttributeに登録されます
    // TODO POJOの場合、ModelAttributeアノテーションは不要です。名称: TaskForm -> taskForm

    //INDEX
    @GetMapping
    public String task(TaskForm taskForm, Model model) {

        taskForm.setNewTask(true);
        List<Task> list = taskService.findAll();
        model.addAttribute("list", list);
        model.addAttribute("title", "タスク一覧");

        return "task";
    }

    //INSERT
    @PostMapping
    @Transactional(readOnly = false)
    public ModelAndView insert(
            @Validated TaskForm taskForm,//ヴァリデーションはフォームクラスに対して行う
            BindingResult result,
            ModelAndView mav) {

        Task task = makeTask(taskForm);
        //redirect、失敗したらそのままHTML表示
        if (!result.hasErrors()) {
            taskService.save(task);
            mav.setViewName("redirect:/task");
        } else {
            taskForm.setNewTask(true);
            mav.addObject("taskForm", taskForm);
            List<Task> list = taskService.findAll();
            mav.addObject("list", list);
            mav.addObject("title", "タスク一覧 afterInsert");
            mav.setViewName("task");
        }
        return mav;
    }

    //Before UPDATE
    @GetMapping(value = "/{id}")//編集ページ
    public ModelAndView showUpdate(
            @ModelAttribute("taskForm") TaskForm taskForm,
            @PathVariable Integer id,
            ModelAndView mav) {

        Optional<TaskForm> form = taskService.getTaskForm(id);

        if (!form.isPresent()) {
            mav.setViewName("redirect:/task");
            return mav;
            //return new ModelAndView("redirect:/test")
        }

        mav.addObject("taskId", id);
        mav.addObject("taskForm", form.get());
        List<Task> list = taskService.findAll();
        mav.addObject("list", list);
        mav.addObject("title", "更新フォーム");

        mav.setViewName("task");
        return mav;
    }

    /**
     * UPDATE
     * @param id
     * @param taskForm
     * @param mav
     * @return
     */
    @PostMapping("/update/{id}")
    @Transactional(readOnly = false)
    public ModelAndView update(@PathVariable Integer id, @ModelAttribute("taskForm") TaskForm taskForm, ModelAndView mav) {
        Task task = makeTask(id, taskForm);
        taskService.save(task);
        mav.setViewName("redirect:/task/" + id + "/?complete");
        return mav;
    }


    /**
     * DELETE
     * @param id
     * @param mav
     * @return
     */
    @PostMapping("/delete/{id}")
    @Transactional(readOnly = false)//削除の場合必須
    public ModelAndView delete(@PathVariable Integer id, ModelAndView mav) {
        taskService.deleteById(id);
        mav.setViewName("redirect:/task");
        return mav;
    }

    private Task makeTask(TaskForm taskForm) {
        return new Task(taskForm.getTypeId(), taskForm.getTitle(), taskForm.getDetail(), taskForm.getDeadline());
    }

    private Task makeTask(int id, TaskForm taskForm) {
        return new Task(id, taskForm.getTypeId(), taskForm.getTitle(), taskForm.getDetail(), taskForm.getDeadline());
    }


}
