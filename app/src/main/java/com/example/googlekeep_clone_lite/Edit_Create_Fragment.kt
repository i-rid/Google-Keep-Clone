package com.example.googlekeep_clone_lite

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import kotlinx.android.synthetic.main.color_img_bottom_sheet.*
import kotlinx.android.synthetic.main.color_img_bottom_sheet.view.*
import kotlinx.android.synthetic.main.fragment_edit__create.*
import kotlin.properties.Delegates


class Edit_Create_Fragment : Fragment(R.layout.fragment_edit__create) {

    var flag by Delegates.notNull<Boolean>()
    var currPos: Int? = arguments?.getInt("id")
    private var selectedNoteColor by Delegates.notNull<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = NotesDBHelper(view.context).writableDatabase


        handleData()

        toolbarCreate!!.setNavigationOnClickListener { v: View? ->
            val title = etTitleCreate.text.toString()
            val note  = etNoteCreate.text.toString()

            if (flag){
                //add to db
                if (title.isNotEmpty()){
                    NoteDB_Table.insertNote(db,NoteModel("2",title,note,selectedNoteColor.toString()))
                    NoteDB_Table.refreshNoteList(db)
                }
            }
            else {
                //if data changed then update

                val update = NoteDB_Table.updateNote(db,NoteModel(arguments?.getString("id"),title,note,selectedNoteColor.toString()))
                NoteDB_Table.refreshNoteList(db)
                if (!update)Toast.makeText(v?.context,"Update Failed",Toast.LENGTH_SHORT).show()
            }
            fragmentManager?.popBackStack()

        }

        toolbarCreate!!.setOnMenuItemClickListener {
                item: MenuItem? ->
            if (item?.itemId == R.id.pinCreate){
                displayMessage("dummy icon 1")
            }
            else if (item?.itemId == R.id.notificationCreate){
                displayMessage("dummy icon 2")
            }
            else if (item?.itemId == R.id.archiveCreate){
                displayMessage("dummy icon 3")
            }
            else fragmentManager?.popBackStack()

            true
        }

        dotCreate.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(it.context)
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_right)
            val delete = bottomSheetDialog.findViewById<LinearLayout>(R.id.deleteLinearLayout)
            delete?.setOnClickListener {

                NoteDB_Table.deleteNote(db, arguments?.getString("id").toString() )
                NoteDB_Table.refreshNoteList(db)
                bottomSheetDialog.dismiss()
                fragmentManager?.popBackStack()
            }
            bottomSheetDialog.show()
        }

        bottomAppBarCreate.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.palleteCreate -> {
                    // Handle search icon press
                    val paletteSheet = BottomSheetDialog(view.context)
                    paletteSheet.setContentView(R.layout.color_img_bottom_sheet)

                    val viewClr1 = paletteSheet.findViewById<View>(R.id.viewColor1)
                    val viewClr2 = paletteSheet.findViewById<View>(R.id.viewColor2)
                    val viewClr3 = paletteSheet.findViewById<View>(R.id.viewColor3)
                    val viewClr4 = paletteSheet.findViewById<View>(R.id.viewColor4)
                    val viewClr5 = paletteSheet.findViewById<View>(R.id.viewColor5)
                    val viewClr6 = paletteSheet.findViewById<View>(R.id.viewColor6)

                    val imgColor1 = paletteSheet.findViewById<ImageView>(R.id.imgColor1)
                    val imgColor2 = paletteSheet.findViewById<ImageView>(R.id.imgColor2)
                    val imgColor3 = paletteSheet.findViewById<ImageView>(R.id.imgColor3)
                    val imgColor4 = paletteSheet.findViewById<ImageView>(R.id.imgColor4)
                    val imgColor5 = paletteSheet.findViewById<ImageView>(R.id.imgColor5)
                    val imgColor6 = paletteSheet.findViewById<ImageView>(R.id.imgColor6)

                    val img1 = paletteSheet.findViewById<CircularRevealCardView>(R.id.cardView1)
                    val img2 = paletteSheet.findViewById<CircularRevealCardView>(R.id.cardView2)
                    val img3 = paletteSheet.findViewById<CircularRevealCardView>(R.id.cardView3)
                    val img4 = paletteSheet.findViewById<CircularRevealCardView>(R.id.cardView4)
                    val img5 = paletteSheet.findViewById<CircularRevealCardView>(R.id.cardView5)

                    val check1 = paletteSheet.findViewById<FrameLayout>(R.id.checkCircle1)
                    val check2 = paletteSheet.findViewById<FrameLayout>(R.id.checkCircle2)
                    val check3 = paletteSheet.findViewById<FrameLayout>(R.id.checkCircle3)
                    val check4 = paletteSheet.findViewById<FrameLayout>(R.id.checkCircle4)
                    val check5 = paletteSheet.findViewById<FrameLayout>(R.id.checkCircle5)

                    img1?.setOnClickListener {
                        check1?.visibility = View.VISIBLE
                        check2?.visibility = View.INVISIBLE
                        check3?.visibility = View.INVISIBLE
                        check4?.visibility = View.INVISIBLE
                        check5?.visibility = View.INVISIBLE
                    }

                    img2?.setOnClickListener {
                        check2?.visibility = View.VISIBLE
                        check1?.visibility = View.INVISIBLE
                        check3?.visibility = View.INVISIBLE
                        check4?.visibility = View.INVISIBLE
                        check5?.visibility = View.INVISIBLE
                    }

                    img3?.setOnClickListener {
                        check3?.visibility = View.VISIBLE
                        check1?.visibility = View.INVISIBLE
                        check2?.visibility = View.INVISIBLE
                        check4?.visibility = View.INVISIBLE
                        check5?.visibility = View.INVISIBLE
                    }

                    img4?.setOnClickListener {
                        check4?.visibility = View.VISIBLE
                        check1?.visibility = View.INVISIBLE
                        check2?.visibility = View.INVISIBLE
                        check3?.visibility = View.INVISIBLE
                        check5?.visibility = View.INVISIBLE
                    }

                    img5?.setOnClickListener {
                        check5?.visibility = View.VISIBLE
                        check1?.visibility = View.INVISIBLE
                        check2?.visibility = View.INVISIBLE
                        check3?.visibility = View.INVISIBLE
                        check4?.visibility = View.INVISIBLE
                    }

                    viewClr1?.setOnClickListener {
                        selectedNoteColor = R.color.noteColor1
                        imgColor1?.setImageResource(R.drawable.ic_outline_check_24)
                        imgColor2?.setImageResource(0)
                        imgColor3?.setImageResource(0)
                        imgColor4?.setImageResource(0)
                        imgColor5?.setImageResource(0)
                        imgColor6?.setImageResource(0)
                        handleNoteColor(selectedNoteColor)
                    }

                    viewClr2?.setOnClickListener {
                        selectedNoteColor = R.color.noteColor2
                        imgColor2?.setImageResource(R.drawable.ic_outline_check_24)
                        imgColor1?.setImageResource(0)
                        imgColor3?.setImageResource(0)
                        imgColor4?.setImageResource(0)
                        imgColor5?.setImageResource(0)
                        imgColor6?.setImageResource(0)
                        handleNoteColor(selectedNoteColor)
                    }

                    viewClr3?.setOnClickListener {
                        selectedNoteColor = R.color.noteColor3
                        imgColor3?.setImageResource(R.drawable.ic_outline_check_24)
                        imgColor1?.setImageResource(0)
                        imgColor2?.setImageResource(0)
                        imgColor4?.setImageResource(0)
                        imgColor5?.setImageResource(0)
                        imgColor6?.setImageResource(0)
                        handleNoteColor(selectedNoteColor)
                    }

                    viewClr4?.setOnClickListener {
                        selectedNoteColor = R.color.noteColor4
                        imgColor4?.setImageResource(R.drawable.ic_outline_check_24)
                        imgColor1?.setImageResource(0)
                        imgColor2?.setImageResource(0)
                        imgColor3?.setImageResource(0)
                        imgColor5?.setImageResource(0)
                        imgColor6?.setImageResource(0)
                        handleNoteColor(selectedNoteColor)
                    }

                    viewClr5?.setOnClickListener {
                        selectedNoteColor = R.color.noteColor5
                        imgColor5?.setImageResource(R.drawable.ic_outline_check_24)
                        imgColor1?.setImageResource(0)
                        imgColor2?.setImageResource(0)
                        imgColor4?.setImageResource(0)
                        imgColor3?.setImageResource(0)
                        imgColor6?.setImageResource(0)
                        handleNoteColor(selectedNoteColor)
                    }

                    viewClr6?.setOnClickListener {
                        selectedNoteColor = R.color.noteColor6
                        imgColor6?.setImageResource(R.drawable.ic_outline_check_24)
                        imgColor1?.setImageResource(0)
                        imgColor2?.setImageResource(0)
                        imgColor4?.setImageResource(0)
                        imgColor5?.setImageResource(0)
                        imgColor3?.setImageResource(0)
                        handleNoteColor(selectedNoteColor)
                    }

                    paletteSheet.show()
                    true
                }
                R.id.addItemCreate -> {
                    // Handle more item (inside overflow menu) press
                    val paletteSheet = BottomSheetDialog(view.context)
                    paletteSheet.setContentView(R.layout.color_img_bottom_sheet)
                    displayMessage("pallete")
                    paletteSheet.show()
                    true
                }
                else -> false
            }
        }


    }


    private fun handleNoteColor(Color: Int) {
        bottomAppBarCreate.background = ColorDrawable(resources.getColor(Color))
        appBarLayoutCreate.background = ColorDrawable(resources.getColor(Color))
        bodyCreate.background         = ColorDrawable(resources.getColor(Color))

    }

    fun handleData(){
        val title = arguments?.getString("title")
        val note = arguments?.getString("note")
        val color = arguments?.getString("color")?.toInt()
        //if null create note else update
        if(arguments !=null){
            etTitleCreate.setText(title )
            etNoteCreate.setText(note )
            if (color != null) {
                selectedNoteColor = color
                handleNoteColor(selectedNoteColor)
            }
            flag = false
        }
        else{
            flag = true
            selectedNoteColor = R.color.noteColor1
            handleNoteColor(selectedNoteColor)
        }

    }

    private fun displayMessage(message : String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}