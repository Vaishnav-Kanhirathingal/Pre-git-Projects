package com.kenetic.affermationsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kenetic.affermationsapp.R
import com.kenetic.affermationsapp.model.Affirmation

class ItemAdapter(
    private val context: Context,
    private val dataset: List<Affirmation>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title_text_view)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    //compulsory function
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_xml_file, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    //compulsory function
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text =
            context.resources.getString(item.stringResourceID) //setting text for each
        holder.imageView.setImageResource(item.imageResourceID)
    }

    //compulsory function
    override fun getItemCount() = dataset.size
}