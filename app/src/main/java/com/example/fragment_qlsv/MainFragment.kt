package com.example.fragment_qlsv

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModel

class MainFragment : Fragment() {

    private var imgAdd: ImageView? = null
    private var tvAdd: TextView? = null
    private var rcvStudent: RecyclerView? = null
    private val viewModel: StudentViewModel by activityViewModels()
    private var recyclerViewAdapter: StudentAdapter? = null
    private var imgDelete: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("CommitTransaction", "NotifyDataSetChanged", "CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        tvAdd = view?.findViewById(R.id.tv_add)
        imgAdd = view?.findViewById(R.id.img_add)
        rcvStudent = view?.findViewById(R.id.rcv_sinhvien)
        imgDelete = view?.findViewById(R.id.img_delete)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rcv_sinhvien)
        recyclerViewAdapter = StudentAdapter(requireContext(), listOf()){ student, i ->
            viewModel.setStudent(student) //Truyen du lieu vao ViewModel

            //Khoi tao EditFragment va dung no thay the fragment hien tai
            val editFragment = EditFragment()
            requireActivity().supportFragmentManager.beginTransaction().
            replace(R.id.fragment_container, editFragment).
            addToBackStack(null).
            commit()
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerViewAdapter

        viewModel.students.observe(viewLifecycleOwner) { students ->
            recyclerViewAdapter?.updateData(students) // Cập nhật adapter với danh sách sinh viên mới
        }

        tvAdd?.setOnClickListener{
            val addFragment = AddFragment()
            requireActivity().supportFragmentManager.beginTransaction().
            replace(R.id.fragment_container, addFragment).
            addToBackStack(null).
            commit()

        }

        imgAdd?.setOnClickListener{
            val addFragment = AddFragment()
            requireActivity().supportFragmentManager.beginTransaction().
            replace(R.id.fragment_container, addFragment).
            addToBackStack(null).
            commit()
        }

        //Dang bi xung dot voi su kien nhap de sua item
//        imgDelete?.setOnClickListener{
//            val selectStudent = viewModel.getSelectStudent()
//            if (selectStudent != null) {
//                viewModel.deleteStudent(selectStudent)
//            }
//        }
        return view
    }

}