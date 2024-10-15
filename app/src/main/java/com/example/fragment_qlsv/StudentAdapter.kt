package com.example.fragment_qlsv

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private var context: Context,
                     private var studentList: List<Student>,
                     private var onItemClick: (Student, Int) -> Unit
): RecyclerView.Adapter<StudentAdapter.ItemHolder>() {

    class ItemHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var name: TextView = itemView.findViewById(R.id.tv_name)
        var address: TextView = itemView.findViewById(R.id.tv_address)
        var phone: TextView = itemView.findViewById(R.id.tv_phone)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val Index = studentList[position]
        holder.name.text = Index.name
        holder.address.text = Index.address
        holder.phone.text = Index.phone

        holder.itemView.setOnClickListener{
            onItemClick.invoke(Index, position)
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newStudent: List<Student>){
        studentList = newStudent
        notifyDataSetChanged()
    }
}