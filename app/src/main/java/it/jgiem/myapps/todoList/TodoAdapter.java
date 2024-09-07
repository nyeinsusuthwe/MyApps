package it.jgiem.myapps.todoList;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.jgiem.myapps.databinding.AdapterTodoBinding;


public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<Todo> todoList;
    private TodoListener todoListener;

    public TodoAdapter(List<Todo> todoList, TodoListener todoListener){
        this.todoList = todoList;
        this.todoListener = todoListener;
    }

    public void setTodoList(List<Todo> todoList){
        this.todoList = todoList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoViewHolder(AdapterTodoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todoList.get(position);
        holder.getBinding().cbTodo.setText(todo.getTitle());
        holder.getBinding().cbTodo.setChecked(todo.isCompleted());
        holder.getBinding().ibDelete.setOnClickListener(v->{
            todoListener.onDeleteTodo(todo.getId());
        });
        holder.getBinding().ibEdit.setOnClickListener(v->{
            todoListener.onEditTodo(todo);
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder{
        private AdapterTodoBinding binding;
        public TodoViewHolder(AdapterTodoBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public AdapterTodoBinding getBinding(){
            return  binding;
        }
    }
}
