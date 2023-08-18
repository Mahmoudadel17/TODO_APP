package com.example.todo.taskPreview


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.todo.commonComponents.AppContext
import com.example.todo.model.MyDatabase
import com.example.todo.model.task.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.time.LocalDateTime

class TasksScreenViewModel :ViewModel(){

    private val database = Room.databaseBuilder(
        AppContext.getAppContext(),
        MyDatabase::class.java,
        "MyDatabase"
    ).build()

    private val taskDao = database.taskDao()

    private  val _tasks:MutableStateFlow<List<Task>> = MutableStateFlow(emptyList())
    val tasks = _tasks.asStateFlow()

    private val _isChange = MutableStateFlow(false)
    val isChange: StateFlow<Boolean> = _isChange.asStateFlow()

    private val _showDialog  = MutableStateFlow(false)
    val showDialog : StateFlow<Boolean> = _showDialog .asStateFlow()
    init {
//        repeat(5){
//            addTask(Task(title = "title $it", content = "this content for task $it", isFavorite = false, isComplete = false,))
//            addTask(Task(title = "title $it", content = "this content for task $it", isFavorite = false, isComplete = true,))
//
//        }
       viewModelScope.launch(Dispatchers.IO) {
           getAllTasks()
       }
    }

    private fun getAllTasks(){
        _isChange.value = _isChange.value.not()
        viewModelScope.launch (Dispatchers.IO){
            val list = taskDao.getAllTasks()
            _tasks.value = list.toMutableList()
        }
    }

    fun formatDate(): String {
        val date = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d", Locale.ENGLISH)
        return date.format(formatter)
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

    fun onShowDialogDelete(){
        _showDialog.value = true
    }
    fun onDismissDialogDelete(){
        _showDialog.value = false
    }

    fun onTimeChange(task: Task,hour:Int,minute:Int){
        val newDueDate : LocalDateTime? =
            task.dueDate?.let {
                LocalDateTime.of(it.year, it.month, it.dayOfMonth, hour, minute)
            }
        task.dueDate = newDueDate
        viewModelScope.launch(Dispatchers.IO){
            taskDao.updateTask(task)
            getAllTasks()
        }
    }
    fun onDateChange(task: Task,year:Int,month:Int,day:Int){
        val newDueDate : LocalDateTime? =
            task.dueDate?.let {
                LocalDateTime.of(year,month,day,it.hour,it.minute)
            }
        task.dueDate = newDueDate
        viewModelScope.launch(Dispatchers.IO){
            taskDao.updateTask(task)
            getAllTasks()
        }
    }



}