package com.example.testcft.screen.detailcardinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.testcft.R
import com.example.testcft.databinding.FragmentDetailCardInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailCardInfoFragment : Fragment(R.layout.fragment_detail_card_info) {

    private var mBinding: FragmentDetailCardInfoBinding? = null
    private val binding get() = mBinding!!

    private val safeArgs: DetailCardInfoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding = FragmentDetailCardInfoBinding.bind(view)

        binding.apply {

            val bankCardInfo = safeArgs.cardDetail

            tvBankDetail.text = bankCardInfo.nameBank
            tvUrlDetail.text = bankCardInfo.urlBank
            tvPhoneDetail.text = bankCardInfo.phoneBank
            tvTypeDetail.text = bankCardInfo.type
            tvSchemeDetail.text = bankCardInfo.scheme
            tvLengthDetail.text = bankCardInfo.length.toString()
            tvBrandDetail.text = bankCardInfo.brand
            tvCountyDetail.text = bankCardInfo.countyBank
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

}