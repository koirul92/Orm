package com.example.orm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orm.R
import com.example.orm.StudentAdapter
import com.example.orm.room.StudentDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var mDB : StudentDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mDB = StudentDatabase.getInstance(this)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        fetchData()

        fabAdd.setOnClickListener {
            val keActivityAdd = Intent(this, AddActivity::class.java)
            startActivity(keActivityAdd)
        }
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    fun fetchData(){
        GlobalScope.launch {
            val listStudent = mDB?.studentDao()?.getAllStudent()

            runOnUiThread{
                listStudent?.let {
                    val adapter = StudentAdapter(it)
                    recyclerView.adapter = adapter
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        StudentDatabase.destroyInstance()
    }
}