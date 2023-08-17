package com.example.todo.taskPreview


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.example.todo.model.task.Task
import com.example.todo.ui.theme.taskColor
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskItem(
    task: Task,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    onCompleteChecked:(Task) -> Unit,
    onFavoriteClicked: (Task) -> Unit,
    onDeleteClicked:(Task) -> Unit,
    onTaskClick: (Task) -> Unit,
    ) {
    Box(
        modifier = modifier
            .padding(5.dp)
            .then(Modifier.shadow(15.dp))
            .clickable {
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
                    onClick = {
                        onFavoriteClicked(task)
                    },
                    modifier = Modifier.weight(0.15f)
                ) {
                    Icon(
                        imageVector = if (task.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "favorite icon",
                        tint =MaterialTheme.colorScheme.onSurface
                    )
                }
                IconButton(
                    onClick = {
                              onDeleteClicked(task)
                    },
                    modifier = Modifier.weight(0.15f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "delete icon",
                        tint =MaterialTheme.colorScheme.onSurface
                    )
                }

            }
           Row(verticalAlignment = Alignment.CenterVertically,)
           {
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

