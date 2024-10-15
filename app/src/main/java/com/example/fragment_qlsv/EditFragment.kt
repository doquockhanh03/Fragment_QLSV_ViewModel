package com.example.fragment_qlsv

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.newFixedThreadPoolContext


class EditFragment : Fragment() {
    private var edtName: EditText? = null
    private var edtAddress: EditText? = null
    private var edtPhone: EditText? = null
    private var btnSave: Button? = null
    private var currentID: Int? = null

    private val viewModel: StudentViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit, container, false)
        edtPhone = view?.findViewById(R.id.edt_editPhone)
        edtName = view?.findViewById(R.id.edt_editName)
        edtAddress = view?.findViewById(R.id.edt_editAddress)
        btnSave = view?.findViewById(R.id.btn_SaveEdit)

        //Nhan du lieu tu ViewModel
        viewModel.student.observe(viewLifecycleOwner){student ->
            currentID = student.id
            edtName?.setText(student.name)
            edtAddress?.setText(student.address)
            edtPhone?.setText(student.phone)
        }

        btnSave?.setOnClickListener{
            val updateStudent = Student(
                id = currentID ?: 0,
                name = edtName?.text.toString(),
                address = edtAddress?.text.toString(),
                phone = edtPhone?.text.toString(),
            )

            viewModel.updateStudent(updateStudent)
            requireActivity().supportFragmentManager.popBackStack()
        }
        return view
    }
}