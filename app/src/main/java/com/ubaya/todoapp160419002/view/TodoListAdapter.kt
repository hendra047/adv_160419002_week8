package com.ubaya.todoapp160419002.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.todoapp160419002.R
import com.ubaya.todoapp160419002.model.Todo
import kotlinx.android.synthetic.main.layout_item_todo.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick: (Todo) -> Unit)
    :RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    class TodoViewHolder(var view:View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position]
        with(holder.view) {
            checkTask.text = todo.title
            checkTask.isChecked = false
            checkTask.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) adapterOnClick(todo)
            }
            buttonEdit.setOnClickListener {
                val action = TodoListFragmentDirections.actionEditTodoFragment(todo.uuid)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount() = todoList.size

    fun updateTodoList(newTodolist: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodolist)
        notifyDataSetChanged()
    }
}