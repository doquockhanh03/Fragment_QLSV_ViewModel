package com.example.fragment_qlsv
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels


class AddFragment : Fragment() {
    private var edtName: TextView? = null
    private var edtAddress: TextView? = null
    private var edtPhone: TextView? = null
    private var btnSave: Button? = null

    private val viewModel: StudentViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        edtName = view.findViewById(R.id.edt_addName)
        edtAddress = view.findViewById(R.id.edt_addAddress)
        edtPhone = view.findViewById(R.id.edt_addPhone)
        btnSave = view.findViewById(R.id.btn_SaveAdd)

        val index: Int = viewModel.getLastStudent() ?:0
        btnSave?.setOnClickListener{
            val addStudent = Student(
                id = (index.plus(1)),
                name = edtName?.text.toString(),
                address = edtAddress?.text.toString(),
                phone = edtPhone?.text.toString()
            )
            viewModel.addStudent(addStudent)
            requireActivity().supportFragmentManager.popBackStack()
        }
        return view
    }


}