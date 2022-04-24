package com.ubaya.todoapp160419002.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.todoapp160419002.R
import com.ubaya.todoapp160419002.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*

/**
 * A simple [Fragment] subclass.
 * Use the [EditTodoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditTodoFragment : Fragment() {
    private lateinit var viewModel: DetailTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textTitleLayout.text = "Edit Todo"
        buttonAdd.text = "Save Changes"

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        observeViewModel()

        buttonAdd.setOnClickListener {
            val radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
            viewModel.update(editTitle.text.toString(), editNotes.text.toString(), radio.tag.toString().toInt(), uuid)

            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
    }

    private fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            it?.let { todo ->
                editTitle.setText(todo.title)
                editNotes.setText(todo.notes)

                when(todo.priority) {
                    1 -> radioLow.isChecked = true
                    2 -> radioMedium.isChecked = true
                    else -> radioHigh.isChecked = true
                }
            }
        })
    }
}