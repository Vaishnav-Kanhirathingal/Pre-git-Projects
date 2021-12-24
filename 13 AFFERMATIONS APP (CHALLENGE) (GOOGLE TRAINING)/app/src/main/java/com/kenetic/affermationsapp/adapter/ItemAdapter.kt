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
        val textView1: TextView = view.findViewById(R.id.item_title_text_view1)
        val imageView1: ImageView = view.findViewById(R.id.item_image1)
        val textView2: TextView = view.findViewById(R.id.item_title_text_view2)
        val imageView2: ImageView = view.findViewById(R.id.item_image2)
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

        holder.textView1.text =
            context.resources.getString(item.stringResourceID1) //setting text for each

        holder.imageView1.setImageResource(item.imageResourceID1)

        holder.textView2.text =
            context.resources.getString(item.stringResourceID2) //setting text for each

        holder.imageView2.setImageResource(item.imageResourceID2)
    }

    //compulsory function
    override fun getItemCount() = dataset.size
}