package com.example.testcft.screen.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testcft.R
import com.example.testcft.databinding.FragmentMainBinding
import com.example.testcft.util.Resource
import com.example.testcft.util.valideUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private var mBinding: FragmentMainBinding?= null
    private val binding get() = mBinding!!

    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding = FragmentMainBinding.bind(view)

        viewModel.bankCardLiveData.observe(viewLifecycleOwner) { responce ->

            when(responce){
                is Resource.Success -> {
                    binding.pagProgressBar.visibility = View.INVISIBLE
                    responce.data?.let {
                        binding.apply {
                            tvSchemeDetail.text = it.scheme
                            tvTypeDetail.text = it.type
                            tvBankDetail.text = it.bank.name
                            tvBrandDetail.text = it.brand
                            tvUrlDetail.text = it.bank.url
                            tvUrlDetail.setOnClickListener {
                                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(valideUrl(responce.data.bank.url))))
                            }
                            tvLengthDetail.text = it.number.length.toString()
                            tvCountyDetail.text = it.country.name
                            tvCountyDetail.setOnClickListener {
                                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:${responce.data.country.latitude},${responce.data.country.longitude}?z=5")))
                            }
                            tvPhoneDetail.text = it.bank.phone
                            tvPhoneDetail.setOnClickListener {
                                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${responce.data.bank.phone}")))
                            }
                        }
                    }
                }
                is Resource.Error -> {
                    binding.pagProgressBar.visibility = View.INVISIBLE
                    responce.data?.let {
                        Log.e("checkData", "MainFragment: error: ${it}")
                    }
                }
                is Resource.Loading -> {
                    binding.pagProgressBar.visibility = View.VISIBLE
                }
            }
        }


        binding.btnBin.setOnClickListener {

            val text = binding.eTBin.text.toString()

            viewModel.getBankCardInformation(text)
        }



        binding.btnRequest.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToCacheFragment()
            findNavController().navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}