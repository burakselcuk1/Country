package com.example.countryapp3.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp3.R
import com.example.countryapp3.model.Country
import com.example.countryapp3.ui.FeedFragmentDirections
import com.example.countryapp3.utils.downloadFromUrl
import com.example.countryapp3.utils.placeholderProgressBar
import kotlinx.android.synthetic.main.item_country.view.*


class CountryAdapter(private val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val region: TextView

        init {
            // Define click listener for the ViewHolder's View.
            name = view.findViewById(R.id.name)
            region = view.findViewById(R.id.region)
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_country, viewGroup, false)



        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element


        viewHolder.name.text = countryList[position].countryName
        viewHolder.region.text = countryList[position].countryRegion

        viewHolder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)

            Navigation.findNavController(it).navigate(action)
        }
        viewHolder.itemView.imageView.downloadFromUrl(countryList[position].imageUrl, placeholderProgressBar(viewHolder.itemView.context))
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = countryList.size

    fun updateCountryList(newCountryList: ArrayList<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}
