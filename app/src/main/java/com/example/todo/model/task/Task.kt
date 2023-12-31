package com.example.todo.model.task

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Tasks_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title:String,
    var content:String,
    var isComplete:Boolean = false,
    var isFavorite:Boolean = false,
    var dueDate: LocalDateTime
)


