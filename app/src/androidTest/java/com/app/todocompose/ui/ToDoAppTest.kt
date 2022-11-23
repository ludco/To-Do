package com.app.todocompose.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ToDoAppTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setupFormScreen() {
        composeTestRule.setContent {
            ToDoApp()
        }
    }

    @Test
    fun toDoApp_onFABClicked_showAddTaskDialog() {
        composeTestRule.onNodeWithTag("test-FAB").performClick()
        composeTestRule.onNodeWithText("Add a task").assertIsDisplayed()
    }

    @Test
    fun homeScreen_taskListIsEmpty_showAllDoneScreen() {
        composeTestRule.onNodeWithText("All done !").assertIsDisplayed()
    }

    @Test
    fun homeScreen_taskListContainOneTask_showTasksListScreen() {
        addTaskToTasksList()
        composeTestRule.onNodeWithText("Do something").assertIsDisplayed()
    }

    @Test
    fun homeScreen_removeTheOneTaskFromTasksList_showAllDoneScreen() {
        addTaskToTasksList()
        composeTestRule.onNodeWithContentDescription("Delete").performClick()
        composeTestRule.onNodeWithText("All done !").assertIsDisplayed()
    }

    private fun addTaskToTasksList() {
        composeTestRule.onNodeWithTag("test-FAB").performClick()
        composeTestRule.onNodeWithText("Task name").performTextInput("Do something")
        composeTestRule.onNodeWithText("OK").performClick()

    }
}