package com.example.googlekeep_clone_lite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class NotesDBHelper(context: Context) : SQLiteOpenHelper(
    context,
    "GoogleKeepCloneDB",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(NoteDB_Table.CMD_CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}