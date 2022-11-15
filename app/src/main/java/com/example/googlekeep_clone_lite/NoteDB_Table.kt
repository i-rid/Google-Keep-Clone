package com.example.googlekeep_clone_lite

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log

object NoteDB_Table {
    val TABLE_NAME = "notes"
    object Columns{
        val ID = "id"
        val TTITLE = "title"
        val NOTE = "note"
        val COLOR = "color"
    }
    val CMD_CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME
        (
        ${Columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${Columns.TTITLE} TEXT,
        ${Columns.NOTE} TEXT,
        ${Columns.COLOR} TEXT
        )
        """.trimIndent()

    fun insertNote(db: SQLiteDatabase,notes: NoteModel){
        val row = ContentValues()
        row.put(Columns.TTITLE,notes.title)
        row.put(Columns.NOTE,notes.note)
        row.put(Columns.COLOR,notes.noteClr)
        db.insert(TABLE_NAME,null,row)
    }

    fun getAllNotes(db: SQLiteDatabase):ArrayList<NoteModel>{
        val list1 = ArrayList<NoteModel>()
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(Columns.ID, Columns.TTITLE, Columns.NOTE, Columns.COLOR),
            null, null,
            null, null,
            null
        )
        while (cursor.moveToNext()){
            val list2 = NoteModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3))
            list1.add(list2)
        }
        return list1
    }

    fun updateNote(db: SQLiteDatabase, notes: NoteModel):Boolean{
        val row = ContentValues()
        row.put(Columns.ID,notes.id)
        row.put(Columns.TTITLE,notes.title)
        row.put(Columns.NOTE,notes.note)
        row.put(Columns.COLOR,notes.noteClr)
        db.update(TABLE_NAME,row,"ID = ?", arrayOf(notes.id))
        return true
    }

    fun deleteNote(db: SQLiteDatabase, id:String):Int{
        return db.delete(TABLE_NAME,"ID = ?", arrayOf(id))
    }


    fun refreshNoteList(db: SQLiteDatabase) {
        val noteList = NoteDB_Table.getAllNotes(db)
        list.clear()
        list.addAll(noteList)
        myAdapter.notifyDataSetChanged()
        Log.d("KEEPCLONE",noteList.toString())
    }
}