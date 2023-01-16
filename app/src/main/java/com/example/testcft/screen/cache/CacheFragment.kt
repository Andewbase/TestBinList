package com.example.testcft.screen.cache

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testcft.R
import com.example.testcft.data.cache.BankCardInfoEntity
import com.example.testcft.databinding.FragmentCacheBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CacheFragment : Fragment(R.layout.fragment_cache) {

    private var mBinding: FragmentCacheBinding?= null
    private val binding get() = mBinding!!

    private val viewModel by viewModels<CacheViewModel>()

    private val adapter by lazy { CacheAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentCacheBinding.bind(view)

        viewModel.allCardInfo.observe(viewLifecycleOwner){
            adapter.submitList(it.asReversed())
            binding.apply {
                rvCardListInfo.adapter = adapter
                rvCardListInfo.setHasFixedSize(true)
            }
        }

        adapter.setOnClick(object: CacheAdapter.OnItemClick{
            override fun onItemClick(bankCardInfoEntity: BankCardInfoEntity) {
                val action = CacheFragmentDirections.actionCacheFragmentToDetailCardInfoFragment(bankCardInfoEntity)
                findNavController().navigate(action)
            }
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

}