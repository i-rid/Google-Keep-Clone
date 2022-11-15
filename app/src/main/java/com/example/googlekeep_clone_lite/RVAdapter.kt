package com.example.googlekeep_clone_lite

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.card_item.view.*
import kotlinx.android.synthetic.main.fragment_edit__create.view.*
import kotlin.properties.Delegates


class RVAdapter(var list: ArrayList<NoteModel>) : RecyclerView.Adapter<RVAdapter.RVViewHolder>() {

    inner class RVViewHolder(v: View): RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_item,parent,false
        )
        return RVViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {

        var lpos = holder.absoluteAdapterPosition
        holder.itemView.apply {
            title.text = list[position].title.toString()
            descrip.text=list[position].note.toString()
            val clr = list[position].noteClr

            if (clr != R.color.noteColor1.toString())
            {
                rv_row.setCardBackgroundColor(resources.getColor(clr.toInt()))
                rv_row.strokeColor = resources.getColor(clr.toInt())
            }
            else if (clr == R.color.noteColor1.toString())
            {
                rv_row.setCardBackgroundColor(resources.getColor(R.color.noteColor1))
                rv_row.strokeColor = resources.getColor(R.color.cardStrokeColor)
            }

        }

        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                val title = list[lpos].title
                val desc = list[lpos].note


                val bundle = Bundle()
//                bundle.putInt("id",list[holder.absoluteAdapterPosition].id.toInt())
                bundle.putString("id",list[holder.absoluteAdapterPosition].id)

                bundle.putString("title",list[holder.absoluteAdapterPosition].title)
                bundle.putString("note",list[holder.absoluteAdapterPosition].note)
                bundle.putString("color",list[holder.absoluteAdapterPosition].noteClr)

                val fragment = Edit_Create_Fragment()
                fragment.arguments = bundle

                val activity = p0?.context as FragmentActivity
                val fm = activity.supportFragmentManager
                fm.beginTransaction().apply {
                    replace(R.id.drawer_layout,fragment)
                    addToBackStack("fragment")
                    commit()
                }
            }
        })

    }
    override fun getItemCount(): Int = list.size


}