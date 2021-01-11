package com.student.mvvmtodoapp.ui.deleteallcomplited

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.student.mvvmtodoapp.data.TaskDao
import com.student.mvvmtodoapp.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DeleteAllCompletedViewModel @ViewModelInject constructor(
    private val taskDao: TaskDao,
    @ApplicationScope private val applicationScope: CoroutineScope
) : ViewModel() {

    fun onConfirmClick() = applicationScope.launch {
        taskDao.deleteCompletedTask()
    }
}