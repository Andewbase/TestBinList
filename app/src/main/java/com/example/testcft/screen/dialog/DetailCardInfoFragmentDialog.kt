package com.example.testcft.screen.dialog

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testcft.databinding.FragmentDetailCardInfoBinding
import com.example.testcft.screen.base.BaseDialogFragment
import com.example.testcft.util.Const.DASH_TEXT
import com.example.testcft.util.valideUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCardInfoFragmentDialog : BaseDialogFragment<FragmentDetailCardInfoBinding>(FragmentDetailCardInfoBinding::inflate){

    private val safeArgs: DetailCardInfoFragmentDialogArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            backButton.setOnClickListener {
                val action = DetailCardInfoFragmentDialogDirections.actionDetailCardInfoFragmentToMainFragment()
                findNavController().navigate(action)
            }

            val bankCardInfo = safeArgs.cardDetail

            cardNumber.text = bankCardInfo.number
            cardSchema.text = bankCardInfo.scheme
            cardTypeValue.text = bankCardInfo.type
            cardBrandValue.text = bankCardInfo.brand
            detailsCountryValue.text = bankCardInfo.countryName
            detailsCountryValue.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:${bankCardInfo.countryLatitude},${bankCardInfo.countryLongitude}?z=5")))
            }
            detailsCurrencyValue.text = bankCardInfo.currency
            detailsBankNameValue.text = bankCardInfo.nameBank ?: DASH_TEXT
            detailsBankCityValue.text = bankCardInfo.cityBank ?: DASH_TEXT
            detailsBankWebsiteValue.text = bankCardInfo.urlBank ?: DASH_TEXT
            if (bankCardInfo.urlBank.isNullOrEmpty()){
                detailsBankWebsiteValue.isClickable = false
            }else{
                detailsBankWebsiteValue.setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(valideUrl(bankCardInfo.urlBank))))
                }
            }


            when{
                bankCardInfo.phoneBank.isNullOrEmpty() -> {
                    detailsBankPhoneValue1.text = DASH_TEXT
                    detailsBankPhoneValue2.text = DASH_TEXT
                }

                bankCardInfo.phoneBank.size == 1 -> {
                    val textPhone1 = bankCardInfo.phoneBank[0].filter { it.isDigit() }
                    detailsBankPhoneValue1.text = textPhone1
                    detailsBankPhoneValue2.text = " - "
                    detailsBankPhoneValue1.setOnClickListener {
                        startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${textPhone1}")))
                    }
                }

                bankCardInfo.phoneBank.size >= 2 ->{
                    val textPhone1 = bankCardInfo.phoneBank[0].filter { it.isDigit() }
                    val textPhone2 = bankCardInfo.phoneBank[1].filter { it.isDigit() }
                    detailsBankPhoneValue1.text = textPhone1
                    detailsBankPhoneValue2.text = textPhone2
                    detailsBankPhoneValue1.setOnClickListener {
                        startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${textPhone1}")))
                    }
                    detailsBankPhoneValue2.setOnClickListener {
                        startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${textPhone2}")))
                    }
                }
            }

        }

    }



}