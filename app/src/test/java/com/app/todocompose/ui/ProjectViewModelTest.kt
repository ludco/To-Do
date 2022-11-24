package com.app.todocompose.ui

import com.app.todocompose.fake.FakeDataSource
import com.app.todocompose.fake.FakeLocalProjectRepository
import com.app.todocompose.fake.FakeLocalTaskRepository
import com.app.todocompose.rules.TestDispatcherRule
import com.app.todocompose.ui.viewmodels.ProjectViewModel
import com.app.todocompose.ui.viewmodels.TaskViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ProjectViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun taskViewModel_getProjects_checkSuccess() = runTest {
        val projectViewModel = ProjectViewModel(projectRepository = FakeLocalProjectRepository())
        Assert.assertEquals(
            projectViewModel.projects,
            FakeDataSource.projectList
        )
    }
}