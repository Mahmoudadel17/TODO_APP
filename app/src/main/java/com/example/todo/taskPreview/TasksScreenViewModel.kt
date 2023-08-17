package com.example.todo.taskPreview

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.todo.commonComponents.AppContext
import com.example.todo.model.MyDatabase
import com.example.todo.model.task.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TasksScreenViewModel :ViewModel(){

    private val database = Room.databaseBuilder(
        AppContext.getAppContext(),
        MyDatabase::class.java,
        "MyDatabase"
    ).build()

    private val taskDao = database.taskDao()

    private  val _tasks:MutableStateFlow<List<Task>> = MutableStateFlow(emptyList())
    val tasks = _tasks.asStateFlow()

    init {
        getAllTasks()
    }

    private fun getAllTasks(){
        viewModelScope.launch (Dispatchers.IO){
            val list = taskDao.getAllTasks()
            _tasks.value = list
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch (Dispatchers.IO){
            taskDao.deleteTask(task)
            getAllTasks()
        }
    }


    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            taskDao.addTask(task)
            getAllTasks()
        }
    }

    fun onTaskComplete(task: Task){
        task.isComplete = task.isComplete.not()
        viewModelScope.launch (Dispatchers.IO){
            taskDao.updateTask(task)
            getAllTasks()
        }
    }
    fun onTaskFavorite(task: Task){
        task.isFavorite = task.isFavorite.not()
        viewModelScope.launch (Dispatchers.IO){
            taskDao.updateTask(task)
            getAllTasks()
        }
    }

    fun onTaskDelete(task: Task){
        viewModelScope.launch (Dispatchers.IO){
            taskDao.deleteTask(task)
            getAllTasks()
        }
    }




}