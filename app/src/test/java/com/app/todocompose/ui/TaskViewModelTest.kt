package com.app.todocompose.ui

import com.app.todocompose.domain.task.Task
import com.app.todocompose.fake.FakeDataSource
import com.app.todocompose.fake.FakeLocalTaskRepository
import com.app.todocompose.rules.TestDispatcherRule
import com.app.todocompose.ui.viewmodels.TaskUiState
import com.app.todocompose.ui.viewmodels.TaskViewModel
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TaskViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun taskViewModel_getTasks_checkSuccess() = runTest {
        val taskViewModel = TaskViewModel(taskRepository = FakeLocalTaskRepository())
        assertEquals(
            TaskUiState.Success(FakeDataSource.tasksList),
            taskViewModel.taskUiState
        )
    }

}