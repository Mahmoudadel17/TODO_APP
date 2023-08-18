package com.example.todo.model.task

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.Nullable
import java.time.LocalDateTime

@Entity(tableName = "Tasks_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title:String,
    var content:String,
    var isComplete:Boolean,
    var isFavorite:Boolean,
    @Nullable
    var dueDate: LocalDateTime? = LocalDateTime.now()
)


