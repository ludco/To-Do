package com.app.todocompose.domain.task

import androidx.room.*
import com.app.todocompose.domain.project.Project

@Entity(
    foreignKeys = [ForeignKey(
        entity = Project::class,
        parentColumns = ["id"],
        childColumns = ["projectId"]
    )],
    indices = [Index(value = arrayOf("name"), unique = true)],
)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(index = true)
    val projectId: Long,
    val name: String
)
