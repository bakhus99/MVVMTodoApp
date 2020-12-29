package com.student.mvvmtodoapp.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.student.mvvmtodoapp.data.TaskDao

class TasksViewModel @ViewModelInject constructor(
    private val  taskDao: TaskDao
) : ViewModel() {

    val tasks = taskDao.getTask().asLiveData()

}