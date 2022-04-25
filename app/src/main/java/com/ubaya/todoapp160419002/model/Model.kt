package com.ubaya.todoapp160419002.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name="title")
    var title: String,
    @ColumnInfo(name="notes")
    var notes: String,
    @ColumnInfo(name="priority")
    var priority: Int,
    @ColumnInfo(name="is_done") // karena database SQLite tidak memiliki datatype BOOLEAN sehingga digantikan menggunakan datatype INTEGER dimana nilai TRUE digantikan dengan angka 1 dan nilai FALSE digantikan dengan angka 0
    var isDone: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}