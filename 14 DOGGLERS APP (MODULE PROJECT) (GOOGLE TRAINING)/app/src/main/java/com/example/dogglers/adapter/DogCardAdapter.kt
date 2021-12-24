package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.data.DataSource

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {
    private val dataset = DataSource.dogs
    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        // TODO: Declare and initialize all of the list item UI components
        var dogImage: ImageView = view!!.findViewById<ImageView>(R.id.dog_image)
        var dogName = view!!.findViewById<TextView>(R.id.dog_name_text_view)
        var dogAge = view!!.findViewById<TextView>(R.id.dog_age_text_view)
        var dogHobbies = view!!.findViewById<TextView>(R.id.dog_hobbies_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        val viewToReturn: View?
        if (layout==1){
            viewToReturn = LayoutInflater.from(parent.context)
                .inflate(R.layout.vertical_horizontal_list_item,parent,false)
        }
        else if (layout==2){
            viewToReturn = LayoutInflater.from(parent.context)
                .inflate(R.layout.vertical_horizontal_list_item,parent,false)
            //horizontal
        }
        else{
            viewToReturn = LayoutInflater.from(parent.context)
                .inflate(R.layout.grid_list_item,parent,false)
            //grid
        }
        // TODO Inflate the layout

        // TODO: Null should not be passed into the view holder. This should be updated to reflect
        //  the inflated layout.
        return DogCardViewHolder(viewToReturn)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val resources = context?.resources
        // TODO: Get the data at the current position
        val itemSet = dataset[position]
        // TODO: Set the image resource for the current dog
        holder.dogImage.setImageResource(itemSet.imageResourceId)
        // TODO: Set the text for the current dog's name
        holder.dogName.text = itemSet.name
        // TODO: Set the text for the current dog's age
        holder.dogAge.text = resources!!.getString(R.string.dog_age,itemSet.age)
        // TODO: Set the text for the current dog's hobbies by passing the hobbies to the
        //  R.string.dog_hobbies string constant.
        //  Passing an argument to the string resource looks like:
        //  resources?.getString(R.string.dog_hobbies, dog.hobbies)
        holder.dogHobbies.text = resources.getString(R.string.dog_hobbies,itemSet.hobbies)
    }
}
