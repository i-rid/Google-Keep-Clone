package com.example.googlekeep_clone_lite

import android.app.ProgressDialog.show
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

public var list = ArrayList<NoteModel>()
public lateinit var myAdapter: RVAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = NotesDBHelper(this).writableDatabase


        //recyclerview
        myAdapter = RVAdapter(list)
        rv.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        rv.adapter = myAdapter
        NoteDB_Table.refreshNoteList(db)


        searchTxt.setOnClickListener {
            Toast.makeText(this,"eta search",Toast.LENGTH_SHORT).show()
        }

        fab.setOnClickListener {
//            CreateNote_Fragment.display(supportFragmentManager)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.drawer_layout,Edit_Create_Fragment())
                addToBackStack("fragment?")
                commit()
            }
        }

        configureToolbar()
        configureNavDrawer()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    private fun configureToolbar() {
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
        actionbar!!.setDisplayHomeAsUpEnabled(true)
    }
    private fun configureNavDrawer() {
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.notesNav -> Toast.makeText(applicationContext,
                    "clicked item 1",Toast.LENGTH_SHORT).show()

                R.id.remindersNav -> Toast.makeText(applicationContext,
                    "clicked item 2",Toast.LENGTH_SHORT).show()

                R.id.newLabelNav -> Toast.makeText(applicationContext,
                    "clicked item 3",Toast.LENGTH_SHORT).show()

                R.id.trashNav -> Toast.makeText(applicationContext,
                    "clicked item 4",Toast.LENGTH_SHORT).show()

                R.id.settingsNav -> Toast.makeText(applicationContext,
                    "clicked item 5",Toast.LENGTH_SHORT).show()

                R.id.helpNav -> Toast.makeText(applicationContext,
                    "clicked item 6",Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home ){
            drawer_layout.openDrawer(GravityCompat.START)
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private fun displayMessage(message : String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}