package com.example.todo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo.ui.theme.TodoTheme
import com.example.todo.ui.viewmodel.TodoViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import com.example.todo.ui.model.Todo
import com.example.todo.ui.viewmodel.TodoUIState


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoTheme {
                TodoApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp(todoViewModel: TodoViewModel = viewModel()){
    TodoScreen(uiState = todoViewModel.todoUIState)
    Scaffold (
        topBar = {
            TopAppBar(title = { Text("todos") })
        })
       { paddingValues ->
           TodoScreen(
               uiState = todoViewModel.todoUIState,
           )
    }
}

@Composable
fun TodoScreen(uiState: TodoUIState){
    when (uiState) {
        is TodoUIState.Loading -> LoadingScreen()
        is TodoUIState.Error -> ErrorScreen()
        is TodoUIState.Success -> TodoList(uiState.todos)
    }
}

@Composable
fun TodoList(todos: List<Todo>){
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        items(todos) { todo ->
            Text(
                text = todo.title,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
        }
    }
}

@Composable
fun LoadingScreen(){
    Text("Loading...")
}

@Composable
fun ErrorScreen(){
    Text("Error handling the data...")
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoTheme {
        TodoApp()
    }
}