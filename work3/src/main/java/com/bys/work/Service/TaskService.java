package com.bys.work.Service;

import com.bys.work.mapper.TaskMapper;
import com.bys.work.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackageName:com.bys.work.Service
 * @ClassName:UserService
 * @Description
 * @Author: yushengbi
 * @Date:2020/3/22 13:06
 */
@Service
public class TaskService {

    @Autowired
    TaskMapper taskMapper;


    public List<Task> getAllTasks() {
        return taskMapper.getAllTasks();
    }

    public Task getTaskById(Integer id) {
        return taskMapper.selectByPrimaryKey(id);
    }

    public Integer addTask(Task task) {
        return taskMapper.insertSelective(task);
    }

    public Integer deleteTaskById(Integer id) {
        return taskMapper.deleteByPrimaryKey(id);
    }
}
