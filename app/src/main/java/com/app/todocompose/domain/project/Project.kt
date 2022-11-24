package com.app.todocompose.domain.project

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Project(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String
)