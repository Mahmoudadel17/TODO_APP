package com.example.todo.commonComponents


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.example.todo.model.task.Task
import com.example.todo.ui.theme.taskColor
import java.time.format.DateTimeFormatter

val myTask = Task(title = "title nnnnnnnnnnnnnnnnnnnnnnnnnn", content = "content", isComplete = true, isFavorite = true, isOpen = true)

val myList = listOf(
    Task(
        title = "title nnnnnnnnnnnnnnnnnnnnnnnnnn",
        content = "content zxlkfvnaslk nlskfn lksnd ldkfn lknflkn lsdkfn ",
        isComplete = true,
        isFavorite = true,
        isOpen = true),
    Task(
        title = "title",
        content = "content zxlkfvnaslk nlskfn lksnd ldkfn lknflkn lsdkfn ",
        isComplete = true,
        isFavorite = true,
        isOpen = false),
    Task(
        title = "title",
        content = "content zxlkfvnaslk nlskfn lksnd ldkfn lknflkn lsdkfn ",
        isComplete = true,
        isFavorite = true,
        isOpen = false),
    Task(
        title = "title",
        content = "content zxlkfvnaslk nlskfn lksnd ldkfn lknflkn lsdkfn ",
        isComplete = true,
        isFavorite = true,
        isOpen = true),
    Task(
        title = "title",
        content = "content zxlkfvnaslk nlskfn lksnd ldkfn lknflkn lsdkfn ",
        isComplete = true,
        isFavorite = true,
        isOpen = true),
    Task(
        title = "title",
        content = "content zxlkfvnaslk nlskfn lksnd ldkfn lknflkn lsdkfn ",
        isComplete = true,
        isFavorite = true,
        isOpen = false),
    Task(
        title = "title",
        content = "content zxlkfvnaslk nlskfn lksnd ldkfn lknflkn lsdkfn ",
        isComplete = true,
        isFavorite = true,
        isOpen = false),
    Task(
        title = "title",
        content = "content zxlkfvnaslk nlskfn lksnd ldkfn lknflkn lsdkfn ",
        isComplete = true,
        isFavorite = true,
        isOpen = false),


)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListTasks() {
    LazyColumn{
        items(myList){
            NoteItem(it)
        }
    }
}

@Composable
fun NoteItem(
    task: Task = myTask,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    onTaskClick: (Task) -> Unit = {},
    onMoreMenuClick: (Task) -> Unit = {},
    onCompleteChecked: (Task) -> Unit = {},

) {
    var c by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .padding(5.dp)
            .then(Modifier.shadow(15.dp))
            .clickable {
                c = c.not()
                task.isOpen = task.isOpen.not()
                onTaskClick(task)
            }

    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val clipPath = Path().apply {
                lineTo(size.width - cutCornerSize.toPx(), 0f)
                lineTo(size.width, cutCornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            clipPath(clipPath) {
                drawRoundRect(
                    color = Color(taskColor.toArgb()),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(taskColor.toArgb(), 0x000000, 0.2f)
                    ),
                    topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                    size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }
        Column (
            modifier = Modifier
                .padding(16.dp)
                .padding(end = 32.dp)
                .fillMaxWidth(),
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = task.isComplete,
                    onCheckedChange =
                    { onCompleteChecked(task) },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.onSurface,
                        uncheckedColor = MaterialTheme.colorScheme.onSurface,
                        checkmarkColor = MaterialTheme.colorScheme.background,
                    ),
                    modifier = Modifier.weight(0.15f)
                )

                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(0.70f)
                )

                IconButton(
                    onClick = { onMoreMenuClick(task) },
                    modifier = Modifier.weight(0.15f)
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "menu icon",
                        tint =MaterialTheme.colorScheme.onSurface
                    )
                }

            }
           Row(verticalAlignment = Alignment.CenterVertically,)
           {

               if (c){
                   Spacer(modifier = Modifier.width(7.dp))

                   Text(
                       text = task.content,
                       style = MaterialTheme.typography.bodyMedium,
                       color = MaterialTheme.colorScheme.onSurface,
                       maxLines = 10,
                       overflow = TextOverflow.Ellipsis,
                   )
               }else{
                   if (task.dueDate != null) {
                       Spacer(modifier = Modifier.width(7.dp))
                       Icon(
                           Icons.Filled.DateRange,
                           contentDescription = "DueDate",
                           tint =  MaterialTheme.colorScheme.onSurface,
                           )
                       Spacer(modifier = Modifier.width(5.dp))
                       Text(text = task.dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                           color = MaterialTheme.colorScheme.onSurface)
                   }
               }
           }

        }


    }

}





//
//
//
//@Composable
//fun NoteItemSelected(
//    task: Task,
//    modifier: Modifier = Modifier,
//    cornerRadius: Dp = 10.dp,
//    cutCornerSize: Dp = 30.dp,
//    onDeleteClick: () -> Unit = {}
//) {
//    Box(
//        modifier = modifier
//    ) {
//        Canvas(modifier = Modifier.matchParentSize()) {
//            val clipPath = Path().apply {
//                lineTo(size.width - cutCornerSize.toPx(), 0f)
//                lineTo(size.width, cutCornerSize.toPx())
//                lineTo(size.width, size.height)
//                lineTo(0f, size.height)
//                close()
//            }
//
//            clipPath(clipPath) {
//                drawRoundRect(
//                    color = Color(taskColor.toArgb()),
//                    size = size,
//                    cornerRadius = CornerRadius(cornerRadius.toPx())
//                )
//                drawRoundRect(
//                    color = Color(
//                        ColorUtils.blendARGB(taskColor.toArgb(), 0x000000, 0.2f)
//                    ),
//                    topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
//                    size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
//                    cornerRadius = CornerRadius(cornerRadius.toPx())
//                )
//            }
//        }
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//                .padding(end = 32.dp)
//        ) {
//            Text(
//                text = task.title,
//                style = MaterialTheme.typography.h6,
//                color = MaterialTheme.colors.onSurface,
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = task.content,
//                style = MaterialTheme.typography.body1,
//                color = MaterialTheme.colors.onSurface,
//                maxLines = 10,
//                overflow = TextOverflow.Ellipsis
//            )
//        }
//        IconButton(
//            onClick = onDeleteClick,
//            modifier = Modifier.align(Alignment.BottomEnd)
//        ) {
//            Icon(
//                imageVector = Icons.Default.Delete,
//                contentDescription = "Delete note",
//                tint = MaterialTheme.colors.onSurface
//            )
//        }
//    }
//}