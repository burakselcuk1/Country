package com.example.countryapp3.ui

import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat.Token.fromBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.Person.fromBundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.media.AudioAttributesCompat.fromBundle
import com.example.countryapp3.R
import com.example.countryapp3.model.Country
import com.example.countryapp3.utils.downloadFromUrl
import com.example.countryapp3.utils.placeholderProgressBar
import com.example.countryapp3.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.*


class CountryFragment : Fragment() {

    private lateinit var viewModel: CountryViewModel
    private var countryUuid = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid

        }
        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRomm(countryUuid)
        getData()
    }

    private fun getData() {
        viewModel.countryLİveData.observe(viewLifecycleOwner, Observer { country ->

            country?.let {

                countryName.text = country.countryName
                countryCapital.text = country.countryCapital
                countryCurrency.text = country.countryCurrency
                countryLanguage.text = country.countryLanguage
                countryRegion.text = country.countryRegion
                context?.let {
                    countryImage.downloadFromUrl(country.imageUrl, placeholderProgressBar(it))

                }
            }

        })
    }

}