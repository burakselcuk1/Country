package com.example.countryapp3.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countryapp3.Adapter.CountryAdapter
import com.example.countryapp3.R
import com.example.countryapp3.model.Country
import com.example.countryapp3.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {
    private lateinit var viewModel: FeedViewModel
    private var contryAdapter = CountryAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        countryList.layoutManager = LinearLayoutManager(context)
        countryList.adapter = contryAdapter
        observeLiveData()
        swipeRefreshLayout.setOnRefreshListener {
            countryList.visibility = View.GONE
            countryError.visibility = View.GONE
            countryLoading.visibility = View.VISIBLE

            viewModel.getDataFromApı()
            swipeRefreshLayout.isRefreshing = false
        }
    }


    fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {countries ->

            countries?.let {
                countryList.visibility = View.VISIBLE
                contryAdapter.updateCountryList(countries as ArrayList<Country>)
                countryError.visibility = View.GONE
                countryLoading.visibility = View.GONE
            }

        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {error ->

            error?.let {
            if (it){
                countryError.visibility = View.VISIBLE

            }else{
                countryError.visibility = View.GONE

            }

            }


        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer {loading ->

            loading?.let {
                if (it){
                    countryLoading.visibility = View.VISIBLE
                    countryError.visibility = View.GONE
                    countryList.visibility = View.GONE
                }else{
                    countryLoading.visibility = View.GONE
                }
            }

        })
    }


}