package com.example.test_image_project.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.test_image_project.R
import com.example.test_image_project.model.ImageModel


class ImageRecyclerViewAdapter(private val items: List<ImageModel>,private val context: Context) : RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val editButton: Button = itemView.findViewById(R.id.editButton)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
        val imageNameText: TextView = itemView.findViewById(R.id.imageNameText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]


        holder.imageNameText.text=item.name
        Glide.with(context)
            .load(item.imageURL)
            .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
            .into(holder.imageView)
        holder.editButton.setOnClickListener {
            //TODO: need to handle edit api
        }
        holder.deleteButton.setOnClickListener {
            //TODO: need to handle delete api
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
