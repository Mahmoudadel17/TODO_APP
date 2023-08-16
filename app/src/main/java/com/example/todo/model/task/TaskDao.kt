package com.example.todo.model.task

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    @Query("SELECT * FROM Tasks_table")
    fun getAllTasks(): List<Task>

    @Query("SELECT * FROM tasks_table WHERE id = :id")
    fun getTaskByID(id:Int) : Task

    @Insert
    fun addTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)
}