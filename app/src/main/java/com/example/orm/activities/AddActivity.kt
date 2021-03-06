package com.example.orm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.orm.R
import com.example.orm.Student
import com.example.orm.room.StudentDatabase
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddActivity : AppCompatActivity() {
    var mDb: StudentDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        mDb = StudentDatabase.getInstance(this)

        btnSave.setOnClickListener {
            val objectStudent = Student(
                null,
                etNamaStudent.text.toString(),
                etEmailStudent.text.toString()
            )

            GlobalScope.async {
                val result = mDb?.studentDao()?.insertStudent(objectStudent)
                runOnUiThread {
                    if(result != 0.toLong() ){
                        //sukses
                        Toast.makeText(this@AddActivity,"Sukses menambahkan ${objectStudent.nama}",Toast.LENGTH_LONG).show()
                    }else{
                        //gagal
                        Toast.makeText(this@AddActivity,"Gagal menambahkan ${objectStudent.nama}",Toast.LENGTH_LONG).show()
                    }
                    finish()
                }
            }
        }
    }
}