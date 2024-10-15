package com.example.fragment_qlsv

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {
    private val _studentViewModel = MutableLiveData<Student>()
    val student: LiveData<Student> get() = _studentViewModel

    private val _studentsLiveData = MutableLiveData<List<Student>>()
    val students: LiveData<List<Student>> get() = _studentsLiveData

    private val studentList: MutableList<Student> = mutableListOf()

    init {
        // Khởi tạo danh sách sinh viên mẫu
        studentList.addAll(
            listOf(
                Student(1, "Do Quoc Khanh", "Tuyen Quang", "0862839423"),
                Student(2, "Nguyen Van A", "Ha Noi", "0912345678"),
                Student(3, "Le Thi B", "Da Nang", "0987654321"),
                Student(4, "Truong Minh C", "HCM", "0123456789")
            )
        )
        _studentsLiveData.value = studentList // Cập nhật LiveData với danh sách sinh viên
    }

    fun getSelectStudent(): Student? {
        return _studentViewModel.value
    }

    fun getLastStudent(): Int?{
        return studentList.lastOrNull()?.id
    }

    fun setStudent(student: Student) {
        _studentViewModel.value = student
    }

    fun addStudent(student: Student) {
        studentList.add(student)
        _studentsLiveData.value = studentList // Cập nhật LiveData khi thêm sinh viên
        Log.e("","name = " + student.name )
        Log.e("","address = " + student.address )
        Log.e("","id = " + student.id )
        Log.e("","phone = " + student.phone )
    }

    fun updateStudent(updatedStudent: Student) {
        val index = studentList.indexOfFirst { it.id == updatedStudent.id }
        if (index != -1) {
            studentList[index] = updatedStudent
            _studentsLiveData.value = studentList // Cập nhật LiveData khi sửa sinh viên
        }
        Log.e("StudentViewModel", "Updated student: ${updatedStudent.name}")
    }

    fun deleteStudent(deleteStudent: Student){
        val index = studentList.indexOfFirst { it.id == deleteStudent.id}
        if(index != -1){
            studentList.removeAt(index)
            _studentsLiveData.value = studentList
        }
    }


}