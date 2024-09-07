package it.jgiem.myapps.todoList;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.jgiem.myapps.databinding.ActivityMainTodoBinding;
import it.jgiem.myapps.databinding.DialogTodoBinding;


public class MainActivityTodoList extends AppCompatActivity {

    private ActivityMainTodoBinding binding;
    private TodoAdapter todoAdapter;
    private List<Todo> todoList;
    private AlertDialog addTodoDialog;
    private DialogTodoBinding dialogTodoBinding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainTodoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initRecyclerView();
        initListeners();
        initTodoDialog();
    }

    private void initTodoDialog() {
        dialogTodoBinding = DialogTodoBinding.inflate(getLayoutInflater());

        addTodoDialog = new AlertDialog.Builder(this)
                .setTitle("Add Todo Dialog")
                .setView(dialogTodoBinding.getRoot())
                .setPositiveButton("ADD", (dialog, which) ->{
                    
                    if(dialogTodoBinding.etTitle.getTag() == null){
                        //ADD
                        String title = dialogTodoBinding.etTitle.getText().toString();
                        if(!title.isBlank()){
                            todoList.add(new Todo(new Random().nextInt(), title, dialogTodoBinding.cbCompleted.isChecked()));
                            todoAdapter.setTodoList(todoList);
                            dialog.cancel();
                        } else {
                            Toast.makeText(this, "Cannot add", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //Update
                        int id = (int) dialogTodoBinding.etTitle.getTag();
                        String title = dialogTodoBinding.etTitle.getText().toString();
                        if(!title.isBlank()){
                            var existingTodo = todoList.stream().filter(td->td.getId()==id).findFirst().get();
                            var updatedTodo = new Todo(id, title, dialogTodoBinding.cbCompleted.isChecked());
                            if(!existingTodo.equals(updatedTodo)){
                                var index = todoList.indexOf(existingTodo);
                                todoList.set(index, updatedTodo);
                                todoAdapter.setTodoList(todoList);
                                dialog.cancel();
                            } else{
                                Toast.makeText(this, "Same Todo", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(this, "Cannot Update", Toast.LENGTH_SHORT).show();
                        }
                    }
                    
                    
                    
                })
                .setNegativeButton("CANCLE", (dialog, which) ->{
                    dialog.cancel();
                })
                .create();
    }

    private void initListeners() {
        binding.fabAdd.setOnClickListener(v->{
            Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show();
            showAddTodoDialog();
        });
    }

    private void showAddTodoDialog() {
        addTodoDialog.show();
    }

    private void initRecyclerView() {
        todoList = new ArrayList<>(List.of(new Todo(new Random().nextInt(), "Eat Breakfast", false)));
        todoAdapter= new TodoAdapter(todoList, new TodoListener() {
            @Override
            public void onEditTodo(Todo todo) {
                showAddTodoDialog(todo);
            }

            @Override
            public void onDeleteTodo(int id) {
                todoList.removeIf(todo->todo.getId() == id);
                todoAdapter.setTodoList(todoList);
            }
        });
        //todoAdapter = new TodoAdapter(List.of(new Todo(new Random().nextInt(), "Eat Breakfast", false)));
        binding.rvTodo.setAdapter(todoAdapter);
        binding.rvTodo.setLayoutManager(new LinearLayoutManager(this));
    }

    private void showAddTodoDialog(Todo todo){
        dialogTodoBinding.etTitle.setTag(todo.getId());
        dialogTodoBinding.etTitle.setText(todo.getTitle());
        dialogTodoBinding.cbCompleted.setChecked(todo.isCompleted());
        addTodoDialog.show();
    }
}