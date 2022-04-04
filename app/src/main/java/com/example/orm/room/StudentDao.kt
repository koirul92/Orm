package com.example.orm.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.orm.Student

@Dao
interface StudentDao {
    @Query("Select * FROM Student")
    fun getAllStudent(): List<Student>

    @Insert(onConflict = REPLACE)
    fun insertStudent(student: Student):Long

    @Update
    fun updateStudent(student: Student):Int

    @Delete
    fun deleteStudent(student: Student):Int
}