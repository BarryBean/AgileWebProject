package com.bys.work.controller;

import com.bys.work.Service.TaskService;
import com.bys.work.model.RespBean;
import com.bys.work.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @PackageName:com.bys.work.controller
 * @ClassName:UserController
 * @Description
 * @Author: yushengbi
 * @Date:2020/3/22 13:03
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable("id") Integer id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/")
    public RespBean addTask(@RequestBody Task task) {
        if (taskService.addTask(task) == 1) {
            return RespBean.ok("新增成功");
        }
        return RespBean.error("新增失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteTask(@PathVariable("id") Integer id) {
        if (taskService.deleteTaskById(id) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
