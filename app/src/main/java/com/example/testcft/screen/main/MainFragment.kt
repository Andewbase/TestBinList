package com.example.testcft.screen.main


import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testcft.R
import com.example.testcft.data.cache.BankCardInfoEntity
import com.example.testcft.databinding.FragmentMainBinding
import com.example.testcft.screen.adapter.CacheAdapter
import com.example.testcft.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private var mBinding: FragmentMainBinding?= null
    private val binding get() = mBinding!!

    private val viewModel by viewModels<MainViewModel>()

    private val adapter by lazy { CacheAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding = FragmentMainBinding.bind(view)

       initSearch()

        binding.btnBin.setOnClickListener {
            val text = binding.eTBin.text.toString().filter { it.isDigit() }
            viewModel.getBankCardInformation(text)

            viewModel.bankCardLiveData.observe(viewLifecycleOwner){ response ->
                when(response){
                    is Resource.Success -> {
                        binding.pagProgressBar.visibility = View.INVISIBLE
                        response.data?.let {
                            val action = MainFragmentDirections.actionMainFragmentToDetailCardInfoFragment(response.data.toCardDetail(text))
                            findNavController().navigate(action)
                        }
                    }
                    is Resource.Error -> {
                        binding.pagProgressBar.visibility = View.INVISIBLE
                        val action = MainFragmentDirections.actionMainFragmentToErrorFragmentDialog()
                        findNavController().navigate(action)
                        response.data?.let {}
                    }
                    is Resource.Loading -> {
                        binding.pagProgressBar.visibility = View.VISIBLE
                    }
                }
            }

            binding.eTBin.text!!.clear()
        }

        binding.rvCardHistory.adapter = adapter

        viewModel.allCardInfo.observe(viewLifecycleOwner){
            if (it.isEmpty()){
                binding.tvEmpryList.visibility = View.VISIBLE
            }else{
                adapter.submitList(it)
            }
        }


        adapter.setOnClick(object: CacheAdapter.OnItemClick{
            override fun onItemClick(cardDetail: BankCardInfoEntity) {
                val action = MainFragmentDirections.actionMainFragmentToDetailCardInfoFragment(cardDetail.toCardDetail())
                findNavController().navigate(action)
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    private fun initSearch() = with(binding) {
        eTBin.apply {
            addTextChangedListener { editable ->
                val currentText = editable.toString()
                val length = currentText.length
                if (length == 4) {
                    this.setText(currentText.plus(" "))
                    this.setSelection(length + 1)
                }
                btnBin.isEnabled = length > 8
            }
        }
    }
}