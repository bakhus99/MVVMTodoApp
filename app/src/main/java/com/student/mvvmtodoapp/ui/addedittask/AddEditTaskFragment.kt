package com.student.mvvmtodoapp.ui.addedittask

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.student.mvvmtodoapp.R
import com.student.mvvmtodoapp.databinding.FragmentDetailTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditTaskFragment : Fragment(R.layout.fragment_detail_task) {

    private val viewModel: AddEditTaskViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailTaskBinding.bind(view)

        binding.apply {
            etTaskName.setText(viewModel.taskName)
            checkBoxImportant.isChecked = viewModel.taskImportance
            checkBoxImportant.jumpDrawablesToCurrentState()
            tvDataCreated.isVisible = viewModel.task != null
            tvDataCreated.text = "Created: ${viewModel.task?.createdDateFormatted}"
        }
    }

}