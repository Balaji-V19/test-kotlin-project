package com.example.test_image_project.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.test_image_project.R
import com.example.test_image_project.model.ImageModelResponse
import com.example.test_image_project.view_model.ImageViewModel


class ImageRecyclerViewAdapter(private val items: List<ImageModelResponse>,
                               private val context: Context,
                               private val viewModel: ImageViewModel
) :
    RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder>() {

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
            .load(item.imageUrl)
            .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
            .into(holder.imageView)
        holder.editButton.setOnClickListener {
            val mainIntent = Intent(context, AddOrEditImageActivity::class.java)
            mainIntent.putExtra("imageUrl",item.imageUrl)
            mainIntent.putExtra("name", item.name)
            mainIntent.putExtra("id", item.id)
            context.startActivity(mainIntent)
        }
        holder.deleteButton.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Alert Dialog")
            builder.setMessage("Do you want to Delete ${item.name}?")
            builder.setPositiveButton("Yes",null)
            builder.setNegativeButton("No",null)
            val dialog = builder.create()
            dialog.show()

            val mPositiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val mNegativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)

            mNegativeButton.setOnClickListener {
                dialog.cancel()
            }

            mPositiveButton.setOnClickListener {
                viewModel.deleteImage(item.id)
                dialog.cancel()
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
