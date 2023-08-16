package com.example.todo.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todo.model.task.LocalDateTimeConverter
import com.example.todo.model.task.Task
import com.example.todo.model.task.TaskDao

@Database(entities = [Task::class], version = 1)
@TypeConverters(LocalDateTimeConverter::class)
abstract class MyDatabase :RoomDatabase() {
    abstract fun taskDao():TaskDao
}