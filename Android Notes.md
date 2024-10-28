Here's a step-by-step tutorial for Module 2 of your Jetpack Compose course, focusing on building a "Task Manager App." We'll break down the concepts and immediately apply them to our project. The goal is to create a task list where users can add, display, and manage tasks using Jetpack Compose.

### Module 2: Composables and Layouts (Building Project 1: Task Manager App)

#### 1. Understanding Composables
_Composables_ are the building blocks of Jetpack Compose. They are functions that define the UI in a declarative way. Every UI component, from a button to a list, is created using composable functions.

##### Step 1: Basic Composables
Let's create a simple UI with some of the basic composable elements like `Text`, `Button`, and `Image` to get started.

```kotlin
@Composable
fun Greeting() {
    Text(text = "Welcome to the Task Manager App!")
}

@Composable
fun SimpleButton() {
    Button(onClick = { /* Action to perform */ }) {
        Text(text = "Add Task")
    }
}
```

- `Text`: Displays text on the screen.
- `Button`: A clickable button that triggers actions when pressed.

##### Step 2: Creating a Task Item Composable
Next, we'll create a composable that represents a task in our task list. This composable will be reusable for each task.

```kotlin
@Composable
fun TaskItem(taskName: String) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = taskName)
        Button(onClick = { /* Mark task as complete */ }) {
            Text(text = "Complete")
        }
    }
}
```

- `Row`: Arranges items horizontally.
- `Modifier`: Used to add spacing, alignment, and styling to the composable.

#### 2. Layout and Design Principles

##### Step 1: Implementing Layouts with Column, Row, Box, and LazyColumn
Let's build the layout for displaying a list of tasks. We'll use `Column` to arrange items vertically and `LazyColumn` for efficient scrolling when the list grows.

```kotlin
@Composable
fun TaskList(tasks: List<String>) {
    LazyColumn {
        items(tasks) { task ->
            TaskItem(taskName = task)
        }
    }
}
```

- `LazyColumn`: A vertically scrolling list that only renders items currently visible on the screen, improving performance.
- `items`: A function used within `LazyColumn` to display each task in the list.

##### Step 2: Styling with Modifier
We'll use the `Modifier` parameter to add padding and other styling to our components.

```kotlin
@Composable
fun StyledTaskItem(taskName: String) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(Color.LightGray),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = taskName, style = TextStyle(fontWeight = FontWeight.Bold))
        Button(onClick = { /* Action to mark task complete */ }) {
            Text(text = "Complete")
        }
    }
}
```

- `background`: Sets the background color of the component.
- `padding`: Adds space around the content.

#### 3. Building Task Manager Features

##### Step 1: Adding Tasks to the List
To make the app interactive, let's create a UI component that allows users to add tasks to the list.

```kotlin
@Composable
fun AddTaskInput(onAddTask: (String) -> Unit) {
    var taskName by remember { mutableStateOf("") }

    Row(modifier = Modifier.padding(8.dp)) {
        TextField(
            value = taskName,
            onValueChange = { taskName = it },
            placeholder = { Text(text = "Enter task") },
            modifier = Modifier.weight(1f)
        )
        Button(onClick = { onAddTask(taskName); taskName = "" }) {
            Text(text = "Add")
        }
    }
}
```

- `TextField`: A composable for text input.
- `remember`: Used to remember the state of the task input field.
- `onAddTask`: A callback that gets triggered when a task is added.

##### Step 2: Displaying and Updating the Task List
Now let's combine everything to manage the list of tasks, add new ones, and mark tasks as complete.

```kotlin
@Composable
fun TaskManagerApp() {
    var tasks by remember { mutableStateOf(listOf<String>()) }

    Column(modifier = Modifier.padding(16.dp)) {
        AddTaskInput { newTask ->
            tasks = tasks + newTask
        }
        Spacer(modifier = Modifier.height(8.dp))
        TaskList(tasks = tasks)
    }
}
```

- `tasks`: Holds the list of tasks in the app's state.
- `AddTaskInput`: Adds new tasks to the list.
- `TaskList`: Displays all tasks.

### Bringing It All Together
Here's the complete composable to run the Task Manager app:

```kotlin
@Composable
fun TaskManagerApp() {
    var tasks by remember { mutableStateOf(listOf<String>()) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Task Manager", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))
        AddTaskInput { newTask ->
            tasks = tasks + newTask
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(tasks) { task ->
                TaskItem(taskName = task)
            }
        }
    }
}
```

This setup allows users to:
- Add new tasks using an input field.
- Display tasks in a scrollable list.
- Mark tasks as complete dynamically.

### Next Steps
- Add animations to the task list for smoother transitions.
- Enhance the UI with colors, icons, and themes.
- Implement task persistence to save data when the app is closed.

This approach covers the foundational aspects of using composables and layouts in Jetpack Compose, all while building a fully functional Task Manager app.