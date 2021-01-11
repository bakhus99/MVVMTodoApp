package com.student.mvvmtodoapp.ui.addedittask

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.student.mvvmtodoapp.R
import com.student.mvvmtodoapp.databinding.FragmentDetailTaskBinding
import com.student.mvvmtodoapp.util.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

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

            etTaskName.addTextChangedListener {
                viewModel.taskName = it.toString()
            }

            checkBoxImportant.setOnCheckedChangeListener { _, isChecked ->
                viewModel.taskImportance = isChecked
            }

            fabSaveTask.setOnClickListener {
                viewModel.onSaveClick()
            }
        }
     viewLifecycleOwner.lifecycleScope.launchWhenStarted {
         viewModel.addEditEvent.collect { event ->
             when (event){
                 is AddEditTaskViewModel.AddEditEvent.ShowInvalidInputMessage -> {
                     Snackbar.make(requireView(),event.msg,Snackbar.LENGTH_LONG).show()
                 }
                 is AddEditTaskViewModel.AddEditEvent.NavigateBackWithResult -> {
                     binding.etTaskName.clearFocus()
                     setFragmentResult(
                         "add_edit_request",
                         bundleOf("add_edit_result" to event.result)
                     )
                     findNavController().popBackStack()
                 }
             }.exhaustive
         }
     }
    }
}