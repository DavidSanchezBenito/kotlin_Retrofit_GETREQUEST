package com.example.kotlin_retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_items.view.*


//once it has been created, we can call it from the main activity
class myAdapter(val context: Context, val userList: List<MyDataItem>) : RecyclerView.Adapter<myAdapter.ViewHolder>() {
    class ViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        var id: TextView
        var title: TextView
        var body: TextView

        init {
            id = vista.txt_id
            title = vista.txt_title
          body = vista.txt_body
            vista.setOnClickListener {
                Toast.makeText(vista.context, "has pulsado una seleccion", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.row_items,parent, false)
        return ViewHolder(layoutInflater)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //relleno datos del Array por cardView
        holder.id.text = userList[position].id.toString()
        holder.title.text = userList[position].title
        holder.body.text = userList[position].body
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}