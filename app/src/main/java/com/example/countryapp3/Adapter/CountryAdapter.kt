package com.example.countryapp3.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp3.R
import com.example.countryapp3.databinding.ItemCountryBinding
import com.example.countryapp3.model.Country
import com.example.countryapp3.ui.FeedFragmentDirections
import com.example.countryapp3.utils.downloadFromUrl
import com.example.countryapp3.utils.placeholderProgressBar
import kotlinx.android.synthetic.main.item_country.view.*


class CountryAdapter(private val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */

    class CountryViewHolder(var view: ItemCountryBinding) : RecyclerView.ViewHolder(view.root) {

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        // Create a new view, which defines the UI of the list item
       val inflater = LayoutInflater.from(parent.context)

        val view = DataBindingUtil.inflate<ItemCountryBinding>(inflater,R.layout.item_country,parent,false)

        return CountryViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.view.country = countryList[position]


        /*
        viewHolder.name.text = countryList[position].countryName
        viewHolder.region.text = countryList[position].countryRegion

        viewHolder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)

            Navigation.findNavController(it).navigate(action)
        }
        viewHolder.itemView.imageView.downloadFromUrl(countryList[position].imageUrl, placeholderProgressBar(viewHolder.itemView.context))
         */

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = countryList.size

    fun updateCountryList(newCountryList: ArrayList<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}
