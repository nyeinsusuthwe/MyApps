package it.jgiem.myapps.todoList;

public interface TodoListener {
    void onEditTodo(Todo todo);
    void onDeleteTodo(int id);
}
