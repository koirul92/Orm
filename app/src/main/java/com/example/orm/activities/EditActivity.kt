package com.example.orm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.orm.R
import com.example.orm.Student
import com.example.orm.room.StudentDatabase
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@DelicateCoroutinesApi
class EditActivity : AppCompatActivity() {
    var mDb: StudentDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        mDb = StudentDatabase.getInstance(this)

        val objectStudent = intent.getParcelableExtra<Student>("student")

        if (objectStudent != null) {
            etNamaStudent.setText(objectStudent.nama)
        }
        if (objectStudent != null) {
            etEmailStudent.setText(objectStudent.email)
        }

        btnSave.setOnClickListener {
            if (objectStudent != null) {
                objectStudent.nama = etNamaStudent.text.toString()
            }
            if (objectStudent != null) {
                objectStudent.email = etEmailStudent.text.toString()
            }

            GlobalScope.async {
                val result = objectStudent?.let { it1 -> mDb?.studentDao()?.updateStudent(it1) }

                runOnUiThread {
                    if(result!=0){
                        if (objectStudent != null) {
                            Toast.makeText(this@EditActivity,"Sukses mengubah ${objectStudent.nama}", Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(this@EditActivity,"Gagal mengubah ${objectStudent.nama}", Toast.LENGTH_LONG).show()
                    }

                    finish()
                }
            }
        }
    }
}